package design;

import java.util.Vector;

public class TestEventListener {

	public static void main(String[] args) {
		
		EventSource es = new EventSource();
		
		es.addListener(new EventListener() {
			@Override
			public void handleEvent(EventObject event) {
				event.doEvent();
				if(event.getSource().equals("closeWindows")){
                    System.out.println("doClose");
                }
			}
			
		});
		
		es.sendEvent(new EventObject("DDD"));
		es.sendEvent(new EventObject("closeWindows"));
		
		
		
		System.out.println("\n---------------------\n");
		//触发、监听 窗口关闭事件
		es.onCloseWindows(new EventListener() {
			@Override
			public void handleEvent(EventObject event) {
				event.doEvent();
				if(event.getSource().equals("closeWindows")){
                    System.out.println("doClose");
                }
			}
		});
		
		 //执行窗口关闭动作，它的内部会触发监听事件
        es.doCloseWindows();
        
	}

}




//事件对象
class EventObject extends java.util.EventObject{

	public EventObject(Object source) {
		super(source);
	}
	
	public void doEvent(){
        System.out.println("通知一个事件源 source :"+ this.getSource());
    }
	
}

//事件源
class EventSource {
   //监听器列表，监听器的注册则加入此列表
    private Vector<EventListener> list = new Vector<EventListener>();
    
    //注册监听器
    public void addListener(EventListener eventListener){
    	list.add(eventListener);
    }
    //撤销注册
    public void removeListener(EventListener eventListener){
    	list.remove(eventListener);
    }
    
    //通知所有的注册者，并执行他们的handleEvent方法
    public void sendEvent(EventObject event){        
        for(EventListener eventListener: list)
        {
            eventListener.handleEvent(event);
        }
    }
    
    
    //额外：模拟窗口点击事件  --监听
    public void onCloseWindows(EventListener eventListener){
        System.out.println("触发关闭窗口事件");
        list.add(eventListener);
    }
    //额外：模拟窗口点击事件  --动作
    public void doCloseWindows(){
        this.sendEvent(new EventObject("closeWindows"));
    }
}


//监听器接口
interface EventListener extends java.util.EventListener {
  //事件处理
  public void handleEvent(EventObject event);
}

