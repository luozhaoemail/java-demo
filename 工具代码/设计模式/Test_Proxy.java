package design_pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**8.代理模式（Proxy）：提供了对目标对象另外的访问方式;
 即通过代理对象访问目标对象.这样做的好处是:可以在目标对象实现的基础上,增强额外的功能操作,即扩展目标对象的功能.
 不要要随意去修改别人已经写好的代码或者方法,如果需改修改,可以通过代理的方式来扩展该方法
 
 关键是： 代理对象是对目标对象的扩展,并会调用目标对象
 
 1.1.静态代理
 1.2.动态代理:目标对象有实现接口,用JDK代理
 1.3.Cglib代理:目标对象没有实现接口,用Cglib代理,要是有第三方依赖包，暂时不做	
 */
public class Test_Proxy {

	public static void main(String[] args) {
		// 1.1.静态代理
		UserDaoProxy proxy = new UserDaoProxy(new UserDao());
		proxy.save();
				
		// 1.2.动态代理
		IterfcDao dao = new UserDao();
		System.out.println(dao.getClass().getName());
		 
		//给目标对象dao，创建代理对象proxy2
		IterfcDao proxy2 = (IterfcDao)new ProxyFactory(dao).getProxyInstance();
		System.out.println(proxy2.getClass().getName());
		
		proxy2.save();
		
		// 1.3.Cglib代理	
	}

}


// 1.1.静态代理: 代理对象与目标对象要实现相同的接口,然后通过调用相同的方法来调用目标对象的方法
interface IterfcDao //共同接口
{
    void save();
}

class UserDao implements IterfcDao //目标对象
{

	@Override
	public void save() {
		System.out.println("----已经保存数据!----");
	}
	
}

class UserDaoProxy implements IterfcDao  //代理对象
{
	private UserDao user;
	
	public UserDaoProxy(UserDao user){
		this.user = user;
	}	
	
	@Override
	public void save() {
		System.out.println("代理：开始事务...");
        user.save();
        System.out.println("代理：提交事务...");
	}
	
}


// 1.2.动态代理 利用java.lang.reflect.Proxy反射来实现
class ProxyFactory
{
	//维护一个目标对象
    private Object target;    
    public ProxyFactory (Object target)
    {
        this.target=target;
    }
    
    //给目标对象生成代理对象
    public Object getProxyInstance()
    {
    	return Proxy.newProxyInstance(
    			target.getClass().getClassLoader(),
    			target.getClass().getInterfaces(),
    			new InvocationHandler() {
				@Override
				public Object invoke(Object proxy,Method method, Object[] args)throws Throwable {
					 System.out.println("开始事务2");			                      
			                 Object returnValue = method.invoke(target, args);
			                 System.out.println("提交事务2");
			                 return returnValue;
				}    							
    			});///
    	/*newProxyInstance三个参数说明：
    	ClassLoader loader,:指定当前目标对象使用类加载器,获取加载器的方法是固定的
		Class<?>[] interfaces,:目标对象实现的接口的类型,使用泛型方式确认类型
		InvocationHandler h:事件处理,执行目标对象的方法时,会触发事件处理器的方法,会把当前执行目标对象的方法作为参数传入
    	 */
    }    
}

