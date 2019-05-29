package com.bjsxt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * session����ѧϰ:
 * 		���⣺
 * 			һ���û��Ĳ�ͬ����������ݹ�����ô�죿
 * 		�����
 * 			ʹ��session����
 * 		ԭ��
 * 			�û���һ�η��ʷ��������������ᴴ��һ��session��������û�������
 * 			��session�����JSESSIONIDʹ��Cookie�����洢��������У���֤
 * 			�û������������ܹ���ȡ��ͬһ��session����Ҳ��֤�˲�ͬ�����ܹ���ȡ��
 * 			��������ݡ�
 * 		�ص㣺
 * 			�洢�ڷ�������
 * 			���������д���
 * 			����Cookie����
 * 			һ�λỰ
 * 			Ĭ�ϴ洢ʱ����30����
 *		���ã�
 *			�����һ���û���ͬ����������ݹ�������
 *		ʹ�ã�
 *			����session����/��ȡsession����
				HttpSession hs=req.getSession();
				���������ӵ��session�ı�ʶ��Ҳ����JSESSIONID���򷵻����Ӧ��session����
				���������û��session�ı�ʶ��Ҳ����JSESSIONID���򴴽��µ�session���󣬲�����JSESSIONID��Ϊ��cookie���ݴ洢��������ڴ���
 * 				���session������ʧЧ�ˣ�Ҳ�����´���һ��session���󣬲�����JSESSIONID�洢��������ڴ��С�
 * 			����session�洢ʱ��
 * 				hs.setMaxInactiveInterval(int seconds);
 * 				ע�⣺
 * 					��ָ����ʱ����session����û�б�ʹ�������٣����ʹ���������¼�ʱ��
 * 			����sessionǿ��ʧЧ
 * 				hs.invalidate();
 * 			�洢�ͻ�ȡ����
 * 				�洢��hs.setAttribute(String name,Object value);
 * 				��ȡ��hs.getAttribute(String name) ���ص���������ΪObject
 * 				ע�⣺
 * 					�洢�Ķ�����ȡ���Ķ��������ڲ�ͬ�������У����Ǵ洢Ҫ����ȡ��ִ�С�
 * 			ʹ��ʱ��:
 * 				һ���û��ڵ�½web��Ŀʱ�Ὣ�û��ĸ�����Ϣ�洢��Sesion�У������û�����������ʹ�á�
 * 			�ܽ᣺
 * 				session�����һ���û��Ĳ�ͬ��������ݹ������⣬ֻҪ��JSESSIONID��ʧЧ��session����ʧЧ������¡�
 * 				�û������������ڴ���ʱ���ܻ�ȡ��ͬһ��session����
 * 			������
 * 				һ�λỰ
 * 				��JSESSIONID��SESSION����ʧЧ�������Ϊ������Ŀ�ڡ�
 * 			sessionʧЧ����
 * 				���û������е�JSESSIONID�ͺ�̨��ȡ����SESSION�����JSESSIONID���бȶԣ����һ��
 * 				��sessionû��ʧЧ�������һ����֤��sessionʧЧ�ˡ��ض��򵽵�¼ҳ�棬���û����µ�¼��
 * 		ע�⣺
 * 			JSESSIONID�洢����Cookie����ʱ�洢�ռ��У�������رռ�ʧЧ��
 * 
 * @author MyPC
 *
 */
public class SessionServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//������������ʽ
		req.setCharacterEncoding("utf-8");
		//������Ӧ�����ʽ
		resp.setContentType("text/html;charset=utf-8");
		//��ȡ������Ϣ
			String name="����";
		//����������Ϣ
			//����session����
			HttpSession hs=req.getSession();
			//����session�Ĵ洢ʱ��
				//hs.setMaxInactiveInterval(5);
			System.out.println(hs.getId());
			//����sessionǿ��ʧЧ
				//hs.invalidate();
			//�洢����
				hs.setAttribute("name",name);
		//��Ӧ������
			//ֱ����Ӧ
			resp.getWriter().write("sessionѧϰ");
			//����ת��
			//�ض���
	}
}
