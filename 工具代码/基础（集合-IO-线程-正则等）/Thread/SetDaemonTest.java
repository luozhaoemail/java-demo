package cn.Thread;
/**
 * JVM 在main()方法中先创建一个用户线程，再创建一个守护线程。当main()方法结束后，程序终结，同时JVM也关闭守护线程。
 * @author luozhao
 *
 */
public class SetDaemonTest {

	public static void main(String[] args) throws InterruptedException {
	   Thread t = new Thread(new Daemon(), "t");
		/*Daemon d = new Daemon();
		Thread t =new Thread(d);*/
		
		t.setDaemon(true); //守护线程
		
		t.start();
		Thread.sleep(200);
	    System.out.println("Finishing program");
	    
	}

}


class Daemon implements Runnable{
	@Override
	public void run(){
		while(true)
		{
			work();
		}
	}
	
	private void work(){
		
		try {
			System.out.println("wwwwwwwww");
			Thread.sleep(200);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
}