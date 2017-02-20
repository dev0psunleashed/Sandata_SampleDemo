package com.sandata.lab.rest.staff.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

public class StaffProfileChangedLog extends BaseObject {

    private static final long serialVersionUID = 124199639211004L;

    @SerializedName("ChangedOn")
    private Date changedOn;

    @SerializedName("ChangedBy")
    private String changedBy;

    @SerializedName("ReasonCode")
    private String reasonCode;

    @SerializedName("Notes")
    private String notes;

    @SerializedName("ChangedFields")
    private List<FieldChangedLog> changedFields;

    @SerializedName("UserGuid")
    private String userGuid;

    /**
     * @return the changedOn
     */
    public Date getChangedOn() {
        return changedOn;
    }

    /**
     * @param changedOn the changedOn to set
     */
    public void setChangedOn(Date changedOn) {
        this.changedOn = changedOn;
    }

    /**
     * @return the changedBy
     */
    public String getChangedBy() {
        return changedBy;
    }

    /**
     * @param changedBy the changedBy to set
     */
    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    /**
     * @return the reasonCode
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * @param reasonCode the reasonCode to set
     */
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the changedFields
     */
    public List<FieldChangedLog> getChangedFields() {
        if (changedFields == null) {
            changedFields = new ArrayList<FieldChangedLog>();
        }

        return changedFields;
    }

    /**
     * @return the userGuid
     */
    public String getUserGuid() {
        return userGuid;
    }

    /**
     * @param userGuid the userGuid to set
     */
    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }
}
