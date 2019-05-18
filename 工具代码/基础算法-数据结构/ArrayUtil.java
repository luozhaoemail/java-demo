package view;

import java.util.*;

public class ArrayUtil {

	public static void main(String[] args) {
		int[] a={3,1,3,5,7,2,3,4,5};
//		int[] a=new int[1000000];
		print(a);
		long t1=System.currentTimeMillis();
		print(distinct1(a));//31ms
//		print(distinct2(a));//60ms
		long t2=System.currentTimeMillis();
		outln("时间="+(t2-t1));
		
		int b[]={2,1,3,2};
		print(intersect(a,b));
		print(union(a,b));
		print(sub(a,b));
	}

	//数组去重
	public static int[] distinct1(int a[])
	{		
		int count=0;
		for(int i=0;i<a.length;i++)	
		{	
			for(int j=i+1;j<a.length; j++)
			{
				if(a[i]==a[j])
				{
					count++; //记录重复元素的个数			
					break;					
				}
			}
		}			
		int[] b =new int[a.length-count];//新数组的长度
		int index=0;  //新数组的索引值
		
		for(int i=0;i<a.length;i++)	
		{
			int tmp =a[i];//旧数组中的元素
			boolean flag=false; //默认不是重复元素
			
			for(int j=0;j<b.length;j++) //拿着旧数组 的元素tmp 与新数组的每个元素比较一次。
			{
				if(b[j]==tmp)
				{
					flag=true;
					break;
				}
			}
			
			if(flag==false)
				b[index++]=tmp;
		}			
					
		return b;
	}
	
	public static int[] distinct2(int a[])
	{
		Set<Integer> set = new LinkedHashSet<Integer>();//顺序不变
		for(int i=0;i<a.length;i++)
		{
			set.add(a[i]);
		}
		int[] b =new int[set.size()];
		
		int index=0;
		Iterator<Integer> it = set.iterator();
		while(it.hasNext())
			b[index++]=it.next();
		
		return b;		
	}
	
	//数组打印
	public static <T> void print(T a[])
	{
		for(int i=0;i<a.length;i++)
			out(a[i]+" ");
		outln("");
	}
	public static void print(int a[])
	{
		for(int i=0;i<a.length;i++)
			out(a[i]+" ");
		outln("");
	}
	
	//简单打印
	public static void out(Object obj)
	{
		System.out.print(obj);
	}
	
	//简单打印
	public static void outln(Object obj)
	{
		System.out.println(obj);
	}
	
	
	//数组交集
	public static Integer[] intersect(int[] arr1, int[] arr2)
	{
        List<Integer> list = new LinkedList<Integer>();
        Set<Integer> common = new HashSet<Integer>();                  
        for(Integer i: arr1)
        {
            if(!list.contains(i))
                list.add(i);
        }
        for(Integer j: arr2)
        {
            if(list.contains(j))
                common.add(j);
        }
        Integer[] result={};
        return common.toArray(result);	   
	}
	//并集
	public static Integer[] union(int[] arr1, int[] arr2)
	{
		Set<Integer> set = new HashSet<Integer>();
		for(int str:arr1)
			set.add(str);
        
        for(int str:arr2)
        	set.add(str);
        
        Integer[] result={};
        return set.toArray(result);	  
	}
	
	//差集
	public static Integer[] sub(int[] arr1, int[] arr2)
	{
		List<Integer> list = new LinkedList<Integer>();
		for(Integer i: arr1)
        {
            if(!list.contains(i))
                list.add(i);
        }
        for(Integer j: arr2)
        {
            if(list.contains(j))
               list.remove(j);
        }
        Integer[] result={};
        return list.toArray(result);
	}
	
	
}
