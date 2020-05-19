package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.phoenix.mapreduce.index.automation.YarnApplication.finalStatus;

import bean.S6a;

/** 前提：
	$HIVE_HOME/bin/hiveserver2
	beeline -u jdbc:hive2://localhost:10000
 
 报错：
java -cp $HIVE_HOME/lib/*:test.jar  tool.HiveAPI 
Could not initialize class org.apache.derby.jdbc.AutoloadedDriver40 
说明说明Hive/lib中的缺少derby的包，这个包在hive/jdbc下面

不报错：
java -cp $HIVE_HOME/jdbc/*:test.jar  tool.HiveAPI
java -cp $SPARK_HOME/jars/*:test.jar  tool.HiveAPI

 */
public class HiveAPI {	
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";  
    private static String url = "jdbc:hive2://localhost:10000/lz"; 
    private static String user = "root";  
    private static String password = "root";  
    
    public static Connection getConn()  {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    
    public static ResultSet executeSQL(String sql) throws SQLException  {
        Connection conn = HiveAPI.getConn();  
        Statement stmt = conn.createStatement();
        System.out.println("Running ===>" + sql);
        ResultSet res=null;
        try {
        	//////////
            res = stmt.executeQuery(sql); 
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

       
    public static void close(Connection conn,Statement stmt){
          try {
            if (conn != null) {  
                  conn.close();  
                  conn = null;  
              }  
              if (stmt != null) {  
                  stmt.close();  
                  stmt = null;  
              }
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }
    
	public static void countTime(long start) {
  		System.out.println("花费时间= " + (System.currentTimeMillis() - start)/1000.0 + " 秒");
  	}
    
    public static void main(String[] args) throws Exception {  
//    	String str="select * from t1 where Telnum=13501231234";		   
//    	String str2="select * from t1 where CAST(Telnum as string) like'%1350123123%'";
    	    	
    	String str="select * from t1 where Telnum=13501231234 and Time1>='2017/5/1 00:00:00' and Time2<='2017/5/2 00:00:00'";		   
    	String str2="select * from t1 where CAST(Telnum as string) like'%1350123123%' and Time1>='2017/5/1 00:00:00' and Time2<='2017/5/2 00:00:00'";
    	
    	long start = System.currentTimeMillis();
    	ResultSet res = HiveAPI.executeSQL(str);
    	while(res!=null && res.next()){
    		S6a s = new S6a();
    		s.Time1 = res.getString(1);
    		s.Time2 = res.getString(2);
    		s.seat = res.getInt(3);
    		s.IMSI = res.getLong(4);
    		s.Telnum = res.getLong(5);
    		s.Type = res.getInt(6);
    		s.Cause = res.getInt(7);
    		System.out.println(s);
    		
    	}
    	countTime(start);
    	
    	System.out.println("\n---------------\n");

    	start = System.currentTimeMillis();
    	res = HiveAPI.executeSQL(str2);
    	while(res!=null && res.next()){
    		S6a s = new S6a();
    		s.Time1 = res.getString(1);
    		s.Time2 = res.getString(2);
    		s.seat = res.getInt(3);
    		s.IMSI = res.getLong(4);
    		s.Telnum = res.getLong(5);
    		s.Type = res.getInt(6);
    		s.Cause = res.getInt(7);
    		System.out.println(s);
    	}
    	countTime(start);
    }
}
