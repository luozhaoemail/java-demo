package Set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class TreeSet2 {
	
	public static void sop(Object obj){
		System.out.println(obj);
	}
	
	public static void main(String[] args) {
		
		TreeSet<String> ts =new TreeSet<String>(new ComStr());
		ts.add("abcd");
		ts.add("shhh");
		ts.add("dsvuiq");
		ts.add("xz");   //字符串默认排序为字母顺序
		/**
		 * ts.add(4);//=ts.add(new Integer(4)) 这是自动装箱动作
		 */
		
		Iterator<String> it = ts.iterator();
		while(it.hasNext())
			sop(it.next());
		
	}
}
 
class ComStr implements Comparator<String>  //自定义排序规则，按照字符串长度排序
{
	@Override
	public int compare(String s1, String s2) {
		
		/*if(s1.length() > s2.length())
			return 1;
		if(s1.length() == s2.length())//长度相同在比较字母大小
			return s1.compareTo(s2);
		return -1;*/
		/**
		 * Comparator ---->compare()
		 * Comparable ---->compareTo()
		 * 
		 * 因为数字是Integer类型，这个类实现了Comparable接口，内部自定义了compareTo()方法
		 * 所以可以用它来比较数字、
		 */
		int num = new Integer(s1.length()).compareTo(new Integer(s2.length()));
		if(num == 0)
			return s1.compareTo(s2);
		return num;
	}
	
}