package Set;

import java.util.ArrayList;
import java.util.Iterator;

public class Genericity {
	public static void main(String[] agrs){
		//test();
		
		test2();
		
		
	}
	
	private static void test() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("ss");
		al.add("vvv");
		
		ArrayList<Integer> aa = new ArrayList<Integer>();
		aa.add(33);
		aa.add(5);
	
		printall(al);
		printall(aa);
		
	}
	
	private static void test2(){
		ArrayList<Perso> al = new ArrayList<Perso>();
		al.add(new Perso("zhangs"));
		al.add(new Perso("lisi"));
		
		ArrayList<Student> aa = new ArrayList<Student>();
		al.add(new Perso("student01"));
		al.add(new Perso("student02"));
			
		print(al);
		print(aa);
		printtt(al);
		printtt(aa);
	}

	public static void printall(ArrayList<?> a)//不明确类型用？表示，占位符，这就是一个通用模板
	{
		Iterator<?> it = a.iterator();
		while(it.hasNext())
			System.out.println(it.next());//限定：不能用成员函数getName()
	}
	/** ?  通配符
	 * 	？ extends E 上限，表示可以接收E和E的子类
	 *  ? super E  下限，表示可以接收E和E的父类
	 */
	public static void print(ArrayList<? extends Perso> a)//泛型限定，限定为Perso 和它的子类Stduent
	{
		Iterator<? extends Perso> it = a.iterator();
		while(it.hasNext())
			System.out.println(it.next().getName());
	}
		
	public static void printtt(ArrayList<? super Student> a)//泛型限定，限定为Student 和它的父类Perso
	{
		Iterator<? super Student> it = a.iterator();
		while(it.hasNext())
			System.out.println(it.next());
	}
	
}

class Perso
{
	private String name;
	Perso(String name)
	{
		this.name =  name;
	}
	
	public String getName(){
		return name;
	}
}

class Student extends Perso
{
	Student(String name)
	{
		super(name);		
	}
		
}
