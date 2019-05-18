package cn.reflect;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

/**
 * 反射技术： 通过配置文件动态获取类，仅在配置文件中添加类名，而不用修改源码
 * 
 * @author Administrator
 *
 */
public class ReflectTest {

	public static void main(String[] args) throws Exception {
		NoteBook nb = new NoteBook();
		nb.run();
		// nb.useUSB(new UsbMouse()); // 传统方法
		// nb.useUSB(new UsbKey());

		// 1 新建配置文件
		File config = new File("file\\usb.properties");
		if (!config.exists())
			config.createNewFile();
		// 2 读取配置文件
		FileReader fr = new FileReader(config);
		// 获取键值
		Properties pr = new Properties(); // 属性文件 文件中存储键-值对key=value
		pr.load(fr);

		for (int i = 1; i <= pr.size(); i++) {

			String name = pr.getProperty("usb" + i);
			// 反射技术
			Class c = Class.forName(name);  //获取类名
			USB usb = (USB) c.newInstance();  //创建实例

			nb.useUSB(usb);// 加载配置文件中的类 传对象给Notebook
		}

		fr.close();
	}
}
