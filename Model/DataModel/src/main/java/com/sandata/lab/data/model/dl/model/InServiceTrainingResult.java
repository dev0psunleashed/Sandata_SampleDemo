package com.sandata.lab.data.model.dl.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>InServiceTrainingResult</p>
 * <p>Date: 7/7/2016</p>
 * <p>Time: 11:20 AM</p>
 *
 * @author jasonscott
 */

@XmlType(name = "In_Service_Training_Result")
@XmlEnum
public enum InServiceTrainingResult {

    @XmlEnumValue("Enrolled")
    ENROLLED("Enrolled"),
    @XmlEnumValue("Completed")
    COMPLETED("Completed"),
    @XmlEnumValue("No Show")
    NO_SHOW("No Show"),
    @XmlEnumValue("Dropped")
    DROPPED("Dropped");

    private final String value;

    InServiceTrainingResult(String v) {
        value = v;
    }

    @Override
    public String toString() {
        return value;
    }

    public static InServiceTrainingResult fromValue(String v) {
        for (InServiceTrainingResult inServiceTrainingResult : InServiceTrainingResult.values()) {
            if (inServiceTrainingResult.value.equalsIgnoreCase(v)) {
                return inServiceTrainingResult;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
