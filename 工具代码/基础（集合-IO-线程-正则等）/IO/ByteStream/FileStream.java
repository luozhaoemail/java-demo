package cn.IO.ByteStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 字节流byte,可以处理图片等数据，以二进制位单位 
 * 字节流基类 InputStream OutputSteam，基类为后缀名 
 * 
 * FileOutputStream文件输出流写入诸如图像数据之类的原始字节的流  
 * 注意区别：FileWriter是写入字符流
 */
public class FileStream {

	public static void main(String[] args) throws IOException {
		//writeFile();
		readFile();
		readFile_2();
	}


	public  static void writeFile() throws IOException{ 
		
		FileOutputStream fos = new FileOutputStream("file\\stream.txt");
		
		fos.write("abcde".getBytes()); //转成byte数组再写入
		
		//字节流是最小单位，不需要刷新。 字符流要刷新的原因是：一个字符由多个字节组成，需要缓冲
		fos.close();
	}
	
	private static void readFile() throws IOException {
	
		FileInputStream fin = new FileInputStream("file\\stream.txt");
		byte[] buf = new byte[1024];
		int len = 0;
		while((len=fin.read(buf))!=-1){
			System.out.println(new String(buf,0,len));
		}
		fin.close();
	}
	
	private static void readFile_2() throws IOException {
		
		FileInputStream fin = new FileInputStream("file\\stream.txt");
		
		int num = fin.available(); //获取个数
		System.out.println("字符个数为："+num);   //换行符占2个
		
		byte[] buf = new byte[num];//定义一个大小刚刚好的缓冲区,就不用循环了 
		fin.read(buf);   //但是这种方法适用于文件不是很大的情况，当文件大于内存时就会溢出
		
		System.out.println("内容："+new String(buf)); 
		fin.close();
	}
}
