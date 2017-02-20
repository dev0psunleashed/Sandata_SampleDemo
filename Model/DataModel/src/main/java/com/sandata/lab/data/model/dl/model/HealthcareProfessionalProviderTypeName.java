package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;

public enum HealthcareProfessionalProviderTypeName {

    @SerializedName("Referring")
    REFERRING("Referring"),
    @SerializedName("Primary")
    PRIMARY("Primary"),
    @SerializedName("Secondary")
    SECONDARY("Secondary"),
    @SerializedName("Certifying")
    CERTIFYING("Certifying"),
    @SerializedName("Other")
    OTHER("Other");

    private final String value;

    HealthcareProfessionalProviderTypeName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HealthcareProfessionalProviderTypeName fromValue(String v) {
        for (HealthcareProfessionalProviderTypeName c: HealthcareProfessionalProviderTypeName.values()) {
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
