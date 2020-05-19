package ck4g.tool;

import java.util.List;
import java.util.Map;

import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.storage.StorageLevel;

import ck4g.control.Config;

/**
 * 此类定义了spark入口SparkSession的相关信息
 * @author cxd
 * @author wzh
 * @author lz
 */
public class SparkApi {
	private SparkSession spark;

	/**
	 * 在构造函数中对SparkSession进行了二次封装，配置了相关信息
	 */
	private SparkApi() {
		
		/*
		 * spark Config的相关配置信息
		 */
		Log.log(Config.appname);//打印出程序名称
		Log.log(Config.dbmaster);//打印出spark的master
		Log.log(Config.dbwarehouse);//打印出数据仓库的信息
		Log.log(Config.sparkCores+"");//打印出运行时候使用多少核的信息
		
		spark = SparkSession
				.builder()
				.appName(Config.appname)//指定本程序的名称
				.master(Config.dbmaster)//设置spark的master
				.config("spark.sql.warehouse.dir",Config.dbwarehouse)//设置数据仓库的信息
				.config("spark.scheduler.mode", "FAIR")
				.config("spark.speculation",true)//开启推测机制	
				.config("spark.sql.inMemoryColumnarStorage.compressed",false)
				.config("spark.shuffle.consolidateFiles",true)
				.config("spark.serializer","org.apache.spark.serializer.KryoSerializer")//指定序列化方式
				.config("spark.cores.max", String.valueOf(Config.sparkCores))//指定运行时使用的最大核心数
				.config("spark.executor.memory", "2g")//指定计算节点的内存为2G
				.enableHiveSupport()
				.getOrCreate();
		
		spark.sql("use " + Config.dbname);//使用数据库4gcheck02
	}

	private static volatile SparkApi instance;
	public static SparkApi getIstance() {

		if (instance == null) {

			synchronized (SparkApi.class) {
				if (instance == null) {
					instance = new SparkApi();
				}
			}
		}
		return instance;
	}
	
	public void close() {
		spark.stop();
	}

	/**
	 * 获取结果集中第一条记录
	 * @param sql
	 * @return 结果集中的第一条记录
	 */
	public Row getFirstRow(String sql) {
		Row row = null;
		try {
			row = spark.sql(sql).cache().first();
		} catch (Exception e) {
			row = null;
			Log.log("row is null . sql = " + sql);
		}
		return row;
	}

	/**
	 * 
	 * @param sql
	 * @return
	 */
	public boolean isDataEmpty(String sql) {
		Row row = null;
		try {
			row = spark.sql(sql).cache().first();
		} catch (Exception e) {
			row = null;
			Log.log("row is null . sql = " + sql);
		}
		if (row != null)
			return true;
		else
			return false;

	}

	/**
	 * 根据一条sql查询语句去执行查询，最后返回一个集合，不去重。
	 * @param sql 要执行查询的sql语句
	 * @return List<Row>集合，没有去除重复的元素。
	 */
	public List<Row> Query(String sql) {
		
		return spark.sql(sql).cache().collectAsList();// collect花费时间很多
	}
	
	/**
	 * 根据一条sql查询语句去执行查询，最后返回一个集合，不去重，但进行了排序。
	 * @param sql 要执行查询的sql语句
	 * @param sort 进行排序
	 * @return List<Row>集合，没有去除重复的元素，但对元素进行了排序。
	 */
	public List<Row> Query(String sql,String sort) {
		return spark.sql(sql).cache().orderBy(sort).collectAsList();// collect花费时间很多
	}

	/**
	 * 根据一条sql查询语句去执行查询，对查询元素进行去重操作
	 * @param sql 要执行查询的sql语句
	 * @return List<Row>集合，但集合中的元素去重了
	 */
	public List<Row> Runsql(String sql) {
		return spark.sql(sql).cache().distinct().collectAsList();// collect花费时间很多
	}
	
	/**
	 * 根据一条sql查询语句去执行查询，对查询元素进行了去重和排序
	 * @param sql  要执行查询的sql语句
	 * @param sort 对元素进行排序
	 * @return List<Row>集合，但集合中的元素经过去重和排序了
	 */
	public List<Row> Runsql(String sql,String sort) {
			
		return spark.sql(sql).cache().distinct().orderBy(sort).collectAsList();// collect花费时间很多
	}

	/**
	 * 执行一条sql语句
	 * @param sql 传入的一条sql语句
	 */
	public void Execute(String sql) {
		spark.sql(sql);	
	}

	
	/**
	 * 根据一条sql语句缓存一张临时表
	 * @param sql 传入的一条sql语句
	 * @param tbName 设置临时表的表名
	 * @return 根据传入的sql语句得到的数据集Dataset
	 */
	public Dataset<Row> CacheTable(String sql,String tbName)
	{
		Dataset<Row> df = spark.sql(sql);		
		try
		{
			df.createTempView(tbName);
			df.cache();
		} 
		catch (AnalysisException e)
		{
			Log.log(sql);
			Log.log(e.toString());
		}
		return df;
	}
	
	/**
	 * 删除一张临时表
	 * @param tbName 删除临时表的表名
	 */
	public void unCacheTable(String tbName)
	{
		spark.catalog().dropTempView(tbName);
	}

	/**
	 * 根据一条sql查询语句创建一个DataFrame并使用MEMORY_AND_DISK_SER()作为持久化策略
	 * @param sql 传入的一条sql语句
	 * @return 根据传入的sql语句得到的数据集Dataset
	 */
	public Dataset<Row> GetDataFrame(String sql)
	{
		return spark.sql(sql).persist(StorageLevel.MEMORY_AND_DISK_SER());
	}
	
	/**
	 * 根据一条sql查询语句创建一个DataFrame并不做cache缓存
	 * @param sql 传入的一条sql语句
	 * @return 根据传入的sql语句得到的数据集Dataset
	 */
	public Dataset<Row> GetDataFrameNoCache(String sql)
	{
		return spark.sql(sql);
	}
	
	/**
	 * 创建一个DataFrame并使用repartiton(1)将所有小分区文件合并成一个大文件
	 * @param data
	 * @param c
	 * @return
	 */
	public Dataset<Row> createDataFrame(List<?> data,Class<?> c)
	{
		return spark.createDataFrame(data,c).repartition(1);
	}

	/**
	 * 使用jdbc读取关系型数据库（这里读取的是mysql）中的数据
	 * @param options 一个Map，里面封装了连接数据库的url，password，username，tableName等信息
	 * @return 数据库中一张表的数据集Dataset
	 */
	public Dataset<Row> readJDBC(Map<String, String> options)
	{
		return spark.read().format("jdbc").options(options).load();
	}
	
}
