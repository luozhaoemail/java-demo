package cn.Thread;
/**
 * 独立运算的时候，单独封装成一个线程，各个线程独自运行，互不干扰
 * @author luozhao
 *
 */
public class Test1 {

	public static void main(String[] args) {
	
		new Thread()  //1匿名类 Thread的子类
		{
			public void run()
			{
			   for(int i=0; i<80 ;i++)
			   {
				   System.out.println(currentThread().getName()+"-----"+i);
			   }
			}
		}.start();

		
		Runnable r =new Runnable() //2 Runnabale的子类
		{
			public void run()
			{
			   for(int i=0; i<80 ;i++)
			   {
				   System.out.println(Thread.currentThread().getName()+"***********"+i);
			   }
			}
		};
		new Thread(r).start();
		
		for(int i=0; i<80 ;i++) //3主线程
		{
			System.out.println(Thread.currentThread().getName()+"...................."+i);
		}
	}

}
