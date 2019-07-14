package com.java.service.impl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class InitComponent implements ServletContextListener,ApplicationContextAware{

	private static ApplicationContext applicationContext;
	/**
	 * 设置applicationContext
	 *
	 * <p>Title: setApplicationContext</p> 
	 * <p>Description: </p> 
	 * @param applicationContext
	 * @throws BeansException 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}
	/**
	 * 用与初始化
	 *
	 * <p>Title: contextInitialized</p> 
	 * <p>Description: </p> 
	 * @param sce 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext application = sce.getServletContext();
		//后面为要加载到页面的缓存内容，后续开发
	}
	/**
	 * 用于销毁
	 *
	 * <p>Title: contextDestroyed</p> 
	 * <p>Description: </p> 
	 * @param sce 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
