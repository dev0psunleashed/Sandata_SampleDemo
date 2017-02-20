package com.sandata.lab.rest.dashboard.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * StaffDetailsResponse object
 * 
 * @author khangle
 * @date April 11, 2016
 *
 */
public class StaffDetailsResponse extends BaseObject {

	private static final long serialVersionUID = 1L;

	@SerializedName("StaffFirstName")
	private String staffFirstName;
	
	@SerializedName("StaffLastName")
	private String staffLastName;
	
	@SerializedName("StaffID")
	private String staffId;
	
	@SerializedName("Value")
	private int value;
	
	@SerializedName("ID")
	private String id;

	public String getStaffFirstName() {
		return staffFirstName;
	}

	public void setStaffFirstName(String staffFirstName) {
		this.staffFirstName = staffFirstName;
	}

	public String getStaffLastName() {
		return staffLastName;
	}

	public void setStaffLastName(String staffLastName) {
		this.staffLastName = staffLastName;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
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
