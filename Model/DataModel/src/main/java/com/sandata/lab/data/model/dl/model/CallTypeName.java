package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;

public enum CallTypeName {

    /**
     * TEL
     */
    @SerializedName("TEL")
    TEL("TEL"),

    /**
     * MVV
     */
    @SerializedName("MVV")
    MVV("MVV"),

    /**
     * FVV
     */
    @SerializedName("FVV")
    FVV("FVV"),

    /**
     * UI
     */
    @SerializedName("UI")
    UI("UI"),

    /**
     * OTHER
     */
    @SerializedName("OTHER")
    OTHER("OTHER");

    private final String value;

    CallTypeName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CallTypeName fromValue(String v) {
        for (CallTypeName s : CallTypeName.values()) {
            if (s.value.equalsIgnoreCase(v)) {
                return s;
            }
        }

        return valueOf(v);
    }
}
