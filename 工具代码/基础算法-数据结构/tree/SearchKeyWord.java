package view.tree;

import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

//对文本文件的关键词进行搜索
public class SearchKeyWord {

	public static void main(String[] args) {
		String str="对文本文件的关键词进行文本搜索";//下标从0开始
		System.out.println(str.length());//15个
		System.out.println(str.indexOf("文本"));// 1  某个元素第一次出现的索引位置的值
		System.out.println(str.indexOf("文本",0));//1 从第0个位置开始匹配  
		System.out.println(str.lastIndexOf("文本"));//11 某个元素最后一次出现的索引位置的值
		System.out.println(str.lastIndexOf("文本",str.length()));//11
		System.out.println(str.substring(14));//索
		System.out.println(str.substring(str.lastIndexOf("文本"),str.length()));//文本搜索
		
		SearchKeyWord skw = new SearchKeyWord();
		skw.SearchKeyword(new File("C://pqt//file.txt"),"string");
		
		
	}
	
	
	public void SearchKeyword(File file,String keyword)
	{
		//参数校验  
        verifyParam(file, keyword);
		LineNumberReader read = null;
		try
		{
			read = new LineNumberReader(new FileReader(file));
			String line=null;
			while((line=read.readLine())!=null)
			{
				int index=0;
				int next=0;
				int times=0;
				//从 next位置开始查找指定keyword在line中第一次出现处的索引
				while( (index=line.indexOf(keyword,next) ) != -1)
				{
					next = index + keyword.length();
					times++;
				}
				
				if(times>0)
					System.out.println("第"+ read.getLineNumber() +"行" + "出现 "+keyword+" 次数: "+times);  
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(read);
		}
	}
	
	private void verifyParam(File file, String keyword) 
	{  
        //对参数进行校验证  
        if(file == null ){  
            throw new NullPointerException("the file is null");  
        }  
        if(keyword == null || keyword.trim().equals("")){  
            throw new NullPointerException("the keyword is null or \"\" ");  
        }  
          
        if(!file.exists()) {  
            throw new RuntimeException("the file is not exists");  
        }  
        //非目录  
        if(file.isDirectory()){  
            throw new RuntimeException("the file is a directory,not a file");  
        }  
          
        //可读取  
        if(!file.canRead()) {  
            throw new RuntimeException("the file can't read");  
        }  
    } 
	
	 private void close(Closeable able)
	 {  
        if(able != null)
        {  
            try
            {  
                able.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
                able = null;  
            }  
        }  
	 }  
}
