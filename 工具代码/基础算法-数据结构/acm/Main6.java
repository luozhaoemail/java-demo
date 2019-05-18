package view.acm;

import java.util.*;

public class Main6 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] line= sc.nextLine().split(" ");
		int n =line.length;
		int[] a = new int[n];
		for(int i=0; i<n; i++)
			a[i] = Integer.parseInt(line[i]);		
	
		System.out.println(avg(a,n));
	}
	
	public static int avg(int a[], int n) {
		int sum = 0;
	    for (int i = 0; i < n; ++i)
	        sum += a[i];
//	    System.out.println(sum);
	    
	    int m =sum/2;
	    int[][] dp = new int[n+1][m+1];

	    for (int i = 1; i <= n; ++i) 
	    {
	        for (int j = 1; j <= m; ++j) 
	        {
	            if(j >= a[i-1])
	            	dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-a[i-1]] + a[i-1]);
	            else 
	            	dp[i][j] = dp[i-1][j];
	        }
	    }
	  
	    return sum - 2*dp[n][m];
	}

	

}
