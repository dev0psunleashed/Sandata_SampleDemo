package com.sandata.lab.billing.edi.model.claim.institutional;

import java.util.Date;

import com.sandata.lab.billing.edi.model.enums.DiagnosisCodeType;
import com.sandata.lab.billing.edi.model.enums.ResponseCodeType;

public class HealthCareInformationCode {
    private long id;
    private long claimId;

    private DiagnosisCodeType type;
    private String code;
    private Date startDate;
    private Date endDate;
    private java.math.BigDecimal amount;
    private ResponseCodeType presentOnAdmission;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClaimId() {
        return claimId;
    }

    public void setClaimId(long claimId) {
        this.claimId = claimId;
    }

    public DiagnosisCodeType getType() {
        return type;
    }

    public void setType(DiagnosisCodeType type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public java.math.BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }

    public ResponseCodeType getPresentOnAdmission() {
        return presentOnAdmission;
    }

    public void setPresentOnAdmission(ResponseCodeType presentOnAdmission) {
        this.presentOnAdmission = presentOnAdmission;
    }
}