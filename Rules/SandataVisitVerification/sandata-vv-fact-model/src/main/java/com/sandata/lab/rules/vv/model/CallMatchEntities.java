package com.sandata.lab.rules.vv.model;

import java.io.Serializable;

public class CallMatchEntities implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String scheduleId;
	private String patientId;
	private String staffId;
	
	public CallMatchEntities(String scheduleId, String patientId, String staffId) {
		
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

}
