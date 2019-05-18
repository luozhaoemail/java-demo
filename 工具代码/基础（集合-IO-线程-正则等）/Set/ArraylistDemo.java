package Set;

import java.util.ArrayList;
import java.util.Iterator;

class Person {
	private String name;
	private int age;
	
	Person(String name, int age)
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
		
		if(!(obj instanceof Person))
			return false;
		
		Person p = (Person)obj;
		System.out.println(name+"//////"+p.name);
		
		return name.equals(p.name) && age==p.age; //这里的equals方法是字符串的
		//如果两个对象的成员数据一样就认为对象的相等的，返回真
	}
	 
	   
    @Override  
    public int hashCode() {    
    	String str = name + age;  
        return str.hashCode();
    	
    }
	

}

class ArraylistDemo{
	public static void sop(Object obj){
		System.out.println(obj);
	}
	
	public static void main(String[] args){
		ArrayList al = new ArrayList();
		al.add(new Person("aa",20));
		al.add(new Person("aa",20));		
		al.add(new Person("bb",33));
		al.add(new Person("bb",33));	
		al.add(new Person("cc",55));
		
		//al = unique(al);
		
		//sop("remove: " +al.remove(new Person("bb",33))); //remove()底层也会调用重写的equals()
		
		Iterator it = al.iterator();
		while(it.hasNext()){
			Person p = (Person)it.next();
			sop(p.getName()+"------"+p.getAge());
		}
		
		for(int i=0;i<al.size();i++)
			sop(al.get(i));//输出对象
	}
	
	public static ArrayList unique(ArrayList al){
		
		ArrayList tmp = new ArrayList();
		Iterator it = al.iterator();
		
		while(it.hasNext()){
			Object obj = it.next();
			if(!tmp.contains(obj))   //contains()会自动调用person的equals()方法来比较相同的对象
				tmp.add(obj);
		}
		
		return tmp;
	}
}
