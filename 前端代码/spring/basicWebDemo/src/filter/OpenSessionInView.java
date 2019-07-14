package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.apache.ibatis.session.SqlSession;

import tools.MyBatisUtil;


/**
 * 最开始是由Spring框架提出的.整合Hibernate框架是使用的是OpenSessionInView
 * 同一个filter可以在一个的 servlet 前后都执行
 *  （filter--） servlet （--filter）
 * 
 * @author Administrator
 *
 */
@WebFilter("/*") //这里加了注解,Web.xml也配置了该filter,所以会创建两次
public class OpenSessionInView implements Filter{
	
	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		System.out.println("---OpenSessionInView初始化");
	}

	@Override
	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		
		SqlSession session = MyBatisUtil.getSession();
		System.out.println("1 -----> session的地址："+session);
		
		try {
			filterchain.doFilter(servletrequest, servletresponse); //放行servlet, 对于同一个servlet则只有一个session
			//session.commit(); //如果正常则提交事务
			
		} catch (Exception e) {
			session.rollback();//如果异常则回滚事务
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSession(); //关闭session
		}
	}

	@Override
	public void destroy() {
		System.out.println("---OpenSessionInView销毁了");
		
	}

}
