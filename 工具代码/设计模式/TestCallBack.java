package design;

public class TestCallBack {

	public static void main(String[] args) {
		//写法1
		Caller ca1 = new Caller();
		ca1.call_fun(new CallBack_son());
		
		//写法2：通过匿名函数省略了继承回调接口的类, 在spark算子中广泛的应用了回调函数
		Caller ca2 = new Caller();
		ca2.call_fun(new Callback_inter() {
			@Override
			public void call() {
	            System.out.println("回调成功了！");
				//todo here
			}
		});
		
		//对标多线程：也是一个回调函数的写法。Thread就是回调者，Runnable就是一个回调接口
		//写法1
		new Thread(new MyThread()).start();
		
		//写法2
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO
				 System.out.println("回调一个新线程！");  
			}
		}).start();
		

	}

}

class MyThread implements Runnable{
	@Override
    public void run(){                                                                                
		System.out.println("MyThread类的run（）方法在运行");
    }
}


class CallBack_son implements Callback_inter{
    @Override
    public void call() {
        System.out.println("终于回调成功了！");  
      //todo here
    }
}


////////
class Caller{
	
	public void call_fun( Callback_inter inter) //传入回调接口作为参数
	{
		System.out.println("start callback...");
		inter.call();
		System.out.println("end callback...\n");
	}

}

////////核心就是定义一个接口，或者抽象类。然后通过它的方法进行调用子类的方法，向上转型。可以通过匿名函数实现它的方法。
interface Callback_inter{
	public void call();
}
