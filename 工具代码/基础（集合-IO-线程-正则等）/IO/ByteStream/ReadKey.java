package cn.IO.ByteStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 字符流：FileReader FileWriter  BufferedReader  BufferedWriter
 * 字节流:FileInputStream FileOutputstream BufferedInputStream BufferedOutputStream
 * 
 * InputStreamReader 字节流转向字符流
 * OutputStreamWriter 字符流转向字节流
 * 键盘录入
 * @author luozhao
 *
 */
public class ReadKey {

	public static void main(String[] args) throws IOException {
		//myReadLine();
		//trans1();
		trans2();
		
	}
	
	public static void trans2() throws IOException{ //写入转换流
		InputStream in = System.in; //键盘录入是字节流		
		InputStreamReader insr = new InputStreamReader(in);  //字节流转向字符流				
		BufferedReader bufr = new BufferedReader(insr);//读取缓冲
		//BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in)); 等价于
		
		OutputStream our = System.out; //字符输出流
		OutputStreamWriter osw = new OutputStreamWriter(our); //字符流转成字节流
		BufferedWriter buwr = new BufferedWriter(osw);//写入缓冲
		//BufferedWriter buwr = new BufferedWriter(new OutputStreamWriter(System.out));等价于 
		
		String line = null;
		while((line=bufr.readLine())!=null){   //读
			if("over".equals(line))
				break;
		    //System.out.println(line.toUpperCase()); 这一句用下面的替代，输出语句的原理
			buwr.write(line.toUpperCase());  //写
			buwr.newLine();//写入换行符
			buwr.flush();
		}
		
		bufr.close();
		
	}
	
	public static void trans1() throws IOException{ //读取转换流
		InputStream in = System.in; //键盘录入是字节流
		
		InputStreamReader insr = new InputStreamReader(in);  //字节流转向字符流
				
		BufferedReader bufr = new BufferedReader(insr);
		
		String line = null;
		while((line=bufr.readLine())!=null){
			if("over".equals(line))
				break;
			System.out.println(line.toUpperCase());
		}
		bufr.close();
		
	}
	
	public static void myReadLine() throws IOException{
		InputStream in = System.in;
		StringBuilder sb = new StringBuilder();
		
		while(true){   //这就是readLine()方法的原理
			int ch = in.read();
			if(ch == '\r')
				continue;
			if(ch == '\n')
			{
				String s = sb.toString();
				if("over".equals(s))
					break;
				System.out.println(s.toUpperCase());
				sb.delete(0,sb.length()); //换行后要清空缓冲区
			}
			else
				sb.append((char)ch);  //这是一行内容
			
			//System.out.println('\r'+0); //13
			//System.out.println('\n'+0);  //10			
		}
	}

}
