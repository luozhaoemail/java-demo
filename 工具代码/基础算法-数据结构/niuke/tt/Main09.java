package view.niuke.tt;

import java.util.*;

public class Main09 
{
	    public static List<int[]> markedList = new ArrayList<int[]>();
	    public static int max=0; 
	    public static void main(String[] args) 
	    {
	    	  Scanner sc = new Scanner(System.in);
	    	  String line=sc.nextLine();		      
	    	  String [] str =line.split(",");
	    	  int m = Integer.parseInt(str[0]);
	    	  int n = Integer.parseInt(str[1]);
		      int[][] a =new int[m][n];
		      
		      for(int i=0;i<m;i++)
		      {
		    	  line=sc.nextLine();
		    	  str = line.split(",");
		    	  for(int j=0;j<n;j++)
		    	  {
		    		  a[i][j]=Integer.parseInt(str[j]);
		    	  } 
		      }
		     
	        
	        int con = getCount(fill(a)); 
	        
	        System.out.println(con+","+(max+2));
	        
	    }
	    public static int getCount(int[][] a)
	    {
	        int count = 0;
	        int sum =0;
	        int row = a.length;
	        int col = a[0].length;
	        for(int i = 0;i<row;i++)
	        {
	            for(int j=0;j<col;j++)
	            {
	                //当该点为被标记，并且为1时，标记所有与该点连通的点
	                if(a[i][j]==1 && !isMarked(i,j))
	                {
	                	sum += markPoint(i,j,a);	                   
	                    count++;
//	                    if(sum>max)
//	                    	max=sum;
	                    max = Math.max(max, sum);
	                }
	            }
	        }
	        return count;
	    }
	    

	    public static int[][] fill(int[][] a)
	    {
	        int row = a.length;
	        int col = a[0].length;
	        int[][] b = new int[row+2][col+2];
	        for(int i = 1;i<=row;i++)
	        {
	            for(int j=1;j<=col;j++)
	            {
	                b[i][j]=a[i-1][j-1];
	            }
	        }
	        return b;
	    }
	    
	    //判断该点是否被标记
	    public static boolean isMarked(int i,int j)
	    {
	        int size = markedList.size();
	        if(size<=0) 
	        	return false;
	        for(int k=0;k<size;k++)
	        {
	            int[] m = markedList.get(k);
	            if(m[0]==i&&m[1]==j) 
	            	return true;
	        }
	        return false;
	    }
	    
	    //标点
	    public static int markPoint(int i,int j,int[][] a)
	    {
	    	int sum=0;
	    	int val =a[i][j];
	        if(isMarked(i,j))
	        {
	        	return sum;
	        } 
	        	sum+=val;
//	        sum++;
	        
	        //将该点标记，即放入一个list内
	        int[] m = new int[]{i,j};
	        markedList.add(m);
	        	       
	        if(!isMarked(i-1,j-1)&&a[i-1][j-1]==1) 
	        	markPoint(i-1,j-1,a);
	        if(!isMarked(i-1,j)&&a[i-1][j]==1)
	        	markPoint(i-1,j,a);
	        if(!isMarked(i-1,j+1)&&a[i-1][j+1]==1) 
	        	markPoint(i-1,j+1,a);
	        if(!isMarked(i,j-1)&&a[i][j-1]==1) 
	        	markPoint(i,j-1,a);
	        if(!isMarked(i,j+1)&&a[i][j+1]==1)
	        	markPoint(i,j+1,a);
	        if(!isMarked(i+1,j-1)&&a[i+1][j-1]==1)
	        	markPoint(i+1,j-1,a);
	        if(!isMarked(i+1,j)&&a[i+1][j]==1)
	        	markPoint(i+1,j,a);
	        if(!isMarked(i+1,j+1)&&a[i+1][j+1]==1)
	        	markPoint(i+1,j+1,a);
	        
	        return sum;
	    }
	    
	}