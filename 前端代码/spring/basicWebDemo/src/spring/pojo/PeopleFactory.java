package spring.pojo;
/**
 * 工厂设计模式:帮助创建类对象.一个工厂可以生产多个对象.
 * @author Administrator
 *
 */
public class PeopleFactory {
	public People newInstance(){
		return new People(1,"实例工厂测试");
	}
	
	public static People getStaticInstance(){
		return new People(2,"静态工厂测试");
	}
}
