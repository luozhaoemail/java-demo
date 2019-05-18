package view.acm;

import java.text.DecimalFormat;
import java.util.*;

public class T2 {
	
	//跑步，求坐标
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		double L= sc.nextDouble();
		double R= sc.nextDouble();
		double t = L/R;//弧度
		
		double x = R * Math.cos(t); 
		double y = R * Math.sin(t); 
		
		DecimalFormat df = new DecimalFormat("0.000");
		System.out.println(df.format(x)+" "+df.format(y));
		System.out.println(df.format(x)+" "+df.format(-y));
		
		/**小明绕着坐标圆跑步，起始地址为（r,0） ,沿着圆弧最后跑的长度为L，求最后的坐标。
		 * 圆的坐标：x=r*cos(n), y=r*sin(n)
		 * 关键是求旋转角度n 
		 * 圆弧公式： L= n*r
		 * 1°对应的弧长= 2*PI*R/ 360 = PI*R/180
		 * x°对应的弧长= x*PI*R/180
		 * 角度制弧长 L =  x*PI*R/180
 		 * 
		 * 而 360°=2*PI, 180°=PI , 1°=PI/180 弧度 ， x° = x*PI/180 =t 弧度
		 * 因此弧度制弧长  L = t*R , t表示弧度大小 
		 */
		
		
		
	}
	
	
}
