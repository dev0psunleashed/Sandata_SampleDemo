package com.sandata.lab.rest.dashboard.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * 
 * @author thanhxle
 *
 */
public class Patient {

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("PatientId")
    private String patientId;



	@SerializedName("Date")
	private Date date;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

    
}
