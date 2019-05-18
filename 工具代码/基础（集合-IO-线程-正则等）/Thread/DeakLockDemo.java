package cn.Thread;

class Ticknum  implements Runnable{
	 private int num =100;
	 Object c =new Object();
	 boolean flag = true;
	 
	 public void run(){
		 if(flag){
			 while(true){
				synchronized(c){  //同步代码块    object锁
					 /*if(num>0){
						 try{Thread.sleep(10);}
						 catch(Exception e){}
						 System.out.println(Thread.currentThread().getName()+"----code:"+ num--);
					 }	*/					 
				 }
				 show(); //show锁
			 }
		 }
		 else 
			 while(true)
				 show();
	 }
	 
	 public  synchronized void show(){   //同步函数  this  //show锁
		synchronized(c){    //  object锁
		 if(num>0){
			 try{Thread.sleep(10);}
			 catch(Exception e){}
			 System.out.println(Thread.currentThread().getName()+"++++++show:"+ num--);
		 }
		}
	 }
	 
}

public class DeakLockDemo {

	public static void main(String[] args) {
		Ticknum t =new Ticknum();
		Thread t1 =new Thread(t);
		Thread t2 =new Thread(t);
		t1.start();
		try{Thread.sleep(10);} catch(Exception e){}
		t.flag = false;
		t2.start();

	}

}
