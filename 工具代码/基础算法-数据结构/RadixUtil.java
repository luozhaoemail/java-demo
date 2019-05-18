package view;

import java.util.Stack;

/*任意进制转换
 *10进制 转 R进制 
 *R 进制 转 10进制 
 * 
 */
public class RadixUtil {
	private static String numStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static char[] array = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	public static void main(String[] args)
	{	
		DecimaltoBinary(8);
		DecimaltoBinary2(8);
		binToDecimal("1000");
		System.out.println("八进制");
		DecimaltoOctonary(10);
		octToDecimal("12");
		
		
		//移位：
		int num=-2;
		System.out.println("原始："+num);
		System.out.println("二进制："+Integer.toBinaryString(num));
		System.out.println("左移："+(num<<1) );//num << 1,相当于num乘以2   8<<1 =16
		System.out.println("二进制："+Integer.toBinaryString(num<<1));
		System.out.println("右移："+(num>>1) );//num >> 1,相当于num除以2   8>>1 =4
		System.out.println("二进制："+Integer.toBinaryString(num>>1));
		System.out.println("右移："+(num>>>1) );//无符号右移，忽略符号位，空位都以0补齐  2^31-1=2147483647 ==Integer.MAX_VALUE
		System.out.println("无符号右移："+Integer.toBinaryString(num>>>1));//共31位
		System.out.println("无符号右移："+(num>>>1 == Integer.MAX_VALUE));

		
		//方法一 调API
		 System.out.println("十进制转换到R 进制：");
		 int x = 123;
		 System.out.println(Integer.toBinaryString(x));
		 System.out.println(Integer.toOctalString(x));
		 System.out.println(Integer.toHexString(x));
		 
		 System.out.println("R 制转换到十进制：");
		 System.out.println(Integer.valueOf("FFFF",16));
		 System.out.println(Integer.valueOf("166",8));
		 System.out.println(Integer.valueOf("1010",2));
		 
		//方法一 自定义
		 System.out.println("自定义");
		 Long tmp = N_to_10("FFFF", 16);                     
         String tmp2 = _10_to_N(123, 16);
		 System.out.println(tmp);
		 System.out.println(tmp2);
         String newStr = tmp2.replaceFirst("^0*", "");  
         System.out.println(newStr);
	}
	
	

	//10进制转为其他进制，除留取余，逆序排列
    public static String _10_to_N(long number, int N)
    {
        Long rest = number;
        Stack<Character> stack = new Stack<Character>();
        StringBuilder result = new StringBuilder(0);
        while (rest != 0) {
            stack.add(array[new Long((rest % N)).intValue()]);
            rest = rest / N;
        }
        for (; !stack.isEmpty();) {
            result.append(stack.pop());
        }
        return result.length() == 0 ? "0":result.toString();

    }

    // 其他进制转为10进制，按权展开
    public static long N_to_10(String number, int N)
    {
        char ch[] = number.toCharArray();
        int len = ch.length;
        long result = 0;
        if (N == 10) {
            return Long.parseLong(number);
        }
        long base = 1;
        for (int i = len - 1; i >= 0; i--) {
            int index = numStr.indexOf(ch[i]);
            result += index * base;
            base *= N;
        }

        return result;
    }
	
    //最简单的方法：移位实现十进制转二进制
    public static void DecimaltoBinary(int n)
    {
    	//将十进制数的每一位都分别右移到最低位，再 &1将其他位清0，然后把这个数按十进制输出
    	//1在内存中除过最低位是1，其余31位都是零
    	for(int i=31; i>=0; i--)
    		System.out.print(n>>>i & 1);
    		
    }
    //传统方法：除以2取余数
    public static void DecimaltoBinary2(int n)
    {
    	String bin="";//拼接余数0或1
    	int r=0;
    	
    	while(n!=0)
    	{
    		r=n % 2;
    		bin = r + bin; //余数逆序排列
    		n=n / 2;    		
    	}
    	
    	System.out.println("\n"+bin);
    }
    public static void binToDecimal(String bi)//1101
    {
    	int sum=0;
    	int len = bi.length();
    	for(int i=1;i<=len;i++)
    	{
//    		int x = Integer.valueOf(bi.charAt(i-1)-'0');//i=1,最高位
    		int x = Integer.valueOf(bi.substring(i-1, i));//i=1,最高位
    		sum += Math.pow(2,len-i)*x;//平方项为4-1=3
    	}
    	
    	System.out.println("["+bi+"]="+sum);
    }
    
    
  //传统方法：除以2取余数
    public static void DecimaltoOctonary(int n)
    {
    	String bin="";//拼接余数0或1
    	int r=0;
    	
    	while(n!=0)
    	{
    		r=n % 8;
    		bin = r + bin; //余数逆序排列
    		n=n / 8;    		
    	}
    	
    	System.out.println(bin);
    }
    public static void octToDecimal(String bi)//1101
    {
    	int sum=0;
    	int len = bi.length();
    	for(int i=1;i<=len;i++)
    	{
//    		int x = Integer.valueOf(bi.charAt(i-1)-'0');//i=1,最高位
    		int x = Integer.valueOf(bi.substring(i-1, i));//i=1,最高位
    		sum += Math.pow(8,len-i)*x;//平方项为4-1=3
    	}
    	
    	System.out.println("["+bi+"]="+sum);
    }
    

}
