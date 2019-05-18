package cn.IO.ByteStream;

import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义缓冲区
 * @author luozhao
 *
 */
public class MyBuffered {

	private InputStream in;
	private byte[] buf = new byte[1024];
	private int p=0; //位置指针
	private int n=0; //计数器
	
	public	MyBuffered(InputStream in)
	{
		this.in = in;
	}
	
	public int myRead() throws IOException //每次读取一个字节
	{
		if(n == 0)
		{	
			n = in.read(buf); //通过in对象从硬盘读取数据，并存到内存的缓冲数组里
			if(n<0)
				return -1;
			p = 0; //每次取下标(指针)要归零
			
			byte b =buf[p];   //读取1个字节   如果二进制文件刚刚和结束发-1相同  (11111111)就会读取出错
			/**为了避免文件内容和结束符一样，就将1字节的byte,转成4字节的int型
			 *  byte 11111 --> int 11111111 11111111 11111111 11111111 
			 *   11111111 11111111 11111111 11111111  (-1)
			 * & 00000000 00000000 00000000 11111111  (255)
			 * ---------------------------------------------
			 *   00000000 00000000 00000000 11111111  (-1)
			 */
			n--;
			p++;
		
			return b&255;  //返回4个字节,与255来实现 高位补0
		}
		else if(n>0)
		{
			byte b = buf[p];
			n--;
			p++;
		
			return b&0xff; //与255来实现 高位补0
		}
		else
			return -1;
	}
	
	public void myClose() throws IOException
	{
		in.close();
	}
}
