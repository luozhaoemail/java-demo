package spring.test;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


/**
 * 把前置通知和后置通知都写到一个通知中,组成了环绕通知
 * @author Administrator
 *
 */
public class SpringAOP3 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext4.xml");
		AopDemo demo = ac.getBean("aopDemo", AopDemo.class);
		try {
			demo.demo1();
		} catch (Exception e) {
			System.out.println("不处理异常");
		}
		
	}

}

@Component
class AopDemo {

	@Pointcut("execution(* spring.test.AopDemo.demo1(..))")
	public void demo1() throws Exception{
		int i = 5/0;
		System.out.println("dddddddddddddddddddddddddddd");
	}	
}


@Component
@Aspect
class MyAdvice {
	@Before("spring.test.AopDemo.demo1()")
	public void mybefore() {
		System.out.println("前置通知");
	}

	@After("spring.test.AopDemo.demo1()")
	public void myafter() {
		System.out.println("后置通知");
	}

	@AfterThrowing("spring.test.AopDemo.demo1()")
	public void mythrow() {
		System.out.println("异常通知");
	}

	@Around("spring.test.AopDemo.demo1()")
	public Object myarround(ProceedingJoinPoint p) throws Throwable {
		System.out.println("环绕-前置");
		Object result = p.proceed();
		System.out.println("环绕-后置");
		return result;
	}
}