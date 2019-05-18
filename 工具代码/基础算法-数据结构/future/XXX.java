package view.future;

import java.util.*;

public class XXX {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
		{					
			String[] str = sc.nextLine().split(" ");
			
			int n = str.length;
			int[] b = new int[n];
			for(int i=0;i<n;i++)
			{
				b[i] = Integer.parseInt(str[i]);
			}
					
			solution(b);	        
	        
		}//
	}
	
	private static void solution(int[] a) 
	{
		int sum=0;
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
        for(int i=0;i<rst.length;i++)    	
    		sum += Integer.parseInt(rst[i]);
        
        System.out.println(sum);
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
	

}
