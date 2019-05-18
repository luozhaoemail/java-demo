package Set;

import java.util.HashSet;
import java.util.Iterator;
/**
 * HashSet是如何保证唯一性的呢----通过hashCode() 和 equals()两个方法
 * 如果对象的hashCode值相同，会判断equals是否为真，也就是判断内容 
 * 如果对象的hashCode值不相同，就不会判断equals
 */
class Person2 {
	private String name;
	private int age;
	
	Person2(String name, int age)
	{
		this.name = name;
		this.age = age;		
	}
	
	public String getName(){
		return name;
	}
	
	public int getAge(){
		return age;
	}
	
	@Override 
	public boolean equals(Object obj){/////重写equals方法
		
		if(!(obj instanceof Person2))
			return false;
		
		Person2 p = (Person2)obj;
		System.out.println(name+"===="+p.name);
		
		return name.equals(p.name) && age==p.age; //这里的equals方法是字符串的
		//如果两个对象的成员数据一样就认为对象的相等的，返回真
	}
	 
	   
    @Override  
    public int hashCode() {    
    	String str = name + age;
    	System.out.println(name+" : "+str.hashCode()+"----hashcode");
        return str.hashCode();
    	
    }
}


public class Hashset {
	
	public static void sop(Object obj){
		System.out.println(obj);
	}
	
	public static void main(String[] args) {
		test2();
		
	}


	private static void test() {
		
		HashSet hs = new HashSet();//无序，不重复
		hs.add("a");
		hs.add("b");
		sop(hs.add("c")); //添加成功返回真
		sop(hs.add("c")); //添加失败返回假
		
		Iterator it = hs.iterator();
		while(it.hasNext()){
			sop(it.next());
		}
		
	}
	
	private static void test2() {
		HashSet hs = new HashSet();
		hs.add(new Person2("a",1));
		hs.add(new Person2("b",2));
		hs.add(new Person2("c",3));
		hs.add(new Person2("c",3));				
		//sop(hs);

		sop(hs.contains(new Person2("a",1)));//先判断hash值是否相同，再调用equals
		sop(hs.remove(new Person2("b",2)));
		
		
		Iterator it = hs.iterator();
		while(it.hasNext()){
			Person2 p = (Person2)it.next();
			sop(p.getName()+"-----"+p.getAge());
		}
		
	}
	
	

}
