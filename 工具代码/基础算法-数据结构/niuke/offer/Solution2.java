package view.niuke.offer;

import java.util.*;

public class Solution2 {

	public static void main(String[] args) 
	{
//		f2(1);
//		f2(2);
//		f2(3);
//		f2(4);
		System.out.println(f4(2));
		
		
	}
	
	/**把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
	     输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 
	     例如数组{1,2,  3,4,5}旋转后为{3,4,5,  1,2},求该旋转数组最小值：{3,4,5,  1,2}   
	   1，原数组是递增的，旋转后的其实分为两部分，都是递增的，一部分大，一部分小
	            且前面部分比后面部分大。3,4,5 和 1,2
	   2.要求最小值1，用二分查找的思想，两个指针分别指向两部分
	   3.如果中间值大于后面指针，则说明中间值还在前面序列
	   			   小于后面指针，说明中间值在后面序列
	 
	 */
	public static void f1()
	{
		int[] a={1,0,1,1,1};
//		int[] a={3,4,5,1,2};
		int l =0;
		int r =a.length-1;
		int mid=0;
		while(a[l] >= a[r])
		{
			if((r-l)==1)
			{
				mid = r;
				break;
			}			
//			mid = (l+r)/2;//可能溢出
			mid = l+(r-l)/2;
			
			//也可以  a[mid]>=a[l] && a[l]>a[r]
			if(a[mid]>a[r]) //说明mid还在第一部分递增序列中，且在后半部分
				l = mid;//左指针后移
			
			if(a[mid]<=a[r]) //说明mid还在第二部分的递增序列中，且在前半部分
				r = mid;//右指针前移
			
		}
		
		System.out.println(a[mid]);		
	}
	
	
	/**一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
	 f0=0
	 f1=1
	 f2=2
	 f3=3
	 */
	public static void f2(int n)
	{
		int f1=1;
		int f2=2;
		int s = 0;
		if(n==1)
			s=1;
		if(n==2)
			s=2;
		for(int i=3;i<=n; i++)
		{
			s = f1+f2;
			f1= f2;
			f2= s;					
		}
		
		System.out.println(s);
	}
	
	/**一只青蛙一次可以跳上1级台阶，也可以跳上2级……
	 * 它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
		
	 */
	public static void f3(int n)
	{
		//每个台阶都有跳与不跳两种情况（除了最后一个台阶），最后一个台阶必须跳。
		//所以共用2^(n-1)种情况，左移做乘法得到2^(n-1)的结果。
		int s =  1<< --n;  //2^(n-1)  //把1左移了(n-1)位
		System.out.println(s);
		//= Math.pow(2, target - 1);
		
		//　value << num
		//num表示移动的位数, = value* 2^num
		int x =4;
		System.out.println((x<<1));//4左移1位  =4* 2^1=8
		System.out.println((1<<x));//1左移4位  =1* 2^4=16
				
	}
	
	
	/**用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
	   本质上还是斐波那契数列，
	   当n=1, f1=1
	   n=2, f2=2
	   n=3, f3=3
	 */
	public static int f4(int n)//动态规划
	{
		if(n<=0)
			return 0;
		else if(n==1)
			return 1;
		else if(n==2)
			return 2;
		else
        {
            int[] a= new int[n+1];
            a[1]=1;
            a[2]=2;
            
            for(int i=3; i<=n; i++)
			    a[i] = a[i-1]+a[i-2];
		
		    return a[n];	
        }
	}
	

}
