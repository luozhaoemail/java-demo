package spring.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.pojo.User;
import spring.mapper.UserMapper;
import spring.pojo.People;
import spring.pojo.PeopleFactory;
import spring.pojo.Student;
import spring.service.UserService;
import spring.service.impl.UserServiceImpl;

/**
 * Spring  创建对象的三种方式
 * @author Administrator
 *
 */
public class SpringMybatis {

	public static void main(String[] args) {
		
		 test1();
	}
	
	public static void test1() {
		//ClassPathXmlApplicationContext 默认去classes文件夹根目录开始寻找
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext2.xml");		
		/*//获取applicationContext.xml所有的bean对象：
		String[] names = ac.getBeanDefinitionNames();
		for (String string : names) {
			System.out.println(string);
		}*/
		
		UserServiceImpl bean = ac.getBean("userService",UserServiceImpl.class);
		List<User> list = bean.show();
		System.out.println(list);
	}
	
}
