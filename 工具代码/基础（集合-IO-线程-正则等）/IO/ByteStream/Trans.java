package cn.IO.ByteStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**转换流可以指定字符编码如	GBK	UTF-8
 * OutputStream osw=new OutputStream(new FileOutputStram("D:\\out.txt"),"UTF-8")
 * 
 * InputStreamReader   字节流   通向   字符流   的桥梁，这个对象本身是字符流
 * OutputStreamWriter  字符流   通向   字节流   的桥梁，这个对象本身是字符流
 *
 * 流操作3个明确：1.明确源和目的： 源：输入流     InputStream   Reader
 *  					 目的：输出流    OutputStream  Writer
 * 2.明确数据是否是纯文本：是，字符流； 不是，字节流。
 * 
 * 3.明确具体使用的对象： 源设备：内存、硬盘、键盘     目的设备：内存、硬盘、控制台
 * 
 * 文件-->文件 FileReder-->FileWriter   可以加入Buffered提高效率
 * 键盘-->文件InputStreamReader --> OutputStreamWriter
 */
public class Trans {

	public static void main(String[] args) throws IOException {
	//键盘(控制台)-->文件
	//BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));	
	//BufferedWriter buwr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\out.txt")));
	
    //文件-->控制台
	BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\out.txt")));
	BufferedWriter buwr = new BufferedWriter(new OutputStreamWriter(System.out));
		
	    //下面的是通用代码
	    String line = null;
		while((line=bufr.readLine())!=null)
		{
			if("over".equals(line))
				break;
			buwr.write(line.toUpperCase());
			buwr.newLine();
			buwr.flush();
		}
		
		bufr.close();
		buwr.close();

	}

}
