//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.27 at 10:53:27 PM EST 
//


package com.sandata.lab.rest.oracle.model;

import com.sandata.lab.data.model.*;
import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;


import java.math.BigInteger;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for Schedule_Authorization complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Schedule_Authorization">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Schedule_Authorization_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Change_Version_ID" use="required" type="{}Change_Version_ID" />
 *       &lt;attribute name="Schedule_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Authorization_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Authorization_Qualifier" use="required" type="{}Authorization_Qualifier" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Schedule_Authorization")
@OracleMetadata(
        packageName = "PKG_SCHEDULE",
        insertMethod = "insertSchedAuth",
        updateMethod = "updateSchedAuth",
        deleteMethod = "deleteSchedAuth",
        getMethod = "getSchedAuth",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.SchedAuthT",
        primaryKeys = {})
public class ScheduleAuthorization extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Schedule_Authorization_SK", required = true)
    @SerializedName("ScheduleAuthorizationSK")
	@Mapping(getter = "getSchedAuthSk", setter = "setSchedAuthSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger scheduleAuthorizationSK;
    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
	@Mapping(getter = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = 1)
    protected Date recordUpdateTimestamp;
    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
	@Mapping(getter = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = 2)
    protected Date recordCreateTimestamp;
    @XmlAttribute(name = "Change_Version_ID", required = true)
    @SerializedName("ChangeVersionID")
	@Mapping(getter = "getChangeVersionId", setter = "setChangeVersionId", type = "java.math.BigDecimal", index = 3)
    protected BigInteger changeVersionID;
    @XmlAttribute(name = "Schedule_SK", required = true)
    @SerializedName("ScheduleSK")
	@Mapping(getter = "getSchedSk", setter = "setSchedSk", type = "java.math.BigDecimal", index = 4)
    protected BigInteger scheduleSK;
    @XmlAttribute(name = "Authorization_SK", required = true)
    @SerializedName("AuthorizationSK")
	@Mapping(getter = "getAuthSk", setter = "setAuthSk", type = "java.math.BigDecimal", index = 5)
    protected BigInteger authorizationSK;
    @XmlAttribute(name = "Authorization_Qualifier", required = true)
    @SerializedName("AuthorizationQualifier")
	@Mapping(getter = "getAuthQlfr", setter = "setAuthQlfr", type = "String", index = 6)
    protected AuthorizationQualifier authorizationQualifier;

    /**
     * Gets the value of the scheduleAuthorizationSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getScheduleAuthorizationSK() {
        return scheduleAuthorizationSK;
    }

    /**
     * Sets the value of the scheduleAuthorizationSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setScheduleAuthorizationSK(BigInteger value) {
        this.scheduleAuthorizationSK = value;
    }

    /**
     * Gets the value of the recordUpdateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordUpdateTimestamp() {
        return recordUpdateTimestamp;
    }

    /**
     * Sets the value of the recordUpdateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordUpdateTimestamp(Date value) {
        this.recordUpdateTimestamp = value;
    }

    /**
     * Gets the value of the recordCreateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordCreateTimestamp() {
        return recordCreateTimestamp;
    }

    /**
     * Sets the value of the recordCreateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordCreateTimestamp(Date value) {
        this.recordCreateTimestamp = value;
    }

    /**
     * Gets the value of the changeVersionID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getChangeVersionID() {
        return changeVersionID;
    }

    /**
     * Sets the value of the changeVersionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setChangeVersionID(BigInteger value) {
        this.changeVersionID = value;
    }

    /**
     * Gets the value of the scheduleSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getScheduleSK() {
        return scheduleSK;
    }

    /**
     * Sets the value of the scheduleSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setScheduleSK(BigInteger value) {
        this.scheduleSK = value;
    }

    /**
     * Gets the value of the authorizationSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAuthorizationSK() {
        return authorizationSK;
    }

    /**
     * Sets the value of the authorizationSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAuthorizationSK(BigInteger value) {
        this.authorizationSK = value;
    }

    /**
     * Gets the value of the authorizationQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link AuthorizationQualifier }
     *     
     */
    public AuthorizationQualifier getAuthorizationQualifier() {
        return authorizationQualifier;
    }

    /**
     * Sets the value of the authorizationQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorizationQualifier }
     *     
     */
    public void setAuthorizationQualifier(AuthorizationQualifier value) {
        this.authorizationQualifier = value;
    }

}