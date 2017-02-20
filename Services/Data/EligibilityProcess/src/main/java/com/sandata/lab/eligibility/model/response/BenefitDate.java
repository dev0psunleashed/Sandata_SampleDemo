package com.sandata.lab.eligibility.model.response;

import java.util.*;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.eligibility.model.response.enums.DateTypeCodeType;

public class BenefitDate {
	
	@SerializedName("Id")
    private long id;

	@SerializedName("Type")
    private DateTypeCodeType type;
	
	@SerializedName("IsRange")
    private boolean isRange;
	
	@SerializedName("StartDate")
    private Date startDate;
	
	@SerializedName("EndDate")
    private Date endDate;

	@SerializedName("BenefitId")
    private long benefitId;
	
	@SerializedName("Benefit")
    private Benefit benefit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DateTypeCodeType getType() {
        return type;
    }

    public void setType(DateTypeCodeType type) {
        this.type = type;
    }

    public boolean isRange() {
        return isRange;
    }

    public void setRange(boolean isRange) {
        this.isRange = isRange;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
}