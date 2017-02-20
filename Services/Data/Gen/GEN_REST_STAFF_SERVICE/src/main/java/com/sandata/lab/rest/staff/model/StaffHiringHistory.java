package com.sandata.lab.rest.staff.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;
import java.util.List;

public class StaffHiringHistory extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("LastDateWorked")
    protected Date lastDateWorked;

    @SerializedName("HireDate")
    protected Date hireDate;

    @SerializedName("RehireDate")
    protected Date rehireDate;

    @SerializedName("EmploymentStatusChange")
    protected List<StaffEmploymentStatusChange> employmentStatusChange;

    public Date getLastDateWorked() {
        return lastDateWorked;
    }

    public void setLastDateWorked(Date lastDateWorked) {
        this.lastDateWorked = lastDateWorked;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getRehireDate() {
        return rehireDate;
    }

    public void setRehireDate(Date rehireDate) {
        this.rehireDate = rehireDate;
    }

    public List<StaffEmploymentStatusChange> getEmploymentStatusChange() {
        return employmentStatusChange;
    }

    public void setEmploymentStatusChange(List<StaffEmploymentStatusChange> employmentStatusChange) {
        this.employmentStatusChange = employmentStatusChange;
    }
}
