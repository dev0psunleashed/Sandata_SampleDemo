//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.28 at 03:44:22 PM EDT 
//


package com.sandata.lab.data.model.dl.model;

import com.sandata.lab.data.model.*;
import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;


import com.sandata.lab.data.model.*;
import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Element_Type_Name.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Element_Type_Name">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="50"/>
 *     &lt;enumeration value="SCHEMA"/>
 *     &lt;enumeration value="TABLE"/>
 *     &lt;enumeration value="COLUMN"/>
 *     &lt;enumeration value="VIEW"/>
 *     &lt;enumeration value="ENTITY"/>
 *     &lt;enumeration value="ATTRIBUTE"/>
 *     &lt;enumeration value="COLLECTION"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Element_Type_Name")
@XmlEnum
public enum ElementTypeName {

    SCHEMA,
    TABLE,
    COLUMN,
    VIEW,
    ENTITY,
    ATTRIBUTE,
    COLLECTION;

    public String value() {
        return name();
    }

    public static ElementTypeName fromValue(String v) {
        return valueOf(v);
    }

}
