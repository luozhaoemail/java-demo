package com.bjsxt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCookieServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置请求编码格式
			req.setCharacterEncoding("utf-8");
		//设置响应编码格式
			resp.setContentType("text/html;charset=utf-8");
		//获取请求信息
			//获取Cookie信息
				Cookie[] cks=req.getCookies();
				if(cks!=null){
					for(Cookie c:cks){
						String name=c.getName();
						String value=c.getValue();
						System.out.println(name+":"+value);
					}
				}
				
			//获取用户信息
		//处理请求信息
		//响应处理结果
			//直接响应
			//请求转发
			//重定向
	}
}
