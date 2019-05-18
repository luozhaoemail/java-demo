package cn.net;

import java.net.ServerSocket;
import java.net.Socket;

public class UpPicServer {

	public static void main(String[] args) throws Exception {
		System.out.println("服务端开启--------");
		ServerSocket sc = new ServerSocket(10005);
		
		while(true){	
			Socket s = sc.accept();	
	
			//多线程并发处理多个客户端同时上传
			new Thread(new UpLoadPic(s)).start();			
		}
	}

}
