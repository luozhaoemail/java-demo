package lc.control;

import lc.ana.AnaManager;
import lc.dao.CommonData;
import lc.tool.MyConstant;

/**
 *  spark-submit --class lc.control.Processor socket.jar
 *  spark-submit --jars /root/phoenix-5.0.0-HBase-2.0-client.jar --class lc.control.Processor test.jar
 */
public class Processor {

	public static void main(String[] args) {
		//CommonData.getIstance();
		// 启动监听
		AnaManager anaMgr = new AnaManager();
		TcpServer anaSrv = new TcpServer();
		anaSrv.init(anaMgr,MyConstant.socktPort,5);
		anaSrv.start();
	

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
