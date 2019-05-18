package view;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 * @author luozhao
 *
 */
public class RegexDemo {

	public static void main(String[] args) {
		test1();//匹配
		test2();//切割
		test3();//替换
		test4();//获取
		
		
		test5();//去重

	}

	private static void test1() {
		String qq = "9921215";
		boolean b = qq.matches("[1-9]\\d+");//第一位数字不能是0，后面全是数字
		System.out.println(b);
		
		qq = "+2524";
		b = qq.matches(".[1-9]\\d{3}");//第一位任意字符，第二位不能为0，后面智能有3个数字
		System.out.println(b);
/**
[abc] a、b 或 c（简单类） 
[^abc] 任何字符，除了 a、b 或 c（否定） 
[a-zA-Z] a 到 z 或 A 到 Z，两头的字母包括在内（范围 
. 任何字符（与行结束符可能匹配也可能不匹配） 
\d 数字：[0-9]  注意：\d是一个整体，要加上转义字符：\\d
\D 非数字： [^0-9] 
\s 空白字符：[ \t\n\x0B\f\r] 
\S 非空白字符：[^\s] 
\w 单词字符：[a-zA-Z_0-9] 
\W 非单词字符：[^\w] 
X? 表示X出现一次或一次也没有 
X* 表示X出现零次或多次 
X+ 表示X出现一次或多次 
X{n} 表示X出现恰好 n 次 
X{n,} 表示X出现至少 n 次 
X{n,m} 表示X出现至少 n 次，但是不超过 m 次 
\b 单词边界 
\B 非单词边界 


 */
	}
	
	private static void test2() {
		//String str = "zhang3 lisi  wangwu 	ddd";
		//String reg=  "\\s+"; //若干个空白字符
		
		//String str = "zhang3.lisi.wangwu.ddd";
		//String reg=  "\\.";// .代表任何字符，要用转义字符
		
		//String str = "C:\\abc\\a.txt"; // 表示C:\abc\a.txt
		//String reg=  "\\\\";// 转义后\\表示一个\，str有2个\\，所以是\\\\
		
		String str = "Illoveyyyou";//切分叠词比较特殊：(.)表示第一组，\1表示前面的内容再一次出现
		String reg=  "(.)\\1+"; // 所以叠词用到第一组的重复 \\1+  把 ll和yyy都去掉
		/**
		 * 匹配相同的叠词切割  正则复用:用()封装 ,并自动从1开始编号，称为组 。
		 * 通过组的编号就可以调用组，进行复用  
		 * 不分组默认为0，0组代表整个表达式
		 */
		
		//根据重复的字母拆分
		String[] arr= str.split(reg);
		for(String s: arr)
			System.out.println(s);
		
	}
	
	private static void test3() {
		String str="wo45045arekndwo15463znenk5555";
		str=str.replaceAll("\\d{4,}"," ");//用#替换出现4次以上的数字
		System.out.println(str);
		
		str="Illoveyyyou";
		str=str.replaceAll("(.)\\1+"," $1");//把叠词替换成一个字母 ，$1表示获取第一组，不写默认整个字符串为第0组
		System.out.println(str);
		
		/**replace与replaceAll的区别
		 * replaceAll是基于规则表达式的替换
		 * replace只是字符替换，也支持字符串，不是基于正则表达式的
		 */
	
	}
	
	private static void test4() {
		/**获取符合规则的子串
		 * 1.将正则表达式封装成对象
		 * 2.关联正则表达式和字符串
		 * 3.取出符合规则的子串		 * 
		 * Pattern p = Pattern.compile("a*b");  将字符串的正则式编译成Pattern正则对象
		 * Matcher m = p.matcher("aaaab"); 通过Matcher对象将正则表达式规则作用到字符串上
		 * boolean b = m.matches(); 开始操作字符串		 * 
		 * 等效于  boolean b = Pattern.matches("a*b","aaaab");
		 */
		String str ="mdd iddng ddd k,hhh qslss";
		String reg ="\\b[a-z]{3}\\b";//取出3个字母的单词	\b表示单词边界
		
		Pattern p = Pattern.compile(reg);//1
		Matcher m = p.matcher(str);//2	
		while(m.find())//重置此匹配器，然后尝试查找匹配该模式、从指定索引开始的输入序列的下一个子序列。
		{	
			System.out.println(m.group());//返回在以前匹配操作期间由给定组捕获的输入子序列。 
			System.out.println(m.start()+"...."+(m.end()-1));//匹配的初始索引+....+最后匹配字符之后的偏移量-1	
		}
	}
	
	
	private static void test5() {
		//字符串去重
		String str ="我我...我...我我..我.我我...要..学.....学学学...学编.....编编编程程....程程程";
		str = str.replaceAll("\\.+", "");
		System.out.println(str);
		str = str.replaceAll("(.)\\1+", "$1");
		System.out.println(str);
		
		
		//ip地址排序
		String ip ="127.1.22.1   3.3.3.3   172.23.22.231   25.5.25.1";
		ip = ip.replaceAll("(\\d+)", "00$1");//每一段数字前加2个0
		System.out.println(ip);
		
		ip = ip.replaceAll("0*(\\d{3})", "$1");//去掉前面多余的0，这样每段都是3位     0*表示0出现零次或多次 
		System.out.println(ip);
		
		String [] sip =ip.split("\\s+");	//按空格切ip	
		Arrays.sort(sip); //按字符串排序
		for(String s :sip)
			System.out.println(s.replaceAll("0*(\\d+)", "$1")); //去掉数字前面多余的0	
		
		
		//邮箱校验
		String mail = "lu22oz_hao@16A3.edu.cn.com";		
		String reg ="\\w+@\\w+(\\.\\w+){1,3}";  //精确  加括号(\\.\\w+)，表示一组
		System.out.println("邮箱："+mail.matches(reg));
		
		//电话号码mail
		String s ="18080797638";
		boolean f = s.matches("1[358][0-9]{9}");
		System.out.println("是不是电话号码："+f);
		
		//电话号码打码
		//先将号码分组：前3位，中间4位，后面4位，将第二组替换为*，正常显示第1、3组
		String ss="18080797638";
		ss=ss.replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1****$3"); 
		System.out.println(ss);//180****7638
		
		//将部分数字号码替换为*
		ss ="我的qq是240860747ssss6689898986jk年龄是22hjhjhj";
		ss = ss.replaceAll("\\d{5,}", "***");
		System.out.println(ss);
		
		
		//剔除字母，并拆分
		str ="1234ABCDACBD123"; 
		//(A)(B)(C)(D)的组顺序为1234，后面的ACBD的组顺序为1324
		String[] arr = str.split("(A)(B)(C)(D)\\1\\3\\2\\4");  //按照这一串字符ABCDACBD来拆分
		for(String x :arr)
			System.out.println(x);
		
	}
	
}
