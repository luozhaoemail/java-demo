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
import pojo.PageInfo;
import pojo.User;
import service.UserSrevice;
import service.Imp.UserSeviceImp;

/**
 * Servlet implementation class ElServlet
 */
@WebServlet("/page.do")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserSrevice peopleService = new UserSeviceImp();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");		
		
		//第一次访问的验证,如果没有传递参数,设置默认值
		String pageSizeStr = req.getParameter("pageSize");
		int pageSize = 2;
		if(pageSizeStr!=null && !pageSizeStr.equals("")){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		String pageNumberStr = req.getParameter("pageNumber");
		int pageNumber = 1;
		if(pageNumberStr!=null && !pageNumberStr.equals("")){
			pageNumber = Integer.parseInt(pageNumberStr);
		}
		
		//查询业务层数据
		PageInfo pi = peopleService.showPage(pageSize, pageNumber);
		
		//返回结果
		req.setAttribute("PageInfo", pi);
		req.getRequestDispatcher("page.jsp").forward(req, resp);
	}  
   

}
