package spring.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.People;

@Configuration
public class SpringZhujie {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext5.xml");
		People peo = ac.getBean("people", People.class);
		System.out.println("自动注入："+peo);
		
		
//		Demo demo = new Demo();
		Demo demo =  ac.getBean("demo1", Demo.class);		
		System.out.println(demo.host);
		System.out.println(demo.port);
		
		
		Demo demo2 =  ac.getBean("demo1", Demo.class);
		System.out.println(demo.hashCode());//<bean/>标签对应的对象默认是单例的.
		System.out.println(demo2.hashCode());
		
	}
}


class Demo{	
	//<bean id="demo1" class="spring.test.Demo"></bean>
	@Value("${host}")
	public  String host; //使用注解读取属性文件demo.properties
	
	@Value("${port}")
	public  String port;
	
	/**
	 方式二：
	 <bean id="demo2" class="spring.test.Demo">
    	<property name="host" value="${host}"></property>
    	<property name="port" value="${port}"></property>    	
    </bean>
	 */

	
	
	
}
