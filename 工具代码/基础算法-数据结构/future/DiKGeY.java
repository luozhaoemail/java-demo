package view.future;

import java.util.Scanner;

public class DiKGeY {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext())
    	{
        	int t = in.nextInt();
            in.nextLine();
            int[][] groups = new int[t][2];
            for (int i = 0; i < t; i++)
            {
                groups[i][0] = in.nextInt();
                groups[i][1] = in.nextInt();
                in.nextLine();
            }
            
            for (int[] group : groups) 
            {
                int x = group[0];
                int k = group[1];
  
                int y = 0, n =1;
                while(k>0){
                    if(x%2!=0){
                        //此时x的二进制最右端为1的话，一直使x右移，就是找到x的为0的位置
                        while(x%2!=0){
                            n = n*2;  //每移一位，n记录一下变化的值
                            x = x/2;
                        }
                    }
                    //如果k的二进制最右端为1，就使y加上n
                    if(k%2!=0)
                        y = y+n;

                    n  = n*2;
                    x = x/2;
                    k = k/2; //同时使x,k右移，以便下一步判断
                }
                System.out.println(y);
            }
        	
    	}//while
        
        


        
    }
}
