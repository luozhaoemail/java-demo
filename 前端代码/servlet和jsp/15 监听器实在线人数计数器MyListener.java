package com.bjsxt.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//监听session--->HttpSessionListener
//监听application--->ServletContextListener 
public class MyListener implements HttpSessionListener,ServletContextListener{
	
	//session被创建时人数自增
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		//获取ServletContext对象
		ServletContext sc=se.getSession().getServletContext();
		//获取在线统计人数的变量
		int count=(int) sc.getAttribute("count");
		//存储
		sc.setAttribute("count",++count);
		
	}
	//session被销毁时人数自减
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
				//获取ServletContext对象
				ServletContext sc=se.getSession().getServletContext();
				//获取在线统计人数的变量
				int count=(int) sc.getAttribute("count");
				//存储
				sc.setAttribute("count",--count);
		
	}
	//application对象初始化
	@Override
	public void contextInitialized(ServletContextEvent sce) {
			//获取application
			ServletContext sc=sce.getServletContext();
			//在application对象存储变量用来统计在线人数
			sc.setAttribute("count",0);		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	

}
