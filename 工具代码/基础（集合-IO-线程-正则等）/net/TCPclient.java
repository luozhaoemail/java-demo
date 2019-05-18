package cn.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPclient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("客户端运行--------");
		
		//1 建立tcp的 客户端Socket 
		Socket s = new Socket("172.23.22.211",10003);
		
		//2 通道中出行socket io流  。客户端要获取套接字的输出流，并发送到服务端  
		//从客户端发出为输出流，从服务端接收为输入流。  而在底层自动将输出流转为输入流
		
		OutputStream out = s.getOutputStream();
		
		out.write("hello world".getBytes()); //3
		s.close(); //4
	}

}
