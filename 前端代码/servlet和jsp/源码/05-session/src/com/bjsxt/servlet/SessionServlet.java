package com.bjsxt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * session技术学习:
 * 		问题：
 * 			一个用户的不同请求处理的数据共享怎么办？
 * 		解决：
 * 			使用session技术
 * 		原理：
 * 			用户第一次访问服务器，服务器会创建一个session对象给此用户，并将
 * 			该session对象的JSESSIONID使用Cookie技术存储到浏览器中，保证
 * 			用户的其他请求能够获取到同一个session对象，也保证了不同请求能够获取到
 * 			共享的数据。
 * 		特点：
 * 			存储在服务器端
 * 			服务器进行创建
 * 			依赖Cookie技术
 * 			一次会话
 * 			默认存储时间是30分钟
 *		作用：
 *			解决了一个用户不同请求处理的数据共享问题
 *		使用：
 *			创建session对象/获取session对象
				HttpSession hs=req.getSession();
				如果请求中拥有session的标识符也就是JSESSIONID，则返回其对应的session队形
				如果请求中没有session的标识符也就是JSESSIONID，则创建新的session对象，并将其JSESSIONID作为从cookie数据存储到浏览器内存中
 * 				如果session对象是失效了，也会重新创建一个session对象，并将其JSESSIONID存储在浏览器内存中。
 * 			设置session存储时间
 * 				hs.setMaxInactiveInterval(int seconds);
 * 				注意：
 * 					在指定的时间内session对象没有被使用则销毁，如果使用了则重新计时。
 * 			设置session强制失效
 * 				hs.invalidate();
 * 			存储和获取数据
 * 				存储：hs.setAttribute(String name,Object value);
 * 				获取：hs.getAttribute(String name) 返回的数据类型为Object
 * 				注意：
 * 					存储的动作和取出的动作发生在不同的请求中，但是存储要先于取出执行。
 * 			使用时机:
 * 				一般用户在登陆web项目时会将用户的个人信息存储到Sesion中，供该用户的其他请求使用。
 * 			总结：
 * 				session解决了一个用户的不同请求的数据共享问题，只要在JSESSIONID不失效和session对象不失效的情况下。
 * 				用户的任意请求在处理时都能获取到同一个session对象。
 * 			作用域：
 * 				一次会话
 * 				在JSESSIONID和SESSION对象不失效的情况下为整个项目内。
 * 			session失效处理：
 * 				将用户请求中的JSESSIONID和后台获取到的SESSION对象的JSESSIONID进行比对，如果一致
 * 				则session没有失效，如果不一致则证明session失效了。重定向到登录页面，让用户重新登录。
 * 		注意：
 * 			JSESSIONID存储在了Cookie的临时存储空间中，浏览器关闭即失效。
 * 
 * @author MyPC
 *
 */
public class SessionServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		//获取请求信息
			String name="张三";
		//处理请求信息
			//创建session对象
			HttpSession hs=req.getSession();
			//设置session的存储时间
				//hs.setMaxInactiveInterval(5);
			System.out.println(hs.getId());
			//设置session强制失效
				//hs.invalidate();
			//存储数据
				hs.setAttribute("name",name);
		//响应处理结果
			//直接响应
			resp.getWriter().write("session学习");
			//请求转发
			//重定向
	}
}
