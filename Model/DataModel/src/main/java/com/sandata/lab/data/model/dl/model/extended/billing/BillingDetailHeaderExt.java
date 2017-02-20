package com.sandata.lab.data.model.dl.model.extended.billing;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.BillingDetail;
import com.sandata.lab.data.model.dl.model.ICDDiagnosisCodeRevisionQualifier;
import com.sandata.lab.data.model.dl.model.InvoiceTypeQualifier;
import com.sandata.lab.data.model.dl.model.find.InvoiceFindResult;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BillingDetailHeaderExt extends InvoiceFindResult {

    private static final long serialVersionUID = 1L;

    @SerializedName("ICDDiagnosisCodePrimary")
    private String icdDiagnosisCodePrimary;
    @SerializedName("InvoiceType")
    private InvoiceTypeQualifier invoiceType;
    @SerializedName("AuthorizationIDNumber")
    private String authorizationIdNumber;
    @SerializedName("PlaceOfService")
    private String placeOfService;
    @SerializedName("ProviderFirstName")
    private String providerFirstName;
    @SerializedName("ProviderLastName")
    private String providerLastName;
    @SerializedName("InvoiceFormat")
    private String invoiceFormat;
    @SerializedName("InvoiceTotalAmount")
    private BigDecimal invoiceTotalAmount;
    @SerializedName("BillingDetailServiceItems")
    private List<BillingDetail> billingDetailServiceLineItems;

    //BAR-131: HCFA-1500
    @SerializedName("PatientMiddleName")
    private String patientMiddleName;
    @SerializedName("PatientMedicareID")
    private String patientMedicareID;
    @SerializedName("PatientMedicaidID")
    private String patientMedicaidID;
    @SerializedName("PatientDateOfBirth")
    private Date patientDateOfBirth;
    @SerializedName("PatientGender")
    private String patientGender;
    @SerializedName("PatientTaxpayerIdentificationNumberQualifier")
    private String patientTaxpayerIdentificationNumberQualifier;
    @SerializedName("PatientTaxpayerIdentificationNumber")
    private String patientTaxpayerIdentificationNumber;
    @SerializedName("PatientAddress1")
    private String patientAddress1;
    @SerializedName("PatientAddress2")
    private String patientAddress2;
    @SerializedName("PatientApartmentNumber")
    private String patientApartmentNumber;
    @SerializedName("PatientCity")
    private String patientCity;
    @SerializedName("PatientState")
    private String patientState;
    @SerializedName("PatientPostalCode")
    private String patientPostalCode;
    @SerializedName("PatientZip4")
    private String patientZip4;
    @SerializedName("PatientCounty")
    private String patientCounty;
    @SerializedName("PatientRegion")
    private String patientRegion;
    @SerializedName("PatientBorough")
    private String patientBorough;
    @SerializedName("PatientPhoneQualifier")
    private String patientPhoneQualifier;
    @SerializedName("PatientPhone")
    private String patientPhone;
    @SerializedName("PatientPhoneExtension")
    private String patientPhoneExtension;

    //BAR-132: UB-04
    @SerializedName("ClaimID")
    private String claimID;
    @SerializedName("ClaimBillTypeName")
    private String claimBillTypeName;
    @SerializedName("InsuredGroupName")
    private String insuredGroupName;
    @SerializedName("InsuredGroupNumber")
    private String insuredGroupNumber;
    @SerializedName("ReleaseOfInformationCode")
    private String releaseOfInformationCode;
    @SerializedName("PatientSignatureSourceCode")
    private String patientSignatureSourceCode;
    @SerializedName("IndividualRelationshipCode")
    private String individualRelationshipCode;
    @SerializedName("BusinessEntityFederalTaxID")
    private String businessEntityFederalTaxID;
    @SerializedName("BusinessEntityNPI")
    private String businessEntityNPI;
    @SerializedName("PatientDischargeDate")
    private Date patientDischargeDate;
    @SerializedName("RenderingProviderNPI")
    private String renderingProviderNPI;
    @SerializedName("ReferringProviderNPI")
    private String referringProviderNPI;
    @SerializedName("AttendingProviderNPI")
    private String attendingProviderNPI;
    @SerializedName("BillingProviderNPI")
    private String billingProviderNPI;
    @SerializedName("PayToProviderNPI")
    private String payToProviderNPI;
    @SerializedName("AuthorizationDate")
    private Date authorizationDate;
    @SerializedName("ICDDiagnosisCodeRevisionQualifierPrimary")
    private ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifierPrimary;
    @SerializedName("ICDDiagnosisCode2")
    private String icdDiagnosisCode2;
    @SerializedName("ICDDiagnosisCodeRevisionQualifier2")
    private ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier2;
    @SerializedName("ICDDiagnosisCode3")
    private String icdDiagnosisCode3;
    @SerializedName("ICDDiagnosisCodeRevisionQualifier3")
    private ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier3;
    @SerializedName("ICDDiagnosisCode4")
    private String icdDiagnosisCode4;
    @SerializedName("ICDDiagnosisCodeRevisionQualifier4")
    private ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier4;
    @SerializedName("ICDDiagnosisCode5")
    private String icdDiagnosisCode5;
    @SerializedName("ICDDiagnosisCodeRevisionQualifier5")
    private ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier5;
    @SerializedName("ICDDiagnosisCode6")
    private String icdDiagnosisCode6;
    @SerializedName("ICDDiagnosisCodeRevisionQualifier6")
    private ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier6;
    @SerializedName("ICDDiagnosisCode7")
    private String icdDiagnosisCode7;
    @SerializedName("ICDDiagnosisCodeRevisionQualifier7")
    private ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier7;
    @SerializedName("ICDDiagnosisCode8")
    private String icdDiagnosisCode8;
    @SerializedName("ICDDiagnosisCodeRevisionQualifier8")
    private ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier8;
    @SerializedName("ICDDiagnosisCode9")
    private String icdDiagnosisCode9;
    @SerializedName("ICDDiagnosisCodeRevisionQualifier9")
    private ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier9;
    @SerializedName("ICDDiagnosisCode10")
    private String icdDiagnosisCode10;
    @SerializedName("ICDDiagnosisCodeRevisionQualifier10")
    private ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier10;
    @SerializedName("ICDDiagnosisCode11")
    private String icdDiagnosisCode11;
    @SerializedName("ICDDiagnosisCodeRevisionQualifier11")
    private ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier11;
    @SerializedName("ICDDiagnosisCode12")
    private String icdDiagnosisCode12;
    @SerializedName("ICDDiagnosisCodeRevisionQualifier12")
    private ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier12;

    //GEOR-5488
    @SerializedName("InvoiceManualOverrideIndicator")
    protected boolean invoiceManualOverrideIndicator;

    public String getIcdDiagnosisCodePrimary() {
        return icdDiagnosisCodePrimary;
    }

    public void setIcdDiagnosisCodePrimary(String icdDiagnosisCodePrimary) {
        this.icdDiagnosisCodePrimary = icdDiagnosisCodePrimary;
    }

    public InvoiceTypeQualifier getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceTypeQualifier invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getAuthorizationIdNumber() {
        return authorizationIdNumber;
    }

    public void setAuthorizationIdNumber(String authorizationIdNumber) {
        this.authorizationIdNumber = authorizationIdNumber;
    }

    public String getPlaceOfService() {
        return placeOfService;
    }

    public void setPlaceOfService(String placeOfService) {
        this.placeOfService = placeOfService;
    }

    public String getProviderFirstName() {
        return providerFirstName;
    }

    public void setProviderFirstName(String providerFirstName) {
        this.providerFirstName = providerFirstName;
    }

    public String getProviderLastName() {
        return providerLastName;
    }

    public void setProviderLastName(String providerLastName) {
        this.providerLastName = providerLastName;
    }

    public String getInvoiceFormat() {
        return invoiceFormat;
    }

    public void setInvoiceFormat(String invoiceFormat) {
        this.invoiceFormat = invoiceFormat;
    }

    public BigDecimal getInvoiceTotalAmount() {
        return invoiceTotalAmount;
    }

    public void setInvoiceTotalAmount(BigDecimal invoiceTotalAmount) {
        this.invoiceTotalAmount = invoiceTotalAmount;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientMedicareID() {
        return patientMedicareID;
    }

    public void setPatientMedicareID(String patientMedicareID) {
        this.patientMedicareID = patientMedicareID;
    }

    public String getPatientMedicaidID() {
        return patientMedicaidID;
    }

    public void setPatientMedicaidID(String patientMedicaidID) {
        this.patientMedicaidID = patientMedicaidID;
    }

    public Date getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public void setPatientDateOfBirth(Date patientDateOfBirth) {
        this.patientDateOfBirth = patientDateOfBirth;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientTaxpayerIdentificationNumberQualifier() {
        return patientTaxpayerIdentificationNumberQualifier;
    }

    public void setPatientTaxpayerIdentificationNumberQualifier(String patientTaxpayerIdentificationNumberQualifier) {
        this.patientTaxpayerIdentificationNumberQualifier = patientTaxpayerIdentificationNumberQualifier;
    }

    public String getPatientTaxpayerIdentificationNumber() {
        return patientTaxpayerIdentificationNumber;
    }

    public void setPatientTaxpayerIdentificationNumber(String patientTaxpayerIdentificationNumber) {
        this.patientTaxpayerIdentificationNumber = patientTaxpayerIdentificationNumber;
    }

    public String getPatientAddress1() {
        return patientAddress1;
    }

    public void setPatientAddress1(String patientAddress1) {
        this.patientAddress1 = patientAddress1;
    }

    public String getPatientAddress2() {
        return patientAddress2;
    }

    public void setPatientAddress2(String patientAddress2) {
        this.patientAddress2 = patientAddress2;
    }

    public String getPatientApartmentNumber() {
        return patientApartmentNumber;
    }

    public void setPatientApartmentNumber(String patientApartmentNumber) {
        this.patientApartmentNumber = patientApartmentNumber;
    }

    public String getPatientCity() {
        return patientCity;
    }

    public void setPatientCity(String patientCity) {
        this.patientCity = patientCity;
    }

    public String getPatientState() {
        return patientState;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }

    public String getPatientPostalCode() {
        return patientPostalCode;
    }

    public void setPatientPostalCode(String patientPostalCode) {
        this.patientPostalCode = patientPostalCode;
    }

    public String getPatientZip4() {
        return patientZip4;
    }

    public void setPatientZip4(String patientZip4) {
        this.patientZip4 = patientZip4;
    }

    public String getPatientCounty() {
        return patientCounty;
    }

    public void setPatientCounty(String patientCounty) {
        this.patientCounty = patientCounty;
    }

    public String getPatientRegion() {
        return patientRegion;
    }

    public void setPatientRegion(String patientRegion) {
        this.patientRegion = patientRegion;
    }

    public String getPatientBorough() {
        return patientBorough;
    }

    public void setPatientBorough(String patientBorough) {
        this.patientBorough = patientBorough;
    }

    public String getPatientPhoneQualifier() {
        return patientPhoneQualifier;
    }

    public void setPatientPhoneQualifier(String patientPhoneQualifier) {
        this.patientPhoneQualifier = patientPhoneQualifier;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getPatientPhoneExtension() {
        return patientPhoneExtension;
    }

    public void setPatientPhoneExtension(String patientPhoneExtension) {
        this.patientPhoneExtension = patientPhoneExtension;
    }

    public String getBusinessEntityFederalTaxID() {
        return businessEntityFederalTaxID;
    }

    public void setBusinessEntityFederalTaxID(String businessEntityFederalTaxID) {
        this.businessEntityFederalTaxID = businessEntityFederalTaxID;
    }

    public String getRenderingProviderNPI() {
        return renderingProviderNPI;
    }

    public void setRenderingProviderNPI(String renderingProviderNPI) {
        this.renderingProviderNPI = renderingProviderNPI;
    }

    public String getReferringProviderNPI() {
        return referringProviderNPI;
    }

    public void setReferringProviderNPI(String referringProviderNPI) {
        this.referringProviderNPI = referringProviderNPI;
    }

    public String getAttendingProviderNPI() {
        return attendingProviderNPI;
    }

    public void setAttendingProviderNPI(String attendingProviderNPI) {
        this.attendingProviderNPI = attendingProviderNPI;
    }

    public String getBillingProviderNPI() {
        return billingProviderNPI;
    }

    public void setBillingProviderNPI(String billingProviderNPI) {
        this.billingProviderNPI = billingProviderNPI;
    }

    public String getPayToProviderNPI() {
        return payToProviderNPI;
    }

    public void setPayToProviderNPI(String payToProviderNPI) {
        this.payToProviderNPI = payToProviderNPI;
    }

    public ICDDiagnosisCodeRevisionQualifier getIcdDiagnosisCodeRevisionQualifierPrimary() {
        return icdDiagnosisCodeRevisionQualifierPrimary;
    }

    public void setIcdDiagnosisCodeRevisionQualifierPrimary(ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifierPrimary) {
        this.icdDiagnosisCodeRevisionQualifierPrimary = icdDiagnosisCodeRevisionQualifierPrimary;
    }

    public String getIcdDiagnosisCode2() {
        return icdDiagnosisCode2;
    }

    public void setIcdDiagnosisCode2(String icdDiagnosisCode2) {
        this.icdDiagnosisCode2 = icdDiagnosisCode2;
    }

    public ICDDiagnosisCodeRevisionQualifier getIcdDiagnosisCodeRevisionQualifier2() {
        return icdDiagnosisCodeRevisionQualifier2;
    }

    public void setIcdDiagnosisCodeRevisionQualifier2(ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier2) {
        this.icdDiagnosisCodeRevisionQualifier2 = icdDiagnosisCodeRevisionQualifier2;
    }

    public String getIcdDiagnosisCode3() {
        return icdDiagnosisCode3;
    }

    public void setIcdDiagnosisCode3(String icdDiagnosisCode3) {
        this.icdDiagnosisCode3 = icdDiagnosisCode3;
    }

    public ICDDiagnosisCodeRevisionQualifier getIcdDiagnosisCodeRevisionQualifier3() {
        return icdDiagnosisCodeRevisionQualifier3;
    }

    public void setIcdDiagnosisCodeRevisionQualifier3(ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier3) {
        this.icdDiagnosisCodeRevisionQualifier3 = icdDiagnosisCodeRevisionQualifier3;
    }

    public String getIcdDiagnosisCode4() {
        return icdDiagnosisCode4;
    }

    public void setIcdDiagnosisCode4(String icdDiagnosisCode4) {
        this.icdDiagnosisCode4 = icdDiagnosisCode4;
    }

    public ICDDiagnosisCodeRevisionQualifier getIcdDiagnosisCodeRevisionQualifier4() {
        return icdDiagnosisCodeRevisionQualifier4;
    }

    public void setIcdDiagnosisCodeRevisionQualifier4(ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier4) {
        this.icdDiagnosisCodeRevisionQualifier4 = icdDiagnosisCodeRevisionQualifier4;
    }

    public String getIcdDiagnosisCode5() {
        return icdDiagnosisCode5;
    }

    public void setIcdDiagnosisCode5(String icdDiagnosisCode5) {
        this.icdDiagnosisCode5 = icdDiagnosisCode5;
    }

    public ICDDiagnosisCodeRevisionQualifier getIcdDiagnosisCodeRevisionQualifier5() {
        return icdDiagnosisCodeRevisionQualifier5;
    }

    public void setIcdDiagnosisCodeRevisionQualifier5(ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier5) {
        this.icdDiagnosisCodeRevisionQualifier5 = icdDiagnosisCodeRevisionQualifier5;
    }

    public String getIcdDiagnosisCode6() {
        return icdDiagnosisCode6;
    }

    public void setIcdDiagnosisCode6(String icdDiagnosisCode6) {
        this.icdDiagnosisCode6 = icdDiagnosisCode6;
    }

    public ICDDiagnosisCodeRevisionQualifier getIcdDiagnosisCodeRevisionQualifier6() {
        return icdDiagnosisCodeRevisionQualifier6;
    }

    public void setIcdDiagnosisCodeRevisionQualifier6(ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier6) {
        this.icdDiagnosisCodeRevisionQualifier6 = icdDiagnosisCodeRevisionQualifier6;
    }

    public String getIcdDiagnosisCode7() {
        return icdDiagnosisCode7;
    }

    public void setIcdDiagnosisCode7(String icdDiagnosisCode7) {
        this.icdDiagnosisCode7 = icdDiagnosisCode7;
    }

    public ICDDiagnosisCodeRevisionQualifier getIcdDiagnosisCodeRevisionQualifier7() {
        return icdDiagnosisCodeRevisionQualifier7;
    }

    public void setIcdDiagnosisCodeRevisionQualifier7(ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier7) {
        this.icdDiagnosisCodeRevisionQualifier7 = icdDiagnosisCodeRevisionQualifier7;
    }

    public String getIcdDiagnosisCode8() {
        return icdDiagnosisCode8;
    }

    public void setIcdDiagnosisCode8(String icdDiagnosisCode8) {
        this.icdDiagnosisCode8 = icdDiagnosisCode8;
    }

    public ICDDiagnosisCodeRevisionQualifier getIcdDiagnosisCodeRevisionQualifier8() {
        return icdDiagnosisCodeRevisionQualifier8;
    }

    public void setIcdDiagnosisCodeRevisionQualifier8(ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier8) {
        this.icdDiagnosisCodeRevisionQualifier8 = icdDiagnosisCodeRevisionQualifier8;
    }

    public String getIcdDiagnosisCode9() {
        return icdDiagnosisCode9;
    }

    public void setIcdDiagnosisCode9(String icdDiagnosisCode9) {
        this.icdDiagnosisCode9 = icdDiagnosisCode9;
    }

    public ICDDiagnosisCodeRevisionQualifier getIcdDiagnosisCodeRevisionQualifier9() {
        return icdDiagnosisCodeRevisionQualifier9;
    }

    public void setIcdDiagnosisCodeRevisionQualifier9(ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier9) {
        this.icdDiagnosisCodeRevisionQualifier9 = icdDiagnosisCodeRevisionQualifier9;
    }

    public String getIcdDiagnosisCode10() {
        return icdDiagnosisCode10;
    }

    public void setIcdDiagnosisCode10(String icdDiagnosisCode10) {
        this.icdDiagnosisCode10 = icdDiagnosisCode10;
    }

    public ICDDiagnosisCodeRevisionQualifier getIcdDiagnosisCodeRevisionQualifier10() {
        return icdDiagnosisCodeRevisionQualifier10;
    }

    public void setIcdDiagnosisCodeRevisionQualifier10(ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier10) {
        this.icdDiagnosisCodeRevisionQualifier10 = icdDiagnosisCodeRevisionQualifier10;
    }

    public String getIcdDiagnosisCode11() {
        return icdDiagnosisCode11;
    }

    public void setIcdDiagnosisCode11(String icdDiagnosisCode11) {
        this.icdDiagnosisCode11 = icdDiagnosisCode11;
    }

    public ICDDiagnosisCodeRevisionQualifier getIcdDiagnosisCodeRevisionQualifier11() {
        return icdDiagnosisCodeRevisionQualifier11;
    }

    public void setIcdDiagnosisCodeRevisionQualifier11(ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier11) {
        this.icdDiagnosisCodeRevisionQualifier11 = icdDiagnosisCodeRevisionQualifier11;
    }

    public String getIcdDiagnosisCode12() {
        return icdDiagnosisCode12;
    }

    public void setIcdDiagnosisCode12(String icdDiagnosisCode12) {
        this.icdDiagnosisCode12 = icdDiagnosisCode12;
    }

    public ICDDiagnosisCodeRevisionQualifier getIcdDiagnosisCodeRevisionQualifier12() {
        return icdDiagnosisCodeRevisionQualifier12;
    }

    public void setIcdDiagnosisCodeRevisionQualifier12(ICDDiagnosisCodeRevisionQualifier icdDiagnosisCodeRevisionQualifier12) {
        this.icdDiagnosisCodeRevisionQualifier12 = icdDiagnosisCodeRevisionQualifier12;
    }

    public Date getPatientDischargeDate() {
        return patientDischargeDate;
    }

    public void setPatientDischargeDate(Date patientDischargeDate) {
        this.patientDischargeDate = patientDischargeDate;
    }

    public String getBusinessEntityNPI() {
        return businessEntityNPI;
    }

    public void setBusinessEntityNPI(String businessEntityNPI) {
        this.businessEntityNPI = businessEntityNPI;
    }

    public Date getAuthorizationDate() {
        return authorizationDate;
    }

    public void setAuthorizationDate(Date authorizationDate) {
        this.authorizationDate = authorizationDate;
    }

    public String getClaimID() {
        return claimID;
    }

    public void setClaimID(String claimID) {
        this.claimID = claimID;
    }

    public String getClaimBillTypeName() {
        return claimBillTypeName;
    }

    public void setClaimBillTypeName(String claimBillTypeName) {
        this.claimBillTypeName = claimBillTypeName;
    }

    public String getInsuredGroupName() {
        return insuredGroupName;
    }

    public void setInsuredGroupName(String insuredGroupName) {
        this.insuredGroupName = insuredGroupName;
    }

    public String getInsuredGroupNumber() {
        return insuredGroupNumber;
    }

    public void setInsuredGroupNumber(String insuredGroupNumber) {
        this.insuredGroupNumber = insuredGroupNumber;
    }

    public String getReleaseOfInformationCode() {
        return releaseOfInformationCode;
    }

    public void setReleaseOfInformationCode(String releaseOfInformationCode) {
        this.releaseOfInformationCode = releaseOfInformationCode;
    }

    public String getPatientSignatureSourceCode() {
        return patientSignatureSourceCode;
    }

    public void setPatientSignatureSourceCode(String patientSignatureSourceCode) {
        this.patientSignatureSourceCode = patientSignatureSourceCode;
    }

    public String getIndividualRelationshipCode() {
        return individualRelationshipCode;
    }

    public void setIndividualRelationshipCode(String individualRelationshipCode) {
        this.individualRelationshipCode = individualRelationshipCode;
    }

    public boolean isInvoiceManualOverrideIndicator() {
        return invoiceManualOverrideIndicator;
    }

    public void setInvoiceManualOverrideIndicator(boolean invoiceManualOverrideIndicator) {
        this.invoiceManualOverrideIndicator = invoiceManualOverrideIndicator;
    }

    public List<BillingDetail> getBillingDetailServiceLineItems() {
        return billingDetailServiceLineItems;
    }

    public void setBillingDetailServiceLineItems(List<BillingDetail> billingDetailServiceLineItems) {
        this.billingDetailServiceLineItems = billingDetailServiceLineItems;
    }

    public boolean getInvoiceManualOverrideIndicator() {
        return invoiceManualOverrideIndicator;
    }
}
