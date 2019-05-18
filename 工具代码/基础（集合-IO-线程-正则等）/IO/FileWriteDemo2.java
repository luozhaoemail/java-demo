package cn.IO;

import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author luozhao
 *
 */
public class FileWriteDemo2 {

	public static void main(String[] args) {
	
		FileWriter fw = null;
		try {
			
			fw = new FileWriter("file\\1.txt",true);//trur表示可以在原文件末尾续写
			fw.write("\r\njejejeje");  //Windows回车符由两个字符组成\r\n  而Linux回车符有\n组成
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally{
		 try {
			 if(fw != null)
				fw.close();
			}
		 catch (IOException e) {				
				e.printStackTrace();
			}
			
		}//
		
		
	}

}
