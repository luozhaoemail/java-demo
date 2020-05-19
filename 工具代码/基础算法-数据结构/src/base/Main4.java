package base;

import java.util.Scanner;

public class Main4 {

	public static void main(String[] args) {
//		Scanner sc =new Scanner(System.in);
//		int x=sc.nextInt();
		System.out.println(dp2(50));

	}
	
	public static void dp(int n)
	{
		int[] a= new int[n+1];
		a[1]=1;
		a[2]=2;		
		
		for(int i=3; i<=n; i++)
			a[i]=a[i-1]+a[i-2];
		
		System.out.println(a[n]);
		
	}
	
	
	public static long dp2(int n){
		if(n<=0)
			 return 0;
		if(n==1) 
			 return 1;
		if(n==2) 
			 return 2;
		
		long a =1;
		long b =2;
		long result = 0L;
		for(int i=3; i<=n; i++){
			result = a+b;
			a = b;
			b = result;
		}
		
		return result;
		
	}

}
