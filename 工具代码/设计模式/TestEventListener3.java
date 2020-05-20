package design;

import java.util.Vector;

public class TestEventListener3 {

	public static void main(String[] args) {
		//注册消息
		Watch watch = new Watch();
		EventServer.addListener(watch);
		
		//工作流程：模块1 ----触发----> 模块2
		Test.do_fun1();
		
		System.out.println("\n--------\n");

		Test.do_fun2();
		

	}

}



//客户端接口
interface EventListener3 {
	/**每个客户端需要都实现这个接口，才能接收需要的消息，并触发相应的操作
	 * @Title: handleEvent 
	 * @param event 	      需要触发的消息值
	 * @param parameters   参数个数不限定，任意个
	 */
	public void handleEvent(int event, Object... parameters);
}

//消息枚举
class Msg{
	//定义消息命令的枚举值
	public static final int safety = 0;
	public static final int danger  = 1; 
	
    public static final int[] alarn = new int[]{
    	safety,
    	danger
    };
}

//服务端
class EventServer {
  //监听器列表，监听器的注册则加入此列表
  private static Vector<EventListener3> list = new Vector<EventListener3>();
  
  /**注册监听器
   * @Title: addListener 
   * @param event		注册的时候，提前将消息和对应的类绑定起来
   * @param eventListener
   */
  public static void addListener(EventListener3 eventListener){
  		list.add(eventListener);
  }//step1:将消息和事件监听接口绑定
  
  //撤销注册
  public static void removeListener(EventListener3 eventListener){
  		list.remove(eventListener);
  }
  
  //通知所有的注册者，并执行他们的handleEvent方法
  public static void sendEvent(int event, Object... args){        
      for(EventListener3 eventListener: list){
          eventListener.handleEvent(event, args);//这里会调用监听接口的方法
      }//step2:根据消息调用对应的事件监听接口之类
  }
}


//模块1
class Test{
	public static void do_fun1(){
		System.out.println("模块1：开始工作do_fun1");
		EventServer.sendEvent(Msg.safety, null);
	}
	
	public static void do_fun2(){
		System.out.println("模块1：开始工作do_fun2");
		EventServer.sendEvent(Msg.danger, null);
	}
}

//模块2
class Watch implements EventListener3
{
	public void handleEvent(int event, Object... args){
		//todo
		switch(event){
		    case Msg.safety://0
		    	System.out.println("模块2: 安全事件,开始下一步操作");
		    	break;	
		    	
		    case Msg.danger://0
		    	System.out.println("模块2: 危险事件,开始下一步操作");
		    	break;	
		    default: break;	
		}
	}
}


