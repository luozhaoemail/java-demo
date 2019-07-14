package com.java.back.run;

import java.net.ServerSocket;
import java.net.Socket;

import com.java.back.api.Manager;


/**
 * 该类定义了socket连接服务器，并继承了Thread线程
 */
public class TcpServer extends Thread{	
	boolean isRun = false;	
	ServerSocket SocServer = null;  // socket服务器
    Socket soclink = null;     // socket 连接   
    Manager mg = null;
    
	public TcpServer(){;}
	
	public boolean init(Manager mg,int serverport,int linkcount)
	{
		try{
			SocServer = new ServerSocket(serverport,linkcount);
		}catch (Exception e){
			Log.log("端口已经被占用：" + serverport);
			return false;
		}
		
		this.isRun = true;
		this.mg = mg;
		return true;
	}
	public void run()
	{
		Log.log("----------> TcpServer线程已经启动");  
		int i=0;
		while(isRun)
    	{
			try{
				// 这里会等待，所以不用加sleep
				Log.log("----------> 等待客户端连接 ...."); 
				soclink = SocServer.accept();
				++i;
				Log.log("----------> 第 "+i+" 个客户端已连接："+soclink.getInetAddress()+":"+soclink.getPort());
				
		        TcpAgent agent = new TcpAgent();
		        agent.init(mg,soclink);
		        agent.start();
		        
			}catch(Exception e){
				e.printStackTrace();
				try {
	                soclink.close();
	            } catch (Exception e2) {
	            	e2.printStackTrace();
	            }
			}
    	}
	}
}
