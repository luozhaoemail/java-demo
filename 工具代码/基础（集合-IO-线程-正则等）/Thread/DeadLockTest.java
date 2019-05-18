package cn.Thread;

class Test implements Runnable{
	private boolean flag;
    
	Test(boolean f){
		this.flag =f;		
	}
	
	@Override
	public void run() {     ///死锁例子
		 if(flag){
			 synchronized(Mylock.la){   //同步代码块
				 System.out.println(" if lock A ");
				 synchronized(Mylock.lb){
					 System.out.println("if lock B");
				 } 
			 } 
		 }
		 else{
			 synchronized(Mylock.lb){
				 System.out.println("else lock B");
				 synchronized(Mylock.la){
					 System.out.println("else lock A");
				 } 
			 }
		 }
	}
	
}

class Mylock{
	static Object la = new Object();
	static Object lb = new Object();
}

public class DeadLockTest {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Test(true));
		Thread t2 = new Thread(new Test(false));
		t1.start();
		t2.start();
	}
}
