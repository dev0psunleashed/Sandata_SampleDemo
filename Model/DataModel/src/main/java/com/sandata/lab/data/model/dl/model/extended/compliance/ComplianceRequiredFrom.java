package com.sandata.lab.data.model.dl.model.extended.compliance;

import com.google.gson.annotations.SerializedName;

public enum ComplianceRequiredFrom {

    @SerializedName("Hire Date")
    HIRE_DATE("Hire Date"),
    @SerializedName("First Day Work")
    FIRST_DAY_WORK("First Day Work");

    private final String value;

    ComplianceRequiredFrom(String v) {
        value = v;
    }

    public String value() {
        return name();
    }

    public static ComplianceRequiredFrom fromValue(String v) {
        for (ComplianceRequiredFrom c: ComplianceRequiredFrom.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
