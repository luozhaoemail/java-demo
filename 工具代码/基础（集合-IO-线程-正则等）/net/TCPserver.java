package cn.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPserver {

	public static void main(String[] args) throws IOException {
		System.out.println("服务端开启--------");
		ServerSocket ss =new ServerSocket(10003);//1创建服务器端 套接字
	
		while(true){
			Socket s= ss.accept(); //2 获取客户端对象  阻塞
			String ip = s.getInetAddress().getHostAddress();
			System.out.println("ip"+"......连接上了");
			InputStream in = s.getInputStream(); //3获取输入流
			
			//4显示
			byte[] buf =new byte[1024];
			int len =in.read(buf);
			String txt = new String(buf,0,len);
			System.out.println(txt);
	
			s.close();
		}
		
	}

}
