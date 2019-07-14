package service.Imp;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.UserDao;
import dao.Imp.UserDaoImp;
import pojo.PageInfo;
import pojo.User;
import service.UserSrevice;
import tools.MyBatisUtil;

public class UserSeviceImp implements UserSrevice{
	//获取Dao层对象
	UserDao ud=new UserDaoImp();
	
	@Override
	public List<User> getUserInfoService(String name) {
		return ud.getUserInfo(name);
	}

	@Override
	public PageInfo showPage(int pageSize, int pageNumber) throws IOException {
		/*Reader reader = Resources.getResourceAsReader("mybatis/mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=factory.openSession();*/
		//使用OpenSessionInView,一个servlet只会生成一个session，且会在filter中close和commit
		SqlSession session = MyBatisUtil.getSession();
		System.out.println("2 -----> session的地址："+session);
		session = MyBatisUtil.getSession();
		System.out.println("3 -----> session的地址："+session);//地址是一样的
		
		Map<String,Object> map = new HashMap<>(); //多个条件参数查询
		map.put("pageSize", pageSize);
		map.put("pageStart", pageSize*(pageNumber-1));//!!!!!最关键的就是计算每一页的其实位置
		
		List<mybatis.User> list = session.selectList("pageDao.page2", map);//查询mybatis
		
		PageInfo pi = new PageInfo();
		pi.setPageNumber(pageNumber);
		pi.setPageSize(pageSize);
		pi.setList(list);
		
		//当前页的总条数，最后一页可能未填满
		long count = session.selectOne("pageDao.selCount");
		//System.out.println("count="+count);
		pi.setTotal(count%pageSize==0 ? count/pageSize : count/pageSize+1 );
		
		System.out.println("当前分页："+pi);		
		return pi;
	}

}
