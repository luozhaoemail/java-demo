package cn.IO.ByteStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 用文件字节流 复制一个图片
 */
public class CopyPic {

	public static void main(String[] args) {

		FileInputStream fin = null;
		FileOutputStream fos = null; 
		
		try
		{
			fin = new FileInputStream("file\\1.jpg"); //读  1.jpg
			fos = new FileOutputStream("file\\copy.jpg");//写  复制成copy.jpg	
			
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
