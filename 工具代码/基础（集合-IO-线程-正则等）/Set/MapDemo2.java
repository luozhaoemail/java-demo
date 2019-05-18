package Set;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MapDemo2 {

	public static void main(String[] args) {
		
		HashMap<Stu,String> hm = new HashMap<Stu,String>();//无序，按has值存储
		//TreeMap<Stu,String> hm = new TreeMap<Stu,String>(new StuCom());//有序，按二叉树存储
		
		hm.put(new Stu("zhang01",21),"1中国四川");
		hm.put(new Stu("zhang02",22),"2重庆");
		hm.put(new Stu("zhang01",19),"3北京");
		hm.put(new Stu("zhang04",24),"4上海");
		hm.put(new Stu("zhang04",24),"4上海");		
		
		//两种遍历方式
		Iterator<Stu> it = hm.keySet().iterator();
		while(it.hasNext()){
			Stu stu = it.next();
			System.out.println("key: "+stu+"------> "+hm.get(stu));
		}
		
		System.out.println("\n-----------------------");
		
		Iterator<Map.Entry<Stu,String>> iter = hm.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<Stu,String> me = iter.next();
			System.out.println("key: "+me.getKey()+"************** "+me.getValue());
		}

	}

}


class Stu implements Comparable<Stu> //实现这个接口，使对象之间具有可比较性
{
	private String name;
	private int age;
	
	Stu(String name, int age)
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
	public String toString() {
		return "Stu [name=" + name + ", age=" + age + "]";
	}
	
	@Override
	public boolean equals(Object obj)//如果两个对象的成员数据一样就认为对象的相等的，返回真
	{
		if(!(obj instanceof Stu))
			throw new ClassCastException("类型不匹配");
		
		Stu stu =(Stu)obj;
		System.out.println(name+"===="+stu.name);
		return name.equals(stu.name) && age==stu.age;
		
	}	
	
	@Override
	public int hashCode() //hash值默认为地址。这里重新定义hash值
	{	
		System.out.println(name.hashCode()+age*34+"----hashcode");
		return name.hashCode()+age*34;
	}
	
	
	@Override
	public int compareTo(Stu s) 
	{		
		int num = new Integer(age).compareTo(new Integer(s.age));	//比较年龄	
		System.out.println(name+"  compare to  "+s.name);
		if(num==0)
			return name.compareTo(s.name);//主要条件相同就比较次要条件
		
		return num;			
	
	}
	
}

class StuCom implements Comparator<Stu>
{
	@Override
	public int compare(Stu o1, Stu o2) {
				
		int num = o1.getName().compareTo(o2.getName()); //比较姓名
		//System.out.println("调用Mycompare.compare()");
		
		if(num==0){ //姓名相同的情况下，再去比较年龄				
			return new Integer(o1.getAge()).compareTo(new Integer(o2.getAge()));
		}		
		return num;
		
	}
	
}
