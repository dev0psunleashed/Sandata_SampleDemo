package com.sandata.lab.data.model.dl.model.extended.visit;

import com.google.gson.annotations.SerializedName;

public enum SupervisoryVisitFrequency {

    @SerializedName("Every 14 days")
    FOURTEEN_DAYS("Every 14 days"),
    @SerializedName("Every 30 days")
    THIRTY_DAYS("Every 30 days"),
    @SerializedName("Every 60 days")
    SIXTY_DAYS("Every 60 days"),
    @SerializedName("Every 90 days")
    NINETY_DAYS("Every 90 days"),
    @SerializedName("Every 120 days")
    HUNDRED_TWENTY_DAYS("Every 120 days"),
    @SerializedName("Every 180 days")
    HUNDRED_EIGHTY_DAYS("Every 180 days");

    private final String value;

    SupervisoryVisitFrequency(String v) {
        value = v;
    }

    public String value() {
        return name();
    }

    public static SupervisoryVisitFrequency fromValue(String v) {
        for (SupervisoryVisitFrequency c: SupervisoryVisitFrequency.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
