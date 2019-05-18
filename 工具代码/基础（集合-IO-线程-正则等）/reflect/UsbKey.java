package cn.reflect;

public class UsbKey implements USB {

	public void open() {
		System.out.println("usb键盘开启");
	}

	public void close() {
		System.out.println("usb键盘关闭");
	}
}
