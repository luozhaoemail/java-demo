package design_pattern;

/**
2. 抽象工厂模式（Abstract Factory）
创建多个工厂类，这样一旦需要增加新的功能，直接增加新的工厂类就可以了，不需要修改之前的代码。
 */
public class Test_AbstractFactory 
{
	public static void main(String[] args)
	{
		food x = new FactoryForA().get();
	    x = new FactoryForB().get();
	}
	 
}

/**
//产品接口
interface food{}

//产品种类
class A implements food{}
class B implements food{}
class C implements food{}
 */

//创建多个工厂类，这样一旦需要增加新的功能，直接增加新的工厂类就可以了，不需要修改之前的代码。
//工厂接口
interface produce
{
	public food get();
}

//工厂A
class FactoryForA implements produce{
    @Override
    public food get() {
    	System.out.println("FactoryForA  生成了食物 A");
        return new A();
    }
}
//工厂B
class FactoryForB implements produce{
    @Override
    public food get() {
    	System.out.println("FactoryForB  生成了食物 B");
        return new B();
    }
}







