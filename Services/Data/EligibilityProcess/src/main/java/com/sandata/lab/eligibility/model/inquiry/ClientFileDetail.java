package com.sandata.lab.eligibility.model.inquiry;

import java.math.BigDecimal;
import java.util.*;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.eligibility.annotation.Mapping;


public class ClientFileDetail {

    @SerializedName("SearchProcess")
    private SearchProcessIndicator searchProcess = SearchProcessIndicator.Schedule;

    @SerializedName("TraceNumber")
    private String traceNumber;

    @SerializedName("SubscriberFirstName")
    @Mapping(columnName="PT_FIRST_NAME")
    private String subscriberFirstName;

    @SerializedName("SubscriberMiddleName")
    @Mapping(columnName="PT_MIDDLE_NAME")
    private String subscriberMiddleName;

    @SerializedName("SubscriberLastName")
    @Mapping(columnName="PT_LAST_NAME")
    private String subscriberLastName;

    @SerializedName("SubscriberSuffixName")
    private String subscriberSuffixName = "";

    @SerializedName("SubscriberInsuranceId")
    @Mapping(columnName="PT_MEDICAID_ID")
    private String subscriberInsuranceId;

    @SerializedName("SubscriberGender")
    @Mapping(columnName="PT_GENDER_TYP_NAME", sourceEnum="com.sandata.lab.data.model.dl.model.GenderTypeName")
    private Gender subscriberGender = Gender.Unknown;

    @SerializedName("SubscriberAddress1")
    @Mapping(columnName="PT_ADDR1")
    private String subscriberAddress1;

    @SerializedName("SubscriberAddress2")
    @Mapping(columnName="PT_ADDR2")
    private String subscriberAddress2;

    @SerializedName("SubscriberCity")
    @Mapping(columnName="PT_CITY")
    private String subscriberCity;

    @SerializedName("SubscriberState")
    @Mapping(columnName="PT_STATE")
    private String subscriberState;

    @SerializedName("SubscriberPostalCode")
    @Mapping(columnName="PT_PSTL_CODE")
    private String subscriberPostalCode;

    @SerializedName("SubscriberHomePhone")
    @Mapping(columnName="PT_PHONE_HOME")
    private String subscriberHomePhone;

    @SerializedName("SubscriberWorkPhone")
    @Mapping(columnName="PT_PHONE_WORK")
    private String subscriberWorkPhone;

    @SerializedName("SubscriberCellPhone")
    @Mapping(columnName="PT_PHONE_CELL")
    private String subscriberCellPhone;

    @SerializedName("PatientFirstName")
    @Mapping(columnName="PT_FIRST_NAME")
    private String patientFirstName;

    @SerializedName("PatientMiddleName")
    @Mapping(columnName="PT_MIDDLE_NAME")
    private String patientMiddleName;

    @SerializedName("PatientLastName")
    @Mapping(columnName="PT_LAST_NAME")
    private String patientLastName;

    @SerializedName("PatientSuffixName")
    private String patientSuffixName = "";

    @SerializedName("PatientInsuranceId")
    @Mapping(columnName="PT_MEDICAID_ID")
    private String patientInsuranceId;

    @SerializedName("PatientGender")
    @Mapping(columnName="PT_GENDER_TYP_NAME", sourceEnum="com.sandata.lab.data.model.dl.model.GenderTypeName")
    private Gender patientGender = Gender.Unknown;

    @SerializedName("PatientAddress1")
    @Mapping(columnName="PT_ADDR1")
    private String patientAddress1;

    @SerializedName("PatientAddress2")
    @Mapping(columnName="PT_ADDR2")
    private String patientAddress2;

    @SerializedName("PatientCity")
    @Mapping(columnName="PT_CITY")
    private String patientCity;

    @SerializedName("PatientState")
    @Mapping(columnName="PT_STATE")
    private String patientState;

    @SerializedName("PatientPostalCode")
    @Mapping(columnName="PT_PSTL_CODE")
    private String patientPostalCode;

    @SerializedName("PatientHomePhone")
    @Mapping(columnName="PT_PHONE_HOME")
    private String patientHomePhone;

    @SerializedName("PatientWorkPhone")
    @Mapping(columnName="PT_PHONE_WORK")
    private String patientWorkPhone;

    @SerializedName("PatientCellPhone")
    @Mapping(columnName="PT_PHONE_CELL")
    private String patientCellPhone;

    @SerializedName("ServiceStartDate")
    @Mapping(columnName="PT_INS_START_DATE")
    private Date serviceStartDate = new Date();

    @SerializedName("ServiceEndDate")
    @Mapping(columnName="PT_INS_END_DATE")
    private Date serviceEndDate = new Date();

    @SerializedName("PayerId")
    @Mapping(columnName="PAYER_ID")
    private String payerId;

    @SerializedName("ProviderNPI")
    @Mapping(columnName="BE_NPI")
    private String providerNPI;

    @SerializedName("AmountBilled")
    @Mapping(columnName="PT_PAYER_PMT_RESP_VAL")
    private java.math.BigDecimal amountBilled = BigDecimal.ZERO;

    @SerializedName("ClientUse")
    private String clientUse;

    @SerializedName("SubscriberDateOfBirth")
    @Mapping(columnName="PT_DOB")
    private Date subscriberDateOfBirth = null;

    @SerializedName("SubscriberSocialSecurityNumber")
    @Mapping(columnName="PT_TIN")
    private String subscriberSocialSecurityNumber;

    @SerializedName("PatientDateOfBirth")
    @Mapping(columnName="PT_DOB")
    private Date patientDateOfBirth = null;

    @SerializedName("PatientSocialSecurityNumber")
    @Mapping(columnName="PT_TIN")
    private String patientSocialSecurityNumber;

    public SearchProcessIndicator getSearchProcess() {
        return searchProcess;
    }

    public void setSearchProcess(SearchProcessIndicator searchProcess) {
        this.searchProcess = searchProcess;
    }

    public String getTraceNumber() {
        return traceNumber;
    }

    public void setTraceNumber(String traceNumber) {
        this.traceNumber = traceNumber;
    }

    public String getSubscriberFirstName() {
        return subscriberFirstName;
    }

    public void setSubscriberFirstName(String subscriberFirstName) {
        this.subscriberFirstName = subscriberFirstName;
    }

    public String getSubscriberMiddleName() {
        return subscriberMiddleName;
    }

    public void setSubscriberMiddleName(String subscriberMiddleName) {
        this.subscriberMiddleName = subscriberMiddleName;
    }

    public String getSubscriberLastName() {
        return subscriberLastName;
    }

    public void setSubscriberLastName(String subscriberLastName) {
        this.subscriberLastName = subscriberLastName;
    }

    public String getSubscriberSuffixName() {
        return subscriberSuffixName;
    }

    public void setSubscriberSuffixName(String subscriberSuffixName) {
        this.subscriberSuffixName = subscriberSuffixName;
    }

    public String getSubscriberInsuranceId() {
        return subscriberInsuranceId;
    }

    public void setSubscriberInsuranceId(String subscriberInsuranceId) {
        this.subscriberInsuranceId = subscriberInsuranceId;
    }

    public Gender getSubscriberGender() {
        return subscriberGender;
    }

    public void setSubscriberGender(Gender subscriberGender) {
        this.subscriberGender = subscriberGender;
    }

    public String getSubscriberAddress1() {
        return subscriberAddress1;
    }

    public void setSubscriberAddress1(String subscriberAddress1) {
        this.subscriberAddress1 = subscriberAddress1;
    }

    public String getSubscriberAddress2() {
        return subscriberAddress2;
    }

    public void setSubscriberAddress2(String subscriberAddress2) {
        this.subscriberAddress2 = subscriberAddress2;
    }

    public String getSubscriberCity() {
        return subscriberCity;
    }

    public void setSubscriberCity(String subscriberCity) {
        this.subscriberCity = subscriberCity;
    }

    public String getSubscriberState() {
        return subscriberState;
    }

    public void setSubscriberState(String subscriberState) {
        this.subscriberState = subscriberState;
    }

    public String getSubscriberPostalCode() {
        return subscriberPostalCode;
    }

    public void setSubscriberPostalCode(String subscriberPostalCode) {
        this.subscriberPostalCode = subscriberPostalCode;
    }

    public String getSubscriberHomePhone() {
        return subscriberHomePhone;
    }

    public void setSubscriberHomePhone(String subscriberHomePhone) {
        this.subscriberHomePhone = subscriberHomePhone;
    }

    public String getSubscriberWorkPhone() {
        return subscriberWorkPhone;
    }

    public void setSubscriberWorkPhone(String subscriberWorkPhone) {
        this.subscriberWorkPhone = subscriberWorkPhone;
    }

    public String getSubscriberCellPhone() {
        return subscriberCellPhone;
    }

    public void setSubscriberCellPhone(String subscriberCellPhone) {
        this.subscriberCellPhone = subscriberCellPhone;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientSuffixName() {
        return patientSuffixName;
    }

    public void setPatientSuffixName(String patientSuffixName) {
        this.patientSuffixName = patientSuffixName;
    }

    public String getPatientInsuranceId() {
        return patientInsuranceId;
    }

    public void setPatientInsuranceId(String patientInsuranceId) {
        this.patientInsuranceId = patientInsuranceId;
    }

    public Gender getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(Gender patientGender) {
        this.patientGender = patientGender;
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

    public String getPatientHomePhone() {
        return patientHomePhone;
    }

    public void setPatientHomePhone(String patientHomePhone) {
        this.patientHomePhone = patientHomePhone;
    }

    public String getPatientWorkPhone() {
        return patientWorkPhone;
    }

    public void setPatientWorkPhone(String patientWorkPhone) {
        this.patientWorkPhone = patientWorkPhone;
    }

    public String getPatientCellPhone() {
        return patientCellPhone;
    }

    public void setPatientCellPhone(String patientCellPhone) {
        this.patientCellPhone = patientCellPhone;
    }

    public Date getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(Date serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    public Date getServiceEndDate() {
        return serviceEndDate;
    }

    public void setServiceEndDate(Date serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getProviderNPI() {
        return providerNPI;
    }

    public void setProviderNPI(String providerNPI) {
        this.providerNPI = providerNPI;
    }

    public java.math.BigDecimal getAmountBilled() {
        return amountBilled;
    }

    public void setAmountBilled(java.math.BigDecimal amountBilled) {
        this.amountBilled = amountBilled;
    }

    public String getClientUse() {
        return clientUse;
    }

    public void setClientUse(String clientUse) {
        this.clientUse = clientUse;
    }

    public Date getSubscriberDateOfBirth() {
        return subscriberDateOfBirth;
    }

    public void setSubscriberDateOfBirth(Date subscriberDateOfBirth) {
        this.subscriberDateOfBirth = subscriberDateOfBirth;
    }

    public String getSubscriberSocialSecurityNumber() {
        return subscriberSocialSecurityNumber;
    }

    public void setSubscriberSocialSecurityNumber(String subscriberSocialSecurityNumber) {
        this.subscriberSocialSecurityNumber = subscriberSocialSecurityNumber;
    }

    public Date getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public void setPatientDateOfBirth(Date patientDateOfBirth) {
        this.patientDateOfBirth = patientDateOfBirth;
    }

    public String getPatientSocialSecurityNumber() {
        return patientSocialSecurityNumber;
    }

    public void setPatientSocialSecurityNumber(String patientSocialSecurityNumber) {
        this.patientSocialSecurityNumber = patientSocialSecurityNumber;
    }
}