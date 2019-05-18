package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.ForeachPartitionFunction;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.storage.StorageLevel;

import scala.Function;

public class FoeachDemo {
	public static HashMap<String,S11> map = new HashMap<String,S11>();
	
	
	public static void main(String[] args) {
		SparkSession spark = SparkSession
				  .builder()
				  .appName("qqqqqqqqqqqqqqqqq")
				  .master("spark://bd01:7077")		  
				  .config("spark.sql.warehouse.dir", "hdfs://bd01:9000/user/spark/warehouse")				  
				  .config("spark.cores.max", "15")
				  //.config("spark.driver.extraJavaOptions","-Xms4096m -Xmn1024m -XX:PermSize=1024m -XX:MaxPermSize=2048m  -Xloggc:./gc`date +%Y-%m-%d-%H-%M`.txt -XX:PrintGCDetails")			            
				 // .config("spark.executor.extraJavaOptions","-Xms4096m -Xmn1024m -XX:PermSize=1024m -XX:MaxPermSize=2048m  -Xloggc:./gc`date +%Y-%m-%d-%H-%M`.txt -XX:PrintGCDetails")			             
				  .config("spark.executor.memory", "4g")
				  //.config("spark.driver.memory", "4g")//在In client mode，这里设置无效，因为JVM在这之前已经创建了，只能提交的时候 --driver-memory 4g
				  .config("spark.executor.instances", "15")
				  .config("spark.executor.cores", "1")
				  .config("spark.driver.cores","3") 
				  .config("spark.task.cpus", "1")
				
				  /*.config("spark.memory.useLegacyMode","true")
				  .config("spark.storage.safetyFaction ","0.95")
				  .config("spark.storage.memoryFraction","0.7")
				  .config("spark.shuffle.memoryFraction","0.2")
				  .config("spark.storage.unrollFraction","0.2")*/
				  .enableHiveSupport()
				  .getOrCreate();
		
		//  .config("","")  spark-submit --class demo.FoeachDemo test.jar
		
		spark.sql("use lc");
		
		test(spark);
		//test2(spark);
	}
	
	private static void testMap(SparkSession spark){
		List<Integer> data = Arrays.asList(1, 2, 4, 3, 5, 6, 7);
		//RDD有两个分区
		JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
		JavaRDD<Integer> javaRDD = jsc.parallelize(data,2);
		//计算每个分区的合计
		JavaRDD<Integer> mapPartitionsRDD = javaRDD.mapPartitions(new FlatMapFunction<Iterator<Integer>, Integer>() {   
		 @Override
		 public Iterator<Integer> call(Iterator<Integer> integerIterator) throws Exception {
		        int isum = 0;
		        while(integerIterator.hasNext())
		            isum += integerIterator.next();
		        LinkedList<Integer> linkedList = new LinkedList<Integer>();
		        linkedList.add(isum);
		        return (Iterator<Integer>) linkedList;    }
		});

		System.out.println("mapPartitionsRDD~~~~~~~~~~~~~~~~~~~~~~" + mapPartitionsRDD.collect());

	}

	@SuppressWarnings("unused")
	private static void test(SparkSession spark) {
		System.out.println("ssssssssssssssssssssssss");
		long t1 = System.currentTimeMillis();
		Dataset<Row> ds = spark.sql("select * from tb_p2p").cache();
							   //.persist(StorageLevel.MEMORY_AND_DISK_SER());//1330809行
		
		List<Row> list = ds.collectAsList();//5.434秒 
		long t2 = System.currentTimeMillis();
		System.out.println("collectAsList时间=" + (t2 - t1)/1000.0+"秒\n");//list.size());
		
	}
	
	@SuppressWarnings("serial")
	private static void test2(SparkSession spark) {
		System.out.println("1111111111111111111111111111111");
		long t1 = System.currentTimeMillis();
		spark.sql("select * from tb_s11 limit 100").foreachPartition(new ForeachPartitionFunction<Row>(){

			@Override
			public void call(Iterator<Row> it) throws Exception {
				Row row = it.next();
				S11 s11 = new S11(row.getString(0),row.getString(1),row.getInt(2),
						row.getString(3),row.getString(4),row.getInt(5),row.getInt(6));	
				
			}
			
		});	
		
		spark.sql("select * from tb_s11").foreachPartition(new MyFunction());
		
		long t2 = System.currentTimeMillis();
		System.out.println("foreachPartition时间=" + (t2 - t1)/1000.0+"秒\n");//6s
		
		/*long start=System.nanoTime();  
		Set<String> setEach=map.keySet();  
		for(String key:setEach){  
		    System.out.println(map.get(key));  
		}  
		long end=System.nanoTime();  
		System.out.println("keySet遍历map耗时"+(end-start)/1000+"微秒"); */ 
		
			
	}
}
