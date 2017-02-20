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
 * Entity Purpose: Identifies Application Privilege(s) mapped between Application Role(s) and Application Secure Element(s). The privilege may be a grant, denial, or exclusion from an otherwise defined list
 * 
 * The entity stores historical data of the main/operationl entity.
 * 
 * 
 * <p>Java class for Application_Privilege_History complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Application_Privilege_History">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Application_Privilege_History_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Change_Timestamp" use="required" type="{}Record_Change_Timestamp" />
 *       &lt;attribute name="Record_Change_Code" use="required" type="{}Record_Change_Code" />
 *       &lt;attribute name="Application_Privilege_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Application_Secure_Element_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Application_Tenant_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Application_User_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Application_Secure_Group_SK" type="{}Surrogate_Key" />
 *       &lt;attribute name="Include_Inheritance_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Privilege_Access_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Privilege_No_Access_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Privilege_Create_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Privilege_No_Create_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Privilege_Update_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Privilege_No_Update_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Privilege_Delete_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Privilege_No_Delete_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Exclusion_Indicator" type="{}Indicator" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Application_Privilege_History")
@OracleMetadata(
        packageName = "PKG_APP",
        insertMethod = "insertAppPrivHist",
        updateMethod = "updateAppPrivHist",
        deleteMethod = "deleteAppPrivHist",
        getMethod = "getAppPrivHist",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.AppPrivHistT",
        primaryKeys = {})
public class ApplicationPrivilegeHistory extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Application_Privilege_History_SK", required = true)
    @SerializedName("ApplicationPrivilegeHistorySK")
	@Mapping(getter = "getAppPrivHistSk", setter = "setAppPrivHistSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger applicationPrivilegeHistorySK;
    @XmlAttribute(name = "Record_Change_Timestamp", required = true)
    @SerializedName("RecordChangeTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecChangeTmstp", setter = "setRecChangeTmstp", type = "java.sql.Timestamp", index = 1)
    protected Date recordChangeTimestamp;
    @XmlAttribute(name = "Record_Change_Code", required = true)
    @SerializedName("RecordChangeCode")
	@Mapping(getter = "getRecChangeCode", setter = "setRecChangeCode", type = "String", index = 2)
    protected RecordChangeCode recordChangeCode;
    @XmlAttribute(name = "Application_Privilege_SK", required = true)
    @SerializedName("ApplicationPrivilegeSK")
	@Mapping(getter = "getAppPrivSk", setter = "setAppPrivSk", type = "java.math.BigDecimal", index = 3)
    protected BigInteger applicationPrivilegeSK;
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
    @XmlAttribute(name = "Application_Secure_Element_SK", required = true)
    @SerializedName("ApplicationSecureElementSK")
	@Mapping(getter = "getAppSecrEltSk", setter = "setAppSecrEltSk", type = "java.math.BigDecimal", index = 6)
    protected BigInteger applicationSecureElementSK;
    @XmlAttribute(name = "Application_Tenant_SK", required = true)
    @SerializedName("ApplicationTenantSK")
	@Mapping(getter = "getAppTenantSk", setter = "setAppTenantSk", type = "java.math.BigDecimal", index = 7)
    protected BigInteger applicationTenantSK;
    @XmlAttribute(name = "Application_User_SK", required = true)
    @SerializedName("ApplicationUserSK")
	@Mapping(getter = "getAppUserSk", setter = "setAppUserSk", type = "java.math.BigDecimal", index = 8)
    protected BigInteger applicationUserSK;
    @XmlAttribute(name = "Application_Secure_Group_SK")
    @SerializedName("ApplicationSecureGroupSK")
	@Mapping(getter = "getAppSecrGrpSk", setter = "setAppSecrGrpSk", type = "java.math.BigDecimal", index = 9)
    protected BigInteger applicationSecureGroupSK;
    @XmlAttribute(name = "Include_Inheritance_Indicator")
    @SerializedName("IncludeInheritanceIndicator")
	@Mapping(getter = "getInclInhcInd", setter = "setInclInhcInd", type = "java.math.BigDecimal", index = 10)
    protected Boolean includeInheritanceIndicator;
    @XmlAttribute(name = "Privilege_Access_Indicator")
    @SerializedName("PrivilegeAccessIndicator")
	@Mapping(getter = "getPrivAccessInd", setter = "setPrivAccessInd", type = "java.math.BigDecimal", index = 11)
    protected Boolean privilegeAccessIndicator;
    @XmlAttribute(name = "Privilege_No_Access_Indicator")
    @SerializedName("PrivilegeNoAccessIndicator")
	@Mapping(getter = "getPrivNoAccessInd", setter = "setPrivNoAccessInd", type = "java.math.BigDecimal", index = 12)
    protected Boolean privilegeNoAccessIndicator;
    @XmlAttribute(name = "Privilege_Create_Indicator")
    @SerializedName("PrivilegeCreateIndicator")
	@Mapping(getter = "getPrivCreateInd", setter = "setPrivCreateInd", type = "java.math.BigDecimal", index = 13)
    protected Boolean privilegeCreateIndicator;
    @XmlAttribute(name = "Privilege_No_Create_Indicator")
    @SerializedName("PrivilegeNoCreateIndicator")
	@Mapping(getter = "getPrivNoCreateInd", setter = "setPrivNoCreateInd", type = "java.math.BigDecimal", index = 14)
    protected Boolean privilegeNoCreateIndicator;
    @XmlAttribute(name = "Privilege_Update_Indicator")
    @SerializedName("PrivilegeUpdateIndicator")
	@Mapping(getter = "getPrivUpdateInd", setter = "setPrivUpdateInd", type = "java.math.BigDecimal", index = 15)
    protected Boolean privilegeUpdateIndicator;
    @XmlAttribute(name = "Privilege_No_Update_Indicator")
    @SerializedName("PrivilegeNoUpdateIndicator")
	@Mapping(getter = "getPrivNoUpdateInd", setter = "setPrivNoUpdateInd", type = "java.math.BigDecimal", index = 16)
    protected Boolean privilegeNoUpdateIndicator;
    @XmlAttribute(name = "Privilege_Delete_Indicator")
    @SerializedName("PrivilegeDeleteIndicator")
	@Mapping(getter = "getPrivDeleteInd", setter = "setPrivDeleteInd", type = "java.math.BigDecimal", index = 17)
    protected Boolean privilegeDeleteIndicator;
    @XmlAttribute(name = "Privilege_No_Delete_Indicator")
    @SerializedName("PrivilegeNoDeleteIndicator")
	@Mapping(getter = "getPrivNoDeleteInd", setter = "setPrivNoDeleteInd", type = "java.math.BigDecimal", index = 18)
    protected Boolean privilegeNoDeleteIndicator;
    @XmlAttribute(name = "Exclusion_Indicator")
    @SerializedName("ExclusionIndicator")
	@Mapping(getter = "getExclInd", setter = "setExclInd", type = "java.math.BigDecimal", index = 19)
    protected Boolean exclusionIndicator;

    /**
     * Gets the value of the applicationPrivilegeHistorySK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationPrivilegeHistorySK() {
        return applicationPrivilegeHistorySK;
    }

    /**
     * Sets the value of the applicationPrivilegeHistorySK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationPrivilegeHistorySK(BigInteger value) {
        this.applicationPrivilegeHistorySK = value;
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
     * Gets the value of the applicationPrivilegeSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationPrivilegeSK() {
        return applicationPrivilegeSK;
    }

    /**
     * Sets the value of the applicationPrivilegeSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationPrivilegeSK(BigInteger value) {
        this.applicationPrivilegeSK = value;
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
     * Gets the value of the applicationSecureElementSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationSecureElementSK() {
        return applicationSecureElementSK;
    }

    /**
     * Sets the value of the applicationSecureElementSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationSecureElementSK(BigInteger value) {
        this.applicationSecureElementSK = value;
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
     * Gets the value of the applicationUserSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationUserSK() {
        return applicationUserSK;
    }

    /**
     * Sets the value of the applicationUserSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationUserSK(BigInteger value) {
        this.applicationUserSK = value;
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
     * Gets the value of the includeInheritanceIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeInheritanceIndicator() {
        return includeInheritanceIndicator;
    }

    /**
     * Sets the value of the includeInheritanceIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeInheritanceIndicator(Boolean value) {
        this.includeInheritanceIndicator = value;
    }

    /**
     * Gets the value of the privilegeAccessIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrivilegeAccessIndicator() {
        return privilegeAccessIndicator;
    }

    /**
     * Sets the value of the privilegeAccessIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrivilegeAccessIndicator(Boolean value) {
        this.privilegeAccessIndicator = value;
    }

    /**
     * Gets the value of the privilegeNoAccessIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrivilegeNoAccessIndicator() {
        return privilegeNoAccessIndicator;
    }

    /**
     * Sets the value of the privilegeNoAccessIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrivilegeNoAccessIndicator(Boolean value) {
        this.privilegeNoAccessIndicator = value;
    }

    /**
     * Gets the value of the privilegeCreateIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrivilegeCreateIndicator() {
        return privilegeCreateIndicator;
    }

    /**
     * Sets the value of the privilegeCreateIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrivilegeCreateIndicator(Boolean value) {
        this.privilegeCreateIndicator = value;
    }

    /**
     * Gets the value of the privilegeNoCreateIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrivilegeNoCreateIndicator() {
        return privilegeNoCreateIndicator;
    }

    /**
     * Sets the value of the privilegeNoCreateIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrivilegeNoCreateIndicator(Boolean value) {
        this.privilegeNoCreateIndicator = value;
    }

    /**
     * Gets the value of the privilegeUpdateIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrivilegeUpdateIndicator() {
        return privilegeUpdateIndicator;
    }

    /**
     * Sets the value of the privilegeUpdateIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrivilegeUpdateIndicator(Boolean value) {
        this.privilegeUpdateIndicator = value;
    }

    /**
     * Gets the value of the privilegeNoUpdateIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrivilegeNoUpdateIndicator() {
        return privilegeNoUpdateIndicator;
    }

    /**
     * Sets the value of the privilegeNoUpdateIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrivilegeNoUpdateIndicator(Boolean value) {
        this.privilegeNoUpdateIndicator = value;
    }

    /**
     * Gets the value of the privilegeDeleteIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrivilegeDeleteIndicator() {
        return privilegeDeleteIndicator;
    }

    /**
     * Sets the value of the privilegeDeleteIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrivilegeDeleteIndicator(Boolean value) {
        this.privilegeDeleteIndicator = value;
    }

    /**
     * Gets the value of the privilegeNoDeleteIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrivilegeNoDeleteIndicator() {
        return privilegeNoDeleteIndicator;
    }

    /**
     * Sets the value of the privilegeNoDeleteIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrivilegeNoDeleteIndicator(Boolean value) {
        this.privilegeNoDeleteIndicator = value;
    }

    /**
     * Gets the value of the exclusionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExclusionIndicator() {
        return exclusionIndicator;
    }

    /**
     * Sets the value of the exclusionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExclusionIndicator(Boolean value) {
        this.exclusionIndicator = value;
    }

}
