package design_pattern;
/**
java的设计模式大体上分为三大类：
创建型模式（5种）：工厂方法模式，抽象工厂模式，单例模式，建造者模式，原型模式。
结构型模式（7种）：适配器模式，装饰器模式，代理模式，外观模式，桥接模式，组合模式，享元模式。
行为型模式（11种）：策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、
				      备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。
 
 
 * 1.静态工厂模式
 常用的工厂模式是静态工厂，利用static方法，作为一种类似于常见的工具类Utils等辅助效果，一般情况下工厂类不需要实例化。
 */

public class Test_StaticFactory 
{	
	//客户端代码只需要将相应的参数传入即可得到对象
    //用户不需要了解工厂类内部的逻辑。
	public static void main(String[] args)
	{
		food x = get("A");
		System.out.println(x);
		
		x = get("B");
		System.out.println(x);
		
		x = get("C");
		System.out.println(x);
	}
	
	
	public static food get(String name)
    {
        food x = null ;
        if ( name.equals("A")) 
        {
            x = StaticFactory.getA();
        }
        else if ( name.equals("B"))
        {
            x = StaticFactory.getB();
        }
        else 
        {
            x = StaticFactory.getC();
        }  
        
        return x;
    }
	 
}



//调用接口
interface food{}

//种类
class A implements food{}
class B implements food{}
class C implements food{}

//一个工厂
class StaticFactory 
{
    private StaticFactory(){}
    
    public static food getA()
    {  
    	System.out.println("一个工厂生成了食物 A");
    	return new A(); 
    }
    
    public static food getB()
    {  
    	System.out.println("一个工厂生成了食物 B");
    	return new B();     
    }
    
    public static food getC()
    { 
    	System.out.println("一个工厂生成了食物 C");
    	return new C();    
    } 
    
    
}



