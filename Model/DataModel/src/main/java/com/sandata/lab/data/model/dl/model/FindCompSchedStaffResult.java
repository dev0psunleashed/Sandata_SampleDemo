package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;

/**
 * <p>FindCompSchedStaffResult</p>
 * <p>Represents a Staff for find request in Staff Compliance Scheduler.</p>
 * <p>Date: 6/27/16</p>
 * <>Time: 11:27 AM</>
 */
public class FindCompSchedStaffResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffSK")
    protected BigInteger staffSK;

    @SerializedName("StaffID")
    protected String staffID;

    @SerializedName("StaffFirstName")
    protected String staffFirstName;

    @SerializedName("StaffMiddleName")
    protected String staffMiddleName;

    @SerializedName("StaffLastName")
    protected String staffLastName;

    @SerializedName("StaffEmploymentStatusTypeName")
    protected EmploymentStatusTypeName staffEmploymentStatusTypeName;

    @SerializedName("StaffPositionName")
    protected ServiceName staffPositionName;

    public BigInteger getStaffSK() {
        return staffSK;
    }

    public void setStaffSK(BigInteger staffSK) {
        this.staffSK = staffSK;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffMiddleName() {
        return staffMiddleName;
    }

    public void setStaffMiddleName(String staffMiddleName) {
        this.staffMiddleName = staffMiddleName;
    }

    public String getStaffLastName() {
        return staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public EmploymentStatusTypeName getStaffEmploymentStatusTypeName() {
        return staffEmploymentStatusTypeName;
    }

    public void setStaffEmploymentStatusTypeName(EmploymentStatusTypeName staffEmploymentStatusTypeName) {
        this.staffEmploymentStatusTypeName = staffEmploymentStatusTypeName;
    }

    public ServiceName getStaffPositionName() {
        return staffPositionName;
    }

    public void setStaffPositionName(ServiceName staffPositionName) {
        this.staffPositionName = staffPositionName;
    }
}
