package com.bjsxt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjsxt.pojo.User;

public class ShowServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//������������ʽ
			req.setCharacterEncoding("utf-8");
		//������Ӧ�����ʽ
			resp.setContentType("text/html;charset=utf-8");
		//��ȡ������Ϣ
			HttpSession hs=req.getSession();
			if(hs.getAttribute("user")==null){
				//�ض���
				resp.sendRedirect("/login/ck");
				return;
			}
			User u=(User) hs.getAttribute("user");
		//����������Ϣ
		//��Ӧ������
			resp.getWriter().write("<html>");
			resp.getWriter().write("<head>");
			resp.getWriter().write("</head>");
			resp.getWriter().write("<body>");
			resp.getWriter().write("<table border='1px'>");
			resp.getWriter().write("<tr>");
			resp.getWriter().write("<td>�û���</td>");
			resp.getWriter().write("<td>"+u.getUname()+"</td>");
			resp.getWriter().write("</tr>");
			resp.getWriter().write("<tr>");
			resp.getWriter().write("<td>����</td>");
			resp.getWriter().write("<td>"+u.getPwd()+"</td>");
			resp.getWriter().write("</tr>");
			resp.getWriter().write("</table>");
			resp.getWriter().write("</body>");
			resp.getWriter().write("</html>");	
	}
}
