package cn.net;

import java.io.*;
import java.net.Socket;


public class UpTextsClient {

	public static void main(String[] args) throws Exception {
		System.out.println("客户端运行--------");
		Socket s = new Socket("172.23.22.211",10005);
		
		BufferedReader buf =new BufferedReader(new FileReader("file\\1.txt"));
		
		PrintWriter out =new PrintWriter(s.getOutputStream(),true);		
		
		String line =null;
		while((line=buf.readLine())!=null){	
			
			out.println(line);//将键盘数据发送到服务端							    
		}
		//发送结束标志
		//out.println("0");
		s.shutdownOutput();
		
		//接收服务端返回的数据   
		BufferedReader bufin = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String info = bufin.readLine();
		System.out.println(info);
		
		buf.close();
		s.close();
	}

}
