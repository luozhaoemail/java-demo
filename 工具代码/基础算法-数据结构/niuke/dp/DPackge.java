package view.niuke.dp;

public class DPackge {
	/**
		动态规划算法--01背包问题 
		获得的最大价值,待求s？	
	 */
	public static void main(String[] args) 
	{
//		int[] weight={2,1,3,2};//物品的重量
//	    int[] value={12,10,20,15};//物品的价值
		int[] weight={4, 6, 2, 2, 5, 1};
	    int[] value={8, 10, 6, 3, 7, 2};
	    int n=value.length;//物品个数
	    int c=12;//背包最大容量
	    
	    int[] w = new int[n+1];		 
		int[] v = new int[n+1];
	    for (int i=1; i<n+1; i++)
		{
			w[i] = weight[i-1];
			v[i] = value[i-1];
		}
	    
	    int[][] m=new int[n+1][c+1];
	    //m[0][j]第一行全为0
	    //m[i][0]第一列全为0
		 
		//1 求出所有的最优子结构，即所有的可能，但是并不清楚具体选择哪几样物品能获得最大价值。
		for(int i=1;i<n+1;i++)
	    {
			for(int j=1;j<c+1;j++)
	    	{	    		
	    		if(j>=w[i]) //第i个物品在背包中
	    			m[i][j]=Math.max(m[i-1][j], m[i-1][j-w[i]]+v[i]);
	    		
	    		else//第i个物品不在背包中
	    			m[i][j]=m[i-1][j];
	    	}
	    } 
	    
	    System.out.println("价值矩阵：");
	    for(int i=0;i<n+1;i++)
	    {
	    	for(int j=0;j<c+1;j++)
	    		System.out.print(m[i][j]+"\t");	   
            System.out.println();
        }
	    System.out.println("背包中重量=["+c+"], 最大价值为："+m[n][c]);//最右下角元素     
	    
	    
	    /**但是我们并不清楚具体选择哪几样物品能获得最大价值。
		     另起一个 x[ ] 数组，x[i]=0表示不拿，x[i]=1表示拿。
		   m[n][c]为最优值，如果m[n][c]=m[n-1][c] ,说明有没有第n件物品都一样，则x[n]=0 ; 否则 x[n]=1。
		    
		      当x[n]=0时，由x[n-1][c]继续构造最优解；
		      当x[n]=1时，则由x[n-1][c-w[i]]继续构造最优解。
		      以此类推，可构造出所有的最优解。  
	     * 
	     */
	    int[] x = new int[n+1];
	    for(int i=n;i>=1;i--)
	    {
	        if(m[i][c]==m[i-1][c])//有没有第i件物品背包容量c都一样
	            x[i]=0;//则肯定不是它
	        else
	        {
	            x[i] = 1;
	            c= c-w[i];//减去当前物品
	        }
	    }
	    System.out.println("背包中的物品为：");
	    for(int i=1;i<=n;i++)
	    {
	    	if(x[i]==1)
	    		System.out.println("物品"+i+" ,重量=["+w[i]+"],价值=["+v[i]+"]");	    	
	    }
	    
	    
	}
	


}
