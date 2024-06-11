package lc.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 统计项目的代码总行数 = 8761 行
 * @author Administrator
 *
 */
public class CountCode 
{
	public static void main(String[] args) throws Exception 
	{			
		File dir = new File("E:\\研三\\毕业材料\\实验\\代码\\check4G");//bigdataweb check4G
		List<File> list =  new ArrayList<File>();
		fileList(dir,list);  //1 获取所有txt文件，递归获取子文件
		int x=0;
		System.out.println("文件个数="+list.size());
		for(int i=0; i<list.size(); i++) //2循环遍历每一个txt文件，并取值
		{
			File file = list.get(i);
			System.out.println("加载第"+(i+1)+"个文件"+file.getAbsolutePath());
			BufferedReader br = new BufferedReader(new FileReader(file));
		
			String str = "";
			while((str = br.readLine()) != null)//O(m)
	        {
				x++;
				//System.out.println(str);
	        }
		}
		System.out.println(x); //8761行代码
		
			
	}//main
	
	
	//递归获取每个子文件夹下的文件
	public  static void fileList(File dir, List<File> list){		
		File[] fs = dir.listFiles(); //获取所有文件
		
		for(File f: fs) //遍历
		{	if(f.isDirectory())
				fileList(f,list); //如果是目录，递归到子目录
			else //如果是文件
			{
				if(f.getName().endsWith(".java") || f.getName().endsWith(".jsp"))
					list.add(f);  //仅过滤出java文件，并添加到List集合
			}				
		}
	}
	
}
