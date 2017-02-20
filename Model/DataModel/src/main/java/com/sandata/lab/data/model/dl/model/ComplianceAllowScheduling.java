package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;

public enum ComplianceAllowScheduling {

    //dmr--GEOR-6683:
    //dmr--NOTE: There should be consistency with naming. UI request this to be WARN and in scheduling it is WARNING (AllowScheduling entity).
    @SerializedName("Allow")
    ALLOW("Allow"),
    @SerializedName("Prevent")
    PREVENT("Prevent"),
    @SerializedName("Warn")
    WARN("Warn");

    private final String value;

    ComplianceAllowScheduling(String v) {
        value = v;
    }

    @Override
    public String toString() {
        return value;
    }

    public static ComplianceAllowScheduling fromValue(String v) {
        for (ComplianceAllowScheduling a : ComplianceAllowScheduling.values()) {
            if (a.value.equalsIgnoreCase(v)) {
                return a;
            }
        }

        return valueOf(v);
    }
}
