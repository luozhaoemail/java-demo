package dao;

import java.util.List;

import pojo.User;


public interface UserDao {

	public List<User> getUserInfo(String name);
}
