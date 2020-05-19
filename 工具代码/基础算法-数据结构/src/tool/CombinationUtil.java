package tool;

public class CombinationUtil {

	public static void main(String[] args) {
		System.out.println("3!="+factorial(3));
		System.out.println("A<3,2>="+arrangement(3,2));
		System.out.println("C<3,2>="+combination(3,2));

	}
	/**排列组合
	 * A(n,m) = n!/(n-m)!
	 * C(n,m) = n!/(m!*(n-m)!)
	 * 因此核心在于阶乘
	 */
		
	public static long factorial(int n)
	{
		long sum=1;
		while(n>0)
			sum *= n--;
		return sum;
	}
	
	//A(n,m) = n!/(n-m)!
	public static long arrangement(int n, int m)
	{
		return n>=m ? factorial(n)*factorial(n-m) : 0;//要求：n>=m
	}
	
	//C(n,m) = n!/(m!*(n-m)!) = A(n,m)/A(m,m)
	public static long combination(int n, int m) 
	{
		return n>=m ? factorial(n)/(factorial(m)*factorial(n-m)) : 0;
		//return n>=m ?arrangement(n,m)/arrangement(m,m) : 0 //
	}

}
