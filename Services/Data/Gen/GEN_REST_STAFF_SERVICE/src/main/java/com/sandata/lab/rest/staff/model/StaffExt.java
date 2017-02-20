package com.sandata.lab.rest.staff.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.Staff;

import java.util.Date;

public class StaffExt extends Staff {

    private static final long serialVersionUID = 1L;

    @SerializedName("OriginalStaffHireDate")
    protected Date originalStaffHireDate;

    public Date getOriginalStaffHireDate() {
        return originalStaffHireDate;
    }

    public void setOriginalStaffHireDate(Date originalStaffHireDate) {
        this.originalStaffHireDate = originalStaffHireDate;
    }
}
