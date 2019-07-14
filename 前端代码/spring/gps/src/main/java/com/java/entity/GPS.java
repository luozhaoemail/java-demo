package com.java.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class GPS {
	public int id;
	public String carflag;
	public String touchevent;
	public String opstatus;
	public String gpstime;
	public BigDecimal gpslongitude;
	public BigDecimal gpslatitude;
	public String gpsspeed;
	public int gpsorientation;
	public String gpsstatus;
		
	
	@Override
	public String toString() {
		return "GPS [id=" + id + ", carflag=" + carflag + ", touchevent="
				+ touchevent + ", opstatus=" + opstatus + ", gpstime="
				+ gpstime + ", gpslongitude=" + gpslongitude + ", gpslatitude="
				+ gpslatitude + ", gpsspeed=" + gpsspeed + ", gpsorientation="
				+ gpsorientation + ", gpsstatus=" + gpsstatus + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCarflag() {
		return carflag;
	}

	public void setCarflag(String carflag) {
		this.carflag = carflag;
	}

	public String getTouchevent() {
		return touchevent;
	}

	public void setTouchevent(String touchevent) {
		this.touchevent = touchevent;
	}

	public String getOpstatus() {
		return opstatus;
	}

	public void setOpstatus(String opstatus) {
		this.opstatus = opstatus;
	}

	public String getGpstime() {
		return gpstime;
	}

	public void setGpstime(String gpstime) {
		this.gpstime = gpstime;
	}

	public BigDecimal getGpslongitude() {
		return gpslongitude;
	}

	public void setGpslongitude(BigDecimal gpslongitude) {
		this.gpslongitude = gpslongitude;
	}

	public BigDecimal getGpslatitude() {
		return gpslatitude;
	}

	public void setGpslatitude(BigDecimal gpslatitude) {
		this.gpslatitude = gpslatitude;
	}

	public String getGpsspeed() {
		return gpsspeed;
	}

	public void setGpsspeed(String gpsspeed) {
		this.gpsspeed = gpsspeed;
	}

	public int getGpsorientation() {
		return gpsorientation;
	}

	public void setGpsorientation(int gpsorientation) {
		this.gpsorientation = gpsorientation;
	}

	public String getGpsstatus() {
		return gpsstatus;
	}

	public void setGpsstatus(String gpsstatus) {
		this.gpsstatus = gpsstatus;
	}
	
	
	
	
	
}
