package cn.SystemClass;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateClass {

	public static void main(String[] args) {
	
		Date d = new Date();
		System.out.println(d);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  E   hh:mm:ss");
		String time = format.format(d);
		
		System.out.println(time);
	}
	
	public static void cout(Object obj)
	{
		System.out.println(obj);
	}

}
