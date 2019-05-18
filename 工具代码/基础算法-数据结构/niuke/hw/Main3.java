package view.niuke.hw;

import java.util.*;

public class Main3 
{	
    public static void main(String[] args)
    {
     
    	  f2();
    }
    
    /**
     * 如果要买归类为附件的物品，必须先买该附件所属的主件。每个主件可以有 0 个、 1 个或 2 个附件。
     * 附件不再有从属于自己的附件。王强想买的东西很多，为了不超出预算，他把每件物品规定了一个重要度，
     * 分为 5 等：用整数 1 ~ 5 表示，第 5 等最重要。他还从因特网上查到了每件物品的价格（都是 10 元的整数倍）。
     * 他希望在不超过 N 元（可以等于 N 元）的前提下，使每件物品的价格与重要度的乘积的总和最大。     * 
     */
    public static void f1()
    {
    	 Scanner sc = new Scanner(System.in);
         int N=sc.nextInt();//总钱数  // 此处总钱数除以10，后续单价同样除以10
         int m=sc.nextInt();//个数
         
         int[] v=new int[m+1];//价格
         int[] p=new int[m+1];//重要度 1 ~ 5
         int[] q=new int[m+1];//主件还是附件  主件=0  附件>1, q[i]是主键的下标 
         for(int i=1;i<m+1;i++)
         {
       	  	v[i]= sc.nextInt()/10;//价格都是10的倍数，可以减少循环次数
       		p[i]= sc.nextInt();
       		q[i]= sc.nextInt();
         }
         
         int [][] dp= new int[m+1][N+1];//i表示当前商品，当前最大价值为j， dp[i][j]
         /**三个变量
          * i对应物品个数 m,  [1-m]
          * j对应总价钱N,表示价值容量，是动态增长的，一直到j=N。 [1-N]
          * dp[i][j]对应每件物品的价格与重要度的乘积的总和最大，就是要求的值。
          * q[i]=0 or >1 就是循环中的条件
          */
         
         
         for(int i=1;i<m+1; i++)
         {
        	 for(int j=1;j<N+1; j++)
        	 {
        		 if(q[i]==0)//主件
        		 {
        			 if(v[i]<=j) //主件<=当前价值容量j
        				 dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-v[i]]+v[i]*p[i]);
        		 }
        		 else//>0附件，q[i]>0,表示主件的下标
        		 {
        			 if(v[i]+v[q[i]]<=j)//附件+主件<=当前价值容量j
        				 dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-v[i]]+v[i]*p[i]);
        		 }        			 
        	 }
        	 
         }
         
         
        System.out.println("价值矩阵：");
 	    for(int i=0;i<m+1;i++)
 	    {
 	    	for(int j=0;j<N+1;j++)
 	    		System.out.print(dp[i][j]+"\t");	   
             System.out.println();
         }
 	    System.out.println("背包中重量=["+N+"], 最大价值为："+dp[m][N]*10);//最右下角元素     
    }
    
    
    /**
     * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。
     * 从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出。
     * 
     */
    public static void f2()
    {
    	 Scanner sc = new Scanner(System.in);
    	 String[] str = sc.nextLine().split(";");
    	 
    	 int x=0;
    	 int y=0;
    	 for(int i=0; i<str.length; i++)
    	 {
    		 if(!str[i].matches("[A|D|S|W]\\d{2,}"))
    			 continue;
    		 
    		 //A10
    		 char ch = str[i].charAt(0);//A
    		 Integer d = Integer.parseInt(str[i].substring(1,str[i].length()));//10
    		 switch(ch)
    		 {
    		 	case 'A':
    		 		x -=d;
    		 	break;
    		 	case 'D':
    		 		x +=d;
        		 	break;
    		 	case 'S':
    		 		y -=d;
        		 	break;
    		 	case 'W':
    		 		y +=d;
        		 	break;
    		 	default:
        		 	break;
    		 }
    	 }
    	 
    	 System.out.println("("+x+","+y+")");
    	 
    }
    
    
    /**
     * 请解析IP地址和对应的掩码，进行分类识别。要求按照A/B/C/D/E类地址归类，不合法的地址和掩码单独归类。
     * @Title: f2
     */
    public static void f3()
    {
    	
    }
    
    
    /**
     * 请解析IP地址和对应的掩码，进行分类识别。要求按照A/B/C/D/E类地址归类，不合法的地址和掩码单独归类。
     * @Title: f2
     */
    public static void f4()
    {
    	
    }
   
    
}