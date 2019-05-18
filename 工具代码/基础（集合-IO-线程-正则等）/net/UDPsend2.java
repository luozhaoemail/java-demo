package cn.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class UDPsend2 {

	public static void main(String[] args) throws IOException {
		System.out.println("发送端！！！！");
		DatagramSocket ds =new DatagramSocket(); //1.建立udp的socket
		
		//2	数据封装成包	
		 BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		 String text =null;		 
         while((text=bufr.readLine()) != null){
             if("0".equals(text))
            	 break;

             byte []buff = text.getBytes();//转成字符数组

             DatagramPacket dp = new DatagramPacket(buff,buff.length,
            		 				InetAddress.getByName("luozhao-PC"),10000);

             ds.send(dp);//3 用socket对象的send方法发送数据包
         }
	     
         ds.close(); //4 关闭资源
	}

}
