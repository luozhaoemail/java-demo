package java2.regex;
import java.util.Arrays;

import org.junit.Test;

/**
 * 正则表达式练习
 * Junit单元测试练习
 * @author luozhao
 *
 */

public class Test2 {

	@Test
	public void test() {
		String str ="我我...我...我我..我.我我...要..学.....学学学...学编.....编编编程程....程程程";
		str = str.replaceAll("\\.+","");//先去所有的掉点 \\.表示\.再转义成.
		System.out.println(str);
		
		str =str.replaceAll("(.)\\1+","$1");//去掉重复内容，只保留一个
		System.out.println(str); 
	}
	
	@Test
	public void test2() {//ip地址排序
		String ip ="127.1.22.1   3.3.3.3   172.23.22.231   25.5.25.1";
		ip = ip.replaceAll("(\\d+)", "00$1");//每一段数字前加2个0
		System.out.println(ip);
		
		ip = ip.replaceAll("0*(\\d{3})", "$1");//去掉前面多余的0，这样每段都是3位     0*表示0出现零次或多次 
		System.out.println(ip);
		
		String [] sip =ip.split(" +");	//按空格切ip	
		Arrays.sort(sip); //按字符串排序
		for(String str :sip)
			System.out.println(str.replaceAll("0*(\\d+)", "$1")); //去掉数字前面多余的0		
	}
	
	@Test
	public void test3() {//邮箱校验
		String mail = "lu22oz_hao@16A3.edu.cn.com";		
		String reg ="[a-zA-Z_0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z]+){1,3}";  //精确
		System.out.println("邮箱："+mail.matches(reg));
		
		String mail2 ="1@1.1";
		String reg2 = "\\w+@\\w+(\\.\\w+)+";//不精确		
		System.out.println("邮箱："+mail2.matches(reg2));
	}

}
