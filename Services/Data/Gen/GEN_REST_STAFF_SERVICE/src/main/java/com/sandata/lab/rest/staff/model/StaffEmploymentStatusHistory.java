package com.sandata.lab.rest.staff.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;
import java.util.Date;

public class StaffEmploymentStatusHistory extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffSK")
    protected BigInteger staffSk;

    @SerializedName("StaffID")
    protected String staffId;

    @SerializedName("BusinessEntityID")
    protected String businessEntityId;

    @SerializedName("EmploymentStatusChangeDate")
    protected Date employmentStatusChangeDate;

    @SerializedName("Status")
    protected String status;

    @SerializedName("ReasonCode")
    protected String reasonCode;

    @SerializedName("Notes")
    protected String notes;

    @SerializedName("Modified")
    protected Date modified;

    @SerializedName("ModifiedBy")
    protected String modifiedBy;

    public Date getEmploymentStatusChangeDate() {
        return employmentStatusChangeDate;
    }

    public void setEmploymentStatusChangeDate(Date employmentStatusChangeDate) {
        this.employmentStatusChangeDate = employmentStatusChangeDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

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

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }
}
