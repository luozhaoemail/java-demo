package com.java.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.entity.User;
import com.java.service.AuthService;
import com.java.service.RoleService;
import com.java.utils.ResponseUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/admin/auth")
public class AuthAdminController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthService authService;
	/*
	 * 点击菜单加载权限
	 */
	@RequestMapping("/menu")
	@ResponseBody
	private void menuAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parentId = request.getParameter("parentId");
		try{
			HttpSession session = request.getSession();
			User currentUser = (User)session.getAttribute("currentUser");
			String authIds = roleService.getAuthIdsById(currentUser.getRoleId());
			JSONArray jsonArray = authService.getAuthsByParentId(parentId,authIds);
			//System.out.println("jsonArray===="+jsonArray);
			ResponseUtil.write(response, jsonArray);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 角色分配权限
	 * @Title: authMenu 
	 * <p>Description:TODO(这里用一句话描述这个方法的作用)</p>
	 * @param @param request
	 * @param @param response    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping("/authMenu")
	@ResponseBody
	private void authMenu(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String parentId = request.getParameter("parentId");//根 id = -1
		String roleId = request.getParameter("roleId");//选中行的id
		try{
			String authIds = roleService.getAuthIdsById(Integer.parseInt(roleId));
			JSONArray jsonArray = authService.getCheckedAuthsByParentId(parentId,authIds);
			ResponseUtil.write(response, jsonArray);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
