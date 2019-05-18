package cn.net;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class UpTextServer {

	public static void main(String[] args) throws Exception {
		System.out.println("服务端开启--------");
		ServerSocket sc =new ServerSocket(10005);		
		
		while(true){
			Socket s = sc.accept();
			System.out.println(s.getInetAddress().getHostAddress()+"......连接上了");
			
			//接收客户端发来的数据
			BufferedReader buf = new BufferedReader(new InputStreamReader(s.getInputStream()));
			//用于给客户端发送数据  
			PrintWriter pw = new PrintWriter(new FileWriter("file\\2.txt"),true);
		
			String line = null;
			while((line=buf.readLine())!=null){
			//	if("0".equals(line)) break;
				pw.println(line.toUpperCase());				
			}
			PrintWriter out =new PrintWriter(s.getOutputStream(),true);
			out.println("上传成功！");
			
			s.close();
		}
	}

}
