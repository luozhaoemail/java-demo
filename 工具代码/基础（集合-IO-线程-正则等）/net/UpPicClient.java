package cn.net;



import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class UpPicClient {

	public static void main(String[] args) throws Exception {
		System.out.println("客户端开启--------");
		Socket s = new Socket("172.23.22.211",10005);
		
		File pic = new File("file\\1.jpg");
		FileInputStream fin = new FileInputStream(pic);
		/*图片传输用字节流*/
		
		OutputStream out = s.getOutputStream();
		
		byte[] buf = new byte[1024];//缓冲
		int len = 0;
		
		while((len=fin.read(buf)) !=-1 ){
			out.write(buf,0,len);
		}
		s.shutdownOutput();
		
		/*接收 上传成功反馈*/
		InputStream in = s.getInputStream();
		byte[] bufIn =new byte[1024]; 
		int lenIn =in.read(bufIn);
		System.out.println(new String(bufIn,0,lenIn)); //字节转字符

		s.close();
	}

}
