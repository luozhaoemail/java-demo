package xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;

public class TCPclient {
	private final static String lineReg = "\\d{1},\\d{11,15},.+,.+";
	private final static String dateReg = "\\d{4}\\/\\d{1,2}\\/\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}";
	private final static String path="C://cmd.txt";
	
	public static void main(String[] args) {
		System.out.println("客户端运行--------");
		Socket sock = null;
		BufferedReader buf = null;
		String ch = "Y";
		try {			
			
			
			while (ch.equalsIgnoreCase("Y")) {	
				sock = new Socket("117.187.20.66", 10002);	
				/**************************					
				BufferedReader buf =new BufferedReader(new InputStreamReader(System.in));//键盘读入字符串string字符流				
				**********************/
				//menu();
				buf = new BufferedReader(new FileReader(path));
				PrintWriter out = new PrintWriter(sock.getOutputStream(), true); // 打印流给服务端发送数据

				String line = null;
				while ((line = buf.readLine()) != null)
					out.println(line);// 将数据发送到服务端
				sock.shutdownOutput();//发送结束标志
				
				// 接收服务端返回的数据
				buf = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				String info = buf.readLine();
				System.out.println(info);

				//////////////////////////
				System.out.println("继续查询输入:Y , 退出输入：N");
				buf = new BufferedReader(new InputStreamReader(System.in));
				String cmd = buf.readLine().trim();
				if (cmd.equalsIgnoreCase("N"))
					ch = "N";
				else if (cmd.equalsIgnoreCase("Y"))
					ch = "Y";
				else {
					ch = cmd;
					System.out.println("输入" + ch + "错误，程序即将退出");
				}

			}// while

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				buf.close();
				sock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}// finally

	}

	/**
	 * JSONTokener读取包含json格式数据的文件，然后可以将JSONTokener对象作为
	 * 参数来构造JSONObject或JSONArray，然后再进行相应的解析。
	 */
	public static JSONObject readJsonFile(String path) 
								throws FileNotFoundException{					
		JSONTokener jsonTokener = new JSONTokener(new FileReader(new File(path)));
		JSONObject jsonObject = new JSONObject(jsonTokener);
		System.out.println(jsonObject);
		return jsonObject;
	}
	
	/**
	 * JSONWriter可以用来构建一个JSON格式的文本，并转换成String，可以写入文件，便于传输和存储
	 */
	public static void writeJsonFile(String path,int flag, String num, String stime, String etime) 
								throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(path);
		JSONWriter jsonWriter = new JSONWriter(writer);
		jsonWriter.object()
				  .key("flag").value(flag)
				  .key("num").value(num)
				  .key("stime").value(stime)
				  .key("etime").value(etime)
				  .endObject();
		writer.flush();
		writer.close();
	}	

	/**
	 * 发送命令参数
	 */
	public static void sendArgs(int flag, String num, String stime, String etime) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("flag", 2);
		jsonObj.put("num", "14708539687");
		jsonObj.put("stime", "2017/3/7 00:00:00");
		jsonObj.put("etime", "2017/3/7 12:00:00");
		System.out.println(jsonObj);
	}
	
	/**
	 * 通过键盘输入生成json文件到本地
	 */
	public static void menu(){
		System.out.println("\n----------------4G行卡故障查询系统------------------");
		System.out.println("选择IMSI号查询请输入：1, 选择电话号码查询请输入：2, 退出请输入：0");
		System.out.println("输入格式为：标志位<0,1,2>，号码<tellnum,imsi>，开始时间<yyyy/MM/dd HH:mm:ss>，结束时间<yyyy/MM/dd HH:mm:ss> "); 
		System.out.println("例如：2,8618080117638,2017/3/7 01:00:00,2017/3/8 02:00:00");
				
		try{			
			BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
			String line =null;
			while((line=bufr.readLine()) != null)
			{
				if(line.trim().matches(lineReg))
				{				
					String[] str = line.trim().split(",");
					int flag = Integer.parseInt(str[0]);
					if(flag==1 || flag==2)
					{	
						String num = str[1];
						String stime = str[2];
						String etime = str[3];	
						
						if( (num.length()==11 || num.length()==13 || num.length()==15)
							 && stime.matches(dateReg) && etime.matches(dateReg))
						{	//客户输入参数符合要求						
							////////////////////////////////////////////////////////////							
							writeJsonFile(path,flag,num,stime,etime);//把数据写入json文件
							JSONObject jsonObj = readJsonFile(path);//读取json文件	
							break;
							////////////////////////////////////////////////////////////
						}
						else 
							System.out.println("输入号码或者日期格式不正确。");					
					}
					else
						System.out.println("请重新输入查询标志位<0,1,2>");
								
				}//
				else if(line.equals("0"))
					//System.exit(0);
					break;
				else
					System.out.println("请按照正确格式重新输入：");	
				
		
			  }//while
						
			}catch(Exception e)
			{
				System.out.println("客户端异常:"+e);
				e.printStackTrace();
			}
		
	}//
}