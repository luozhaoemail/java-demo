package cn.net.myServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GUI {

	public static void main(String[] args) {
		new Frame().setVisible(true);

	}

}

class Frame extends JFrame {

	private JTextField text1;
	private JButton button1;
	private JTextField text2;

	public Frame() {
		super();
		this.setSize(500, 500);
		this.getContentPane().setLayout(null);// 设置布局控制器
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 一定要设置关闭

		text1 = new JTextField();
		text1.setBounds(20, 20, 300, 40);
		this.add(text1);

		this.add(this.getButton(), null);// 添加按钮

		text2 = new JTextField();
		text2.setBounds(20, 70, 450, 380);
		this.add(text2);

	}

	private JButton getButton() {
		if (button1 == null) {
			button1 = new JButton();
			button1.setBounds(350, 20, 60, 40);
			button1.setText("转到");
			button1.setToolTipText("转到!!!");// 设置鼠标在Label上停留时显示提示信息
			button1.addActionListener(new HelloButton());// 添加监听器类，其主要的响应都由监听器类的方法实现

		}
		return button1;
	}

	private class HelloButton implements ActionListener { // 响应按钮事件
		public void actionPerformed(ActionEvent e) {
			try {

				transServer();//

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("鼠标点击了一下");

		}
	}

	private void transServer() throws IOException {

		String str_url = text1.getText();
		URL url = new URL(str_url);

		String host = url.getHost();
		int port = url.getPort();
		String path = url.getPath();

		Socket s = new Socket(host, port);
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
		// 发送
		pw.println("GET" + path + "  HTTP/1.1");
		pw.println("Accept: */*");
		pw.println("HOST: 172.23.22.211:10005");
		pw.println("Connection: close");
		pw.println();// 空行

		// 接收
		InputStream in = s.getInputStream();
		byte[] buf = new byte[1024];
		int len = in.read(buf);
		String str = new String(buf, 0, len);
		text2.setText(str); // 显示在控制台
		s.close();
	}

}