package com.java.dao;

import net.sf.json.JSONArray;

public interface AuthDao {
	
		//通过父ID得到多个菜单
		public JSONArray getAuthsByParentId(String parentId,String authIds)throws Exception;
		//通过父id得到权限菜单数据并塞到jsonObject中	
		public JSONArray getCheckedAuthsByParentId(String parentId,String authIds)throws Exception;
}
