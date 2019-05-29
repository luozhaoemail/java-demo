package com.bjsxt.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ServletContext对象学习：
 * 		问题:
 * 			不同的用户使用相同的数据
 * 		解决:
 * 			ServletContext对象
 * 		特点:
 * 			服务器创建
 * 			用户共享
 * 		作用域：
 * 			整个项目内
 * 		生命周期：
 * 			服务器启动到服务器关闭
 * 		使用：
 * 			获取ServletContext对象
 * 					//第一种方式：
						ServletContext sc=this.getServletContext();
					//第二种方式：
						ServletContext sc2=this.getServletConfig().getServletContext();
					//第三种方式：
						ServletContext sc3=req.getSession().getServletContext();
 * 			使用ServletContext对象完成数据共享
 * 					//数据存储
 * 						sc.setAttribute(String name, Object value);
 * 					//数据获取
 * 						sc.getAttribute("str") 返回的是Object类型
 * 					注意：
 * 						不同的用户可以给ServletContext对象进行数据的存取。
 * 						获取的数据不存在返回null。
 * 			获取项目中web.xml文件中的全局配置数据
 * 					sc.getInitParameter(String name); 根据键的名字返回web.xml中配置的全局数据的值，返回String类型。
 * 													  如果数据不存在返回null。
 * 					sc.getInitParameterNames();返回键名的枚举
 * 				配置方式：注意 一组<context-param>标签只能存储一组键值对数据，多组可以声明多个  <context-param>进行存储。
 * 					  <context-param>
						  	<param-name>name</param-name>
						  	<param-value>zhangsan</param-value>
  					  </context-param>
  				作用：将静态数据和代码进行解耦。
  			获取项目webroot下的资源的绝对路径。
  				String path=sc.getRealPath(String path);	
  				获取的路径为项目根目录，path参数为项目根目录中的路径
  			获取webroot下的资源的流对象
  				InputStream is = sc.getResourceAsStream(String path);
  				注意：
  					此种方式只能获取项目根目录下的资源流对象，class文件的流对象需要使用类加载器获取。
  					path参数为项目根目录中的路径
 * 
 * 
 * @author MyPC
 *
 */
public class ServletContextServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取ServletContext对象
			//第一种方式：
			ServletContext sc=this.getServletContext();
			//第二种方式：
			ServletContext sc2=this.getServletConfig().getServletContext();
			//第三种方式：
			ServletContext sc3=req.getSession().getServletContext();
			System.out.println(sc==sc2);
			System.out.println(sc==sc3);
		//使用ServletContext对象完成数据共享
			//数据存储
			sc.setAttribute("str", "ServletContext对象学习");
		//获取项目web.xml的全局配置数据
			String str = sc.getInitParameter("name2");
			System.out.println("全局配置参数："+str);
		//获取项目根目录下的资源的绝对路径
			//String path="D:\\apache-tomcat-7.0.56\\webapps\\sc\\doc\\1.txt";
			String path=sc.getRealPath("/doc/1.txt");
			System.out.println(path);
		//获取项目根目录下资源的流对象
			InputStream is = sc.getResourceAsStream("/doc/1.txt");
	
	}
}
