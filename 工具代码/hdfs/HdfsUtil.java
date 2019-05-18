package tool;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

import bean.FileInfo;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * HDFS 文件系统操作类
 */
public class HdfsUtil {
	
	private static volatile HdfsUtil instance;
	public static HdfsUtil getIstance()
	{
		if(instance == null)
		{
			synchronized(HdfsUtil.class)
			{
				if(instance == null) 			
					instance = new HdfsUtil();
			}
		}
		return instance;
	}
	private HdfsUtil(){	}
	
    private static Configuration conf = new Configuration();
    private static FileSystem fs;
    private static DistributedFileSystem hdfs;
    static {
        try {
            FileSystem.setDefaultUri(conf, Msg.hadoopURL);
            fs = FileSystem.get(conf);
            hdfs = (DistributedFileSystem)fs;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    /***############################################****/
    /***############################################****/
    /***############################################****/    
       
    /**
     * 列出所有DataNode的名字信息
     * <hostname, ip>
     */
    public Map<String,String> listDataNodeInfo() 
    {
    	Map<String,String> map = new HashMap<>();

        try {
            DatanodeInfo[] dn = hdfs.getDataNodeStats();          

            for (int i=0;i<dn.length;i++)
            {
                System.out.println(dn[i].getIpAddr()+"\t"+dn[i].getHostName());
                map.put(dn[i].getHostName(), dn[i].getIpAddr());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 创建文件夹可以递归创建文件夹
     * @param dir
     * @throws IOException
     */
    public void createDir(String dir) throws IOException {
        Path path = new Path(dir);
        hdfs.mkdirs(path);
        System.out.println("new dir \t" + conf.get("fs.default.name") + dir);
    }
    

    /**
     *  删除文件
     * @param fileName
     * @throws IOException
     */
    public void deleteFile(String fileName) throws IOException {
        Path f = new Path(fileName);
        boolean isExists = hdfs.exists(f);
        if (isExists) { //if exists, delete
            boolean isDel = hdfs.delete(f,true);
            System.out.println(fileName + "  delete? \t" + isDel);
        } else {
            System.out.println(fileName + "  exist? \t" + isExists);
        }
    }

    /**
     * 查看文件是否存在
     */
    public boolean checkFileExist(String filePath) {
        boolean exist=false;
        try {
            Path a= hdfs.getHomeDirectory();
            System.out.println("main path:"+a.toString());

            //Path f = new Path("/user/xxx/input01/");
            Path f = new Path(filePath);
            exist = fs.exists(f);
            System.out.println("Whether exist of this file:"+exist);

            //删除文件
//          if (exist) {
//              boolean isDeleted = hdfs.delete(f, false);
//              if(isDeleted) {
//                  System.out.println("Delete success");
//              }
//          }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exist;
    }

    /**
     *创建文件到HDFS系统上
     */
    public void createFile(String fileLocation,String text) {
        try {
            //Path f = new Path("/user/1.txt");
            Path f = new Path(fileLocation);
//            System.out.println("Create and Write :"+f.getName()+" to hdfs");

            FSDataOutputStream os = fs.create(f, true);
            Writer out = new OutputStreamWriter(os, "utf-8");//以UTF-8格式写入文件，不乱码
            out.write(text);
            out.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取本地文件到HDFS系统<br>
     * 请保证文件格式一直是UTF-8，从本地->HDFS
     */
    public void copyFileToHDFS(String SouceFilePath,String distinctFilePath) {
        try {
            Path f = new Path(distinctFilePath);
            File file = new File(SouceFilePath);

            FileInputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);

            FSDataOutputStream os = fs.create(f, true);
            Writer out = new OutputStreamWriter(os, "utf-8");

            String str = "";
            while((str=br.readLine()) != null) {
                out.write(str+"\n");
            }
            br.close();
            isr.close();
            is.close();
            out.close();
            os.close();
            System.out.println("Write content of file "+file.getName()+" to hdfs file "+f.getName()+" success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件到HDFS上 hdfs上保存数据块
     * @param localFile 本地文件地址
     * @param hdfsFile hdfs文件位置
     * @param buffersize 每一个数据块的大小
     * @throws IOException
     */
    public void copyFileToHDFSBlock(String localFile, String hdfsFile,String buffersize) throws IOException{
        conf.set("dfs.block.size", buffersize);//第二个参数的单位是字节，并且是字符串形式
        FileSystem fs=FileSystem.get(conf);
        Path src=new Path(localFile);//参数是本地文件的绝对路径的字符串形式
        Path dst=new Path(hdfsFile);
        fs.copyFromLocalFile(src,dst);
        System.out.println("upload to:"+conf.get("fs.default.name"));
    }
    

    /**
     * 读取hdfs中的文件内容
     */
    public void readFileFromHdfs(String filePath) {
        try {
            Path f = new Path(filePath);

            FSDataInputStream dis = fs.open(f);
            InputStreamReader isr = new InputStreamReader(dis, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String str = "";
            while ((str = br.readLine()) !=null) {
                System.out.println(str);
            }
            br.close();
            isr.close();
            dis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***############################################****/
    /***############################################****/
    /***############################################****/
    
    /**
     * 计算目录的大小
     */
    public String countFile(String path)
    {
    	String ff="";
    	try{
	    	Path pth = new Path(path);
	    	long f = fs.getContentSummary(pth).getLength();	
	    	ff = changByte(f);	    
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return ff;
    }
   
    /***********************************************/
    
    /**高性能无阻塞无界队列：ConcurrentLinkedQueu 是 线程安全的并发Queue
     *  add()和offer()都是加入元素,没有区别
	 *	poll()取出队头元素，删除元素
	 *  peek()取出队头元素，不删。
     */
    public ConcurrentLinkedQueue<FileInfo> QueueFileInfo(String dirName)
    {    	
    	ConcurrentLinkedQueue<FileInfo> que = new ConcurrentLinkedQueue<>();
    	try {
    		Path f = new Path(dirName);
			FileStatus[] status = fs.listStatus(f);
			for (int i = 0; i< status.length; i++) 
			{		              
		        String str =  status[i].getPath().toString();
            	str = str.substring(str.indexOf("/user"));//去掉hdfs://cqhadoop01:9000，从根目录开始
            	if(!str.contains("_SUCCESS"))
            	{
            		FileInfo fi = new FileInfo(); 
            		fi.path = str;
            		fi.size = changByte(status[i].getLen());
            		
            		BlockLocation[] locas = fs.getFileBlockLocations(status[i], 0, status[i].getLen());            		
            		String[] hosts = locas[0].getHosts();
            		fi.location = hosts[0];  
            		
            		que.add(fi);
            	}	
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return que;
    }
    
    /**只有 1个副本的 情况，不递归子目录，不适用与分区表
     * 取得某个表目录下所有文件【路径，大小，块位置】 
     */
    public List<FileInfo> getFileInfo(String dirName)
    {
    	List<FileInfo> list = new ArrayList<>();
    	try {
    		Path f = new Path(dirName);
			FileStatus[] status = fs.listStatus(f);
			for (int i = 0; i< status.length; i++) 
			{		              
		        String str =  status[i].getPath().toString();
            	str = str.substring(str.indexOf("/user"));//去掉hdfs://cqhadoop01:9000，从根目录开始
            	if(!str.contains("_SUCCESS"))
            	{
            		FileInfo fi = new FileInfo(); 
            		fi.path = str;
            		fi.size = changByte(status[i].getLen());
            		
            		BlockLocation[] locas = fs.getFileBlockLocations(status[i], 0, status[i].getLen());            		
            		String[] hosts = locas[0].getHosts();
            		fi.location = hosts[0];  
            		
            		list.add(fi);
            	}	
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return list;
    }
    
    /**输入文件的 路径
     * 取得文件块所在的位置.. 3个副本
     */
    public List<String> getBolockHosts(String filePath) {
        List<String> list=new ArrayList<>();
        try {
            Path f = new Path(filePath);
            FileStatus fileStatus = fs.getFileStatus(f);

            BlockLocation[] blkLocations = fs.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());          
            for (BlockLocation currentLocation : blkLocations)
            {
                String[] hosts = currentLocation.getHosts();
                for (String host : hosts)
                {
                    list.add(host);
                    System.out.println(host);
                }
            }

            //取得最后修改时间
           /* long modifyTime = fileStatus.getModificationTime();
            Date d = new Date(modifyTime);
            System.out.println("最后修改时间:"+d);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    /**
     * 列出当前目录下所有文件，不进入子目录
     * @param dirName
     * @throws IOException
     */
    public List<String> listFiles(String dirName) throws IOException {
    	List<String> list = new ArrayList<String>();
    	
    	Path f = new Path(dirName);
        FileStatus[] status = hdfs.listStatus(f);
        for (int i = 0; i< status.length; i++)
        {
            String str =  status[i].getPath().toString();
        	str = str.substring(str.indexOf("/user"));//去掉hdfs://cqhadoop01:9000，从根目录开始
        	if(!str.contains("_SUCCESS"))
        			list.add(str);
        	
        	System.out.println(str+ "\tsize:"+ changByte(status[i].getLen()));
        }
        
        return list;
    }
    
    
    /**递归所有子目录
     * list all file/directory
     * @throws IOException
     * @throws IllegalArgumentException
     * @throws FileNotFoundException
     */
    public List<String> listFileStatus(String path) throws FileNotFoundException, IllegalArgumentException, IOException {
    	List<String> list = new ArrayList<String>();
    	
    	FileStatus[] fileStatus = fs.listStatus(new Path(path));
        int listlength = fileStatus.length;
        
        for (int i=0; i<listlength; i++)
        {
            if (fileStatus[i].isDirectory() == false)
            {            	
            	String str =  fileStatus[i].getPath().toString();
            	str = str.substring(str.indexOf("/user"));//去掉hdfs://cqhadoop01:9000，从根目录开始
            	if(!str.contains("_SUCCESS"))
            			list.add(str);
            	
                System.out.println(str+ "\tsize:"+ changByte(fileStatus[i].getLen()));
//     			  /user/spark/warehouse/lc.db/a/part-00000-c000.snappy.parquet	size:869.57 K
            }
            else 
            {
                String newpath = fileStatus[i].getPath().toString();
                listFileStatus(newpath);
            }
        }
        
        return list;
    }
    
    
    /**字节转换为KB、MB、GB
     * @Title: getPrintSize 
     * @param size 长整型字节数
     * @return 字符串型
     */
    public String changByte(long lsize)
    {  
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义  
    	double size = (double)lsize;     	
    	if (size < 1024) 
        {  
            return String.valueOf(size) + " B";  
        } 
        else  
            size = size / 1024;   
    	
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位  
        //因为还没有到达要使用另一个单位的时候  
        //接下去以此类推  
        if (size < 1024) 
        {   
            String num = String.format("%.2f", size);
        	return num+" KB";
        } 
        else  
            size = size / 1024;
        
        if (size < 1024)
        {  
        	String num = String.format("%.2f", size);
        	return num+" MB";
        } 
        else   
        	size = size / 1024;    
        
        if (size < 1024) 
        {  
        	String num = String.format("%.2f", size);
        	return num+" GB";
        } 
        else 
        {  
            size = size / 1024;
            String num = String.format("%.2f", size);
        	return num+" TB";
        } 
    }

}

