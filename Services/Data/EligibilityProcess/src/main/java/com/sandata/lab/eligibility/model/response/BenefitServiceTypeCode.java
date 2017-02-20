package com.sandata.lab.eligibility.model.response;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.eligibility.model.response.enums.BenefitServiceTypeCodeType;

public class BenefitServiceTypeCode {
	
	@SerializedName("Id")
    private long id;
	
	@SerializedName("BenefitId")
    private long benefitId;

	@SerializedName("ServiceType")
    private BenefitServiceTypeCodeType serviceType;
	
	@SerializedName("Benefit")
    private Benefit benefit;

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

    public BenefitServiceTypeCodeType getServiceType() {
        return serviceType;
    }

    public void setServiceType(BenefitServiceTypeCodeType serviceType) {
        this.serviceType = serviceType;
    }

    public Benefit getBenefit() {
        return benefit;
    }

    public void setBenefit(Benefit benefit) {
        this.benefit = benefit;
    }
}