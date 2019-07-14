package com.java.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.java.entity.User;
import com.java.service.UserService;

public class MyRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	/**
	 * 为当前的登录的用户角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		String userName = (String) token.getPrincipal();
		User user = null;
		AuthenticationInfo authcInfo = null;
		try {
		  user = userService.getByUserName(userName);
		  if(user!=null)
		  {
			    SecurityUtils.getSubject().getSession().setAttribute("currentUser", user);// 把当前用户信息存到session中 
				authcInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), "xxx");
				return authcInfo;
		  }
			  
		} catch (Exception e) {			
			System.out.println("用户名或者密码错误！！！！"+MyRealm.class.getName());
			//e.printStackTrace();			
		}
		return authcInfo;
	}

}
