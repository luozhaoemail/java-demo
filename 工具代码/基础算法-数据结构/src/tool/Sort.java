package tool;

public class Sort {

	public static void main(String[] args) {
		int a[] ={52,-2,52,3,44,68,3,40,11};
		int b[] ={52,-2,52,3,44,68,3,40,11};
		int c[] ={52,-2,52,3,44,68,3,40,11};
		bubbleSort(a);
		selectSort(b);
		insertSort(c);
		ArrayUtil.print(a);
		ArrayUtil.print(b);
		ArrayUtil.print(c);
		
		/*	
		for(int i=0; i<a.length; i++)
			System.out.print(a[i]+" ");
		System.out.println();*/
	}
	
	
	
	//冒泡排序：发现逆序就向后交换，最大的数被交换到最后面
	public static void bubbleSort(int[] a)
	{
		int temp = 0;
		for(int i=0; i<a.length-1; i++)//n个数的数列总共扫描n-1次
		{
			for(int j=0; j<a.length-i-1; j++)//每趟结束最大的值在末尾去了，因此末尾不用比较了
			{
				if(a[j] > a[j+1])//前面比后面大就交换
				{
					temp = a[j];
					a[j] = a[j+1];					
					a[j+1] = temp;
				}
			}
		}
	} 
	
	//选择排序：每次选出最小的和前面的元素交换
	public static void selectSort(int[] a) 
	{
		int temp =0;
		
		for(int i=0; i<a.length; i++)
		{
			int k = i; //待确定的位置
			for(int j=i+1; j<a.length; j++)
			{
				if(a[j] < a[k])
					k = j;	//不断更新最小值，找到最小的a[j],位置记录到k中，即a[k]最小，应该交换			
			}
			temp =a[i]; 
			a[i] = a[k];
			a[k] = temp;
		}
	}
	
	//插入排序 O（n^2）
	public static void insertSort(int[] a)
	{
		int temp = 0;
		int j = 0;
		for(int i=0; i<a.length; i++)
		{
			temp = a[i]; //以当前i为分割，i前面的有序，i后面无序,将i+1个拿出插入到i前面去，插入到一个合适的位置
			for(j=i; j>0 && temp < a[j-1]; j--)//arr[i]< arr[i-1]>, a[i]小于前面的a[i-1]，应该继续向前找，找到一个合适的位置			{
				a[j] = a[j-1];//直到a[i]在序列中保持递增，然后把后面的数依次后移，为插入a[i]腾出位置
			
			a[j] =temp;
		}
	}

}
