package view;
import java.util.Arrays;
import java.util.Scanner;
/**
	给一个数组{6,2,1},将其所有子集按照规则进行计算：集合中最小数*最大数
	最后求出值最大的子集合
	[6] = 6 * 6 = 36;	最小值和最大值都是6
	[2] = 2 * 2 = 4;
	[1] = 1 * 1 = 1;
	
	[6,2] = 2 * 8 = 16;	最小值是2，最大值是8=2+6
	[2,1] = 1 * 3 = 3;

	[6, 2, 1] = 1 * 9 = 9;	
	因此[6]的值为36，最大。
 */
public class MaxAera {

	public static void main(String[] args) 
	{
		solve();  
	}
	
	public static void solve()
	{
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n;i++)
        {
            arr[i] = in.nextInt();
        }
        System.out.println(solve2(arr));
        in.close(); 
	}
	//枚举出所有的集合
	public static int solve(int[] arr) 
	{
		int n = arr.length;
		int max = Integer.MIN_VALUE;
		
		for(int i=1; i<=n; i++)//取第i个数求子集 ，遍历次数1 2 3，作为间隔
		{
			String str="";
			
			for(int j=0; j<=n-i; j++)//每个子集内部, j循环次数j<=2[0 1 2]  j<=1[0 1]  j<=0[0]
			{
				str +="[";
				
				int min= Integer.MAX_VALUE;
				int sum=0;
				for(int k=j; k<=j+i-1; k++)//求最大值[6 2]  //依次获取从第j个索引位置的数据直到第j+i-1个位置的数据
				{
					min = min < arr[k] ? min : arr[k]; //2
					sum +=arr[k];//6+2=8
					
					str +=arr[k]+" ";					
				}
				max = max > min*sum ? max : min*sum;//2*8=16			

				str +="]="+min+"*"+sum+"="+min*sum+"\n";
			}
			System.out.println(str);
		}
       
        return max;
	}
	
	
	public static int solve2(int[] arr)
	{
		String str="[";
		
		int n = arr.length;
		Arrays.sort(arr);//先排序，递增
		int sum=0;
		for(int i=0;i<n;i++)//求最大子集的和，区间为：[0,n-1]
		{
			sum+=arr[i];
		
			str +=arr[i]+" ";	
		}
		int max =arr[0]*sum;//求最大子集的值
	
		str +="]="+arr[0]+"*"+sum+"="+max;
		System.out.println(str);
		
		
		for(int i=0;i<n-1;i++)
		{
			sum -= arr[i];//依次踢出最小的元素 
			max = Math.max(max, arr[i+1]*sum);//区间为：[1,n-1]
			
			//输出
			str="[";
			for(int j=i+1;j<n;j++)//踢出前面的元素 
				str +=arr[j]+" ";
			str +="]="+arr[i+1]+"*"+sum+"="+max;
			System.out.println(str);
		}
		
		
		return max;
	}

}
