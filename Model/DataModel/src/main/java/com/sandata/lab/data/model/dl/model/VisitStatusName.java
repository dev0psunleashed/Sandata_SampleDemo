package com.sandata.lab.data.model.dl.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.SerializedName;

@XmlType(name = "Visit_Status_Name")
@XmlEnum
public enum VisitStatusName {

    /**
     * Omitted
     */
    @SerializedName("Omitted")
    OMITTED("Omitted"),

    /**
     * Scheduled
     */
    @SerializedName("Scheduled")
    SCHEDULED("Scheduled"),

    /**
     * In Process
     */
    @SerializedName("In Process")
    IN_PROCESS("In Process"),

    /**
     * Incomplete
     */
    @SerializedName("Incomplete")
    INCOMPLETE("Incomplete"),

    /**
     * Verified
     */
    @SerializedName("Verified")
    VERIFIED("Verified"),

    /**
     * Processed
     */
    @SerializedName("Processed")
    PROCESSED("Processed");

    private final String value;

    VisitStatusName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VisitStatusName fromValue(String v) {
        for (VisitStatusName s : VisitStatusName.values()) {
            if (s.value.equalsIgnoreCase(v)) {
                return s;
            }
        }

        return valueOf(v);
    }
}
