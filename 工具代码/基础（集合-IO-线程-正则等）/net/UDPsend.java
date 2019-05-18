package cn.net;

import java.io.IOException;
import java.net.*;

public class UDPsend {

	public static void main(String[] args) throws IOException {
		System.out.println("发送端！！！！");
		DatagramSocket ds =new DatagramSocket(); //1.建立udp的socket
		
		//2	数据封装成包	
		 String text = "hello world";  
         byte []buff = text.getBytes();//转成字符数组
         DatagramPacket dp = new DatagramPacket(buff,buff.length,InetAddress.getByName("luozhao-PC"),10000);
	     
         ds.send(dp);//3 用socket对象的send方法发送数据包
         ds.close(); //4 关闭资源
	}

}
