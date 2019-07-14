package spring.test;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 把前置通知和后置通知都写到一个通知中,组成了环绕通知
 * @author Administrator
 *
 */
public class SpringAOP2 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext4.xml");
		SpringAOP demo = ac.getBean("demo", SpringAOP.class);
		try {
			demo.demo1("ddddd");
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 *  环绕-前置--原来的方法前面进行扩展
			一个参数 emo1ddddd
			环绕-前置--原来的方法后面进行扩展
		 */
	}

}

/** *  cglib  动态代理, 基于字节码,生成真实对象的子类.
 * @author Administrator
 *
 */
class MyArround implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		System.out.println("环绕-前置--原来的方法前面进行扩展");
		
		Object result = arg0.proceed();//放行,调用切点方式
		
		System.out.println("环绕-前置--原来的方法后面进行扩展");
		return result;
	}
	
}