package Set;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Arraylist {

	public static void main(String[] args) {

		test2();	
		
		
	}

	public static void sop(Object obj){
		System.out.println(obj);
	}
	
	public static void test(){
		ArrayList al = new ArrayList();
		al.add("sasa");
		al.add("bbbb");
		al.add("jjjj");		
		sop("size:"+ al.size());
		sop(al);
		//al.remove(0);
		sop(al.contains("sasa"));
		//al.clear();
		sop(al.isEmpty());
		
		ArrayList bl = new ArrayList();
		bl.add("2121212");
		bl.add("2fgkfff");
		bl.add("jjjj");	
		bl.retainAll(al);
		sop("求交集："+bl);
		
		bl.add(0, "1111111111");
		bl.set(0, "00000000");
		sop(bl.get(0));
		sop(bl.indexOf("jjjj"));
		
		for(int x=0;x<bl.size();x++)
			sop("bl["+x+"] = "+bl.get(x));
		
		Iterator it = al.iterator();
		while(it.hasNext())
			sop(it.next());
		
		for(Iterator i=al.iterator();i.hasNext();)
			sop(i.next());
	}
	
	
	public static void test2(){
		ArrayList al = new ArrayList();
		al.add("sasa");
		al.add("bbbb");
		al.add("jjjj");	
		al.add("jjjj");	
		al.add("jjjj");	
		al.add("jjjj");	
		
		al = unique(al);////去重复元素
		
		//sop(al);
		ListIterator li = al.listIterator();
		while(li.hasNext())
		{
			Object obj = li.next();
			if(obj.equals("bbbb"))
				li.add("ccccc");
		}		
		//sop(al);
		
		while(li.hasPrevious())
		{
			sop(li.previous());
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
