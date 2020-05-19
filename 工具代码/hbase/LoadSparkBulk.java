package ex1;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapred.TableOutputFormat;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2;
import org.apache.hadoop.hbase.mapreduce.LoadIncrementalHFiles;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Job;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.storage.StorageLevel;

import conn.HBaseUtil;
import phoenix.SparkConnection;
import scala.Tuple2;

/**利用Spark Rdd生成Hfile直接导入到Hbase
 * 利用Rdd生成Hfile,然后利用BulkLoad导入Hfile的话
spark-submit --class ex1.LoadSparkBulk test.jar


前提在hbase中创建表：create 'int_s6a','cf',值指定列簇即可，列是动态扩展的。
 */
public class LoadSparkBulk implements Serializable
{	
	private static final long serialVersionUID = 1L;
	private static SparkSession spark;
	
    public static void main(String[] args) throws Exception 
    { 
    	long t1 = System.currentTimeMillis();
    	System.setProperty("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
    	
    	String tb = "int_s6a";//HBase中的表名
//    	HBaseUtil.init();
//    	HBaseUtil.createTable(tb, new String[]{"cf"}, true);//相当于create 'int_s6a','cf' ,preBuildRegion预分区
    	
    	String hfilePath = "hdfs://lyy1:9000/data/hfile/int_s6a.hfile";
    	
    	spark = SparkSession
				  .builder()
				  .appName("LoadSparkBulk")
				  .master("spark://lyy1:7077")			  
				  .config("spark.sql.warehouse.dir", "hdfs://lyy1:9000/user/spark/warehouse")	
				  .config("spark.cores.max", "24")
				  .config("spark.executor.memory", "4g")
				  .enableHiveSupport()
				  .getOrCreate();         
    	spark.sql("use xdr");
    	
    	
    	
		Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","lyy1,lyy2,lyy3,lyy4");
        conf.set("hbase.zookeeper.property.clientPort","2181");
        conf.set("zookeeper.znode.parent","/hbase");
        conf.set(TableOutputFormat.OUTPUT_TABLE, tb);
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        
        TableName tbName = TableName.valueOf(tb);
        Table table = connection.getTable(tbName);
        
        Job job = Job.getInstance(conf);
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(KeyValue.class);
        job.setOutputFormatClass(HFileOutputFormat2.class);
        HFileOutputFormat2.configureIncrementalLoad(job, table,connection.getRegionLocator(tbName));
        
        
        Dataset<Row> ds = spark.sql("select * from int_s6a"); //where timeM=1517684760000 limit 1
        String[] colName =ds.columns();
        Arrays.sort(colName);//1.列要按照字典排序，升序
      
        JavaRDD<Row> rdd = ds.toJavaRDD().filter(new Function<Row, Boolean>() {
			
			@Override
			public Boolean call(Row row) throws Exception {
				//过滤掉电话号码不符合要求的
				if(row.getAs("msisdn")!=null && row.getAs("msisdn").toString().length()!=0)
					return true;				
				else 
					return false;
			}
		});
       long count = rdd.persist(StorageLevel.MEMORY_AND_DISK_SER()).count();
       System.out.println("rdd.count= "+count);
       
        JavaPairRDD<ImmutableBytesWritable,KeyValue> hfileRdd = 
        		rdd.flatMapToPair(new PairFlatMapFunction<Row, ImmutableBytesWritable, KeyValue>() {
        			
        	private static final long serialVersionUID = 1L;
        	
			@Override
			public Iterator<Tuple2<ImmutableBytesWritable, KeyValue>> call(Row row) throws Exception {
//				Int_s6a s6a = new Int_s6a(row);
//				Field[] fields = s6a.getClass().getDeclaredFields();
//				byte [] rowkey = Bytes.toBytes(s6a.getMsisdn().substring(2));//8615121218711
				
				String isdn = row.getAs("msisdn").toString();
				if(isdn.startsWith("86"))
					isdn = isdn.substring(2);
				byte [] rowkey = Bytes.toBytes(isdn);//8615121218711
                byte [] cf = Bytes.toBytes("cf");
				ArrayList<Tuple2<ImmutableBytesWritable, KeyValue>> tpLists =
							new ArrayList<Tuple2<ImmutableBytesWritable, KeyValue>>();
				for (int i = 0; i < colName.length; i++)//拆分一行的多个字段
				{									
//					for(int j=0; j<fields.length; j++)
//					{	
//						fields[j].setAccessible(true);
//						if(colName[i].equals(fields[j].getName()) 
//							|| (colName[i]=="interface" && fields[j].getName()=="interface_" ))
//						{
//							byte [] column = Bytes.toBytes(colName[i]);	
//							byte [] value = s6a.reflect(fields[j],s6a);							
//							KeyValue keyValue = new KeyValue(rowkey,cf,column,value);
//							tpLists.add(new Tuple2<ImmutableBytesWritable, KeyValue>(new ImmutableBytesWritable(rowkey), keyValue));
//						}
//					}					
				
					byte [] column = Bytes.toBytes(colName[i]);
					byte [] value = Bytes.toBytes(row.getAs(colName[i]).toString());//要求关联phoenix表字段全部是varchar类型。
					KeyValue keyValue = new KeyValue(rowkey,cf,column,value);
					tpLists.add(new Tuple2<ImmutableBytesWritable, KeyValue>(new ImmutableBytesWritable(rowkey), keyValue));			
				}
				
				return tpLists.iterator();
			}
		}).sortByKey(true);//2.行也要按照字典排序，升序 
        
        
        //自动删除output
  		Path path = new Path(hfilePath);
  		FileSystem fs = path.getFileSystem(conf);
  		if(fs.exists(path))
  			fs.delete(path,true);
    
        hfileRdd.saveAsNewAPIHadoopFile(hfilePath, ImmutableBytesWritable.class,
        								KeyValue.class, HFileOutputFormat2.class, conf);
                
		//利用bulk load hfile
        LoadIncrementalHFiles bulkLoader = new LoadIncrementalHFiles(conf);
        bulkLoader.doBulkLoad(new Path(hfilePath),admin,table,connection.getRegionLocator(tbName));
       
        long t2 = System.currentTimeMillis();
      	System.out.println(":::::::::::时间=" + (t2 - t1)/1000.0+"秒\n");   
        
    }

}


