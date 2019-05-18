package view.niuke.hw;

import java.util.*;

public class Main2 {

	public static void main(String[] args)
	{
		f7();
	}
	
	/**给定n个字符串，请对n个字符串按照字典序排列。
	 	
	 */
	public static void f7()
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String str = Integer.toBinaryString(n); 
		List<String> l = new ArrayList<String>();
 		for(int i=0;i<n;i++)
			l.add(sc.next());
 		
 		Collections.sort(l);
 		for(int i=0;i<n;i++)
 			System.out.println(l.get(i));
	}
	
	/**
	 输入一个int整数或者字符串,将这个整数以字符串的形式逆序输出
	 逆序输出就是先将其存到数组，逆序遍历输出即可
	 1516000  =>  0006151
	 */
	public static void f6()
	{
		Scanner sc = new Scanner(System.in);
		char[] ch = sc.nextLine().toCharArray();
		//逆序打印数组即可
		 for(int i=ch.length-1; i>=0; i--)
             System.out.print(ch[i]);
		 System.out.println();
	}
	
	/**
	 计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)。不在范围内的不作统计。
	 */
	public static void f5()
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
        {
			/*String str =sc.nextLine();
			String rst="";
			for(int i=0;i<str.length(); i++)
			{
				char ch = str.charAt(i);
				if(ch>=0 && ch<=127)
				{
					String s = String.valueOf(ch);
					if(!rst.contains(s))
						rst += s;
				}
			}
			
			System.out.println(rst.length());*/
			
			//方法2
			String str =sc.nextLine();
			int n = str.length();
			int [] a =new int[128];//定义一个位组
			for(int i=0;i<n;i++)
		        a[str.charAt(i)]=1; //把相应的位置1  类似于bloomfilter
		    
			int x=0;
			for(int i=0;i<a.length;i++)//统计位组中位1的个数
				if(a[i]==1)
					x++;
			
			System.out.println(x);
			
        }
	}
	
	/**
	 按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
	 9876673
	 37689
	 */
	public static void f4()
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
        {
			String n = sc.nextLine();
			String rst="";
			for(int i=n.length()-1;i>=0; i--)//从右向左
			{
				String s=n.charAt(i)+"";
				if(!rst.contains(s))
					rst += s;
			}
			
			System.out.println(rst);
			
			
        }  	
		
	}
	
	/**数据表记录包含表索引和数值，请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
	4
	0 1  合并后     0 3
	0 2   		1 2
	1 2   		3 4
	3 4
	 */
	public static void f3()
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
		{
			int n = sc.nextInt();	
			Map<Integer,Integer> map = new TreeMap<Integer,Integer>();
			for(int i=0;i<n;i++)
			{
				int index=sc.nextInt();
				int value=sc.nextInt();
				if(map.containsKey(index))
					value += map.get(index);			
				map.put(index, value);		
				
				/*
				if(map.containsKey(index))
					map.put(index, value+map.get(index));
			    else
                    map.put(index, value);*/	
			}	
						
			for(Map.Entry<Integer,Integer> entry :map.entrySet())
			{
				System.out.println(entry.getKey()+" "+entry.getValue());
			}
			
		}
	}
	
	/**接受一个正浮点数值，输出该数值的近似整数值。四舍五入
	 * 如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
	 
	 */
	public static void f2()
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
		{
			double x = sc.nextDouble();
			
			//方法1
			int y = (int) x;
    		y = (x-y) >= 0.5 ? y+1 : y; 
    		
    		//方法2
//			double z= x-y;
//			if(z>=0.5) 
//				y++;
    		
    		//方法3
    		 x += 0.5; //大于0.5的+0.5已经进1了，小于0.5的+0.5没有进1
    		 y=(int) x;
    		
			System.out.println(y);
		
		}
	}
	
	/**输入一个正整数，按照从小到大的顺序输出它的所有质数的因子
	     把输入的整数因式分解，只不过因子必须都是质数
	     例如：180 = 2 * 2 * 3 * 3 * 5；90 = 2 * 3 * 3 * 5；而不是找出所有的质数因子
	 */
	public static void f1()
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
		{
			String rst="";
			long n = sc.nextLong();
			for(int i=2;i<=n;i++) //180的因数范围｛2~180｝
			{
				while(n % i == 0) //对于每一个可能的因数，都要一直除到质数为止，出道不能整除。
				{
					n= n / i; // 180/2=90 90/2=45 45/2不能整除 ，45/3=15 15/3=5 5/3不能整除，
					rst += i+" ";//5/4不能整除，5/5=1。结束
				}
			}
			
			System.out.println(rst);
			
		}
	}
	//区别：求n以内的所以质数（素数）
	public static void f1_2()
	{
		long n = 100;
		String rst="";
		boolean flag=true;
		int count =0;
		for(int i=2; i<=n; i++)//找2~1000之间素数
		{
			flag=true;
			//判断i是不是素数
			for(int j=2; j<=Math.sqrt(i); j++)//条件也可以写成：j<=i/2   j*j<=i , j<=Math.sqrt(i); 
			{
				if(i % j == 0)//如果i能被某个数j整数，则i不是素数
				{
					flag=false;			
					break;
				}
			}	
			
			if(flag==true)
			{
				rst += i +" ";
				count++;
			}
		}
		System.out.println("共有"+count+"个素数：");
		System.out.println(rst);
	}

}
