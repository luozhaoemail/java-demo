package cn.IO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * public class BufferedWriter extends Writer 是Writer的子类，用来提供读写效率的，
 * 其原理是封装了数组
 * @author luozhao
 *
 */
public class BufferedWriterDemo {

	public static void main(String[] args) throws IOException {
		
		FileWriter fw = new FileWriter("file\\buf.txt");  //创建文件写入流
 
		BufferedWriter w = new BufferedWriter(fw); //将流对象传给缓冲区
		w.write("111111");
		
		w.newLine(); //写入换行符，可以跨平台    windows下相当于w.write("\r\n"); Linux下换行符为\n
		
		w.write("222222");
		w.flush();//只要用到缓冲区，就要刷新
		
		w.close();//不需要fw.close()，只需要把缓冲区关闭就能关闭流对象
		
	}

}
