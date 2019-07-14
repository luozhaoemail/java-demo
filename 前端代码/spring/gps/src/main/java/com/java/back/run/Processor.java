package com.java.back.run;

import com.java.back.api.AnaManager;

/**
 *  spark-submit --class lc.control.Processor socket.jar
 *  spark-submit --jars /root/phoenix-5.0.0-HBase-2.0-client.jar --class lc.control.Processor test.jar
 *  
 *  java -cp hb.jar:$SPARK_HOME/jars/*:$PNX_HOME/* com.java.back.run.Processor
 *  
 */
public class Processor {

	public static void main(String[] args) {
		
		// 启动监听
		AnaManager anaMgr = new AnaManager();
		TcpServer tcpserver = new TcpServer();
		tcpserver.init(anaMgr,20006,5);
		tcpserver.start();
	

		while(true)
		{
			try{
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
