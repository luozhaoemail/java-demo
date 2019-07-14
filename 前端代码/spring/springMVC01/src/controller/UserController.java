package controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import pojo.User;
import service.UserService;

//第5步，在控制器里面写实现代码
@Controller
public class UserController {
	
	//注入业务实现类
	@Resource
	private UserService usrservImpl;
	
	//注册控制器名称
	@RequestMapping("register")
	public String register(User users,MultipartFile file,HttpServletRequest req){

		//获取文件名
		String filename = file.getOriginalFilename();
		System.out.println("filename="+filename);//1.jpg
		
		//判断上传文件类型
		String suffix = filename.substring(filename.lastIndexOf("."));//获取后缀名
		if(!suffix.equalsIgnoreCase(".jpg"))
		{
			req.setAttribute("warn", "只能上次jpg格式的图片xxx");
			return "redirect:/login.jsp";
		}
		req.setAttribute("warn", "图片上传成功！！！");
				
		//获取到文件存放完整路径images
		String path = "/images/"+filename;
		System.out.println("存放路径："+path);
		try {
			//在images目录下存放文件，文件名存在数据库中
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//只能取到webapps文件夹内容,在数据库中存放文件路径
		users.setPhoto(filename);
		int index = usrservImpl.insertRegister(users);
		if(index>0){
			req.getSession().setAttribute("user", users);
			return "main1";
		}else{
			return "redirect:/login.jsp";
		}
	}
}
