package com.java.service;

import java.util.List;

import com.java.entity.PageBean;
import com.java.entity.Role;
import com.java.utils.Pagination;


public interface RoleService {
	/*
	 * 通过id 查询 角色名称
	 */
	public String getRoleNameById(Integer id)throws Exception;
	/*
	 *通过id 查询 权限菜单id集合	 
	 */
	public String getAuthIdsById(Integer id)throws Exception;
	/*
	 * 角色列表
	 */
	public Pagination page(Role role, int pageIndex, int pageSize)throws Exception;
	
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
