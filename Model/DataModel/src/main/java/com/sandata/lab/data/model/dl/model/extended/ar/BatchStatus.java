package com.sandata.lab.data.model.dl.model.extended.ar;

import com.google.gson.annotations.SerializedName;

/**
 * BAR-248: AR_TXN_BATCH.AR_TXN_BATCH_POST_IND
 *
 * 0: OPEN
 * 1: POSTED
 *
 * <p/>
 *
 * @author David Rutgos
 */
public enum BatchStatus {

    @SerializedName("Open")
    OPEN("Open"),
    @SerializedName("Posted")
    POSTED("Posted");

    private final String value;

    BatchStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BatchStatus fromValue(String v) {
        for (BatchStatus c: BatchStatus.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        return valueOf(v);
    }

    public String toString() {
        return this.value;
    }
}
