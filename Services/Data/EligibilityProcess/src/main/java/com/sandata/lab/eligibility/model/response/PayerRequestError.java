package com.sandata.lab.eligibility.model.response;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.eligibility.model.response.enums.FollowUpActionCodeType;
import com.sandata.lab.eligibility.model.response.enums.RejectReasonCodeType;

public class PayerRequestError {
	
	@SerializedName("Id")
    private long id;

	@SerializedName("payerId")
    private long payerId;
	@SerializedName("NameError")
    private boolean isNameError;
	@SerializedName("RejectReasonCode")
    private RejectReasonCodeType rejectReasonCode;
	@SerializedName("FollowUpActionCode")
    private FollowUpActionCodeType followUpActionCode;

	@SerializedName("Payer")
    private Payer payer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPayerId() {
        return payerId;
    }

    public void setPayerId(long payerId) {
        this.payerId = payerId;
    }

    public boolean isNameError() {
        return isNameError;
    }

    public void setNameError(boolean isNameError) {
        this.isNameError = isNameError;
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

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }
}