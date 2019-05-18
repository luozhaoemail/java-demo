package java2.thread;

/**
 * 生产者-消费者    多个生产者，多个消费者，但是生产一个立即消费一个 
 * @author luozhao
 *
 */
class Resource{
	private String name;
	private int count =1;
	private boolean flag =false;
	
	public synchronized void Set(String name){
		
		while(flag) //循环判断  while保证，每次被wait的线程在醒了之后，都得再次判断标记
			try {
				this.wait();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		
		this.name = name+(count++);
		System.out.println(Thread.currentThread().getName()+"*****生产者："+this.name);
		flag = true;
		this.notifyAll();  ////循环判断容易造成所有线程全部等待，所以要全部唤醒
	}
	
	public synchronized void Out(){
		while(!flag)//循环判断
			try {
				this.wait();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		System.out.println(Thread.currentThread().getName()+"----------消费者："+this.name);
		flag =false;
		this.notifyAll();
	}
}


class Producer implements Runnable{
	private Resource res;
	
	public Producer(Resource res) {
	   this.res = res;
	}
	
	public void run(){
		while(true){
			res.Set("ppp");
		}
	}
}

class Consumer implements Runnable{
	private Resource res;
	
	public Consumer(Resource res) {
	   this.res = res;
	}
	
	public void run(){
		while(true){
			res.Out();
		}
	}
}


public class Test7 {
	public static void main(String[] args) {	
		Resource r = new Resource();
		
		new Thread(new Producer(r)).start();
		new Thread(new Producer(r)).start();
		new Thread(new Consumer(r)).start();
		new Thread(new Consumer(r)).start();
		
	}

}
