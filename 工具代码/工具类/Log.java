package tool;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 该类主要设置了日志打印相关信息
 * @author luozhao
 *
 */
public class Log {
	public static Logger log = Logger.getRootLogger();  
	
		
	/**
	 * @param str --要输出的提示信息
	 */
	public static void log(String str)
	{
		DateFormat sdf = new SimpleDateFormat("HH:mm:ss");//yyyy-MM-dd HH:mm:ss
		String strTime = sdf.format(new Date());
        
		Thread th = Thread.currentThread();
		String thName = th.getName();
		StackTraceElement[] trace = th.getStackTrace();
        StackTraceElement tmp = trace[2]; // 注意!这里是下标为2的,而不是为1的
       
//        String printStr = "["+thName+"]:"+strTime +" " + tmp.getClassName() + "." + tmp.getMethodName() 
//						+  ":" + tmp.getLineNumber() + ":" + str;
        //[main]:2018-10-09 19:05:25 watch.ComputeMem.getSample:64:ds.count()= 249111
        
        String printStr = strTime+" " +tmp.getClassName()+":"+tmp.getLineNumber()+":"+str;
        
        System.out.println(printStr);
      
	}
	
	 /**
	  * 写文件操作
     * @param content --内容
     * @param filename --文件名
     */
	public static void writeFile(String content, String filename) {
		PrintWriter pout = null;		
		try {
			pout = new PrintWriter(new FileOutputStream(filename,true),true);
			pout.println(content);//输出到文件
			
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} finally {
			try {
				pout.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
	}
	
	
	public static void logDebug(String message) {  
        log.debug(message);  
    }  
  
    public static void logInfo(String message) {  
        log.info(message);  
    }  
  
    public static void logWarn(String message) {  
        log.warn(message);  
    }  
  
    public static void logError(String message) {  
        log.error(message);  
    }  
    
    public static long restMem() {
    	long max = Runtime.getRuntime().maxMemory();
		long total = Runtime.getRuntime().totalMemory(); //MB
		long free = Runtime.getRuntime().freeMemory();
		long used = total - free;
		
		System.out.println("totalMem="+changByte(total)+"  maxMem="+changByte(max)
				+"	usedMem="+changByte(free)+"  freeMem="+changByte(used));	
		return free;
	}
    
    //java.lang.management监视和管理 Java 虚拟机
    public static long getGCinfo(){
    	long t=0L;
    	for (GarbageCollectorMXBean gc : ManagementFactory.getGarbageCollectorMXBeans())
		{
            long count = gc.getCollectionCount();
            long time = gc.getCollectionTime();
            String name = gc.getName();
            t += time;
            System.out.println(String.format("%s: %s times %s s", name, count, time/1000.0));
        }
    	
    	return t;   	
    }
    
    /**字节转换为KB、MB、GB
     * @Title: getPrintSize 
     * @param size 长整型字节数
     * @return 字符串型
     */
    public static String changByte(long lsize)
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
