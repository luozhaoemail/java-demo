package mybatis;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**实现 JDBC tomcat Pool
 *  1 在 web 项目的 META-INF 中存放 context.xml,在 context.xml 编写数据库连接池相关属性
 *  2 然后在servlet中使用该连接池
 */

@WebServlet("/pool")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Context cxt = new InitialContext();
			DataSource ds = (DataSource) cxt.lookup("java:comp/env/testpool"); //java:comp/env/ +  context.xml的连接名testpool 
			Connection conn = ds.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("select * from users");
			ResultSet rs = ps.executeQuery();
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			while(rs.next()){
				out.print(rs.getInt(1)+"&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString(2)+"<br/>");
			}
			// 使用中是active，当关闭连接对象时,把连接对象归还给数据库连接池,把状态改变成 Idle
			out.flush();
			out.close();
			rs.close();
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
