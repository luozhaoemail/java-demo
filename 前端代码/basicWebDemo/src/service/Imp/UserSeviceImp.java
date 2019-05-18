package service.Imp;

import java.util.List;

import dao.UserDao;
import dao.Imp.UserDaoImp;
import pojo.User;
import service.UserSrevice;

public class UserSeviceImp implements UserSrevice{
	//获取Dao层对象
	UserDao ud=new UserDaoImp();
	
	@Override
	public List<User> getUserInfoService(String name) {
		return ud.getUserInfo(name);
	}

}
