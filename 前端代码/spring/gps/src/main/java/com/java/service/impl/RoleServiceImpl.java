package com.java.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.RoleDao;
import com.java.entity.Role;
import com.java.service.RoleService;
import com.java.utils.Pagination;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	public String getRoleNameById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAuthIdsById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return roleDao.getAuthIdsById(id);
	}

	public Pagination page(Role role, int pageIndex, int pageSize) throws Exception {
		//判断前台当前页以及计算当前页的行数
		if (pageIndex > 0) {
			pageIndex = (pageIndex - 1) * pageSize;
		} else {
			pageIndex = 0;
		}
		//从dao层拿数据  主要是每页记录数（list1）与总记录数(count1)
		Pagination page = new Pagination();
		//定义Map集合用于存放每页行数
		List<Map<String, Object>> rows = null;
		//定义存放 合计一栏集合
		List<Map<String,Object>> footer = null;
		//定义total 存总记录数
		int total =0;
		rows = this.roleDao.roleList(role, pageIndex, pageSize);
		total = this.roleDao.roleCount(role);
		page.setRows(rows);
		page.setTotal(total);
		return page;
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
		return roleDao.updateRoleAuthIds(role);
	}


}
