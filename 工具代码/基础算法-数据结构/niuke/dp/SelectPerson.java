package view.niuke.dp;

import java.util.Scanner;

public class SelectPerson {
/**合唱团：题目要求n各学生中选择k个，使这k个学生的能力值乘积最大。
 * 有 n 个学生站成一排，每个学生有一个能力值，牛牛想从这 n 个学生中按照顺序选取 k 名学生，
 * 要求相邻两个学生的位置编号的差不超过 d，
 * 使得这k 个学生的能力值的乘积最大，你能返回最大的乘积吗？ 
 */
	public static void main(String[] args)
	{
		f1();

	}
	
	
	/**动态规划： 从n个元素中选择k个，使这k个元素的乘积最大
	 * 有 n 个学生站成一排，每个学生有一个能力值，牛牛想从这 n 个学生中按照顺序选取 k 名学生，
	 * 要求相邻两个学生的位置编号的差不超过 d，使得这 k 个学生的能力值的乘积最大，求最大的乘积
		3
		7 4 7
		2 50
		从n个学生中选择k个可以看成： 
		先从n个学生里选择最后1个，然后在剩下的里选择k-1个，并且让这1个和前k-1个满足约束条件。
	 */
	public static void f1()
	{
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		int[] a=new int[n+1];
		for(int i=1;i<=n;i++)
			a[i]=sc.nextInt();
		int k=sc.nextInt();
		int d=sc.nextInt();
						
		/**逆向地推：
		 * 满足关系：C<n,k>=C<n-1,k>+C<n-1,k-1>
		 * 假设现在已经选择完毕，从最后一个人向前逆推：
		 * m[i][j]表示当前选择第i个人时，前面已经选择了j个人，那么这j+1个人的最大乘积为m[i][j],
		 	i的范围[1,n], j的范围[1,k]
		 	递推关系:
		 	当前状态下，已经选择了j个人，面临着选不选i的问题：
		 		如果当前价值已经最优了，则不需要选第i个人(有可能是负数)，m[i][j]=m[i][j] 
		 		如果当前选择第i个人,则乘上它的价值a[i],即 m[i][j]= m[i-1][j-1]*a[i]
		 			因此  m[i][j]=Max{m[i][j],m[i-1][j-1]*a[i]}
		 			但是，如果为负的最小值，而第i个人的值a[i]也为负数，则m[i-1][j-1]*a[i]就变成正的最大了
		 		
		 		因此    m[i][j]=Max｛{m[i][j], Max{m[i-1][j-1]*a[i],h[i-1][j-1]*a[i}｝
		 		同样    h[i][j]=Max｛{h[i][j], Max{h[i-1][j-1]*a[i],m[i-1][j-1]*a[i}｝
		    初始状态：
		    m[i][0]第一列全0，当前选择第i人，前面已经选择了0人的乘积
		    m[0][j]当前选择第0人，前面已经选择了j人的乘积
		 */
		
		//1初始化 
		int[][] max = new int[n+1][k+1];
		int[][] min = new int[n+1][k+1];
		for(int i=1;i<=n;i++)
		{
			max[i][0]=0;
			min[i][0]=0;
		}		
		for(int j=1;j<=k;j++)
		{
			
			max[0][j]=a[j];//当前选择第0人，前面已经选择了j人的乘积
			min[0][j]=a[j];
		}
		
		long ans = Long.MIN_VALUE;
		 
		//2递推m[i][j]   i的范围[1,n], j的范围[1,k]
		for(int i=0; i<=n; i++)
		{
			for(int j=1;j<=k;j++)
			{
				//纳入限制条件: 编号差<=d，i-p<=d  --->  p>=i-d 即在这个范围内的都应该计算
				for(int p=Math.max(i-d,0); p<=i-1; p++)//d=50
				{
//					if(a[j]>=0)
//					{
//						max[i][j]=Math.max( max[i][j], Math.max( max[i-1][j-p]*a[i],min[i-1][j-p]*a[i]));
//						min[i][j]=Math.min( min[i][j], Math.min( min[i-1][j-p]*a[i],max[i-1][j-p]*a[i]));
//					}
//					else
//					{
//						max[i][j]=Math.max( max[i][j], Math.max( max[i-1][j-p]*a[i],min[i-1][j-p]*a[i]));
//						min[i][j]=Math.min( min[i][j], Math.min( min[i-1][j-p]*a[i],max[i-1][j-p]*a[i]));
//					}
					 if(j-p >= 0)
					 {
						 max[i][j]=Math.max( max[i][j], Math.max( max[i-1][j-p]*a[i],min[i-1][j-p]*a[i]));
						 min[i][j]=Math.min( min[i][j], Math.min( min[i-1][j-p]*a[i],max[i-1][j-p]*a[i]));
					 }
					
				}
			}
			ans = Math.max(ans, max[i][k]);//右下角最大
		}
		
		
		 
	    System.out.println("价值矩阵：");
	    for(int i=0;i<=n;i++)
	    {
	    	for(int j=0;j<=k;j++)
	    		System.out.print(max[i][j]+"\t");	   
            System.out.println();
        }
	    System.out.println("已选择人数=["+k+"], 最大价值为："+ans);//最右下角元素     
		
	    /*
	     3 7 4 7 2 50
	     1 29 1 13
36
7 -15 31 49 -44 35 44 -47 -23 15 -11 10 -21 10 -13 0 -20 -36 22 -13 -39 -39 -31 -13 -27 -43 -6 40 5 -47 35 -8 24 -31 -24 -1 
3 31
	     
	     */
	}
}
