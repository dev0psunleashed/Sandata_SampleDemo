//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 10:25:43 PM EDT 
//


package com.sandata.lab.data.model.dl.model;

import com.sandata.lab.data.model.*;
import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.Adapter1;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Hierarchical data store for system configuration. Stores data as a key-value pairs.
 * 
 * <p>Java class for Application_System_Key_Configuration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Application_System_Key_Configuration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Application_System_Key_Configuration" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Application_System_Key_Configuration_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Application_System_Key_Configuration_Parent_SK" type="{}Surrogate_Key" />
 *       &lt;attribute name="Key_Name" use="required" type="{}Key_Name" />
 *       &lt;attribute name="System_Key_Configuration_Value" type="{}Key_Value" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Application_System_Key_Configuration", propOrder = {
    "applicationSystemKeyConfiguration"
})
@OracleMetadata(
        packageName = "PKG_APP",
        insertMethod = "insertAppSysKeyConf",
        updateMethod = "updateAppSysKeyConf",
        deleteMethod = "deleteAppSysKeyConf",
        getMethod = "getAppSysKeyConf",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.AppSysKeyConfT",
        primaryKeys = {})
public class ApplicationSystemKeyConfiguration extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlElement(name = "Application_System_Key_Configuration")
    @SerializedName("ApplicationSystemKeyConfiguration")
    protected List<ApplicationSystemKeyConfiguration> applicationSystemKeyConfiguration;
    @XmlAttribute(name = "Application_System_Key_Configuration_SK", required = true)
    @SerializedName("ApplicationSystemKeyConfigurationSK")
	@Mapping(getter = "getAppSysKeyConfSk", setter = "setAppSysKeyConfSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger applicationSystemKeyConfigurationSK;
    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = 1)
    protected Date recordCreateTimestamp;
    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(Adapter1.class)
	@Mapping(getter = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = 2)
    protected Date recordUpdateTimestamp;
    @XmlAttribute(name = "Application_System_Key_Configuration_Parent_SK")
    @SerializedName("ApplicationSystemKeyConfigurationParentSK")
	@Mapping(getter = "getAppSysKeyConfParSk", setter = "setAppSysKeyConfParSk", type = "java.math.BigDecimal", index = 3)
    protected BigInteger applicationSystemKeyConfigurationParentSK;
    @XmlAttribute(name = "Key_Name", required = true)
    @SerializedName("KeyName")
	@Mapping(getter = "getKeyName", setter = "setKeyName", type = "String", index = 4)
    protected String keyName;
    @XmlAttribute(name = "System_Key_Configuration_Value")
    @SerializedName("SystemKeyConfigurationValue")
	@Mapping(getter = "getSysKeyConfVal", setter = "setSysKeyConfVal", type = "String", index = 5)
    protected String systemKeyConfigurationValue;

    /**
     * Gets the value of the applicationSystemKeyConfiguration property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationSystemKeyConfiguration property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationSystemKeyConfiguration().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicationSystemKeyConfiguration }
     * 
     * 
     */
    public List<ApplicationSystemKeyConfiguration> getApplicationSystemKeyConfiguration() {
        if (applicationSystemKeyConfiguration == null) {
            applicationSystemKeyConfiguration = new ArrayList<ApplicationSystemKeyConfiguration>();
        }
        return this.applicationSystemKeyConfiguration;
    }

    /**
     * Gets the value of the applicationSystemKeyConfigurationSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationSystemKeyConfigurationSK() {
        return applicationSystemKeyConfigurationSK;
    }

    /**
     * Sets the value of the applicationSystemKeyConfigurationSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationSystemKeyConfigurationSK(BigInteger value) {
        this.applicationSystemKeyConfigurationSK = value;
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
     * Gets the value of the applicationSystemKeyConfigurationParentSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationSystemKeyConfigurationParentSK() {
        return applicationSystemKeyConfigurationParentSK;
    }

    /**
     * Sets the value of the applicationSystemKeyConfigurationParentSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationSystemKeyConfigurationParentSK(BigInteger value) {
        this.applicationSystemKeyConfigurationParentSK = value;
    }

    /**
     * Gets the value of the keyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * Sets the value of the keyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyName(String value) {
        this.keyName = value;
    }

    /**
     * Gets the value of the systemKeyConfigurationValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemKeyConfigurationValue() {
        return systemKeyConfigurationValue;
    }

    /**
     * Sets the value of the systemKeyConfigurationValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemKeyConfigurationValue(String value) {
        this.systemKeyConfigurationValue = value;
    }

}
