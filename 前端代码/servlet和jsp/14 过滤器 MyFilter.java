package com.bjsxt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ��������ʹ�ã�
 * 	���ã�
 * 		�Է��������ܵ�������Դ����Ӧ�����������Դ���й���
 * 		����servlet
 * 	ʹ��:
 * 		����һ��ʵ����Filter�ӿڵ���ͨjava��
 * 		��д�ӿڵķ���
 * 			init����:������������ִ�С���Դ��ʼ��
 * 			doFilter����:��������ķ������ڴ˷����п��Զ���Դʵ�ֹ���
 * 					ע�⣺
 * 						��Ҫ�ֶ���������з��С�chain.doFilter(request, response);
 * 			destory�������������ر�ִ�С�
 *		��web.xml�����ù�����
 *			 	<filter>
				  	<filter-name>myFilter</filter-name>
				  	<filter-class>com.bjsxt.filter.MyFilter</filter-class>
				</filter>
			  	<filter-mapping>
			  		<filter-name>myFilter</filter-name>
			  		<url-pattern>/*</url-pattern>
			  	</filter-mapping>
			  ע��:
			  	url-pattern:/*
			  			��ʾ�������е�����
			  	url-pattern:*.do
			  			��ʾ������.do��β������һ������������ģ�����ش���
			  	url-pattern:/ts
			  			��ʾ����ָ��url���������ĳ��servlet������������أ�����servlet��
		���������������ڣ�
			�������������������رա�
		�ܽ�:
			����������Ա���������ã����������������е�uri��Ϣ���á�
		ִ��:
			������������󵽷����������������յ�����󣬸���URI��Ϣ��web.xml���ҵ���Ӧ��
			������ִ��doFilter�������÷����Դ˴�������д�����������Ҫ������У����к�
			������з���Ҫ��Ĺ�����������й��ˣ��ҵ�ִ�ж�Ӧ��servlet����������servlet��
			��������Ϻ�Ҳ��service���������ˡ��������������Ӧ��doFilter��������ִ�С�
			
		������
			ͳһ�����ʽ���á�
			session����
			Ȩ�޹���
			��Դ����ͳһˮӡ����г�ʻ�ȵȣ�
			
 * @author MyPC
 *
 */
public class MyFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("MyFilter.init(�ұ���ʼ����)");
		
	}

	//doFilter�ж�����еĹ���
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("MyFilter.doFilter(�ұ�ִ����)");
		//���ñ����ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//�ж�session
			HttpSession hs=((HttpServletRequest) request).getSession();
			if(hs.getAttribute("user")==null){
				((HttpServletResponse)response).sendRedirect("/a/login.jsp");
			}else{
				//����
				chain.doFilter(request, response);
			}
		System.out.println("MyFilter.doFilter(�ұ�ִ����2)");
		
	}

	@Override
	public void destroy() {
		System.out.println("MyFilter.destroy(�ұ�������)");
		
	}
	
}

<!-- ���ù����� -->
  <filter>
  	<filter-name>myFilter</filter-name>
  	<filter-class>com.bjsxt.filter.MyFilter</filter-class>
  </filter>
  <filter-mapping>
  	<filter-name>myFilter</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping>
  <!--���õڶ���������  -->
   <filter>
  	<filter-name>myFilter2</filter-name>
  	<filter-class>com.bjsxt.filter.MyFilter2</filter-class>
  </filter>
  <filter-mapping>
  	<filter-name>myFilter2</filter-name>
  	<url-pattern>*.do</url-pattern>
  </filter-mapping>
  <!--���õ�����������  -->
   <filter>
  	<filter-name>myFilter3</filter-name>
  	<filter-class>com.bjsxt.filter.MyFilter3</filter-class>
  </filter>
  <filter-mapping>
  	<filter-name>myFilter3</filter-name>
  	<url-pattern>/ts.do</url-pattern>
  </filter-mapping>
  
  
  <!-- spring mvc���ַ���������� -->
	<filter>
		<filter-name>encoding</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>utf-8</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>encoding</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
