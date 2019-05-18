package demo;

import java.io.Serializable;  
import java.util.ArrayList;  
import java.util.List;   
import org.apache.spark.sql.Dataset;  
import org.apache.spark.sql.Encoders;  
import org.apache.spark.sql.Row;  
import org.apache.spark.sql.SparkSession; 

/** Spark SQL对Hive的支持其中一个最重要的部分是与Hive metastore的交互，使得Spark SQL可以访问Hive表的元数据。
	在使用之前需要在Spark的classpath里面添加指定数据库的JDBC驱动。例如从Spark Shell连接Postgres数据库：
	bin/spark-shell --driver-class-path postgresql-9.4.1207.jar --jars postgresql-9.4.1207.jar 
 */
public class HiveTable {

	public static class Record implements Serializable {  
		  private int key;  
		  private String value;  
		  
		  public int getKey() {  
		    return key;  
		  }  
		  
		  public void setKey(int key) {  
		    this.key = key;  
		  }  
		  
		  public String getValue() {  
		    return value;  
		  }  
		  
		  public void setValue(String value) {  
		    this.value = value;  
		  }  
		}  
	
	public static void main(String[] args) {
		
		SparkSession spark = SparkSession
				  .builder()
				  .appName("SQL demo")
				  .master("local[*]") //.master("spark://bd01:7077")
				  .config("spark.sql.warehouse.dir", "hdfs://bd01:9000/user/spark/warehouse")
				  .enableHiveSupport()
				  .getOrCreate();

		spark.sql("LOAD DATA LOCAL INPATH '/home/1.txt' INTO TABLE tb_http");	
		
	    spark.stop();
	  }
	
	
	private static void runTable(SparkSession spark) {
		
		spark.sql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING)");  
		spark.sql("LOAD DATA LOCAL INPATH 'examples/src/main/resources/kv1.txt' INTO TABLE src");  
		  
		// Queries are expressed in HiveQL  
		spark.sql("SELECT * FROM src").show();  
		// +---+-------+  
		// |key|  value|  
		// +---+-------+  
		// |238|val_238|  
		// | 86| val_86|  
		// |311|val_311|  
		// ...  
		// only showing top 20 rows  
		
		// Aggregation queries are also supported.  
		spark.sql("SELECT COUNT(*) FROM src").show();  
		// +--------+  
		// |count(1)|  
		// +--------+  
		// |    500 |  
		// +--------+  
		  
		// The results of SQL queries are themselves DataFrames and support all normal functions.  
		Dataset<Row> sqlDF = spark.sql("SELECT key, value FROM src WHERE key < 10 ORDER BY key");  
		  
		// The items in DaraFrames are of type Row, which lets you to access each column by ordinal.  
		Dataset<String> stringsDS = sqlDF.map(row -> "Key: " + row.get(0) + ", Value: " + row.get(1), Encoders.STRING());  
		stringsDS.show();  
		// +--------------------+  
		// |               value|  
		// +--------------------+  
		// |Key: 0, Value: val_0|  
		// |Key: 0, Value: val_0|  
		// |Key: 0, Value: val_0|  
		// ...  
		  
		
		
		// You can also use DataFrames to create temporary views within a SparkSession.  
		List<Record> records = new ArrayList<Record>();  
		for (int key = 1; key < 100; key++) {  
		  Record record = new Record();  
		  record.setKey(key);  
		  record.setValue("val_" + key);  
		  records.add(record);  
		}  
		Dataset<Row> recordsDF = spark.createDataFrame(records, Record.class);  
		recordsDF.createOrReplaceTempView("records");  
		  
		// Queries can then join DataFrames data with data stored in Hive.  
		spark.sql("SELECT * FROM records r JOIN src s ON r.key = s.key").show();  
		// +---+------+---+------+  
		// |key| value|key| value|  
		// +---+------+---+------+  
		// |  2| val_2|  2| val_2|  
		// |  2| val_2|  2| val_2|  
		// |  4| val_4|  4| val_4|  
		// ...  
		// only showing top 20 rows  
	}
	 
	
	

}
