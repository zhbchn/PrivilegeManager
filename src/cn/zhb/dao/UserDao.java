package cn.zhb.dao;

import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.zhb.domain.Role;
import cn.zhb.domain.User;
import cn.zhb.utils.JdbcUtils;

public class UserDao {
	public void add(User user) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "insert into user(id,username,password,description) values(?,?,?,?)";
            Object[] params = {user.getId(), user.getUsername(), user.getPassword(), user.getDescription()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User find(String id) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from user where id=?";
            User user = (User) runner.query(sql, id, new BeanHandler(User.class));

            if (user == null) {
                return null;
            }

            // 找出用户拥有的所有角色
            sql = "select r.* from user_role ur,role r where ur.user_id=? and r.id=ur.role_id";
            ArrayList<Role> list = (ArrayList<Role>) runner.query(sql, id, new BeanListHandler(Role.class));
            user.getRoles().addAll(list);
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 登录
    public User find(String username, String password) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from user where username=? and password=?";
            Object[] params = {username, password};
            User user = (User) runner.query(sql, params, new BeanHandler(User.class));

            if (user == null) {
                return null;
            }

            // 找出用户拥有的所有角色
            sql = "select r.* from user_role ur,role r where ur.user_id=? and r.id=ur.role_id";
            ArrayList<Role> list = (ArrayList<Role>) runner.query(sql, user.getId(), new BeanListHandler(Role.class));
            user.getRoles().addAll(list);
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUserRoles(User user, ArrayList<Role> roles) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            // 先删除用户所有的角色
            String sql = "delete from user_role where user_id=?";
            runner.update(sql, user.getId());

            // 再为用户赋予新的角色
            for (Role role : roles) {
                sql = "insert into user_role(user_id,role_id) values(?,?)";
                Object[] params = {user.getId(), role.getId()};
                runner.update(sql, params);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> getAll() {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from user";
            ArrayList<User> list = (ArrayList<User>) runner.query(sql, new BeanListHandler(User.class));
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
