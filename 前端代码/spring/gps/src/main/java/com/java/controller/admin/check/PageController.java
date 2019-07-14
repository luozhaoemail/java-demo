package com.java.controller.admin.check;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.entity.GPSCount;
import com.java.entity.PageInfo;
import com.java.service.GPSService;
import com.java.utils.JsonPage;
import com.java.utils.ResponseUtil;

@Controller
@RequestMapping("/admin/check")
public class PageController {
	private JSONObject pageJson;
	private boolean isfirstLoad=true;
	
	@Resource
	private GPSService gpsService;
	
	
	@RequestMapping("/page")
	@ResponseBody
	private void page(HttpServletRequest request, HttpServletResponse response,
						String page, String rows)throws Exception{
		
		String time1 = request.getParameter("time1").trim();
		String time2 = request.getParameter("time2").trim();
				
		System.out.println("\n----分页查询2-------");
		PageInfo pi = new PageInfo();
		pi.pageNumber = Integer.parseInt(page); //第几页
		pi.pageSize = Integer.parseInt(rows); //每页多少条
		
		gpsService.getPages(time1,time2,pi); //查询业务层
		
		JSONObject pges = new JSONObject();
		pges.put("rows",pi.list);
		pges.put("total",pi.pageTotal);
		ResponseUtil.write(response, pges);
	}
	
	
	/**
	 * 加上注解ResponseBody，返回JSON字符串以流的方式输出，不会跳转，自动设置响应头为application/json
	 */
	@RequestMapping("/count1")
	@ResponseBody
	private List<GPSCount> count1(HttpServletRequest request, HttpServletResponse response)throws Exception{	
		List<GPSCount> list = gpsService.gpstime_num();		
		return list;
	}
	
	@RequestMapping("/count2")
	@ResponseBody
	private List<GPSCount> count2(HttpServletRequest request, HttpServletResponse response)throws Exception{	
		List<GPSCount> list = gpsService.gpsspeed_num();		
		return list;
	}
}
