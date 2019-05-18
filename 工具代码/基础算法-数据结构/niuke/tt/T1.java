package view.niuke.tt;

import java.util.*;

public class T1 {

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
		{
			List<int[]> list = new ArrayList<int[]>();
			list.add(new int[1]);
			int n = sc.nextInt();
			for(int i=1; i<=10;i++)
			{
				String[] str = sc.nextLine().split(" ");
				int[] arr = new int[str.length];
				for(int j=0; i<str.length; i++)
					arr[i] = Integer.parseInt(str[i]);

				list.add(arr);	
			}
			
			System.out.println(2);
		}
	}
	
	public static void f5(int n)
	{
		String reg="\\d+|+|-|(|)";
		System.out.println();
	}
	
	/**
10
0
5 3 0
8 4 0
9 0
9 0
3 0
0
7 9 0
0
9 7 0
	 */

}
