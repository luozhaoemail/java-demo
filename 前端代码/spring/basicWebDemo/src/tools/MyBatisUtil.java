package tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**ThreadLocal
线程容器,给线程绑定一个 Object 内容,后只要线程不变,可以随时取出.
但是改变线程了就无法取出内容.
 */
public class MyBatisUtil {
	//factory实例化的过程是一个比较耗费性能的过程.
	//保证有且只有一个factory
	private static SqlSessionFactory factory;
	private static ThreadLocal<SqlSession> tl = new ThreadLocal<>();
	static{
		try {
			//InputStream is = Resources.getResourceAsStream("mybatis.xml");
			Reader is = Resources.getResourceAsReader("mybatis/mybatis.xml");
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取SqlSession的方法，类似于单例模式
	 */
	public static SqlSession getSession(){
		SqlSession session = tl.get();
		if(session==null){
			tl.set(factory.openSession());
		}
		return tl.get();
	}
	
	public static void closeSession(){
		SqlSession session = tl.get();
		if(session!=null){
			session.close();
		}
		tl.set(null);
	}
}
