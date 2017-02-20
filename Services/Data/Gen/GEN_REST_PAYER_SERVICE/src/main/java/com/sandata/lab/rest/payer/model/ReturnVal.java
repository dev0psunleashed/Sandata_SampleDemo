package com.sandata.lab.rest.payer.model;

import com.sandata.lab.data.model.base.BaseObject;

public class ReturnVal extends BaseObject {

    private static final long serialVersionUID = 1L;

    private long value;
    private String id;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
