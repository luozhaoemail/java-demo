package view;

import java.util.Arrays;
import java.util.Scanner;

/*P为二维坐标轴一象限点集合，某点右上方都没有其他店，则为最大。
 *求一堆点集合中的最大点集合，就是求右上角的边界点集合
 */
public class PmaxDemo {

	public static void main(String[] args) {
//		find1();
		find2();
    }
	
	public static void find1()
	{
		 Scanner sc = new Scanner(System.in);
	      int n = sc.nextInt();
	      int[][] a =new int[n][2];
	      for(int i=0;i<n;i++)
	      {
	    	  a[i][0]=sc.nextInt();//x坐标
	    	  a[i][1]=sc.nextInt();//y坐标
	      }  
	      System.out.println("边界点集合个数："+solve(n,a));
	      sc.close();
	}	
	/**
	 * 方法一：
	 * 将坐标按照输入的顺序进行遍历，用两个指针i，j探索，首先找到<x,y>坐标均大于前面的点
	 * 即第j个点的<x,y>均比第i个点大，假设它是边界点
	 * 然后让i指向他,再用j探索有没有比他更大的点，如果没有他就是最大的点，输出
	 * 如果有继续修改i指向它。
	 */
    public static int solve(int n, int[][] a)
    {
        int count=0;       
        for(int i = 0; i<n; i++)//指针i
        {
        	boolean flag=true;        	
        	for(int j=0; j<n; j++)//指针j
        	{
        		if(a[j][0]>a[i][0] && a[j][1]>a[i][1])//找到<x,y>最大的点
        		{
        			flag=false;
        			break;
        		}
        	}
        	     	
        	if(flag)
        	{
        		count++;
        		System.out.println(a[i][0]+" "+a[i][1]);
        	}
        }
        
        return count;
    }
    
    /**方法2
     * 先将当前点坐标按照y值递减排序，然后遍历该数组，并且将该点的横坐标的最大值保存在一个变量中，
     * [(4,6),(7,5),(5,3),(1,2),(9,0)] 
     * 当该点的横坐标大于当前横坐标的最大值时，则该点为边界点。
     * 遍历第一个元素时：该点可以直接输出，并且将横坐标最大值设置为4； 
	 * 遍历第二个元素时：该点的横坐标大于横坐标最大值4，所以该点也输出，并将横坐标最大值设置为7；
	 * 
	 *  即从矩阵的右上角开始搜索，按照y递减，即从第一行开始，先找到这行x最大的输出，再下移一行。
     */
    public static void find2()
	{
    	Scanner sc= new Scanner(System.in);
    	int n =sc.nextInt();
    	Point [] p =new Point[n];
    	for(int i=0;i<n;i++)
    	{    		
    		Point tmp= new Point(sc.nextInt(),sc.nextInt());
    		p[i]= tmp;
    	}
    	Arrays.sort(p);//按照y递减排序
    	System.out.println("y递减排序:");
    	for(int i=0;i<n;i++)
    	{ 
    		System.out.println(p[i].x+" "+p[i].y);
    	}
    	
    	System.out.println("边集合如下：");    	
    	int max = 0;
    	for(int i=0;i<n;i++)
    	{
    		if(p[i].x > max)
    		{
    			max= p[i].x;
    			System.out.println(p[i].x+" "+p[i].y);
    		}
    	}
	}
    
    private static class Point implements Comparable<Point>
    {
    	int x;
    	int y;
    	    	
    	public Point(int x, int y)
    	{			
			this.x = x;
			this.y = y;
		}

		@Override
    	public int compareTo(Point p)
    	{
    		return Integer.compare(p.y, this.y);
    	}
    }


}
