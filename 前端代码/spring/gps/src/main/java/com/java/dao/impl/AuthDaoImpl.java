package com.java.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.java.dao.AuthDao;
import com.java.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Repository("authDao")
public class AuthDaoImpl implements AuthDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JSONArray getAuthByParentId(String parentId,String authIds)throws Exception{
		JSONArray jsonArray=new JSONArray();
		String sql="select * from t_auth where parentId = '"+parentId+"' and authId in ("+authIds+") ";
		//System.out.println("getAuthByParentId sql="+sql);
		
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql);
		/*
		 *  id：节点ID，对加载远程数据很重要。
			text：显示节点文本。
			state：节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
			checked：表示该节点是否被选中。
			attributes: 被添加到节点的自定义属性。
			children: 一个节点数组声明了若干节点。
		 */
		for (Map<String, Object> map : queryForList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id",map.get("authId"));
			jsonObject.put("text",map.get("authName").toString());
			jsonObject.put("state",map.get("state").toString());
			jsonObject.put("iconCls",map.get("iconCls").toString());
			JSONObject attributeObject = new JSONObject();
			attributeObject.put("authPath",map.get("authPath").toString());
			jsonObject.put("attributes", attributeObject);//重新绑定菜单路径
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	//通过父ID得到多个菜单
	public JSONArray getAuthsByParentId(String parentId, String authIds) throws Exception {
		JSONArray jsonArray = this.getAuthByParentId(parentId, authIds);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getAuthsByParentId(jsonObject.getString("id"), authIds));
				//递归获取  我们定义open 为子菜单或者是就一个菜单，closed 为父菜单
			}
		}
		return jsonArray;
	}
	//通过父id得到权限菜单数据并塞到jsonObject中
	public JSONArray getCheckedAuthByParentId(String parentId,String authIds)throws Exception{
		JSONArray jsonArray=new JSONArray();
		String sql="select * from t_auth where parentId = '"+parentId+"'";
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> map : queryForList) {
			JSONObject jsonObject = new JSONObject();
			int authId = Integer.parseInt(map.get("authId").toString());
			jsonObject.put("id", authId);
			jsonObject.put("text",map.get("authName").toString());
			jsonObject.put("state",map.get("state").toString());
			jsonObject.put("iconCls",map.get("iconCls").toString());
			if(StringUtil.existStrArr(authId+"", authIds.split(","))){
				jsonObject.put("checked", true);//选中该节点
			}
			JSONObject attributeObject = new JSONObject();
			attributeObject.put("authPath",map.get("authPath").toString());
			jsonObject.put("attributes", attributeObject);//重新绑定菜单路径
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	/**
	 * 
	 *
	 * @Title: getCheckedAuthsByParentId 
	 * <p>Description:通过父id 及根id得到所有菜单权限</p>
	 * @param @param con
	 * @param @param parentId
	 * @param @param authIds
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return JSONArray    返回类型 
	 * @throws
	 */
	public JSONArray getCheckedAuthsByParentId(String parentId,String authIds)throws Exception{
		JSONArray jsonArray = this.getCheckedAuthByParentId(parentId,authIds);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getCheckedAuthsByParentId(jsonObject.getString("id"),authIds));
			}
		}
		return jsonArray;
	}
}
