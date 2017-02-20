//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 01:27:57 PM EDT 
//


package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Patient_Assessment_Frequency.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Patient_Assessment_Frequency">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="50"/>
 *     &lt;enumeration value="Every 30 Days"/>
 *     &lt;enumeration value="Every 60 Days"/>
 *     &lt;enumeration value="Every 90 Days"/>
 *     &lt;enumeration value="Every 180 Days"/>
 *     &lt;enumeration value="Every 365 Days"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Patient_Assessment_Frequency")
@XmlEnum
public enum PatientAssessmentFrequency {

    @SerializedName("Every 30 Days")
    EVERY_30_DAYS("Every 30 Days"),
    @SerializedName("Every 60 Days")
    EVERY_60_DAYS("Every 60 Days"),
    @SerializedName("Every 90 Days")
    EVERY_90_DAYS("Every 90 Days"),
    @SerializedName("Every 180 Days")
    EVERY_180_DAYS("Every 180 Days"),
    @SerializedName("Every 365 Days")
    EVERY_365_DAYS("Every 365 Days");
    private final String value;

    PatientAssessmentFrequency(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PatientAssessmentFrequency fromValue(String v) {
        for (PatientAssessmentFrequency c: PatientAssessmentFrequency.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        return valueOf(v);
    }

}
