package com.sandata.lab.rest.lookup.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;

/**
 * Defines a enumerated lookup value that is generated via the data layer.
 * <p/>
 *
 * @author David Rutgos
 */
public class EnumLookup extends BaseObject {

    private static final long serialVersionUID = 1L;

    public EnumLookup() {
    }

    public EnumLookup(int id, String type, String desc) {
        this(id, type);
        this.desc = desc;
    }

    public EnumLookup(int id, String type) {
        this.id = id;
        this.type = type;

        recordCreated = new Date();
        recordUpdated = new Date();
    }

    @SerializedName("ID")
    protected int id;

    @SerializedName("Type")
    protected String type;

    @SerializedName("Description")
    protected String desc;

    @SerializedName("RecordCreateTimestamp")
    protected Date recordCreated;

    @SerializedName("RecordUpdateTimestamp")
    protected Date recordUpdated;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
