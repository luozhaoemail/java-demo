package cn.reflect;

public class UsbMouse implements USB {

	public void open() {
		System.out.println("usb鼠标开启");
	}

	public void close() {
		System.out.println("usb鼠标关闭");
	}
}
