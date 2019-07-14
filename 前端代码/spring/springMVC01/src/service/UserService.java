package service;


import pojo.User;

//第3步，业务实现接口，也就是用户注册
public interface UserService {
	
	int insertRegister(User user);
	
	User SelectRegistser(String name, String pwd);
}
