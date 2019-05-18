package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class JsoupDemo {

	public static void main(String[] args) {
	    long start = System.currentTimeMillis();
		test1();
		countTime(start);
	}
	
	// 0.901 秒
	//直接通过 Jsoup.connect获取请求页面
	public static void test1()
	{
		String str="http://job.cqupt.edu.cn/portal/home/calendar-page.html?fairDate=2018-11-02%2000:00";
		try
		{
			Document doc = Jsoup.connect(str).get();
//			System.out.println("title= "+doc.title());
			Elements list = doc.select("a");
			for(int i=0; i<list.size()-2;i++)
			{
				Element e = list.get(i);
				System.out.println(e.text()+"\n--- "+e.attr("href"));
			}			
			
		} catch (IOException e) {		
			e.printStackTrace();
		}
	}
	// 1.12 秒
	//先通过HttpURLConnection去获取html字符串，再用Jsoup解析
	public static void test2()
	{
		String str="http://job.cqupt.edu.cn/portal/home/calendar-page.html?fairDate=2018-11-02%2000:00";		
		String html = httpRequest(str, "GET", null);
		Document doc = Jsoup.parse(html);
		Elements list = doc.select("a");
		for(int i=0; i<list.size()-2;i++)
		{
			Element e = list.get(i);
			System.out.println(e.text()+"\n--- "+e.attr("href"));
		}			
	
	}
	
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
	
    //long start = System.currentTimeMillis();
  	//countTime(start);
  	public static void countTime(long start) {
  		System.out.println("花费时间 " + (System.currentTimeMillis() - start)/1000.0 + " 秒");
  	}
}
