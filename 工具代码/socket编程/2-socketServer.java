package socket;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

public class socketServer {
    public static void main(String [] args){
        ServerSocket ss = null;
        Socket sk = null;
        try{
            System.out.println("create server socket....");
            ss = new ServerSocket(10002);
            System.out.println("wait for a connection....");
            while(true)     //服务器端一直监听这个端口，等待客户端的连接
            {
              sk = ss.accept();  //当有客户端连接时，产生阻塞               
              System.out.println(sk.getInetAddress().getHostAddress()+":"+sk.getPort()+"连接上了>>>>>");
              
              new socketThread(sk).start();//新建一个socketThread处理这个客户端的socket连接
          }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
          try{
              if(ss != null){
                  ss.close();
              }
              if(sk != null){
                  sk.close();
              }
          }
          catch(Exception ex){
              System.out.println(ex.getMessage());
          }
        }
      }

}

 class socketThread extends Thread {
    public Socket socket;

    public socketThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        PrintWriter pw = null;
        Calendar c;
        
        try {
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
             while(true)//服务器每隔3秒向客户端发送当前时间信息
             {
                   Thread.sleep(3000);
                   c = Calendar.getInstance(); //获取当前时间
                   pw.println("----Server Time:"+c.getTime().toString()); 
                   pw.flush();
             }
             
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}