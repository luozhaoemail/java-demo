package service;

import java.io.IOException;
import java.util.List;

import pojo.PageInfo;
import pojo.User;

public interface UserSrevice {

	public List<User> getUserInfoService(String name);
	
	public PageInfo showPage(int pageSize,int pageNumber) throws IOException;
}
