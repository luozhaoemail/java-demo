package view.niuke.num;

import java.util.*;

import view.ArrayUtil;

public class Main3 {
	public static  int ans=-1;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int k=0; k<t; k++)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			boolean [][] a = new boolean[n][n];
			int[] cnt = new int[n];
			int[] group = new int[n];
			int[] vis = new int[n];	
			
			for(int i=0; i<m; i++)
			{
				int x = sc.nextInt();
				int y = sc.nextInt();
				a[x-1][y-1]=true;				
			}
			
			
		    for(int i=n-1;i>0;i--)
		    {
		        vis[0]=i;
		        dfs(i,1,a,cnt,group,vis);
		        cnt[i]=ans;
		    }

		    boolean f= true;
		    for(int i=n-1;i>0;i--)
		    {
		       if(cnt[i]==1)
		    	   f=false;
		    }
		    if(f)
		    	System.out.println("Yes");
		    else
		    	System.out.println("No");
		}

	}
	
	public static int dfs(int u, int pos, boolean[][] a, int[] cnt ,int[] group,int[] vis) 
	{
		int n = a[0].length;		
		int i, j;
	    for( i = u+1; i < n; i++)//按递增顺序枚举顶点 
		{
	        if( cnt[i]+pos <= ans ) return 0;//剪枝 
	        if( a[u][i] ) 
			{
	            for( j = 0; j < pos; j++ ) if( !a[i][ vis[j] ] ) break; 
	            if( j == pos )
				{     // 若为空，则皆与 i 相邻，则此时将i加入到 最大团中 
	                vis[pos] = i;
	                if( dfs( i, pos+1, a,cnt,group,vis)>0 ) return 1;    
	            }    
	        }
	    }    
	    if( pos > ans )
		{
	            for( i = 0; i < pos; i++ )
	                group[i] = vis[i]; // 更新最大团元素 
	            ans = pos;
	            return 1;    
	    }    
	    return 0;
	}

}
