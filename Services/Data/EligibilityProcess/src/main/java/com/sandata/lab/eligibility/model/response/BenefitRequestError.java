package com.sandata.lab.eligibility.model.response;

import com.sandata.lab.eligibility.model.response.enums.FollowUpActionCodeType;
import com.sandata.lab.eligibility.model.response.enums.RejectReasonCodeType;

public class BenefitRequestError {
    private long id;

    private long benefitId;
    private RejectReasonCodeType rejectReasonCode;
    private FollowUpActionCodeType followUpActionCode;

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

    public RejectReasonCodeType getRejectReasonCode() {
        return rejectReasonCode;
    }

    public void setRejectReasonCode(RejectReasonCodeType rejectReasonCode) {
        this.rejectReasonCode = rejectReasonCode;
    }

    public FollowUpActionCodeType getFollowUpActionCode() {
        return followUpActionCode;
    }

    public void setFollowUpActionCode(FollowUpActionCodeType followUpActionCode) {
        this.followUpActionCode = followUpActionCode;
    }

    public Benefit getBenefit() {
        return benefit;
    }

    public void setBenefit(Benefit benefit) {
        this.benefit = benefit;
    }
}