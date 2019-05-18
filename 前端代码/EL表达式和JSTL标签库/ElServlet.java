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

import pojo.Address;
import pojo.User;

/**
 * Servlet implementation class ElServlet
 */
@WebServlet("/ElServlet")
public class ElServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		req.setCharacterEncoding("utf-8");//设置请求编码格式	
		resp.setContentType("text/html;charset=utf-8");	//设置响应编码格式
		
		//获取请求信息，从前台接收值
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");		
		System.out.println("后台接收：  "+uname+", "+pwd);
		
		
		//使用request作用域进行数据流转：
		//1 向前台传值普通字符串
		req.setAttribute("str", "今天的天气很好！");
		
		//2 向前台传对象类型
		User u=new User(1, "柳岩","拍电影",new Address("河南","商丘", "虞城县"));
		req.setAttribute("user", u);
		
		//3 向前台传对象类型List集合
		//存储普通字符
		List<String> list=new ArrayList<String>();
		list.add("张家辉");
		list.add("关晓彤");
		list.add("刘诗诗");
		//存储对象
		User u2=new User(2, "古力娜扎","拍电影",new Address("新疆","乌鲁木齐","乌鲁木齐"));
		List<User> list2=new ArrayList<User>();
		list2.add(u2);
		req.setAttribute("list",list);
		req.setAttribute("list2",list2);
		//Map集合
		//存储普通字符
		Map<String,String> map=new HashMap<String,String>();
		map.put("a", "北京");
		map.put("b", "上海");
		map.put("c", "商丘");
		req.setAttribute("map",map);
		//存储对象
		Map<String,User> map2=new HashMap<String,User>();
		map2.put("a1", new User(3, "迪丽热巴","拍电影",new Address("新疆","吐鲁番","吐鲁番")));
		req.setAttribute("map2",map2);
		//空值判断
		req.setAttribute("s","");
		req.setAttribute("s1",new User());
		req.setAttribute("s2",new ArrayList());
		req.setAttribute("s3",new HashMap());
		
		//请求转发
		req.getRequestDispatcher("/el.jsp").forward(req, resp);	
		return;
	}  
   

}
