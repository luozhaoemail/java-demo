package java2.thread;
/**
 * 同步函数 ：它使用的锁是this
 * @author luozhao
 *
 */
public class Test3 {

	public static void main(String[] args) {
		Cus c= new Cus();
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
		t1.start();//Thread0
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {}
		c.f=false;
		t2.start();//Thread1
		
		//多个线程使用同一个锁才能线程安全
	}

}

class Bank{
	int num;	
	
	public synchronized void add(int n ){ //同步函数
		
		num += n;  //1		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {}
		
		System.out.println("num= "+ num);//2 这两个地方都在操作num，所以要线程同步
	}
	/**
	 public static synchronized void show(int n ){ //静态的同步函数，使用的是锁是字节码文件对象Bank.clsss
	 synchronized(Bank.clsss){}	
	 这样这两个线程同步时才是使用的同一个锁。
	}
	 */

}

class Cus implements Runnable{
	Bank b = new Bank();
	boolean f = true;
	
	@Override
	public void run() {
		if(f)
		{
			synchronized(this){
				for(int i=0;i<3;i++)
				{
					b.num += 100;
					System.out.println("//////num= "+ b.num);//2 这两个地方都在操作num，所以要线程同步
				}
			}
				
		}else
		{
			for(int i=0;i<3;i++)
				b.add(100);
		}
			
		}
		
}
