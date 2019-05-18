package net;

import org.apache.commons.collections.CollectionUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/*1.实现PageProcessor接口*/
public class WebMagicDemo implements PageProcessor
{
	/*2.设置抓取网站的相关配置*/   
	// 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	private static int count =0;
	
	/*3.返回site*/   
	@Override
	public Site getSite()
	{
		return site;  
	}
	 
	 /*4.爬取*/
	public static void main(String[] args)
	{
		System.out.println(CollectionUtils.class.getProtectionDomain().getCodeSource().getLocation());

		String str = "http://job.cqupt.edu.cn/portal/home/calendar-page.html?fairDate=2018-11-02%2000:00";
//		String str = "https://www.cnblogs.com/";
		Spider.create(new WebMagicDemo())				
				.addUrl(str)		//开始抓页面		
				.addPipeline(new ConsolePipeline())// 抓取页面的存储路径				
				.thread(5)// 开启5个线程抓取				
				.run();// 启动爬虫
	}
	
	
	/*5.爬取逻辑*/
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	@Override
	public void process(Page page)
	{		
		 //获取整个页面的html代码，并且将其存到LinkedHashMap中
		 page.putField("html", page.getHtml());
	     String pageHtml = page.getResultItems().get("html").toString();
	     System.err.println(pageHtml);
	}
	
	
}
