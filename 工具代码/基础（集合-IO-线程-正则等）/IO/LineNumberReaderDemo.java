package cn.IO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

/**
 * BufferedReader
 *    |--LineNumberReader //是BufferedReader的子类， 用法差不多
 *  
 *   跟踪行号的缓冲字符输入流。setLineNumber(int)和 getLineNumber()可分别用于设置和获取当前行号。 
 *   
 *   行编号从 0 开始,遇到换行符\n、回车符\r就结束
 * *
 */
public class LineNumberReaderDemo {

	public static void main(String[] args) throws IOException{
		
		//testNum();	
		
		//throws异常的方式
		FileReader fr = new FileReader("file\\1.html");
		MyLineNumberReader mynum = new MyLineNumberReader(fr);
		String txt = null;
		mynum.setLineNumber(100);
		while((txt=mynum.myReadLine())!=null)
			System.out.println(mynum.getLineNumber()+": "+txt);
		
		
	}

	private static void testNum() { //try-catch异常的方式
		LineNumberReader lnr =null;
		
		try 
		{
			lnr = new LineNumberReader(new FileReader("file\\1.html"));
			
			String txt =null;
			lnr.setLineNumber(100);//设置行号从100开始
			
			try 
			{
				while((txt=lnr.readLine())!=null)			
					System.out.println(lnr.getLineNumber()+": "+txt);//显示行号
			} 
			catch (IOException e) {}

		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			
			try{
				  if(lnr!=null)
					  lnr.close();
				}
				catch(IOException e){}
			
		}//finally
		
	}

}

/**自定义LineNumberReader 实现原理是计数器
 * 
 * */
class MyLineNumberReader {
	private Reader r;
	private int num;
	
	MyLineNumberReader(Reader r){
		this.r = r;
	}
	
	public String myReadLine() throws IOException{
		num++;  //读一行行号加一次
		StringBuilder sb = new StringBuilder();
		int ch =0;
		while((ch=r.read())!=-1){
			if(ch=='\r')
				continue;
			if(ch=='\n')
				return sb.toString();  //读到换行符返回一行
			else
				sb.append((char) ch);			
		}		
		if(sb.length()!=0) //末行 没有回车的情况
			return sb.toString();		
		return null;  //读到末尾返回空
	}

	
	public void setLineNumber(int num){
		this.num =num;
	}
	
	public int getLineNumber(){
		return num;
	}
	
	public void close() throws IOException {
		r.close();	//实际关闭的是	传入对象的流
	}
	
}
