package com.java.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.UserDao;
import com.java.entity.User;
import com.java.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserDao userDao;
	
	public User getByUserName(String userName) throws Exception {		
		return userDao.getByUserName(userName);
	}
	
	
	public int setPwd(String userName,String newPwd) throws Exception{
		return userDao.setPwd(userName, newPwd);
	}
	

}
