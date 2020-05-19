package lc.ana;

import java.io.PrintWriter;

import org.json.JSONObject;

import lc.tool.Log;
import lc.tool.MyConstant;


public class AnaManager extends Manager
{
	public AnaManager()
	{
	}
	
	//重写父类Manager的方法
	public boolean receive(String strjson,PrintWriter pw) 
	{
		try
		{
			JSONObject jObj = new JSONObject(strjson);
			getResult(jObj,pw);
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
	* @Title: GetResult 
	* @param json 反射：得到返回前台的值
	* @param pw
	 */
	public void getResult(JSONObject json,PrintWriter pw)
	{
		try{
			Log.log("\n ===> 1---接收前台的JSON:" + json.toString());
			// comd = com.lc.bigdata.ana.pve.cpu
			
			String className = json.getString("command");
			AnaView avw = (AnaView)Class.forName(json.getString("command")).newInstance();
			
			if(className.equals("lc.ana.hdp.check.SendResult"))
			{	
				MyConstant.pwriter = pw;			
				avw.getResult(json);
				pw.close(); ///每次调用都会生成一个新的PrintWriter
			}
			else
			{
				pw.println(avw.getResult(json));
				pw.close(); ///每次调用都会生成一个新的PrintWriter
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
