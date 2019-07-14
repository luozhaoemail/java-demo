package com.java.entity;

public class Permission
{
	private String ip;
	private int port;
	private String username;
	private String password;
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Permission [ip=" + ip + ", port=" + port + ", username="
				+ username + ", password=" + password + "]";
	}
	
	
	
	
}
