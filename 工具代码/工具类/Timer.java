package ck4g.tool;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 功能描述：Timer类用作对时间做转换处理，包括时间戳和指定时间格式之间的相互转换。
 * @author cxd
 * @author wzh
 * @author lz
 */
public class Timer {
	
	/**
	 * 此函数的功能是将一个时间类型为yyyy/MM/dd HH:mm:ss的时间转换成时间戳。
	 * @param s 调用此参数时传入的参数，类型为String，表示时间，其格式类型为yyyy/MM/dd HH:mm:ss。
	 * @return 若传入时间格式正确，则返回该时间的时间戳，类型为long，长度为13位，精确到毫秒，否则返回0。
	 */
    public static long dateToStamp(String timeStr) {
    
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
        	Date date = SDF.parse(timeStr); 
        	return date.getTime();
    	} catch(Exception e) {
    		e.printStackTrace();
        }
        
        return 0;
    }
    
    /**
     * 此函数功能是将一个时间戳转换成yyyy/MM/dd HH:mm:ss格式的时间。
     * @param timestamp 调用此函数时传入的时间戳。
     * @return 传入时间戳的yyyy/MM/dd HH:mm:ss格式的时间，类型为String。
     */
    public static String stampToDate(Long timestamp) {
        
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");     
        Date date = new Date(timestamp);
        return SDF.format(date);
    }
   
    /**
     * 获取一个时间戳中的hour值，即一天中的某一个小时
     * @param timestamp 调用此函数时传入的参数，为一个long型的时间戳。
     * @return 传入时间戳的小时值，类型为String。
     */
   
    public static String stampToDateH(Long timestamp){
        
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH");     
        Date date = new Date(timestamp);
        return SDF.format(date);
    }

    /**
     * 获取一个时间戳中的日期值，即一个月中的某一天
     * @param timestamp
     * @return 传入时间戳的日期值，类型为String。
     */
	public static String stampToDateD(Long timestamp){
	    
	    SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");     
	    Date date = new Date(timestamp);
	    return SDF.format(date);
	}

	/**
	 * 获取系统当前时间的时间戳 
	 * @return 当前系统时间的时间戳，类型为long型，长度为13位，精确到毫秒。 
	 */
    public static long getCurrentStamp()
    {
    	Date now = new Date(); 
    	return now.getTime();
    }

    /**
     * 获取系统当前时间，并转换成yyyy/MM/dd HH:mm:ss格式
     * @return yyyy/MM/dd HH:mm:ss格式的时间，类型为String
     */
    public static String getCurrentDate()
    {
    	SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");     
        Date date = new Date();
        return SDF.format(date);
    }

    /**
     * 比较时间字符串，date1 = date2 返回0 ，date1 > date2 返回 1，date1 <date2 返回-1。
     * @param date1 要比较的第一个时间字符串
     * @param date2 要比较的第二个时间字符串
     * @return date1 = date2 返回0 ，date1 > date2 返回 1，date1 <date2 返回-1。
     */
    public static int cmpDate(String date1,String date2)
    {
    	if (null == date1 && null == date2) 
    	{
    		return 0;
    	}
    	
    	if (null == date1) 
    	{
    		return -1;
    	}
    	if (null == date2) 
    	{
    		return 1;
    	}
    	
    	SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	try {
	        Date d1 = SDF.parse(date1); 
	        Date d2 = SDF.parse(date2);
	        
	        if (d1.getTime() == d2.getTime()) 
	        {
	        	return 0;
	        } 
	        else if (d1.getTime() > d2.getTime()) 
	        {
	        	return 1;
	        }
    	} catch (Exception e) 
    	{
    		e.printStackTrace();
    		return -1;
    	}
    	
        return -1;
    }
    
    /**
     * 将指定格式为 yyyy/MM/dd HH:mm:ss 类型的时间转换为提前nSec秒的时间戳，返回类型为long
     * @param time 需要转换的时间字符串
     * @param nSec 需要提前的秒数
     * @return 若传入的时间字符串正常，则返回提前nSec秒的时间戳，否则返回0，其类型均为long
     */  
    public static long addTimeToStamp(String time ,int nSec) {
   	 	SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
   	 	
   	 	try {
   	 		Date date = SDF.parse(time);
   	 		return date.getTime()+ nSec * 1000;
   	 	} catch(Exception e) 
   	 	{
   	 		e.printStackTrace();
   	 	}
   	 	return 0; 
    }
    
    /**
     * 将指定格式为 yyyy/MM/dd HH:mm:ss 类型的时间转换为提前nSec秒的时间，返回类型为String
     * @param time 需要转换的时间字符串
     * @param nSec 需要提前的秒数
     * @return 若传入的时间字符串正常，则返回提前nSec秒的时间戳，，其类型为String,否则返回null
     */
    public static String addTimeToDate(String time ,int nSec) {
   	 	SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
   	 	try {
   	 		Date date = SDF.parse(time);
   	 		return SDF.format(date.getTime()+ nSec * 1000);
   	 	} catch (Exception e)
   	 	{
   	 		e.printStackTrace();
   	 	}
   	 	return null;      	     
    }
}
