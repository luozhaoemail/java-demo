package com.java.service;

import java.util.List;
import java.util.Map;

import com.java.entity.User;

/** *
 * @ClassName: UserService 
 * <p>Description: </p>
 * @
 */
public interface UserService {

	//通过用户名得到用户
	public User getByUserName(String userName)throws Exception;
	
	//修改密码
	public int setPwd(String userName,String newPwd) throws Exception;

}
