package tool;

import org.apache.spark.sql.SparkSession;

public class SparkConfig 
{
	public SparkSession sp;
	
	
	//保证内存中只有一个SparkApi实例对象
	private static volatile SparkConfig instance;
	public static SparkConfig getIstance() {

		if(instance == null) {

			synchronized (SparkConfig.class) {
				if (instance == null) {					
					instance = new SparkConfig();
				}
			}
		}
		return instance;
	}
	
	private SparkConfig()
	{		
		System.setProperty("spark.serializer","org.apache.spark.serializer.KryoSerializer");
		int cores = 40;
		sp = SparkSession
				.builder()
				.appName(Msg.name)
				.master(Msg.master)//spark://lyy1:7077 local[*]		  
				.config("spark.sql.warehouse.dir", Msg.warehosue) //hdfs://lyy1:9000/user/spark/warehouse		  
//				.config("spark.cores.max", "16")
				  
				//调度
				.config("spark.scheduler.mode", "FAIR")//公平调度方式，默认FIFO
				.config("spark.speculation",true)//默认false,开启推测机制,将执行事件过长的节点去掉，重新分配任务
				.config("spark.locality.wait","60s")//默认3s,为了数据本地性最长等待时间
				//XX:PermSize占用堆外内存
//				.config("spark.executor.extraJavaOptions","-Xms1024m -Xmx1024m , -XX:PermSize=256M -XX:MaxPermSize=256M -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -Xloggc:/tmp/spark_gc.log")
				
				//内存管理
				.config("spark.executor.memory", "2g")//指定计算节点的内存为4G
//				.config("spark.executor.memoryoverhead","2g")//堆外内存，减少频繁GC
//				.config("spark.driver.maxResultSize","2g")//executor给driver返回结果集大小				
//				.config("spark.memory.useLegacyMode","true")//默认false,是否使用老版固定内存管理
//				.config("spark.memory.fraction","0.8")//默认0.6，堆内存中用于执行、混洗和存储（缓存）的比例
//				.config("spark.memory.storageFraction","0.6")//默认0.5,rdd的storage与cache的默认分配的内存池大小
//				.config("spark.memory.offHeap.enabled","true")//默认false,启用堆外内存
//				.config("spark.memory.offHeap.size","2g")//默认0,堆外内存分配的大小
//				.config("spark.streaming.unpersist",true)//释放缓存
			
//				.config("spark.scheduler.executorTaskBlacklistTime","30000")//默认0，task失败所在的executor进入黑名单30s
				.config("spark.sql.inMemoryColumnarStorage.compressed",true)//压缩列存储
//				.config("spark.sql.inMemoryColumnarStorage.batchSize","10000") //来设置一次处理多少row
	
				//shuffle时的并行度
//			    .config("spark.default.parallelism",String.valueOf(3*cores))//shuffle时的并行度=结果是56+3=59
//			    .config("spark.sql.shuffle.partitions",String.valueOf(cores))//sql聚合是的并行度
				
			    //shuffle优化
//			    .config("spark.shuffle.consolidateFiles", "true")//默认值：false,合并
//			    .config("spark.shuffle.file.buffer","32m")//默认32k,写到磁盘缓冲，减小写磁盘次数
//			    .config("spark.reducer.maxSizeInFlight","48m")//默认48k，read task缓冲,减小拉取数据次数
//			    .config("spark.shuffle.io.maxRetries","10")//默认3,拉取失败重试次数，避免full gc导致拉取失败
//			    .config("spark.shuffle.io.retryWait","30s")//默认5s,每次重试拉取数据的等待间隔
//			    .config("spark.shuffle.sort.bypassMergeThreshold","1000")//默认值：200

			    //压缩和序列化
				.config("spark.rdd.compress",true)//RDD压缩
				.config("spark.serializer","org.apache.spark.serializer.KryoSerializer")//指定序列化方式
//				.config("spark.kryo.registrator", "ck4g.tool.MyKryoRegistrator")//自定义注册类
				.config("spark.kryoserializer.buffer","64m")//默认64k，太小会Buffer overflow
			    .config("spark.kryoserializer.buffer.max","64m")//64m
//				.config("spark.kryo.classesToRegister", "demo.TestKryo")//单个类注册			   
//				.config("spark.kryo.referenceTracking",false)//true
//				.config("spark.kryo.registrationRequired",true)//false
//				.config("spark.kryo.unsafe",true)//false
				  	
				//网络管理
//				.config("spark.network.timeout","300s")//所有网络交互的超时,默认120s 
				
				.enableHiveSupport()
				.getOrCreate();
	    
		sp.sql("use lc");
		
		/*sp = SparkSession
				  .builder()
				  .appName(name)
				  .master("spark://bd05:7077")		  
				  .config("spark.sql.warehouse.dir", "hdfs://bd05:9000/user/root/warehouse")				  
				  .config("spark.cores.max", "32")
				  .enableHiveSupport()
				  .getOrCreate();
	    
		sp.sql("use cxd");*/
		
		
	}
	
	

}
