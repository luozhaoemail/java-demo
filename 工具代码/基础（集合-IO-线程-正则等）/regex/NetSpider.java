package cn.regex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网络爬虫：到网络中获取指定规则的数据
 * @author luozhao
 *
 */
public class NetSpider {

	public static void main(String[] args) throws IOException {
		 
		String reg = "\\w+@\\w+(\\.\\w+)+";
		List<String> list = getLocalMail(reg);   //List集合有重复元素
		for(String m: list)
			System.out.println(m);
		
		HashSet<String> set = getNetMail(reg);  //set集合没有重复元素
		for(String m: set)
			System.out.println(m);
	}

	private static List<String> getLocalMail(String reg) throws IOException {

		 BufferedReader buf = new BufferedReader(new FileReader("file\\1.html"));
		 
		 String line =null;
		 
		 //String reg = "\\w+@\\w+(\\.\\w+)+"; //1
		 
		 Pattern p = Pattern.compile(reg);	 //2
		
		 List<String> list = new ArrayList<String>();
		 
		 while((line=buf.readLine())!=null){
			 
			 Matcher m =p.matcher(line); //3
			 while(m.find()){   //4
				 //System.out.println(m.group());  //5
				// System.out.println(line);
		         list.add(m.group());
			 }			
		 }		 
		 buf.close();
		 return list;
	}
	
	private static HashSet<String> getNetMail(String regs) throws IOException {
		
		String str_url ="http://bbs.tianya.cn/post-enterprise-401802-5.shtml";
		URL url = new URL(str_url);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();			
		BufferedReader buf = new BufferedReader(new InputStreamReader(in));
		
		String line =null;
		Pattern p = Pattern.compile(regs);
		HashSet<String> set = new HashSet<String>();
		 
		while((line=buf.readLine())!=null){
			Matcher m =p.matcher(line);
			while(m.find()){   
				set.add(m.group());
			 }			
		 }		 
		 buf.close();
		 return set;
	}

}
