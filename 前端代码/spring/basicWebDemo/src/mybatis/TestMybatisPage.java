package mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class TestMybatisPage {
	public static Logger logger = Logger.getLogger(TestMybatisPage.class);
	//public static Logger logger = Logger.getRootLogger();  
	
	
	public static SqlSession getSession() throws IOException{
		Reader reader = Resources.getResourceAsReader("mybatis/mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);		
		SqlSession session=factory.openSession();//fasle默认不自动提交事务,需要手动 session.commit()提交事务
		//SqlSession session=factory.openSession(true);自动提交
		return session; 
	}
	
	public static void main(String[] args) throws IOException {	
		logger.error("查询第一页:"); //log4j.properties中设置级别ERROR以上才会显示
		testPage(5,1);		
		logger.error("查询第二页:");
		testPage(5,2);
		logger.error("查询第三页:");
		testPage(5,3);
	}

	/**select * from users limit 0,5  从0开始，向后查5个 
	 * testPage(5,1);  pageStart=5*(1-1)=0, pageSize=5   
	 * testPage(5,2);  pageStart=5*(2-1)=5, pageSize=5
	 * @param pageSize 每页显示几条数据
	 * @param pageNumber  第几页
	 * @throws IOException 
	 */
	public static void testPage(int pageSize, int pageNumber) throws IOException{
		SqlSession session = getSession();				
//		int pageSize = 2;//显示几个		
//		int pageNumber = 1;//第几页
		Map<String,Object> map = new HashMap<>(); //多个条件参数查询
		map.put("pageSize", pageSize);
		map.put("pageStart", pageSize*(pageNumber-1));
				
		List<User> list = session.selectList("pageDao.page2", map);
		for (User u : list) {
			System.out.println(u.toString());
		}	
		
		session.close();
	}
	
	
}
