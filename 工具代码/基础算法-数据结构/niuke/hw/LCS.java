package view.niuke.hw;

import java.util.*;

public class LCS {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		while(sc.hasNextLine())
		{
			//String str = "AAAGTCGAC";
			String str =sc.nextLine();
			String result ="";
			
			int max = 0;// 重复子串的最长长度		
			int first = 0;// 最长重复子串的起始位置
			int k = 0;
			for (int i = 1; i < str.length(); i++)
			{
				for (int j = 0; j < str.length() - i; j++)
				{
					if (str.charAt(j) == str.charAt(i + j))
					{
						k++;
					} 
					else 
					{
						k = 0;
					}
					if (k > max) 
					{
						max = k;
						first = j - k + 1;
					}
				}
			}
			if (max > 0)
			{				
				result =  str.substring(first, first + max);
			}
			System.out.println(result+" "+max);
			
		}
		
		
	}
		
}
