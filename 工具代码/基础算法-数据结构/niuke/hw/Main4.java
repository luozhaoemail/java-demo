package view.niuke.hw;

import java.util.*;
public class Main4 
{	
    public static void main(String[] args)
    {
    	List<String> lst =new ArrayList<String>();
        String str="(+ (* 2 3) (^ 4))";//.replaceAll(" ", "");
//    	String str="(+ 2 3)";
    	for(int i=0;i<str.length();i++)
        {        
        	String ch = str.substring(i,i+1);
        	if(ch.matches("\\d|\\+|\\*|\\^|\\(|\\)"))
        		lst.add(ch);
        }	
        System.out.println(lst); //[+, *, 2, 3, ^, 4]
        preCompute(lst);
    }
    
    public static class Node
    {
    	
    }
    
    public static void preCompute(List<String> lst)
    {
    	Stack<String> stk = new Stack<String>();
    	String ch ="";
    	for(int i=0; i<lst.size(); i++) 
    	{
    		ch = lst.get(i);
    		if(ch.matches("\\d|\\+|\\*|\\^|\\("))
    		{
    			stk.push(ch);    			
    		}    		
    		else if(ch.equals(")"))
    		{   
				int x = Integer.parseInt(stk.pop());//4
    			if(stk.peek().equals("^") )//单目
    			{
    				stk.pop();//^
    				stk.pop();//(
    				x++;
    				stk.push(x+"");
//    				System.out.println(stk);
    			}  
    			else
    			{
    				int y = Integer.parseInt(stk.pop()); //
    				if(stk.peek().matches("\\+|\\*") )//双目
        			{   
            			String op = stk.pop();
            			int z = opFun(x,y,op);
            			stk.pop(); //(
            			stk.push(z+"");
        			}
    			}
    			
    		}//
    		else	
    		{
    			continue;   			
    		}    		
    	}
    	System.out.println(stk.peek());    	
    }
    
    public static int opFun(int x,int y,String op)
    {
    	int z=0;
    	switch (op)
    	{
			case "*":
				z= x * y;
			break;
			case "+":
				z= x + y;
				break;			
			default:
				break;
		}
    	
    	return z;
    }
    
   
    
}