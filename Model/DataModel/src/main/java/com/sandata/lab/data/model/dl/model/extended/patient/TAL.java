package com.sandata.lab.data.model.dl.model.extended.patient;

import com.google.gson.annotations.SerializedName;

/**
 * GEOR-5711: T.A.L.: Patient Transportation Assistance Level.
 *
 * https://mnt-ers-ts01.sandata.com/object/view.spg?key=203137
 * <p/>
 *
 * @author David Rutgos
 */
public enum TAL {

    /**
     * Non-Ambulatory
     *
     */
    @SerializedName("Non-Ambulatory")
    NON_AMBULATORY("NON-AMBULATORY"),

    /**
     * Wheelchair
     *
     */
    @SerializedName("Wheelchair")
    WHEELCHAIR("WHEELCHAIR"),

    /**
     * Ambulatory
     *
     */
    @SerializedName("Ambulatory")
    AMBULATORY("AMBULATORY");

    private final String value;

    TAL(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TAL fromValue(String v) {
        for (TAL c: TAL.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        return valueOf(v);
    }
}
