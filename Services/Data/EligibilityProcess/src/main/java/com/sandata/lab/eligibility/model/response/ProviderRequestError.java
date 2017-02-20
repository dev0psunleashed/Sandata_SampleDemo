package com.sandata.lab.eligibility.model.response;

import com.sandata.lab.eligibility.model.response.enums.FollowUpActionCodeType;
import com.sandata.lab.eligibility.model.response.enums.RejectReasonCodeType;

public class ProviderRequestError {
    private long id;

    private long providerId;
    private RejectReasonCodeType rejectReasonCode;
    private FollowUpActionCodeType followUpActionCode;

    private Provider provider;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProviderId() {
        return providerId;
    }

    public void setProviderId(long providerId) {
        this.providerId = providerId;
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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}