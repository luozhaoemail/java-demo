package base;

import java.util.*;

import tool.ArrayUtil;

public class Main5 {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
//		System.out.println();
		String s1 =sc.nextLine();
		A a = new A(s1);
//		System.out.println(a);
		
		String s2 =sc.nextLine();
		B b = new B(s2);
//		System.out.println(b);
		
		if(a.viewspotid.equals(b.viewspotid))
			System.out.println(a.orderdate+":"+a.quantity);
		
		
	}
	
	public static void dp(int n)
	{
		
		
	}
	

}
/**
[{"orderid":"1234568","orderdate":"2018-08-09","viewspotid":"10002","quantity":3}]
[{"viewspotid":"10002","level":5,"city":"重庆"}]

2018-08-09:3
 */
class A
{
	public String orderid;
	public String orderdate;
	public String viewspotid;//a.viewspotid =b.viewspotid
	public String quantity;
	
	public A(String str) 
	{
		String ss  = str.substring(2,str.length()-2);
		String[] bean = ss.split(",");
		
		this.orderid = bean[0].substring(bean[0].indexOf(":")+1, bean[0].length()-1).replace("\"", "");
		this.orderdate = bean[1].substring(bean[1].indexOf(":")+1, bean[1].length()-1).replace("\"", "");
		this.viewspotid = bean[2].substring(bean[2].indexOf(":")+1, bean[2].length()-1).replace("\"", "");
		this.quantity = bean[3].substring(bean[3].indexOf(":")+1, bean[3].length());
	}

	@Override
	public String toString() {
		return "A [orderid=" + orderid + ", orderdate=" + orderdate
				+ ", viewspotid=" + viewspotid + ", quantity=" + quantity + "]";
	}
	
}


class B
{
	public String viewspotid;//
	public String level;
	public String city;
	
	public B(String str)
	{
		String ss  = str.substring(2,str.length()-2);
		String[] bean = ss.split(",");
		
		this.viewspotid = bean[0].substring(bean[0].indexOf(":")+1, bean[0].length()-1).replace("\"", "");
		this.level = bean[1].substring(bean[1].indexOf(":")+1, bean[1].length());
		this.city = bean[2].substring(bean[2].indexOf(":")+1, bean[2].length()-1).replace("\"", "");
		
	}

	@Override
	public String toString() {
		return "B [viewspotid=" + viewspotid + ", level=" + level + ", city="
				+ city + "]";
	}
	
	
	
	
}
