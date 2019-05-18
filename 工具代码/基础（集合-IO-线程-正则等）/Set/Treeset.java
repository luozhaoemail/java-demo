package Set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class Treeset {
	public static void main(String[] args){
		
		test3();
	}
	
	
	public static void sop(Object obj){
		System.out.println(obj);
	}
	
	public static void test() {	
		
		TreeSet ts = new TreeSet();	//TreeSet是有序的hash集合
		ts.add("b");
		ts.add("c");
		ts.add("a");
		ts.add("A");
		
		
		Iterator it = ts.iterator();
		while(it.hasNext()){
			sop(it.next());			
		}

		
	}

	public static void test2(){
		
		TreeSet ts = new TreeSet();
		ts.add(new People("aaa",100));
		ts.add(new People("ccc",5));
		ts.add(new People("bbb",22));
		ts.add(new People("x",5));
		ts.add(new People("x",6));
		ts.add(new People("x",6));
		
		Iterator it = ts.iterator();
		while(it.hasNext()){
			People p = (People)it.next();
			sop(p.getName()+"------"+p.getAge());
		}
	}
	
	private static void test3() {
		
		TreeSet ts = new TreeSet(new Mycompare());//安装名字排序，而不是安装年龄排序
		
		ts.add(new People("aaa",100));
		ts.add(new People("ccc",5));
		ts.add(new People("bbb",22));
		ts.add(new People("bbb",19));
		ts.add(new People("aaa",100));
		
		Iterator it = ts.iterator();
		while(it.hasNext()){
			People p = (People)it.next();
			sop(p.getName()+"------"+p.getAge());
		}
	}
	
}

/**
 * Comparator ---->compare()
 * Comparable ---->compareTo()	
 *
 */

class People implements Comparable //实现这个接口，使对象之间才可以比较
{
	private String name;
	private int age;
	
	People(String name, int age)
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
	public int compareTo(Object obj) {   //比较年龄
		if(!(obj instanceof People))
			throw new RuntimeException("不是学生对象");
		
		People p =(People) obj;
		System.out.println(name+"  compare to  "+p.name);
		
		if(age>p.age)
			return 1;
		
		if(age==p.age)//主要条件相同就比较次要条件
			return name.compareTo(p.name);
		
		return -1;
	}
	
}
/**
 *当对象不具备可比较性是要实现这个接口,让容器自身具备比较性
 *定义一个类，实现Comparator接口，重写覆盖compare()方法
 *
 *当两种排序都存在时，以这种为主、
 **/
class Mycompare implements Comparator 
{
	@Override
	public int compare(Object o1, Object o2) {

		People p1 =(People) o1;
		People p2 =(People) o2;
		
		int num = p1.getName().compareTo(p2.getName()); //比较姓名
		System.out.println("调用Mycompare.compare()");
		
		if(num==0){ //姓名相同的情况下，再去比较年龄			
			/*if(p1.getAge()>p2.getAge())
				return 1;
			if(p1.getAge()==p2.getAge())
				return 0;
			return -1;	*/
			return new Integer(p1.getAge()).compareTo(new Integer(p2.getAge()));
		}
		
		return num;
		
	}
	
}




