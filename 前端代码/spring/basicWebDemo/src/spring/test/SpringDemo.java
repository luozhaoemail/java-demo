package spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.pojo.People;
import spring.pojo.PeopleFactory;
import spring.pojo.Student;

/**
 * Spring  创建对象的三种方式
 * @author Administrator
 *
 */
public class SpringDemo {

	public static void main(String[] args) {
		
		 test4();
	}
	
	//通过构造方法创建
	public static void test1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		
		//获取applicationContext.xml所有的bean对象：
		String[] names = ac.getBeanDefinitionNames();
		for (String string : names) {
			System.out.println(string);
		}
				
		//获取指定的bean对象
		People people = ac.getBean("peo",People.class);
		System.out.println(people);
		
		people = ac.getBean("peo1",People.class);
		System.out.println(people);
		
	}
	
	//实例工厂:需要先创建工厂,才能生产对象
	public static void test2() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		
		//传统方式：
		PeopleFactory factory = new PeopleFactory();
		People people = factory.newInstance();
		System.out.println("传统方式："+people);
		/**传统方式相当于：
	    <bean id="factory" class="spring.pojo.PeopleFactory"></bean>
	    <bean id="peo2" factory-bean="factory" factory-method="newInstance"></bean>
		*/			
		
		people = ac.getBean("peo2",People.class);
		System.out.println("工厂方法："+people);
		
	}
	
	//静态工厂: 不需要创建工厂,快速创建对象.
	public static void test3() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		
		//传统方式：
		People people = PeopleFactory.getStaticInstance();
		System.out.println("传统方式："+people);
		/**传统方式相当于：
	   	<bean id="peo3" class="spring.pojo.PeopleFactory" factory-method="getStaticInstance"></bean>
		*/			
		
		people = ac.getBean("peo3",People.class);
		System.out.println("工厂方法："+people);
		
	}

	// 注入: (通过 set 方法)给 Bean 的属性赋值 -
	public static void test4() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		People people = ac.getBean("peo4",People.class);
		System.out.println("注入方法："+people);
		
		people = ac.getBean("peo5",People.class);
		System.out.println("注入方法2："+people);
		
		Student stu =  ac.getBean("stu",Student.class);
		System.out.println("复合嵌套类型----："+stu);
	}
}
