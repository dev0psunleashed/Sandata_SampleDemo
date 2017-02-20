//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.27 at 10:53:27 PM EST 
//


package com.sandata.lab.rest.oracle.model;

import com.sandata.lab.data.model.*;
import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Patient_Status_Name.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Patient_Status_Name">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="50"/>
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="HOLD"/>
 *     &lt;enumeration value="DISCHARGED"/>
 *     &lt;enumeration value="REFERRAL"/>
 *     &lt;enumeration value="NOT TAKEN UNDER CARE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Patient_Status_Name")
@XmlEnum
public enum PatientStatusName {

    ACTIVE("ACTIVE"),
    HOLD("HOLD"),
    DISCHARGED("DISCHARGED"),
    REFERRAL("REFERRAL"),
    @XmlEnumValue("NOT TAKEN UNDER CARE")
    NOT_TAKEN_UNDER_CARE("NOT TAKEN UNDER CARE");
    private final String value;

    PatientStatusName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PatientStatusName fromValue(String v) {
        for (PatientStatusName c: PatientStatusName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
