//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.27 at 10:53:27 PM EST 
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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


/**
 * Stores information on the various pay rates that are associated with a Staff member
 * 
 * <p>Java class for Staff_Associated_Rate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Staff_Associated_Rate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Staff_Associated_Rate_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Record_Effective_Timestamp" use="required" type="{}Record_Effective_Timestamp" />
 *       &lt;attribute name="Record_Termination_Timestamp" use="required" type="{}Record_Termination_Timestamp" />
 *       &lt;attribute name="Record_Created_By" type="{}Record_Created_By" />
 *       &lt;attribute name="Record_Updated_By" type="{}Record_Updated_By" />
 *       &lt;attribute name="Change_Reason_Memo" type="{}Change_Reason_Memo" />
 *       &lt;attribute name="Current_Record_Indicator" use="required" type="{}Current_Record_Indicator" />
 *       &lt;attribute name="Change_Version_ID" use="required" type="{}Change_Version_ID" />
 *       &lt;attribute name="Business_Entity_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Staff_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Patient_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Staff_Associated_Rate_Effective_Date" use="required" type="{}Attribute_Effective_Date" />
 *       &lt;attribute name="Staff_Associated_Rate_Termination_Date" use="required" type="{}Attribute_Termination_Date" />
 *       &lt;attribute name="Service_Name" use="required" type="{}Service_Name" />
 *       &lt;attribute name="Rate_Sub_Type_Name" use="required" type="{}Rate_Sub_Type_Name" />
 *       &lt;attribute name="Rate_Type_Name" use="required" type="{}Rate_Type_Name" />
 *       &lt;attribute name="Rate_Qualifier_Code" use="required" type="{}Rate_Qualifier_Code" />
 *       &lt;attribute name="Rate_Change_Qualifier" use="required" type="{}Rate_Change_Qualifier" />
 *       &lt;attribute name="Staff_Associated_Rate_Amount" use="required" type="{}Money" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Staff_Associated_Rate")
@OracleMetadata(
        packageName = "PKG_STAFF",
        insertMethod = "insertStaffAssocRate",
        updateMethod = "updateStaffAssocRate",
        deleteMethod = "deleteStaffAssocRate",
        getMethod = "getStaffAssocRate",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.StaffAssocRateT",
        primaryKeys = {})
public class StaffAssociatedRate extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Staff_Associated_Rate_SK", required = true)
    @SerializedName("StaffAssociatedRateSK")
	@Mapping(getter = "getStaffAssocRateSk", setter = "setStaffAssocRateSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger staffAssociatedRateSK;
    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = 1)
    protected Date recordCreateTimestamp;
    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = 2)
    protected Date recordUpdateTimestamp;
    @XmlAttribute(name = "Record_Effective_Timestamp", required = true)
    @SerializedName("RecordEffectiveTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecEffTmstp", setter = "setRecEffTmstp", type = "java.sql.Timestamp", index = 3)
    protected Date recordEffectiveTimestamp;
    @XmlAttribute(name = "Record_Termination_Timestamp", required = true)
    @SerializedName("RecordTerminationTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecTermTmstp", setter = "setRecTermTmstp", type = "java.sql.Timestamp", index = 4)
    protected Date recordTerminationTimestamp;
    @XmlAttribute(name = "Record_Created_By")
    @SerializedName("RecordCreatedBy")
	@Mapping(getter = "getRecCreatedBy", setter = "setRecCreatedBy", type = "String", index = 5)
    protected String recordCreatedBy;
    @XmlAttribute(name = "Record_Updated_By")
    @SerializedName("RecordUpdatedBy")
	@Mapping(getter = "getRecUpdatedBy", setter = "setRecUpdatedBy", type = "String", index = 6)
    protected String recordUpdatedBy;
    @XmlAttribute(name = "Change_Reason_Memo")
    @SerializedName("ChangeReasonMemo")
	@Mapping(getter = "getChangeReasonMemo", setter = "setChangeReasonMemo", type = "String", index = 7)
    protected String changeReasonMemo;
    @XmlAttribute(name = "Current_Record_Indicator", required = true)
    @SerializedName("CurrentRecordIndicator")
	@Mapping(getter = "getCurrRecInd", setter = "setCurrRecInd", type = "java.math.BigDecimal", index = 8)
    protected boolean currentRecordIndicator;
    @XmlAttribute(name = "Change_Version_ID", required = true)
    @SerializedName("ChangeVersionID")
	@Mapping(getter = "getChangeVersionId", setter = "setChangeVersionId", type = "java.math.BigDecimal", index = 9)
    protected BigInteger changeVersionID;
    @XmlAttribute(name = "Business_Entity_ID", required = true)
    @SerializedName("BusinessEntityID")
	@Mapping(getter = "getBeId", setter = "setBeId", type = "String", index = 10)
    protected String businessEntityID;
    @XmlAttribute(name = "Staff_ID", required = true)
    @SerializedName("StaffID")
	@Mapping(getter = "getStaffId", setter = "setStaffId", type = "String", index = 11)
    protected String staffID;
    @XmlAttribute(name = "Patient_ID", required = true)
    @SerializedName("PatientID")
	@Mapping(getter = "getPtId", setter = "setPtId", type = "String", index = 12)
    protected String patientID;
    @XmlAttribute(name = "Staff_Associated_Rate_Effective_Date", required = true)
    @SerializedName("StaffAssociatedRateEffectiveDate")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getStaffAssocRateEffDate", setter = "setStaffAssocRateEffDate", type = "java.sql.Timestamp", index = 13)
    protected Date staffAssociatedRateEffectiveDate;
    @XmlAttribute(name = "Staff_Associated_Rate_Termination_Date", required = true)
    @SerializedName("StaffAssociatedRateTerminationDate")
    @XmlJavaTypeAdapter(Adapter1.class)
	@Mapping(getter = "getStaffAssocRateTermDate", setter = "setStaffAssocRateTermDate", type = "java.sql.Timestamp", index = 14)
    protected Date staffAssociatedRateTerminationDate;
    @XmlAttribute(name = "Service_Name", required = true)
    @SerializedName("ServiceName")
	@Mapping(getter = "getSvcName", setter = "setSvcName", type = "String", index = 15)
    protected ServiceName serviceName;
    @XmlAttribute(name = "Rate_Sub_Type_Name", required = true)
    @SerializedName("RateSubTypeName")
	@Mapping(getter = "getRateSubTypName", setter = "setRateSubTypName", type = "String", index = 16)
    protected RateSubTypeName rateSubTypeName;
    @XmlAttribute(name = "Rate_Type_Name", required = true)
    @SerializedName("RateTypeName")
	@Mapping(getter = "getRateTypName", setter = "setRateTypName", type = "String", index = 17)
    protected String rateTypeName;
    @XmlAttribute(name = "Rate_Qualifier_Code", required = true)
    @SerializedName("RateQualifierCode")
	@Mapping(getter = "getRateQlfrCode", setter = "setRateQlfrCode", type = "String", index = 18)
    protected RateQualifierCode rateQualifierCode;
    @XmlAttribute(name = "Rate_Change_Qualifier", required = true)
    @SerializedName("RateChangeQualifier")
	@Mapping(getter = "getRateChangeQlfr", setter = "setRateChangeQlfr", type = "String", index = 19)
    protected RateChangeQualifier rateChangeQualifier;
    @XmlAttribute(name = "Staff_Associated_Rate_Amount", required = true)
    @SerializedName("StaffAssociatedRateAmount")
	@Mapping(getter = "getStaffAssocRateAmt", setter = "setStaffAssocRateAmt", type = "java.math.BigDecimal", index = 20)
    protected BigDecimal staffAssociatedRateAmount;

    /**
     * Gets the value of the staffAssociatedRateSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStaffAssociatedRateSK() {
        return staffAssociatedRateSK;
    }

    /**
     * Sets the value of the staffAssociatedRateSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStaffAssociatedRateSK(BigInteger value) {
        this.staffAssociatedRateSK = value;
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
     * Gets the value of the recordEffectiveTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordEffectiveTimestamp() {
        return recordEffectiveTimestamp;
    }

    /**
     * Sets the value of the recordEffectiveTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordEffectiveTimestamp(Date value) {
        this.recordEffectiveTimestamp = value;
    }

    /**
     * Gets the value of the recordTerminationTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordTerminationTimestamp() {
        return recordTerminationTimestamp;
    }

    /**
     * Sets the value of the recordTerminationTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordTerminationTimestamp(Date value) {
        this.recordTerminationTimestamp = value;
    }

    /**
     * Gets the value of the recordCreatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordCreatedBy() {
        return recordCreatedBy;
    }

    /**
     * Sets the value of the recordCreatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordCreatedBy(String value) {
        this.recordCreatedBy = value;
    }

    /**
     * Gets the value of the recordUpdatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordUpdatedBy() {
        return recordUpdatedBy;
    }

    /**
     * Sets the value of the recordUpdatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordUpdatedBy(String value) {
        this.recordUpdatedBy = value;
    }

    /**
     * Gets the value of the changeReasonMemo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangeReasonMemo() {
        return changeReasonMemo;
    }

    /**
     * Sets the value of the changeReasonMemo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangeReasonMemo(String value) {
        this.changeReasonMemo = value;
    }

    /**
     * Gets the value of the currentRecordIndicator property.
     * 
     */
    public boolean isCurrentRecordIndicator() {
        return currentRecordIndicator;
    }

    /**
     * Sets the value of the currentRecordIndicator property.
     * 
     */
    public void setCurrentRecordIndicator(boolean value) {
        this.currentRecordIndicator = value;
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
     * Gets the value of the businessEntityID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessEntityID() {
        return businessEntityID;
    }

    /**
     * Sets the value of the businessEntityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessEntityID(String value) {
        this.businessEntityID = value;
    }

    /**
     * Gets the value of the staffID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStaffID() {
        return staffID;
    }

    /**
     * Sets the value of the staffID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStaffID(String value) {
        this.staffID = value;
    }

    /**
     * Gets the value of the patientID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * Sets the value of the patientID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientID(String value) {
        this.patientID = value;
    }

    /**
     * Gets the value of the staffAssociatedRateEffectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getStaffAssociatedRateEffectiveDate() {
        return staffAssociatedRateEffectiveDate;
    }

    /**
     * Sets the value of the staffAssociatedRateEffectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStaffAssociatedRateEffectiveDate(Date value) {
        this.staffAssociatedRateEffectiveDate = value;
    }

    /**
     * Gets the value of the staffAssociatedRateTerminationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getStaffAssociatedRateTerminationDate() {
        return staffAssociatedRateTerminationDate;
    }

    /**
     * Sets the value of the staffAssociatedRateTerminationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStaffAssociatedRateTerminationDate(Date value) {
        this.staffAssociatedRateTerminationDate = value;
    }

    /**
     * Gets the value of the serviceName property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceName }
     *     
     */
    public ServiceName getServiceName() {
        return serviceName;
    }

    /**
     * Sets the value of the serviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceName }
     *     
     */
    public void setServiceName(ServiceName value) {
        this.serviceName = value;
    }

    /**
     * Gets the value of the rateSubTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link RateSubTypeName }
     *     
     */
    public RateSubTypeName getRateSubTypeName() {
        return rateSubTypeName;
    }

    /**
     * Sets the value of the rateSubTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateSubTypeName }
     *     
     */
    public void setRateSubTypeName(RateSubTypeName value) {
        this.rateSubTypeName = value;
    }

    /**
     * Gets the value of the rateTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateTypeName() {
        return rateTypeName;
    }

    /**
     * Sets the value of the rateTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateTypeName(String value) {
        this.rateTypeName = value;
    }

    /**
     * Gets the value of the rateQualifierCode property.
     * 
     * @return
     *     possible object is
     *     {@link RateQualifierCode }
     *     
     */
    public RateQualifierCode getRateQualifierCode() {
        return rateQualifierCode;
    }

    /**
     * Sets the value of the rateQualifierCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateQualifierCode }
     *     
     */
    public void setRateQualifierCode(RateQualifierCode value) {
        this.rateQualifierCode = value;
    }

    /**
     * Gets the value of the rateChangeQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link RateChangeQualifier }
     *     
     */
    public RateChangeQualifier getRateChangeQualifier() {
        return rateChangeQualifier;
    }

    /**
     * Sets the value of the rateChangeQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateChangeQualifier }
     *     
     */
    public void setRateChangeQualifier(RateChangeQualifier value) {
        this.rateChangeQualifier = value;
    }

    /**
     * Gets the value of the staffAssociatedRateAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStaffAssociatedRateAmount() {
        return staffAssociatedRateAmount;
    }

    /**
     * Sets the value of the staffAssociatedRateAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStaffAssociatedRateAmount(BigDecimal value) {
        this.staffAssociatedRateAmount = value;
    }

}