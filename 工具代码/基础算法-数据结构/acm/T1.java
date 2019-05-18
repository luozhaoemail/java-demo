package view.acm;

import java.util.*;

public class T1 {

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
		{
			int x =sc.nextInt();
			for(int t=1;t<=x; t++)
			{
				int n = sc.nextInt();
				int[] a= new int[n];
				for(int i=0; i<n; i++)
					a[i]=sc.nextInt();
				
				fun(t,a);
				
			}			
			
		}
		
	}
	
	public static void fun(int t, int[] a)
	{
		int n = a.length;
		int[][] A = new int[n][n];
		int[][] B = new int[n][n];
		for(int i=0;i<n;i++)
		{
			A[i][i] = a[i];			
			for(int j=i-1; j>=0; j--)
			{
				A[i][j] = Math.max(a[i]+A[i+1][j], a[j]+A[i][j-1]);
				B[i][j] = Math.min(A[i+1][j], B[i][j-1]);
			}
		}	
		int min =Math.min(A[0][n-1], B[0][n-1]);
		int max =Math.max(A[0][n-1], B[0][n-1]);
				
		System.out.println("Case #"+t+": "+min+" "+max);
	}
	
	/**
2 
6
4 7 2 9 5 2
10
140 649 340 982 105 86 56 610 340 879
	 */

}
