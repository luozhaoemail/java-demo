package spring.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import spring.pojo.User;
import spring.service.UserService;
import spring.service.impl.UserServiceImpl;


@WebServlet("/showuser")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	
	//对service实例化
	@Override
	public void init() throws ServletException {
		//手动获取：applicationContext2.xml
		//ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext2.xml");
		
		//配置再web.xml 自动获取applicationContext2.xml
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		
		userService = ac.getBean("userService",UserServiceImpl.class);		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");		
					
		//获取业务层对象
		List<User> list = userService.show();
		System.out.println(list);
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}  
   

}
