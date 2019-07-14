package com.java.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.service.UserService;

/**
 * 用户管理员控制层
 */
@Controller
@RequestMapping("/admin/user")
public class UserAdminController {
	
	@Resource
	private UserService userService;
}
