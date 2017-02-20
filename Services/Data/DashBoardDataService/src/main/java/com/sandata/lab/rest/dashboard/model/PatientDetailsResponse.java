package com.sandata.lab.rest.dashboard.model;

import com.google.gson.annotations.SerializedName;

/**
 * PatientDetailsResponse object
 * 
 * @author khangle
 * @date April 11, 2016
 */
public class PatientDetailsResponse {

	private static final long serialVersionUID = 1L;

	@SerializedName("PatientFirstName")
	private String patientFirstName;
	
	@SerializedName("PatientLastName")
	private String patientLastName;
	
	@SerializedName("PatientID")
	private String patientId;
	
	@SerializedName("Value")
	private int value;

	@SerializedName("ID")
	private String id;

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
