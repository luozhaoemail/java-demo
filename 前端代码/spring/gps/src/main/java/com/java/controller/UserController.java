package com.java.controller;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.entity.User;
import com.java.service.UserService;
import com.java.utils.CryptographyUtil;
import com.java.utils.ResponseUtil;

/**
 * @ClassName: UserController 
 * @date 2017年10月8日 下午10:08:33 
 * @
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), CryptographyUtil.md5(user.getPassword(), "lz"));
		try{
			subject.login(token); // 登录验证
			return "redirect:/admin/main.jsp";
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("user", user);
			request.setAttribute("errorInfo", "用户名或者密码错误！");
			return "login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		Subject subject = SecurityUtils.getSubject();
		subject.logout(); //退出
		
		//方式2：清除session
		//session.invalidate();
	
		return "redirect:/admin/login.jsp";

	}
	
	
	@RequestMapping("/modifypwd")
	public void modifyPasswd(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		User user = (User) request.getSession().getAttribute("currentUser");		
		String newPassword = request.getParameter("newPassword"); 		
		String oldPassword = request.getParameter("oldPassword");
		
		String flag="false";
		String old_md5 = CryptographyUtil.md5(oldPassword, "lz"); 
		if(user.getPassword().equals(old_md5)){						
			String new_md5 =CryptographyUtil.md5(newPassword, "lz");
			userService.setPwd(user.getUserName(), new_md5);	
			flag="true";
		}		
		String str = "{'old_md5':'"+flag+"'}";
		JSONObject result = JSONObject.fromObject(str);
		ResponseUtil.write(response, result);	
	}
	
	
}
