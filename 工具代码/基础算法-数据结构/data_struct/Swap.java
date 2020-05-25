package tool;

/**两个变量交换的3种方法
 */
public class Swap {

	public static void main(String[] args) {
		int[] a={5,10};
		swap3(a);
		ArrayUtil.print(a);
		getMax(a);
	}
	
	
	//1.利用第三个变量交换数值，最简单的方法
	public static void swap1(int[] a) //技巧：利用数组的地址引用传参数，并作为返回值
	{
		int tmp = a[0];
		a[0] = a[1];
		a[1] = tmp;
	}

	//2.用两个数 求和 然后 相减 的方式进行交换,弊端在于如果 x 和 y 的数值过大的话，超出 int 的值会损失精度。
	public static void swap2(int[] a) 
	{
		a[0] = a[0]+a[1];  //  5+10      x+y求和
		a[1] = a[0]-a[1];  // (5+10)-10=5,  求和后的值减去本身就得到另外一个值
		a[0] = a[0]-a[1];  // (5+10)-((5+10)-10)= (5+10)-5 =10   求和后的值减去算出来的另外一个值，就得到本身
	}


	//3.位运算，利用的思想原理是：一个数 异或^ 同一个数两次，结果还是那个数(a=a^b^b)，而且不会超出int范围
	public static void swap3(int[] a) 
	{
		a[0] = a[0]^a[1];  //  5+10      x+y求和
		a[1] = a[0]^a[1];  // 利用了a[0]本身进行缓存的效果 (a[0]^a[1])^a[1] =a[0]
		a[0] = a[0]^a[1];  // 利用了a[1]本身进行缓存的效果 (a[0]^a[1])^ {(a[0]^a[1])^a[1]} =a[1]
	}
	
	// 三目运算符 求最大值和最小值
	public static void getMax(int[] a){
		int x= a[0]>=a[1] ? a[0]: a[1];
		System.out.println("max="+x);

		int y = a[0]<a[1] ? a[0]: a[1];
		System.out.println("min="+y);
	}

}
