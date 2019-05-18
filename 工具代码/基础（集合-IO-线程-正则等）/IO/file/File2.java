package cn.IO.file;

import java.io.File;

/**
 * 利用递归
 * 列出全部文件和删除全部文件
 * 
 * File[] listFiles(FilenameFilter filter)

|--E:\pdf
|  |--E:\pdf\BriskPDF.exe
|  |--E:\pdf\c661e8756193bb5c108177c62c4f764a.png
|  |--E:\pdf\EasyPDF-settings.txt
|  |--E:\pdf\lua5.1.dll
|  |--E:\pdf\Uninstall
|  |  |--E:\pdf\Uninstall\IRIMG1.JPG
|  |  |--E:\pdf\Uninstall\IRIMG2.JPG
|  |  |--E:\pdf\Uninstall\IRIMG3.JPG
 */
public class File2 {

	public static void main(String[] args)
	{
		File dir = new File("E:\\pdf");
		showDir(dir,0);
		
		File dir2 = new File("E:\\1");
		deleDir(dir2);
	}


	public static String getLeve(int lev){
		//StringBuilder 字符串变量（非线程安全） 用在字符串缓冲区被单个线程使用的时候比 StringBuffer 要快
		StringBuilder sb = new StringBuilder();
		
		sb.append("|--");
		for(int i=0; i<lev ;i++)
			//sb.append("|--");
			sb.insert(0,"|  ");
		return sb.toString();
	}
	
	public static void showDir(File dir, int lev) {  //递归列出全部文件
		
		System.out.println(getLeve(lev)+dir);
		lev++;
		
		File[] files = dir.listFiles();
		for(int i=0; i<files.length; i++)
		{
			if(files[i].isDirectory())  //递归地读取子目录里面的内容
				showDir(files[i],lev);
			else
				System.out.println(getLeve(lev)+files[i]);			
		}
		
	}
	
	private static void deleDir(File dir) { //删除有内容的文件夹
		File[] fs = dir.listFiles();
		
		for(int i=0; i<fs.length ;i++)
			if(fs[i].isDirectory())
				deleDir(fs[i]);
			else
				System.out.println(fs[i]+"--删除文件--"+fs[i].delete());//删除所有文件，但文件夹还在
		
		System.out.println(dir+"--删除目录--"+dir.delete());//删除所有文件夹
	}
	/**
	 *  E:\1\2\a.txt--删除文件--true
		E:\1\2\b.txt--删除文件--true
		E:\1\2--删除目录--true
		E:\1\3\b.txt--删除文件--true
		E:\1\3\e.txt--删除文件--true
		E:\1\3--删除目录--true
		E:\1--删除目录--true
	 */
	
	
}
