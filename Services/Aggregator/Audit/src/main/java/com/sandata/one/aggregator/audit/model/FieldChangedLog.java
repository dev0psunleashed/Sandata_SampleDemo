package com.sandata.one.aggregator.audit.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

public class FieldChangedLog extends BaseObject {

    private static final long serialVersionUID = -813795007480920534L;

    @SerializedName("ChangedField")
    private String changedField;

    @SerializedName("ChangedFrom")
    private String changedFrom;

    @SerializedName("ChangedTo")
    private String changedTo;

    /**
     * @return the changedField
     */
    public String getChangedField() {
        return changedField;
    }

    /**
     * @param changedField the changedField to set
     */
    public void setChangedField(String changedField) {
        this.changedField = changedField;
    }

    /**
     * @return the changedFrom
     */
    public String getChangedFrom() {
        return changedFrom;
    }

    /**
     * @param changedFrom the changedFrom to set
     */
    public void setChangedFrom(String changedFrom) {
        this.changedFrom = changedFrom;
    }

    /**
     * @return the changedTo
     */
    public String getChangedTo() {
        return changedTo;
    }

    /**
     * @param changedTo the changedTo to set
     */
    public void setChangedTo(String changedTo) {
        this.changedTo = changedTo;
    }
}
