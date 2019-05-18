package view.niuke.offer;

import java.util.*;

public class Solution {

	public static void main(String[] args) 
	{
		for(int i=1;i<=5; i++)
		{
			push(i);
		}
		System.out.println("stack1="+stack1);
		System.out.println("stack2="+stack2);
		
		for(int i=1;i<=5; i++)
		{
			System.out.print(pop()+"  ");
		}
		System.out.println("\nstack1="+stack1);
		System.out.println("stack2="+stack2);
	}
	
	/**用两个栈来实现一个队列，完成队列的Push和Pop操作。
	        栈A用来作入队列
		栈B用来出队列，当栈B为空时，栈A全部出栈到栈B, 栈B再出栈（即出队列）
	 * 
	 */
	public static Stack<Integer> stack1 = new Stack<Integer>();
	public static Stack<Integer> stack2 = new Stack<Integer>();
	
	public static void push(int x)
	{
		stack1.push(x);//入队
	}
	public static int pop()
	{
		 if(stack1.empty() && stack2.empty())//队空
	          throw new RuntimeException("Queue is empty!");
	     
		
		if(stack2.isEmpty())//必须先将栈B清空
		{
			while(!stack1.isEmpty())//将栈A的数一次性全部转移到栈B
			{
				stack2.push(stack1.pop());
			}
		}
		
		return stack2.pop();//如果栈B不空，先不慌转移A的数，直接出栈到b空
	}

}
