package javabase;

public class TestClassLoad {
	static
    {
        System.out.println("最先初始化主类静态块");
    }

	public static void main(String[] args) {
		 System.out.println("爸爸的岁数:" + Son.factor);  //调用Son类继承的静态变量时，会先初始化它的父类，但不会初始化本类
		 /**
		  	最先初始化主类静态块
			爷爷在静态代码块
			爸爸在静态代码块   //先初始化 静态块  按照父>子顺序
			爸爸的岁数:25    //直接调用父类的静态变量，不会触发初始化自己
		  */
		
		//System.out.println("爸爸的岁数:" + new Son());  //new Son类时，会触发本类的初始化，但是要先初始化它的父类的静态代码，然后是父类的构造函数
		 /**
		  	最先初始化主类静态块  
			爷爷在静态代码块
			爸爸在静态代码块
			儿子在静态代码块   //先初始化 静态块  按照父>子顺序
			我是爸爸~        //再初始化 成员块  按照父>子顺序
			我是儿子~
			爸爸的岁数:javabase.Son@15db9742
		  */
	}

	/**类加载步骤: 加载、验证、准备、解析、初始化、使用、卸载
	 * 加载：将字节码加载到内存中，并在JVM方法区创建一个 Class对象
	 * 验证： 1 JVM规范校验（对字节流进行文件格式校验，判断是否符合 JVM 规范）
	 * 		 2  代码逻辑校验（对数据流和控制流进行校验）
	 * 准备：JVM开始为类变量分配内存并初始化，为类变量初始化零值，如果是被 final 修饰则是指定的值。
	 * 		 只会为「静态变量」分配内存，而不会为「类成员变量」分配内存。「类成员变量」的内存分配需要等到初始化阶段才开始。
	 * 解析：JVM 针对类或接口、字段、类方法、接口方法、方法类型、方法句柄和调用点限定符 7 类引用进行解析。这个阶段的主要任务是将其在常量池中的符号引用替换成直接其在内存中的直接引用。
	 * 初始化：有类初始化方法 和 对象初始化方法 ，类初始化方法一般在类初始化的时候执行。对象初始化方法一般在实例化类对象的时候执行。
	 * 		1  遇到 new、getstatic、putstatic、invokestatic触发，即实例化对象、读取或设置一个类的静态字段式触发
	 *      2  反射调用调用时触发
	 *      3  当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。
	 *      4 执行Main()时，会先触发初始化主类。
	 *      5 java.lang.invoke.MethodHandle调用方法句柄时触发
	 * 使用：JVM 便开始从入口方法开始执行用户的程序代码
	 * 卸载：JVM 便开始销毁创建的 Class 对象，最后负责运行的 JVM 也退出内存
	 * 
	 */
}



class Grandpa
{
    static
    {
        System.out.println("爷爷在静态代码块");
    }
}    

class Father extends Grandpa
{
    static
    {
        System.out.println("爸爸在静态代码块");
    }

    public static int factor = 25;

    public Father()
    {
        System.out.println("我是爸爸~");
    }
}

class Son extends Father
{
    static 
    {
        System.out.println("儿子在静态代码块");
    }

    public Son()
    {
        System.out.println("我是儿子~");
    }
}