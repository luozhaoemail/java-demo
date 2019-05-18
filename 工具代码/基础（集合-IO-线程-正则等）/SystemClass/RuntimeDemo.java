 package cn.SystemClass;


/**
Runtime对象没有构造函数，静态方法直接类名调取
非静态的方法就要通过getXXX()方法获取 ，这叫单例模式，保证了类的唯一性
*/
public class RuntimeDemo {

	public static void main(String[] args) throws  Exception {
		
		Runtime r =  Runtime.getRuntime();//不能new，只能获取
		
		/**exec()执行字符串命令，返回Process子类的一个实例
		        创建一个进程来执行程序     转义字符\\ 表示  \
		*/
		Process p =  r.exec("C:\\11\\StikyNot.exe");
		
		Thread.sleep(4000);		
		p.destroy();//杀掉进程
		
		r.exec("H:\\Notepad++\\notepad++.exe C:\\11\\Luozhao.java");//打开文件
	}

}
