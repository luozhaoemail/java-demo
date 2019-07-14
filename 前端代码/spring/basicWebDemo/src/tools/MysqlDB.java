package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlDB {
	public final static String DRIVER = "com.mysql.jdbc.Driver";
	public final static String URL = "jdbc:mysql://localhost:3306/test";
	public final static String USER = "root";
	public final static String PASSWD = "root";
	
	static {
		try {
			Class.forName(DRIVER);	
			
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return conn;
	}
	
	public static ResultSet getResultSet(String sql) throws SQLException{
		System.out.println("SQL="+sql);
		Connection conn =  getConnection();
		PreparedStatement ps1 = conn.prepareStatement(sql);
		ResultSet rs = ps1.executeQuery();
		return rs;
	}
	
}
