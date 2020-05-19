package watch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




//第三包org.json.jar
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 发起http请求并获取结果 
 * @author  罗昭
 * @Time 2017年10月13日 下午3:22:07
 * @version  V1.0
 */
public class HttpRequestUtil
{	
	
	/** 获取spark正在运行job的端口。这个端口是动态的从4040开始累加
	  * @param spark地址
	  * @return appID和端口的映射
	  */
	 public static HashMap<String,String> getJobPort(String adress)
	 {
		 /**数据demo---目的是将appid和port关联起来。
		<a href="app?appId=app-20171113154318-1906">app-20171113154318-1906</a>
		<a href="#" onclick="if (window.confirm('Are you sure you want to kill application app-20171113154318-1906 ?')) { this.parentNode.submit(); return true; } else { return false; }" class="kill-link">(kill)</a>
		<a href="http://172.16.101.11:4043">dns3.0</a>
		 */
		HashMap<String,String> map = new HashMap<String,String>();
		String str = HttpRequestUtil.httpRequest(adress, "GET", null);
		Document doc = Jsoup.parse(str);		
		Elements links = doc.select("a");  		
		for(int i=0; i<links.size()-2;i++)
		{			
			Element e1 = links.get(i);
			Element e2 = links.get(i+1);
			Element e3 = links.get(i+2);
			if(e1.attr("href").startsWith("app?appId=")
				&& e2.attr("href").equals("#")
			    && e3.attr("href").startsWith("http://"))
			{		
				String url = e3.attr("href");
				String port = url.substring(url.lastIndexOf(":")+1, url.length());
				map.put(e1.text(),port);//key=appid, value=port
			}
		}
		 
		return map;		
	 }
	
	/** 
     * 发起http请求并获取结果,统一返回json      
     * @param requestUrl 请求地址 
     * @param requestMethod 请求方式（GET、POST） 
     * @param outputStr 提交的数据 
     * @return JSONObject对象
     */ 
	public static JSONObject requestJSONObject(String requestUrl, String requestMethod, String outputStr)
	{
		JSONObject obj;
		String str = httpRequest(requestUrl,requestMethod,outputStr);
		if(str.startsWith("{"))
        {
        	obj = new JSONObject(str);        	
        }
		else
		{
			obj = new JSONObject("{error:这不是一个JSONObject对象}");
		}
		
		return obj;
	}
	
	/** 
     * 发起http请求并获取结果,统一返回json      
     * @param requestUrl 请求地址 
     * @param requestMethod 请求方式（GET、POST） 
     * @param outputStr 提交的数据 
     * @return JSONArray数组
     */ 
	public static JSONArray requestJSONArray(String requestUrl, String requestMethod, String outputStr)
	{
		JSONArray obj;
		String str = httpRequest(requestUrl,requestMethod,outputStr);
		if(str.startsWith("["))
        {
        	obj = new JSONArray(str);        	
        }
		else
		{			
			obj = new JSONArray("[{error:这不是一个JSONObject数组}]");
		}
		
		return obj;
	}
	
	/** 
     * 发起http请求并获取结果,统一返回json      
     * @param requestUrl 请求地址 
     * @param requestMethod 请求方式（GET、POST） 
     * @param outputStr 用于post方式时指定提交的数据 
     * @return json格式的字符串
     */ 
    public static String httpRequest(String requestUrl, String requestMethod, String outputStr)
    {          
        StringBuffer buffer = new StringBuffer();
        InputStream inputStream=null;
        try 
        {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
            httpUrlConn.setDoOutput(true);//post请求参数要放在正文内，默认false
            httpUrlConn.setDoInput(true); //设置是否从httpUrlConnection读入，默认情况下是true
            httpUrlConn.setUseCaches(false);//Post请求不能使用缓存  
            
            httpUrlConn.setConnectTimeout(10000);//设置超时时间（毫秒）
            httpUrlConn.setReadTimeout(10000);
            
            // 设置请求方式（GET/POST）  
            httpUrlConn.setRequestMethod(requestMethod);  
            if ("GET".equalsIgnoreCase(requestMethod)) 
            {
                httpUrlConn.connect();  
            }
            
            // 当有数据需要提交时  
            if (null != outputStr)
            {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // 注意编码格式，防止中文乱码  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }
            
            //将返回的输入流转换成字符串  
            inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");//  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null)
            {  
                buffer.append(str);  
            }  
            
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();             
            
        }
        catch (ConnectException ce) 
        {  
        	ce.printStackTrace();
        	System.out.println("连接超时");
        }
        catch (Exception e) 
        {  
		   e.printStackTrace();
		   System.out.println("请求错误");
        }
        finally
        {
    		try
    		{
    			if(inputStream!=null)
    			{
    				inputStream.close();
    			}
			} 
    		catch (IOException e)
    		{
				e.printStackTrace();
			}
        } 
        return buffer.toString();
    } 
    
    public static String changByte(int mb)
    {    	
    	if(mb<1024)
    	{
    		return mb+"MB";
    	}
    	else
    	{
    		double num = mb/1024.0;
    		return  String.format("%.2f", num) + "GB";
    	}
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
    
    /** 
     * @param 要转换的毫秒数 
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式 
     * @author fy.zhang 
     */  
    public static String formatDuring(long mss) {  
        long days = mss / (1000 * 60 * 60 * 24);  
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);  
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);  
        double seconds = (mss % (1000 * 60)) / 1000.0;
        double ms = mss / 1000.0;
        String str = "";
        if(days==0)
        {
        	if(hours==0)
        	{
        		if(minutes==0)
            	{
        			if(seconds==0)
                	{
                		str = ms+"豪秒";
                	}
                	else
                	{
                		str = ms+"秒";
                	}
            	}
            	else
            	{
            		str = minutes + "分钟"+ seconds + "秒";  
            	}
        	}
        	else
        	{
        		str = hours + "小时" + minutes + "分钟"+ seconds + "秒";
        	}
        }	
        else
        {    	
        	str = days + "天" + hours + "小时" + minutes + "分钟"+ seconds + "秒";  
        }
        
       return str;
    }// 
    
    //单位统一变成MB
    public static double changetoMB(String str)
	{
		double size = Double.parseDouble(str.substring(0, str.indexOf(" ")));  
		if(str.contains("GB"))
			size *= 1024;
		else if(str.contains("KB"))
			size /= 1024;
		
		return size;			
	}

}

