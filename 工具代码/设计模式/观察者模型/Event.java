package tool;

import java.util.*;
/**
观察者模式：定义了对象之间的一对多依赖，当一个对象状态发生改变时，其依赖者便会收到通知并做相应的更新
以松耦合方式在一系列对象之间沟通状态，独立复用 主题（Subject）/可观测者（Observable）和观测者（Observer）
 */

/**
 *  主题(发布方) 即服务端
 */
public class Event 
{	
	//EventHandler是客户端(订阅者)接口
	private static Map<Integer,Set<EventHandler>> eventMap = new HashMap<Integer,Set<EventHandler>>();	
	
	/**1、客户端的观察者注册
     * 
     * @param event-----消息类别
     * @param handler----实现了EventHandler接口的对象
     */
    public static void registerHandler(int event, EventHandler obj) 
    {
        Set<EventHandler> handlers = eventMap.get(event);
        if (handlers == null) 
        {
            handlers = new HashSet<EventHandler>();
            eventMap.put(event, handlers);//因为handlers是对象指针（引用），先put到Map中
        }
        handlers.add(obj);//所以可以在后面add值，是add到Map中去了的，同样有效。
    }
	
	
	/**
	 *2、服务端发布消息 ：可以在任意的类中调用这个静态方法发送信息，只要调用就能接收到消息。
	 */
    public synchronized static void sendEvent(int event, Object... args)
    {    	
        Set<EventHandler> handlers = eventMap.get(event);//根据消息获取观察者
        if (handlers != null) 
        {        	
        	 //3、客户端接收消息
            for (EventHandler handler : handlers) 
            	handler.execute(event, args);
        }
    }
 
   
  
     
    
}
