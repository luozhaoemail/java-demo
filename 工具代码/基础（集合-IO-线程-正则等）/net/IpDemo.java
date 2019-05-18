package cn.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpDemo {

	public static void main(String[] args) throws UnknownHostException {
		/**
		 * InetAddress的构造函数不是公开的（public），所以需要通过它提供的静态方法来获取创建InetAddress的实例
		 * 
		getLocalHost()仅返回象征本地主机的InetAddress对象。
　　			本机地址还为localhost,127.0.0.1，这三个地址都是一回事。
　　		getByName()方法返回一个传给它的主机名的InetAddress。
　　			如果这些方法不能解析主机名，它们引发一个UnknownHostException异常
		 */
		InetAddress ip = InetAddress.getLocalHost(); //获取本地主机地址对象  外网地址
		System.out.println(ip.getHostName()+" :  "+ip.getHostAddress() );
		
		InetAddress ip2 =InetAddress.getByName("172.23.22.233");//获取其他主机地址对象
		System.out.println(ip2.getHostName()+" :  "+ip2.getHostAddress() );
		
		InetAddress ip3 =InetAddress.getByName("www.baidu.com");//获取其他主机地址对象  外网
		System.out.println(ip3.getHostName()+" :  "+ip3.getHostAddress() );
		
		InetAddress ip4 =InetAddress.getByName("00-PC");//获取其他主机地址对象     局域网内
		System.out.println(ip4.getHostName()+" :  "+ip4.getHostAddress() );
		
		
		
		InetAddress address = InetAddress.getLocalHost();

        System.out.println(address);
        // 输出：机器名/IP地址
        // 如username-PC/10.4.16.131

        // 通过域名得到IP地址
        address = InetAddress.getByName("www.sohu.com");
        System.out.println(address);
        // 输出：域名/IP地址
	}

}
