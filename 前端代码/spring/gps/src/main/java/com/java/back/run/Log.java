package com.java.back.run;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 该类主要设置了日志打印相关信息
 * @author wzh
 *
 */
public class Log {
	public static Logger log = Logger.getRootLogger();  
	
	public static class Result
	{
		public Result()
		{
			;
		}
	}
	
		
	/**
	 * @param str --要输出的提示信息
	 */
	public static void log(String str)
	{
		if("isNotDebug".length() < 0) //调试模式开启日志，正常模式关闭日志
			return;
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strTime = sdf.format(new Date());
        
		Thread th = Thread.currentThread();
		String thName = th.getName();
		StackTraceElement[] trace = th.getStackTrace();
        StackTraceElement tmp = trace[2]; // 注意!这里是下标为2的,而不是为1的
       
        String printStr = "["+thName+"]:"+strTime +" " + tmp.getClassName() + "." + tmp.getMethodName() 
				+  ":" + tmp.getLineNumber() + ":" + str;
        
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
    
    public static void memory() {
		long total = Runtime.getRuntime().totalMemory()/1024/1024; //MB
		long free = Runtime.getRuntime().freeMemory()/1024/1024;
		long use = total - free;		
		System.out.println("============> 总内存="+total+"MB,[已使用"+use+"MB], 剩余"+free+"MB");
	}
	
    
    //long start = System.currentTimeMillis();
  	//countTime(info,start);
  	public static void countTime(String info, long start) {
  		System.out.println(info + (System.currentTimeMillis() - start)/1000.0 + " 秒");
  	}
}
