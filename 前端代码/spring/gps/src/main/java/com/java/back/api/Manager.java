package com.java.back.api;

import java.io.PrintWriter;
/**
 * 业务处理接口
 * @author Administrator
 *
 */
public class Manager extends Thread{
	public Manager(){}
	
	public boolean receive(String strjson,PrintWriter pw)
	{
		return true;
	}

}
