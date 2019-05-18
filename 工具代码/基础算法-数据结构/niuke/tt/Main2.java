package view.niuke.tt;

import java.util.*;

public class Main2 {
	public static class Pair
	{
		public int s;
		public int e;
		public Pair(int s, int e) 
		{			
			this.s = s;
			this.e = e;
		}
		@Override
		public String toString() {
			return s + "," + e;
		}	
			
	}
		
	public static void main(String[] args)
	{
		List<Pair> list =new ArrayList<Pair>();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int j=0;j<n;j++)
  	  	{
			String line=sc.nextLine();		      
	   	  	String [] str =line.split(";");
	   	  	for(int i=0;i<str.length;i++)
	   	  	{
	   	  		String [] num =line.split(",");
	   	  		int x = Integer.parseInt(num[0]);
	   	  		int y = Integer.parseInt(num[1]);
	   	  		Pair p =new Pair(x,y);
	   	  		list.add(p);
	   	  	}	   	  
  	  	}	      
   	  	
   	    List<Pair> list2=merge(list);
   	    System.out.println(list2.size());
   	  	
	      
	      sc.close();
	}
	
	
	public static List<Pair> merge(List<Pair> list)
	{
		int n = list.size();
		int[] starts = new int[n];
		int[] ends = new int[n];
		for (int i = 0; i < n; i++) {
			starts[i] = list.get(i).s;
			ends[i] = list.get(i).e;
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		
		List<Pair> res = new ArrayList<Pair>();
		for (int i = 0, j = 0; i < n; i++) 
		{ 
			if (i == n - 1 || starts[i + 1] > ends[i])
			{
				res.add(new Pair(starts[j], ends[i]));
				j = i + 1;
			}
		}
		return res;
	}

	
	
	

}
