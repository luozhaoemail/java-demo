package Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortDemo {

	public static void main(String[] args) {
		//test();
		binSearch();
	}
	
	public static void test(){
		List<String> list = new ArrayList<String>();
		list.add("edf");
		list.add("accd");
		list.add("abdskhuj");
		list.add("qq");
		sop(list); 
		
		Collections.sort(list);//默认排序
		sop("默认排序 "+list); 
		sop("max= "+ Collections.max(list));
		
		Collections.sort(list,new MySort());
		sop("按长度排序"+list); 		
		sop("max2= "+ Collections.max(list,new MySort()));
		
	}
	
	public static void binSearch(){
		List<String> list = new ArrayList<String>();
		list.add("edf");
		list.add("accd");
		list.add("abdskhuj");
		list.add("qq");
		Collections.sort(list);
		sop(list);
		int index = Collections.binarySearch(list,"qqq");
		sop("index= "+index);
		/**
		 * 如果搜索键包含在列表中，则返回搜索键的索引；否则返回 (-(插入点) - 1)。
		 * 插入点是将该元素按有序方式插入到合适的位置
		 * qqq应该插入在qq后面，返回-4-1=-4   aaaa应插入在最前面，返回-0-1=-1
		 */
		
		int index2 = halfSearch(list,"aaaa");
		sop("index2= "+index2);
		
		Collections.sort(list,new MySort());
		sop(list);		
		int index3 = halfSearch2(list,"accd",new MySort());
		sop("index= "+index3);
		
		Collections.swap(list,1,2);//交换指定位置
		sop("swap: "+list);
		Collections.shuffle(list);//随机排序  例如在掷骰子的时候就会用到随机排序
		sop("shuffle: "+list);
		
	}
	//binarySearch的实现原理如下
	public static int halfSearch(List<String> list,String key){
		int max,min,mid;
		max = list.size()-1;
		min =0;
		while(min<=max){
			mid = (max+min)>>1; // 相当于除以2
			String str = list.get(mid);
			
			int num =str.compareTo(key);
			if(num>0)
				max= mid -1;
			else if(num<0)
				min = mid+1;
			else
				return mid;
		}
		return -min-1;
	}
	//当对象不具备比较性的时候
	public static int halfSearch2(List<String> list,String key,Comparator<String> cmp){
		int max,min,mid;
		max = list.size()-1;
		min =0;
		while(min<=max){
			mid = (max+min)>>1; // 相当于除以2
		
			String str = list.get(mid);			
			int num =cmp.compare(str, key);
		
			if(num>0)
				max= mid -1;
			else if(num<0)
				min = mid+1;
			else
				return mid;
		}
		return -min-1;
	}
	
	
	public static void sop(Object obj){
		System.out.println(obj);
	}

}

class MySort implements Comparator<String>{
	
	public int compare(String s1,String s2){
		if(s1.length()>s2.length())
			return 1;
		if(s1.length()<s2.length())
			return -1;
		return s1.compareTo(s2);
		
	}
}
