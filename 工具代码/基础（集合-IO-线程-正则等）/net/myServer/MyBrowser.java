package cn.net.myServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 模拟浏览器发送http消息
 * @author luozhao
 *
 */
public class MyBrowser {

	public static void main(String[] args) throws IOException {
		Socket s = new Socket("172.23.22.211",10005);
		PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
	    //发送
		pw.println("GET /myweb/1.html  HTTP/1.1");
		pw.println("Accept: */*");
		pw.println("HOST: 172.23.22.211:10005");
		pw.println("Connection: close");
		pw.println();//空行
		
		//接收
		InputStream in = s.getInputStream();
		byte[] buf =new byte[1024];
		int len = in.read(buf);
		String str = new String(buf,0,len);
		System.out.println(str); //显示在控制台
	    
		s.close();
	}

}
