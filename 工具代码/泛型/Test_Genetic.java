package genetic;

import java.util.Random;

/**
 * 泛型有三种使用方式，分别为：泛型类、泛型接口、泛型方法
 * 泛型类,泛型接口，是在实例化类的时候指明泛型的具体类型；
 * 泛型方法，是在调用方法的时候指明泛型的具体类型 。
 */
public class Test_Genetic {
	public static void main(String[] args) {
		//泛型类
		Generic<String> g = new Generic<String>("hello");
		Console.out(g);	//泛型方法	
		Generic<Integer> g2 = new Generic<Integer>(123);
		Console.out(g2);		
		Generic g3 = new Generic(1.111);
		Console.out(g3);
		
		showKeyValue(g);
		
		//泛型接口
		Generator<Double> inter = new FruitGenerator<Double>();
		inter.next();
		Generator<String> inter2 = new RandomGenerator();
		Console.out(inter2.next());
	}
	
	//这不是一个泛型方法，这是一个普通的方法，只不过使用了泛型通配符?
    //?是一种类型实参，可以看做为所有类的父类
	 public static void showKeyValue(Generic<?> obj)
	 {
		 Console.out("泛型测试: " + obj.getKey());
	 }
}



//此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
//在实例化泛型类时，必须指定T的具体类型
//最典型的就是各种容器类，如：List、Set、Map。
class Generic<T>
{ 
    //key这个成员变量的类型为T,T的类型由外部指定  
    private T key;

    public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    //虽然在方法中使用了泛型，但是这并不是一个泛型方法。
    //这只是类中一个普通的成员方法，只不过他的返回值是在声明泛型类已经声明过的泛型。
    //所以在这个方法中才可以继续使用 T 这个泛型。
    public T getKey(){ //getKey的返回值类型为T，T的类型由外部指定
        return key;
    }

	@Override
	public String toString() {
		return "Generic [key=" + key + "]";
	}    
    
}

//定义一个泛型接口
interface Generator<T> {
  public T next();
}

class FruitGenerator<T> implements Generator<T>{
    @Override
    public T next() {
    	System.out.println("----泛型接口");
        return null;
    }
}
//或者
class RandomGenerator implements Generator<String> {

    private String[] fruits = new String[]{"Apple", "Banana", "Pear"};

    @Override
    public String next() {
        Random rand = new Random();
        return fruits[rand.nextInt(3)];
    }
}


class Console
{
	//泛型方法
	/**
	 * 1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
	 * 2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
	 * 3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
	 * 4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
	 * @param out
	 */
	public static <T> void out(T out)
	{
		System.out.println(out);
	}
}
