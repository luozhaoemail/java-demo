package cn.Thread;

public class JionTest {

	public static void main(String[] args) throws InterruptedException {
		
		Join j =new Join();
		Thread t1 =new Thread(j);
		Thread t2 =new Thread(j);
		t1.start();	
		
		t1.join();//main等待。t1先执行，t1完了后main才能恢复过来
		//t1.setPriority(Thread.MAX_PRIORITY); //优先级为10
		
		
		t2.start();
		
		/*for(int x =0;x<80;x++)
		{
			System.out.println(Thread.currentThread().toString()+"--------------"+ x);
		}*/
		System.out.println("The End");
	}

}

class Join implements Runnable{
	
	public void run(){
		for(int x =0;x<70;x++)
		{
			System.out.println(Thread.currentThread().toString()+"******"+ x);
			//Thread.yield();//临时暂停  让线程交替
		}
		
	}
}