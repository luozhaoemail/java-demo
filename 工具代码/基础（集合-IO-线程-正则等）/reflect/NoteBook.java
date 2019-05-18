package cn.reflect;

public class NoteBook {

	public void run() {
		System.out.println("笔记本电脑开启");
	}

	public void useUSB(USB usb) { // 多态
		if (usb != null) {
			usb.open();
			usb.close();
		}
	}
}
