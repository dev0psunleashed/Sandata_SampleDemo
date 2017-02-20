//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.27 at 10:53:27 PM EST 
//


using Sandata.George.Common;
using Sandata.George.Common.Helpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;





/**
 * <p>Java class for Patient_Designe_Contact_Detail_Type_Name.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Patient_Designe_Contact_Detail_Type_Name">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="50"/>
 *     &lt;enumeration value="RESPONSIBLE PARTY"/>
 *     &lt;enumeration value="EMERGENCY CONTACT"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
public enum PatientDesigneContactDetailTypeName {


    /**
     * "Identifies the contact as ""Responsible Party"""
     * 
     */
    RESPONSIBLE_PARTY("RESPONSIBLE PARTY"),

    /**
     * "Identifies the contact as ""In Case Of Emergnecy"""
     * 
     */
    EMERGENCY_CONTACT("EMERGENCY CONTACT"),

    /**
     * "Identifies the contact as ""Other"""
     * 
     */
    OTHER("OTHER");
    private final String value;

    PatientDesigneContactDetailTypeName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PatientDesigneContactDetailTypeName fromValue(String v) {
        for (PatientDesigneContactDetailTypeName c: PatientDesigneContactDetailTypeName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
	}
}