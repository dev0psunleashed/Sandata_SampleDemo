package com.sandata.lab.data.model.result;

import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;

public class SequenceKeyValueResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    private BigInteger sequenceKey;
    private String value;

    public BigInteger getSequenceKey() {
        return sequenceKey;
    }

    public void setSequenceKey(BigInteger sequenceKey) {
        this.sequenceKey = sequenceKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
