package com.java.controller.admin.check;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;

import net.sf.json.JSONArray;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.controller.socket.SocketClientController;
import com.java.entity.HbaseBean;
import com.java.utils.ResponseUtil;

@Controller
@RequestMapping("/admin/check")
public class IndexController {
	/*@Resource
	private CountService countService;*/
	
	@RequestMapping("/listTables")
	@ResponseBody
	private JSONArray listTables(HttpServletRequest request, HttpServletResponse response)throws Exception{
		SocketClientController sd = new SocketClientController();		
		String cmd = "{command:'com.java.back.api.SendHbaseView'}";	
		String result = sd.SocketConnect(cmd);
		JSONArray arr = JSONArray.fromObject(result);
		System.out.println("----------"+arr);
		return arr;
	}
	
	//参数自动匹配，要求参数名一样
	@RequestMapping(value="/createTable",produces="text/html;charset=utf-8")
	@ResponseBody
	private String createTable(String tbname, String clfmaily)throws Exception{
		SocketClientController sd = new SocketClientController();
		String cmd = "{command:'com.java.back.api.SendcreatetableView'"
				+ ",tbname:'"+tbname
				+ "',clfmaily:'"+clfmaily
				+"'}";	
		return sd.SocketConnect(cmd);		
	}
	
	//参数自动匹配，要求参数名一样
	@RequestMapping(value="/deleteTable",produces="text/html;charset=utf-8")
	@ResponseBody
	private String deleteTable(String tbname)throws Exception{
		SocketClientController sd = new SocketClientController();
		String cmd = "{command:'com.java.back.api.SenddeletetableView'"
				+ ",tbname:'"+tbname				
				+"'}";
		return sd.SocketConnect(cmd);	
	}
		
}
