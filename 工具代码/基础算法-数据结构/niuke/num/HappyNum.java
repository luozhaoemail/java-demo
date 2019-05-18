package view.niuke.num;

import java.util.*;
public class HappyNum {
/**
 * 一个十进制的数字，将其每一位的数字取出进行平方和的累加，得到的新数字重复该操作，直至得到的平方和为1，那么该数字为快乐数。
19 就是一个快乐数:将其各位拆分
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
 */
	public static void main(String[] args) 
	{
		int n=1000;
		System.out.println(countFactZeros(n));
	}
	
	public static boolean isHappy(int n)
	{
        if(n <= 0)
            return false;
        
        Set<Integer> set = new HashSet<>();
        while(n != 1) 
        {
            int sum = 0;
            while(n > 0)//19
            {
                sum += (n % 10) * (n % 10);//19%10=9 个位 
                n /= 10; //19/10=1 十位
            }//1^2 + 9^2 = 82 = sum
            
            if(set.contains(sum)) //如果重复出现的数字跳出
                return false;
            else 
                set.add(sum); 
            
            n = sum;//又开始循环82
        }
        return true;
    }
	
	//100!(100的阶乘)后面有几个0问题
	public static int countFactZeros(int n)
	{
		int count=0;
		for(int i=5;n/i>0;i*=5)
		{
			System.out.print(n+" ");
			count+=n/i;
		}
		return count;
	}
}
