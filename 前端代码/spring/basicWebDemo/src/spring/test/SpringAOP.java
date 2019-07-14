package spring.test;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAOP {

	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext3.xml");
		SpringAOP demo = ac.getBean("demo", SpringAOP.class);
		demo.demo0();
		demo.demo1();
//		demo.demo1("张三");
//		demo.demo1("王五",999);
		demo.demo2();
		/**本来demo0、demo1、demo2是顺序执行的，现在对demo1横切，
		 * 在demo1的前后 织入 额外的代码，扩充了demo1的功能
		        顺序执行demo0 -------
			#执行前置通知
			执行无参数 demo1
			#执行后置通知
			顺序执行demo2 -------
		 */
	}
	
	public int demo0() throws Exception{
		System.out.println("顺序执行demo0 -------");
		return 99999999;
	}
	
	
	public void demo1() throws Exception{
		int x = 5/0;
		System.out.println("执行无参数 demo1");
	}	
	public void demo1(String name){
		System.out.println("一个参数 emo1"+ name);
	}	
	public void demo1(String name, int age) throws Exception{
		System.out.println("两个参数 demo1"+name+"  "+age);
	}
	
		
	public void demo2() throws Exception{
		System.out.println("顺序执行demo2 -------");
	}
}


class MyBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		System.out.println("\n#执行前置通知");
		System.out.println("切点对象："+arg2);
		System.out.println("切点方法："+arg0);
		System.out.println("切点方法参数：");
		for(Object obj: arg1)
			System.out.print(obj+" 	");
		System.out.println("");
		
		
	}	
}

class MyAfterAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {	
		System.out.println("\n#执行后置通知");		
		System.out.println("切点对象："+arg3);
		System.out.println("切点方法："+arg1);
		System.out.println("切点方法参数：");
		for(Object obj: arg2)
			System.out.print(obj+" 	");
		System.out.println();
		System.out.println(arg1.getName()+"方法的返回值="+arg0);
	}
}

//AspectJ 方式 自定义异常处理类:只有当切点报异常才能触发异常通知
class MyThrowAdvice {
	
	public void myexception(Exception e1){
		System.out.println("执行异常通知"+e1.getMessage());
	}
}

class MyThrowAdvice2 implements ThrowsAdvice{
	
	public void afterThrowing(Exception e1) throws Throwable {
		System.out.println("执行异常通过-schema-base 方式");
	}
}

