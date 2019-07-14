package com.java.dao;

import java.util.List;
import java.util.Map;

import com.java.entity.Role;

public interface RoleDao {
	/*
	 * 通过id 查询 角色名称
	 */
	public String getRoleNameById(Integer id)throws Exception;
	/*
	 *通过id 查询 权限菜单id集合	 
	 */
	public String getAuthIdsById(Integer id)throws Exception;
	
	//分页查询 角色列表
	List<Map<String, Object>> roleList(Role role,int pageIndex, int pageSize)throws Exception;
	/*
	 * 查询角色总数
	 */
	int roleCount(Role role)throws Exception; 
	
	/*
	 * 删除角色
	 */
	public int roleDelete(String delIds)throws Exception;
	
	/*
	 * 添加角色
	 */
	public int roleAdd(Role role)throws Exception;
	
	/*
	 * 更新角色
	 *
	 */
	public int roleUpdate(Role role)throws Exception;
	
	/*
	 * 角色页面授权角色  更新
	 */
	public int updateRoleAuthIds(Role role)throws Exception;
}
