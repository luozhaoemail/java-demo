package com.java.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.java.dao.RoleDao;
import com.java.entity.PageBean;
import com.java.entity.Role;
import com.java.utils.StringUtil;
@Repository("roleDao")
public class RoleDaoImpl implements RoleDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public String getRoleNameById(Integer id) throws Exception {
		
//		StringBuffer sb  = new StringBuffer();
//		sb.append(" select roleName from t_role ");
//		sb.append(" where roleId=:roleid ");
//		//封装参数
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("roleid", id);
		return null;
		
	}

	public String getAuthIdsById(Integer id) throws Exception {
		String authIds = null;
		String sql = "select authIds from t_role where roleId = '"+id+"'";
		Map<String, Object> queryForMap = jdbcTemplate.queryForMap(sql);
		if(queryForMap.size()>0){
			authIds = queryForMap.get("authIds").toString();
		}
		return authIds;
	}

	public List<Role> roleList(PageBean pageBean, Role role) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int roleCount(Role role) throws Exception {
		StringBuffer sb=new StringBuffer("select count(*) as total from t_role ");
		if(StringUtil.isNotEmpty(role.getRoleName())){
			sb.append(" and roleName like '%"+role.getRoleName()+"%'");
		}
		return this.jdbcTemplate.queryForObject(sb.toString(),Integer.class);
	}

	public int roleDelete(String delIds) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int roleAdd(Role role) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int roleUpdate(Role role) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateRoleAuthIds(Role role) throws Exception {
		// TODO Auto-generated method stub
		String sql = " update t_role set authIds='"+role.getAuthIds()+"' where roleId='"+role.getRoleId()+"' ";
		//封装参数
		return this.jdbcTemplate.update(sql);
	}

	public List<Map<String, Object>> roleList(Role role, int pageIndex, int pageSize) throws Exception {
		StringBuilder  sb = new StringBuilder();
		sb.append("SELECT * from t_role ");
		if(StringUtil.isNotEmpty(role.getRoleName())){
			sb.append(" and roleName like '%"+role.getRoleName()+"%'");
		}
		//封装参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//分页条件
		if(pageIndex >= 0 && pageSize > 0){
			sb.append("LIMIT :pageIndex, :pageSize");
			paramMap.put("pageIndex", pageIndex);
			paramMap.put("pageSize", pageSize);
		}
		return this.namedParameterJdbcTemplate.queryForList(sb.toString(), paramMap);
	}

}
