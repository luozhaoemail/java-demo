package view.acm;

import java.util.*;

/**a b 两个数组，长度都是n, 数组元素都介于[1-n]之间，且不重复，
现在要找一个最长子序列c, 其中任意的元素满足
在a中的顺序： c[j], c[i]
在b中的顺序： c[i], c[j]
也就是说子序列在a和b中的顺序刚刚相反
求c数组的长度

思路: 因为A中 X 在 Y 前面，那么B中肯定是反过来的。即是一个逆序。
那么可以直接把B反过来，求数组A和B的最长公共子序列LCS。
5
1 2 4 3 5
5 2 3 4 1

c={3,4}
 */
public class Main3 {

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
		{
			int n = sc.nextInt();
			int[] a= new int[n];
			int[] b= new int[n];			
			for(int i=0; i<n; i++)
				a[i] = sc.nextInt();
			for(int i=0; i<n; i++)
				b[i] = sc.nextInt();		
			
			reverse(b);
			lcs(a,b);
		}
		
	}
	
	public static void lcs(int[] a, int[] b)
	{
	    int len1 = a.length;
	    int len2 = b.length;
	    
	    int dp[][] = new int[len1+1][len2+1];
	    
	    for (int i = 1; i <= len1; i++)
	    {
	        for( int j = 1; j <= len2; j++) 
	        {
	           if(a[i-1] == b[j-1])	          
	                dp[i][j] = dp[i-1][j-1] + 1;	                
	           else 
	        	   dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);	            
	        }
	    }
//	    return dp[len1][len2];
	    
	    Stack<Integer> stack = new Stack<Integer>();
		int i = len1 - 1;
		int j = len2 - 1;
		
		while((i >= 0) && (j >= 0))
		{
			if(a[i] == b[j])//字符串从后开始遍历，如若相等，则存入栈中
			{
				stack.push(a[i]);
				i--;
				j--;
			}
			else
			{
				//如果字符串的字符不同，则在数组中找相同的字符，注意：数组的行列要比字符串中字符的个数大1，因此i和j要各加1
				if(dp[i+1][j] > dp[i][j+1])
					j--;
				else
					i--;
			}
		}
		
		while(!stack.isEmpty()){//打印输出栈正好是正向输出最大的公共子序列
			System.out.print(stack.pop());
		}	
		
		
	}

	
	
	 public static void reverse(int[] arr)
	 {
		int n = arr.length;
        for(int i=0; i<n/2; i++) 
        {
        	//方法2:
//          int tem = arr[i]; 
//          arr[i] = arr[n-1-i];//0~3
//          arr[n-1-i] = tem;//
        	
        	//方法2:
        	 arr[i] = arr[i] ^ arr[n-1-i];
        	 arr[n-1-i] = arr[i] ^ arr[n-1-i]; //a^b^b=a
        	 arr[i] = arr[i] ^ arr[n-1-i]; //a^b^a= b
        	 
        	 //方法3
//        	 arr[i] = arr[n-1-i] - arr[i]; //b-a
//        	 arr[n-1-i] =  arr[n-1-i] - arr[i]; //b-(b-a)=a
//        	 arr[i] = arr[n-1-i] + arr[i]; //a+(b-a)=b
        	
        }
        //n=4, 0 1 2 3
        //a[0]=a[3]
        //0+3=3   i+ (n-1-i) = n-1       
	 }
	
	
	
	
}
