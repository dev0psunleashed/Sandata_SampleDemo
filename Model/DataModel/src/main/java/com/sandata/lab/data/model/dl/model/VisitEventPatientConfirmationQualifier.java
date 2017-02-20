package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;

/// https://mnt-ers-ts01.sandata.com/object/view.spg?tab=properties&key=685140&diagramKey=66819
public enum VisitEventPatientConfirmationQualifier {

    /**
     * Confirm
     */
    @SerializedName("Confirm")
    CONFIRM("Confirm"),

    /**
     * Deny
     */
    @SerializedName("Deny")
    DENY("Deny"),

    /**
     * Bypass
     */
    @SerializedName("Bypass")
    BYPASS("Bypass");

    private final String value;

    VisitEventPatientConfirmationQualifier(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VisitEventPatientConfirmationQualifier fromValue(String v) {
        for (VisitEventPatientConfirmationQualifier s : VisitEventPatientConfirmationQualifier.values()) {
            if (s.value.equalsIgnoreCase(v)) {
                return s;
            }
        }

        return valueOf(v);
    }
}
