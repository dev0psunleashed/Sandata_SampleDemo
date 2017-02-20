//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.28 at 11:05:46 PM EDT 
//


package com.sandata.lab.data.model.dl.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Visit_Task_Type_Code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Visit_Task_Type_Code">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="20"/>
 *     &lt;enumeration value="TEL"/>
 *     &lt;enumeration value="FVV"/>
 *     &lt;enumeration value="MVV"/>
 *     &lt;enumeration value="UI"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Visit_Task_Type_Code")
@XmlEnum
public enum VisitTaskTypeCode {


    /**
     * Telephony / IVR (Interactive Voice Response)
     * 
     */
    TEL,

    /**
     * Fixed Visit Verification device
     * 
     */
    FVV,

    /**
     * Mobile Visit Verification device
     * 
     */
    MVV,

    /**
     * User Interface visit verification
     * 
     */
    UI;

    public String value() {
        return name();
    }

    public static VisitTaskTypeCode fromValue(String v) {
        return valueOf(v);
    }

}
