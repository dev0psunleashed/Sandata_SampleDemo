package com.sandata.lab.data.model.dl.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>CompliantIndicator</p>
 * <p>Date: 7/7/2016</p>
 * <p>Time: 11:20 AM</p>
 *
 * @author jasonscott
 */

@XmlType(name = "Compliant_Indicator")
@XmlEnum
public enum CompliantIndicator {

    @XmlEnumValue("Compliant")
    COMPLIANT("Compliant"),
    @XmlEnumValue("Non-Compliant")
    NON_COMPLIANT("Non-Compliant");

    private final String value;

    CompliantIndicator(String v) {
        value = v;
    }

    @Override
    public String toString() {
        return value;
    }

    public static CompliantIndicator fromValue(String v) {
        for (CompliantIndicator c : CompliantIndicator.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
