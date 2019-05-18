package cn.Thread;

import java.util.concurrent.locks.*;

/**
 * 生产者-消费者    多个生产者，多个消费者，但是生产一个立即消费一个 
 *Lock 实现提供了比使用 synchronized 方法和语句可获得的更广泛的锁定操作。
 *此实现允许更灵活的结构，可以具有差别很大的属性，可以支持多个相关的 Condition 对象。  
 *Condition 实例实质上被绑定到一个锁上。要为特定 Lock 实例获得 Condition 实例，请使用其 newCondition() 方法。 
 ** Lock 替代了 synchronized 方法和语句的使用，Condition 替代了 Object 监视器方法的使用。
 * Condition 实例实质上被绑定到一个锁上。
 * @author luozhao
 *
 * */
class Resource2{
	private String name;
	private int count =1;
	private boolean flag =false;
	
	private Lock lock = new ReentrantLock();	//一个可重入的互斥锁
	private Condition con_1 =lock.newCondition(); //多个Condition对象
	private Condition con_2 =lock.newCondition();
 	
	public  void Set(String name) throws InterruptedException
	{
		lock.lock();
		try{
			while(flag)
				con_1.await();//生产者等待
			this.name = name+ count++;
			System.out.println(Thread.currentThread().getName()+"*****生产者："+this.name);
			flag = true;
			//实现本方唤醒对方操作，而不用全部唤醒
			con_2.signal();  //消费者唤醒
			//con.signalAll();
		}
		finally{
			lock.unlock();
		}
	}
	
	public void Out() throws InterruptedException
	{
		lock.lock();
		try{
			while(!flag)//循环判断
				con_2.await();//消费者等待
			System.out.println(Thread.currentThread().getName()+"----------消费者："+this.name);
			flag =false;
			con_1.signal();//生产者唤醒
		}
		finally{
			lock.unlock();
		}
	}
}


class Producer2 implements Runnable{
	private Resource2 res;
	
	public Producer2(Resource2 res) {
	   this.res = res;
	}
	
	public void run(){
		while(true){
			try {
				res.Set("商品");
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
}

class Consumer2 implements Runnable{
	private Resource2 res;
	
	public Consumer2(Resource2 res) {
	   this.res = res;
	}
	
	public void run(){
		while(true){
			try {
				res.Out();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
}


public class Produce_Consume2 {
	public static void main(String[] args) {
	
		Resource2 r = new Resource2();
		new Thread(new Producer2(r)).start();
		new Thread(new Producer2(r)).start();
		new Thread(new Consumer2(r)).start();
		new Thread(new Consumer2(r)).start();
		
	}

}
