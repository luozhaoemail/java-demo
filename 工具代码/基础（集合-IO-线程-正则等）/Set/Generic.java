package Set;
/**
 * 自定义泛型类
 * @author luozhao
 *
 */
public class Generic {
	public static void main(String[] agrs){
		/**泛型类没有泛型函数灵活
		 * 类型一旦固定，只能处理这种类型的。要使用其他类型只能重新定义一个对象。
		 * 而泛型函数相当于函数模板，类型灵活
		 */
		Demo<String> d = new Demo<String>();		
		d.show("hehhe");
		d.print(12121);	
		
		Demo<Integer> de = new Demo<Integer>();		
		de.show(123);		
		de.print("abncddsuhuskhdkhjk");
		
		
		Demo2 demo = new Demo2();
		demo.print(4);
		demo.print("sssss");
		
		Demo2.method("ddd");
		Demo2.method(111);
		
		InterSub<Integer> in = new InterSub<Integer>();
		in.show(222);
				
	}
}

class Demo<T> //泛型类
{
	public void show(T t){
		System.out.println("show: "+t);
	}
	
	public <T> void print(T t){ //泛型函数
		System.out.println("print: "+t);
	}
	
	/**这是错误的
	 * public static void method(T t){	
			System.out.println("static method: "+t);
		}
	注意：泛型类里的普通静态方法不能用泛型,只能讲静态方法定义成泛型的
	**/

}

class Demo2
{
	public <T> void print(T t){  //泛型函数
		System.out.println("print: "+t);
	}
	
	//注意：泛型类里的普通静态方法不能用泛型,只能讲静态方法定义成泛型的
	public static <W> void method(W w){
		System.out.println("static method: "+w);
	}
	
}

interface Inter<T> //接口泛型
{
	void show(T t);
}
class InterSub<T> implements Inter<T>
{
	@Override
	public void show(T t) {
		System.out.println("inter generic : "+t);
	}
	
}
