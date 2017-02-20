package com.sandata.lab.eligibility.model.response;

import com.sandata.lab.eligibility.model.response.enums.AdditionalInformationCodeCategoryCodeType;
import com.sandata.lab.eligibility.model.response.enums.AdditionalInformationCodeListCodeType;

public class BenefitAdditionalInformation {
    private long id;
    private long benefitId;
    private Benefit benefit;

    private AdditionalInformationCodeListCodeType benefitCodeListType;
    private String benefitCode;
    private AdditionalInformationCodeCategoryCodeType benefitCodeCategory;
    private String injuredBodyPart;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBenefitId() {
        return benefitId;
    }

    public void setBenefitId(long benefitId) {
        this.benefitId = benefitId;
    }

    public Benefit getBenefit() {
        return benefit;
    }

    public void setBenefit(Benefit benefit) {
        this.benefit = benefit;
    }

    public AdditionalInformationCodeListCodeType getBenefitCodeListType() {
        return benefitCodeListType;
    }

    public void setBenefitCodeListType(AdditionalInformationCodeListCodeType benefitCodeListType) {
        this.benefitCodeListType = benefitCodeListType;
    }

    public String getBenefitCode() {
        return benefitCode;
    }

    public void setBenefitCode(String benefitCode) {
        this.benefitCode = benefitCode;
    }

    public AdditionalInformationCodeCategoryCodeType getBenefitCodeCategory() {
        return benefitCodeCategory;
    }

    public void setBenefitCodeCategory(AdditionalInformationCodeCategoryCodeType benefitCodeCategory) {
        this.benefitCodeCategory = benefitCodeCategory;
    }

    public String getInjuredBodyPart() {
        return injuredBodyPart;
    }

    public void setInjuredBodyPart(String injuredBodyPart) {
        this.injuredBodyPart = injuredBodyPart;
    }
}