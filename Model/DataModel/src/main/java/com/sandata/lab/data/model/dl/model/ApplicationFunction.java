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
 * Identification of functionality to the system
 * 
 * Entity Purpose: The Application Function entity registers functionality within the application. This is intended to be aggregate to a process, page, or other level of functionality that can be decomposed into Functional Elements
 * 
 * 
 * <p>Java class for Application_Function complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Application_Function">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Application_Secure_Element" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Application_Function_Element" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
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
@XmlType(name = "Application_Function", propOrder = {
    "applicationSecureElement",
    "applicationFunctionElement"
})
@OracleMetadata(
        packageName = "PKG_APP",
        insertMethod = "insertAppFun",
        updateMethod = "updateAppFun",
        deleteMethod = "deleteAppFun",
        getMethod = "getAppFun",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.AppFunT",
        primaryKeys = {})
public class ApplicationFunction extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlElement(name = "Application_Secure_Element")
    @SerializedName("ApplicationSecureElement")
    protected List<ApplicationSecureElement> applicationSecureElement;
    @XmlElement(name = "Application_Function_Element")
    @SerializedName("ApplicationFunctionElement")
    protected List<ApplicationFunctionElement> applicationFunctionElement;
    @XmlAttribute(name = "Application_Function_SK", required = true)
    @SerializedName("ApplicationFunctionSK")
	@Mapping(getter = "getAppFunSk", setter = "setAppFunSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger applicationFunctionSK;
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
    @XmlAttribute(name = "Application_Module_SK")
    @SerializedName("ApplicationModuleSK")
	@Mapping(getter = "getAppModSk", setter = "setAppModSk", type = "java.math.BigDecimal", index = 3)
    protected BigInteger applicationModuleSK;
    @XmlAttribute(name = "Function_Name")
    @SerializedName("FunctionName")
	@Mapping(getter = "getFunName", setter = "setFunName", type = "String", index = 4)
    protected String functionName;
    @XmlAttribute(name = "Function_Type_Name")
    @SerializedName("FunctionTypeName")
	@Mapping(getter = "getFunTypName", setter = "setFunTypName", type = "String", index = 5)
    protected String functionTypeName;
    @XmlAttribute(name = "Function_Area")
    @SerializedName("FunctionArea")
	@Mapping(getter = "getFunArea", setter = "setFunArea", type = "String", index = 6)
    protected String functionArea;
    @XmlAttribute(name = "Function_Scope")
    @SerializedName("FunctionScope")
	@Mapping(getter = "getFunScope", setter = "setFunScope", type = "String", index = 7)
    protected String functionScope;

    /**
     * Gets the value of the applicationSecureElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationSecureElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationSecureElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicationSecureElement }
     * 
     * 
     */
    public List<ApplicationSecureElement> getApplicationSecureElement() {
        if (applicationSecureElement == null) {
            applicationSecureElement = new ArrayList<ApplicationSecureElement>();
        }
        return this.applicationSecureElement;
    }

    /**
     * Gets the value of the applicationFunctionElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationFunctionElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationFunctionElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicationFunctionElement }
     * 
     * 
     */
    public List<ApplicationFunctionElement> getApplicationFunctionElement() {
        if (applicationFunctionElement == null) {
            applicationFunctionElement = new ArrayList<ApplicationFunctionElement>();
        }
        return this.applicationFunctionElement;
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