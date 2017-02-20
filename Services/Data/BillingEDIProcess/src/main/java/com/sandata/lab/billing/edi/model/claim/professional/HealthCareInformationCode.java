package com.sandata.lab.billing.edi.model.claim.professional;

import com.sandata.lab.billing.edi.model.enums.DiagnosisCodeType;

public class HealthCareInformationCode {
    private long id;
    private long claimId;

    private Long diagnosisNumber;
    private DiagnosisCodeType type;
    private String code;

    public long getClaimId() {
        return claimId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getDiagnosisNumber() {
        return diagnosisNumber;
    }

    public void setDiagnosisNumber(Long diagnosisNumber) {
        this.diagnosisNumber = diagnosisNumber;
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

    public void setClaimId(long claimId) {
        this.claimId = claimId;
    }
}