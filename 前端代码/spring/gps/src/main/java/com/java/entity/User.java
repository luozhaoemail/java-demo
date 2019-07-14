package com.java.entity;
/**
 * 用户与角色是多对一的关系
 *
 * @ClassName: User 
 * @date 2017年9月21日 上午11:11:12 
 * @
 */
public class User {
	
	private int userId;
	private String userName;
	private String password;
	private int userType;
	private int roleId;//角色ID（外键）
	private String userDescription;
	
	
	//无参构造
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	//有参构造
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUserDescription() {
		return userDescription;
	}
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
}
