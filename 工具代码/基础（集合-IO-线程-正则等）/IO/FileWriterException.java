package cn.IO;

import java.io.FileWriter;
import java.io.IOException;
/**	IO异常处理
 *  标准的利用try catch 来处理异常。而不是抛出异常。
 * @author luozhao
 *
 */
public class FileWriterException {

	public static void main(String[] args) {
		
		FileWriter fw = null;
		try{
			fw = new FileWriter("file\\2.txt");  //文件可能不存在，路径可能不正确 FileNotFoundException
			fw.write("0000");  //有可能写入异常
		}
		catch(IOException e){
			System.out.println("手动异常1："+e.toString());
		}
		finally{
			try{
				if(fw!=null)  //必须要判断fw不为空，才close。
					fw.close();  //如果前面try块异常不执行，那么fw没有new 对象，直接跳到这里关闭就会异常
			}
			catch(IOException ex){
				System.out.println("手动异常2："+ex.toString());
			}
		}
		
		

		
	}

}
