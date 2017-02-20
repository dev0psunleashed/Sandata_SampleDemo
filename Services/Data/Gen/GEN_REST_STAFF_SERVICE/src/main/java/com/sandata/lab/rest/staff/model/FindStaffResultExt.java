package com.sandata.lab.rest.staff.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.EmploymentStatusTypeName;
import com.sandata.lab.data.model.dl.model.FindStaffResult;

import java.util.Date;

public class FindStaffResultExt extends FindStaffResult {

    private static final long serialVersionUID = 1L;

    @SerializedName("OriginalStaffHireDate")
    protected Date originalStaffHireDate;

    @SerializedName("StaffPositionName")
    protected String staffPositionName;

    @SerializedName("StaffLocation")
    protected String staffLocation;
    
    public String getStaffPositionName() {
        return staffPositionName;
    }

    public void setStaffPositionName(String staffPositionName) {
        this.staffPositionName = staffPositionName;
    }

    public Date getOriginalStaffHireDate() {
        return originalStaffHireDate;
    }

    public void setOriginalStaffHireDate(Date originalStaffHireDate) {
        this.originalStaffHireDate = originalStaffHireDate;
    }

    public String getStaffLocation() {
        return staffLocation;
    }

    public void setStaffLocation(String staffLocation) {
        this.staffLocation = staffLocation;
    }
}
