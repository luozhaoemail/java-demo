package view;

public class Search {

	public static void main(String[] args) {
		int a[] ={1,3,6,14,38,53,99};
		int index = fibonacciSearch(99,a);	
		System.out.println("a["+index+"]="+a[index]);

	}
	
	//针对有序序列
	public static int binarySearch(int key,int arr[])
	{
		int low = 0;
		int high= arr.length-1;
		int middle = 0;
		
		if(key<arr[low] || key>arr[high])
			return -1;
		
		while(low <= high)
		{
			middle =(low + high)/2;
			if(key < arr[middle])
				high = middle-1;
			else if(key > arr[middle])
				low = middle+1;
			else
				return middle;
		}
		return -1;
	}
	
	public static int sequenceSearch(int key,int arr[])
	{
		for(int i = 0; i < arr.length; i++) 
		{
	        if(arr[i] == key)
	            return i;
	    } 
	    return -1;//不存在的话返回-1
	}
	
	
	//斐波那契查找,黄金分割数列:F(1)=1，F(2)=1，F(n)=f(n-1)+F(n-2) （n>=2）
	//该数列越往后相邻的两个数的比值越趋向于黄金比例值（0.618）。
	//1、1、2、3、5、8、13、21
	public static int fibonacciSearch(int key,int arr[])
	{
		int i=0;
		while(getFibonacci(i)-1 ==arr.length)
			i++;
		
		int low = 0;
        int height = arr.length-1;
        while(low<=height){
            int mid = low + getFibonacci(i-1);
            if(arr[mid] == key)
            {
                return mid;
            }
            else if(key < arr[mid])
            {
                height = mid-1;
                i--;
            }
            else if(key > arr[mid])
            {
                low = mid+1;
                i = i-2;
            }
        }
        return -1;
				
	}	
	public static int getFibonacci(int n)// 得到第n个斐波那契数
	{
		int res = 0;
		if(n==0)
			res =0;
		else if(n==1)
			res =1;
		else
		{
			int first = 0;
			int second = 1;
			for(int i=2; i<n; i++)
			{
				res = first+second;
				first = second;
				second = res;					
			}
		}
		return res;
	}
}
