package cn.IO.file;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
/**
 * File类不是实行流式操作的，它直接处理文件和文件系统。
 * File类没有指定信息怎样从文件读取或向文件存储，它只是描述了文件本身的属性。 * 
 * 
 */
public class FileDemo {

	public static void main(String[] args) throws IOException {
		
		//construt();
		create();
	}

	public static void construt() {
		/**File的3中构造函数，将文件或目录封装成文件类		 
		1. File(String directoryPath)
        2. File(String directoryPath, String filename)
        3. File(File dirObj, String filename)
		 
		 */
		File f1 = new File("abc.txt");
		File f2 = new File("D:\\","zzz.txt");
		
		File d = new File("D:\\");
		File f3 = new File(d,"1.txt");
		
		out("f1: "+f1);
		out("f2: "+f2);
		out("f3: "+f3);
		
		//separator 跨平台的文件分隔符：window\\  linux /
		File f4 = new File("D:"+File.separator+"abc"+File.separator+"1.txt");
		
	}


	private static void create() throws IOException {
	/**
	 * 创建方法
	1.boolean createNewFile() 不存在返回true 存在返回false
	2.boolean mkdir() 创建目录
	3.boolean mkdirs() 创建多级目录
	 
	 * 删除方法
	1.boolean delete()
	2.boolean deleteOnExit() 文件使用完成后删除
	
	 * 判断方法
	1.boolean canExecute()判断文件是否可执行
	2.boolean canRead()判断文件是否可读
	3.boolean canWrite() 判断文件是否可写
	4.boolean exists() 判断文件是否存在
	5.boolean isDirectory()
	6.boolean isFile()
	7.boolean isHidden()
	8.boolean isAbsolute()判断是否是绝对路径 文件不存在也能判断
	
	 * 获取方法	
	1.String getName()
	2.String getPath()
	3.String getAbsolutePath()
	4.String getParent()//如果没有父目录返回null
	5.long lastModified()//获取最后一次修改的时间
	6.long length()
	7.boolean renameTo(File f)
	8.File[] liseRoots()//获取机器盘符
	9.String[] list()
	10.String[] list(FilenameFilter filter)
		 
 */
		File dir = new File("D:\\abc");
		out("创建一级目录："+dir.mkdir());
		dir = new File("D:\\abc\\1\\2\\3");
		out("创建多级目录："+dir.mkdirs());
		File f = new File(dir,"1.txt");
		out("创建"+f.createNewFile());  //创建文件，成功返回true，如果文件已存在则不创建，返回false。
		//和输出流不一样，输出流当文件存在时会覆盖原来的
		
		//out("删除"+f.delete());  //删除文件，成功返回true，失败返回false。
		//f.deleteOnExit();
		
		out(f.canExecute());
		out(f.canRead());
		out(f.canWrite());
		out(f.exists());
		out(f.isDirectory());
		out(f.isFile());
		out(f.isHidden());
		out(f.isAbsolute());		
		
		
		out("文件名；"+f.getName());
		out("相对路径："+f.getPath());
		out("绝对路径："+f.getAbsolutePath());	
		out("父目录："+f.getParent());
		out("最后一次修改的时间："+f.lastModified());
		out("长度："+f.length());
		
		
		File f2 = new File(dir,"li4.txt");
		out("移动文件:"+f.renameTo(f2));
		
		
		File [] files = File.listRoots(); //获取本机的所有盘符
		for(File ff : files)
			out(ff+":"+ff.length());
		
		
		File fff = new File("D:\\");  //list对象必须是一个已经存在的目录
		String[] names = fff.list();  //列出D盘下所有的文件，包括隐藏文件
		for(String name:names)
			out(name);
		

		File ddd = new File("D:\\BaiduYunDownload");  //文件过滤 ，  只列出*.mp4 文件		
		String[] ns = ddd.list( new FilenameFilter()   //内部类
		  {
			public boolean accept(File dir,String name) //获取参数(ddd, ns)
			{
				//out("目录："+dir+"....."+name);	
				/*if(name.endsWith(".mp4"))
					return true;
				else
					return false;*/
				return name.endsWith(".mp4");
			}
	      }
		); 
		out("目录："+ddd+"中MP4个数为："+ ns.length);
		for(String name:ns)
			out(name);		
		
 	}
	
	
	public static void out(Object o) {
		
		System.out.println(o);
	}
}
