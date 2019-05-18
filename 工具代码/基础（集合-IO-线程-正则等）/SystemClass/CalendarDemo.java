package cn.SystemClass;

import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarDemo {

	public static void main(String[] args) {
				
		//demo();
		
		Calendar c = Calendar.getInstance();	
		
		c.set(2012,10,11);//月份会自动+1  2012 11 11
		c.add(Calendar.MONTH, -12); //-12月
		c.add(Calendar.YEAR, 4);  //+4年
		demo2(c);
		
	}
		
	
	private static void demo() {
//		Date d = new Date();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy");		
//		System.out.println(format.format(d));
		
		Calendar c = Calendar.getInstance();
		
		String[] mons = {"一月","二月","三月","四月",  //查表法  都从0开始就不用加1
						 "五月","六月","七月","八月",
						 "九月","十月","十一月","十二月"};
		int index = c.get(Calendar.MONTH); // 月份从0开始要+1，9+1
		cout(index);
		cout(mons[index]);
		
		String[] weeks = {"","日","一","二","三","四","五","六"};//前面加一个空就不用减1
		int i = c.get(Calendar.DAY_OF_WEEK);		
		cout(i);// 星期从0开始要-1，5-1
		cout("星期"+weeks[i]);
		
		cout( c.get(Calendar.YEAR)+"年 "
		    +(c.get(Calendar.MONTH)+1 )+"月"
			 +c.get(Calendar.DAY_OF_MONTH)+"日 "
	  +"星期"+(c.get(Calendar.DAY_OF_WEEK)-1 ));
		
	}
	
	
	private static void demo2(Calendar c ) {
				
		String[] mons = {"一月","二月","三月","四月", 
				 "五月","六月","七月","八月",
				 "九月","十月","十一月","十二月"};
		String[] weeks = {"","星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		
		int i = c.get(Calendar.MONTH);
		int j = c.get(Calendar.DAY_OF_WEEK);
		
		cout( c.get(Calendar.YEAR));
		cout( mons[i] );
		cout( c.get(Calendar.DAY_OF_MONTH) );
		cout( weeks[j] );
	}


	public static void cout(Object obj)
	{
		System.out.println(obj);
	}

	
}
