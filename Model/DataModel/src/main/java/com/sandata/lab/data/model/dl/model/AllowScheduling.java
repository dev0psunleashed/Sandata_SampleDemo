package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>AllowScheduling</p>
 * <p>Date: 7/7/2016</p>
 * <p>Time: 11:20 AM</p>
 *
 * @author jasonscott
 */

@XmlType(name = "Allow_Scheduling")
@XmlEnum
public enum AllowScheduling {

    //dmr--SAN-430: Remove Allow
    //dmr--@SerializedName("Allow")
    //dmr--ALLOW("Allow"),
    @SerializedName("Prevent")
    PREVENT("Prevent"),
    @SerializedName("Warning")
    WARNING("Warning");

    private final String value;

    AllowScheduling(String v) {
        value = v;
    }

    @Override
    public String toString() {
        return value;
    }

    public static AllowScheduling fromValue(String v) {
        for (AllowScheduling a : AllowScheduling.values()) {
            if (a.value.equalsIgnoreCase(v)) {
                return a;
            }
        }

        return valueOf(v);
    }
}
