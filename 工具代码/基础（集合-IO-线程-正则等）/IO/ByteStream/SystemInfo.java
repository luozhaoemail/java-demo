package cn.IO.ByteStream;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Properties;

public class SystemInfo {

	public static void main(String[] args) throws FileNotFoundException {
		
		Properties pro = System.getProperties();  //获取系统状态
		
		pro.list(new PrintStream("D:\\sysinfo.txt"));  //list()将属性列表输出到指定输出流

	}

}
