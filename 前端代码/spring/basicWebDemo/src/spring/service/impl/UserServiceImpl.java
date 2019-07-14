package spring.service.impl;

import java.util.List;

import spring.pojo.User;
import spring.mapper.UserMapper;
import spring.service.UserService;

public class  UserServiceImpl  implements UserService{
	private UserMapper userMapper; //调用mybatis业务层,要创建get set方法
		
	@Override
	public List<User> show() {
		return userMapper.selAll();
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}	
	
}
