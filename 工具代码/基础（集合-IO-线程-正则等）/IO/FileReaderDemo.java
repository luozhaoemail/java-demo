package cn.IO;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {

	public static void main(String[] args) throws IOException {

		//read1();
		read2();
	}

	private static void read2() throws IOException {  
		
		FileReader fr = new FileReader("file\\2.txt");  //abc12
		
		char[] buf = new char[3]; 
		/**read(char []) 读取字符数组，返回字符的个数 ，末尾返回-1
		 * 缓冲区只能存3个字符，也就是每次只能读3个字符                 a b c。每次读到的字符会覆盖前面的，第二次读只有1、2两个字符会把
		 * 数组里之前的a、b覆盖掉，而数组里的c字符位置不会覆盖  1 2 c
		 */
		int  num =0;
		while((num=fr.read(buf))!=-1){
			
		//	System.out.println("num="+num+"-----"+new String(buf)); 每次取整个数组长度
			
			/**
			 * String(char[] value, int offset, int count) 
          	        分配一个新的 String，读取指定长度是字符串。读到几个取几个
			 */
			System.out.println("num="+num+"-----"+new String(buf,0,num));//
		}		
		
		fr.close();
		
	}

	public static void read1() throws IOException {
		
		FileReader fr = new FileReader("file\\2.txt");
		
		//int ch = fr.read();//一次读一个字符，会自动往下读。返回的字符在0-65535之间，读到末尾返回-1
		
		int ch = 0;
		while((ch=fr.read())!= -1){
			System.out.print((char)ch);//强转
		}
		
		//System.out.println(ch);// ASCII码
		//System.out.println((char)ch);//强转
		
		fr.close();//这个close不会刷新流，而是直接关闭
	}
}
