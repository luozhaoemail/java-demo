package view;

import java.util.*;

/**
定义两个字符串变量：s和m，再定义两种操作 
第一种操作： 
　　m = s； 
　　s = s + s； 
第二种操作： 
　　s = s + m； 

假设s，m初始化如下： 
　　s = "a"； 
　　m = s； 
求最小的操作步骤数，可以将s拼接到长度等于n。

输入描述： 
一个整数n，表明我们需要得到s字符串长度 
 */
public class RecurDemo2 {
	public static int n;
	public static int res;
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 n = sc.nextInt();
		 res= n-1;

		 String s = "a";
         String m = s;
         dfs(s,m,0);
         ArrayUtil.outln(res);
         
         ArrayUtil.outln(decom(n));

	}
	
	//方法1:采用dfs算法遍历每一种拼接到长度等于n的可能，输出最小的步骤数。
	//每次操作都是同样的两手选择，这种重复性可以用递归去模拟。
	//把所有能让s拼接到长度为n的操作都模拟出来，维护一个全局变量，每次模拟出就取最小值即可。
	//长度为n是一种递归结束条件，另一种递归结束条件是长度大于n，因为之后怎么操作也会大于n。
	public static void dfs(String s, String m,int step)
	{
		if(s.length()>n) 
			return;
		if(s.length()==n)
		{
			res = Math.min(res, step);//深度优先递归出最小的res值
			ArrayUtil.outln("递归res, step： "+res+" "+step);
			
		}
		// 第一种操作
	    String newm = s;//a-->aa
	    String news1 = s + s;//aa-->aaaa
	    dfs(news1, newm, step+1);
	    // 第二种操作 
	    String news2 = s + m;
	    dfs(news2, m, step+1);
	}

	//方法2：第一种操作每次都将s复制给m,再把s翻倍。比如s=aa,那么操作之后：m=aa,s=aaaa
	//由此得出经过第一种操作s的长度始终是偶数(2x)
	//第二种操作则是单纯的加上s+m,前提是m的值(也就是操作之前s复制给他的值)，如果为奇数，就是相加
	//比如初始s=a,m=a, 则s=aa，如果初始s=aa,m=aa,s=aaaa，因此经过了第一种操作（修改m），长度始终会变成偶数
	//如果只有操作第二种，则s=a,m=a ,m不会被修改，每次都是s+m ,即 a aa aaa aaaa aaaaa
	/**	转换为：
	 * 求解最少次数拼接到长度为n，则需求m的最大值长度，最后的结果一定是m的倍数。
	 * 因此分为两种情况：当n是质数，无法进行因数分解，只能进行第二种操作，n-1次。
	 * 当n不是质数，假设n=a*b*c，可分为多个因数，则m=其中的某几个因素积，如果a<b<c，m=b*c,n=a+b*c=(a-1)+b*c
	 * 在不b*c递归分解=b+c=(b-1)+(c-1),所以总的n=(a-1)+(b-1)+(c-1)
	 */
	public static int decom(int n)
	{
		System.out.print(n+"=");
		List<Integer> list=new ArrayList<Integer>();
		for(int i=2; i<n; i++)
		{
			while(i!=n)
			{
				if(n%i == 0)
				{
					list.add(i);
					n=n/i;
					System.out.print(i+"*");
				}
				else 
					break;
			}
		}//for
		list.add(n);
		System.out.println(n);
		
		int res=0;
		for(int i: list)//自动拆箱Integer->int
			res += i-1;
		return res;
	}
}
