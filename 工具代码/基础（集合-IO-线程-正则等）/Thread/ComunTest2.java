package cn.Thread;
/**
 * 等待唤醒机制  在同步中对同一把锁进行操作。
 * @author luozhao
 *1.当input发现Resource中没有数据时，开始输入，输入完成后，叫output来输出。如果发现有数据，就wait();
 *2.当output发现Resource中没有数据时，就wait() ；当发现有数据时，就输出，然后，叫醒input来输入数据。
 */
class Res2{
	private String name;
	private String sex;
	private boolean flag= false;//初始没有数据
    
	public synchronized void Set(String name, String sex){
		if(flag)
			try {
				this.wait();//flag为true 才wait，等输出了才再次输入
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		this.name =name;
		this.sex =sex;
		
		flag = true;  //输入了数据
		this.notify();   //操作完了唤醒等待线程
	}
	
	public synchronized void Out(){
		if(!flag)  
			try {
				this.wait();//flag为false 才wait，等有数据了下输出
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		System.out.println(name+"......"+sex);
		
		flag =false; //输出了数据
		this.notify();
	}
}

class Input2 implements Runnable{  
	private Res2 r;
	
	Input2(Res2 rr){
		this.r = rr;
	}
	public void run(){
		int x =0;
		while(true){			
				if(x == 0 )
					r.Set("luozhao", "man");				
				else	
					r.Set("丽丽","女");	
				
				x = (x+1)%2;	
		}
		
	}
			
}

class Output2 implements Runnable{
	private Res2 r;
	
	Output2(Res2 rr){
		this.r = rr;
	}
	public void run(){
		while(true){
	      r.Out();
		}
	}
			
}

public class ComunTest2 {

	public static void main(String[] args) {
		Res2 r =new Res2();  //资源
		
		new Thread(new Input2(r)).start();
		new Thread(new Output2(r)).start();			

	}

}
