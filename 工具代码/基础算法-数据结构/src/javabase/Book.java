package javabase;

public class Book {
	static
    {
        System.out.println("1 最先初始化主类静态块");
    }

	public static void main(String[] args) //初始化阶段：调用main()时先初始化这个主类
    {
        staticFunction();
    }
	/**如果不要这一句：static Book book = new Book()
	 	1 最先初始化主类静态块
		4 书的静态代码块
		5 书的静态方法
	 
	 
	 */

    //static Book book = new Book(); //准备阶段：类变量 初始化为 null
    //这条语句又触发了类的实例化

    static  //这个静态块的执行顺序 在 static Book book = new Book()后面
    {
        System.out.println("4 书的静态代码块");
    }

    //构造代码块,每次调用构造方法前，都会先执行构造代码块。
    {
        System.out.println("2 书的普通代码块--构造代码块");
    }
    Book()
    {
        System.out.println("3 书的构造方法");
        System.out.println("3 price=" + price +",amount=" + amount);
    }

    public static void staticFunction(){
        System.out.println("5 书的静态方法");
    }

    int price = 110;
    static int amount = 112; //准备阶段：类变量 初始化为 0
    
}
