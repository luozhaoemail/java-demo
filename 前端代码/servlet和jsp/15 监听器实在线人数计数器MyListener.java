package com.bjsxt.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//����session--->HttpSessionListener
//����application--->ServletContextListener 
public class MyListener implements HttpSessionListener,ServletContextListener{
	
	//session������ʱ��������
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		//��ȡServletContext����
		ServletContext sc=se.getSession().getServletContext();
		//��ȡ����ͳ�������ı���
		int count=(int) sc.getAttribute("count");
		//�洢
		sc.setAttribute("count",++count);
		
	}
	//session������ʱ�����Լ�
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
				//��ȡServletContext����
				ServletContext sc=se.getSession().getServletContext();
				//��ȡ����ͳ�������ı���
				int count=(int) sc.getAttribute("count");
				//�洢
				sc.setAttribute("count",--count);
		
	}
	//application�����ʼ��
	@Override
	public void contextInitialized(ServletContextEvent sce) {
			//��ȡapplication
			ServletContext sc=sce.getServletContext();
			//��application����洢��������ͳ����������
			sc.setAttribute("count",0);		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	

}
