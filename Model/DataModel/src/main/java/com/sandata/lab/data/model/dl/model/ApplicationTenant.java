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
 * Identification of the tenancy within the system.
 * 
 * Entity Purpose: Identifies tenants within the system and/or application.
 * 
 * 
 * 
 * <p>Java class for Application_Tenant complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Application_Tenant">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Application_User_Role" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Base_Application_Function" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Application_Secure_Element" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Application_Data_Content" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Application_Privilege" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Application_Role" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Application_Tenant_Key_Configuration" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Application_Tenant_Business_Entity_Crosswalk" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Application_Tenant_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Tenant_Name" type="{}Name_Generic" />
 *       &lt;attribute name="Tenant_Type_Name" type="{}Type_Name" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Application_Tenant", propOrder = {
    "applicationUserRole",
    "baseApplicationFunction",
    "applicationSecureElement",
    "applicationDataContent",
    "applicationPrivilege",
    "applicationRole",
    "applicationTenantKeyConfiguration",
    "applicationTenantBusinessEntityCrosswalk"
})
@OracleMetadata(
        packageName = "PKG_APP",
        insertMethod = "insertAppTenant",
        updateMethod = "updateAppTenant",
        deleteMethod = "deleteAppTenant",
        getMethod = "getAppTenant",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.AppTenantT",
        primaryKeys = {})
public class ApplicationTenant extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlElement(name = "Application_User_Role")
    @SerializedName("ApplicationUserRole")
    protected List<ApplicationUserRole> applicationUserRole;
    @XmlElement(name = "Base_Application_Function")
    @SerializedName("BaseApplicationFunction")
    protected List<BaseApplicationFunction> baseApplicationFunction;
    @XmlElement(name = "Application_Secure_Element")
    @SerializedName("ApplicationSecureElement")
    protected List<ApplicationSecureElement> applicationSecureElement;
    @XmlElement(name = "Application_Data_Content")
    @SerializedName("ApplicationDataContent")
    protected List<ApplicationDataContent> applicationDataContent;
    @XmlElement(name = "Application_Privilege")
    @SerializedName("ApplicationPrivilege")
    protected List<ApplicationPrivilege> applicationPrivilege;
    @XmlElement(name = "Application_Role")
    @SerializedName("ApplicationRole")
    protected List<ApplicationRole> applicationRole;
    @XmlElement(name = "Application_Tenant_Key_Configuration")
    @SerializedName("ApplicationTenantKeyConfiguration")
    protected List<ApplicationTenantKeyConfiguration> applicationTenantKeyConfiguration;
    @XmlElement(name = "Application_Tenant_Business_Entity_Crosswalk")
    @SerializedName("ApplicationTenantBusinessEntityCrosswalk")
    protected List<ApplicationTenantBusinessEntityCrosswalk> applicationTenantBusinessEntityCrosswalk;
    @XmlAttribute(name = "Application_Tenant_SK", required = true)
    @SerializedName("ApplicationTenantSK")
	@Mapping(getter = "getAppTenantSk", setter = "setAppTenantSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger applicationTenantSK;
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
    @XmlAttribute(name = "Tenant_Name")
    @SerializedName("TenantName")
	@Mapping(getter = "getTenantName", setter = "setTenantName", type = "String", index = 3)
    protected String tenantName;
    @XmlAttribute(name = "Tenant_Type_Name")
    @SerializedName("TenantTypeName")
	@Mapping(getter = "getTenantTypName", setter = "setTenantTypName", type = "String", index = 4)
    protected String tenantTypeName;

    /**
     * Gets the value of the applicationUserRole property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationUserRole property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationUserRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicationUserRole }
     * 
     * 
     */
    public List<ApplicationUserRole> getApplicationUserRole() {
        if (applicationUserRole == null) {
            applicationUserRole = new ArrayList<ApplicationUserRole>();
        }
        return this.applicationUserRole;
    }

    /**
     * Gets the value of the baseApplicationFunction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the baseApplicationFunction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBaseApplicationFunction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BaseApplicationFunction }
     * 
     * 
     */
    public List<BaseApplicationFunction> getBaseApplicationFunction() {
        if (baseApplicationFunction == null) {
            baseApplicationFunction = new ArrayList<BaseApplicationFunction>();
        }
        return this.baseApplicationFunction;
    }

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
     * Gets the value of the applicationDataContent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationDataContent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationDataContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicationDataContent }
     * 
     * 
     */
    public List<ApplicationDataContent> getApplicationDataContent() {
        if (applicationDataContent == null) {
            applicationDataContent = new ArrayList<ApplicationDataContent>();
        }
        return this.applicationDataContent;
    }

    /**
     * Gets the value of the applicationPrivilege property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationPrivilege property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationPrivilege().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicationPrivilege }
     * 
     * 
     */
    public List<ApplicationPrivilege> getApplicationPrivilege() {
        if (applicationPrivilege == null) {
            applicationPrivilege = new ArrayList<ApplicationPrivilege>();
        }
        return this.applicationPrivilege;
    }

    /**
     * Gets the value of the applicationRole property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationRole property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicationRole }
     * 
     * 
     */
    public List<ApplicationRole> getApplicationRole() {
        if (applicationRole == null) {
            applicationRole = new ArrayList<ApplicationRole>();
        }
        return this.applicationRole;
    }

    /**
     * Gets the value of the applicationTenantKeyConfiguration property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationTenantKeyConfiguration property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationTenantKeyConfiguration().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicationTenantKeyConfiguration }
     * 
     * 
     */
    public List<ApplicationTenantKeyConfiguration> getApplicationTenantKeyConfiguration() {
        if (applicationTenantKeyConfiguration == null) {
            applicationTenantKeyConfiguration = new ArrayList<ApplicationTenantKeyConfiguration>();
        }
        return this.applicationTenantKeyConfiguration;
    }

    /**
     * Gets the value of the applicationTenantBusinessEntityCrosswalk property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationTenantBusinessEntityCrosswalk property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationTenantBusinessEntityCrosswalk().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicationTenantBusinessEntityCrosswalk }
     * 
     * 
     */
    public List<ApplicationTenantBusinessEntityCrosswalk> getApplicationTenantBusinessEntityCrosswalk() {
        if (applicationTenantBusinessEntityCrosswalk == null) {
            applicationTenantBusinessEntityCrosswalk = new ArrayList<ApplicationTenantBusinessEntityCrosswalk>();
        }
        return this.applicationTenantBusinessEntityCrosswalk;
    }

    /**
     * Gets the value of the applicationTenantSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationTenantSK() {
        return applicationTenantSK;
    }

    /**
     * Sets the value of the applicationTenantSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationTenantSK(BigInteger value) {
        this.applicationTenantSK = value;
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
     * Gets the value of the tenantName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTenantName() {
        return tenantName;
    }

    /**
     * Sets the value of the tenantName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTenantName(String value) {
        this.tenantName = value;
    }

    /**
     * Gets the value of the tenantTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTenantTypeName() {
        return tenantTypeName;
    }

    /**
     * Sets the value of the tenantTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTenantTypeName(String value) {
        this.tenantTypeName = value;
    }

}