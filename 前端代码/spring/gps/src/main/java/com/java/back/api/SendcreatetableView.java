package com.java.back.api;

import java.util.List;

import org.apache.phoenix.shaded.net.minidev.json.JSONArray;
import org.json.JSONObject;

import com.java.back.run.Log;
import com.java.back.service.SimpleHbaseAPI;

public class SendcreatetableView extends AnaView 
{
	/**
	 * @Title: GetResult 接受前台传入参数返回json的父类,具体的实现在子类
	 * @param json 前台传过来的值
	 * @return	返回给前台所需json字符串
	 */
	@Override
    public String getResult(JSONObject json) 
    {
		String tableName = json.getString("tbname");
		String clfmaily = json.getString("clfmaily");
		String[] cf = clfmaily.split("\\,");		
		
		String JsonSp = "";
		try {
			JsonSp = SimpleHbaseAPI.createTable(tableName, cf);
		}
		catch (Exception e) {		
			e.printStackTrace();
		}		
		Log.log("\n ===> 2---返回结果----"+JsonSp);
        return JsonSp;
    }
}
