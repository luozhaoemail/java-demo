package cn.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class UDPreceive2 {

	public static void main(String[] args) throws IOException {
		System.out.println("接收端--------");
		DatagramSocket ds =new DatagramSocket(10000);//1创建UDP套接字socket
		while(true){
			byte [] buf =new byte[1024];  //2定义数据包
			DatagramPacket dp =new DatagramPacket(buf,buf.length); 
			ds.receive(dp);    //3接收数据   会阻塞，因为在等待发送端数据
			
			String ip = dp.getAddress().getHostAddress();  //4通过数据包对象获取内容
			int port = dp.getPort();
			String txt = new String(dp.getData(),0,dp.getLength());		
			System.out.println(ip+":"+port+" ----> "+txt);
		
		}
		//ds.close(); //关闭
		
	}

}
