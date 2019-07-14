package com.java.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.entity.Role;
import com.java.service.RoleService;
import com.java.utils.Pagination;
import com.java.utils.ResponseUtil;
import com.java.utils.StringUtil;

import net.sf.json.JSONObject;

/**
 * 用户管理员控制层
 */
@Controller
@RequestMapping("/admin/role")
public class RoleAdminController {
	
	@Autowired
	private RoleService roleService;
	
	//角色列表
	@RequestMapping("/list")
	@ResponseBody
	private void roleList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Integer pageIndex = NumberUtils.toInt(request.getParameter("page"), 0);
		Integer pageSize = NumberUtils.toInt(request.getParameter("rows"), 20);
		
		Role role = new Role();
		String s_roleName = request.getParameter("s_roleName");
		if(StringUtil.isNotEmpty(s_roleName)){
			role.setRoleName(s_roleName);
		}
		Pagination page = roleService.page(role, pageIndex, pageSize);
		JSONObject result = new JSONObject();
		result.put("rows", page.getRows());
		result.put("total", page.getTotal());
//		result.put("success", page);
		ResponseUtil.write(response, result);
	}
	/**
	 * 
	 *
	 * @Title: auth 
	 * <p>Description:正式授权角色权限</p>
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping("/auth")
	@ResponseBody
	private void auth(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String roleId = request.getParameter("roleId");
		String authIds = request.getParameter("authIds");
		Role role = new Role(Integer.parseInt(roleId), authIds);
		try{
			JSONObject result=new JSONObject();
			int updateNums = roleService.updateRoleAuthIds(role);
            if(updateNums>0){
            	result.put("success", true);
			}else{
				result.put("errorMsg", "授权失败");
			}
			ResponseUtil.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
