package com.bjsxt.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class NumServlet extends HttpServlet {
	
	//��дinit��ʼ�������������ݶ�ȡ��ServletContext������
	@Override
	public void init() throws ServletException {
		//��ȡ�ļ��еļ���������
			//��ȡ�ļ�·��
			String path=this.getServletContext().getRealPath("/nums/nums.txt");
			//����������
			FileReader fr=null;
			BufferedReader br=null;
			try {
				fr=new FileReader(path);
				br=new BufferedReader(fr);
				String nums=br.readLine();
				System.out.println(nums);
				this.getServletContext().setAttribute("nums", nums);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	//��д���ٷ������洢���������ļ���
	@Override
	public void destroy() {
		//��ȡ��ҳ������
		int nums=(int) this.getServletContext().getAttribute("nums");
		//��ȡ�ļ�·��
		String path=this.getServletContext().getRealPath("/nums/nums.txt");
		//����������
		BufferedWriter bw=null;
		FileWriter fw=null;
		try {
			fw=new FileWriter(path);
			bw=new BufferedWriter(fw);
			bw.write(nums+"");
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
