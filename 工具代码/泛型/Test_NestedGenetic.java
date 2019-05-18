package genetic;

//1 泛型类中嵌套泛型方法
//2 可变参数
//3 静态方法与泛型
//4 泛型上下边界
public class Test_NestedGenetic {
	public static void main(String[] args) {
		
		Person person = new Person();
		GenerateTest<Person>  gg = new GenerateTest<Person>(person);
		gg.show_1(person);
		gg.show_2(person);
		gg.show_3(person);
		
		gg.printMsgs("luozhao",1.111,1234,"55.55");
		
		GenerateTest.out(person);
		
		//upBorder(gg);//类型不匹配
		upBorder(new GenerateTest<Integer>(11));//Integer是Number的子类。
		downBorder(new GenerateTest<Number>(11));//Number是Integer的父类。
		
		
	}
	
	
	
	//添加泛型的上下边界，必须与泛型的声明在一起 。
	//<? extends T>表示包括T在内的任何T的子类   <=T
	//<? super T>表示包括T在内的任何T的父类  >=T

	//上边界，即传入的类型实参必须是 指定类型的子类型，最高不能超过Number上界
	public static <T> void upBorder(GenerateTest<? extends Number> obj){
		System.out.println("上边界：" +obj.toString());
	}
	//这里表示最高只能使用Number
	
	//下边界,即传入的类型实参必须是 指定类型的父类型，最低不能低于Integer下界
	public static <T> void downBorder(GenerateTest<? super Number> obj){
		System.out.println("下边界：" +obj.toString());
	}
	//这里表示最低都要使用Number类型
	
}


class Person{
    @Override
    public String toString() {
        return "Person";
    }
}

class GenerateTest<T>    //<T extends Number>
{
	public T value;
		
	public GenerateTest(T value) {
		this.value = value;
	}	
	@Override
	public String toString() {
		return "GenerateTest [value=" + value + "]";
	}



	//泛型类的普通成员方法，使用泛型类声明的T
	public void show_1(T t){
        System.out.println(t.toString());
    }
	
	//在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
    //由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
	public <E> void show_2(E e){
		System.out.println(e.toString());
	}
	
	//在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
	//所以尽量不用这种，不容易区分
	//The type parameter T is hiding the type T
    public <T> void show_3(T t){
        System.out.println(t.toString());
    }
    
    
    //可变参数
    public <E> void printMsgs(E... args){
    	for(E t: args)
    		System.out.println("t is "+ t);
    }
    
    //静态方法无法访问类上定义的泛型；如果静态方法操作的引用数据类型不确定的时候，必须要将泛型定义在方法上。
    //即：如果静态方法要使用泛型的话，必须将静态方法也定义成泛型方法 。
    public static <E> void out(E e){
    	System.out.println(e.toString());
    }
       
}
