package mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import org.apache.ibatis.builder.xml.XMLConfigBuilder;
//依赖mybatis-3.2.7.jar
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;


public class TestMybatis {
	public static void main(String[] args) throws IOException {
		System.out.println("====MyBatis 动态 SQL查询测试====");
		//InputStream reader = Resources.getResourceAsStream("myabtis.xml");//报错：Could not find resource myabtis.xml
		Reader reader = Resources.getResourceAsReader("mybatis/mybatis.xml");

		//方式2：
		/*XMLConfigBuilder xmlbd = new XMLConfigBuilder(reader);
		Configuration conf = xmlbd.parse();
		DefaultSqlSessionFactory ff = new DefaultSqlSessionFactory(conf);
		SqlSession sqlSesiion = ff.openSession();	*/			
		
		
		//使用工厂设计模式
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		//生产SqlSession
		SqlSession session=factory.openSession();//fasle默认不自动提交事务,需要手动 session.commit()提交事务
		//SqlSession session=factory.openSession(true);自动提交
		
		
		/*########### 用SqlSession 去查询 ##############################*/
		/*System.out.println("\n insert:");
		try {
			User user = new User("admin","123","c:/touxiang.jpg");//id自增，不用写		
			int index = session.insert("userDao.insert", user);
		
			if(index>0)
				System.out.println("成功");
			else
				System.out.println("失败");	
							
			session.commit(); //提交事务
			
		} catch (Exception e1) {
			e1.printStackTrace();			
			session.rollback();//如果出现异常,应该回滚事务,回到数据提交前的状态.
		}
		
		System.out.println("\n update:");
		User usr = new User();
		usr.setUsername("admin");
		usr.setPassword("11111111");
		usr.setPhoto("rrrr.png");
		session.update("userDao.update", usr);
		session.commit();*/
		
		
		/*System.out.println("selAll:");
		List<User> list = session.selectList("userDao.selAll");
		for (User u : list) {
			System.out.println(u.toString());
		}
		
		
		System.out.println("\n selCount:");
		int count = session.selectOne("userDao.selCount");
		System.out.println("总数= "+count);*/
		
		
		/*System.out.println("\n 条件查找:");
		list = session.selectList("userDao.selWhere","xiaoming");
		for (User u : list) {
			System.out.println(u.toString());
		}
		
		
		System.out.println("\n 条件查找,返回map:");
		Map<Integer, User> map = session.selectMap("userDao.selectByID", 1, "id");//查询条件是 where id=1
		System.out.println(map);*/
		
		
		//如果希望传递多个参数,可以使用对象或map
		System.out.println("\n 多个条件参数查询:");
		Map<String,Object> map = new HashMap<>();
		map.put("id", 7);
		map.put("username", "admin");
		User u2 = session.selectOne("userDao.select_id_name", map);//查询条件是 where id=1
		System.out.println(u2);
		
				
		/*System.out.println("delete:");
		int x = session.delete("userDao.delete", 6);
		System.out.println("删除成功："+x);
		session.commit();*/
		
		session.close();
	}

}
