package cn.IO.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesDemo {
/**
 * Properties是hashtable的子类，具备map集合的特点，
 * 里面的键值对都是字符串
 * 是集合与IO相结合的集合容器
 * 应用于键值对形式的配置文件
 * @throws IOException 
 */
	public static void main(String[] args) throws IOException {
		
		//pro();
		
		//readPro();
	
	    loadPro();
	}

	
	public static void pro() throws IOException{
		
		Properties p =new Properties();
		
		p.setProperty("张三", "30");
		p.setProperty("李四", "40");
		p.setProperty("王五", "50");
		
		//p.setProperty("li4", 100+"");//修改
		
		String value =p.getProperty("张三");
		System.out.println(value);
	
		Set<String> names = p.stringPropertyNames();//key		
		for(String s:names) //遍历
			System.out.println(s+"----"+p.getProperty(s));//value
		
		p.list(new PrintStream("D:\\user.properties"));  //list()将属性列表输出到指定输出流
		
	}
	
	private static void readPro() throws IOException {  //读取配置文件
		
		BufferedReader bufr = new BufferedReader(new FileReader("D:\\user.properties"));
		Properties p =new Properties();
		
		String line = null;
		while((line=bufr.readLine())!=null){
			
			String[] arr = line.split("=");//拆分等号
			p.setProperty(arr[0], arr[1]);
			
			//System.out.println(line);
		}
		
		bufr.close();
		System.out.println(p);
	}
	
	private static void loadPro() throws IOException { 
		Properties p = new Properties();
		
		FileInputStream fin = new FileInputStream("D:\\sysinfo.txt");
		
		p.load(fin); //加载属性文件
		
		//System.out.println(p);	
		p.setProperty("os.name", "luozhao0000000000");//修改
		
		FileOutputStream fout = new FileOutputStream("D:\\sysinfo.txt");
		p.store(fout, "*************************"); //保存配置文件(文件输出流，注释信息)
		
		p.list(System.out);
		
		fin.close();
		fout.close();
	}
}
