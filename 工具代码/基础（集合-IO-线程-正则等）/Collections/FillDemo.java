package Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class FillDemo {

	public static void main(String[] args) {
		
		//fillDemo();
		reVerse();
	}

	public static void fillDemo(){
		List<String> list = new ArrayList<String>();
		list.add("edf");
		list.add("accd");
		list.add("abdskhuj");
		list.add("qq");
		
		sop(list);
		//Collections.fill(list,"pp");//使用指定元素替换指定列表中的全部元素。
		Collections.replaceAll(list, "qq", "xxxx");//替换指定元素
		Collections.reverse(list);//翻转
		sop(list);
		
	}
	
	public static void reVerse(){
		
		//TreeSet<String> ts =new TreeSet<String>(new ComStr());//按长度升序
		//TreeSet<String> ts =new TreeSet<String>(Collections.reverseOrder(new ComStr()));//按长度降序
		TreeSet<String> ts = new TreeSet<String>(Collections.reverseOrder());//默认按字母降序
		ts.add("abcd");
		ts.add("shh");
		ts.add("dsvuiq");
		ts.add("z"); 
		
		Iterator it = ts.iterator();
		while(it.hasNext()){
			sop(it.next());			
		}
		
				
	}
	
	public static void sop(Object obj){
		System.out.println(obj);
	}
}

class ComStr implements Comparator<String>  //自定义排序规则，按照字符串长度排序
{	
	public int compare(String s1, String s2) {		
		
		int num = new Integer(s1.length()).compareTo(new Integer(s2.length()));
		if(num == 0)
			return s1.compareTo(s2);
		return num;
	}
	
}
