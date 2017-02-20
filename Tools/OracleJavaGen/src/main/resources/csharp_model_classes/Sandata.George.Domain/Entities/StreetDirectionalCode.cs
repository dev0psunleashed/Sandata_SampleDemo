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
 * <p>Java class for Street_Directional_Code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Street_Directional_Code">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="2"/>
 *     &lt;enumeration value="N"/>
 *     &lt;enumeration value="S"/>
 *     &lt;enumeration value="E"/>
 *     &lt;enumeration value="W"/>
 *     &lt;enumeration value="NE"/>
 *     &lt;enumeration value="NW"/>
 *     &lt;enumeration value="SE"/>
 *     &lt;enumeration value="SW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
public enum StreetDirectionalCode {


    /**
     * North
     * 
     */
    N,

    /**
     * South
     * 
     */
    S,

    /**
     * East
     * 
     */
    E,

    /**
     * West
     * 
     */
    W,

    /**
     * Northeast
     * 
     */
    NE,

    /**
     * Northwest
     * 
     */
    NW,

    /**
     * Southeast
     * 
     */
    SE,

    /**
     * Southwest
     * 
     */
    SW;

    public String value() {
        return name();
    }

    public static StreetDirectionalCode fromValue(String v) {
        return valueOf(v);
    }

}
	}
}