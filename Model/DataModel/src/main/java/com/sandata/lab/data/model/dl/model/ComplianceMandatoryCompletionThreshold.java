package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;

public enum ComplianceMandatoryCompletionThreshold {

    @SerializedName("Days")
    DAYS("Days"),
    @SerializedName("Weeks")
    WEEKS("Weeks"),
    @SerializedName("Months")
    MONTHS("Months"),
    @SerializedName("Years")
    YEARS("Years");

    private final String value;

    ComplianceMandatoryCompletionThreshold(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ComplianceMandatoryCompletionThreshold fromValue(String v) {
        for (ComplianceMandatoryCompletionThreshold c: ComplianceMandatoryCompletionThreshold.values()) {
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
