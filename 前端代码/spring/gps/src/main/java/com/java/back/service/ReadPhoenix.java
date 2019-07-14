package com.java.back.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.phoenix.jdbc.PhoenixConnection;
import org.json.JSONObject;


//jdbc的ResultSet方式只适合小量数据的读取
//java -cp test.jar:$SPARK_HOME/jars/*:$PNX_HOME/* lc.stream.ReadPhoenix
//spark-submit --class data.ReadPhoenix  test.jar
public class ReadPhoenix
{
	public static PhoenixConnection conn;
	public static Statement statement;  
	static{
		try {
			conn= PhoenixUtil.getConnection();
			statement = conn.createStatement();
			System.out.println("ReadPhoenix 只会执行一次");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
			
	public static String readHbase(String table, String isdn,String t1,String t2) throws Exception
	{	 		
			String sql ="select * from "+table+" where ISDN='"+isdn+
					"' and STARTTIME>='"+t1+"' and ENDTIME<='" +t2+"'";
			System.out.println(sql);
			
			JSONObject jsonObj = new JSONObject();
	        ResultSet ret = statement.executeQuery(sql);  
	        while(ret != null && ret.next())  
	        {
	    		jsonObj.put("startTime",ret.getString(1));
	    		jsonObj.put("endTime",ret.getString(2));
	    		jsonObj.put("iMSI",ret.getString(3));
	    		jsonObj.put("iSDN",ret.getString(4));
	    		jsonObj.put("isOverdue",ret.getString(5)); 
	    		jsonObj.put("isIndustryCard",ret.getString(6));
	    		jsonObj.put("isStatic",ret.getString(7));
	    		jsonObj.put("isUser4G",ret.getString(8));
	    		jsonObj.put("faultlayer",ret.getString(9)); 
	    		jsonObj.put("faultCase",ret.getString(10));
	    		jsonObj.put("conclusion",ret.getString(11));  
	    		
	    		break;
	        }	        
	        System.out.println("处理结果：\n"+jsonObj.toString());
	        return jsonObj.toString();		
	}
	

}
