package com.sandata.lab.data.model.dl.model.find;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.Adapter1;
import com.sandata.lab.data.model.Adapter2;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.model.AccountsReceivable;
import com.sandata.lab.data.model.dl.model.AccountsReceivableTransactionBatch;
import com.sandata.lab.data.model.dl.model.AccountsReceivableTransactionBatchDetail;
import com.sandata.lab.data.model.dl.model.PaymentTypeQualifier;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountsReceivableDetail {

    private static final long serialVersionUID = 1L;

    @SerializedName("NoteDescription")
    String noteDescription;

    @SerializedName("NoteType")
    String noteType;

    @SerializedName("TransactionDescription")
    String transactionDescription;

    @SerializedName("TransactionType")
    String transactionType;

    @SerializedName("TransactionDate")
    Date transactionDate;

    @SerializedName("DenialCode")
    String denialCode;

    @SerializedName("AdjustmentCode")
    String adjustmentCode;


    @SerializedName("CustomerReferenceNumber")
    String customReferenceNumber;



    @SerializedName("TransactionCode")
    String transactionCode;

    @XmlElement(name = "Accounts_Receivable")
    @SerializedName("AccountsReceivable")
    protected List<AccountsReceivable> accountsReceivable;
    @XmlAttribute(name = "Accounts_Receivable_Transaction_Batch_Detail_SK", required = true)
    @SerializedName("AccountsReceivableTransactionBatchDetailSK")
    @Mapping(getter = "getArTxnBatchDetSk", setter = "setArTxnBatchDetSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger accountsReceivableTransactionBatchDetailSK;
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
    @XmlAttribute(name = "Record_Created_By")
    @SerializedName("RecordCreatedBy")
    @Mapping(getter = "getRecCreatedBy", setter = "setRecCreatedBy", type = "String", index = 3)
    protected String recordCreatedBy;
    @XmlAttribute(name = "Record_Updated_By")
    @SerializedName("RecordUpdatedBy")
    @Mapping(getter = "getRecUpdatedBy", setter = "setRecUpdatedBy", type = "String", index = 4)
    protected String recordUpdatedBy;
    @XmlAttribute(name = "Change_Reason_Code")
    @SerializedName("ChangeReasonCode")
    @Mapping(getter = "getChangeReasonCode", setter = "setChangeReasonCode", type = "String", index = 5)
    protected String changeReasonCode;
    @XmlAttribute(name = "Change_Reason_Memo")
    @SerializedName("ChangeReasonMemo")
    @Mapping(getter = "getChangeReasonMemo", setter = "setChangeReasonMemo", type = "String", index = 6)
    protected String changeReasonMemo;
    @XmlAttribute(name = "Change_Version_ID", required = true)
    @SerializedName("ChangeVersionID")
    @Mapping(getter = "getChangeVersionId", setter = "setChangeVersionId", type = "java.math.BigDecimal", index = 7)
    protected BigInteger changeVersionID;
    @XmlAttribute(name = "Business_Entity_ID")
    @SerializedName("BusinessEntityID")
    @Mapping(getter = "getBeId", setter = "setBeId", type = "String", index = 8)
    protected String businessEntityID;
    @XmlAttribute(name = "Accounts_Receivable_Transaction_Batch_ID", required = true)
    @SerializedName("AccountsReceivableTransactionBatchID")
    @Mapping(getter = "getArTxnBatchId", setter = "setArTxnBatchId", type = "String", index = 9)
    protected String accountsReceivableTransactionBatchID;
    @XmlAttribute(name = "Payer_ID")
    @SerializedName("PayerID")
    @Mapping(getter = "getPayerId", setter = "setPayerId", type = "String", index = 10)
    protected String payerID;
    @XmlAttribute(name = "Invoice_Number")
    @SerializedName("InvoiceNumber")
    @Mapping(getter = "getInvNum", setter = "setInvNum", type = "String", index = 11)
    protected String invoiceNumber;
    @XmlAttribute(name = "Check_Date")
    @SerializedName("CheckDate")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter2.class)
    @XmlSchemaType(name = "date")
    @Mapping(getter = "getCheckDate", setter = "setCheckDate", type = "java.sql.Timestamp", index = 12)
    protected Date checkDate;
    @XmlAttribute(name = "Check_Deposit_Date")
    @SerializedName("CheckDepositDate")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter2.class)
    @XmlSchemaType(name = "date")
    @Mapping(getter = "getCheckDepositDate", setter = "setCheckDepositDate", type = "java.sql.Timestamp", index = 13)
    protected Date checkDepositDate;
    @XmlAttribute(name = "Check_Received_Date")
    @SerializedName("CheckReceivedDate")
    @XmlJavaTypeAdapter(Adapter2.class)
    @XmlSchemaType(name = "date")
    @Mapping(getter = "getCheckRcvdDate", setter = "setCheckRcvdDate", type = "java.sql.Timestamp", index = 14)
    protected Date checkReceivedDate;
    @XmlAttribute(name = "Payment_Type_Qualifier")
    @SerializedName("PaymentTypeQualifier")
    @Mapping(getter = "getPmtTypQlfr", setter = "setPmtTypQlfr", type = "String", index = 15)
    protected PaymentTypeQualifier paymentTypeQualifier;
    @XmlAttribute(name = "Payment_Type_Number")
    @SerializedName("PaymentTypeNumber")
    @Mapping(getter = "getPmtTypNum", setter = "setPmtTypNum", type = "String", index = 16)
    protected String paymentTypeNumber;
    @XmlAttribute(name = "Payment_Amount")
    @SerializedName("PaymentAmount")
    @Mapping(getter = "getPmtAmt", setter = "setPmtAmt", type = "java.math.BigDecimal", index = 17)
    protected BigDecimal paymentAmount;
    @XmlAttribute(name = "Accounts_Receivable_Note_Type_Code")
    @SerializedName("AccountsReceivableNoteTypeCode")
    @Mapping(getter = "getArNoteTypCode", setter = "setArNoteTypCode", type = "String", index = 18)
    protected String accountsReceivableNoteTypeCode;
    @XmlAttribute(name = "Accounts_Receivable_Transaction_Note")
    @SerializedName("AccountsReceivableTransactionNote")
    @Mapping(getter = "getArTxnNote", setter = "setArTxnNote", type = "String", index = 19)
    protected String accountsReceivableTransactionNote;
    @XmlAttribute(name = "Accounts_Receivable_Transaction_Batch_Post_Indicator")
    @SerializedName("AccountsReceivableTransactionBatchPostIndicator")
    @Mapping(getter = "getArTxnBatchPostInd", setter = "setArTxnBatchPostInd", type = "java.math.BigDecimal", index = 20)
    protected Boolean accountsReceivableTransactionBatchPostIndicator;


    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }


    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public String getDenialCode() {
        return denialCode;
    }

    public void setDenialCode(String denialCode) {
        this.denialCode = denialCode;
    }

    public String getCustomReferenceNumber() {
        return customReferenceNumber;
    }

    public void setCustomReferenceNumber(String customReferenceNumber) {
        this.customReferenceNumber = customReferenceNumber;
    }

    public String getAdjustmentCode() {
        return adjustmentCode;
    }

    public void setAdjustmentCode(String adjustmentCode) {
        this.adjustmentCode = adjustmentCode;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public List<AccountsReceivable> getAccountsReceivable() {
        if (accountsReceivable == null) {
            accountsReceivable = new ArrayList<AccountsReceivable>();
        }
        return this.accountsReceivable;
    }

    /**
     * Gets the value of the accountsReceivableTransactionBatchDetailSK property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getAccountsReceivableTransactionBatchDetailSK() {
        return accountsReceivableTransactionBatchDetailSK;
    }

    /**
     * Sets the value of the accountsReceivableTransactionBatchDetailSK property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setAccountsReceivableTransactionBatchDetailSK(BigInteger value) {
        this.accountsReceivableTransactionBatchDetailSK = value;
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
     * Gets the value of the accountsReceivableTransactionBatchID property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAccountsReceivableTransactionBatchID() {
        return accountsReceivableTransactionBatchID;
    }

    /**
     * Sets the value of the accountsReceivableTransactionBatchID property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAccountsReceivableTransactionBatchID(String value) {
        this.accountsReceivableTransactionBatchID = value;
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
     * Gets the value of the invoiceNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * Sets the value of the invoiceNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setInvoiceNumber(String value) {
        this.invoiceNumber = value;
    }

    /**
     * Gets the value of the checkDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * Sets the value of the checkDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCheckDate(Date value) {
        this.checkDate = value;
    }

    /**
     * Gets the value of the checkDepositDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Date getCheckDepositDate() {
        return checkDepositDate;
    }

    /**
     * Sets the value of the checkDepositDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCheckDepositDate(Date value) {
        this.checkDepositDate = value;
    }

    /**
     * Gets the value of the checkReceivedDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Date getCheckReceivedDate() {
        return checkReceivedDate;
    }

    /**
     * Sets the value of the checkReceivedDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCheckReceivedDate(Date value) {
        this.checkReceivedDate = value;
    }

    /**
     * Gets the value of the paymentTypeQualifier property.
     *
     * @return
     *     possible object is
     *     {@link PaymentTypeQualifier }
     *
     */
    public PaymentTypeQualifier getPaymentTypeQualifier() {
        return paymentTypeQualifier;
    }

    /**
     * Sets the value of the paymentTypeQualifier property.
     *
     * @param value
     *     allowed object is
     *     {@link PaymentTypeQualifier }
     *
     */
    public void setPaymentTypeQualifier(PaymentTypeQualifier value) {
        this.paymentTypeQualifier = value;
    }

    /**
     * Gets the value of the paymentTypeNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPaymentTypeNumber() {
        return paymentTypeNumber;
    }

    /**
     * Sets the value of the paymentTypeNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPaymentTypeNumber(String value) {
        this.paymentTypeNumber = value;
    }

    /**
     * Gets the value of the paymentAmount property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the value of the paymentAmount property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setPaymentAmount(BigDecimal value) {
        this.paymentAmount = value;
    }

    /**
     * Gets the value of the accountsReceivableNoteTypeCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAccountsReceivableNoteTypeCode() {
        return accountsReceivableNoteTypeCode;
    }

    /**
     * Sets the value of the accountsReceivableNoteTypeCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAccountsReceivableNoteTypeCode(String value) {
        this.accountsReceivableNoteTypeCode = value;
    }

    /**
     * Gets the value of the accountsReceivableTransactionNote property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAccountsReceivableTransactionNote() {
        return accountsReceivableTransactionNote;
    }

    /**
     * Sets the value of the accountsReceivableTransactionNote property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAccountsReceivableTransactionNote(String value) {
        this.accountsReceivableTransactionNote = value;
    }

    /**
     * Gets the value of the accountsReceivableTransactionBatchPostIndicator property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isAccountsReceivableTransactionBatchPostIndicator() {
        return accountsReceivableTransactionBatchPostIndicator;
    }

    /**
     * Sets the value of the accountsReceivableTransactionBatchPostIndicator property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setAccountsReceivableTransactionBatchPostIndicator(Boolean value) {
        this.accountsReceivableTransactionBatchPostIndicator = value;
    }


}
