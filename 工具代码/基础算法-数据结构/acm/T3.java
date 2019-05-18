package view.acm;

import java.util.*;

public class T3 {
	/**即一个长度为n的数组，每一个数的范围是1到9，
	 * 现在我们需要将这个数组分成多个连续子数组，
	 * 保证每个子数组内数字均不相同，问一共有多少种满足要求的分法。 
	 */
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
		{			
			int n = sc.nextInt();
			List<int[]> list =new ArrayList<int[]>();			
			for(int i=0; i<n; i++)
			{ 
				int[] tt=new int[2];
				tt[0] = sc.nextInt();
				tt[1] = sc.nextInt();
				list.add(tt);			
			}	
			
			for(int[] b : list)
				for(int i=0; i<2; i++)
					System.out.println(b[0]+","+b[1]);
			
		}
		
	}
	
	public static void fun(int[] a)
	{
		/**dp[i]表示前i个数有多少种分法
		 *  限制条件是数是否重复
		 *  第i个数可以单独组成一组：dp[i] = dp[i]+1；
  			第i个数和第i个数组成一组：dp[i+1] = dp[i-1]。
		 */
		int n = a.length;
		Set<Integer> set = new HashSet<Integer>();		
		for(int i=0;i<n; i++)
		{
			set.add(a[i]);
			
		
			
		}
		
		
	}
	

}
