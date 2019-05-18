package cn.IO;

import java.io.FileWriter;
import java.io.IOException;
/**
 * FileWriter对象初始化就必须指定文件名，也就是字符写入的目的地。
 * 如果有同名文件，将会被覆盖。
 * @author luozhao
 *
 */
public class FileWriteDemo {

	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("file\\2.txt"); //1
		fw.write("abcde"); //2 将字符串写入到流中去（缓冲）
		fw.flush();//3 刷新缓冲，将字符刷到文件中去
		
		fw.write("12345");//流没有关闭还可以继续写    这种方式智能写一次
		fw.close();//关闭流，关闭之前会刷新一次缓冲

		
	}

}
