package view.niuke.hw;

import java.io.*;
import java.util.*;

import view.ArrayUtil;

public class Main1
{
	public static void main(String[] args) throws IOException 
	{
		f5_2();
	}
	
	/**
	 
	 
	 */
	public static void f6()
	{
		
	}
	
	/**
	 写出一个程序，接受一个十六进制的数值字符串，输出该数值的十进制字符串。（多组同时输入 ）	 
	 */
	public static void f5()
	{
		int a=0;
		int b=17;
		print(b>>a);
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
		{
//			String str = sc.nextLine();//全部都接收包含空格
			String str = sc.next();//只接受空格之前的
			
			String num = str.substring(2).toUpperCase();//去掉0x
			int res =0; 
			for(int i=0;i<num.length();i++)
			{
				char ch = num.charAt(i);
				String dd="";
				if(ch>=65 && ch<=70) //A=65 a=97  F=70  f=102 大写和小写相差32 a-A=32
				{
					switch(ch)
					{
						case 65: 
							dd="10";
						break;
						case 66: 
							dd="11";
						break;
						case 67: 
							dd="12";
						break;
						case 68: 
							dd="13";
						break;
						case 69: 
							dd="14";
						break;
						case 70: 
							dd="15";
						break;
						default:break;
					}
				}
				else if(ch>=48 && ch<=57)//0=48 9=57
					dd = ch+"";
				else
					break;
				
				//先获取每一位的数字 C460 => C 4 6 0 
				//进制转换 C460=C*16^3+4*16^2+6*16^1+0*16^0
				Integer y = new Integer(dd);					
				int t = num.length()-1-i;
				//最高位i=0，n-1-i=3-i=3,应该乘16^3，所以t=3；
				//最低位(个位) i=3; t=0; 乘16
				
				while(t>0)
				{
					y = y*16;
					t--;
				}
				res +=y;
			}//for
			System.out.println(res);
		}
	}
	//方法2：牛逼
    //	Integer自带方法  Integer.parseInt(str,x);//可以把任意进制 x 转成10进制
	//  System.out.println(Integer.parseInt("1A",16));
	//  System.out.println(Integer.parseInt("123",8));
	//  System.out.println(Integer.parseInt("0101",2));
	public static void f5_2()
	{	
		Scanner sc=new Scanner(System.in);
        while (sc.hasNext())
        {
            String str=sc.next().substring(2).toUpperCase();
            char ch;
            int n = str.length();
            int p = 0;//指针，初始指向最高位. n=4,p=0, 那么应该乘 16^3，即 n-1-p
           //进制转换 C460=C*16^3+4*16^2+6*16^1+0*16^0
            int ddd=0;
            int res=0;
            while(n>0)
            {
            	ch = str.charAt(n-1-p); //取第几位的数 3-0=3
            	if(ch>='0' && ch<='9')//ASCII，0=48，9=57
            		ddd = ch -'0'+10;
            	else if(ch>='A' && ch<='F')
            		ddd = ch -'A'+10;//ASCII，A=65，F=70      C-A=67-65=2 ，C= 2+10=12
            	else
            		break;
            	
            	res += ddd*Math.pow(16,p); //（底数，几次方） 第三位的几次方为p=0
            	p++;
            }
        
        }
	}
	
	/**
	 •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组； 
	 •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。 
	 
	 */
	public static void f4()
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
		{
			String line = sc.nextLine();
			int n = line.length();
			 //分页思想				
			int x = n / 8;//多少页
			if(n % 8 != 0)
				x++; //余数独占一页
			
			int start =0; 
			int end =0;
			
			String[] str = new String[x];
			for(int i=0;i<x;i++)
			{				
				start = i*8;//0 --8  --16
				end = start+8;//7 --15 --23  开区间，因为substring会自动减一
				if(end >= n)
					end = n;
				str[i] = line.substring(start,end);//[s,e-1]
			}	
					
			int len = str[x-1].length();
			if(len < 8)
			{
				 int d= 8-len;
				 while(d>0)
				 {
					 str[x-1] += "0";					 
					 d--;
				 }
			}
						
			for(String s : str)
				System.out.println(s);			
		}
		
	}
	//方法2： 牛逼
	public static void f4_2()
	{
		 Scanner sc = new Scanner(System.in);
         while(sc.hasNext())
         {  
    		 String s = new String(sc.nextLine());
    		 if(s.length()%8 !=0 )
    			 s += "00000000"; //先给后面加上8个0
    		 
    		 //循环迭代
    		 while(s.length() >= 8) //后面剩余的不要了。
    		 {
    			 System.out.println(s.substring(0, 8));//只输出前面8个
                 s = s.substring(8);//相当于s =s-8,每次减去前面的8个字符，字符串s变短 
    		 }
         }
	}
	
	/**随机生成了N个1到1000之间的随机整数（N≤1000）
	 * 对其进行去重和排序
	 */
	public static void f3()
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
		{
			Set<Integer> set = new TreeSet<Integer>();
			int n = sc.nextInt();		
			int[] a =new int[n]; 
			for(int i=0; i<n; i++)
	        {
	            a[i] = sc.nextInt();
	            set.add(a[i]);
	        }
		
			for(Integer i : set)
				System.out.print(i+" ");
		}
        
	}
	
	/**接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
	 ABCDEF A 查找A在字符串中的出现次数
	 1
	 */
	public static void f2() throws IOException
	{
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine().toLowerCase();
		String s = sc.next();
		char ch =s.charAt(0);
//		String str = bf.readLine().toLowerCase();
//		char ch = str.charAt(str.length()-1);
//		str = str.substring(0,str.length()-1);
//		String ch2= str.substring(str.lastIndexOf(" ")+1);
//		print(ch);
//		print(ch2);
		
		int x=0;
		for(int i=0;i<str.length(); i++)
		{
			if(str.charAt(i) == ch)
				x++;
		}
		print(x);
	}
	
	/**
	 * 计算字符串最后一个单词的长度，单词以空格隔开。
	 * hello world
	 * 5
	 * 字符串常见操作：
	 * str.length()
	 * String的下标从0开始
	 * 
	 */
	public static void f1()
	{
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		int start = str.lastIndexOf(" ")+1;
		int length = str.length()-start;
		System.out.println(length); //str.length()-(str.lastIndexOf(" ")+1)
		
//		System.out.println(str.substring(start,str.length()));//start,end-1
		
		/*//字符串常见操作：
		String str ="hello world";
		print(str.length());//长度就是字符的个数n，下标从[0,n-1], n=11, 范围是[0-10]
		//默认是从0开始查找 
		print(str.indexOf("h"));//第一个字符下标为0
		print(str.indexOf("d"));//最后一个字符下标为10
		//从指定下标开始查找
		print(str.indexOf(" ", 2));//5
		print(str.indexOf(" ", 9));//-1
		print(str.indexOf("d", 9));//10
		
		print(str.lastIndexOf(""));
		print(str.lastIndexOf("h",10));
		
		char ch = str.charAt(str.lastIndexOf("r",10));
		print(ch);
		
		print(str.substring(6));//从指定下标开始[6-length()-1]		
		print(str.substring(0, 2));//he  [0-1]   [startindex,endIndex-1]
*/	
		}
	
	//简单打印
	public static void print(Object obj)
	{
		System.out.println(obj);
	}
	

}
