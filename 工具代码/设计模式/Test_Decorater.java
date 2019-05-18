package design_pattern;
/**
 7.装饰模式（Decorator）
  动态地给一个对象添加一些额外的职责，相比生成子类更为灵活
 在Java源码中典型的装饰者模式就是java I/O, 其实装饰者模式也有其缺点，就是产生了太多的装饰者小类，有类爆炸的趋势。
 */
public class Test_Decorater {

	public static void main(String[] args) {
		//定义被装饰者Component1
		InterfaceComponent c1 = new Component1();
		c1.say();
		
		System.out.println();
		AbstractDecorator dct = new Decorator1(c1);
		dct.say();
		
		System.out.println();
		dct = new Decorator2(new Decorator1(c1));
		dct.say();
		
		/**输出
		功能1 + 
		功能1 + 新增功能A + 
		功能1 + 新增功能A + 新增功能B + 
		 */

	}

}


//定义公共接口
interface InterfaceComponent{ 
	public void say();   
}

class Component1 implements InterfaceComponent{   
    @Override
    public void say() { 
        System.out.print("功能1 + ");   
    }   
 }


//抽象装饰者角色Decorator——装饰类
abstract class AbstractDecorator implements InterfaceComponent{   
	private InterfaceComponent component; //作为成员变量  
    
	//传入基础 功能1
    public AbstractDecorator(InterfaceComponent component){   
        this.component = component;   
    }
    
    @Override
	public void say() { 
    	component.say();//委派给原来的组件		
	}
}

//逐步细化功能
class Decorator1 extends AbstractDecorator{
	public Decorator1(InterfaceComponent component) {
		super(component);	
	}
	
	@Override
	public void say() {
		super.say();
		add1();
	}
	
	//新增特性
	public void add1(){ 
        System.out.print("新增功能A + ");  
	}
	
}

//逐步细化功能
class Decorator2 extends AbstractDecorator{	
	public Decorator2(InterfaceComponent component) {
		super(component);	
	}
	
	@Override
	public void say() {
		super.say();
		add2();  
	}
	
	//新增特性
	public void add2(){ 
        System.out.print("新增功能B + ");  
	}
	
}
	
	