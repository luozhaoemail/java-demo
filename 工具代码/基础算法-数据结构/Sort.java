package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;


public class Sort {

	public static void main(String[] args) throws Exception {
		System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024+"MB");
		//1g==981.5MB		512m==491.0MB
		
		/*int a[] ={52,52,2,44,68,3,3,11};
		bubbleSort(a);
		for(int i=0; i<a.length; i++)
			System.out.print(a[i]+" ");
		System.out.println();*/
		
/*		int[] arr=new int[10000000];
        for(int i=0;i<arr.length;i++)
        {	//随机产生数据
            arr[i]=(int)(Math.random()*1000+1);
        }*/
        long startTime = System.currentTimeMillis();    //获取开始时间
        
//      findDup_jdk(arr);//0.045s
        List<Long> list = read();
        BitMapSort(list);
        write(list);
        
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime)/1000.0 + "s");
		
		
		/*int[] arr=new int[10000000];
        for(int i=0;i<arr.length;i++)
        {	//随机产生数据
            arr[i]=(int)(Math.random()*1000+1);
        }
        long startTime = System.currentTimeMillis();    //获取开始时间
        	Qsortplus(arr,0,arr.length-1); //1亿条= 5.679s	1千万条=0.665s
        
//          Quicksort(arr,0,arr.length-1);//1亿条= StackOverflowError 	1千万条=	52.853s
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime)/1000.0 + "s");*/
				
		/* List<Integer> l = new ArrayList<>();
		 for(int i=0;i<10000000;i++)
			l.add((int)(Math.random()*1000+1));

		 long startTime = System.currentTimeMillis();    //获取开始时间
		
//			System.out.println(l);
//			quickSortByList(l,0,l.size()-1);//1亿条=OOM  1千万条=4.89s
//		 	quickSortByList2(l,0,l.size()-1);//1亿条=OOM  1千万条=StackOverflowError
//			Collections.sort(l);//1亿条=	OOM 	1千万条=1.866s
//			System.out.println(l);		 
		 
		 long endTime = System.currentTimeMillis();    //获取结束时间
	     System.out.println("程序运行时间：" + (endTime - startTime)/1000.0 + "s");*/
		
	}
	
	//位图法
    public static <T> void BitMapSort(List<T> list) 
    {
    	int n = list.size();
    	if(n<=0)
    		return;
    	
    	long min = 400000000000000L;
    	BitSet bm = new BitSet(n);
    	for (int i=0; i<n; i++)
    	{	
    		int x = (int)((long)list.get(i)-min);
    		bm.set(x, true);
    	}
    	
    	int count = 0;
    	for(int j = 0; j < bm.length(); j++) 
        {
            if (bm.get(j)) 
            {
                System.out.print(j + min + " ");
                count++;
            }
        }  
    	System.out.println();
        System.out.println("排序后的数组大小为：" + count );
    	
    }
	
	//位图法
    public static void findDup_jdk(int[] array) 
    {
    	int ARRNUM = array.length;
    	int max=9999;
    	int min=0;
    	int n = max-min+1;
        
    	System.out.println("*******调用JDK中的库方法--开始********");
        BitSet bitArray = new BitSet(n);       
        for (int i = 0; i < ARRNUM; i++)
        {
            bitArray.set(array[i] - min);
        }
        int count = 0;
        for (int j = 0; j < bitArray.length(); j++) 
        {
            if (bitArray.get(j)) 
            {
                System.out.print(j + min + " ");
                count++;
            }
        }
        System.out.println();
        System.out.println("排序后的数组大小为：" + count );
        System.out.println("*******调用JDK中的库方法--结束********");
    }

	
	//千万级快排
	public static void Quicksort(int [] arr,int left,int right)
    {
        if(arr.length<=1 || arr==null)
            return;
        
        if(left<right)
        {
            int mid=getmid(arr,left,right);
            Quicksort(arr,left,mid-1);
            Quicksort(arr,mid+1,right);
        }

    }
	public static int getmid(int [] arr,int left,int right)
    {
        int temp=arr[left];
        while (left<right)
        {
            //从右往左
            //最左边小于第一个数
            while (left<right && arr[right]>=temp)
            {
                right--;
            }
            //将最右边比基准数小的数移到左边
            arr[left]=arr[right];
            //从左往右
            //最右边大于第一个数
            while (left<right &&arr[left]<temp)
            {
                left++;
            }
            //将最左边比基准数大的移到右边
            arr[right]=arr[left];

        }
        arr[left]=temp;//基准元素归位
        return left;
    }

	//亿级 快排
	//如果出现大量重复数据，在左右移动过程中就会浪费不少次数，所以要再加一个变量限制。
	public  static  void Qsortplus(int [] arr, int low,int high)
    {
        if(low < high)
        {
            int lt=low;            
            int gt=high;//左边，右边
            
            int i=low+1;//开始循环位置
            int temp=arr[low];//保存第一个数据
            while(i<=gt)//循环夹逼
            {
                if(arr[i]<temp) //小于
                {
                    swap(arr, lt,i);//移动
                    lt++;
                    i++;
                }
                else  if(arr[i]>temp)//大于
                {
                    swap(arr,i,gt);//移动
                    gt--;
                }
                else
                {
                    i++;
                }
            }
            Qsortplus(arr, low, lt-1); 
            Qsortplus(arr, gt+1, high);//分段
        }

    }
	//数据交换
	 public  static  void swap(int [] a, int low,int high)
     {
         int temp=a[low];
         a[low]=a[high];
         a[high]=temp;
		 
		/* a[low] = a[low]^a[high];
		 a[high] = a[low]^a[high];
		 a[low] = a[low]^a[high];*/
     }
	 
	 public  static  void swap(List<Integer> list, int low,int high)
     {
		  int temp = list.get(low);
		  list.set(low, list.get(high));
		  list.set(high,temp);
     }	 
	 
	 public static void quickSortByList2(List<Integer> list, int lo0, int hi0) 
	 {
		 int lo = lo0; 
		 int hi = hi0; 
		 if(lo >= hi)
			 return; 
		 //确定指针方向的逻辑变量 
		 boolean transfer=true; 
		 while (lo != hi)
		 {
			 if(list.get(lo) > list.get(hi))
			 {
				 swap(list, lo,hi);//移动
				//决定下标移动，还是上标移动 
				transfer = (transfer == true) ? false : true;
			 }
			 
			 if(transfer) 
				 hi--;
			 else
				 lo++;
		 }
		 
		 lo--; 
		 hi++;
		 quickSortByList2(list, lo0, lo); 
		 quickSortByList2(list, hi, hi0); 
		 
	 }
	 
	 
	 //list快排---千万级
	 public static void quickSortByList(List<Integer> list, int low, int high) 
	 {
		 if(low < high)
	        {			   
	            int lt=low; //左边           
	            int gt=high; //右边
	            
	            int i=low;//开始循环位置
	            int temp=list.get(i);//保存第一个数据
	            
	            while(i<=gt)//循环夹逼
	            {
	                if(list.get(i)<temp) //小于
	                {
	                    swap(list, lt,i);//移动
	                    lt++;
	                    i++;
	                }
	                else  if(list.get(i)>temp)//大于
	                {
	                    swap(list,i,gt);//移动
	                    gt--;
	                }
	                else
	                {
	                    i++;
	                }
	            }
	            quickSortByList(list, low, lt-1); 
	            quickSortByList(list, gt+1, high);//分段
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
	
	//选择排序
	public static void selectSort(int[] arr) 
	{
		int temp =0;
		
		for(int i=0; i<arr.length; i++)
		{
			int k = i; //待确定的位置
			for(int j=i+1; j<arr.length; j++)
			{
				if(arr[j] < arr[k])
					k = j;	//选择出本应该在第i个位置的数为j,记录到k中			
			}
			temp =arr[i]; 
			arr[i] = arr[k];
			arr[k] = temp;
		}
	}
	
	//插入排序 O（n^2）
	public static void insertSort(int[] arr)
	{
		int temp = 0;
		int j = 0;
		for(int i=0; i<arr.length; i++)
		{
			temp = arr[i];
			for(j=i; j>0 && arr[j-1]>temp; j--)//arr[i-1]>arr[i]，前面的大，应该后移
			{
				arr[j] = arr[j-1];
			}
			arr[j] =temp;
		}
	}
	
	
	
	
	public static List<Long> read() throws Exception
	{
		List<Long> list = new ArrayList<>();
		System.out.println("读入数据");
		BufferedReader br = new BufferedReader(new FileReader("E:/研二/实验/2 改进大表关联算法/实验/joinkey/0.txt"));
		String str = "";
		while((str = br.readLine()) != null)
        { 
			list.add(Long.valueOf(str));
        }
		
		System.out.println("lista.size= "+list.size());
		br.close();
		return list;
	}
	
	public static void write(List<Long> list) throws Exception
	{
		System.out.println("写入结果");
		System.out.println("list.size= "+list.size());
		BufferedWriter bw = new BufferedWriter(new FileWriter("E:/研二/实验/2 改进大表关联算法/实验/外排.txt"));
		for(int i=0; i<list.size(); i++)
		{
			bw.write(list.get(i)+"");
			bw.newLine();
		}		
		bw.flush();
		bw.close();
	}
	
	

}
