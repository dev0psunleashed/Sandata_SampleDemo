package com.sandata.lab.rest.am.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

public class ReportKeyValue extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("Key")
    private String key;

    @SerializedName("Value")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
