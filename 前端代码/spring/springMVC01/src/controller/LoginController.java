package controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pojo.Demo;
import pojo.People;
import pojo.User;
import service.UserService;

@Controller
public class LoginController {
	
	@RequestMapping("{page}")
	public String doMain(@PathVariable String page){
		System.out.println("restful风格！！！");
		return page;
	}
	
	@Resource
	private UserService userimpl;
	
	@RequestMapping("login")
	public String login(String name, String pwd, HttpSession session){		
		User dbuser = userimpl.SelectRegistser(name,pwd);
		System.out.println("查询数据库："+dbuser.getUsername()+" "+dbuser.getPassword());
		if(dbuser!=null && 
				name.equalsIgnoreCase(dbuser.getUsername()) && pwd.equalsIgnoreCase(dbuser.getPassword()) )		
//		if( user.getUsername().equals("1") && 
//		    user.getPassword().equals("1") )
		{
//			session.setAttribute("user", user);
			return "main";
		}
		else
			return "redirect:/login.jsp";
	}
	
	@RequestMapping("test")
	public String test(String name, int age,
			HttpServletRequest req,	@RequestParam("sex")List<String> abc){
		
		req.setAttribute("rst", "返回：["+name+", "+age+", "+abc+"]");

		return "forward:/login.jsp";
		// 如果希望不执行自定义视图解析器,在方法返回值前面添加forward:或 redirect:
	}
	/**传参
	<form action="test" method="post">
		名字:<input type="text" name="name"/><br/>
		年龄:<input type="text" name="age"/><br/>
		<input type="submit" value="提交后台"/>
	</form>
	<h3>${rst}</h3>
	 
	 1.默认保证参数名称和请求中传递的参数名相同
	 String name,int age,
	 
	 2.如果请求参数名和方法参数名不对应使用@RequestParam()赋值
	 @RequestParam(value="name")String a, @RequestParam(value="age")int b
	 
	 3.基本数据类型参数可以设置默认值
	 @RequestParam(defaultValue="1") int age
	 
	 4.如果强制要求必须有某个参数
	 @RequestParam(required=true) String name
	 
	 5.参数是对象类型，会自动将name和age封装成对应的对象People
	 People peo,
	 
	 6.多个同名参数，如 复选框传递的参数就是多个同名参数
	 @RequestParam("sex")List<String> abc
	 
	 7.集合对象类型参数
	  新建类Demo,	  对象名和参数中点前面名称对应
	 
	 8.  restful 传值方式.
	  a. 在@RequestMapping 中一定要和请求格式对应
	  b. {名称} 中名称自定义名称
	  c. @PathVariable 获取@RequestMapping 中内容,默认按照方法参数名称去寻找.
	 eg：
	 <a href="demo8/123/abc">跳转</a>
	 @RequestMapping("test/{id1}/{name}")
	 @PathVariable String name, @PathVariable("id1") int age
	 */
	@RequestMapping("test1")
	public String test1(Demo demo, ModelMap model){
		System.out.println(demo); 
		//Demo [peo=People [name=1, age=1]]
		//Demo [list=[People [name=1, age=1], People [name=1, age=1]]]
		List<People> list = demo.getPeo();
		System.out.println("size = "+list.size());
		model.addAttribute("datalist",list);
		return "forward:/login.jsp";
		// 如果希望不执行自定义视图解析器,在方法返回值前面添加forward:或 redirect:
	}
	
	
	@RequestMapping("dwn")
	public void download(String fileName,HttpServletResponse res,HttpServletRequest req){
		//设置响应流中文件进行下载
		res.setHeader("Content-Disposition", "attachment;filename="+fileName);
		//把二进制流放入到响应体中.
		
		try{
			ServletOutputStream os = res.getOutputStream();
			String path = req.getServletContext().getRealPath("/files");
			System.out.println("文件路径： "+path);
			File file = new File(path, fileName); 
			
			//工具类import org.apache.commons.io.FileUtils
			//把文件转成二进制流
			byte[] bytes = FileUtils.readFileToByteArray(file);
			os.write(bytes);
			os.flush();
			os.close();
			
		}catch(Exception e){
			System.out.println("--下载异常-"+e.getMessage());
		}
		
	}
	
	@RequestMapping("up")
	public String upload(MultipartFile file, HttpServletRequest req){
		String fileName = file.getOriginalFilename();//a.jpg
		/*String dstPath = req.getServletContext().getRealPath("/images")+fileName;
		System.out.println("----文件= "+dstPath);
		//其实是tomcat下的路径= C:\install\codingWeb\optWK\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\springMVC01\images\头像.jpg
		System.out.println(req.getServletPath());// = /up		//请求url路径
		System.out.println(req.getContextPath());//  = /springMVC01 是项目根目录
*/		
		//上传到项目所在工作空间的路径下，其实是本地路径
		String dstpath="C:/install/codingWeb/optWK/springMVC01/WebContent/images/"+fileName;
		
		try{//把输入流转成文件
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(dstpath));	
		
		}catch(Exception e){
			System.out.println("----上传异常-"+e.getMessage());
		}
		
		return "forward:/login.jsp"; 
	}


}
