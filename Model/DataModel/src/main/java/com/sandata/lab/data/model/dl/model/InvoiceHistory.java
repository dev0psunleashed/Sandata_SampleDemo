package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.Adapter2;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@OracleMetadata(
        packageName = "PKG_INVOICE",
        insertMethod = "insertInvHist",
        updateMethod = "updateInvHist",
        deleteMethod = "deleteInvHist",
        getMethod = "getInvHist",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.InvHistT",
        primaryKeys = {})
public class InvoiceHistory extends BaseObject {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "Invoice_Detail")
    @SerializedName("InvoiceDetail")
    protected List<InvoiceDetail> invoiceDetail;

    @SerializedName("InvoiceHistorySK")
    @Mapping(getter = "getInvHistSk", setter = "setInvHistSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger invoiceHistorySK;

    @SerializedName("RecordCreateTimestampHistory")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
    @Mapping(getter = "getRecCreateTmstpHist", setter = "setRecCreateTmstpHist", type = "java.sql.Timestamp", index = 1)
    protected Date recordCreateTimestampHistory;

    @SerializedName("ActionCode")
    @Mapping(getter = "getActionCode", setter = "setActionCode", type = "String", index = 2)
    protected String actionCode;

    @XmlAttribute(name = "Invoice_SK", required = true)
    @SerializedName("InvoiceSK")
    @Mapping(getter = "getInvSk", setter = "setInvSk", type = "java.math.BigDecimal", index = 3)
    protected BigInteger invoiceSK;
    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
    @Mapping(getter = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = 4)
    protected Date recordCreateTimestamp;
    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
    @Mapping(getter = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = 5)
    protected Date recordUpdateTimestamp;
    @XmlAttribute(name = "Record_Created_By")
    @SerializedName("RecordCreatedBy")
    @Mapping(getter = "getRecCreatedBy", setter = "setRecCreatedBy", type = "String", index = 6)
    protected String recordCreatedBy;
    @XmlAttribute(name = "Record_Updated_By")
    @SerializedName("RecordUpdatedBy")
    @Mapping(getter = "getRecUpdatedBy", setter = "setRecUpdatedBy", type = "String", index = 7)
    protected String recordUpdatedBy;
    @XmlAttribute(name = "Change_Reason_Code")
    @SerializedName("ChangeReasonCode")
    @Mapping(getter = "getChangeReasonCode", setter = "setChangeReasonCode", type = "String", index = 8)
    protected String changeReasonCode;
    @XmlAttribute(name = "Change_Reason_Memo")
    @SerializedName("ChangeReasonMemo")
    @Mapping(getter = "getChangeReasonMemo", setter = "setChangeReasonMemo", type = "String", index = 9)
    protected String changeReasonMemo;
    @XmlAttribute(name = "Change_Version_ID", required = true)
    @SerializedName("ChangeVersionID")
    @Mapping(getter = "getChangeVersionId", setter = "setChangeVersionId", type = "java.math.BigDecimal", index = 10)
    protected BigInteger changeVersionID;
    @XmlAttribute(name = "Business_Entity_ID")
    @SerializedName("BusinessEntityID")
    @Mapping(getter = "getBeId", setter = "setBeId", type = "String", index = 11)
    protected String businessEntityID;
    @XmlAttribute(name = "Business_Entity_Line_of_Business_ID")
    @SerializedName("BusinessEntityLineofBusinessID")
    @Mapping(getter = "getBeLobId", setter = "setBeLobId", type = "String", index = 12)
    protected String businessEntityLineOfBusinessID;
    @XmlAttribute(name = "Business_Entity_Location_ID")
    @SerializedName("BusinessEntityLocationID")
    @Mapping(getter = "getBeLocId", setter = "setBeLocId", type = "String", index = 13)
    protected String businessEntityLocationID;
    @XmlAttribute(name = "Payer_ID")
    @SerializedName("PayerID")
    @Mapping(getter = "getPayerId", setter = "setPayerId", type = "String", index = 14)
    protected String payerID;
    @XmlAttribute(name = "Contract_ID")
    @SerializedName("ContractID")
    @Mapping(getter = "getContrId", setter = "setContrId", type = "String", index = 15)
    protected String contractID;
    @XmlAttribute(name = "Authorization_ID")
    @SerializedName("AuthorizationID")
    @Mapping(getter = "getAuthId", setter = "setAuthId", type = "String", index = 16)
    protected String authorizationID;
    @XmlAttribute(name = "Patient_ID")
    @SerializedName("PatientID")
    @Mapping(getter = "getPtId", setter = "setPtId", type = "String", index = 17)
    protected String patientID;
    @XmlAttribute(name = "Patient_Insurance_ID_Number")
    @SerializedName("PatientInsuranceIDNumber")
    @Mapping(getter = "getPtInsIdNum", setter = "setPtInsIdNum", type = "String", index = 18)
    protected String patientInsuranceIDNumber;
    @XmlAttribute(name = "Invoice_Number")
    @SerializedName("InvoiceNumber")
    @Mapping(getter = "getInvNum", setter = "setInvNum", type = "String", index = 19)
    protected String invoiceNumber;
    @XmlAttribute(name = "Invoice_Status_Name")
    @SerializedName("InvoiceStatusName")
    @Mapping(getter = "getInvStatusName", setter = "setInvStatusName", type = "String", index = 20)
    protected InvoiceStatusName invoiceStatusName;
    @XmlAttribute(name = "Invoice_Submission_Type_Name")
    @SerializedName("InvoiceSubmissionTypeName")
    @Mapping(getter = "getInvSubmTypName", setter = "setInvSubmTypName", type = "String", index = 21)
    protected InvoiceSubmissionTypeName invoiceSubmissionTypeName;
    @XmlAttribute(name = "Invoice_Start_Date", required = true)
    @SerializedName("InvoiceStartDate")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
    @Mapping(getter = "getInvStartDate", setter = "setInvStartDate", type = "java.sql.Timestamp", index = 22)
    protected Date invoiceStartDate;
    @XmlAttribute(name = "Invoice_End_Date", required = true)
    @SerializedName("InvoiceEndDate")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
    @Mapping(getter = "getInvEndDate", setter = "setInvEndDate", type = "java.sql.Timestamp", index = 23)
    protected Date invoiceEndDate;
    @XmlAttribute(name = "Invoice_Date")
    @SerializedName("InvoiceDate")
    @XmlJavaTypeAdapter(Adapter2.class)
    @XmlSchemaType(name = "date")
    @Mapping(getter = "getInvDate", setter = "setInvDate", type = "java.sql.Timestamp", index = 24)
    protected Date invoiceDate;
    @XmlAttribute(name = "Invoice_Type_Qualifier")
    @SerializedName("InvoiceTypeQualifier")
    @Mapping(getter = "getInvTypQlfr", setter = "setInvTypQlfr", type = "String", index = 25)
    protected InvoiceTypeQualifier invoiceTypeQualifier;
    @XmlAttribute(name = "Invoice_Format_Type_Name")
    @SerializedName("InvoiceFormatTypeName")
    @Mapping(getter = "getInvFmtTypName", setter = "setInvFmtTypName", type = "String", index = 26)
    protected String invoiceFormatTypeName;
    @XmlAttribute(name = "ICD_Diagnosis_Code_Primary")
    @SerializedName("ICDDiagnosisCodePrimary")
    @Mapping(getter = "getIcdDxCodePrmy", setter = "setIcdDxCodePrmy", type = "String", index = 27)
    protected String icdDiagnosisCodePrimary;
    @XmlAttribute(name = "ICD_Diagnosis_Code_Revision_Qualifier_Primary")
    @SerializedName("ICDDiagnosisCodeRevisionQualifierPrimary")
    @Mapping(getter = "getIcdDxCodeRevisionQlfrPrmy", setter = "setIcdDxCodeRevisionQlfrPrmy", type = "String", index = 28)
    protected ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifierPrimary;
    @XmlAttribute(name = "ICD_Diagnosis_Code_2")
    @SerializedName("ICDDiagnosisCode2")
    @Mapping(getter = "getIcdDxCode2", setter = "setIcdDxCode2", type = "String", index = 29)
    protected String icdDiagnosisCode2;
    @XmlAttribute(name = "ICD_Diagnosis_Code_Revision_Qualifier_2")
    @SerializedName("ICDDiagnosisCodeRevisionQualifier2")
    @Mapping(getter = "getIcdDxCodeRevisionQlfr2", setter = "setIcdDxCodeRevisionQlfr2", type = "String", index = 30)
    protected ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier2;
    @XmlAttribute(name = "ICD_Diagnosis_Code_3")
    @SerializedName("ICDDiagnosisCode3")
    @Mapping(getter = "getIcdDxCode3", setter = "setIcdDxCode3", type = "String", index = 31)
    protected String icdDiagnosisCode3;
    @XmlAttribute(name = "ICD_Diagnosis_Code_Revision_Qualifier_3")
    @SerializedName("ICDDiagnosisCodeRevisionQualifier3")
    @Mapping(getter = "getIcdDxCodeRevisionQlfr3", setter = "setIcdDxCodeRevisionQlfr3", type = "String", index = 32)
    protected ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier3;
    @XmlAttribute(name = "ICD_Diagnosis_Code_4")
    @SerializedName("ICDDiagnosisCode4")
    @Mapping(getter = "getIcdDxCode4", setter = "setIcdDxCode4", type = "String", index = 33)
    protected String icdDiagnosisCode4;
    @XmlAttribute(name = "ICD_Diagnosis_Code_Revision_Qualifier_4")
    @SerializedName("ICDDiagnosisCodeRevisionQualifier4")
    @Mapping(getter = "getIcdDxCodeRevisionQlfr4", setter = "setIcdDxCodeRevisionQlfr4", type = "String", index = 34)
    protected ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier4;
    @XmlAttribute(name = "ICD_Diagnosis_Code_5")
    @SerializedName("ICDDiagnosisCode5")
    @Mapping(getter = "getIcdDxCode5", setter = "setIcdDxCode5", type = "String", index = 35)
    protected String icdDiagnosisCode5;
    @XmlAttribute(name = "ICD_Diagnosis_Code_Revision_Qualifier_5")
    @SerializedName("ICDDiagnosisCodeRevisionQualifier5")
    @Mapping(getter = "getIcdDxCodeRevisionQlfr5", setter = "setIcdDxCodeRevisionQlfr5", type = "String", index = 36)
    protected ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier5;
    @XmlAttribute(name = "ICD_Diagnosis_Code_6")
    @SerializedName("ICDDiagnosisCode6")
    @Mapping(getter = "getIcdDxCode6", setter = "setIcdDxCode6", type = "String", index = 37)
    protected String icdDiagnosisCode6;
    @XmlAttribute(name = "ICD_Diagnosis_Code_Revision_Qualifier_6")
    @SerializedName("ICDDiagnosisCodeRevisionQualifier6")
    @Mapping(getter = "getIcdDxCodeRevisionQlfr6", setter = "setIcdDxCodeRevisionQlfr6", type = "String", index = 38)
    protected ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier6;
    @XmlAttribute(name = "ICD_Diagnosis_Code_7")
    @SerializedName("ICDDiagnosisCode7")
    @Mapping(getter = "getIcdDxCode7", setter = "setIcdDxCode7", type = "String", index = 39)
    protected String icdDiagnosisCode7;
    @XmlAttribute(name = "ICD_Diagnosis_Code_Revision_Qualifier_7")
    @SerializedName("ICDDiagnosisCodeRevisionQualifier7")
    @Mapping(getter = "getIcdDxCodeRevisionQlfr7", setter = "setIcdDxCodeRevisionQlfr7", type = "String", index = 40)
    protected ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier7;
    @XmlAttribute(name = "ICD_Diagnosis_Code_8")
    @SerializedName("ICDDiagnosisCode8")
    @Mapping(getter = "getIcdDxCode8", setter = "setIcdDxCode8", type = "String", index = 41)
    protected String icdDiagnosisCode8;
    @XmlAttribute(name = "ICD_Diagnosis_Code_Revision_Qualifier_8")
    @SerializedName("ICDDiagnosisCodeRevisionQualifier8")
    @Mapping(getter = "getIcdDxCodeRevisionQlfr8", setter = "setIcdDxCodeRevisionQlfr8", type = "String", index = 42)
    protected ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier8;
    @XmlAttribute(name = "ICD_Diagnosis_Code_9")
    @SerializedName("ICDDiagnosisCode9")
    @Mapping(getter = "getIcdDxCode9", setter = "setIcdDxCode9", type = "String", index = 43)
    protected String icdDiagnosisCode9;
    @XmlAttribute(name = "ICD_Diagnosis_Code_Revision_Qualifier_9")
    @SerializedName("ICDDiagnosisCodeRevisionQualifier9")
    @Mapping(getter = "getIcdDxCodeRevisionQlfr9", setter = "setIcdDxCodeRevisionQlfr9", type = "String", index = 44)
    protected ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier9;
    @XmlAttribute(name = "ICD_Diagnosis_Code_10")
    @SerializedName("ICDDiagnosisCode10")
    @Mapping(getter = "getIcdDxCode10", setter = "setIcdDxCode10", type = "String", index = 45)
    protected String icdDiagnosisCode10;
    @XmlAttribute(name = "ICD_Diagnosis_Code_Revision_Qualifier_10")
    @SerializedName("ICDDiagnosisCodeRevisionQualifier10")
    @Mapping(getter = "getIcdDxCodeRevisionQlfr10", setter = "setIcdDxCodeRevisionQlfr10", type = "String", index = 46)
    protected ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier10;
    @XmlAttribute(name = "ICD_Diagnosis_Code_11")
    @SerializedName("ICDDiagnosisCode11")
    @Mapping(getter = "getIcdDxCode11", setter = "setIcdDxCode11", type = "String", index = 47)
    protected String icdDiagnosisCode11;
    @XmlAttribute(name = "ICD_Diagnosis_Code_Revision_Qualifier_11")
    @SerializedName("ICDDiagnosisCodeRevisionQualifier11")
    @Mapping(getter = "getIcdDxCodeRevisionQlfr11", setter = "setIcdDxCodeRevisionQlfr11", type = "String", index = 48)
    protected ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier11;
    @XmlAttribute(name = "ICD_Diagnosis_Code_12")
    @SerializedName("ICDDiagnosisCode12")
    @Mapping(getter = "getIcdDxCode12", setter = "setIcdDxCode12", type = "String", index = 49)
    protected String icdDiagnosisCode12;
    @XmlAttribute(name = "ICD_Diagnosis_Code_Revision_Qualifier_12")
    @SerializedName("ICDDiagnosisCodeRevisionQualifier12")
    @Mapping(getter = "getIcdDxCodeRevisionQlfr12", setter = "setIcdDxCodeRevisionQlfr12", type = "String", index = 50)
    protected ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier12;
    @XmlAttribute(name = "Rendering_Healthcare_Professional_National_Provider_Identifier")
    @SerializedName("RenderingHealthcareProfessionalNationalProviderIdentifier")
    @Mapping(getter = "getRenderHcpNpi", setter = "setRenderHcpNpi", type = "String", index = 51)
    protected String renderingHealthcareProfessionalNationalProviderIdentifier;
    @XmlAttribute(name = "Referring_Healthcare_Professional_National_Provider_Identifier")
    @SerializedName("ReferringHealthcareProfessionalNationalProviderIdentifier")
    @Mapping(getter = "getRefingHcpNpi", setter = "setRefingHcpNpi", type = "String", index = 52)
    protected String referringHealthcareProfessionalNationalProviderIdentifier;
    @XmlAttribute(name = "Invoice_Total_Amount")
    @SerializedName("InvoiceTotalAmount")
    @Mapping(getter = "getInvTotalAmt", setter = "setInvTotalAmt", type = "java.math.BigDecimal", index = 53)
    protected BigDecimal invoiceTotalAmount;
    @XmlAttribute(name = "Invoice_Total_Unit")
    @SerializedName("InvoiceTotalUnit")
    @Mapping(getter = "getInvTotalUnit", setter = "setInvTotalUnit", type = "java.math.BigDecimal", index = 54)
    protected BigDecimal invoiceTotalUnit;
    @XmlAttribute(name = "Invoice_Bill_Type_Name")
    @SerializedName("InvoiceBillTypeName")
    @Mapping(getter = "getInvBillTypName", setter = "setInvBillTypName", type = "String", index = 55)
    protected String invoiceBillTypeName;
    @SerializedName("InvoiceManualOverrideIndicator")
    @Mapping(getter = "getInvManualOverrideInd", setter = "setInvManualOverrideInd", type = "java.math.BigDecimal", index = 56)
    protected boolean invoiceManualOverrideIndicator;
    @SerializedName("UserName")
    @Mapping(getter = "getUserName", setter = "setUserName", type = "String", index = 57)
    protected String userName;
    @SerializedName("UserGloballyUniqueIdentifier")
    @Mapping(getter = "getUserGuid", setter = "setUserGuid", type = "String", index = 58)
    protected String userGloballyUniqueIdentifier;
    @SerializedName("Memo")
    @Mapping(getter = "getMemo", setter = "setMemo", type = "String", index = 59)
    protected String memo;

    /**
     * Gets the value of the invoiceDetail property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invoiceDetail property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvoiceDetail().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvoiceDetail }
     *
     *
     */
    public List<InvoiceDetail> getInvoiceDetail() {
        if (invoiceDetail == null) {
            invoiceDetail = new ArrayList<InvoiceDetail>();
        }
        return this.invoiceDetail;
    }

    /**
     * Gets the value of the invoiceSK property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getInvoiceSK() {
        return invoiceSK;
    }

    /**
     * Sets the value of the invoiceSK property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setInvoiceSK(BigInteger value) {
        this.invoiceSK = value;
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
     * Gets the value of the businessEntityLineOfBusinessID property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBusinessEntityLineOfBusinessID() {
        return businessEntityLineOfBusinessID;
    }

    /**
     * Sets the value of the businessEntityLineOfBusinessID property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBusinessEntityLineOfBusinessID(String value) {
        this.businessEntityLineOfBusinessID = value;
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
     * Gets the value of the authorizationID property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAuthorizationID() {
        return authorizationID;
    }

    /**
     * Sets the value of the authorizationID property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAuthorizationID(String value) {
        this.authorizationID = value;
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
     * Gets the value of the patientInsuranceIDNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPatientInsuranceIDNumber() {
        return patientInsuranceIDNumber;
    }

    /**
     * Sets the value of the patientInsuranceIDNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPatientInsuranceIDNumber(String value) {
        this.patientInsuranceIDNumber = value;
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
     * Gets the value of the invoiceStatusName property.
     *
     * @return
     *     possible object is
     *     {@link InvoiceStatusName }
     *
     */
    public InvoiceStatusName getInvoiceStatusName() {
        return invoiceStatusName;
    }

    /**
     * Sets the value of the invoiceStatusName property.
     *
     * @param value
     *     allowed object is
     *     {@link InvoiceStatusName }
     *
     */
    public void setInvoiceStatusName(InvoiceStatusName value) {
        this.invoiceStatusName = value;
    }

    /**
     * Gets the value of the invoiceSubmissionTypeName property.
     *
     * @return
     *     possible object is
     *     {@link InvoiceSubmissionTypeName }
     *
     */
    public InvoiceSubmissionTypeName getInvoiceSubmissionTypeName() {
        return invoiceSubmissionTypeName;
    }

    /**
     * Sets the value of the invoiceSubmissionTypeName property.
     *
     * @param value
     *     allowed object is
     *     {@link InvoiceSubmissionTypeName }
     *
     */
    public void setInvoiceSubmissionTypeName(InvoiceSubmissionTypeName value) {
        this.invoiceSubmissionTypeName = value;
    }

    /**
     * Gets the value of the invoiceStartDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Date getInvoiceStartDate() {
        return invoiceStartDate;
    }

    /**
     * Sets the value of the invoiceStartDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setInvoiceStartDate(Date value) {
        this.invoiceStartDate = value;
    }

    /**
     * Gets the value of the invoiceEndDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Date getInvoiceEndDate() {
        return invoiceEndDate;
    }

    /**
     * Sets the value of the invoiceEndDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setInvoiceEndDate(Date value) {
        this.invoiceEndDate = value;
    }

    /**
     * Gets the value of the invoiceDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * Sets the value of the invoiceDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setInvoiceDate(Date value) {
        this.invoiceDate = value;
    }

    /**
     * Gets the value of the invoiceTypeQualifier property.
     *
     * @return
     *     possible object is
     *     {@link InvoiceTypeQualifier }
     *
     */
    public InvoiceTypeQualifier getInvoiceTypeQualifier() {
        return invoiceTypeQualifier;
    }

    /**
     * Sets the value of the invoiceTypeQualifier property.
     *
     * @param value
     *     allowed object is
     *     {@link InvoiceTypeQualifier }
     *
     */
    public void setInvoiceTypeQualifier(InvoiceTypeQualifier value) {
        this.invoiceTypeQualifier = value;
    }

    /**
     * Gets the value of the invoiceFormatTypeName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getInvoiceFormatTypeName() {
        return invoiceFormatTypeName;
    }

    /**
     * Sets the value of the invoiceFormatTypeName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setInvoiceFormatTypeName(String value) {
        this.invoiceFormatTypeName = value;
    }

    /**
     * Gets the value of the icdDiagnosisCodePrimary property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getICDDiagnosisCodePrimary() {
        return icdDiagnosisCodePrimary;
    }

    /**
     * Sets the value of the icdDiagnosisCodePrimary property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setICDDiagnosisCodePrimary(String value) {
        this.icdDiagnosisCodePrimary = value;
    }

    /**
     * Gets the value of the icdDiagnosisCodeRevisionQualifierPrimary property.
     *
     * @return
     *     possible object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public ICDDiagnosisCodeRevisionQualifier getICDDiagnosisCodeRevisionQualifierPrimary() {
        return icdDiagnosisCodeRevisionQualifierPrimary;
    }

    /**
     * Sets the value of the icdDiagnosisCodeRevisionQualifierPrimary property.
     *
     * @param value
     *     allowed object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public void setICDDiagnosisCodeRevisionQualifierPrimary(ICDDiagnosisCodeRevisionQualifier value) {
        this.icdDiagnosisCodeRevisionQualifierPrimary = value;
    }

    /**
     * Gets the value of the icdDiagnosisCode2 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getICDDiagnosisCode2() {
        return icdDiagnosisCode2;
    }

    /**
     * Sets the value of the icdDiagnosisCode2 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setICDDiagnosisCode2(String value) {
        this.icdDiagnosisCode2 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCodeRevisionQualifier2 property.
     *
     * @return
     *     possible object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public ICDDiagnosisCodeRevisionQualifier getICDDiagnosisCodeRevisionQualifier2() {
        return icdDiagnosisCodeRevisionQualifier2;
    }

    /**
     * Sets the value of the icdDiagnosisCodeRevisionQualifier2 property.
     *
     * @param value
     *     allowed object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public void setICDDiagnosisCodeRevisionQualifier2(ICDDiagnosisCodeRevisionQualifier value) {
        this.icdDiagnosisCodeRevisionQualifier2 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCode3 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getICDDiagnosisCode3() {
        return icdDiagnosisCode3;
    }

    /**
     * Sets the value of the icdDiagnosisCode3 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setICDDiagnosisCode3(String value) {
        this.icdDiagnosisCode3 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCodeRevisionQualifier3 property.
     *
     * @return
     *     possible object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public ICDDiagnosisCodeRevisionQualifier getICDDiagnosisCodeRevisionQualifier3() {
        return icdDiagnosisCodeRevisionQualifier3;
    }

    /**
     * Sets the value of the icdDiagnosisCodeRevisionQualifier3 property.
     *
     * @param value
     *     allowed object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public void setICDDiagnosisCodeRevisionQualifier3(ICDDiagnosisCodeRevisionQualifier value) {
        this.icdDiagnosisCodeRevisionQualifier3 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCode4 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getICDDiagnosisCode4() {
        return icdDiagnosisCode4;
    }

    /**
     * Sets the value of the icdDiagnosisCode4 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setICDDiagnosisCode4(String value) {
        this.icdDiagnosisCode4 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCodeRevisionQualifier4 property.
     *
     * @return
     *     possible object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public ICDDiagnosisCodeRevisionQualifier getICDDiagnosisCodeRevisionQualifier4() {
        return icdDiagnosisCodeRevisionQualifier4;
    }

    /**
     * Sets the value of the icdDiagnosisCodeRevisionQualifier4 property.
     *
     * @param value
     *     allowed object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public void setICDDiagnosisCodeRevisionQualifier4(ICDDiagnosisCodeRevisionQualifier value) {
        this.icdDiagnosisCodeRevisionQualifier4 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCode5 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getICDDiagnosisCode5() {
        return icdDiagnosisCode5;
    }

    /**
     * Sets the value of the icdDiagnosisCode5 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setICDDiagnosisCode5(String value) {
        this.icdDiagnosisCode5 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCodeRevisionQualifier5 property.
     *
     * @return
     *     possible object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public ICDDiagnosisCodeRevisionQualifier getICDDiagnosisCodeRevisionQualifier5() {
        return icdDiagnosisCodeRevisionQualifier5;
    }

    /**
     * Sets the value of the icdDiagnosisCodeRevisionQualifier5 property.
     *
     * @param value
     *     allowed object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public void setICDDiagnosisCodeRevisionQualifier5(ICDDiagnosisCodeRevisionQualifier value) {
        this.icdDiagnosisCodeRevisionQualifier5 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCode6 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getICDDiagnosisCode6() {
        return icdDiagnosisCode6;
    }

    /**
     * Sets the value of the icdDiagnosisCode6 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setICDDiagnosisCode6(String value) {
        this.icdDiagnosisCode6 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCodeRevisionQualifier6 property.
     *
     * @return
     *     possible object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public ICDDiagnosisCodeRevisionQualifier getICDDiagnosisCodeRevisionQualifier6() {
        return icdDiagnosisCodeRevisionQualifier6;
    }

    /**
     * Sets the value of the icdDiagnosisCodeRevisionQualifier6 property.
     *
     * @param value
     *     allowed object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public void setICDDiagnosisCodeRevisionQualifier6(ICDDiagnosisCodeRevisionQualifier value) {
        this.icdDiagnosisCodeRevisionQualifier6 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCode7 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getICDDiagnosisCode7() {
        return icdDiagnosisCode7;
    }

    /**
     * Sets the value of the icdDiagnosisCode7 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setICDDiagnosisCode7(String value) {
        this.icdDiagnosisCode7 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCodeRevisionQualifier7 property.
     *
     * @return
     *     possible object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public ICDDiagnosisCodeRevisionQualifier getICDDiagnosisCodeRevisionQualifier7() {
        return icdDiagnosisCodeRevisionQualifier7;
    }

    /**
     * Sets the value of the icdDiagnosisCodeRevisionQualifier7 property.
     *
     * @param value
     *     allowed object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public void setICDDiagnosisCodeRevisionQualifier7(ICDDiagnosisCodeRevisionQualifier value) {
        this.icdDiagnosisCodeRevisionQualifier7 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCode8 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getICDDiagnosisCode8() {
        return icdDiagnosisCode8;
    }

    /**
     * Sets the value of the icdDiagnosisCode8 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setICDDiagnosisCode8(String value) {
        this.icdDiagnosisCode8 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCodeRevisionQualifier8 property.
     *
     * @return
     *     possible object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public ICDDiagnosisCodeRevisionQualifier getICDDiagnosisCodeRevisionQualifier8() {
        return icdDiagnosisCodeRevisionQualifier8;
    }

    /**
     * Sets the value of the icdDiagnosisCodeRevisionQualifier8 property.
     *
     * @param value
     *     allowed object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public void setICDDiagnosisCodeRevisionQualifier8(ICDDiagnosisCodeRevisionQualifier value) {
        this.icdDiagnosisCodeRevisionQualifier8 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCode9 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getICDDiagnosisCode9() {
        return icdDiagnosisCode9;
    }

    /**
     * Sets the value of the icdDiagnosisCode9 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setICDDiagnosisCode9(String value) {
        this.icdDiagnosisCode9 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCodeRevisionQualifier9 property.
     *
     * @return
     *     possible object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public ICDDiagnosisCodeRevisionQualifier getICDDiagnosisCodeRevisionQualifier9() {
        return icdDiagnosisCodeRevisionQualifier9;
    }

    /**
     * Sets the value of the icdDiagnosisCodeRevisionQualifier9 property.
     *
     * @param value
     *     allowed object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public void setICDDiagnosisCodeRevisionQualifier9(ICDDiagnosisCodeRevisionQualifier value) {
        this.icdDiagnosisCodeRevisionQualifier9 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCode10 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getICDDiagnosisCode10() {
        return icdDiagnosisCode10;
    }

    /**
     * Sets the value of the icdDiagnosisCode10 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setICDDiagnosisCode10(String value) {
        this.icdDiagnosisCode10 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCodeRevisionQualifier10 property.
     *
     * @return
     *     possible object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public ICDDiagnosisCodeRevisionQualifier getICDDiagnosisCodeRevisionQualifier10() {
        return icdDiagnosisCodeRevisionQualifier10;
    }

    /**
     * Sets the value of the icdDiagnosisCodeRevisionQualifier10 property.
     *
     * @param value
     *     allowed object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public void setICDDiagnosisCodeRevisionQualifier10(ICDDiagnosisCodeRevisionQualifier value) {
        this.icdDiagnosisCodeRevisionQualifier10 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCode11 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getICDDiagnosisCode11() {
        return icdDiagnosisCode11;
    }

    /**
     * Sets the value of the icdDiagnosisCode11 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setICDDiagnosisCode11(String value) {
        this.icdDiagnosisCode11 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCodeRevisionQualifier11 property.
     *
     * @return
     *     possible object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public ICDDiagnosisCodeRevisionQualifier getICDDiagnosisCodeRevisionQualifier11() {
        return icdDiagnosisCodeRevisionQualifier11;
    }

    /**
     * Sets the value of the icdDiagnosisCodeRevisionQualifier11 property.
     *
     * @param value
     *     allowed object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public void setICDDiagnosisCodeRevisionQualifier11(ICDDiagnosisCodeRevisionQualifier value) {
        this.icdDiagnosisCodeRevisionQualifier11 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCode12 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getICDDiagnosisCode12() {
        return icdDiagnosisCode12;
    }

    /**
     * Sets the value of the icdDiagnosisCode12 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setICDDiagnosisCode12(String value) {
        this.icdDiagnosisCode12 = value;
    }

    /**
     * Gets the value of the icdDiagnosisCodeRevisionQualifier12 property.
     *
     * @return
     *     possible object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public ICDDiagnosisCodeRevisionQualifier getICDDiagnosisCodeRevisionQualifier12() {
        return icdDiagnosisCodeRevisionQualifier12;
    }

    /**
     * Sets the value of the icdDiagnosisCodeRevisionQualifier12 property.
     *
     * @param value
     *     allowed object is
     *     {@link ICDDiagnosisCodeRevisionQualifier }
     *
     */
    public void setICDDiagnosisCodeRevisionQualifier12(ICDDiagnosisCodeRevisionQualifier value) {
        this.icdDiagnosisCodeRevisionQualifier12 = value;
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
     * Gets the value of the referringHealthcareProfessionalNationalProviderIdentifier property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReferringHealthcareProfessionalNationalProviderIdentifier() {
        return referringHealthcareProfessionalNationalProviderIdentifier;
    }

    /**
     * Sets the value of the referringHealthcareProfessionalNationalProviderIdentifier property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReferringHealthcareProfessionalNationalProviderIdentifier(String value) {
        this.referringHealthcareProfessionalNationalProviderIdentifier = value;
    }

    /**
     * Gets the value of the invoiceTotalAmount property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getInvoiceTotalAmount() {
        return invoiceTotalAmount;
    }

    /**
     * Sets the value of the invoiceTotalAmount property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setInvoiceTotalAmount(BigDecimal value) {
        this.invoiceTotalAmount = value;
    }

    /**
     * Gets the value of the invoiceTotalUnit property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getInvoiceTotalUnit() {
        return invoiceTotalUnit;
    }

    /**
     * Sets the value of the invoiceTotalUnit property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setInvoiceTotalUnit(BigDecimal value) {
        this.invoiceTotalUnit = value;
    }

    /**
     * Gets the value of the invoiceBillTypeName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getInvoiceBillTypeName() {
        return invoiceBillTypeName;
    }

    /**
     * Sets the value of the invoiceBillTypeName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setInvoiceBillTypeName(String value) {
        this.invoiceBillTypeName = value;
    }

    public boolean isInvoiceManualOverrideIndicator() {
        return invoiceManualOverrideIndicator;
    }

    public boolean getInvoiceManualOverrideIndicator() {
        return invoiceManualOverrideIndicator;
    }

    public void setInvoiceManualOverrideIndicator(boolean invoiceManualOverrideIndicator) {
        this.invoiceManualOverrideIndicator = invoiceManualOverrideIndicator;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGloballyUniqueIdentifier() {
        return userGloballyUniqueIdentifier;
    }

    public void setUserGloballyUniqueIdentifier(String userGloballyUniqueIdentifier) {
        this.userGloballyUniqueIdentifier = userGloballyUniqueIdentifier;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigInteger getInvoiceHistorySK() {
        return invoiceHistorySK;
    }

    public void setInvoiceHistorySK(BigInteger invoiceHistorySK) {
        this.invoiceHistorySK = invoiceHistorySK;
    }

    public Date getRecordCreateTimestampHistory() {
        return recordCreateTimestampHistory;
    }

    public void setRecordCreateTimestampHistory(Date recordCreateTimestampHistory) {
        this.recordCreateTimestampHistory = recordCreateTimestampHistory;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }
}
