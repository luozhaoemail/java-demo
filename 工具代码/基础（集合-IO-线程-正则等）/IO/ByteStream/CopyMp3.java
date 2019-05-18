package cn.IO.ByteStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 用文件字节流 复制一个图片
 */
public class CopyMp3 {

	public static void main(String[] args) throws IOException {

		long s = System.currentTimeMillis();
		
		//copy1(); //采用自定义数组缓冲区buf[1024]  125毫秒		
		//copy2(); //采用封装好的缓冲区BufferedStream    437毫秒
		copy3(); //采用自定义缓冲类MyBuffered    343毫秒
		
		long e = System.currentTimeMillis();
		System.out.println("总共用时 "+(e-s)+" 毫秒");		
		
	}
	
	
	private static void copy3() throws IOException {
		MyBuffered bufin = new MyBuffered(new FileInputStream("D:\\音乐\\王菲 - 邮差.mp3"));
		BufferedOutputStream bufout = new BufferedOutputStream(new FileOutputStream("D:\\音乐\\4.mp3"));
				
		int by =0;		
		//System.out.println("第一个字节： "+bufin.myRead());
		while((by=bufin.myRead())!=-1) //读取1个字节   如果二进制文件刚刚和结束发-1相同  (11111111)就会读取出错，为了避免转成4字节的int型
			bufout.write(by);  //虽然读取1个字节转成4字节，但写回的时候会强转回去，只取有效的低8位
		
		bufin.myClose();
		bufout.close();
		
	}


	private static void copy2() throws IOException {   //这是一个通用模板，用字节流复制任何文件
		
		BufferedInputStream bufin = new BufferedInputStream(new FileInputStream("D:\\音乐\\王菲 - 邮差.mp3"));
		BufferedOutputStream bufout = new BufferedOutputStream(new FileOutputStream("D:\\音乐\\3.mp3"));
		//其实缓冲区里面就封装了数组,就不用再定义数组了,从输入流读到输出流都在内存中操作
		
		int by =0;		
		while((by=bufin.read())!=-1)
			bufout.write(by);
		
		bufin.close();
		bufout.close();
	}
	
	
	private static void copy1() {   //这是一个通用模板，用字节流复制任何文件
		FileInputStream fin = null;
		FileOutputStream fos = null; 
		
		try
		{
			fin = new FileInputStream("D:\\音乐\\王菲 - 邮差.mp3"); //读  
			fos = new FileOutputStream("D:\\音乐\\2.mp3");//写  复制	
			
			byte[] buf =new byte[1024];
			int len =0;
			
			while((len=fin.read(buf))!=-1) 
				fos.write(buf,0,len);    //边读边写
			
		}
		catch(IOException e)
		{
			throw new RuntimeException("复制文件失败");
		}
		finally
		{
			try{
				if(fin!=null)
					fin.close();
			   }
			catch(IOException e)
				{
					throw new RuntimeException("读取关闭失败");
				}
			try{
				if(fos!=null)
					fos.close();
			   }
			catch(IOException e)
				{
					throw new RuntimeException("写入关闭失败");
				}
		}

	}

}
