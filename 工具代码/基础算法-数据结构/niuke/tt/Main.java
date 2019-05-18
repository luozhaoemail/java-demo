package view.niuke.tt;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		  Scanner sc = new Scanner(System.in);
	      int n = sc.nextInt();
	      int[] a =new int[n];
	      int[] b =new int[n];
	      for(int i=0;i<n;i++)
	    	  a[i]=sc.nextInt();
    	  for(int j=0;j<n;j++)
    		  b[j]=sc.nextInt();
	      
    	  bubbleSort(a);
//    	  print(a);
    	  bubbleSort(b);
//    	  print(b);

    	  
    	  int num=0;
    	  System.out.println(solve(a,b));
	    

	      
	      sc.close();
	}
	
	public static int solve(int[] a,int[] b)
    {
		int x =0;
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a.length;j++)
			{
				if(i<j)
				{
					if(a[j]>b[i])
						x++;
				}
				else
				{
					if(a[i]>=b[j])
						x++;
				}
			}
		}		
		return  x;
    }
	
	public static void print(int a[])
	{
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println("");
	}
	
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
