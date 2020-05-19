package tool;

import java.util.*;

public class SearchIP {
	
	public static void main(String[] args)
	{
		ArrayList<Integer> lst = new ArrayList<Integer>();
		lst.add(2);
		lst.add(5);
		lst.add(9);
		lst.add(12);
		lst.add(32);
		System.out.println(lst);
				
		int index = getfirstMin(lst,2);
		System.out.println("小于/等于x的最大数是"+lst.get(index));
	}
	
	//查找下限，即小于/等于x的最大数=4,  1  4 x 9 10
	public static int getfirstMin(ArrayList<Integer> a,Integer ip)
	{ 		
		if(ip < a.get(0))
			return -1;			
		if(ip > a.get(a.size()-1))				
			return a.size()-1;		
		
		int low = 0;
		int high = a.size()-1;
		int res = high;
		while(low <= high) 
		{  	                  
        	int middle = low +(high-low)/2;        	 
        	if(ip < a.get(middle)) 
            {  
        		res = middle;
        		high = middle-1;         	 
            }
            else               
               low = middle+1;        
        }  
		
        return res-1; 				
   } 

	
	//查找上限，即大于/等于x的最小数=9,    1  4 x 9 10
	public static int getfirstMax(ArrayList<Integer> a,Integer ip)
	{ 		
		if(ip < a.get(0))
			return -1;			
		if(ip > a.get(a.size()-1))				
			return a.size()-1;		
		
		int low = 0;
		int high = a.size()-1;
		int res = high;
		while(low <= high) 
		{  	                  
        	int middle = low +(high-low)/2;        	 
        	if(ip < a.get(middle)) 
            {  
        		res = middle;
        		high = middle-1;         	 
            }
            else               
               low = middle+1;        
        }  
		
        return res-1; 				
   } 
}
