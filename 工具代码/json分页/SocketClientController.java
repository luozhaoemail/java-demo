package com.java.controller.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.java.entity.Permission;
import net.sf.json.JSONObject;

/**
 * Socket客户端
 * @ClassName: SocketClientController 
 * <p>Description: 用于从纯后台取数据的工具类，socket调用取到数据</p>
 * @author lz   
 */
public class SocketClientController 
{
	//默认配置
	private static String ip ="localhost";//在服务器上通过内网访问，使用主机名lyy1或者localhost
	private static int port = 10005;
	
	//通过构造器读取配置文件中的ip和port
	public SocketClientController()
	{
		//从xml配置文件中读取参数
		ApplicationContext ct = new ClassPathXmlApplicationContext("applicationContext.xml");
		Permission per = (Permission) ct.getBean("socket");
		ip = per.getIp();
		port  = per.getPort();
		System.out.println("Permission======"+per.toString());
	}
	
	public SocketClientController(String hostname, int socketport)
	{
		ip = hostname;
		port  = socketport;
	}
	

	/**
	 * 发送命令到服务器执行，然后返回字符串，并转为JSONObject格式返回。
	 * 注意返回结果必须是{}格式的，即JSONObject对象，而不是JSONArray
	 * @param sendCommand 待发送的命令
	 * @param extendParam 额外参数，可为空
	 * @return JSONObject
	 */
	public JSONObject SocketConnect(String sendCommand) 
	{
		BufferedReader br = null;  
		PrintWriter pw = null;
		JSONObject jsonObject = null;
		Socket socket = null;  
	    try
	    {
	    	System.out.println("客户端连接启动Socket:");
	    	socket = new Socket(ip, port);  //建立连接
	    	
	    	//建立输出流用于传输服务器端socket
	    	pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8")));   //桥梁作用
         	
	    	///////////////////////////////////////////////
            pw.println(sendCommand);//传输数据,也就是发送命令          
            pw.flush();  //刷新，清除缓存            
            ///////////////////////////////////////////////
            
            //socket.shutdownOutput();  //传输数据关闭
            
            //建立输入流，接收服务器返回的结果
            br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String str = br.readLine(); 
            //socket.setSoTimeout(60000);//设置超时
            
            System.out.println("检验返回数据到客户端的数据="+str);
            jsonObject = JSONObject.fromObject(str);//将返回结果转为json对象
            pw.flush();
            
		} 
	    catch (Exception e) 
	    {
			System.out.println("返回数据转换出现异常");
		}
       finally
	   {
	      	try 
	      	{
	      		if(null!=socket)
	      		{
	      			socket.close();
	      		}
			} 
	      	catch (IOException e)
	      	{
				//do nothing	
			}
	      }
	    //返回结果
		return jsonObject;
		
	} 
}
