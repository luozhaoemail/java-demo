package cn.net;

import java.io.*;
import java.net.Socket;


public class TransClient {

	public static void main(String[] args) throws Exception {
		System.out.println("客户端运行--------");
		Socket s = new Socket("172.23.22.211",10005);
		
		//键盘读入字符串string    字符流
		BufferedReader buf =new BufferedReader(new InputStreamReader(System.in));
		
		//OutputStream out = s.getOutputStream();  //输出流为字节流 byte[]		
		//BufferedWriter  bufout =new BufferedWriter(new OutputStreamWriter(out));//转换为字符流		
		PrintWriter out =new PrintWriter(s.getOutputStream(),true);//打印流   发送数据

		//InputStream in =s.getInputStream();  接收服务端返回的数据   
		BufferedReader bufin = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		String line =null;
		while((line=buf.readLine())!=null){				
			out.println(line);//将键盘数据发送到服务端
		    //out.flush();
			if("0".equals(line))
				break;
			
			String backStr = bufin.readLine(); //读取服务端返回数据
			System.out.println(backStr);
		
			
		    
		}
		
	  s.close();
	}

}
