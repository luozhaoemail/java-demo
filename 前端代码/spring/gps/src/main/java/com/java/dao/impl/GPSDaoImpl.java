package com.java.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.java.dao.GPSDao;
import com.java.entity.GPSCount;
import com.java.entity.PageInfo;
import com.java.entity.GPS;

@Repository("gpsDao")
public class GPSDaoImpl implements GPSDao{

	@Autowired
	private JdbcTemplate jdbctemplate;
			
	public void getPages(String time1,String time2, PageInfo page) throws Exception {			

		String  clause = " gpstime>='"+time1+"' and gpstime<='"+time2+"' ";	
		
		int limit1 = page.pageSize*(page.pageNumber-1);//最关键的就是计算每一页的其实位置
		int limit2 = page.pageSize;
		clause += " limit "+limit1+","+limit2;
		
		//select * from users where text like '%疫苗1%' or text like '%疫苗2%' limit limit1,limit12;
		String sql ="select * from tb_gps where" + clause;	
		System.out.println("sql="+sql);
		
		List<GPS> list = jdbctemplate.query(sql, new BeanPropertyRowMapper<GPS>(GPS.class));
		System.out.println("list.size="+list.size());		
		page.list = list;
		///////////////填充当前页的记录
		
		//表的总条数，最后一页可能未填满
		String sql2 ="select count(1) from tb_gps";
		long count = jdbctemplate.queryForObject(sql2,new Object[]{},Long.class);		
		page.pageTotal = (count%page.pageSize==0 ? count/page.pageSize : count/page.pageSize+1 );		
		//////////////填充总页数
	}
		
	public List<GPSCount> gpsspeed_num() throws Exception {
		String str="select distinct gpsspeed as xdata,count(1) as ydata from tb_gps group by gpsspeed";		
			
		List<GPSCount> list = jdbctemplate.query(str, new BeanPropertyRowMapper<GPSCount>(GPSCount.class));
		
		return list;
	}	
	public List<GPSCount> gpstime_num() throws Exception {
		String str="select distinct gpstime as xdata,count(1) as ydata from tb_gps group by gpstime";
				
		List<GPSCount> list = jdbctemplate.query(str, new BeanPropertyRowMapper<GPSCount>(GPSCount.class));
		
		return list;
	}
}

