package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class HiveTest {
	public  static void main(String[] args) throws ClassNotFoundException, SQLException{
		
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		Connection conn = DriverManager.getConnection("jdbc:hive2://localhost:10000/lz","root","root");
		
		Statement st =conn.createStatement();
		ResultSet rst = st.executeQuery("select *  from t1 limit 100");
		System.out.println("----show tables");
		while(rst.next()){
			System.out.println(rst.getString(1)+"****"+rst.getString(2));
			
		}
		
		conn.close(); 
	}
}
