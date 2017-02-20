package com.sandata.lab.data.model.dl.model.extended.compliance;

import com.google.gson.annotations.SerializedName;

public enum  ComplianceNotificationPeriod {

    @SerializedName("7")
    SEVEN_DAYS("7"),
    @SerializedName("14")
    FOURTEEN_DAYS("14"),
    @SerializedName("30")
    THIRTY_DAYS("30"),
    @SerializedName("45")
    FORTY_FIVE_DAYS("45"),
    @SerializedName("60")
    SIXTY_DAYS("60"),
    @SerializedName("75")
    SEVENTY_FIVE_DAYS("75");

    private final String value;

    ComplianceNotificationPeriod(String v) {
        value = v;
    }

    public String value() {
        return name();
    }

    public static ComplianceNotificationPeriod fromValue(String v) {
        for (ComplianceNotificationPeriod c: ComplianceNotificationPeriod.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
