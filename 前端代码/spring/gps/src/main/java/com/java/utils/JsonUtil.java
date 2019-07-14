package com.java.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	public static JSONArray formatRsToJsonArray(ResultSet rs)throws Exception{
		ResultSetMetaData md = rs.getMetaData();//得到结果集(rs)的结构，也就是表的结构
		int num = md.getColumnCount();//总列数
		JSONArray array = new JSONArray();
		while(rs.next()){
			JSONObject mapOfColValues = new JSONObject();
			for(int i=1;i<=num;i++){
				Object o = rs.getObject(i);
				if(o instanceof Date){
					mapOfColValues.put(md.getColumnName(i), DateUtil.formatDate((Date)o, "yyyy-MM-dd"));
				}else{
					mapOfColValues.put(md.getColumnName(i), rs.getObject(i));					
				}
			}
			array.add(mapOfColValues);
		}
		return array;
	}
}
