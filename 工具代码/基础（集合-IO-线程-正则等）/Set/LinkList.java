package Set;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class LinkList {

	public static void main(String[] args) {
		test2();
		
	}	
	public static void test(){
		
		LinkedList ll = new LinkedList();
		ll.addFirst("cc");
		ll.addFirst("bb");
		ll.addFirst("aa"); // 头插法
		sop(ll);
		sop(ll.size());
		sop(ll.getFirst());
		sop(ll.getFirst());
		sop(ll.getFirst());
		
		sop(ll.getLast());
		
		sop(ll.removeFirst());//删除后返回删除的值
	}
	
	public static void sop(Object obj){
		System.out.println(obj);
	}

	
	public static void test2(){
		
		LinkedList ll = new LinkedList();
		ll.addLast("aa");
		ll.addLast("bb");
		ll.addLast("cc"); // 尾插法
		ll.addLast("cc"); // 尾插法
		ll.addLast("cc"); // 尾插法		
		
		//sop(ll.getFirst());
		//sop(ll.getLast());
		//sop(ll.removeLast());
		while(!ll.isEmpty()){
			sop(ll.removeFirst());
			//sop(ll.removeLast());
		}
		
	}
	
	public static ArrayList unique(ArrayList al){
		
		ArrayList tmp = new ArrayList();
		Iterator it = al.iterator();
		
		while(it.hasNext()){
			Object obj = it.next();
			if(!tmp.contains(obj))
				tmp.add(obj);
		}
		
		return tmp;
	}
}
