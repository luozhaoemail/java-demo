package com.java.back.api;

import java.io.PrintWriter;

import org.json.JSONObject;

import com.java.back.run.Log;


public class AnaManager extends Manager
{
	public AnaManager(){}
	
	public boolean receive(String strjson,PrintWriter pw) 
	{
		try
		{
			JSONObject jObj = new JSONObject(strjson);
			sendResult(jObj,pw);
		}
		catch(Exception e)
		{
			Log.log("Json 初始化异常，Json字符串："+strjson);
			Log.log(e.getMessage());
		}
		
		return true;
	}
	
	/**
	 * 	
	* @Title: sendResult 
	* @param json 给前台返回值
	* @param pw
	 */
	public void sendResult(JSONObject json,PrintWriter pw)
	{
		try{
			Log.log("\n ===> 1---接收前台的JSON:" + json.toString());
			// comd = com.lc.bigdata.ana.pve.cpu
			
			//反射调用对于的接口: SendxxxView
			String className = json.getString("command");
			AnaView avw = (AnaView)Class.forName(json.getString("command")).newInstance();
			
			if(className.equals("com.java.back.api.SendPhoenixView"))
			{	
				//MyConstant.pwriter = pw;			
				avw.getResult(json);//给前台返回值！！！！！
				pw.close(); ///每次调用都会生成一个新的PrintWriter
			}
			else
			{
				pw.println(avw.getResult(json)); //给前台返回值！！！！！
				pw.close(); ///每次调用都会生成一个新的PrintWriter
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
