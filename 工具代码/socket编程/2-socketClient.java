package socket;

import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class socketClient {
    public static void main(String[] args) {
        Socket sk = null;
        BufferedReader br = null;
        try {
            sk = new Socket("117.187.23.111", 10002);//117.187.23.111  localhost
            
            br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
             String line = null;
             while((line = br.readLine()) != null){
                 System.out.println("Msg from server:"+line);     //服务器端打印客户端发送的信息数据
             }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (sk != null) {
                    sk.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}