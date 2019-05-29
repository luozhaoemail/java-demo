package com.bjsxt.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ServletContext����ѧϰ��
 * 		����:
 * 			��ͬ���û�ʹ����ͬ������
 * 		���:
 * 			ServletContext����
 * 		�ص�:
 * 			����������
 * 			�û�����
 * 		������
 * 			������Ŀ��
 * 		�������ڣ�
 * 			�������������������ر�
 * 		ʹ�ã�
 * 			��ȡServletContext����
 * 					//��һ�ַ�ʽ��
						ServletContext sc=this.getServletContext();
					//�ڶ��ַ�ʽ��
						ServletContext sc2=this.getServletConfig().getServletContext();
					//�����ַ�ʽ��
						ServletContext sc3=req.getSession().getServletContext();
 * 			ʹ��ServletContext����������ݹ���
 * 					//���ݴ洢
 * 						sc.setAttribute(String name, Object value);
 * 					//���ݻ�ȡ
 * 						sc.getAttribute("str") ���ص���Object����
 * 					ע�⣺
 * 						��ͬ���û����Ը�ServletContext����������ݵĴ�ȡ��
 * 						��ȡ�����ݲ����ڷ���null��
 * 			��ȡ��Ŀ��web.xml�ļ��е�ȫ����������
 * 					sc.getInitParameter(String name); ���ݼ������ַ���web.xml�����õ�ȫ�����ݵ�ֵ������String���͡�
 * 													  ������ݲ����ڷ���null��
 * 					sc.getInitParameterNames();���ؼ�����ö��
 * 				���÷�ʽ��ע�� һ��<context-param>��ǩֻ�ܴ洢һ���ֵ�����ݣ���������������  <context-param>���д洢��
 * 					  <context-param>
						  	<param-name>name</param-name>
						  	<param-value>zhangsan</param-value>
  					  </context-param>
  				���ã�����̬���ݺʹ�����н��
  			��ȡ��Ŀwebroot�µ���Դ�ľ���·����
  				String path=sc.getRealPath(String path);	
  				��ȡ��·��Ϊ��Ŀ��Ŀ¼��path����Ϊ��Ŀ��Ŀ¼�е�·��
  			��ȡwebroot�µ���Դ��������
  				InputStream is = sc.getResourceAsStream(String path);
  				ע�⣺
  					���ַ�ʽֻ�ܻ�ȡ��Ŀ��Ŀ¼�µ���Դ������class�ļ�����������Ҫʹ�����������ȡ��
  					path����Ϊ��Ŀ��Ŀ¼�е�·��
 * 
 * 
 * @author MyPC
 *
 */
public class ServletContextServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//��ȡServletContext����
			//��һ�ַ�ʽ��
			ServletContext sc=this.getServletContext();
			//�ڶ��ַ�ʽ��
			ServletContext sc2=this.getServletConfig().getServletContext();
			//�����ַ�ʽ��
			ServletContext sc3=req.getSession().getServletContext();
			System.out.println(sc==sc2);
			System.out.println(sc==sc3);
		//ʹ��ServletContext����������ݹ���
			//���ݴ洢
			sc.setAttribute("str", "ServletContext����ѧϰ");
		//��ȡ��Ŀweb.xml��ȫ����������
			String str = sc.getInitParameter("name2");
			System.out.println("ȫ�����ò�����"+str);
		//��ȡ��Ŀ��Ŀ¼�µ���Դ�ľ���·��
			//String path="D:\\apache-tomcat-7.0.56\\webapps\\sc\\doc\\1.txt";
			String path=sc.getRealPath("/doc/1.txt");
			System.out.println(path);
		//��ȡ��Ŀ��Ŀ¼����Դ��������
			InputStream is = sc.getResourceAsStream("/doc/1.txt");
	
	}
}
