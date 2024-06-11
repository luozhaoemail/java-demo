package view;

import java.io.*;
import java.util.*;

public class SortTest {

	public static void main(String[] args) throws Exception
	{
		String tb_imsi="E:/研二/实验/2 改进算法/data/s4.txt";
		long start = System.currentTimeMillis();
		List<String> list = read();
		String[] ar = list.toArray(new String[list.size()]);
		
//		Collections.sort(list);//list默认 0.245 秒
//		Qsortplus(ar,0,ar.length-1);//快排0.206 秒		
//		Heap.heapSort(ar);//堆排0.232 秒
		RadixSort.radixSort(ar,15);
		
		System.out.println("排序耗时：");
		countTime(start);//
		
		write(tb_imsi,ar);
	}
	
	//排序算法		
	public static void Qsortplus(Long[] arr, int low, int high)
    {
        if(low < high)
        {
            int lt=low;            
            int gt=high;//左边，右边
            
            int i=low+1;//开始循环位置
            long temp=arr[low];//保存第一个数据
            
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
	public  static  void swap(Long [] a, int low,int high)
    {
		Long temp = a[low];
        a[low] = a[high];
        a[high] = temp;
    }
	
	public static void write(String path, String[] a) throws Exception
	{
		long start = System.currentTimeMillis();	
		BufferedWriter bw = new BufferedWriter(new FileWriter(path));
		for(int i=0; i<a.length; i++)
		{
			bw.write(a[i]);
			bw.newLine();
		}		
		bw.flush();
		bw.close();
		countTime(start);
	}
	public static List<String> read() throws Exception
	{
		long start = System.currentTimeMillis();
		String tb_imsi="E:/研二/实验/2 改进算法/data/imsi.txt";
		List<String> list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(tb_imsi));
		String str = "";
		while((str = br.readLine()) != null)
        { 			
//			long imsi = Long.parseLong(str);
			list.add(str);
		}
		br.close();		
		countTime(start);//
		System.out.println(list.size());
		return list;
	}
	public static void change() throws Exception
	{
		long start = System.currentTimeMillis();
		String tb_im="E:/研二/实验/2 改进算法/data/im.txt";
		String tb_imsi="E:/研二/实验/2 改进算法/data/imsi.txt";
		List<Long> list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(tb_im));
		String str = "";
		while((str = br.readLine()) != null)
        { 			
			long imsi = Long.parseLong(str.split("\\,")[3]);
			list.add(imsi);
		}
		br.close();		
		countTime(start);//0.682 秒
		System.out.println(list.size());
		
		start = System.currentTimeMillis();
		BufferedWriter bw = new BufferedWriter(new FileWriter(tb_imsi));
		for(int i=0; i<list.size(); i++)
		{
			bw.write(list.get(i).toString());
			bw.newLine();
		}		
		bw.flush();
		bw.close();
		countTime(start);// 0.183 秒
	}
	//long start = System.currentTimeMillis();
	//countTime(start);
	public static void countTime(long start) {
		System.out.println(": " + (System.currentTimeMillis() - start)/1000.0 + " 秒");
	}
}
