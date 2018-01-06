package cn.zhb.dao;

import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.zhb.domain.Privilege;
import cn.zhb.utils.JdbcUtils;

public class PrivilegeDao {
	 public void add(Privilege p) {
	        try {
	            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
	            String sql = "insert into privilege(id,name,description) values(?,?,?)";
	            Object[] params = {p.getId(), p.getName(), p.getDescription()};
	            runner.update(sql, params);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    public Privilege find(String id) {
	        try {
	            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
	            String sql = "select * from privilege where id=?";
	            return (Privilege) runner.query(sql, id, new BeanHandler(Privilege.class));
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    public ArrayList getAll() {
	        try {
	            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
	            String sql = "select * from privilege";
	            return (ArrayList) runner.query(sql, new BeanListHandler(Privilege.class));
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
