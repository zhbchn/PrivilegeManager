package cn.zhb.dao;

import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.zhb.domain.Privilege;
import cn.zhb.domain.Role;
import cn.zhb.utils.JdbcUtils;

public class RoleDao {
	public void add(Role role) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "insert into role(id,name,description) values(?,?,?)";
            Object[] params = {role.getId(),role.getName(),role.getDescription()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Role find(String id) {
        try {
            // 1. 查找角色的基本信息
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from role where id=?";
            Role role = (Role) runner.query(sql, id, new BeanHandler(Role.class));

            // 2. 找出角色的所有权限
            sql = "select p.* from role_privilege rp,privilege p where rp.role_id=? and p.id=rp.privilege_id";
            ArrayList<Privilege> list = (ArrayList<Privilege>) runner.query(sql, id, new BeanListHandler(Privilege.class));
            role.getPrivileges().addAll(list);
            return role;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList getAll() {
        try {
            // 1. 查找角色的基本信息
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from role";
            ArrayList<Role> list = (ArrayList<Role>) runner.query(sql, new BeanListHandler(Role.class));

            // 2. 找出每一个角色拥有的所有权限
            for (Role r : list) {
                sql = "select p.* from role_privilege rp,privilege p where rp.role_id=? and p.id=rp.privilege_id";
                ArrayList<Privilege> listp = (ArrayList<Privilege>) runner.query(sql, r.getId(), new BeanListHandler(Privilege.class));
                r.getPrivileges().addAll(listp);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 更新角色的权限
    public void updateRolePrivileges(Role role, ArrayList<Privilege> privileges) {
        try {
            // 删除角色拥有的所有权限
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "delete from role_privilege where role_id=?";
            runner.update(sql, role.getId());

            // 为角色赋予新的权限
            for (Privilege p : privileges) {
                sql = "insert into role_privilege(role_id,privilege_id) values(?,?)";
                Object[] params = {role.getId(), p.getId()};
                runner.update(sql, params);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
