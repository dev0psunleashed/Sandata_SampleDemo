package com.sandata.lab.rules.call.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class GPSLocation implements Serializable{

	static final long serialVersionUID = 1L;

	private BigDecimal latitude;
	private BigDecimal longitude;
	private BigDecimal altitude;
	private BigDecimal accuracy;
	
	public GPSLocation() {
		this.setLatitude(null);
		this.setLongitude(null);
		this.setAltitude(null);
		this.setAccuracy(null);
	}
	public GPSLocation(BigDecimal lat, BigDecimal lon, BigDecimal altitude, BigDecimal accuracy) {
		this.setLatitude(lat);
		this.setLongitude(lon);
		this.setAltitude(altitude);
		this.setAccuracy(accuracy);
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	public BigDecimal getAltitude() {
		return altitude;
	}
	public void setAltitude(BigDecimal altitude) {
		this.altitude = altitude;
	}
	public BigDecimal getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(BigDecimal accuracy) {
		this.accuracy = accuracy;
	}
}
