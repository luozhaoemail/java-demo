package view;

import java.util.Scanner;

/*
 *  在n个元素的数组中，找到差值为k的数字对去重后的个数
 *   输入：     5 2
     		 1 5 3 4 2
         输出：3
         说明：(1，3），（5，3），（4，2）
 */
public class RangeArray {

	public static void main(String[] args) {
		solve();
	}
	
	//方法一：将输入的数字数列去重，全部加k，
	//生成一个新的list后，比较这里两个list的相同数的个数
	public static void solve()
	{
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        
        int[] arr = new int[n];
        for(int i = 0; i < n;i++)
        {
            arr[i] = in.nextInt();
        }
        ArrayUtil.outln("fun1= "+fun1(arr,k));
        ArrayUtil.outln("fun2= "+fun2(arr,k));
        in.close(); 
	}	
	public static int fun1(int[] a,int k)
	{
		int b[]=new int[a.length];
		for(int i = 0; i < a.length;i++)
		{
			b[i]=a[i]+k;
		}
		Integer[] x = ArrayUtil.intersect(a, b);
		ArrayUtil.print(x);		
		return x.length;
	}
	
	//方法2：将现有元素加上k,然后判断其在不在输入的数字列中即可。
	public static int fun2(int[] a,int k)
	{
		a = ArrayUtil.distinct1(a);//先去重
		int count=0;
		for(int i = 0; i < a.length;i++)
		{
			int tmp =a[i]+k;
			for(int j = 0; j < a.length;j++)
			{
				if(tmp==a[j])
				{
					ArrayUtil.out(tmp+" ");
					count++;
				}
			}
		}
		ArrayUtil.outln("");
		return count;
	}
}
