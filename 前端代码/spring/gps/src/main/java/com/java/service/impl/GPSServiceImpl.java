package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.GPSDao;
import com.java.entity.GPSCount;
import com.java.entity.PageInfo;
import com.java.entity.GPS;
import com.java.service.GPSService;

@Service("gpsService")
public class GPSServiceImpl implements GPSService{

	@Resource
	private GPSDao gpsDao;	
		
	public void getPages(String time1,String time2,PageInfo page) throws Exception{
		gpsDao.getPages(time1,time2,page);	
	}
	
	public List<GPSCount> gpsspeed_num() throws Exception{
		return gpsDao.gpsspeed_num();
		
	} 
	public List<GPSCount> gpstime_num() throws Exception{
		return gpsDao.gpstime_num();
	}

}
