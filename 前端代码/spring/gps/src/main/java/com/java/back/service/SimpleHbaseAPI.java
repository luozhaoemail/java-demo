package com.java.back.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


//import org.apache.hadoop.hbase.*;
//import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

/**
 java -cp hb.jar:$PNX_HOME/phoenix-4.12.0-HBase-1.2-client.jar com.java.controller.admin.check.SimpleHbaseAPI
 */
public class SimpleHbaseAPI {
    public static Configuration conf;
    public static Connection connection;
    public static Admin admin;
   
    static{
    	init();
    }    

    //初始化链接
    public static void init(){
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","localhost");//Constant.canalIP   
        conf.set("hbase.zookeeper.property.clientPort","2181");
        conf.set("zookeeper.znode.parent","/hbase");

        try {
            connection = ConnectionFactory.createConnection(conf);
            admin = connection.getAdmin();
            System.out.println("==== Hbase 连接成功！！！");
        } catch (IOException e) {
        	  System.out.println("==== Hbase 连接失败！！！");
            e.printStackTrace();
        }
    }

    //关闭连接
    public static void close(){
        try {
            if(null != admin)
                admin.close();
            if(null != connection)
                connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //建表
    public static String createTable(String tableName, String[] cols) throws IOException {        
        TableName tbName = TableName.valueOf(tableName);

        if(admin.tableExists(tbName))
        {
            return tableName+"已经存在!";
        }
        else 
        {
            HTableDescriptor tbdesc = new HTableDescriptor(tbName);
            for(String col: cols)
            {
                HColumnDescriptor ColumnDesc = new HColumnDescriptor(col);
                tbdesc.addFamily(ColumnDesc);
            }
            admin.createTable(tbdesc);
            return tableName+"创建成功!";
        }       
    }

    //删表
    public static String deleteTable(String tableName) throws IOException {
        
        TableName tn = TableName.valueOf(tableName);
        if (admin.tableExists(tn)) 
        {
            admin.disableTable(tn);
            admin.deleteTable(tn);
        }
        return "删除成功!";
    }

    //查看已有表
    public static List<String> listTables() throws IOException {
        
        HTableDescriptor hTableDescriptors[] = admin.listTables();        
        //System.out.println("listTables-------------\n");
        List<String> list = new ArrayList<String>();
        for(HTableDescriptor hTableDescriptor :hTableDescriptors)
        {
        	list.add(hTableDescriptor.getNameAsString());
        	//System.out.println(hTableDescriptor.getNameAsString());
        }
        return list;
    }

    //插入数据
    public static void instersingleRow(String tableName,String rowkey,String colFamily,String col,String val) throws IOException {
        
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowkey));
        put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col), Bytes.toBytes(val));
        table.put(put);
        
        table.close();
        //close();
    }
    
    /** 批量插入，遍历map，将里面的字段和值插入habse
     * @Title: insterBatchRow 
     * @param tableName
     * @param rowkey
     * @param colFamily  
     * @param map  <字段，值>
     * @throws IOException
     */
    public static void insterBatchRow(String tableName,String rowkey,String colFamily,Map<String,String> map) throws IOException {
        
        Table table = connection.getTable(TableName.valueOf(tableName));
        List<Put> putList = new ArrayList<Put>();
        Put put = new Put(Bytes.toBytes(rowkey));   
        for(Map.Entry<String, String> entry: map.entrySet())
        {
        	 System.out.println("Key: "+ entry.getKey()+ " Value: "+entry.getValue());
        	 put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(entry.getKey()), Bytes.toBytes(entry.getValue()));
        	 putList.add(put);
        }
        
        //批量插入
        table.put(putList);
        //table.close();
        //close();
    }
    

    //删除数据
    public static void deleRow(String tableName,String rowkey,String colFamily,String col) throws IOException {
        
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowkey));
        //删除指定列族
        //delete.addFamily(Bytes.toBytes(colFamily));
        //删除指定列
        //delete.addColumn(Bytes.toBytes(colFamily),Bytes.toBytes(col));
        table.delete(delete);
        //批量删除
       /* List<Delete> deleteList = new ArrayList<Delete>();
        deleteList.add(delete);
        table.delete(deleteList);*/
        //table.close();
       // close();
    }

    //根据rowkey查找数据
    public static void getData(String tableName,String rowkey,String colFamily,String col)throws  IOException{
        
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowkey));
        //获取指定列族数据
        //get.addFamily(Bytes.toBytes(colFamily));
        //获取指定列数据
        //get.addColumn(Bytes.toBytes(colFamily),Bytes.toBytes(col));
        Result result = table.get(get);

        showCell(result);
        table.close();
        //close();
    }
    
    
  //批量查找数据
    public void scanData(String tableName)throws IOException{
    	Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
//        scan.setStartRow(Bytes.toBytes(startRow));
//        scan.setStopRow(Bytes.toBytes(stopRow));
        ResultScanner resultScanner = table.getScanner(scan);
        String str="cf1:name:sex|cf2:adress:age";
		Map<String,List<String>> schemaMap=null;// = rr.getSchema(str);		
				
        List<String> list = new ArrayList<String>();
        for(Result result : resultScanner)
        {        	 
     		String rowkey = Bytes.toString(result.getRow());
     		for(Map.Entry<String,List<String>> entry: schemaMap.entrySet())
     		{  
     		     String cf = entry.getKey();//cf  
     		     for(String col : entry.getValue())//columns
     		     { 
     		    	rowkey += "  "+Bytes.toString(result.getValue(Bytes.toBytes(cf), Bytes.toBytes(col)));     		    	
     		     }     		    
             }  
     		list.add(rowkey);     		
        }
        table.close();
        close();
        
        System.out.println("list.size()="+list.size());
		for(int i=0;i<10;i++)
			 System.out.println(list.get(i));
    }

    //批量查找数据
    public static void scanData(String tableName,String startRow,String stopRow)throws IOException{
        
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(startRow));
        scan.setStopRow(Bytes.toBytes(stopRow));
        ResultScanner resultScanner = table.getScanner(scan);     
				
        for(Result result : resultScanner)
        {  
        	showCell(result);
        }
        table.close();
        //close();
    }
    
    //格式化输出
    public static void showCell(Result result){
        Cell[] cells = result.rawCells();
        for(Cell cell:cells){
            System.out.println("RowName:"+new String(CellUtil.cloneRow(cell))+" ");
            System.out.println("Timetamp:"+cell.getTimestamp()+" ");
            System.out.println("column Family:"+new String(CellUtil.cloneFamily(cell))+" ");
            System.out.println("row Name:"+new String(CellUtil.cloneQualifier(cell))+" ");
            System.out.println("value:"+new String(CellUtil.cloneValue(cell))+" ");
            System.out.println("-----------------");
        }
    }
    
    
    public static void main(String[] args) throws IOException {  
    	//SimpleHbaseAPI hb = new SimpleHbaseAPI();
    	System.out.println("===list all tables");
    	listTables(); 
    	
    	System.out.println("===创建");
    	createTable("test",new String[]{"cf"});   
    	
    	System.out.println("===插入");
    	instersingleRow("test", "rw1", "cf", "sno", "11010110");
    	instersingleRow("test", "rw2", "cf", "sname", "luozhao");
    	
    	System.out.println("===查询");
        getData("test", "rw1", "cf", "sno");
        
        System.out.println("===扫描");
        scanData("test", "rw1", "rw2");
        System.out.println("===删除");
        deleRow("test","rw1","cf1","q1");
        //deleteTable("test");
    }
    
}
