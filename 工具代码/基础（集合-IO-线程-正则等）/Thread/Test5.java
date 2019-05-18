package java2.thread;
/**
 * 死锁例子
 * @author luozhao
 *
 */
public class Test5 {

	public static void main(String[] args) {
		Thread t1 = new Thread(new DeadLock(true));
		Thread t2 = new Thread(new DeadLock(false));
		t1.start();
		t2.start();
	}

}

class MyLock{
	static Object la = new Object();
	static Object lb = new Object();
}

class DeadLock implements Runnable{
	boolean f = true;
	public DeadLock(boolean ff){
		f=ff;
	}
	
	@Override
	public void run() {
		if(f){
		synchronized(MyLock.la)
		{ 	System.out.println(" if lock A ");
			synchronized(MyLock.lb)
			{
				 System.out.println(" if lock B ");
			}
		
		}
		}else{
			synchronized(MyLock.lb)
			{ 	System.out.println(" else lock B ");
				synchronized(MyLock.la)
				{
					 System.out.println(" else lock A ");
				}
			
			}
		}
	}
	
}