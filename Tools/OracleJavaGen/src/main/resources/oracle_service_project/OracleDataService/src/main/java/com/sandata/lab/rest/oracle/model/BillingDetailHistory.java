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


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * An invoice detail line defines the billable service that is identified for a service or task, has a specific data of service, and may or may not have a payer that provides some level (if not all) of reimbursement for the service.
 *  Each individual Invoice Detail Line is submitted as a claim, or a set of claims, to the defined payer
 * 
 * <p>Java class for Billing_Detail_History complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Billing_Detail_History">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Billing_Detail_History_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp_History" use="required" type="{}Record_Create_Timestamp_History" />
 *       &lt;attribute name="Action_Code" use="required" type="{}Action_Code" />
 *       &lt;attribute name="Billing_Detail_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Record_Created_By" type="{}Record_Created_By" />
 *       &lt;attribute name="Record_Updated_By" type="{}Record_Updated_By" />
 *       &lt;attribute name="Change_Reason_Code" type="{}Change_Reason_Code" />
 *       &lt;attribute name="Change_Reason_Memo" type="{}Change_Reason_Memo" />
 *       &lt;attribute name="Change_Version_ID" use="required" type="{}Change_Version_ID" />
 *       &lt;attribute name="Business_Entity_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Timesheet_Detail_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Timesheet_Summary_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Claim_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Vendor_Name" type="{}Name_Generic" />
 *       &lt;attribute name="Billing_Detail_Date" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="Billing_Detail_Status">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Billing_Detail_Submission_Status">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Service_Name" type="{}Service_Name" />
 *       &lt;attribute name="Billing_Code" type="{}Billing_Code" />
 *       &lt;attribute name="Modifier_1_Code" type="{}Code" />
 *       &lt;attribute name="Modifier_2_Code" type="{}Code" />
 *       &lt;attribute name="Modifier_3_Code" type="{}Code" />
 *       &lt;attribute name="Modifier_4_Code" type="{}Code" />
 *       &lt;attribute name="Payer_ID" type="{}ID" />
 *       &lt;attribute name="Revenue_Code" type="{}Revenue_Code" />
 *       &lt;attribute name="Rendering_Healthcare_Professional_National_Provider_Identifier" type="{}National_Provider_Identifier" />
 *       &lt;attribute name="Rate_Type_Name" type="{}Rate_Type_Name" />
 *       &lt;attribute name="Rate_Qualifier_Code" type="{}Rate_Qualifier_Code" />
 *       &lt;attribute name="Service_Unit_Name" type="{}Service_Unit_Name" />
 *       &lt;attribute name="Rate_Amount" type="{}Money" />
 *       &lt;attribute name="Billing_Detail_Rate_Amount" type="{}Money" />
 *       &lt;attribute name="Billing_Detail_Total_Amount" type="{}Money" />
 *       &lt;attribute name="Billing_Detail_Total_Unit">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;totalDigits value="8"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="User_Name">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="64"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="User_Globally_Unique_Identifier" type="{}Computing_Globally_Unique_Identifier" />
 *       &lt;attribute name="Memo" type="{}Comment_Long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Billing_Detail_History")
public class BillingDetailHistory extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Billing_Detail_History_SK", required = true)
    @SerializedName("BillingDetailHistorySK")
    protected BigInteger billingDetailHistorySK;
    @XmlAttribute(name = "Record_Create_Timestamp_History", required = true)
    @SerializedName("RecordCreateTimestampHistory")
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected Date recordCreateTimestampHistory;
    @XmlAttribute(name = "Action_Code", required = true)
    @SerializedName("ActionCode")
    protected ActionCode actionCode;
    @XmlAttribute(name = "Billing_Detail_SK", required = true)
    @SerializedName("BillingDetailSK")
    protected BigInteger billingDetailSK;
    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected Date recordCreateTimestamp;
    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected Date recordUpdateTimestamp;
    @XmlAttribute(name = "Record_Created_By")
    @SerializedName("RecordCreatedBy")
    protected String recordCreatedBy;
    @XmlAttribute(name = "Record_Updated_By")
    @SerializedName("RecordUpdatedBy")
    protected String recordUpdatedBy;
    @XmlAttribute(name = "Change_Reason_Code")
    @SerializedName("ChangeReasonCode")
    protected String changeReasonCode;
    @XmlAttribute(name = "Change_Reason_Memo")
    @SerializedName("ChangeReasonMemo")
    protected String changeReasonMemo;
    @XmlAttribute(name = "Change_Version_ID", required = true)
    @SerializedName("ChangeVersionID")
    protected BigInteger changeVersionID;
    @XmlAttribute(name = "Business_Entity_ID", required = true)
    @SerializedName("BusinessEntityID")
    protected String businessEntityID;
    @XmlAttribute(name = "Timesheet_Detail_SK", required = true)
    @SerializedName("TimesheetDetailSK")
    protected BigInteger timesheetDetailSK;
    @XmlAttribute(name = "Timesheet_Summary_SK", required = true)
    @SerializedName("TimesheetSummarySK")
    protected BigInteger timesheetSummarySK;
    @XmlAttribute(name = "Claim_SK", required = true)
    @SerializedName("ClaimSK")
    protected BigInteger claimSK;
    @XmlAttribute(name = "Vendor_Name")
    @SerializedName("VendorName")
    protected String vendorName;
    @XmlAttribute(name = "Billing_Detail_Date")
    @SerializedName("BillingDetailDate")
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected Date billingDetailDate;
    @XmlAttribute(name = "Billing_Detail_Status")
    @SerializedName("BillingDetailStatus")
    protected String billingDetailStatus;
    @XmlAttribute(name = "Billing_Detail_Submission_Status")
    @SerializedName("BillingDetailSubmissionStatus")
    protected String billingDetailSubmissionStatus;
    @XmlAttribute(name = "Service_Name")
    @SerializedName("ServiceName")
    protected ServiceName serviceName;
    @XmlAttribute(name = "Billing_Code")
    @SerializedName("BillingCode")
    protected String billingCode;
    @XmlAttribute(name = "Modifier_1_Code")
    @SerializedName("Modifier1Code")
    protected String modifier1Code;
    @XmlAttribute(name = "Modifier_2_Code")
    @SerializedName("Modifier2Code")
    protected String modifier2Code;
    @XmlAttribute(name = "Modifier_3_Code")
    @SerializedName("Modifier3Code")
    protected String modifier3Code;
    @XmlAttribute(name = "Modifier_4_Code")
    @SerializedName("Modifier4Code")
    protected String modifier4Code;
    @XmlAttribute(name = "Payer_ID")
    @SerializedName("PayerID")
    protected String payerID;
    @XmlAttribute(name = "Revenue_Code")
    @SerializedName("RevenueCode")
    protected String revenueCode;
    @XmlAttribute(name = "Rendering_Healthcare_Professional_National_Provider_Identifier")
    @SerializedName("RenderingHealthcareProfessionalNationalProviderIdentifier")
    protected String renderingHealthcareProfessionalNationalProviderIdentifier;
    @XmlAttribute(name = "Rate_Type_Name")
    @SerializedName("RateTypeName")
    protected String rateTypeName;
    @XmlAttribute(name = "Rate_Qualifier_Code")
    @SerializedName("RateQualifierCode")
    protected RateQualifierCode rateQualifierCode;
    @XmlAttribute(name = "Service_Unit_Name")
    @SerializedName("ServiceUnitName")
    protected ServiceUnitName serviceUnitName;
    @XmlAttribute(name = "Rate_Amount")
    @SerializedName("RateAmount")
    protected BigDecimal rateAmount;
    @XmlAttribute(name = "Billing_Detail_Rate_Amount")
    @SerializedName("BillingDetailRateAmount")
    protected BigDecimal billingDetailRateAmount;
    @XmlAttribute(name = "Billing_Detail_Total_Amount")
    @SerializedName("BillingDetailTotalAmount")
    protected BigDecimal billingDetailTotalAmount;
    @XmlAttribute(name = "Billing_Detail_Total_Unit")
    @SerializedName("BillingDetailTotalUnit")
    protected BigDecimal billingDetailTotalUnit;
    @XmlAttribute(name = "User_Name")
    @SerializedName("UserName")
    protected String userName;
    @XmlAttribute(name = "User_Globally_Unique_Identifier")
    @SerializedName("UserGloballyUniqueIdentifier")
    protected String userGloballyUniqueIdentifier;
    @XmlAttribute(name = "Memo")
    @SerializedName("Memo")
    protected String memo;

    /**
     * Gets the value of the billingDetailHistorySK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBillingDetailHistorySK() {
        return billingDetailHistorySK;
    }

    /**
     * Sets the value of the billingDetailHistorySK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBillingDetailHistorySK(BigInteger value) {
        this.billingDetailHistorySK = value;
    }

    /**
     * Gets the value of the recordCreateTimestampHistory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordCreateTimestampHistory() {
        return recordCreateTimestampHistory;
    }

    /**
     * Sets the value of the recordCreateTimestampHistory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordCreateTimestampHistory(Date value) {
        this.recordCreateTimestampHistory = value;
    }

    /**
     * Gets the value of the actionCode property.
     * 
     * @return
     *     possible object is
     *     {@link ActionCode }
     *     
     */
    public ActionCode getActionCode() {
        return actionCode;
    }

    /**
     * Sets the value of the actionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionCode }
     *     
     */
    public void setActionCode(ActionCode value) {
        this.actionCode = value;
    }

    /**
     * Gets the value of the billingDetailSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBillingDetailSK() {
        return billingDetailSK;
    }

    /**
     * Sets the value of the billingDetailSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBillingDetailSK(BigInteger value) {
        this.billingDetailSK = value;
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
     * Gets the value of the changeReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangeReasonCode() {
        return changeReasonCode;
    }

    /**
     * Sets the value of the changeReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangeReasonCode(String value) {
        this.changeReasonCode = value;
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
     * Gets the value of the timesheetDetailSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTimesheetDetailSK() {
        return timesheetDetailSK;
    }

    /**
     * Sets the value of the timesheetDetailSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTimesheetDetailSK(BigInteger value) {
        this.timesheetDetailSK = value;
    }

    /**
     * Gets the value of the timesheetSummarySK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTimesheetSummarySK() {
        return timesheetSummarySK;
    }

    /**
     * Sets the value of the timesheetSummarySK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTimesheetSummarySK(BigInteger value) {
        this.timesheetSummarySK = value;
    }

    /**
     * Gets the value of the claimSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getClaimSK() {
        return claimSK;
    }

    /**
     * Sets the value of the claimSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setClaimSK(BigInteger value) {
        this.claimSK = value;
    }

    /**
     * Gets the value of the vendorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * Sets the value of the vendorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorName(String value) {
        this.vendorName = value;
    }

    /**
     * Gets the value of the billingDetailDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getBillingDetailDate() {
        return billingDetailDate;
    }

    /**
     * Sets the value of the billingDetailDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingDetailDate(Date value) {
        this.billingDetailDate = value;
    }

    /**
     * Gets the value of the billingDetailStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingDetailStatus() {
        return billingDetailStatus;
    }

    /**
     * Sets the value of the billingDetailStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingDetailStatus(String value) {
        this.billingDetailStatus = value;
    }

    /**
     * Gets the value of the billingDetailSubmissionStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingDetailSubmissionStatus() {
        return billingDetailSubmissionStatus;
    }

    /**
     * Sets the value of the billingDetailSubmissionStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingDetailSubmissionStatus(String value) {
        this.billingDetailSubmissionStatus = value;
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
     * Gets the value of the billingCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingCode() {
        return billingCode;
    }

    /**
     * Sets the value of the billingCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingCode(String value) {
        this.billingCode = value;
    }

    /**
     * Gets the value of the modifier1Code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifier1Code() {
        return modifier1Code;
    }

    /**
     * Sets the value of the modifier1Code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifier1Code(String value) {
        this.modifier1Code = value;
    }

    /**
     * Gets the value of the modifier2Code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifier2Code() {
        return modifier2Code;
    }

    /**
     * Sets the value of the modifier2Code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifier2Code(String value) {
        this.modifier2Code = value;
    }

    /**
     * Gets the value of the modifier3Code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifier3Code() {
        return modifier3Code;
    }

    /**
     * Sets the value of the modifier3Code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifier3Code(String value) {
        this.modifier3Code = value;
    }

    /**
     * Gets the value of the modifier4Code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifier4Code() {
        return modifier4Code;
    }

    /**
     * Sets the value of the modifier4Code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifier4Code(String value) {
        this.modifier4Code = value;
    }

    /**
     * Gets the value of the payerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayerID() {
        return payerID;
    }

    /**
     * Sets the value of the payerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayerID(String value) {
        this.payerID = value;
    }

    /**
     * Gets the value of the revenueCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevenueCode() {
        return revenueCode;
    }

    /**
     * Sets the value of the revenueCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevenueCode(String value) {
        this.revenueCode = value;
    }

    /**
     * Gets the value of the renderingHealthcareProfessionalNationalProviderIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenderingHealthcareProfessionalNationalProviderIdentifier() {
        return renderingHealthcareProfessionalNationalProviderIdentifier;
    }

    /**
     * Sets the value of the renderingHealthcareProfessionalNationalProviderIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenderingHealthcareProfessionalNationalProviderIdentifier(String value) {
        this.renderingHealthcareProfessionalNationalProviderIdentifier = value;
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
     * Gets the value of the serviceUnitName property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceUnitName }
     *     
     */
    public ServiceUnitName getServiceUnitName() {
        return serviceUnitName;
    }

    /**
     * Sets the value of the serviceUnitName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceUnitName }
     *     
     */
    public void setServiceUnitName(ServiceUnitName value) {
        this.serviceUnitName = value;
    }

    /**
     * Gets the value of the rateAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRateAmount() {
        return rateAmount;
    }

    /**
     * Sets the value of the rateAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRateAmount(BigDecimal value) {
        this.rateAmount = value;
    }

    /**
     * Gets the value of the billingDetailRateAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBillingDetailRateAmount() {
        return billingDetailRateAmount;
    }

    /**
     * Sets the value of the billingDetailRateAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBillingDetailRateAmount(BigDecimal value) {
        this.billingDetailRateAmount = value;
    }

    /**
     * Gets the value of the billingDetailTotalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBillingDetailTotalAmount() {
        return billingDetailTotalAmount;
    }

    /**
     * Sets the value of the billingDetailTotalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBillingDetailTotalAmount(BigDecimal value) {
        this.billingDetailTotalAmount = value;
    }

    /**
     * Gets the value of the billingDetailTotalUnit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBillingDetailTotalUnit() {
        return billingDetailTotalUnit;
    }

    /**
     * Sets the value of the billingDetailTotalUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBillingDetailTotalUnit(BigDecimal value) {
        this.billingDetailTotalUnit = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the userGloballyUniqueIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserGloballyUniqueIdentifier() {
        return userGloballyUniqueIdentifier;
    }

    /**
     * Sets the value of the userGloballyUniqueIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserGloballyUniqueIdentifier(String value) {
        this.userGloballyUniqueIdentifier = value;
    }

    /**
     * Gets the value of the memo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemo() {
        return memo;
    }

    /**
     * Sets the value of the memo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemo(String value) {
        this.memo = value;
    }

}
