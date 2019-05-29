package com.bjsxt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Service������doGet������doPost����������
 * 	Service������
 * 		get/post��ʽ�����󶼿��Դ������servlet����Service�����������ȵ���service������������д���
 * 	doGet������
 * 		����get��ʽ������
 * 	doPost������
 * 		����post��ʽ������
 * ע�⣺
 * 		����ڸ�д��service�����е����˸����service����(super.service(arg0, arg1)),
 * 		��service����������󣬻��ٴθ�������ʽ��Ӧ��doGet��doPost����ִ�С�
		���ԣ�һ������£������ǲ��ڸ�д��service�е��ø����service�����ģ��������405����
		
 * Servlet�ĳ�������
 * 		404����:��Դδ�ҵ�
 * 			ԭ��һ���������ַ�е�servlet�ı�����д����
 * 			ԭ�����������Ŀ����ƴд����
 *		500�����ڲ�����������
 *			����һ��
 *				java.lang.ClassNotFoundException: com.bjsxt.servlet.ServletMothod
 * 				�����
 * 					��web.xml��У��servlet���ȫ�޶�·���Ƿ�ƴд����
 *			�������
 *				��Ϊservice������Ĵ���ִ�д�����
 *				�����
 *					���ݴ�����ʾ��service�������еĴ�����д�����ġ�
 *		405����:����ʽ��֧��
 *			ԭ��
 *				����ʽ��servlet�еķ�����ƥ������ɵġ�
 *			�����
 *				����ʹ��service�����������������Ҳ�Ҫ��service�����е��ø����service��
 * @author MyPC
 */
public class ServletMethod extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) //tomcat����ʱ�ᴫ��ʵ�ζ���req��resp
			throws ServletException, IOException {
		int i=5/0;
		System.out.println("����service");
		super.service(req, resp);
		/**
		����ڸ�д��service�����е����˸����service����(super.service(arg0, arg1)),
		��service����������󣬻��ٴθ�������ʽ��Ӧ��doGet��doPost����ִ�С�
		���ԣ�һ������£������ǲ��ڸ�д��service�е��ø����service�����ģ��������405����
		*/
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("����doGet����");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("����doPost����");
	}	
}
