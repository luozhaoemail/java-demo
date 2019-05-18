package java2.thread;
/**
 * 接口法 ：好处是线程类实现了Runnabale接口，还能继承其他的类
 * @author luozhao
 *
 */
public class Test2 {

	public static void main(String[] args) {
		
		Ticket t = new Ticket();//这不是线程,为多个线程共享
		
		Thread th = new Thread(t);//Thread及其子类才能创建线程 Thread0
		th.start();
		
		new Thread(t).start();//Thread1
		new Thread(t).start();//Thread2
		new Thread(t).start();//Thread3

	}

}

class Ticket implements Runnable
{	
	int tick=100;
	Object obj = new Object();
	@Override
	public void run() {	
					
		synchronized(obj) //代码同步，解决共享数据安全问题  ，就是加锁，如同火车上的卫生间，某一时刻只能一个人使用
		{//缺点是比较消耗资源
			while(tick>0) //因为tick有两处运算tick>0 和tick--，所以在这里加锁
			{
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {}
				
				System.out.println(Thread.currentThread().getName()+"........"+ tick--);
				//如果不加同步块，就会出现-1 -2
			}
		
		}
		
	}
	
}
