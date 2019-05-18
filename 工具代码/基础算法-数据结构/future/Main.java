package view.future;

import java.util.*;

/**
0 1 1 1 1 1 1 1 1 0

012345678
12345678

0123456789
123456789
 */
public class Main
{
	
	public static void main(String[] args)
	{		
		Scanner sc = new Scanner(System.in);
//		int[] a = {0,1,2,3,4,5,6,7,8,9};//固定数组
//		Set<Integer> set = new LinkedHashSet<Integer>();
		List<String> list = new ArrayList<String>();
 		
		while(sc.hasNext())
		{					
			String[] str = sc.nextLine().split(" ");
//			String[] str = "0 1 1 1 1 1 1 1 1 0".split(" ");
			int n = str.length;
			int[] b = new int[n];
			for(int i=0;i<n;i++)
			{
				b[i] = Integer.parseInt(str[i]);
			}
			
			
			String num="0123456789";			
			String line =""; 
			String line2 =""; 
			for(int i=0;i<num.length();i++)
			{	
//				int x = Integer.parseInt(num.substring(i,i+1));
				String s = num.substring(i,i+1);
				if(b[i]==0)
				{
					
					String sub1 = num.substring(i+1);
					String sub2 = num.substring(i);
					list.add(sub1);
					list.add(sub2);				
					continue;		
				}
				else
				{
					
				}
				
				
								
			}
			
			String sss="012345678\n"+
						"0123456789\n"+
						"12345678\n"+
						"123456789\n";
			System.out.println(sss);

			
		}//while
	}
	
	
	public static void solution1()
	{
		
		
	}
	
	
	
}
