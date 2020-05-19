package base;

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		fun1();

	}
	
	
	//动态规划：小Q歌单的种类
	public static void fun1()
	{
		int mod=1000000007;//1000000007是一个最小的10位质数
		Scanner sc = new Scanner(System.in);
		int k=sc.nextInt(); //歌单长度
		int A=sc.nextInt(); //第1种歌的长度
		int x=sc.nextInt(); //第1种歌的数量
		int B=sc.nextInt(); //第2种歌的长度
		int y=sc.nextInt(); //第2种歌的数量
		//从x首歌中拿出i首歌，从y首歌中拿出j首歌，满足关系:
		//i*A+j*B=k ==> i*A<=k && (k-i*A)%B==0 && j=(k-i*A)/B  ==> C<x,i>*C<y,j> 
		//dp[i][j]表示当选择或不选第i首A类歌时，此时歌单长度为j(j<k)，共用dp[i][j]种方法
				
		int m = x+y;
		int[] p =new int[m+1];
		
		for(int i=1; i<=x; i++)
			p[i]=A;
		for(int j=x+1; j<=m; j++)
			p[j]=B;
		
		int[][] dp = new int[m+1][k+1];
		dp[0][0]=1;
		
		for(int i=1; i<=m; i++)//A类歌拿了 i首
		{			
			for(int j = 0; j<=k; j++)//歌单长度为 j
			{
				if(j-p[i]>=0) //背包容量j<k，且能够容纳B类歌： 选B和不选B就是2种方案
					dp[i][j] = (dp[i-1][j] %mod + dp[i-1][j-p[i]] %mod )%mod;   
				
				else//歌单长度不能容纳B类歌
					dp[i][j] = dp[i-1][j] %mod; //不选B
			}			
		}
		
		System.out.println(dp[m][k]%mod);
	}

}
