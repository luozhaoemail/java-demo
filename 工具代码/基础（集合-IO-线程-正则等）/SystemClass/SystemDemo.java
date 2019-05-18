package cn.SystemClass;

import java.util.*;

public class SystemDemo
{
	public  static  void main(String[] args){
		//Properties是Hashtable的子类，也就是Map集合的一个子类对象：key和value的键值对应
		//里面定义的全是字符串，没有泛型。
		Properties p = System.getProperties(); 
		
		String val = System.setProperty("MYNAME","LUOZHAO");//设置指定属性
		System.out.println("value1="+val);
		
		String  v = System.getProperty("MYNAME"); //获取指定属性
		System.out.println("value2="+v);
		
		
		//遍历获取所有属性信息
		for(Object obj : p.keySet()){
			
			String value = (String ) p.get(obj);
			System.out.println(obj+"------->"+value);
		}
			
	}
}