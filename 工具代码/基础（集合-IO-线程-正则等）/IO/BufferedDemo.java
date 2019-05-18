package cn.IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class BufferedDemo {

	public static void main(String[] args) throws IOException  {
		
		buffIO();		
		decorator();
		
	}

	private static void decorator() throws IOException {
		FileReader fr = new FileReader("D:\\buf.txt"); 
		MyBufferedReader mb = new MyBufferedReader(fr);
		String txt = null;
		while((txt=mb.myReadLine())!=null){  //自定义myReadLine()
			System.out.println(txt);
		}
		mb.close();
	}

	private static void buffIO() {
		BufferedReader r = null;
		BufferedWriter w = null;
		
		try
		{
			r = new BufferedReader( new FileReader("file\\buf.txt"));
			w = new BufferedWriter( new FileWriter("D:\\buf.txt"));
			
			String line =null;//两个流之间的中转站
			
			while((line=r.readLine())!=null){  //读		
				w.write(line);  //写
				w.newLine();
				w.flush();
			}
		}
		catch(IOException e)
		{
			throw new RuntimeException("读写失败");
		}
		finally
		{
			try{
			  if(r!=null)
				 r.close();
			}
			catch(IOException e){}
			try{
				  if(w!=null)
					 w.close();
			}
			catch(IOException e){}
		}
	}//	

}

/**装饰设计模式Decorator：将已有对象传入，基于已有的功能提供更强的功能。
 * 装饰比继承更为灵活，避免了继承的臃肿，降低了类之间的关系。
 * 他们都是同属于一个体系
 * Reader
 *   |--FileReader
 * 	 |--FileWriter
 *   |--MyBufferedReader //装饰类，加强上面两个类的效率
 * 
 * class MyBufferedReader extends Reader
 * { private Reader r;
 *   BufferedReader(Reader r){}  //只需要把对象传进来就可加强原来的功能。
 * }
 */

class MyBufferedReader extends Reader{ //自定义的装饰类
	private Reader r;
	
	public MyBufferedReader(Reader r){
		this.r = r;//传入待装饰对象
	}
	
	public  String myReadLine() throws IOException{
		StringBuilder sb = new StringBuilder();
		
		int ch = 0;
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
	
	@Override
	public void close() throws IOException {
		r.close();	//实际关闭的是	传入对象的流
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
	
		return r.read(cbuf,off,len); //没有重写
	}
	
}