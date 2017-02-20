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

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


/**
 * The Payment 835 table holds the specific payment records received on an 835 explanation of payment (EOP), and is used to reconcile payments to invoices and invoice details.
 * 
 * 
 * <p>Java class for Claim_Payment_Advice_Line complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Claim_Payment_Advice_Line">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Claim_Payment_Advice_Line_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Record_Created_By" type="{}Record_Created_By" />
 *       &lt;attribute name="Record_Updated_By" type="{}Record_Updated_By" />
 *       &lt;attribute name="Change_Version_ID" use="required" type="{}Change_Version_ID" />
 *       &lt;attribute name="Claim_Payment_Advice_SK" type="{}Surrogate_Key" />
 *       &lt;attribute name="Business_Entity_ID" type="{}ID" />
 *       &lt;attribute name="Billing_Code_Type_Qualifier">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Billing_Code" type="{}Billing_Code" />
 *       &lt;attribute name="Modifier_1_Code" type="{}Code" />
 *       &lt;attribute name="Modifier_2_Code" type="{}Code" />
 *       &lt;attribute name="Modifier_3_Code" type="{}Code" />
 *       &lt;attribute name="Modifier_4_Code" type="{}Code" />
 *       &lt;attribute name="Invoice_Detail_Total_Amount" type="{}Money" />
 *       &lt;attribute name="Invoice_Detail_Provider_Payment_Amount" type="{}Money" />
 *       &lt;attribute name="Invoice_Detail_Provider_Total_Unit">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;totalDigits value="8"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Claim_Line_Payment_Date_Qualifier">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Claim_Line_Payment_Date" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="Claim_Line_Payment_ID_Qualifier">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Claim_Line_Payment_ID">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Claim_Line_Amount_Qualifier">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Claim_Line_Amount" type="{}Money" />
 *       &lt;attribute name="Claim_Line_Claim_Reference_Number">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Claim_Payment_Advice_Line")
@OracleMetadata(
        packageName = "PKG_AR",
        insertMethod = "insertClaimPmtAdviceLine",
        updateMethod = "updateClaimPmtAdviceLine",
        deleteMethod = "deleteClaimPmtAdviceLine",
        getMethod = "getClaimPmtAdviceLine",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.ClaimPmtAdviceLineT",
        primaryKeys = {})
public class ClaimPaymentAdviceLine extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Claim_Payment_Advice_Line_SK", required = true)
    @SerializedName("ClaimPaymentAdviceLineSK")
	@Mapping(getter = "getClaimPmtAdviceLineSk", setter = "setClaimPmtAdviceLineSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger claimPaymentAdviceLineSK;
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
    @XmlAttribute(name = "Record_Created_By")
    @SerializedName("RecordCreatedBy")
	@Mapping(getter = "getRecCreatedBy", setter = "setRecCreatedBy", type = "String", index = 3)
    protected String recordCreatedBy;
    @XmlAttribute(name = "Record_Updated_By")
    @SerializedName("RecordUpdatedBy")
	@Mapping(getter = "getRecUpdatedBy", setter = "setRecUpdatedBy", type = "String", index = 4)
    protected String recordUpdatedBy;
    @XmlAttribute(name = "Change_Version_ID", required = true)
    @SerializedName("ChangeVersionID")
	@Mapping(getter = "getChangeVersionId", setter = "setChangeVersionId", type = "java.math.BigDecimal", index = 5)
    protected BigInteger changeVersionID;
    @XmlAttribute(name = "Claim_Payment_Advice_SK")
    @SerializedName("ClaimPaymentAdviceSK")
	@Mapping(getter = "getClaimPmtAdviceSk", setter = "setClaimPmtAdviceSk", type = "java.math.BigDecimal", index = 6)
    protected BigInteger claimPaymentAdviceSK;
    @XmlAttribute(name = "Business_Entity_ID")
    @SerializedName("BusinessEntityID")
	@Mapping(getter = "getBeId", setter = "setBeId", type = "String", index = 7)
    protected String businessEntityID;
    @XmlAttribute(name = "Billing_Code_Type_Qualifier")
    @SerializedName("BillingCodeTypeQualifier")
	@Mapping(getter = "getBillingCodeTypQlfr", setter = "setBillingCodeTypQlfr", type = "String", index = 8)
    protected String billingCodeTypeQualifier;
    @XmlAttribute(name = "Billing_Code")
    @SerializedName("BillingCode")
	@Mapping(getter = "getBillingCode", setter = "setBillingCode", type = "String", index = 9)
    protected String billingCode;
    @XmlAttribute(name = "Modifier_1_Code")
    @SerializedName("Modifier1Code")
	@Mapping(getter = "getMdfr1Code", setter = "setMdfr1Code", type = "String", index = 10)
    protected String modifier1Code;
    @XmlAttribute(name = "Modifier_2_Code")
    @SerializedName("Modifier2Code")
	@Mapping(getter = "getMdfr2Code", setter = "setMdfr2Code", type = "String", index = 11)
    protected String modifier2Code;
    @XmlAttribute(name = "Modifier_3_Code")
    @SerializedName("Modifier3Code")
	@Mapping(getter = "getMdfr3Code", setter = "setMdfr3Code", type = "String", index = 12)
    protected String modifier3Code;
    @XmlAttribute(name = "Modifier_4_Code")
    @SerializedName("Modifier4Code")
	@Mapping(getter = "getMdfr4Code", setter = "setMdfr4Code", type = "String", index = 13)
    protected String modifier4Code;
    @XmlAttribute(name = "Invoice_Detail_Total_Amount")
    @SerializedName("InvoiceDetailTotalAmount")
	@Mapping(getter = "getInvDetTotalAmt", setter = "setInvDetTotalAmt", type = "java.math.BigDecimal", index = 14)
    protected BigDecimal invoiceDetailTotalAmount;
    @XmlAttribute(name = "Invoice_Detail_Provider_Payment_Amount")
    @SerializedName("InvoiceDetailProviderPaymentAmount")
	@Mapping(getter = "getInvDetProviderPmtAmt", setter = "setInvDetProviderPmtAmt", type = "java.math.BigDecimal", index = 15)
    protected BigDecimal invoiceDetailProviderPaymentAmount;
    @XmlAttribute(name = "Invoice_Detail_Provider_Total_Unit")
    @SerializedName("InvoiceDetailProviderTotalUnit")
	@Mapping(getter = "getInvDetProviderTotalUnit", setter = "setInvDetProviderTotalUnit", type = "java.math.BigDecimal", index = 16)
    protected BigDecimal invoiceDetailProviderTotalUnit;
    @XmlAttribute(name = "Claim_Line_Payment_Date_Qualifier")
    @SerializedName("ClaimLinePaymentDateQualifier")
	@Mapping(getter = "getClaimLinePmtDateQlfr", setter = "setClaimLinePmtDateQlfr", type = "String", index = 17)
    protected String claimLinePaymentDateQualifier;
    @XmlAttribute(name = "Claim_Line_Payment_Date")
    @SerializedName("ClaimLinePaymentDate")
    @XmlJavaTypeAdapter(Adapter1.class)
    @XmlSchemaType(name = "dateTime")
	@Mapping(getter = "getClaimLinePmtDate", setter = "setClaimLinePmtDate", type = "java.sql.Timestamp", index = 18)
    protected Date claimLinePaymentDate;
    @XmlAttribute(name = "Claim_Line_Payment_ID_Qualifier")
    @SerializedName("ClaimLinePaymentIDQualifier")
	@Mapping(getter = "getClaimLinePmtIdQlfr", setter = "setClaimLinePmtIdQlfr", type = "String", index = 19)
    protected String claimLinePaymentIDQualifier;
    @XmlAttribute(name = "Claim_Line_Payment_ID")
    @SerializedName("ClaimLinePaymentID")
	@Mapping(getter = "getClaimLinePmtId", setter = "setClaimLinePmtId", type = "String", index = 20)
    protected String claimLinePaymentID;
    @XmlAttribute(name = "Claim_Line_Amount_Qualifier")
    @SerializedName("ClaimLineAmountQualifier")
	@Mapping(getter = "getClaimLineAmtQlfr", setter = "setClaimLineAmtQlfr", type = "String", index = 21)
    protected String claimLineAmountQualifier;
    @XmlAttribute(name = "Claim_Line_Amount")
    @SerializedName("ClaimLineAmount")
	@Mapping(getter = "getClaimLineAmt", setter = "setClaimLineAmt", type = "java.math.BigDecimal", index = 22)
    protected BigDecimal claimLineAmount;
    @XmlAttribute(name = "Claim_Line_Claim_Reference_Number")
    @SerializedName("ClaimLineClaimReferenceNumber")
	@Mapping(getter = "getClaimLineCrn", setter = "setClaimLineCrn", type = "String", index = 23)
    protected String claimLineClaimReferenceNumber;
    @XmlAttribute(name = "Claim_Line_Service_Line_Item_Number")
    @SerializedName("ClaimLineServiceLineItemNumber")
    @Mapping(getter = "getClaimLineSvcLineItemNum", setter = "setClaimLineSvcLineItemNum", type = "String", index = 24)
    protected BigInteger claimLineServiceLineItemNumber;
    @XmlAttribute(name = "Payer_ID")
    @SerializedName("PayerID")
    @Mapping(getter = "getPayerId", setter = "setPayerId", type = "String", index = 25)
    protected String payerID;

    /**
     * Gets the value of the claimPaymentAdviceLineSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getClaimPaymentAdviceLineSK() {
        return claimPaymentAdviceLineSK;
    }

    /**
     * Sets the value of the claimPaymentAdviceLineSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setClaimPaymentAdviceLineSK(BigInteger value) {
        this.claimPaymentAdviceLineSK = value;
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
     * Gets the value of the claimPaymentAdviceSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getClaimPaymentAdviceSK() {
        return claimPaymentAdviceSK;
    }

    /**
     * Sets the value of the claimPaymentAdviceSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setClaimPaymentAdviceSK(BigInteger value) {
        this.claimPaymentAdviceSK = value;
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
     * Gets the value of the billingCodeTypeQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingCodeTypeQualifier() {
        return billingCodeTypeQualifier;
    }

    /**
     * Sets the value of the billingCodeTypeQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingCodeTypeQualifier(String value) {
        this.billingCodeTypeQualifier = value;
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
     * Gets the value of the invoiceDetailTotalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInvoiceDetailTotalAmount() {
        return invoiceDetailTotalAmount;
    }

    /**
     * Sets the value of the invoiceDetailTotalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInvoiceDetailTotalAmount(BigDecimal value) {
        this.invoiceDetailTotalAmount = value;
    }

    /**
     * Gets the value of the invoiceDetailProviderPaymentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInvoiceDetailProviderPaymentAmount() {
        return invoiceDetailProviderPaymentAmount;
    }

    /**
     * Sets the value of the invoiceDetailProviderPaymentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInvoiceDetailProviderPaymentAmount(BigDecimal value) {
        this.invoiceDetailProviderPaymentAmount = value;
    }

    /**
     * Gets the value of the invoiceDetailProviderTotalUnit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInvoiceDetailProviderTotalUnit() {
        return invoiceDetailProviderTotalUnit;
    }

    /**
     * Sets the value of the invoiceDetailProviderTotalUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInvoiceDetailProviderTotalUnit(BigDecimal value) {
        this.invoiceDetailProviderTotalUnit = value;
    }

    /**
     * Gets the value of the claimLinePaymentDateQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimLinePaymentDateQualifier() {
        return claimLinePaymentDateQualifier;
    }

    /**
     * Sets the value of the claimLinePaymentDateQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimLinePaymentDateQualifier(String value) {
        this.claimLinePaymentDateQualifier = value;
    }

    /**
     * Gets the value of the claimLinePaymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getClaimLinePaymentDate() {
        return claimLinePaymentDate;
    }

    /**
     * Sets the value of the claimLinePaymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimLinePaymentDate(Date value) {
        this.claimLinePaymentDate = value;
    }

    /**
     * Gets the value of the claimLinePaymentIDQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimLinePaymentIDQualifier() {
        return claimLinePaymentIDQualifier;
    }

    /**
     * Sets the value of the claimLinePaymentIDQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimLinePaymentIDQualifier(String value) {
        this.claimLinePaymentIDQualifier = value;
    }

    /**
     * Gets the value of the claimLinePaymentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimLinePaymentID() {
        return claimLinePaymentID;
    }

    /**
     * Sets the value of the claimLinePaymentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimLinePaymentID(String value) {
        this.claimLinePaymentID = value;
    }

    /**
     * Gets the value of the claimLineAmountQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimLineAmountQualifier() {
        return claimLineAmountQualifier;
    }

    /**
     * Sets the value of the claimLineAmountQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimLineAmountQualifier(String value) {
        this.claimLineAmountQualifier = value;
    }

    /**
     * Gets the value of the claimLineAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getClaimLineAmount() {
        return claimLineAmount;
    }

    /**
     * Sets the value of the claimLineAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setClaimLineAmount(BigDecimal value) {
        this.claimLineAmount = value;
    }

    /**
     * Gets the value of the claimLineClaimReferenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimLineClaimReferenceNumber() {
        return claimLineClaimReferenceNumber;
    }

    /**
     * Sets the value of the claimLineClaimReferenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimLineClaimReferenceNumber(String value) {
        this.claimLineClaimReferenceNumber = value;
    }

    public BigInteger getClaimLineServiceLineItemNumber() {
        return claimLineServiceLineItemNumber;
    }

    public void setClaimLineServiceLineItemNumber(BigInteger claimLineServiceLineItemNumber) {
        this.claimLineServiceLineItemNumber = claimLineServiceLineItemNumber;
    }

    public String getPayerID() {
        return payerID;
    }

    public void setPayerID(String payerID) {
        this.payerID = payerID;
    }
}
