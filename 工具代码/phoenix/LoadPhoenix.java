package hbase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.phoenix.jdbc.PhoenixConnection;
import org.apache.phoenix.spark.PhoenixRecordWritable;

public class LoadPhoenix
{
	public static PhoenixConnection getConnection() 
	{
		try {
			Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
			PhoenixConnection conn = (PhoenixConnection) DriverManager.getConnection("jdbc:phoenix:a1,a2,a3,a4", "root", "123456");
			return conn;
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return null;
	}
	
	//1	phoenix sql 单线程批量插入
	public static void batchInsert(String tbname)
	{
		PhoenixConnection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			int upsertBatchSize = conn.getMutateBatchSize();
			System.out.println("批量upsertBatchSize= "+upsertBatchSize);
			String upsertStatement = "upsert into "+tbname+" values(?,?,?,?,?)";//设置占位符的值 
			stmt = conn.prepareStatement(upsertStatement);
			int rowCount = 0;
			for(int i=1; i<=1000000; i++)
			{			   
			    Random randm = new Random();		        
			    String id = UUID.randomUUID().toString();
		        String name = "name_"+i;
		        String sex = EnumRandom.getGenderEnum();
		        String age = randm.nextInt(100)+"";
		        String adress = EnumRandom.getCityEnum();
		        
		        stmt.setString(1, id);
		        stmt.setString(2, name);
		        stmt.setString(3, sex); 
		        stmt.setString(4, age);
		        stmt.setString(5, adress); 
			    stmt.execute();		
			    
			    if (++rowCount % upsertBatchSize == 0)
			    {
	              conn.commit();
	              //System.out.println("—————————Rows upserted: " + rowCount);
			    }
			}
			conn.commit();///////////一批循环之后再提交			
			
		}catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			try{				
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
		}//finally
	}//batchInsert
	
	
	//2	phoenix sql 多线程批量插入
	public static void multiThreadImport(String tbname, final int ThreadNum){		
        final CountDownLatch cdl= new CountDownLatch(ThreadNum);
        long starttime=System.currentTimeMillis();
        for(int k=1;k<=ThreadNum;k++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                	PhoenixConnection conn = null;
            		PreparedStatement stmt = null;                	
                    try {
                    	conn = getConnection();
            			conn.setAutoCommit(false);
            			int upsertBatchSize =conn.getMutateBatchSize(); 
            			System.out.println("批量upsertBatchSize= "+upsertBatchSize);//100最快
            			String upsertStatement = "upsert into "+tbname+" values(?,?,?,?,?)";//设置占位符的值
            			stmt = conn.prepareStatement(upsertStatement);
            			
//            	        PhoenixRecordWritable wr = new PhoenixRecordWritable();
//            	        wr.write(stmt);
            	        
            			int rowCount = 0;
                        for(int i=1;i<=1000000/ThreadNum;i++)
                        {                                 	
                        	Random randm = new Random();	
            		        String id = UUID.randomUUID().toString();
            		        String name = "name_"+i;
            		        String sex = EnumRandom.getGenderEnum();
            		        String age = randm.nextInt(100)+"";
            		        String adress = EnumRandom.getCityEnum();
            		        
            		        stmt.setString(1, id);
            		        stmt.setString(2, name);
            		        stmt.setString(3, sex); 
            		        stmt.setString(4, age);
            		        stmt.setString(5, adress); 
            			    stmt.execute();		
            			    
            			    if (++rowCount % upsertBatchSize == 0)
            			    {
            	              conn.commit();
            	              //System.out.println("—————————Rows upserted: " + rowCount);
            			    }
                        }
                        conn.commit();///////////一批循环之后再提交	
                        cdl.countDown();
                    } catch (Exception e) {
                    }finally{
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        try {
            cdl.await();
            long spendtime=System.currentTimeMillis()-starttime;
            System.out.println( ThreadNum+"个线程花费时间:"+spendtime/1000.0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }//end multiThreadImport
	
		
	//3	使用Hbase原生API,线程池批量导入数据
    public static void hbaseThreadImport(String tbname)
    {    	
    	//stu表结构
    	byte[] cf1 =  Bytes.toBytes("cf1");
    	byte[] cf2 =  Bytes.toBytes("cf2");
    	byte[] col_name =  Bytes.toBytes("name");
    	byte[] col_sex =  Bytes.toBytes("sex");
    	byte[] col_age =  Bytes.toBytes("age");
    	byte[] col_adress =  Bytes.toBytes("adress");
    	
    	List<Put> list = new ArrayList<Put>();
    	for(int i=1; i<=1000000; i++)
    	{   
    		//一行数据
    		Random randm = new Random();		        
    		String id = UUID.randomUUID().toString();
	        String name = "name_"+id;
	        String sex = EnumRandom.getGenderEnum();
	        String age = randm.nextInt(100)+"";
	        String adress = EnumRandom.getCityEnum();
    		
	        //转换为hbase的4行,每行格式：rowkey,cf,column,value    		
    		byte[] rowkey = Bytes.toBytes(id);    		
			byte[] val_name = Bytes.toBytes(name);
			byte[] val_sex = Bytes.toBytes(sex);
			byte[] val_age = Bytes.toBytes(age);		
			byte[] val_adress = Bytes.toBytes(adress);
						
			Put put = new Put(rowkey);
    		put.addColumn(cf1, col_name, val_name);
    		put.addColumn(cf1, col_sex, val_sex);
    		put.addColumn(cf2, col_age, val_age);
    		put.addColumn(cf2, col_adress, val_adress);	
    		list.add(put);
    		
    	}
    	
    	HBaseUtil.init();
    	HBaseServiceImpl hb = new HBaseServiceImpl();
    	
    	hb.batchPut(tbname, list, true);//		同步
    	//hb.batchAsyncPut(tbname, list, true);// 		异步   
    }
	
    
    //生成数据文件：rowkey,cf,column,value 
    public static void lineToRowkey()
    {
    	//stu表结构
    	String col_name = ",cf1,name,";
    	String col_sex = ",cf1,sex,";
    	String col_age = ",cf2,age,";
    	String col_adress = ",cf2,adress,"; 
    	
    	FileWriter fw = null;
    	BufferedWriter w = null;
    	try {
			fw = new FileWriter("/root/stu_data.txt");//创建文件写入流
			w = new BufferedWriter(fw); //将流对象传给缓冲区		
    	
	    	for(int i=1; i<=1000000; i++)
	    	{   
	    		//一行数据
	    		Random randm = new Random();		        
		        String id = i+"";
		        String name = "name_"+i;
		        String sex = EnumRandom.getGenderEnum();
		        String age = randm.nextInt(100)+"";
		        String adress = EnumRandom.getCityEnum();
		        
		        //变成4行
		        String line1 =	id + col_name + name;
		        String line2 =	id + col_sex + sex;
		        String line3 =	id + col_adress + adress;
		        String line4 =	id + col_age + age;	
		        
		        w.write(line1);
		        w.newLine();
		        w.write(line2);
		        w.newLine();
		        w.write(line3);
		        w.newLine();
		        w.write(line4);
		        w.newLine();
	    	
	    	}//for
	        w.flush();
    	
    	} catch (IOException e) {			
			e.printStackTrace();
		}finally{
			try {
				w.close();
				fw.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}			
		}//finally
    	
    }//lineToRowkey
    
    
    
    //4	多线程批量分页
    public static void hbaseMultiInsert(int dataNum,int ThreadNum) throws InterruptedException 
    {	
		  if(dataNum%ThreadNum!=0)//如果不能整除，则要多跑一轮
			  ThreadNum ++; 
    	  int pageSize  = dataNum/ThreadNum;//分页思想：每轮执行多少数据
		  int start = 1;
		  int end = 1;
		  //同步器
		  final CountDownLatch cdl = new CountDownLatch(ThreadNum);
		  long starttime=System.currentTimeMillis();
		  for(int k=1;k<=ThreadNum;k++)
		  {
			  start = (k-1)*pageSize+1;
			  end = start+pageSize-1;
			  if(end>=dataNum)//最后一轮数据未满
		        	end = dataNum;
			  
			  //extends Thread这样调用
			  new MyThread(cdl,start,end).start();
			  
			 /*
			  //MyThread implements Runnable这样调用
			  MyThread my = new MyThread(start,end);			  
			  Thread thread = new Thread(my); 
			  thread.start();
			 */
		  }
		  try {
		      cdl.await();
		      long spendtime=System.currentTimeMillis()-starttime;
		      System.out.println( ThreadNum+"个线程花费时间:"+spendtime/1000.0);
		  } catch (InterruptedException e) {
		      e.printStackTrace();
		  }	  
		
	}//hbaseMultiInsert
    	
	
}//class


class MyThread extends Thread// implements Runnable
{
	int start;
	int end;
	CountDownLatch cdl;

	MyThread(){}
	
	MyThread(CountDownLatch cd ,int i, int j)
	{
		cdl = cd;
		start = i;
		end = j;
	} 
	
	@Override
	public void run() {				
		try {
			bigput(start, end);
			cdl.countDown();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void bigput(int start, int end) throws Exception 
    {
    	//stu表结构
    	Configuration conf = HBaseConfiguration.create();  
        conf.set("hbase.zookeeper.quorum","lyy1,lyy2,lyy3,lyy4");
        conf.set("hbase.zookeeper.property.clientPort","2181");
        conf.set("zookeeper.znode.parent","/hbase");
        Connection conn = ConnectionFactory.createConnection(conf);  
    	Table table = conn.getTable(TableName.valueOf("stu"));
    	byte[] cf1 =  Bytes.toBytes("cf1");
    	byte[] cf2 =  Bytes.toBytes("cf2");
    	byte[] col_name =  Bytes.toBytes("name");
    	byte[] col_sex =  Bytes.toBytes("sex");
    	byte[] col_age =  Bytes.toBytes("age");
    	byte[] col_adress =  Bytes.toBytes("adress");
    	
    	List<Put> list = new ArrayList<Put>();
		//关闭自动清理缓冲区
		for (int i = start; i <= end; i++) 
		{
    		//一行数据
    		Random randm = new Random();		        
    		String id = UUID.randomUUID().toString();
	        String name = "name_"+i;
	        String sex = EnumRandom.getGenderEnum();
	        String age = randm.nextInt(100)+"";
	        String adress = EnumRandom.getCityEnum();
    		
	        //转换为hbase的4行,每行格式：rowkey,cf,column,value    		
    		byte[] rowkey = Bytes.toBytes(id);    		
			byte[] val_name = Bytes.toBytes(name);
			byte[] val_sex = Bytes.toBytes(sex);
			byte[] val_age = Bytes.toBytes(age);		
			byte[] val_adress = Bytes.toBytes(adress);
			
    		Put put = new Put(rowkey);
    		put.addColumn(cf1, col_name, val_name);
    		put.addColumn(cf1, col_sex, val_sex);
    		put.addColumn(cf2, col_age, val_age);
    		put.addColumn(cf2, col_adress, val_adress);	
    		list.add(put);			
		}
		
		//清理提交
		table.put(list);
		table.close();		
	}
	
} 


class EnumRandom
{   	
	enum Gender
	{
		男,
		女
	}
	static int conut_gender = Gender.values().length;
	
	enum City
	{
		北京,
		上海,
		广州,
		成都,
		深圳,
		南京,
		重庆,
		天津,
		武汉,
		哈尔滨
	}
	static int count_city = City.values().length;
	
	public static String getGenderEnum()
	{
		Random random = new Random();  
		Gender[] gen = Gender.values();
		Gender h = gen[random.nextInt(conut_gender)];
		//System.out.println(h.name()+" "+h.ordinal());
		return h.toString();
	}
	
	public static String getCityEnum()
	{
		Random random = new Random();  
		City[] city = City.values();
		City c = city[random.nextInt(count_city)];
		//System.out.println(c.name()+" "+c.ordinal());
		return c.toString();
	}
	
}
