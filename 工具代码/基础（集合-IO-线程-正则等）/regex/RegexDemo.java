package cn.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 * @author luozhao
 
  String str = HttpRequestUtil.httpRequest(url, "GET", null);
		 System.out.println("------------ \n"+str);
		 List<String> list = new ArrayList<String>();
		 
		 String regex ="<a[^<>]*?>\\s*(.*?)\\s*</a>";
		 //String regex = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
		 Pattern pa = Pattern.compile(regex);
		 Matcher ma = pa.matcher(str);
		 while (ma.find())
		 {
			 list.add(ma.group());
		 }
		 return list;
 
 */
public class RegexDemo {

	public static void main(String[] args) {
		
		
		//matches();//匹配
		//splitDemo();//切割
		//replace();//替换
		//getDemo();//获取
		//test();
		//test2();
		test_mail();
	}

	private static void test_mail() { //邮箱校验
	
		String mail = "lu22oz_hao@16A3.edu.cn.com";		
		String reg ="[a-zA-Z_0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z]+){1,3}";  //不通用
		System.out.println("邮箱："+mail.matches(reg));
		
		String mail2 ="1@1.1";
		String reg2 = "\\w+@\\w+(\\.\\w+)+";		
		System.out.println("邮箱："+mail2.matches(reg2));
				
	}

	private static void test2() {//ip地址排序
		
		String ips ="127.0.0.1   3.3.3.3   172.23.22.231   25.5.25.1";
		
		
		ips = ips.replaceAll("(\\d+)", "00$1"); //每一段前面都补两个0
		System.out.println(ips);
		
		ips = ips.replaceAll("0*(\\d{3})", "$1"); //去掉每一段前面多余的0，保证每一段都是留下3位
		System.out.println(ips);
				
		String [] ip =ips.split(" +");		
		Arrays.sort(ip); //按字符串排序
		for(String str :ip)
			System.out.println(str.replaceAll("0*(\\d+)", "$1")); //去掉多余的0
			
		
	}

	private static void test() {
		String str_test ="我我...我...我我..我.我我...要..学.....学学学...学编.....编编编程程....程程程";
		str_test =str_test.replaceAll("\\.+","");
		System.out.println(str_test); 
		
		str_test =str_test.replaceAll("(.)\\1+","$1");
		System.out.println(str_test); 
		
	}

	private static void getDemo() {
		/**
		 * Pattern p = Pattern.compile("a*b");  将字符串的正则式编译成Pattern正则对象
		 * Matcher m = p.matcher("aaaab"); 通过Matcher对象将正则表达式规则作用到字符串上
		 * boolean b = m.matches(); 开始操作字符串
		 * 
		 * 等效于  boolean b = Pattern.matches("a*b","aaaab");
		 */
//		String sss ="123456";
//		String reg ="[1-9]\\d{4,14}";
		
		String sss ="mdd iddng ddd k,ljk qslss";
		String reg ="\\b[a-z]{3}\\b"; //只取5个字母的单词		
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(sss);
		
		System.out.println(m.matches()); //是否匹配
		while(m.find())  //查找符合规则的字串
		{	
			System.out.println(m.group());  //获取匹配后的结果			
			System.out.println(m.start()+"-------"+m.end()); //索引
		}
		
	}

	private static void replace() {
		String ss ="hell####owor##d";
		ss = ss.replaceAll("#+", "@");
		System.out.println(ss);
				
		ss ="hell####owor￥￥￥￥d";//替换成一样的
		ss = ss.replaceAll("(.)\\1+", "");
		System.out.println(ss);
		
		ss ="hell####owor￥￥￥￥d";
		/**替换为各自不同的符号
		 * 第一个参数为正则表达式，第二个为字符串。第二参数使用组时，要用$来调用 ，而\\1只能在正则式里用
		 */
		ss = ss.replaceAll("(.)\\1+", "$1");
		System.out.println(ss);
		
		//将部分数字号码替换为*
		ss ="我的qq是240860747ssss6689898986jk年龄是22hjhjhj";
		ss = ss.replaceAll("\\d{5,}", "***");
		System.out.println(ss);
		
		ss ="18080797638";
		ss = ss.replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1****$3");
		System.out.println(ss);
		
		
		
	}

	private static void splitDemo() {
		//String str ="zhang,li4,wang5";
		String str ="zhang.li4.wang5";

		String [] arr = str.split("\\."); // .代表任何字符，要用转义字符
		for(String s :arr)
			System.out.println(s);
			
		str ="23    -4    555";
		arr = str.split(" +");//1次或者多次空格 
		for(String s :arr)
			System.out.println(s);
		
	/**
	 * 匹配相同的叠词切割  正则复用:用()封装 ,并自动从1开始编号，称为组 。
	 * 通过组的编号就可以调用组，进行复用  
	 * 不分组默认为0，0组代表整个表达式
	 */
		str ="zhangsan##lisi@@@@wangwu"; 		
		arr = str.split("(.)\\1+");    
		for(String s :arr)
			System.out.println(s);
		
		//组嵌套 技巧：从左起数有几个左括号就有几组   ((A)(B(C)))   4组 		
		str ="1234ABCDACBD123"; 
		arr = str.split("(A)(B)(C)(D)\\1\\3\\2\\4");  //按照这一串字符ABCDACBD来拆分
		for(String s :arr)
			System.out.println(s);
		
	}

	private static void matches(){
		String qq = "+2404";
		boolean b = qq.matches(".[1-9]\\d{3}");
		System.out.println(qq + " :" + b + "  " + (1_000_000 - 1) + " " + 0b1111);
		
		String s ="18080797638";
		boolean f = s.matches("1[358][0-9]{9}");
		System.out.println("是不是电话号码："+f);
	
		System.out.println(Double.POSITIVE_INFINITY+" "+Double.NEGATIVE_INFINITY +" "+ Double.NaN);
		int x=1;
		System.out.println(Double.isNaN(x));
		System.out.println(2.0-1.1); //二进制不能精确表示1/10
	}
}
