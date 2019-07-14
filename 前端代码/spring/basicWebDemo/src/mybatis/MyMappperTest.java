package mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

/**
 * 接口绑定方案及多参数传递
 *
 */
public class MyMappperTest {
	public static Logger logger = Logger.getLogger(MyMappperTest.class);
	
	public static SqlSession getSession() throws IOException{
		Reader reader = Resources.getResourceAsReader("mybatis/mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);		
		SqlSession session = factory.openSession();
		return session; 
	}
	
	public static void main(String[] args) throws IOException {	
		test3();		
	}
	
	

	public static void test3() throws IOException{
		//<!-- resultMap自定义映射关系 -->
		SqlSession session = getSession();	
		MyMapper mapper = session.getMapper(MyMapper.class);
		
		List<User> list = mapper.mySelect();
		for (User u : list) {
			System.out.println(u.toString());
		}
		
		list = mapper.selUsers();
		for (User u : list) {
			System.out.println(u.toString());
		}
		
		session.close();
	}
	
	
	public static void test() throws IOException{
		SqlSession session = getSession();			
		
		//代理模式，反射机制
		MyMapper mapper = session.getMapper(MyMapper.class);
		
		logger.error("\n mapper.selAll():"); //log4j.properties中设置级别ERROR以上才会显示
		List<User> list = mapper.selAll();
		for (User u : list) {
			System.out.println(u.toString());
		}
		
		logger.error("\n mapper.selByName:"); //log4j.properties中设置级别ERROR以上才会显示
		list = mapper.selByName(7, "admin");
		for (User u : list) {
			System.out.println(u.toString());
		}
		
		logger.error("\n mapper.selByName:"); //log4j.properties中设置级别ERROR以上才会显示
		list = mapper.selByName2(11, "admin");
		for (User u : list) {
			System.out.println(u.toString());
		}
				
		session.close();
	}
	
	
	
	
	/** MyBatis 中默认 SqlSession 缓存开启，
	 * 重复调用一个select时，先去缓存区中找是否存在 statement对象,
	 * 如果没有缓存 statement对象,去数据库获取数据
	 * 注意这种缓存只在同一个Session内有效，不同的Session是相互隔离的
	 * 
	 * 如果要在不同的不同的Session中共享数据，则需要开启二级缓存：
	 * 在 mapper.xml 中添加：<cache readOnly="true"></cache>
	 * 同一个 factory内多个 SqlSession 都可以获取，注意是同一个 factory
	 * 
	 * 当 SqlSession 对象 close()时或 commit()时会把 SqlSession 缓存的数据刷(flush)到 SqlSessionFactory 缓存区中
	 */
	public static void test2() throws IOException{		
		Reader reader = Resources.getResourceAsReader("mybatis/mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);	
		SqlSession session = factory.openSession();		

		MyMapper mapper = session.getMapper(MyMapper.class);//代理模式，反射机制	
		System.out.println("第一次查询： "+mapper.selAll().hashCode());		
		
		System.out.println("缓存 "+mapper.selAll().hashCode());
		System.out.println("缓存 "+mapper.selAll().hashCode());
		session.close();
	
		session = factory.openSession();	
		mapper = session.getMapper(MyMapper.class);
		System.out.println("二级缓存： "+mapper.selAll().hashCode());
		session.close();
		
		session = factory.openSession();	
		mapper = session.getMapper(MyMapper.class);
		System.out.println("二级缓存： "+mapper.selAll().hashCode());
		session.close();
				
		
	}
	/**打印日志：
2019-05-21 23:53:15[DEBUG] [main--mybatis.MyMapper]:62 - Cache Hit Ratio [mybatis.MyMapper]: 0.0
2019-05-21 23:53:16[DEBUG] [main--mybatis.MyMapper.selAll]:139 - ==>  Preparing: select * from users 
2019-05-21 23:53:16[DEBUG] [main--mybatis.MyMapper.selAll]:139 - ==> Parameters: 
2019-05-21 23:53:16[DEBUG] [main--mybatis.MyMapper.selAll]:139 - <==      Total: 9
第一次查询： -99221689
2019-05-21 23:53:16[DEBUG] [main--mybatis.MyMapper]:62 - Cache Hit Ratio [mybatis.MyMapper]: 0.0
缓存 -99221689
2019-05-21 23:53:16[DEBUG] [main--mybatis.MyMapper]:62 - Cache Hit Ratio [mybatis.MyMapper]: 0.0
缓存 -99221689
2019-05-21 23:53:16[DEBUG] [main--mybatis.MyMapper]:62 - Cache Hit Ratio [mybatis.MyMapper]: 0.25
二级缓存： -99221689
2019-05-21 23:53:16[DEBUG] [main--mybatis.MyMapper]:62 - Cache Hit Ratio [mybatis.MyMapper]: 0.4
二级缓存： -99221689
	 */
	
	
	
}
