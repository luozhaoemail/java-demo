package view.acm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/** 花园
小明有个花园，共m朵花，都不一样。用1~m的整数表示朵花
一天看了n次花，a[i]表示第i次看的花的种类
问在任意的时间段内[l,r]中，他一共看了多少朵不同的花？

5 3
1 2 3 2 2
3
1 4
2 4
1 5
*/
public class Main2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext())
		{
			int n = in.nextInt();//n次看花
			int m = in.nextInt();//m朵花
			int[] a = new int[n];
			for (int i = 0; i < n; i++) //每次看的种类
				a[i] = in.nextInt();
			
			int count = in.nextInt();//时间段的个数
			
			int[][] seIndex = new int[count][2];//时间段 // 保存起始的下标
			for (int j = 0; j < count; j++) 
				for (int k = 0; k < 2; k++)
					seIndex[j][k] = in.nextInt();			
			
			List<Integer> result = new ArrayList<Integer>();			
			for (int j = 0; j < count; j++)//遍历所有的时间段
			{		
				HashSet<Integer> set = new HashSet<Integer>();
				for (int l = seIndex[j][0] - 1; l < seIndex[j][1]; l++) //[L, R]
				{
					if (!set.contains(a[l]))
						set.add(a[l]);
				}
				result.add(set.size());
			}
			
			for (Integer integer : result)
				System.out.println(integer);
			
		}//while
	}
}
