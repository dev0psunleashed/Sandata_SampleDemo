//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.28 at 11:05:46 PM EDT 
//


package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Claim_Submission_Type_Name.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Claim_Submission_Type_Name">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="50"/>
 *     &lt;enumeration value="NEW"/>
 *     &lt;enumeration value="SECOND SUBMISSION"/>
 *     &lt;enumeration value="CORRECTED"/>
 *     &lt;enumeration value="ADJUSTED"/>
 *     &lt;enumeration value="APPEAL"/>
 *     &lt;enumeration value="VOID"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Claim_Submission_Type_Name")
@XmlEnum
public enum ClaimSubmissionTypeName {


    /**
     * New claim
     * 
     */
    @SerializedName("New")
    NEW("New"),

    /**
     * Second submmission
     * 
     */
    @SerializedName("Second Submission")
    SECOND_SUBMISSION("Second Submission"),

    /**
     * Corrected claim
     * 
     */
    @SerializedName("Corrected")
    CORRECTED("Corrected"),

    /**
     * Adjusted claim
     * 
     */
    @SerializedName("Adjusted")
    ADJUSTED("Adjusted"),

    /**
     * Claim appeal
     * 
     */
    @SerializedName("Appeal")
    APPEAL("Appeal"),

    /**
     * Void claim
     * 
     */
    @SerializedName("Void")
    VOID("Void");
    private final String value;

    ClaimSubmissionTypeName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ClaimSubmissionTypeName fromValue(String v) {
        for (ClaimSubmissionTypeName c: ClaimSubmissionTypeName.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        return valueOf(v);
    }

}