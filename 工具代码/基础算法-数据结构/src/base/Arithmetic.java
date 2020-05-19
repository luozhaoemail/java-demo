package base;

/**
 * 问题:
 *     编写算法实现计算一个包含加减乘除四则运算的字符串表达式的值（暂不考虑括号）。例如：2+5*4-6/2
 * 思路：
 *     整个表达式分四趟运算，一趟计算乘法，二趟计算除法，三趟计算加法，四趟计算减法。
 *     每一趟从头开始截取一个最简表达式（两个数和一个运算符）运算，将运算结果放回表达式原位置，然后再截取计算，这个过程递归进行。
 *
 */
//		System.out.println(ex+" = "+complete(ai));
public class Arithmetic {

	public static void main(String[] args) {
		
		char[] ai = "1+2*3".toCharArray();
        int sum=ai[0]-'0';
		for(int i=1;i<ai.length; i+=2)
	    {
	        if(ai[i]=='+')  sum+=ai[i+1]-'0';
	        if(ai[i]=='-')  sum-=ai[i+1]-'0';
	        if(ai[i]=='*')  sum*=ai[i+1]-'0';
	        if(ai[i]=='/')  sum/=ai[i+1]-'0';
	    }
		
		System.out.println(sum);
		
	}
	
	public static double complete(String args) {
		String strResult = "";
		char[] operators = {'*','/','+','-'}; 
		for(int i=0;i<operators.length;i++) {
			strResult = subComplete(args,operators[i]);
			args = strResult;
		}
		return Double.parseDouble(strResult);
	}
	
	public static String subComplete(String args,char opera) {
		int operateIndex = 0;               //运算符索引
		int temp = 0;                       //临时变量，存放索引值
		double a = 0d;                      //数值a
		double b = 0d;                      //数值b
		String beforeOperaString = "";      //运算符之前的字符串
		String afterOperaString = "";       //运算符之后的字符串
		String strNumA = "";                //字符串类型的数字a
		String strNumB = "";                //字符串类型的数字b
		String strLeft = "";                //正在计算的最简表达式左边的串
		String strRight = "";               //正在计算的最简表达式右边的串
		String result = "";                 //运算结果（运算结果=最简表达式之前的字符串+最简表达式的值+最简表达式之后的字符串）
		
		operateIndex = args.indexOf(opera);
		if(operateIndex==-1) {
			return args;
		}
		//以运算符为界将字符串分为两节
		beforeOperaString = args.substring(0, operateIndex);           
		afterOperaString = args.substring(operateIndex+1);               
		//取出运算符两边的数，并得到正在计算的最简表达式左右两边的表达式串       
		temp = findCharIndex(beforeOperaString,false);
		strNumA= beforeOperaString.substring(temp==0?temp:temp+1);      
		if(temp!=0) {
			strLeft = beforeOperaString.substring(0, temp+1);     
		}
		temp = findCharIndex(afterOperaString,true);
		strNumB = afterOperaString.substring(0, temp==0?afterOperaString.length():temp);      
		if(temp!=0) {
			strRight = afterOperaString.substring(temp);         
		}
		a = Double.parseDouble(strNumA);
		b = Double.parseDouble(strNumB);
		if(opera=='*') result = strLeft+(a*b)+strRight;
		else if(opera=='/') result = strLeft+(a/b)+strRight;
		else if(opera=='+') result = strLeft+(a+b)+strRight;
		else if(opera=='-') result = strLeft+(a-b)+strRight;
		//打印
		System.out.println(result);
		return subComplete(result,opera);
	}
	
	/**
	 * 获取一个表达式中第一个或者最后一个运算符的索引值
	 * @param str - 被检查的字符串
	 * @param fromBegin - 如果true，用index查找最简表达式右边第一个运算符；
	 *                    如果false，用lastIndex查找最简表达式左边边最后一个运算符。
	 * @return 运算符索引
	 */
	public static int findCharIndex(String str,boolean fromBegin) {
		int index = 0;
		int temp = 0;
		if(fromBegin) {
			temp = str.indexOf('*');
			index = (temp!=-1)?temp:index;
			temp = str.indexOf('/');
			index = (temp!=-1 && (index==0 || temp<index))?temp:index;
			temp = str.indexOf('+');
			index = (temp!=-1 && (index==0 || temp<index))?temp:index;
			temp = str.indexOf('-');
			index = (temp!=-1 && (index==0 || temp<index))?temp:index;
			return index;
		}
		temp = str.lastIndexOf('*');
		index = (temp!=-1)?temp:index;
		temp = str.lastIndexOf('/');
		index = (temp!=-1 && (index==0 || temp>index))?temp:index;
		temp = str.lastIndexOf('+');
		index = (temp!=-1 && (index==0 || temp>index))?temp:index;
		temp = str.lastIndexOf('-');
		index = (temp!=-1 && (index==0 || temp>index))?temp:index;
		return index;
	}
	
}
