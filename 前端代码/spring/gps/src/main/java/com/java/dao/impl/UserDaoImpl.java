package com.java.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.java.dao.UserDao;
import com.java.entity.User;
@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public User getByUserName(String userName) throws Exception {	
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from t_user where userName ='"+userName+"' ");
		final User user=new User();
		jdbctemplate.query(sb.toString(), new Object[]{}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setRoleId(rs.getInt("roleId"));
				user.setUserType(rs.getInt("userType"));
				user.setUserDescription(rs.getString("userDescription"));
			}
		});
		return user;
	}

	public int setPwd(String userName,String newPwd) throws Exception {
		String sql= "update t_user set password=? where userName=?";
		int x = jdbctemplate.update(sql, newPwd, userName);
		return x;		
	}
}
