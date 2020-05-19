package offer;

import java.util.*;

/**
1.只出现一次的数字
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
示例 1:
输入: [2,2,1]
输出: 1
示例 2:
输入: [4,1,2,1,2]
输出: 4

解法一：考察异或，两个相同的数异或结果为0,0和任何数异或结果为任何数。
解法二：用一个字典保存数以及出现的频率，返回value值为1 的数
 */
public class Word1 {

	public static void main(String[] args) {
		//int[] a={4,1,2,1,2};
		//System.out.println(test1(a));
		
		int[] a={4,4,2};
		test2(a);
	}
	
	public static int test1(int[] a){
		int x=0;
		for(int i=0; i<a.length; i++){
		  x = x ^ a[i];  //任何数和0异或等于本身
		  System.out.println(x+" ^ "+a[i]);
		}
		return x;
	}

	//利用HashSet的特性，删除重复的数组元素，最后剩下一个单独的元素，返回即可
	public static void test2(int[] a){
		Set<Integer> set = new HashSet<>();
		for(int i=0; i<a.length; i++){
			if(set.contains(a[i]))
				set.remove(a[i]);
			else
				set.add(a[i]);  //1个元素存在2次，第一次加入set,第二次删除它。那么最后集合只剩一个元素
		}
		System.out.println(set);
	}
}
