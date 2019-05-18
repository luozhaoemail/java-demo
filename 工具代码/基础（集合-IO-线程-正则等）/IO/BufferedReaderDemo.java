package cn.IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderDemo {

	public static void main(String[] args) throws IOException {
		
		FileReader fr = new FileReader("file\\buf.txt");  //创建文件读取流对象fr
		
		BufferedReader r = new BufferedReader(fr); //将流对象传给缓冲区
		
		/** String s = r.readLine();//一次读取一行  包含该行内容的字符串，不包含任何 行终止符，如果已到达流末尾，则返回 null 
		 * readLine()只返回换行符之前的数据，不返回换行符
		 */
		
		String line = null;
		while((line=r.readLine())!=null){
		  System.out.println(line);
		}
			    
	    r.close();
	}
}
