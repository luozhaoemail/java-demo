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
 * ��������ʹ�ã�
 * 	����:
 * 		�������������request��session��application�Ĵ��������ٺ����ݵĸı�
 * 	ʹ��:
 * 		����һ��ʵ����ָ���ӿڵ�java��
 * 			����request--->ServletRequestListener  ����request����Ĵ���������
 * 				requestInitialized(ServletRequestEvent sre)//����
 * 				requestDestroyed(ServletRequestEvent sre)//����
 * 				ע��:
 * 					sre�βο��Լ���request����
 * 					sre.getServletRequest();
 
 *			����request--->ServletRequestAttributeListener  ����request���������ݵı����
				attributeAdded(ServletRequestAttributeEvent srae)
 * 				attributeRemoved(ServletRequestAttributeEvent srae)	
 * 				attributeReplaced(ServletRequestAttributeEvent srae)
 * 				ע�⣺�βο��Ի�ȡ������������	
 * 					srae.getName()  ��ȡ�������ݵļ�
 * 					srae.getValue() ��ȡ�������ݵ�ֵ
 
 *			����session--->HttpSessionListener ����session�Ĵ���������
 *				sessionCreated(HttpSessionEvent se)  ����
 *				sessionDestroyed(HttpSessionEvent se) ����
 *				ע�⣺�βο��Ի�ȡ��������session����
 *					se.getSession();
 
 *			����session--->HttpSessionAttributeListener ����session���ݵı��
 *				attributeAdded(HttpSessionBindingEvent event)
 *				attributeRemoved(HttpSessionBindingEvent event)
 *				attributeReplaced(HttpSessionBindingEvent event)
 *				ע��:�βο��Ի�ȡ������������
 *					event.getName() 	��ȡ���ݵļ���
 *					event.getValue()	��ȡ���ݵ�ֵ
 
 *			����application--->ServletContextListener ����application����ĳ�ʼ��������
 *				contextInitialized(ServletContextEvent sce) ��ʼ��    ����������
 *				contextDestroyed(ServletContextEvent sce)	����	     �������ر�
 *				ע�⣺
 *					�βο��Ի�ȡ��ǰapplication����
 *					sce.getServletContext();
 
 			����application--->ServletContextAttributeListener �������ݵı��
 					attributeAdded(ServletContextAttributeEvent event)
 *					attributeRemoved(ServletContextAttributeEvent event)
 *					attributeReplaced(ServletContextAttributeEvent event)
 *				ע�⣺
 *					�βο��Ի�ȡ��ǰ����������
 *					event.getName()  ��ȡ���ݵļ���
 *					event.getValue() ��ȡ���ݵ�ֵ
 
 * 		��web.xml�����ü�������
 * 			<listener>
				<listener-class>com.bjsxt.listener.MyListener</listener-class>
			</listener>
			
		������
			ͳ�Ƶ�ǰ����������
			ͳ����ҳ�����������
 * @author MyPC
 *
 */
public class MyListener implements ServletRequestListener,ServletRequestAttributeListener,HttpSessionListener,HttpSessionAttributeListener,ServletContextListener,ServletContextAttributeListener{
	//request��������
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("�ұ�������");
		
	}
	//request���󴴽�
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("�ұ�������");
		
	}
	//����request���������ݵ����
	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		
		System.out.println("request��������һ������-"+srae.getName()+":"+srae.getValue());
		
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
	//����session�Ĵ���
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session��������");
		
	}
	//����session������
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session��������");
		
	}
	//����session���ݵı��
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("session��������һ������"+event.getName()+":"+event.getValue());
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
		System.out.println("application���󱻳�ʼ����");
		
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("application����������");
		
	}
	//����application�����ݱ��
	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		System.out.println("application�д洢������:"+event.getName()+":"+event.getValue());
		
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
