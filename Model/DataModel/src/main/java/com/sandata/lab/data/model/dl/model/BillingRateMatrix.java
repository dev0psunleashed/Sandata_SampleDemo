//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.04 at 10:19:04 PM EDT 
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
 * <p>Java class for Billing_Rate_Matrix complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Billing_Rate_Matrix">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Billing_Rate_Matrix_SK" use="required" type="{}Surrogate_Key" />
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
 *       &lt;attribute name="Business_Entity_Line_of_Business_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Business_Entity_Line_of_Business" use="required" type="{}Name_Generic" />
 *       &lt;attribute name="Business_Entity_Location_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Payer_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Contract_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Billing_Rate_Matrix_Effective_Date" use="required" type="{}Attribute_Effective_Date" />
 *       &lt;attribute name="Billing_Rate_Matrix_Termination_Date" type="{}Attribute_Termination_Date" />
 *       &lt;attribute name="Service_Name" use="required" type="{}Service_Name" />
 *       &lt;attribute name="Rate_Type_Name" use="required" type="{}Rate_Type_Name" />
 *       &lt;attribute name="Rate_Qualifier_Code" use="required" type="{}Rate_Qualifier_Code" />
 *       &lt;attribute name="Rate_Amount" use="required" type="{}Money" />
 *       &lt;attribute name="Service_Unit_Name" use="required" type="{}Service_Unit_Name" />
 *       &lt;attribute name="Billing_Code" use="required" type="{}Billing_Code" />
 *       &lt;attribute name="Modifier_1_Code" type="{}Code" />
 *       &lt;attribute name="Modifier_2_Code" type="{}Code" />
 *       &lt;attribute name="Modifier_3_Code" type="{}Code" />
 *       &lt;attribute name="Modifier_4_Code" type="{}Code" />
 *       &lt;attribute name="Revenue_Code" type="{}Revenue_Code" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Billing_Rate_Matrix")
@OracleMetadata(
        packageName = "PKG_BILLING",
        insertMethod = "insertBillingRateMatrix",
        updateMethod = "updateBillingRateMatrix",
        deleteMethod = "deleteBillingRateMatrix",
        getMethod = "getBillingRateMatrix",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.BillingRateMatrixT",
        primaryKeys = {})
public class BillingRateMatrix extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Billing_Rate_Matrix_SK", required = true)
    @SerializedName("BillingRateMatrixSK")
	@Mapping(getter = "getBillingRateMatrixSk", setter = "setBillingRateMatrixSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger billingRateMatrixSK;
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
    @XmlAttribute(name = "Business_Entity_Location_ID", required = true)
    @SerializedName("BusinessEntityLocationID")
	@Mapping(getter = "getBeLocId", setter = "setBeLocId", type = "String", index = 11)
    protected String businessEntityLocationID;
    @XmlAttribute(name = "Payer_ID", required = true)
    @SerializedName("PayerID")
	@Mapping(getter = "getPayerId", setter = "setPayerId", type = "String", index = 12)
    protected String payerID;
    @XmlAttribute(name = "Contract_ID", required = true)
    @SerializedName("ContractID")
	@Mapping(getter = "getContrId", setter = "setContrId", type = "String", index = 13)
    protected String contractID;
    @XmlAttribute(name = "Billing_Rate_Matrix_Effective_Date", required = true)
    @SerializedName("BillingRateMatrixEffectiveDate")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getBillingRateMatrixEffDate", setter = "setBillingRateMatrixEffDate", type = "java.sql.Timestamp", index = 14)
    protected Date billingRateMatrixEffectiveDate;
    @XmlAttribute(name = "Billing_Rate_Matrix_Termination_Date")
    @SerializedName("BillingRateMatrixTerminationDate")
    @XmlJavaTypeAdapter(Adapter1.class)
	@Mapping(getter = "getBillingRateMatrixTermDate", setter = "setBillingRateMatrixTermDate", type = "java.sql.Timestamp", index = 15)
    protected Date billingRateMatrixTerminationDate;
    @XmlAttribute(name = "Service_Name", required = true)
    @SerializedName("ServiceName")
	@Mapping(getter = "getSvcName", setter = "setSvcName", type = "String", index = 16)
    protected ServiceName serviceName;
    @XmlAttribute(name = "Rate_Type_Name", required = true)
    @SerializedName("RateTypeName")
	@Mapping(getter = "getRateTypName", setter = "setRateTypName", type = "String", index = 17)
    protected String rateTypeName;
    @XmlAttribute(name = "Rate_Qualifier_Code", required = true)
    @SerializedName("RateQualifierCode")
	@Mapping(getter = "getRateQlfrCode", setter = "setRateQlfrCode", type = "String", index = 18)
    protected RateQualifierCode rateQualifierCode;
    @XmlAttribute(name = "Rate_Amount", required = true)
    @SerializedName("RateAmount")
	@Mapping(getter = "getRateAmt", setter = "setRateAmt", type = "java.math.BigDecimal", index = 19)
    protected BigDecimal rateAmount;
    @XmlAttribute(name = "Service_Unit_Name", required = true)
    @SerializedName("ServiceUnitName")
	@Mapping(getter = "getSvcUnitName", setter = "setSvcUnitName", type = "String", index = 20)
    protected ServiceUnitName serviceUnitName;
    @XmlAttribute(name = "Billing_Code", required = true)
    @SerializedName("BillingCode")
	@Mapping(getter = "getBillingCode", setter = "setBillingCode", type = "String", index = 21)
    protected String billingCode;
    @XmlAttribute(name = "Modifier_1_Code")
    @SerializedName("Modifier1Code")
	@Mapping(getter = "getMdfr1Code", setter = "setMdfr1Code", type = "String", index = 22)
    protected String modifier1Code;
    @XmlAttribute(name = "Modifier_2_Code")
    @SerializedName("Modifier2Code")
	@Mapping(getter = "getMdfr2Code", setter = "setMdfr2Code", type = "String", index = 23)
    protected String modifier2Code;
    @XmlAttribute(name = "Modifier_3_Code")
    @SerializedName("Modifier3Code")
	@Mapping(getter = "getMdfr3Code", setter = "setMdfr3Code", type = "String", index = 24)
    protected String modifier3Code;
    @XmlAttribute(name = "Modifier_4_Code")
    @SerializedName("Modifier4Code")
	@Mapping(getter = "getMdfr4Code", setter = "setMdfr4Code", type = "String", index = 25)
    protected String modifier4Code;
    @XmlAttribute(name = "Revenue_Code")
    @SerializedName("RevenueCode")
	@Mapping(getter = "getRevCode", setter = "setRevCode", type = "String", index = 26)
    protected String revenueCode;

    /**
     * Gets the value of the billingRateMatrixSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBillingRateMatrixSK() {
        return billingRateMatrixSK;
    }

    /**
     * Sets the value of the billingRateMatrixSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBillingRateMatrixSK(BigInteger value) {
        this.billingRateMatrixSK = value;
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
     * Gets the value of the businessEntityLocationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessEntityLocationID() {
        return businessEntityLocationID;
    }

    /**
     * Sets the value of the businessEntityLocationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessEntityLocationID(String value) {
        this.businessEntityLocationID = value;
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
     * Gets the value of the contractID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractID() {
        return contractID;
    }

    /**
     * Sets the value of the contractID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractID(String value) {
        this.contractID = value;
    }

    /**
     * Gets the value of the billingRateMatrixEffectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getBillingRateMatrixEffectiveDate() {
        return billingRateMatrixEffectiveDate;
    }

    /**
     * Sets the value of the billingRateMatrixEffectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingRateMatrixEffectiveDate(Date value) {
        this.billingRateMatrixEffectiveDate = value;
    }

    /**
     * Gets the value of the billingRateMatrixTerminationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getBillingRateMatrixTerminationDate() {
        return billingRateMatrixTerminationDate;
    }

    /**
     * Sets the value of the billingRateMatrixTerminationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingRateMatrixTerminationDate(Date value) {
        this.billingRateMatrixTerminationDate = value;
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

}
