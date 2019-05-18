package com.java.utils;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * easyui--json分页工具，直接调用即可
 * @author 罗昭 
 */
public class JsonPage
{
	/**
	 * easyui--json分页工具，直接调用即可
	 * @param jSource	数据源json，必须按照easyui的格式封装，即有rows和total字段。
	 * @param page	当前页码	
	 * @param rows	当前页显示条数
	 * @return
	 */
	public JSONObject getCurrentPageRows(JSONObject jSource, String page, String rows)
	{
		System.out.println("获取参数page-------"+page);
		System.out.println("获取参数rows-------"+rows);
		
		int total = jSource.getInt("total");
		
		//分页查询参数：关键是找到第几页和这一页第一条数据的在原始记录中序号
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//当前页是第几页
        int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页显示条数 
       
        //每页的第一条数据是原始数据的第几条数据： 第一页第一条是1,第二页第一条是number +1   
        int start = (currentpage-1)*pagesize;//开始下标	注意：数组下标为[0, n-1]
        
        int end = start+pagesize-1;//结束下标
        if(end>=total)
        	end = total-1;
        
        System.out.println("记录总数-------"+total);
        System.out.println("当前页码-------"+currentpage);
        System.out.println("每页显示条数-------"+pagesize);
        System.out.println("本页开始id-------"+start);
        System.out.println("本页结束id-------"+end);	        
        
        //截取子数组方法一  两种方法时间都差不多
        //List<String> list =jSource.getJSONArray("rows").subList(start, end);
        //JSONArray range = JSONArray.fromObject(list);
        
        //截取子数组方法二
        JSONArray source = jSource.getJSONArray("rows");
        JSONArray range = new JSONArray();
        for(int i=start; i<=end; i++)//按照下标范围去源数据中截取子数组，再传给页面展示
        {
        	range.add(source.get(i));
        }
        System.out.println("-------range.size()="+range.size());
        
        
        JSONObject  easyui = new JSONObject();
        easyui.put("total",total);
        easyui.put("rows",range);
        
        return easyui;
	}

}
