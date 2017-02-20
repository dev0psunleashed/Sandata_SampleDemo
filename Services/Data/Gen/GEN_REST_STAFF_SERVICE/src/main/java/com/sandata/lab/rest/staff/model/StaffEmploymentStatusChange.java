package com.sandata.lab.rest.staff.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;

public class StaffEmploymentStatusChange extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("EmploymentStatus")
    protected String employmentStatus;

    @SerializedName("ReasonCode")
    protected String reasonCode;

    @SerializedName("NotesForChange")
    protected String notesForChange;

    @SerializedName("EffectiveDate")
    protected Date effectiveDate;

    @SerializedName("CreatedDate")
    protected Date createdDate;

    @SerializedName("UpdatedDate")
    protected Date updatedDate;

    @SerializedName("CreatedBy")
    protected String createdBy;

    @SerializedName("UpdatedBy")
    protected String updatedBy;

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getNotesForChange() {
        return notesForChange;
    }

    public void setNotesForChange(String notesForChange) {
        this.notesForChange = notesForChange;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
