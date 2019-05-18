package Set;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapDemo {

	public static void main(String[] args){
		test();
		
	}
	
	public static void test(){
		Hashtable<Integer,String> ht = new Hashtable<Integer,String>();
		sop("put: "+ht.put(33, "aaaa")); //put返回原来的值,null
		sop("put: "+ht.put(33, "121212"));//相同的键下，后面的会覆盖之前的 ,put返回原来的值,aaaa
		sop("put: "+ht.put(19, "ffds"));
		sop("put: "+ht.put(21, "vvv"));
		sop(ht);
		
		//sop(ht.get(12));		
		//sop(ht.contains("aaaa"));
		//sop(ht.containsKey(12));
		//sop(ht.remove(12));
	
		sop(ht.values()); //
		Collection<String> cl = ht.values();
		sop(cl);
		
		/**map的两种遍历
		 *Set<k> keySet:将所有元素的键key值存到Set集合。再通过set的迭代器遍历，因为map没有迭代器
		 *
		 *Set< Map.Entryk,v> > entrySet: 将k-v映射关系存到Set集合中，这个关系的数据类型是Map.Entry
		 *
		 *Map是一个接口，Entry是一个Map的内部接口
		 *Interface Map
		 *{
		 *	public static interface Entry
		 *	{	
		 *		public abstract Object getKey();
		 *		public abstract Object getValue();
		 *	}
		 *}
		 */
		Set<Integer> ks = ht.keySet();//////
		Iterator<Integer> it =ks.iterator();
		while(it.hasNext())
		{
			int key = it.next();
			sop("key: "+key+"----->value: "+ht.get(key));
		}
		
		Set<Map.Entry<Integer,String>> enset = ht.entrySet();/////
		Iterator<Map.Entry<Integer,String>> iter = enset.iterator();
		while(iter.hasNext())
		{
			Map.Entry<Integer,String> me = iter.next();
			sop("key: "+me.getKey()+"----->value: "+me.getValue());//关系集合的getKey()和getValue()十分方便
		}
		
	}
	
	public static void sop(Object obj){
		System.out.println(obj);
	}
}
