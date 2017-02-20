package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for Compliance_Result. 
 * 
 */
@XmlType(name = "Compliance_Result")
@XmlEnum
public enum ComplianceResult {

    @SerializedName("Complete")
    COMPLETE("Complete"),

    @SerializedName("Not Complete")
    NOT_COMPLETE("Not Complete"),

    @SerializedName("Employable")
    EMPLOYABLE("Employable"),

    @SerializedName("Not Employable")
    NOT_EMPLOYABLE("Not Employable"),

    @SerializedName("Decline")
    DECLINE("Decline"),

    @SerializedName("Consent")
    CONSENT("Consent"),

    @SerializedName("Immune")
    IMMUNE("Immune"),

    @SerializedName("Not Immune")
    NOT_IMMUNE("Not Immune"),

    @SerializedName("Positive")
    POSITIVE("Positive"),

    @SerializedName("Negative")
    NEGATIVE("Negative"),

    @SerializedName("Pregnant")
    PREGNANT("Pregnant");

    private final String value;

    ComplianceResult(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ComplianceResult fromValue(String v) {
        for (ComplianceResult c: ComplianceResult.values()) {
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
