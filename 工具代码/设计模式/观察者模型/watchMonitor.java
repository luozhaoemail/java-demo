package watch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import watch.mode.Executor;

public class watchMonitor extends Thread
{
	public String ip;
	public String appname;
	
	public watchMonitor(String ip, String appname) {
		this.ip = ip;
		this.appname = appname;
	}


	@Override
	public void run() {
		System.out.println("监控线程启动-------------");
		
		while(true)
		{
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	        System.out.println("\n监控线程------当前系统时间： "+df.format(new Date()));// new Date()为获取当前系统时间
	          
			//step 1.使用入口：可以在不同的地方发送消息,这里是在线程不停的发送消息，也可以在其他地方调用Event.sendEvent()发送消息
			Event.sendEvent(Msg.status,m,w,f,M,t);	
			
			pause(10);
	        System.gc();
	        
		}//		
		
	}
	
	// 暂停
    private void pause(int nSec) 
    {
        try
        {
            Thread.sleep(nSec*1000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
}
