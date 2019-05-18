package cn.IO.ByteStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trans2 {

	public static void main(String[] args)   {
		BufferedReader bufr=null;
		BufferedWriter buwr=null;		
		 
		try{
		System.setIn(new FileInputStream("D:\\out.txt"));//重新设置标准输入流
		System.setOut(new PrintStream("D:\\zz.txt"));//重新设置标准输出流
		
								//字符缓冲流提高效率			转换流(字节->字符)		 in是字节流						
		 bufr=new BufferedReader(new InputStreamReader(System.in));
		 buwr=new BufferedWriter(new OutputStreamWriter(System.out));
								//字符缓冲流提高效率			转换流(字符->字节)		 out是字节流
		
		//下面的是通用代码
	    String line = null;
		while((line=bufr.readLine())!=null)
		{
			if("over".equals(line))
				break;
			buwr.write(line.toUpperCase());
			buwr.newLine();
			buwr.flush();
		}
	
	  }//
	 catch(IOException e)
		{
		  try{
		    Date d = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String s =sdf.format(d);
		    
		    PrintStream ps =new PrintStream("D:\\exception.log"); //输出日志信息
		    ps.println(s);
		    System.setOut(ps); //更改日志信息输出流到文本
		  }
		  catch(IOException ex)
		  {
			 throw new RuntimeException("日志文件创建失败");
		  }
		  e.printStackTrace(System.out);//输出流到文本exception.log
		  
		}//
	 finally{
		try {
			bufr.close();
			buwr.close();
		} catch (IOException e) {			 	 
			e.printStackTrace(System.out);
		}
		
	 }//
	 
	}

}
