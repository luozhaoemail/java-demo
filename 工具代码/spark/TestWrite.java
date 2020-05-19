package ex1;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class TestWrite {
	private static SparkSession spark;
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		System.setProperty("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
		
		spark = SparkSession
				  .builder()
				  .appName("LoadSparkBulk")
				  .master("spark://Master:7077")			  
				  .config("spark.sql.warehouse.dir", "hdfs://Master:9000/user/hive/warehouse")	
				  .config("spark.cores.max", "24")
				  .config("spark.executor.memory", "2g")
				  .enableHiveSupport()
				  .getOrCreate();
		
		spark.sql("use xdrtest1");
		
		Dataset<Row> ds = spark.sql("select * from int_s6a");
		ds.write().mode(SaveMode.Append).saveAsTable("test_s6a");
		
		long t2 = System.currentTimeMillis();
		System.out.println("TestWrite:::时间=" + (t2 - t1)/1000.0+"秒\n");
	}
	
}
