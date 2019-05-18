package cn.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 多线程
 * @author luozhao
 *
 */

class Send implements Runnable{   //发送线程
	private DatagramSocket ds;
	
	public Send(DatagramSocket ds){
		super();
		this.ds =ds ;
	}
	@Override
	public void run(){
		try{
			
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		String line =null;
		while((line=bufr.readLine()) != null){
			
			byte[] buff =line.getBytes();
			DatagramPacket dp = new DatagramPacket(buff,buff.length,InetAddress.getByName("luozhao-PC"),10002);
			ds.send(dp);
		    
		    if("0".equals(line))
				break;
			}
		}catch(Exception e)
		{
			
		}
	}
}

class Receive implements Runnable{ //接收线程
	private DatagramSocket ds;
	
	public Receive(DatagramSocket ds){
		super();
		this.ds =ds ;
	}
	@Override
	public void run(){
		try {
			while(true){
				byte [] buf =new byte[1024]; 
				DatagramPacket dp =new DatagramPacket(buf,buf.length); 
				ds.receive(dp); 
				
				String ip = dp.getAddress().getHostAddress();
				int port = dp.getPort();
				String txt = new String(dp.getData(),0,dp.getLength());		
				System.out.println(ip+":"+port+" ----> "+txt);
			    if(txt.equals("0"))
			    	System.out.println(ip+":"+port+"离开聊天室");
			}
		} catch (Exception e) {
			
		}
	}
}

public class UDP_chatRoom {

	public static void main(String[] args) throws Exception {
		 DatagramSocket sendSoc =new DatagramSocket();
		 DatagramSocket RecvSoc =new DatagramSocket(10002);

		 new Thread(new Send(sendSoc)).start();
		 new Thread(new Receive(RecvSoc)).start();
	}

}
