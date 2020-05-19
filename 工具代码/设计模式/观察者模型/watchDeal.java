package watch;

import tool.*;

//EventHandler是观察者：接收消息
public class watchDeal implements EventHandler //step2 继承EventHandler，然后就可以根据消息，来触发相应的处理逻辑
{		
	
	@Override
	public void execute(int event, Object... parameters)  
	{

		//Event.sendEvent(Msg.status,m,w,f,M,t); 的参数会发送到这里，进行触发下游逻辑
		if(event == Msg.status)
		{
			//todo
			
		}
		else //Event.sendEvent(Msg.safety,type,d);
		{
			String type = parameters[0].toString();//type=192.168.1.124:40605的内存使用率
			double d = (double)parameters[1];   //d=0.28			
			switch(event)
			{
			    case Msg.safety://0
			    {
			    	double turn  = Msg.f / Msg.M;  //文件大小 / 内存大小=批次
			    	Msg.t =(int) Math.ceil(turn);//向上取整	
			    	System.out.println(type+"= "+d+"	资源充足! needMeM="+Msg.f+",  restMeM="+Msg.M+",  批次t="+Msg.t);
			    }
			    break;
			    default: break;	
			    
			}//switch
		}//
		
		
		
	}//

}
