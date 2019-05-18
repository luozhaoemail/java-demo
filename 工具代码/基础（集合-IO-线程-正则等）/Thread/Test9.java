package java2.thread;
/**
 * 停止线程：让run方法结束
 * 多线程运行其实循环结构，只要控制住循环就能停止线程
 * 
 * 特殊情况：
 * 当线程处于冻结状态，读不到标记，线程就不会结束 
 *Thread类的interrupt()方法中断线程，强制把冻结的线程恢复到运行，再操作标记来结束线程
 */
public class Test9 {

	public static void main(String[] args) {
		
		StopThread st = new StopThread();
		Thread t1 =new Thread(st);
		Thread t2 =new Thread(st);
		
		//t1.setDaemon(true);设为守护线程也能结束程序，因为当main()方法结束后，程序终结，同时JVM也关闭守护线程。
		//t2.setDaemon(true);
		t1.start();
		t2.start();
		
		int num = 0;
		while(true){
			if(num++ ==60){
				//st.change();//t1和t2都在等待状态。所以执行不到这一句
				//t1.interrupt();//只有用interrupt()方法中断线程，才能让其会运行状态
				//t2.interrupt();
				break;
			}
			System.out.println(Thread.currentThread().getName()+"------- "+num);
		}
		
		System.out.println("gameover");
	}

}

class StopThread implements Runnable
{  boolean f = true;

	@Override
	public synchronized void run() {
		while(f)
		{
			try {
				wait();//t1和t2都在等待状态。
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName()+".....Exception");
				f=false;
			}			
			System.out.println(Thread.currentThread().getName()+"+++++++RUN");
		}
		
	}
	
	public void change(){
		f=false;
	}
	
}