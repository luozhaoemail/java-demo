package java2.thread;
/**
 * 等待唤醒机制  在同步中对同一把锁进行操作。
 * @author luozhao
 *1.当input发现Resource中没有数据时，开始输入，输入完成后，叫output来输出。如果发现有数据，就wait();
 *2.当output发现Resource中没有数据时，就wait() ；当发现有数据时，就输出，然后，叫醒input来输入数据。
 */
class Res{
	 String name;
	 String sex;
	 boolean flag= false;   //是否有资源的标志，初始默认没有资源
	
}

class Input implements Runnable{ //输入资源 
	private Res r;
	
	Input(Res rr){
		this.r = rr;
	}
	public void run(){
		int x =0;
		while(true){
			synchronized(r){
				if(r.flag)
					try {
						r.wait();//等待时间不确定，直到被手动唤醒
					} catch (InterruptedException e) {}
				if(x == 0 ){
					r.name="luozhao";
					r.sex ="man";
				}
				else{			
					r.name="丽丽";
					r.sex ="女";
				}
				x = (x+1)%2;//循环回到起点0 1 0 1 0 1  
				r.flag = true;//现在有资源了
				r.notify();//唤醒，唤醒线程池中的线程
			}
		}
		
	}
			
}

class Output implements Runnable{//输出
	private Res r;
	
	Output(Res rr){
		this.r = rr;
	}
	public void run(){
		while(true){
	      synchronized(r){
	    	 if(!r.flag)//没有资源时为假，非假为真，输出等待
				try {
					r.wait();
				} catch (InterruptedException e) {}
	 		 System.out.println(r.name+"......"+r.sex);
	 		 r.flag= false;
	 		 r.notify();//等待和唤醒是同一把锁，锁为任意的对象
	       }
		}
	}
			
}

public class Test6 {

	public static void main(String[] args) {
		Res r =new Res();  //资源
		
		Input in = new Input(r);  //输入线程
		Output out = new Output(r);//输出线程
		
		Thread t1 =new Thread(in);
		Thread t2 =new Thread(out);
		t1.start();
		t2.start();
		

	}

}
