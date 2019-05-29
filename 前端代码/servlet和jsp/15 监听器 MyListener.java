package com.bjsxt.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * 监听器的使用：
 * 	作用:
 * 		监听作用域对象request、session、application的创建、销毁和内容的改变
 * 	使用:
 * 		创建一个实现了指定接口的java类
 * 			监听request--->ServletRequestListener  监听request对象的创建和销毁
 * 				requestInitialized(ServletRequestEvent sre)//创建
 * 				requestDestroyed(ServletRequestEvent sre)//销毁
 * 				注意:
 * 					sre形参可以监听request对象
 * 					sre.getServletRequest();
 
 *			监听request--->ServletRequestAttributeListener  监听request作用域数据的变更。
				attributeAdded(ServletRequestAttributeEvent srae)
 * 				attributeRemoved(ServletRequestAttributeEvent srae)	
 * 				attributeReplaced(ServletRequestAttributeEvent srae)
 * 				注意：形参可以获取被监听的数据	
 * 					srae.getName()  获取监听数据的键
 * 					srae.getValue() 获取监听数据的值
 
 *			监听session--->HttpSessionListener 监听session的创建和销毁
 *				sessionCreated(HttpSessionEvent se)  创建
 *				sessionDestroyed(HttpSessionEvent se) 销毁
 *				注意：形参可以获取被监听的session对象
 *					se.getSession();
 
 *			监听session--->HttpSessionAttributeListener 监听session数据的变更
 *				attributeAdded(HttpSessionBindingEvent event)
 *				attributeRemoved(HttpSessionBindingEvent event)
 *				attributeReplaced(HttpSessionBindingEvent event)
 *				注意:形参可以获取被监听的数据
 *					event.getName() 	获取数据的键名
 *					event.getValue()	获取数据的值
 
 *			监听application--->ServletContextListener 监听application对象的初始化和销毁
 *				contextInitialized(ServletContextEvent sce) 初始化    服务器启动
 *				contextDestroyed(ServletContextEvent sce)	销毁	     服务器关闭
 *				注意：
 *					形参可以获取当前application对象。
 *					sce.getServletContext();
 
 			监听application--->ServletContextAttributeListener 监听数据的变更
 					attributeAdded(ServletContextAttributeEvent event)
 *					attributeRemoved(ServletContextAttributeEvent event)
 *					attributeReplaced(ServletContextAttributeEvent event)
 *				注意：
 *					形参可以获取当前监听的数据
 *					event.getName()  获取数据的键名
 *					event.getValue() 获取数据的值
 
 * 		在web.xml中配置监听器类
 * 			<listener>
				<listener-class>com.bjsxt.listener.MyListener</listener-class>
			</listener>
			
		案例：
			统计当前在线人数。
			统计网页浏览器次数。
 * @author MyPC
 *
 */
public class MyListener implements ServletRequestListener,ServletRequestAttributeListener,HttpSessionListener,HttpSessionAttributeListener,ServletContextListener,ServletContextAttributeListener{
	//request对象销毁
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("我被销毁了");
		
	}
	//request对象创建
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("我被创建了");
		
	}
	//监听request作用域数据的添加
	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		
		System.out.println("request中增加了一条数据-"+srae.getName()+":"+srae.getValue());
		
	}
	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		
	}
/*------------------------------------------------------------------------------*/
	//监听session的创建
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session被创建了");
		
	}
	//监听session的销毁
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session被销毁了");
		
	}
	//监听session数据的表更
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("session中增加了一条数据"+event.getName()+":"+event.getValue());
	}
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}
/*------------------------------------------------------------------------------*/
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("application对象被初始化了");
		
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("application对象被销毁了");
		
	}
	//监听application的数据变更
	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		System.out.println("application中存储了数据:"+event.getName()+":"+event.getValue());
		
	}
	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		
	}
		
	
}
