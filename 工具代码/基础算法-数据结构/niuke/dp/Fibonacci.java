package view.niuke.dp;

public class Fibonacci {

	public static void main(String[] args) {
		long t1=System.currentTimeMillis();
		System.out.println(fb(30));
		long t2=System.currentTimeMillis();
		System.out.println("递归时间"+(t2-t1));//18ms		
		
		long t3=System.currentTimeMillis();
		System.out.println(dp(30));
		long t4=System.currentTimeMillis();
		System.out.println("动态规划时间"+(t4-t3));//0ms
		
	}
/**0, 1, 1, 2, 3, 5, 8, 13, 21, 24, 55, 
 * fn = fn-1+ fn-2
 * f2= 1
 * f1= 1
 * f0= 0
 */
	
	//递归解法: 自定向下
	public static int fb(int n)
	{
		if(n<=0)
			return 0;
		if(n==1)
			return 1;
		
		return fb(n-1)+fb(n-2);  //f2=f1+f0  f3=f2+f1;			
	}
	
	//动态规划：自底向上
	public static int dp(int n)
	{
		int[] a= new int[n+1];
		a[1]=1;
		a[2]=2;		
		
		for(int i=2; i<=n; i++)
			a[i]=a[i-1]+a[i-2];
		
		return a[n];
		
	}
	
}
