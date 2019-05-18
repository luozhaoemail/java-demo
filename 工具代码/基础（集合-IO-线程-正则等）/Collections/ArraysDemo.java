package Collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysDemo {

	public static void main(String[] args) {
		//toColl();
		toArr();
	}
	
	public static void toColl(){
		
		String[] arr = {"aaa","nnn","ccc"};
		List<String> list = Arrays.asList(arr);//把数组转换成列表集合
		sop(list);
		sop("Contains: "+list.contains("nnn"));//用集合的方法来操作数组的元素很方便
		//但是不能用集合的增删方法，因为数组长度固定。会引发UnsupportedOperationException
		
		//int[] num ={2,3,5}; //输出  [[I@15db9742]
		Integer[] num = {2,3,5};//输出  [2, 3, 5]
		List<Integer> li = Arrays.asList(num);
		sop(li);

		/**如果数组元素是对象，转成集合时直接变成集合的元素 如String Integer
		 * 如果数组元素是基本数据类型，转成集合时整个数组作为集合的一个元素  int
		 */
	}
	
	public static void toArr(){
		ArrayList<String> al = new ArrayList<String>();
		al.add("a");
		al.add("b");
		al.add("c");
		
		String[] str = al.toArray(new String[al.size()]);// 1 5
		/**1.将集合转为数组是为了限制操作，因为数组长度固定，转换后不能增删操作
		 * 2.指定数组长度小于集合的size就会新建一个数组，大于会则不会，就使用new出来的数组，并有空值
		 */
		sop(Arrays.toString(str));
		
		show(2,3,4,8,5,6);
		show("sss",2,22,5);
		
	}
	
	
	public static void sop(Object obj){
		System.out.println(obj);
	}
	
	public static void show(int... arr){ //可变参数，可以传入任意长度的参数。
		System.out.println(arr.length);
	}
	
	public static void show(String str,int... arr){ // 可变参数一定要在最后面
		System.out.println(arr.length);
	}

   /**
    * 静态导入 import static java.lang.System.*;  导入这个类 中所有的静态成员
    * 这样就不用写System了，直接写out.println();
    *  import static java.util.Arrays.*; 就不用写Arrays了
    */
	
}
