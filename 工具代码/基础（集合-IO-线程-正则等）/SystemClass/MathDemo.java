package cn.SystemClass;

import java.util.Random;

public class MathDemo {

	public static void main(String[] args) {
		//ceil返回最double值，为参数的向上取整：大于参数的最小整数
		double d =Math.ceil(3.14);
		cout(d);//4.0
		cout(Math.ceil(-3.14));//-3.0
		
		//ceil返回最double值，为参数的向下取整：小于参数的最大整数
		double f =Math.floor(3.14);
		cout(f);//4.0
		cout(Math.floor(-3.14));//-3.0

		long l = Math.round(12.64);//四舍五入
		cout(l);
		
		cout(Math.pow(3,2)); //平方
		
		cout(Math.random());// [0-1) 的伪随机小数
		cout( (int)(Math.random()*6+1) );//[1-6]
		
		Random r = new Random();
		cout(r.nextInt(6));//[0-6)+1变成 [1-6]
	}
	
	public static void cout(Object obj)
	{
		System.out.println(obj);
	}
}
