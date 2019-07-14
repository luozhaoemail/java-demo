package com.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.AuthDao;
import com.java.service.AuthService;

import net.sf.json.JSONArray;
@Service("authService")
public class AuthServiceImpl implements AuthService{

	@Autowired
	private AuthDao authDao;
	
	//通过父ID得到多个菜单
	public JSONArray getAuthsByParentId(String parentId, String authIds) throws Exception {
		// TODO Auto-generated method stub
		return authDao.getAuthsByParentId(parentId, authIds);
	}
	//通过父id 及根id得到所有菜单权限
	public JSONArray getCheckedAuthsByParentId(String parentId, String authIds) throws Exception {
		// TODO Auto-generated method stub
		return authDao.getCheckedAuthsByParentId(parentId, authIds);
	}

}
