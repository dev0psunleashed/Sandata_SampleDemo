//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 10:25:43 PM EDT 
//


package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.Adapter1;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigInteger;
import java.util.Date;


/**
 * Identification of data structures within the application
 * 
 * Entity Purpose: The Data Structure entity registers data objects within the application for the purposes of allowing schema objects to be exposed as Application Functional Element(s) and mapped to Application Function(s). This allows exposure of tables, views, stored procedures, and packages within the metadata model.
 * 
 * The entity stores historical data of the main/operationl entity.
 * 
 * <p>Java class for Application_Data_Structure_History complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Application_Data_Structure_History">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Application_Data_Structure_History_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Change_Timestamp" use="required" type="{}Record_Change_Timestamp" />
 *       &lt;attribute name="Record_Change_Code" use="required" type="{}Record_Change_Code" />
 *       &lt;attribute name="Application_Data_Structure_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Application_Data_Structure_Parent_SK" type="{}Surrogate_Key" />
 *       &lt;attribute name="Application_Secure_Group_SK" type="{}Surrogate_Key" />
 *       &lt;attribute name="Logical_Element_Type_Name" type="{}Element_Type_Name" />
 *       &lt;attribute name="Element_Type" type="{}Type_Name" />
 *       &lt;attribute name="Logical_Element_Name" type="{}Name_Generic" />
 *       &lt;attribute name="Physical_Element_Type_Name" type="{}Element_Type_Name" />
 *       &lt;attribute name="Physical_Element_Name" type="{}Name_Generic" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Application_Data_Structure_History")
@OracleMetadata(
        packageName = "PKG_APP",
        insertMethod = "insertAppDataStrucHist",
        updateMethod = "updateAppDataStrucHist",
        deleteMethod = "deleteAppDataStrucHist",
        getMethod = "getAppDataStrucHist",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.AppDataStrucHistT",
        primaryKeys = {})
public class ApplicationDataStructureHistory extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Application_Data_Structure_History_SK", required = true)
    @SerializedName("ApplicationDataStructureHistorySK")
	@Mapping(getter = "getAppDataStrucHistSk", setter = "setAppDataStrucHistSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger applicationDataStructureHistorySK;
    @XmlAttribute(name = "Record_Change_Timestamp", required = true)
    @SerializedName("RecordChangeTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecChangeTmstp", setter = "setRecChangeTmstp", type = "java.sql.Timestamp", index = 1)
    protected Date recordChangeTimestamp;
    @XmlAttribute(name = "Record_Change_Code", required = true)
    @SerializedName("RecordChangeCode")
	@Mapping(getter = "getRecChangeCode", setter = "setRecChangeCode", type = "String", index = 2)
    protected RecordChangeCode recordChangeCode;
    @XmlAttribute(name = "Application_Data_Structure_SK", required = true)
    @SerializedName("ApplicationDataStructureSK")
	@Mapping(getter = "getAppDataStrucSk", setter = "setAppDataStrucSk", type = "java.math.BigDecimal", index = 3)
    protected BigInteger applicationDataStructureSK;
    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = 4)
    protected Date recordCreateTimestamp;
    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(Adapter1.class)
	@Mapping(getter = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = 5)
    protected Date recordUpdateTimestamp;
    @XmlAttribute(name = "Application_Data_Structure_Parent_SK")
    @SerializedName("ApplicationDataStructureParentSK")
	@Mapping(getter = "getAppDataStrucParSk", setter = "setAppDataStrucParSk", type = "java.math.BigDecimal", index = 6)
    protected BigInteger applicationDataStructureParentSK;
    @XmlAttribute(name = "Application_Secure_Group_SK")
    @SerializedName("ApplicationSecureGroupSK")
	@Mapping(getter = "getAppSecrGrpSk", setter = "setAppSecrGrpSk", type = "java.math.BigDecimal", index = 7)
    protected BigInteger applicationSecureGroupSK;
    @XmlAttribute(name = "Logical_Element_Type_Name")
    @SerializedName("LogicalElementTypeName")
	@Mapping(getter = "getLgclEltTypName", setter = "setLgclEltTypName", type = "String", index = 8)
    protected ElementTypeName logicalElementTypeName;
    @XmlAttribute(name = "Logical_Element_Name")
    @SerializedName("LogicalElementName")
	@Mapping(getter = "getLgclEltName", setter = "setLgclEltName", type = "String", index = 9)
    protected String logicalElementName;
    @XmlAttribute(name = "Physical_Element_Type_Name")
    @SerializedName("PhysicalElementTypeName")
	@Mapping(getter = "getPhysEltTypName", setter = "setPhysEltTypName", type = "String", index = 10)
    protected ElementTypeName physicalElementTypeName;
    @XmlAttribute(name = "Physical_Element_Name")
    @SerializedName("PhysicalElementName")
	@Mapping(getter = "getPhysEltName", setter = "setPhysEltName", type = "String", index = 11)
    protected String physicalElementName;

    /**
     * Gets the value of the applicationDataStructureHistorySK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationDataStructureHistorySK() {
        return applicationDataStructureHistorySK;
    }

    /**
     * Sets the value of the applicationDataStructureHistorySK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationDataStructureHistorySK(BigInteger value) {
        this.applicationDataStructureHistorySK = value;
    }

    /**
     * Gets the value of the recordChangeTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordChangeTimestamp() {
        return recordChangeTimestamp;
    }

    /**
     * Sets the value of the recordChangeTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordChangeTimestamp(Date value) {
        this.recordChangeTimestamp = value;
    }

    /**
     * Gets the value of the recordChangeCode property.
     * 
     * @return
     *     possible object is
     *     {@link RecordChangeCode }
     *     
     */
    public RecordChangeCode getRecordChangeCode() {
        return recordChangeCode;
    }

    /**
     * Sets the value of the recordChangeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordChangeCode }
     *     
     */
    public void setRecordChangeCode(RecordChangeCode value) {
        this.recordChangeCode = value;
    }

    /**
     * Gets the value of the applicationDataStructureSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationDataStructureSK() {
        return applicationDataStructureSK;
    }

    /**
     * Sets the value of the applicationDataStructureSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationDataStructureSK(BigInteger value) {
        this.applicationDataStructureSK = value;
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
     * Gets the value of the applicationDataStructureParentSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationDataStructureParentSK() {
        return applicationDataStructureParentSK;
    }

    /**
     * Sets the value of the applicationDataStructureParentSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationDataStructureParentSK(BigInteger value) {
        this.applicationDataStructureParentSK = value;
    }

    /**
     * Gets the value of the applicationSecureGroupSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationSecureGroupSK() {
        return applicationSecureGroupSK;
    }

    /**
     * Sets the value of the applicationSecureGroupSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationSecureGroupSK(BigInteger value) {
        this.applicationSecureGroupSK = value;
    }

    /**
     * Gets the value of the logicalElementTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link ElementTypeName }
     *     
     */
    public ElementTypeName getLogicalElementTypeName() {
        return logicalElementTypeName;
    }

    /**
     * Sets the value of the logicalElementTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ElementTypeName }
     *     
     */
    public void setLogicalElementTypeName(ElementTypeName value) {
        this.logicalElementTypeName = value;
    }

    /**
     * Gets the value of the logicalElementName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogicalElementName() {
        return logicalElementName;
    }

    /**
     * Sets the value of the logicalElementName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogicalElementName(String value) {
        this.logicalElementName = value;
    }

    /**
     * Gets the value of the physicalElementTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link ElementTypeName }
     *     
     */
    public ElementTypeName getPhysicalElementTypeName() {
        return physicalElementTypeName;
    }

    /**
     * Sets the value of the physicalElementTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ElementTypeName }
     *     
     */
    public void setPhysicalElementTypeName(ElementTypeName value) {
        this.physicalElementTypeName = value;
    }

    /**
     * Gets the value of the physicalElementName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalElementName() {
        return physicalElementName;
    }

    /**
     * Sets the value of the physicalElementName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalElementName(String value) {
        this.physicalElementName = value;
    }

}
