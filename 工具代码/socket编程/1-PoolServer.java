package xml;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 Socket创建带连接池的多线程文件传输服务器和客户端实例
 */
public class PoolServer {
	protected int maxConnections;
	protected int listenPort;
	protected ServerSocket serverSocket;

	public static void main(String[] args) {
		PoolServer server = new PoolServer(10002,3);
		server.setUpHandlers();
		server.acceptConnections();
	}
	
	/**构造器
	 * @param aListenPort 端口
	 * @param maxConnections 最大连接线程数
	 */
	public PoolServer(int aListenPort, int maxConnections) {
		listenPort = aListenPort;
		this.maxConnections = maxConnections;
	}

	public void setUpHandlers() {
		for (int i = 0; i < maxConnections; i++) {
			PoolHandler currentHandler = new PoolHandler();
			new Thread(currentHandler, "Handler " + i).start();//启用每一个线程
		}
	}
	
	public void acceptConnections() {
		System.out.println("服务端开启--------");
		
		try {
			//5用于指定在服务器忙时，可以与之保持连接请求的等待客户数量，如果没有指定参数，默认为50。
			ServerSocket socketserver = new ServerSocket(listenPort, 5);
			Socket socket = null;
			while (true) {
				socket = socketserver.accept();
				System.out.println(socket.getInetAddress().getHostAddress()+"......连接上了");
				handleConnection(socket);
			}
		} catch (BindException e) {
			System.out.println("不能绑定端口" + listenPort);
		} catch (IOException e) {
			System.out.println("不能在端口上实例化ServerSocket: "+ listenPort);
		}
	}
	
	protected void handleConnection(Socket connectionToHandle) {
		PoolHandler.processRequest(connectionToHandle);
	}
	
}

///////////////////////////////////////////////////////////////////////////////////
/**
 * 委派 PooledConnectionHandler 处理连接
 */
class PoolHandler implements Runnable {
	protected Socket sock;
	protected static List<Socket> pool = new LinkedList<Socket>();
	private final static String path = "C://cmd2.txt";
	
	public PoolHandler() {
	}
	
	//把传入请求添加到池中，并告诉其它正在等待的对象该池已经有一些内容
	public static void processRequest(Socket requestToHandle) {
		synchronized (pool) {
			pool.add(pool.size(), requestToHandle);
			pool.notifyAll();
		}
	}
	
	//在连接池上等待，并且池中一有连接就处理它
	public void run() {
		while (true) {
			synchronized (pool) {
				while (pool.isEmpty()) {
					try {
						pool.wait();
					} catch (InterruptedException e) {
						return;
					}
				}
				sock = pool.remove(0);
			}
			handleConnection();
		}
	}
	
	//获取连接的流，使用它们，并在任务完成之后清除它们
	public void handleConnection() {		
		try {	
			//接收客户端发来的数据
			BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			//用于写入本地文件
			PrintWriter pw = new PrintWriter(new FileWriter(path,true),true);//追加，自动刷新
			
			String line = null;
			while ((line = br.readLine()) != null)
				pw.println(line);
			
			pw = new PrintWriter(sock.getOutputStream(),true);//用于给客户端发送数据  			
			pw.println("上传成功！");
			
			pw.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find requested file on the server.");
		} catch (IOException e) {
			System.out.println("Error handling a client: " + e);
		}
	}//
	
}
