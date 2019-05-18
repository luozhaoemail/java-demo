package demo;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class FilterDemo {

	public static void main(String[] args) {
		SparkSession spark = SparkSession
				  .builder()
				  .appName("qqqqqqqqqqqqqqqqq")
				  .master("local[*]")
				  .config("spark.cores.max", "15")				
				  .getOrCreate();		
		
		test(spark);
	}
	
	
	private static void test(SparkSession spark) {
		System.out.println("-----------开始-------------");
		long t1 = System.currentTimeMillis();
		
		Dataset<String> ds = spark.read().textFile("file:///home/spark/test/ff.txt");
		System.out.println("filter之前的行数："+ds.count()+"\n");
		
		System.out.println("-----------开始转换-------------");
		JavaRDD<String>  rdd1 = ds.toJavaRDD().filter(new Function<String,Boolean>(){
			@Override
			public Boolean call(String line) throws Exception {
				if(line.contains("whether"))
				{
					System.out.println("过滤行： ------");
					return false;
				}
				else
					return true;
			}			
		});		  
		System.out.println("filter之后的行数："+rdd1.count()+"\n");
		
		
		/*List<String> list = rdd1.collect();  //顺序变了
		for(String val : list)
			System.out.println(val);*/
		
	    rdd1.foreach(new VoidFunction<String>(){ //顺序不变
			@Override
			public void call(String line) throws Exception {
				System.out.println("剩下的行： "+line);		
			}
	    	
	    }); 
	    
	    //定义lineLengths作为Map转换的结果 由于惰性，不会立即计算lineLengths
        //第一个参数为传入的内容，第二个参数为函数操作完后返回的结果类型
	    JavaRDD<Integer> lineLengths = rdd1.map(new Function<String, Integer>() {
	          public Integer call(String s) { 
	              System.out.println("每行长度"+s.length());
	              return s.length(); 
	          }
	    });
	    
	    //运行reduce  这是一个动作action  这时候，spark才将计算拆分成不同的task，
        //并运行在独立的机器上，每台机器运行他自己的map部分和本地的reducation，并返回结果集给去驱动程序
	    int totalLength = lineLengths.reduce(new Function2<Integer, Integer, Integer>() {
          public Integer call(Integer a, Integer b) {
        	  	return a + b; 
        	  }
        });
	   System.out.println(totalLength);
		
		
		long t2 = System.currentTimeMillis();
		System.out.println("collectAsList时间=" + (t2 - t1)/1000.0+"秒\n");//list.size());
		
	}
}
