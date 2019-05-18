package Serialize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerialDemo {
	public static void main(String[] args) throws Exception {
		
		//write();
		read();
	}
	public static void write() throws IOException {
		//ObjectOutputStream 将 Java 对象的基本数据类型和图形写入 OutputStream,通过在流中使用文件可以实现对象的持久存储。
		ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream("D:\\1.txt"));
		oos.writeObject(new Person("zhangsan",20,100001));
		oos.close();
	}
	
	public static void read() throws ClassNotFoundException, IOException {
		//ObjectInputStream 对以前使用 ObjectOutputStream 写入的基本数据和对象进行反序列化
		ObjectInputStream ois =new ObjectInputStream(new FileInputStream("D:\\1.txt"));
		Person p = (Person) ois.readObject();
		System.out.println(p);
		ois.close();
	}
	

	
}

class Person implements Serializable
{
	/**默认UID是根据类成员计算来的，当修改类的成员时。序列化和反序列化时的ID不一致会报错local class incompatible。
	 * 所以显示指定UID就始终是一致的了
	 */
	static final long serialVersionUID = 42L;

	private String name;
	private transient int age;//关键词 transient和static修饰的不能被序列化
	private static int id;
	
	Person(String name, int age,int id)
	{
		this.name = name;
		this.age = age;	
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public int getAge(){
		return age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age +", id= "+id+ "]";
	}
	
}
