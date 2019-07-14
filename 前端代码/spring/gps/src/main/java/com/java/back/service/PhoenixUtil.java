package com.java.back.service;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.phoenix.jdbc.PhoenixConnection;

//java -cp hb.jar:$PNX_HOME/phoenix-4.12.0-HBase-1.2-client.jar com.java.controller.admin.check.PhoenixUtil
public class PhoenixUtil {
	public static PhoenixConnection getConnection() 
	{
		try {
			Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
			PhoenixConnection conn = (PhoenixConnection) DriverManager.getConnection("jdbc:phoenix:localhost", "root", "anypasswd");
			return conn;
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return null;
	}
	
	//读取少量字段
	public static void read1(String tb) 
	{
		List<String> list = new ArrayList<String>();
		try
        {  			
			Statement statement = getConnection().createStatement();  			
			String sql ="select ID,\"name\",\"sex\",\"age\",\"adress\" from "+tb;
			out(sql);
	        ResultSet ret = statement.executeQuery(sql);  

	        while(ret != null && ret.next())  
	        {     
	            String row = ret.getString(1)+","+ret.getString(2)+","+ret.getString(3)+","
      				  		 +ret.getString(4)+","+ret.getString(5);
	        	//System.out.println(row);  
	        	list.add(row);
	        }
	     }  
        catch (SQLException e) 
	     { 	           
	            e.printStackTrace();  
	     }
		
		  out("read1.list.size()="+list.size());
		  for(int i=0;i<1;i++)
				out(list.get(i));
	}
	
	
	//字段比较多的情况
	public static void read2(String tb) throws SQLException
	{		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>(); 
		Statement statement = getConnection().createStatement();  
		String sql ="select * from "+tb;
		out(sql);
        ResultSet rs = statement.executeQuery(sql);
        ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据  
        int columnCount = md.getColumnCount();   //获得列数 
        while(rs != null && rs.next())  
        {    
        	 Map<String,Object> row = new HashMap<String,Object>();  
             for (int i = 1; i <= columnCount; i++)
             {  
                 row.put(md.getColumnName(i), rs.getObject(i));  
             }  
        	list.add(row);      	
        }        
        
        out("read2.list.size()="+list.size());
        for(int i=0;i<1;i++)
			out(list.get(i));
	
		
	}
	
	public static void main(String[] args) throws Exception {
		String tbname ="\"stu\"";
		read1(tbname);
		read2(tbname);
		
	}
	
	public static void out(Object obj)
	{
		System.out.println(obj);
	}
}
