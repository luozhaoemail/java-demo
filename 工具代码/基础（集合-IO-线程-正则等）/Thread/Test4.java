package java2.thread;
/**
 * 单例设计模式：保证某个实例有且只有一个 。 那么就要把构造方法设置为私有的,然后自己创建一个实例，再通过静态方法提供给外界
 * @author luozhao
 *1.饿汉模式
 *class Single{
 *	private static final Single s = new Single();
 *	private static Single(){}
 *	public static Single getInstance()
 *	{ *	return s; }
 *}
 *饿汉模式的特点是加载类时比较慢，但运行时获取对象的速度比较快，线程安全。推荐用饿汉模式
 */
public class Test4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/**
 * 2懒汉模式:延迟加载实例，多线程访问存在安全问题，用同步函数加锁低效，
 * 所以用同步代码块，再用双重判断来提高效率
 * @author luozhao
 *懒汉模式的特点是加载类时比较快，但是在运行时获取对象的速度比较慢，线程不安全。
 */
class Single{
	private static  Single s = null;
	private Single(){}
	
	public static Single getInstance(){
		if(s==null)
		{
			synchronized(Single.class)
			{
				if(s==null)
					s =new Single();
			}
		}		
		return s; 		
	}
}
