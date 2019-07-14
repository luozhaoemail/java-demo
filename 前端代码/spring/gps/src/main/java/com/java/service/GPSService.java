package com.java.service;

import java.util.List;

import com.java.entity.GPSCount;
import com.java.entity.PageInfo;
import com.java.entity.GPS;



public interface GPSService {
	
	public void getPages(String time1,String time2, PageInfo page) throws Exception;
	
	public List<GPSCount> gpsspeed_num() throws Exception; 
	public List<GPSCount> gpstime_num() throws Exception; 
}
