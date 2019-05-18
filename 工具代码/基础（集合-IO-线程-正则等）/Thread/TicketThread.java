package cn.Thread;

public class TicketThread {

	public static void main(String[] args) {
		/*Ticket t1 = new Ticket();
		Ticket t2 = new Ticket();
		Ticket t3 = new Ticket();
		*/
		Ticket t = new Ticket();
		Thread t1 =new Thread(t);
		Thread t2 =new Thread(t);
		Thread t3 =new Thread(t);
		
		t1.start();
		t2.start();
		t3.start();

		 
	}

}


class Ticket implements Runnable {//extends Thread{
	private static  int num = 300;  //共享
	
	public void run(){
		
		/*while(true){
			if(num>0){	
				try{
					Thread.sleep(10);
				}catch(Exception e){
					
				}
				System.out.println(Thread.currentThread()+"卖出："+ num--);				
				}
			else 
				break;
			
		}*/
		while(true){
			
			synchronized(new Object()) //代码同步，解决共享数据安全问题
			{  
			   if(num>0){				
				System.out.println(Thread.currentThread()+"卖出："+ num--);				
				}
			   else
				   break;
			}
		}
	}
	
}
