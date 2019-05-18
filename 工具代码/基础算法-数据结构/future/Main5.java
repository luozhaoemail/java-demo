package view.future;

import java.util.*;

/**
5 1 3 4 9 7 6 8

1 3 4 7 8
 */
public class Main5
{
	public static Set<Integer> set = new LinkedHashSet<Integer>();
	public static void main(String[] args)
	{		
		Scanner sc = new Scanner(System.in);
//		int[] a = {0,1,2,3,4,5,6,7,8,9};//固定数组
//		Set<Integer> set = new LinkedHashSet<Integer>();
//		List<Integer> list = new ArrayList<Integer>();
 		
//		while(sc.hasNext())
//		{					
//			String[] str = sc.nextLine().split(" ");
			String[] str = "5 1 3 4 9 7 6 8".split(" ");
			int n = str.length;
			int[] b = new int[n];
			for(int i=0;i<n;i++)
			{
				b[i] = Integer.parseInt(str[i]);
			}
			
			
	        System.out.println("最长子序列 =" + solution(b));
			
			/*for(int i=0;i<n-1;i++)
			{
				if(b[i]>b[i+1])
					continue;
				else
					set.add(b[i]);
				
				for(int j=i+1;j<n;j++)
				{
					if(b[j]>b[i])
						break;
					else
					{
						set.add(b[i]);
					}
				}
			}
			
			System.out.println(set);*/
			
//			find(b);
//			System.out.println(getLIS(b));

			
//		}//while
	}
	
	private static String solution(int[] a) 
	{
        int[] lis = new int[a.length];
        String[] str = new String[a.length];
        for (int i = 0; i < a.length; i++) 
        {
            str[i] = a[i] + "";
            lis[i] = 1;
            for (int j = 0; j < i; j++)
            {
                if (a[i] > a[j] && lis[j] + 1 > lis[i]) 
                {
                    lis[i] = lis[j] + 1;
                    str[i] = str[j];
                    str[i] += "," + a[i];
                }
            }
        }
        int n = getMax(lis);
        String[] rst = str[n].split(",");
        return str[n];
    }
	
	private static int getMax(int[] lis)
	{
        int max = lis[0];
        int num = 0;
        for (int i = 1; i < lis.length; i++)
        {
            if (lis[i] > max)
            {
                max = lis[i];
                num = i;
            }
        }
        return num;
    }
	
	
	public static int getLIS(int[] a)
	{  
        if (a==null || a.length==0)
            return 0;   
        
        int con = 1;  
        int[] dp = new int[a.length];  
        for (int i = 0; i < a.length; i++)
        {  
            dp[i] = 1;  
            for (int j = 0; j < i; j++)
            {  
                if (a[j]<a[i])
                {                  	
                    dp[i] = Math.max(dp[i], dp[j]+1);  
                    con = Math.max(dp[i], con);                     
                }  
            }  
        }  
        
        int max=0;
		int index=0;
		for(int i=0;i<dp.length;i++)
		{
			if(dp[i]>max)
			{
				max=dp[i];
				index=i;
			}
		}
		
		int length=max;
		int[] r=new int[max];
		for(int i=index;i>=0;i--)
		{
			if(dp[i]==max)
			{
				max=max-1;
				r[max]=a[i];
			}
		}
		int sum=0;
		for(int i=0;i<length;i++)
		{
			System.out.println(r[i]+" ");
			sum += r[i];
		}
		
		System.out.println(sum);
        
              
//        System.out.println(set);
        
        return max;  
    }  
	
	
	public static void find(int[] a){
		int[] L=new int[a.length];
		for(int i=0;i<a.length;i++){
			L[i]=1;
			for(int j=0;j<i;j++){
				if(a[j]<a[i] && L[j]+1>L[i]){
					L[i]=L[j]+1;
				}
			}
		}
		int max=0;
		int index=0;
		for(int i=0;i<L.length;i++){
			if(L[i]>max){
				max=L[i];
				index=i;
			}
		}
		int length=max;
		System.out.println(max+":"+index);
		int[] r=new int[max];
		for(int i=index;i>=0;i--){
			if(L[i]==max){
				max=max-1;
				r[max]=a[i];
			}
		}
		int sum=0;
		for(int i=0;i<length;i++){
			System.out.println(r[i]+" ");
			sum += r[i];
		}
		
		System.out.println(sum);
	}

	
	
}
