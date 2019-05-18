package view.acm;

import java.util.*;

public class Main7 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for(int i=0; i<n; i++)
			a[i] = sc.nextInt();		
	
		find(a,n);
//		System.out.println(find(a,n));
	}
	
	public static void find(int a[], int n) {
		Map<Integer,Integer> map = new HashMap<>();
		
		for(int i=0; i<n; i++)
		{
			if(map.containsKey(a[i]))
			{
				map.replace(a[i], map.get(a[i])+1);
			}
			else
				map.put(a[i], 1);				
		}
		
		
		for (Map.Entry<Integer, Integer> entry : map.entrySet())
		{ 
			if(entry.getValue()>1)
				System.out.print(entry.getKey()+" ");
		}
		System.out.println();
	}

	

}
