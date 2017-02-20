package com.sandata.lab.rest.visit.model;

import java.math.BigInteger;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 *
 * @author richardwu
 */
public class StaffForSearch extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffSK")
    protected BigInteger staffSk;
    
    @SerializedName("StaffID")
    protected String staffId;
    
    @SerializedName("StaffFirstName")
    protected String staffFirstName;

    @SerializedName("StaffLastName")
    protected String staffLastName;
    
    @SerializedName("BEID")
    protected String beId;
    
    @SerializedName("StaffPositionName")
    protected String staffPositionName;

	public BigInteger getStaffSk() {
		return staffSk;
	}

	public void setStaffSk(BigInteger staffSk) {
		this.staffSk = staffSk;
	}


	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

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

	public String getBeId() {
		return beId;
	}

	public void setBeId(String beId) {
		this.beId = beId;
	}

	public String getStaffPositionName() {
		return staffPositionName;
	}

	public void setStaffPositionName(String staffPositionName) {
		this.staffPositionName = staffPositionName;
	}
    
    
    
    
}
