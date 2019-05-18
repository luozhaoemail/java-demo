package cn.net;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;



public class TCPclient2 {

	public static void main(String[] args) throws Exception {
	  
		System.out.println("客户端运行--------");
		Socket s =new Socket("172.23.22.211",10004);
	   
	   OutputStream out = s.getOutputStream();
	   out.write("hhhhh**********************hhhhh".getBytes());     //发送数据

	   InputStream in = s.getInputStream();   //接收服务端反馈
	   byte[] buf = new byte[1024];
	   int len = in.read(buf);
	   String str =new String(buf,0,len);
	   System.out.println(str);
	
	   s.close();
	}

}
