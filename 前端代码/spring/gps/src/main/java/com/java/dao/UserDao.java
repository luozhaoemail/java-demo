package com.java.dao;

import com.java.entity.User;


public interface UserDao {
	
	//通过用户名跟密码得到用户
	public User getByUserName(String userName)throws Exception;
	
	//通过用户名跟密码得到用户
	public int setPwd(String userName, String newPwd)throws Exception;
}
