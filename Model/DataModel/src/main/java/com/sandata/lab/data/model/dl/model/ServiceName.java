//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.28 at 11:05:46 PM EDT 
//


package com.sandata.lab.data.model.dl.model;

import com.sandata.lab.data.model.*;
import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Service_Name.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Service_Name">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="20"/>
 *     &lt;enumeration value="RN"/>
 *     &lt;enumeration value="LPN"/>
 *     &lt;enumeration value="HHA"/>
 *     &lt;enumeration value="PCA"/>
 *     &lt;enumeration value="HSK"/>
 *     &lt;enumeration value="HMK"/>
 *     &lt;enumeration value="OT"/>
 *     &lt;enumeration value="PT"/>
 *     &lt;enumeration value="ST"/>
 *     &lt;enumeration value="PCW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Service_Name")
@XmlEnum
public enum ServiceName {


    /**
     * Registered Nurse
     * 
     */
    RN,

    /**
     * Licensed Practical Nurse
     * 
     */
    LPN,

    /**
     * Home Health Aid
     * 
     */
    HHA,

    /**
     * Patient Care Assistant
     * 
     */
    PCA,

    /**
     * Housekeeping
     * 
     */
    HSK,

    /**
     * Homemaking
     * 
     */
    HMK,

    /**
     * Occupational Therapy
     * 
     */
    OT,

    /**
     * Physical Therapy
     * 
     */
    PT,

    /**
     * Speech Therapy
     * 
     */
    ST,

    /**
     * Personal Care Worker
     * 
     */
    PCW;

    public String value() {
        return name();
    }

    public static ServiceName fromValue(String v) {
        return valueOf(v);
    }

}
