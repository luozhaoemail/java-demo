package cn.net.myServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 自定义服务器，运行后通过客户浏览器输入域名和端口便可以访问到。服务器给浏览器返回“欢迎访问”文本信息，服务器控制台打印出访问信息
 * Tomcat也是基于这种原理：建立TCP连接，通过套接字编程传输数据流
 * @author luozhao
 *
 */
public class MyServer {

	public static void main(String[] args) throws IOException {
		System.out.println("服务端开启--------");
		
		ServerSocket sc =new ServerSocket(10005);		
		while(true){
			Socket s = sc.accept();	
			
			String ip = s.getInetAddress().getHostAddress();
			System.out.println(ip+"......连接上了");
			
			//接收
			InputStream in = s.getInputStream();
			byte[] buf =new byte[1024];
			int len = in.read(buf);
			String str = new String(buf,0,len);
			System.out.println(str); //显示在控制台
			
			//发送
			PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
			pw.println("<font color='red' size='8'>欢迎访问</font>");  //写回客户端
			
			s.close();
		}
		
	}

}
