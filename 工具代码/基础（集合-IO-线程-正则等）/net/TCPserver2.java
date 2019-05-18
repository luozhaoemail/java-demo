package cn.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPserver2 {

	public static void main(String[] args) throws Exception {
		System.out.println("服务端开启--------");
		
		ServerSocket ss =new ServerSocket(10004);    
		Socket s = ss.accept();
		System.out.println(s.getInetAddress().getHostAddress()+"......连接上了");
		
		InputStream in = s.getInputStream();   //接收数据
		byte[] buf = new byte[1024];
		int len =in.read(buf);
		String str = new String(buf,0,len);
		System.out.println(str);
		
		//发送反馈
		OutputStream out =s.getOutputStream();
		out.write("我已收到了哈，客户端".getBytes());
		
		s.close();
		ss.close();
	}

}
