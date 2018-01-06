package cn.zhb.dao;

import java.awt.List;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.zhb.domain.Privilege;
import cn.zhb.domain.Resource;
import cn.zhb.utils.JdbcUtils;

public class ResourceDao {
	// 添加资源进数据库
    public void add(Resource r) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "insert into resource(id,uri,description) values(?,?,?)";
            Object[] params = {r.getId(),r.getUri(),r.getDescription()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Resource find(String uri) { // 根据uri进行查找资源
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from resource where uri=?";
            Resource r = (Resource) runner.query(sql, uri, new BeanHandler(Resource.class));

            if (r == null) {
                return null;
            }

            // 得到控制资源的权限(涉及多表查询)
            sql = "select p.* from resource r,privilege p where r.uri=? and p.id=r.privilege_id";
            Privilege p = (Privilege) runner.query(sql, uri, new BeanHandler(Privilege.class));
            r.setPrivilege(p);
            return r;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Resource findById(String id) { // 根据id进行查找资源
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from resource where id=?";
            Resource r = (Resource) runner.query(sql, id, new BeanHandler(Resource.class));

            if (r == null) {
                return null;
            }

            // 得到控制资源的权限(涉及多表查询)
            sql = "select p.* from resource r,privilege p where r.id=? and p.id=r.privilege_id";
            Privilege p = (Privilege) runner.query(sql, id, new BeanHandler(Privilege.class));
            r.setPrivilege(p);
            return r;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList getAll() {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from resource";
            ArrayList<Resource> list = (ArrayList<Resource>) runner.query(sql, new BeanListHandler(Resource.class));

            for (Resource r : list) {
                // 得到控制资源的权限(涉及多表查询)
                sql = "select p.* from resource r,privilege p where r.id=? and p.id=r.privilege_id";
                Privilege p = (Privilege) runner.query(sql, r.getId(), new BeanHandler(Privilege.class));
                r.setPrivilege(p);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePrivilege(Resource r, Privilege p) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "update resource set privilege_id=? where id=?";
            Object[] params = {p.getId(), r.getId()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
