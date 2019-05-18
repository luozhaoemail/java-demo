package cn.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class UpLoadPic implements Runnable {
	private Socket s;
	
	public UpLoadPic(Socket s) {
		this.s = s;
	}
	
	public void run(){
		try{
			
			String ip = s.getInetAddress().getHostAddress();
			System.out.println(ip+"......连接上了");
			
			InputStream in = s.getInputStream();
			
			int i = 0;
			File pic = new File("file\\"+ip+".jpg");
			
			while(pic.exists()){ //解决重名覆盖文件 
				i++;
				pic =new File("file\\"+ip+"("+i +").jpg");
			}			
			FileOutputStream fout = new FileOutputStream(pic);
			
			byte[] buf =new byte[1024];
			int len = 0;
			while((len=in.read(buf))!= -1){
				
				fout.write(buf,0,len);
			}
			
			OutputStream out = s.getOutputStream();
			out.write("上传成功！".getBytes());
			
			fout.close();
			s.close();
		}catch(IOException e){}
		
	}
}
