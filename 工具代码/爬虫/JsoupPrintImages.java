package net;

import java.io.IOException;

import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements; 

public class JsoupPrintImages
{  
     public static void main( String[] args ) throws IOException
     {  
            Document doc = Jsoup.connect("http://job.cqupt.edu.cn/portal/home/calendar-page.html?fairDate=2018-11-02%2000:00").get();  
           
            //<img src="http://job.cqupt.edu.cn:80/resources/portal-skin/images/logo.png" alt="重庆邮电大学">
            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");  
            
            for(int i=0; i<images.size(); i++) 
            {  
            	Element image = images.get(i);
                System.out.println("图像"+i+" src : " + image.attr("src"));  
                System.out.println("height : " + image.attr("height"));  
                System.out.println("width : " + image.attr("width"));  
                System.out.println("alt : " + image.attr("alt")+"\n");  
            }  
            
            //<div id="wrapper" data-jobId="" class="body_nav">
            Element e = doc.getElementById("wrapper");
            System.out.println(e.text());
            
            //<a class="next">next</a>
            e = doc.getElementsByClass("next").first();
            System.out.println(e);
            
            //<li><a href="/">首页</a></li>
            e = doc.getElementsByTag("li").first();
            System.out.println(e.html());
     }  
}
