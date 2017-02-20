package com.sandata.lab.rest.lookup.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;

/**
 * Staff position is the same as the service. This is a sub-model service.
 * <p/>
 *
 * @author David Rutgos
 */
public class StaffPositionLookup extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("Name")
    private String name;

    @SerializedName("Description")
    private String desc;

    @SerializedName("RecordCreateTimestamp")
    private Date recordCreated;

    @SerializedName("RecordUpdateTimestamp")
    private Date recordUpdated;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getRecordCreated() {
        return recordCreated;
    }

    public void setRecordCreated(Date recordCreated) {
        this.recordCreated = recordCreated;
    }

    public Date getRecordUpdated() {
        return recordUpdated;
    }

    public void setRecordUpdated(Date recordUpdated) {
        this.recordUpdated = recordUpdated;
    }
}
