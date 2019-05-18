package view.niuke.num;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		
		for(int i=0; i<10; i++) 
		{
			int rand = new Random().nextInt(1000);
			if(list1.contains(rand)) 			
				i--;			
			else 
				list1.add(rand);			
		}
		System.out.println("原始："+list1);
		
		for(Integer i : list1) 
		{
			char[] tmp = (i+"").toCharArray();
			int re = reverse(tmp);
			list2.add(re);
		}
		
		list2.sort(Comparator.reverseOrder());
	
		System.out.println("转置："+list2);
	}

	private static int reverse(char[] num) 
	{
		String str = "";
		if(num[0] == '-')
		{
			str = "-";
			for(int i=num.length-1; i>=1; i--) 			
				str += num[i];
		} 
		else 
		{
			for(int i=num.length-1; i>=0; i--) 			
				str += num[i];			
		}
		
		return Integer.valueOf(str);
	}
}
