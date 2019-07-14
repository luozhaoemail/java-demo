package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pojo.Address;
import pojo.User;
import service.UserSrevice;
import service.Imp.UserSeviceImp;

/**
 * Servlet implementation class ElServlet
 */
@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");		
		
		//获取请求信息，从前台接收值,后台不会区分post和get请求，
		String uname=req.getParameter("uname");
		System.out.println("后台接收uanme="+uname);
		
		//获取业务层对象
		UserSrevice us=new UserSeviceImp();
		List<User> list = us.getUserInfoService(uname);
		System.out.println("查询结果："+list);
		
		String json = new Gson().toJson(list);
		//响应处理结果
		resp.getWriter().write(json);
	}  
   

}
