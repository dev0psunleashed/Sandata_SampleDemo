//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 01:27:57 PM EDT 
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
 * <p>Java class for Phone_Qualifier.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Phone_Qualifier">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="30"/>
 *     &lt;enumeration value="HOME"/>
 *     &lt;enumeration value="WORK"/>
 *     &lt;enumeration value="MOBILE"/>
 *     &lt;enumeration value="FAX"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Phone_Qualifier")
@XmlEnum
public enum PhoneQualifier {


    /**
     * Home Phone
     * 
     */
    @SerializedName("Home")
    HOME("HOME"),

    /**
     * Work Phone
     * 
     */
    @SerializedName("Work")
    WORK("WORK"),

    /**
     * Mobile Phone
     * 
     */
    @SerializedName("Mobile")
    MOBILE("MOBILE"),

    /**
     * Fax
     * 
     */
    @SerializedName("Fax")
    FAX("FAX"),

    /**
     * Other
     * 
     */
    @SerializedName("Other")
    OTHER("OTHER");

    private final String value;

    PhoneQualifier(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PhoneQualifier fromValue(String v) {
        for (PhoneQualifier c: PhoneQualifier.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        return valueOf(v);
    }
}
