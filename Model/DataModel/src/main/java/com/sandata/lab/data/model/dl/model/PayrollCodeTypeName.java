//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 01:27:57 PM EDT 
//


package com.sandata.lab.data.model.dl.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Payroll_Code_Type_Name.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Payroll_Code_Type_Name">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="50"/>
 *     &lt;enumeration value="HOURS"/>
 *     &lt;enumeration value="MONEY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Payroll_Code_Type_Name")
@XmlEnum
public enum PayrollCodeTypeName {


    /**
     * Hours
     * 
     */
    HOURS,

    /**
     * Money
     * 
     */
    MONEY;

    public String value() {
        return name();
    }

    public static PayrollCodeTypeName fromValue(String v) {
        return valueOf(v);
    }

}