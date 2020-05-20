package design;

import java.util.Vector;

public class TestEventListener2 {

	public static void main(String[] args) {
		//注册事件
		A a = new A();
		B b = new B();
		EventSource2.addListener(a);
		EventSource2.addListener(b);
		
		
		//可以在其他类中使用事件
		EventSource2.sendEvent(new EventObject2("shijianA"),100);
		EventSource2.sendEvent(new EventObject2("shijianB"),1.1,1,2);
	}

}


class A implements EventListener2{
	@Override
	public void handleEvent(EventObject2 event,Object... args){
		//todo
		if(event.getSource().equals("shijianA")){
			int a = (int)args[0];
			System.out.println("接收事件="+event.getSource()+" 的参数="+a);
		}
	}
}

class B implements EventListener2{
	@Override
	public void handleEvent(EventObject2 event,Object... args){
		//todo
		if(event.getSource().equals("shijianB")){
			System.out.println("接收事件="+event.getSource()+" 的参数="+args[0]+", "+args[1]);
		}
	}
}



//事件对象
class EventObject2 extends java.util.EventObject{

	public EventObject2(Object source) {
		super(source);
	}
	
	public void doEvent(){
        System.out.println("通知一个事件源 source :"+ this.getSource());
    }
	
}



//事件源
class EventSource2 {
   //监听器列表，监听器的注册则加入此列表
    private static Vector<EventListener2> list = new Vector<EventListener2>();
    
    //注册监听器
    public static void addListener(EventListener2 eventListener){
    	list.add(eventListener);
    }
    //撤销注册
    public static void removeListener(EventListener2 eventListener){
    	list.remove(eventListener);
    }
    
    //通知所有的注册者，并执行他们的handleEvent方法
    public static void sendEvent(EventObject2 event, Object... args){        
        for(EventListener2 eventListener: list)
        {
            eventListener.handleEvent(event, args);
        }
    }
}


//监听器接口
interface EventListener2 extends java.util.EventListener {
	//事件处理
	public void handleEvent(EventObject2 event, Object... args);
}


