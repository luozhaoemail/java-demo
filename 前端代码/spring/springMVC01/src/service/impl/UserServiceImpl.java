package service.impl;

import javax.annotation.Resource;

import mapper.UserMapper;

import org.springframework.stereotype.Service;

import pojo.User;
import service.UserService;


//第4步，业务实现类，也就是实现用户注册功能

@Service
public class UserServiceImpl implements UserService{
	//注入相关的Mapper
	@Resource
	private UserMapper usermapper;

	@Override
	public int insertRegister(User user) {
		//调用UserMapper，把数据插入到数据里面去
		return usermapper.insertUser(user);
		//也可以在这里写上传文件的代码
		//但是我们可以将上传文件的代码放在控制器里面去，
		//因此这里只需要负责更新数据库就可以了
		
	}

	@Override
	public User SelectRegistser(String name, String pwd) {		
		return usermapper.selectUser(name,pwd);
	}

}
