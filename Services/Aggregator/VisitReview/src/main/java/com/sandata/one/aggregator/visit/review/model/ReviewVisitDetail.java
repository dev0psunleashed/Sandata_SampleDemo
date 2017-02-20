package com.sandata.one.aggregator.visit.review.model;

import com.google.gson.annotations.SerializedName;

public class ReviewVisitDetail extends ReviewVisitResult {

    private static final long serialVersionUID = 1L;

    @SerializedName("AgencyID")
    private String agencyId;

    @SerializedName("AgencyName")
    private String agencyName;

    @SerializedName("BillCode")
    private String billCode;

    @SerializedName("GroupVisitIndicator")
    private Boolean groupVisitIndicator = false;

    @SerializedName("RateType")
    private String rateType;

    @SerializedName("CallHours")
    private String callHours;

    @SerializedName("PatientVerifiedTimeIndicator")
    private Boolean patientVerifiedTimeIndicator;

    @SerializedName("PatientVerifiedServiceIndicator")
    private Boolean patientVerifiedServiceIndicator;

    @SerializedName("PatientDigitalSignatureDocID")
    private String patientDigitalSignatureDocId;

    @SerializedName("PatientAudioSignatureDocID")
    private String patientAudioSignatureDocId;

    @SerializedName("ThirdPartyVisitID")
    private String thirdPartyVisitId;

    /**
     * @return the agencyId
     */
    public String getAgencyId() {
        return agencyId;
    }

    /**
     * @param agencyId the agencyId to set
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    /**
     * @return the agencyName
     */
    public String getAgencyName() {
        return agencyName;
    }

    /**
     * @param agencyName the agencyName to set
     */
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    /**
     * @return the billCode
     */
    public String getBillCode() {
        return billCode;
    }

    /**
     * @param billCode the billCode to set
     */
    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    /**
     * @return the groupVisitIndicator
     */
    public Boolean getGroupVisitIndicator() {
        return groupVisitIndicator;
    }

    /**
     * @param groupVisitIndicator the groupVisitIndicator to set
     */
    public void setGroupVisitIndicator(Boolean groupVisitIndicator) {
        this.groupVisitIndicator = groupVisitIndicator;
    }

    /**
     * @return the rateType
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * @param rateType the rateType to set
     */
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    /**
     * @return the callHours
     */
    public String getCallHours() {
        return callHours;
    }

    /**
     * @param callHours the callHours to set
     */
    public void setCallHours(String callHours) {
        this.callHours = callHours;
    }

    /**
     * @return the patientVerifiedTimeIndicator
     */
    public Boolean getPatientVerifiedTimeIndicator() {
        return patientVerifiedTimeIndicator;
    }

    /**
     * @param patientVerifiedTimeIndicator the patientVerifiedTimeIndicator to set
     */
    public void setPatientVerifiedTimeIndicator(Boolean patientVerifiedTimeIndicator) {
        this.patientVerifiedTimeIndicator = patientVerifiedTimeIndicator;
    }

    /**
     * @return the patientVerifiedServiceIndicator
     */
    public Boolean getPatientVerifiedServiceIndicator() {
        return patientVerifiedServiceIndicator;
    }

    /**
     * @param patientVerifiedServiceIndicator the patientVerifiedServiceIndicator to set
     */
    public void setPatientVerifiedServiceIndicator(Boolean patientVerifiedServiceIndicator) {
        this.patientVerifiedServiceIndicator = patientVerifiedServiceIndicator;
    }

    /**
     * @return the patientDigitalSignatureDocId
     */
    public String getPatientDigitalSignatureDocId() {
        return patientDigitalSignatureDocId;
    }

    /**
     * @param patientDigitalSignatureDocId the patientDigitalSignatureDocId to set
     */
    public void setPatientDigitalSignatureDocId(String patientDigitalSignatureDocId) {
        this.patientDigitalSignatureDocId = patientDigitalSignatureDocId;
    }

    /**
     * @return the patientAudioSignatureDocId
     */
    public String getPatientAudioSignatureDocId() {
        return patientAudioSignatureDocId;
    }

    /**
     * @param patientAudioSignatureDocId the patientAudioSignatureDocId to set
     */
    public void setPatientAudioSignatureDocId(String patientAudioSignatureDocId) {
        this.patientAudioSignatureDocId = patientAudioSignatureDocId;
    }

    /**
     * @return the thirdPartyVisitId
     */
    public String getThirdPartyVisitId() {
        return thirdPartyVisitId;
    }

    /**
     * @param thirdPartyVisitId the thirdPartyVisitId to set
     */
    public void setThirdPartyVisitId(String thirdPartyVisitId) {
        this.thirdPartyVisitId = thirdPartyVisitId;
    }
}
