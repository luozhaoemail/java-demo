package view.niuke.tt;

import java.util.*;

public class A {

	public static void main(String[] args) {
		  Scanner sc = new Scanner(System.in);
	      int m = sc.nextInt();
	      int n = sc.nextInt();
	      int[][] a =new int[m][n];
	      for(int i=0;i<m;i++)
	    	  for(int j=0;j<n;j++)
	    		  a[i][j]=sc.nextInt();
	      
//	      System.out.println("边界点集合个数："+solve(n,a));
	      int[][] maze = {{0,0,0,0,0},
		                  {0,1,0,0,0},
		                  {0,0,0,1,0},
		                  {1,1,0,0,0},
		                  {0,0,0,1,1}};
	      System.out.println(solve(maze));

	      
	      sc.close();
	}
	
	public static int solve(int[][] a)
    {
		int m = a.length;
		int n = a[0].length;	
		int count =0;
		
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			{
				if (a[i][j] == 1)
				{
                    for (int p = i+1; p < n; p++)
                    {
                        for (int q = j+1; q < m; q++) 
                        {
                            if (a[p][q] == 1 && a[p][j] == 1 && a[i][q] == 1)
                                count++;
                        }
                    }                    
				}
				
				/*if(a[i-1][j-1]==1)
					ball[i]++;
				
				
				(a[i-1][j-1]==1 || a[i][j]==1 || a[i][j+1]==1 ||
				   a[i][j-1]==1 || a[i][j]==1 || a[i][j+1]==1 ||
				   a[i+1][j-1]==1 || a[i+1][j]==1 || a[i+1][j+1]==1)*/
			}
		}
		return  count;
    }
	
	

}
