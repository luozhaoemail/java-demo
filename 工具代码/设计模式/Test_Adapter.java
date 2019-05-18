package design_pattern;


/**6.适配器模式（Adapter）它存在于不匹配的二者之间，用于连接二者，将不匹配变得匹配
 * 适配器模式有两种：类适配器、对象适配器、接口适配器
 
类适配：创建新类，继承源类，并实现新接口，例如 
class  adapter extends oldClass  implements newFunc{}
对象适配：创建新类保持源类的实例，并实现新接口，例如 
class adapter implements newFunc { private oldClass oldInstance ;}

适配器：A接口想使用B接口的方法，需要新建一个adapter类来调用B接口的方法给A接口(前提是实现A接口)使用。
以上两者的区别在于如何传递要使用的类的方法.
类适配是继承它的方法，而对象适配是把它作为参数传递进来，作为自己的成员变量。


接口适配：创建新的抽象类实现旧接口方法。例如 
abstract class adapter implements oldClassFunc { void newFunc();}
适用于一个接口中有很多方法，但只想实现其中几个方法，通过抽象类就不必全部实现。
 */
public class Test_Adapter {

	public static void main(String[] args) {
		//1 将PS2接口转换成USB接口
		PS2 ps2= new UsbAdapter();
		ps2.isPS2();
		
		//2
		ps2= new UsbAdapter2(new Usb3_0());
		ps2.isPS2();
		
		//3 只用了AA接口的其中两个方法
		AA aa = new Ashili();
		aa.a();
		aa.d();
	}

}


interface USB{
	void isUSB();
}

class Usb3_0 implements USB{
	@Override
	public void isUSB() {
		 System.out.println("USB 3.0接口");
	}	
}

//将PS2接口没有USB接口，那么就要借助UsbAdapter将其转换成USB接口
interface PS2{
	void isPS2();
}


//1 类适配*******************************************************
class UsbAdapter extends Usb3_0 implements PS2 {
	@Override
	public void isPS2() {
		System.out.println("使用父类Usb3_0的方法，转换成---USB 3.0接口");
		isUSB();
	}
	
}

//2 对象适配*******************************************************
//与类适配唯一不同的是把USB接口作为自己的对象
class UsbAdapter2 implements PS2
{    
    private USB usb;
 
    public UsbAdapter2(USB usb)
    {
        this.usb = usb;
    }
    @Override
    public void isPS2() {
    	System.out.println("调用传入参数Usb3_0方法，转换成---USB 3.0接口");
        usb.isUSB();
    }

}



//3 接口适配*******************************************************
interface AA {
    void a();
    void b();
    void c();
    void d();
    void e();
    void f();
}

//抽象类做适配器
abstract class Adapter implements AA {
    public void a(){}
    public void b(){}
    public void c(){}
    public void d(){}
    public void e(){}
    public void f(){}
}

//只实现了接口AA中的两个方法
class Ashili extends Adapter {
    public void a(){
        System.out.println("实现a方法被调用");
    }
    public void d(){
        System.out.println("实现d方法被调用");
    }
}