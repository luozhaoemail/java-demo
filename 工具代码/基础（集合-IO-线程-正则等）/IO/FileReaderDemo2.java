package cn.IO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 将C盘的一个文件拷贝到D盘
 * @author luozhao
 *
 */
public class FileReaderDemo2 {

	public static void main(String[] args) throws IOException {
		
		//copy1();
		copy2();
	}

	private static void copy1() throws IOException {  //一个字符一个的读和写，读一个写一个
		FileWriter w  = new FileWriter("D:\\copy.java");//目的地  会自动创建这个文件
		FileReader r  = new FileReader("C:\\11\\Luozhao.java"); //源  这个文件必须存在
		
		int ch =0;
		while((ch=r.read())!=-1){ //读出来
			w.write(ch); //写进去
		}
		
		w.close();
		r.close();
	}
	
	private static void copy2() {  //先全部读出来到缓冲再一次性写进去
		FileWriter w = null;
		FileReader r = null;
		try{
			w  = new FileWriter("D:\\copy.java");//目的地  会自动创建这个文件
			r  = new FileReader("C:\\11\\Luozhao.java"); //源  这个文件必须存在
			
			char[] buf = new char[1024];
			
			int len = 0;
			
			while((len=r.read(buf))!=-1){
				w.write(buf,0,len); //写入指定的有效长度字符串
			}
		}
		catch(IOException e){
			throw new RuntimeException("读写失败");
		}
		finally{
			try
			{
				if(r!=null)
					r.close();				
			} 
			catch (IOException e2) {}						
			try
			{
				if(w!=null)
					w.close();				
			} 
			catch (IOException e2) {}
		}
	}

	

}
