package view.niuke.dp;

import java.util.*;

import view.CombinationUtil;

public class DP_Main {

	public static void main(String[] args) {
		f3();

	}
	
	
	/**小Q的歌单
	 精妙之处就在于如何计算排列组合 C<m,n>
	利用数学归纳法：
	由C(n,k) = C(n-1,k) + C(n-1,k-1)
	刚好对应于杨辉三角：a[i][j]=a[i-1][j]+a[i-1][j-1];
	1
	1 1
	1 2 1
	1 3 3 1
	 * 
	 */
	public static void f2()
	{
		//大数相乘的时候，因为(a∗b)%c=((a%c)∗(b%c))%c，所以相乘时两边都对1000000007取模，再保存在int64里面不会溢出
		int mod=1000000007;//1000000007是一个最小的10位质数
		Scanner sc = new Scanner(System.in);
		int k=sc.nextInt();
		int A=sc.nextInt();
		int x=sc.nextInt();
		int B=sc.nextInt();
		int y=sc.nextInt();
		long[][] c=new long[101][101];
		c[0][0]=1;
		for(int i=1;i<=100;i++)
		{
			c[i][0]=1;
			for(int j=1;j<=100;j++)
				c[i][j]=(c[i-1][j-1]+c[i-1][j])%mod;//杨辉三角型，c[i][j]值为其头上两个数相加之和
		}
		long ans=0L;
		for(int i=0;i<=x;i++)
			if(i*A<=k && (k-i*A)%B==0 && (k-i*A)/B<=y)
				ans = (ans + (c[x][i]*c[y][(k-A*i)/B])%mod)%mod;
		
		System.out.println("ans="+ans);
		
	}
	public static void f2_2()//杨辉三角型：动态规划
	{
		int [][]a =new int[10][10];
//		for(int i=0;i<10;i++)
//			for(int j=0;j<10;j++)
//				if(j==0 || i==j) //将第一列和主对角线赋值1
//					a[i][j]=1;
		
		for(int i=0;i<10;i++)
		{
			a[i][0]=1;//将第一列和主对角线赋值1
			a[i][i]=1;
			for(int j=0;j<=i;j++)//对称矩阵，只存下三角形
				if(i>j && i>1 && j>0)
					a[i][j]=a[i-1][j]+a[i-1][j-1];//值为其上两数相加之和
		}
		
		//输出
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<=i;j++)
				System.out.print(a[i][j]+" ");
			System.out.println();
		}				
	}
	public static void f3()//
	{
		int mod=1000000007;//1000000007是一个最小的10位质数
		Scanner sc = new Scanner(System.in);
		int k=sc.nextInt();
		int A=sc.nextInt();
		int x=sc.nextInt();
		int B=sc.nextInt();
		int y=sc.nextInt();
		long ans=0L;
		
		//i*A+j*B=k ==> i*A<=k && (k-i*A)%B==0 && j=(k-i*A)/B  ==> C<x,i>*C<y,j> 
		for(int i=1;i<=x;i++)
		{
			int rightlen=k-i*A;
			if(rightlen>=0 && rightlen%B==0)
			{
				int j=rightlen/B;
				ans = CombinationUtil.combination(x,i)*CombinationUtil.combination(y,j);
			}
		}
		System.out.println("ans="+ans%mod);//取余数
	}
	
}
