package com.sandata.lab.data.model.dl.model.extended.reference;

import com.google.gson.annotations.SerializedName;

public enum Frequency {

    @SerializedName("Day(s)")
    Day("Day(s)"),
    @SerializedName("Week(s)")
    Week("Week(s)"),
    @SerializedName("Month(s)")
    Month("Month(s)"),
    @SerializedName("Year(s)")
    Year("Year(s)");

    private final String value;

    Frequency(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Frequency fromValue(String v) {
        for (Frequency c: Frequency.values()) {
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
