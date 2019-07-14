package com.java.service;

import net.sf.json.JSONArray;

public interface AuthService {

	//通过父ID得到多个菜单
	public JSONArray getAuthsByParentId(String parentId,String authIds)throws Exception;
	
	//通过父id 及根id得到所有菜单权限
	public JSONArray getCheckedAuthsByParentId(String parentId,String authIds)throws Exception;
}
