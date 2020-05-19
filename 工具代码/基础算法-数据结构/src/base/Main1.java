package base;

import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		fun2();

	}
	
	
	//计算(x^y)%N
	public static void fun1()
	{
		Scanner sc =new Scanner(System.in);
		int x=sc.nextInt();
		int y=sc.nextInt();
		int n=sc.nextInt();
		
		int z = (int)Math.pow(x, y);//x^y
		z = z % n;
		
		System.out.println(z);		
		
	}
	
	//给定整数数组，除了某个数，其他的数都出现了两次。找出这个数
	//异或：相同为0，不同为1
	//相邻的数两两异，每次不同的位保留，相同的位为0
	// 0111 ^ 0001 = 0110  表示把相同的位置为0
	//多轮下来则遇到相同的数异或=0,最终剩下的就是不同的那个数
	public static void fun2()
	{
		int[] a= {7,1,2,2,9,4,7,1,4,3,9};
		
		int x = a[0];
		
		for(int i=1;i<a.length; i++)
			x = x^ a[i];
		
		System.out.println(x);	
	}

}
