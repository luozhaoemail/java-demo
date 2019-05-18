package cn.IO.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * 过滤出Java文件，并存到
 * @author luozhao
 *
 */
public class File3 {

	public static void main(String[] args) {
		
		File dir = new File("H:\\workplace\\Demo\\src");
		List<File> list =  new ArrayList<File>();
		fileList(dir,list);
		
		System.out.println("清单大小："+list.size());
		
		File file = new File("D:\\javalist.txt");
		writeFile(list,file.toString());//写到javalist.txt文件中去
	}
	
	public  static void fileList(File dir, List<File> list){
		
		File[] fs = dir.listFiles(); //获取所有文件
		
		for(File f: fs) //遍历
		{	if(f.isDirectory())
				fileList(f,list); //如果是目录，递归到子目录
			else //如果是文件
			{
				if(f.getName().endsWith(".java"))
					list.add(f);  //仅过滤出java文件，并添加到List集合
			}				
		}
	}
	
    //写到本地文件里
	public  static void writeFile(List<File> list,String javaList){
		BufferedWriter bufw =null;
		try
		{
			bufw = new BufferedWriter(new FileWriter(javaList));//写到javalist文件中去
		
			for(File f: list)
			{
				String path = f.getAbsolutePath();
				bufw.write(path);
				bufw.newLine();
				bufw.flush();
			}
			
		}
		catch(IOException e)
		{
			throw new RuntimeException();
		}
		finally
		{
			try{
				if(bufw!=null)
					bufw.close();
				
			}catch(IOException ee){}
		}
		
	}
	
}
