package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;

public enum ScheduleParticipantExclusionQualifier {

    /**
     * Patient's preference not to receive service by staff specified
     *
     */
    @SerializedName("Patient")
    PATIENT("PATIENT"),

    /**
     * Staff's preference not to be scheduled for a specific patient
     *
     */
    @SerializedName("Staff")
    STAFF("STAFF");
    
    private final String value;

    ScheduleParticipantExclusionQualifier(String v) {
        value = v;
    }

    public String value() {
        return value;
    }
    
    public static ScheduleParticipantExclusionQualifier fromValue(String v) {
        for (ScheduleParticipantExclusionQualifier c: ScheduleParticipantExclusionQualifier.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        return valueOf(v);
    }
}
