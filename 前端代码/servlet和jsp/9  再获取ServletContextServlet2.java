package com.bjsxt.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextServlet2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建ServletContext对象
			ServletContext sc=this.getServletContext();
		//获取数据
			System.out.println("ServletContextServlet2.service()"+sc.getAttribute("str"));
	}
}
