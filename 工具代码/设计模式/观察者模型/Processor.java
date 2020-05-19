package control;

import join.Join3;
import join.Join91;
import join.test.Join;
import join.test.Join2;

import org.apache.spark.broadcast.Broadcast;

import tool.Event;
import tool.HdfsUtil;
import tool.Log;
import tool.Msg;
import tool.SparkConfig;
import watch.ComputeMem;
import watch.watchDeal;
import watch.watchMonitor;

//spark-submit --class control.Processor2 --total-executor-cores 12 test.jar 1 
public class Processor2 {

	public static void main(String[] args) throws Exception
	{	
		//初始化spark
		SparkConfig.getIstance();
		Log.log("初始化spark--完毕");
		
		//初始化消息模块
		initEvent();
		Log.log("初始化消息模块--完毕");
				
		//启动监控模块
		watchMonitor m = new watchMonitor(Msg.ip,Msg.name);
		m.start();

		while(true){
			///程序挂起
		}

	}
	
	
	public static void initEvent()
	{
		watchDeal deal = new watchDeal();//观察者
		
		//注册消息
		for(int i=0; i<Msg.alarn.length; i++)
        {
            Event.registerHandler(Msg.alarn[i], deal);
        }
	}
}
