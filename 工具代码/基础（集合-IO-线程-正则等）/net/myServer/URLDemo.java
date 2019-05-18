package cn.net.myServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLDemo {

	public static void main(String[] args) throws IOException {
		
		String str = "http://192.168.1.211:10005/myweb/1.html?name=lz&age=22";
		
		URL url =new URL(str);
		
		System.out.println("getProtocol:"+url.getProtocol());
		System.out.println("getHost:"+url.getHost());
		System.out.println("getPort:"+url.getPort());
		System.out.println("getPath:"+url.getPath());
		System.out.println("getFile:"+url.getFile());
		System.out.println("getQuery:"+url.getQuery());
		
		//URL 封装了socket  能自动解析http协议
		URLConnection conn = url.openConnection();
		System.out.println(conn);
	
		InputStream in = conn.getInputStream();
		byte[] buf =new byte[1024];
		int len = in.read(buf);
		String txt = new String(buf,0,len);
		System.out.println(txt);
	
	}

}
