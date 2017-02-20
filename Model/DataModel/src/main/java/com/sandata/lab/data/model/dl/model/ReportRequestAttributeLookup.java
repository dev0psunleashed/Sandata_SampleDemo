//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.28 at 03:44:22 PM EDT 
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
 * The lookup entitiy that contains the list of all the attributes to describe report request characteristics. 
 * 
 * <p>Java class for Report_Request_Attribute_Lookup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Report_Request_Attribute_Lookup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Report_Request_Attribute" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Report_Request_Attribute_Lookup_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Report_Request_Attribute_Key_Name">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *             &lt;enumeration value="PATIENT_NAME"/>
 *             &lt;enumeration value="PATIENT_ID"/>
 *             &lt;enumeration value="STAFF_NAME"/>
 *             &lt;enumeration value="STAFF_ID"/>
 *             &lt;enumeration value="PAYER_TYPE"/>
 *             &lt;enumeration value="PAYER"/>
 *             &lt;enumeration value="CONTRACT"/>
 *             &lt;enumeration value="LOCATION"/>
 *             &lt;enumeration value="COORDINATOR"/>
 *             &lt;enumeration value="SERVICE"/>
 *             &lt;enumeration value="PROGRAM"/>
 *             &lt;enumeration value="CITY"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Report_Request_Attribute_Key_Description" type="{}Description_Long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Report_Request_Attribute_Lookup", propOrder = {
    "reportRequestAttribute"
})
public class ReportRequestAttributeLookup extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlElement(name = "Report_Request_Attribute")
    @SerializedName("ReportRequestAttribute")
    protected List<ReportRequestAttribute> reportRequestAttribute;
    @XmlAttribute(name = "Report_Request_Attribute_Lookup_SK", required = true)
    @SerializedName("ReportRequestAttributeLookupSK")
    protected BigInteger reportRequestAttributeLookupSK;
    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
    protected Date recordCreateTimestamp;
    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(Adapter1.class)
    protected Date recordUpdateTimestamp;
    @XmlAttribute(name = "Report_Request_Attribute_Key_Name")
    @SerializedName("ReportRequestAttributeKeyName")
    protected String reportRequestAttributeKeyName;
    @XmlAttribute(name = "Report_Request_Attribute_Key_Description")
    @SerializedName("ReportRequestAttributeKeyDescription")
    protected String reportRequestAttributeKeyDescription;

    /**
     * Gets the value of the reportRequestAttribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reportRequestAttribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReportRequestAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReportRequestAttribute }
     * 
     * 
     */
    public List<ReportRequestAttribute> getReportRequestAttribute() {
        if (reportRequestAttribute == null) {
            reportRequestAttribute = new ArrayList<ReportRequestAttribute>();
        }
        return this.reportRequestAttribute;
    }

    /**
     * Gets the value of the reportRequestAttributeLookupSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getReportRequestAttributeLookupSK() {
        return reportRequestAttributeLookupSK;
    }

    /**
     * Sets the value of the reportRequestAttributeLookupSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setReportRequestAttributeLookupSK(BigInteger value) {
        this.reportRequestAttributeLookupSK = value;
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
     * Gets the value of the reportRequestAttributeKeyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportRequestAttributeKeyName() {
        return reportRequestAttributeKeyName;
    }

    /**
     * Sets the value of the reportRequestAttributeKeyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportRequestAttributeKeyName(String value) {
        this.reportRequestAttributeKeyName = value;
    }

    /**
     * Gets the value of the reportRequestAttributeKeyDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportRequestAttributeKeyDescription() {
        return reportRequestAttributeKeyDescription;
    }

    /**
     * Sets the value of the reportRequestAttributeKeyDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportRequestAttributeKeyDescription(String value) {
        this.reportRequestAttributeKeyDescription = value;
    }

}