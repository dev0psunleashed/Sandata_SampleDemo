//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.30 at 12:34:36 PM EDT 
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
 * <p>Java class for Postal_Code_United_States_Postal_Service_City_Type_Code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Postal_Code_United_States_Postal_Service_City_Type_Code">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="1"/>
 *     &lt;enumeration value="D"/>
 *     &lt;enumeration value="A"/>
 *     &lt;enumeration value="N"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Postal_Code_United_States_Postal_Service_City_Type_Code")
@XmlEnum
public enum PostalCodeUnitedStatesPostalServiceCityTypeCode {


    /**
     * "Default - This is the ""preferred"" name - by the USPS - for a city. Each ZIP Code has one - and only one - ""default"" name. In most cases
     * 
     */
    D,

    /**
     *  this is what people who live in that area call the city as well."
     * 
     */
    A,

    /**
     * "Acceptable - This name can be used for mailing purposes. Often times alternative names are large neighborhoods or sections of the city/town. In some cases a ZIP Code may have several ""acceptable"" names which is used to group towns under one ZIP Code."
     * 
     */
    N;

    public String value() {
        return name();
    }

    public static PostalCodeUnitedStatesPostalServiceCityTypeCode fromValue(String v) {
        return valueOf(v);
    }

}
