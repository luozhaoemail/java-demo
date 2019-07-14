package com.java.entity;


/**
 触发事件：0=变空车，1=变载客，2=设防，3=撤防，4=其它
运营状态：0=空车，1=载客，2=驻车，3=停运，4=其它
GPS速度：格式ddd，取值000-255内整数，以公里/小时为单位。
GPS状态：0=无效，1=有效
 *
 */
public class GPSCount {
	public String xdata;
	public long ydata;
	@Override
	public String toString() {
		return "GPSCount [xdata=" + xdata + ", ydata=" + ydata + "]";
	}
	public String getXdata() {
		return xdata;
	}
	public void setXdata(String xdata) {
		this.xdata = xdata;
	}
	public long getYdata() {
		return ydata;
	}
	public void setYdata(long ydata) {
		this.ydata = ydata;
	}	
	
	
}
