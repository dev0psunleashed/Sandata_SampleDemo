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
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Identification of functionality to the system
 * 
 * Entity Purpose: The Application Function entity registers functionality within the application. This is intended to be aggregate to a process, page, or other level of functionality that can be decomposed into Functional Elements
 * 
 * The entity stores historical data of the main/operationl entity.
 * 
 * <p>Java class for Application_Function_History complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Application_Function_History">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Application_Function_History_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Change_Timestamp" use="required" type="{}Record_Change_Timestamp" />
 *       &lt;attribute name="Record_Change_Code" use="required" type="{}Record_Change_Code" />
 *       &lt;attribute name="Application_Function_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Application_Module_SK" type="{}Surrogate_Key" />
 *       &lt;attribute name="Function_Name" type="{}Name_Generic" />
 *       &lt;attribute name="Function_Type_Name" type="{}Type_Name" />
 *       &lt;attribute name="Function_Area" type="{}Name_Generic" />
 *       &lt;attribute name="Function_Scope" type="{}Name_Generic" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Application_Function_History")
@OracleMetadata(
        packageName = "PKG_APP",
        insertMethod = "insertAppFunHist",
        updateMethod = "updateAppFunHist",
        deleteMethod = "deleteAppFunHist",
        getMethod = "getAppFunHist",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.AppFunHistT",
        primaryKeys = {})
public class ApplicationFunctionHistory extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Application_Function_History_SK", required = true)
    @SerializedName("ApplicationFunctionHistorySK")
	@Mapping(getter = "getAppFunHistSk", setter = "setAppFunHistSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger applicationFunctionHistorySK;
    @XmlAttribute(name = "Record_Change_Timestamp", required = true)
    @SerializedName("RecordChangeTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecChangeTmstp", setter = "setRecChangeTmstp", type = "java.sql.Timestamp", index = 1)
    protected Date recordChangeTimestamp;
    @XmlAttribute(name = "Record_Change_Code", required = true)
    @SerializedName("RecordChangeCode")
	@Mapping(getter = "getRecChangeCode", setter = "setRecChangeCode", type = "String", index = 2)
    protected RecordChangeCode recordChangeCode;
    @XmlAttribute(name = "Application_Function_SK", required = true)
    @SerializedName("ApplicationFunctionSK")
	@Mapping(getter = "getAppFunSk", setter = "setAppFunSk", type = "java.math.BigDecimal", index = 3)
    protected BigInteger applicationFunctionSK;
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
    @XmlAttribute(name = "Application_Module_SK")
    @SerializedName("ApplicationModuleSK")
	@Mapping(getter = "getAppModSk", setter = "setAppModSk", type = "java.math.BigDecimal", index = 6)
    protected BigInteger applicationModuleSK;
    @XmlAttribute(name = "Function_Name")
    @SerializedName("FunctionName")
	@Mapping(getter = "getFunName", setter = "setFunName", type = "String", index = 7)
    protected String functionName;
    @XmlAttribute(name = "Function_Type_Name")
    @SerializedName("FunctionTypeName")
	@Mapping(getter = "getFunTypName", setter = "setFunTypName", type = "String", index = 8)
    protected String functionTypeName;
    @XmlAttribute(name = "Function_Area")
    @SerializedName("FunctionArea")
	@Mapping(getter = "getFunArea", setter = "setFunArea", type = "String", index = 9)
    protected String functionArea;
    @XmlAttribute(name = "Function_Scope")
    @SerializedName("FunctionScope")
	@Mapping(getter = "getFunScope", setter = "setFunScope", type = "String", index = 10)
    protected String functionScope;

    /**
     * Gets the value of the applicationFunctionHistorySK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationFunctionHistorySK() {
        return applicationFunctionHistorySK;
    }

    /**
     * Sets the value of the applicationFunctionHistorySK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationFunctionHistorySK(BigInteger value) {
        this.applicationFunctionHistorySK = value;
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
     * Gets the value of the applicationFunctionSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationFunctionSK() {
        return applicationFunctionSK;
    }

    /**
     * Sets the value of the applicationFunctionSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationFunctionSK(BigInteger value) {
        this.applicationFunctionSK = value;
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
     * Gets the value of the applicationModuleSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationModuleSK() {
        return applicationModuleSK;
    }

    /**
     * Sets the value of the applicationModuleSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationModuleSK(BigInteger value) {
        this.applicationModuleSK = value;
    }

    /**
     * Gets the value of the functionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * Sets the value of the functionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctionName(String value) {
        this.functionName = value;
    }

    /**
     * Gets the value of the functionTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctionTypeName() {
        return functionTypeName;
    }

    /**
     * Sets the value of the functionTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctionTypeName(String value) {
        this.functionTypeName = value;
    }

    /**
     * Gets the value of the functionArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctionArea() {
        return functionArea;
    }

    /**
     * Sets the value of the functionArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctionArea(String value) {
        this.functionArea = value;
    }

    /**
     * Gets the value of the functionScope property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctionScope() {
        return functionScope;
    }

    /**
     * Sets the value of the functionScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctionScope(String value) {
        this.functionScope = value;
    }

}
