package view.acm;

import java.util.*;
/**
一个坐标轴内有很多点(x,y), 用一个面积最小的正方形框把所有的点圈起来，求这这个正方形的面积

就是分别求出 x和y轴坐标的最大距离    -x,x  -y,y
2
0 0
2 2

3
-2 3
1 4
3 3

 */
public class Main {
	
	public static void main(String[] args)
	{		
//		solution1();
		solution2();	
	}
	
	
	public static void solution1()
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
		{
			int n = sc.nextInt();
			int[][] a= new int[n][2];
			for(int i=0;i<n;i++)  //a.length
				for(int j=0;j<2; j++)	//a[0].length			
					a[i][j] = sc.nextInt();
		
			int maxX = -999,maxY = -999;
			int minX = 999,minY = 999;

			for(int i = 0;i < n;i++)
			{
				if(a[i][0] < minX)//找到	最小的-x
					minX = a[i][0];			
				if(a[i][0] > maxX) //找到最大的x
					maxX = a[i][0];
				
				
				if(a[i][1] < minY)//找到	最小的-y
					minY = a[i][1];				
				if(a[i][1] > maxY)//找到最大的y
					maxY = a[i][1];				
			}
			
			int subX = Math.abs(maxX - minX);
			int subY = Math.abs(maxY - minY); 
			int res = Math.max(subX, subY);
			System.out.println(res*res);

			
		}//while
		
	}
	
	//	方法2：分别将x,y坐标轴排序，再取最大和最小的距离查，作为正方形的边长
	public static void solution2()
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
		{
			int n = sc.nextInt();
			int[] a= new int[n];
			int[] b= new int[n];
			for(int i=0;i<n;i++)
			{
				a[i] = sc.nextInt();
				b[i] = sc.nextInt();
			}
			
			bubbleSort(a);
			bubbleSort(b);
			
			int l = Math.abs(a[n-1] - a[0]);
			int w = Math.abs(b[n-1] - b[0]);
			int d = l>w ? l: w;
			System.out.println(d*d);
			
		}
	}
	
	//冒泡排序
	public static void bubbleSort(int[] arr)
	{
		int temp = 0;
		for(int i=0; i<arr.length; i++)
		{
			for(int j=0; j<arr.length-i-1; j++)
			{
				if(arr[j] > arr[j+1])//前面比后面大就交换
				{
					temp =arr[j];
					arr[j] =arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	} 
	
}
