package com.java.back.run;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import com.java.back.api.Manager;

public class TcpAgent extends Thread
{
	private Manager mg;//接口
	private Socket soclink ;
	private BufferedReader br;
	private PrintWriter pw;
	
	public TcpAgent(){;}
	
	public boolean init(Manager mg,Socket soclink)
	{
		this.mg = mg;
		this.soclink = soclink;
		
		try 
		{	
			System.out.println("\n=============>TcpAgent初始化");
		    soclink.setKeepAlive(true);  
            soclink.setSoTimeout(10 * 1000); //20秒
	        br = new BufferedReader(new InputStreamReader(soclink.getInputStream(),"UTF-8"));  
	        pw = new PrintWriter(soclink.getOutputStream(),true);
		}
		catch ( Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void run()
	{
	    while(true)
		{
			try{
        		Log.log("\n=============> 等待新的客户端接入 ....\n");
        		soclink.sendUrgentData(0); //发送心跳包,判断远端是否断开了连接
	        	String str = br.readLine();
	        	Log.log("\n=============> 收到客户端消息 :" + str);
				
				if(null != str && str.length() > 10) // '{command:}'的长度=10
				{
					long t1 = System.currentTimeMillis();
					/////////////////////////////////////////////////
					///////////Manager -> AnaManager.receive ，跳转AnaManager/////////
					/////////////////////////////////////////////////
					System.out.println("=============> PrintWriter地址 = "+pw.toString());
					if(false == mg.receive(str,pw))////调用接口
					{
						String strReq = "{command:'req',cause:'procfail'" + "}";
						pw.println(strReq);
					}
					
					long t2 = System.currentTimeMillis();
					Log.log("处理完毕,花费时间="+(t2-t1)/1000.0+"秒 ");
				}
				
			}catch(SocketTimeoutException e){
				e.printStackTrace();
				Log.log(soclink.getInetAddress().getHostAddress()+"超时退出");
				closeSocket();
				break;
			}catch(SocketException e){  
				e.printStackTrace();
				Log.log(soclink.getInetAddress().getHostAddress()+"连接已经关闭"); 
				closeSocket();
				break;
			} catch (Exception e) {
				Log.log(soclink.getInetAddress().getHostAddress()+" "+e.getMessage());
				closeSocket();
				break;
			}
		}//end while
	}
	
	public void closeSocket()
    {
        try
        {
            System.out.println("======================>关闭socket连接："+soclink.getInetAddress().getHostAddress()+"\n\n");
            soclink.close();
            br.close();
            pw.close();
        }
        catch (Exception e) {  
            e.printStackTrace();  
        }
    }
}
