package com.sandata.lab.billing.edi.model.claim.professional;

import com.sandata.lab.billing.edi.model.enums.CertificationConditionIndicatorType;
import com.sandata.lab.billing.edi.model.enums.CertificationConditionType;
import com.sandata.lab.billing.edi.model.enums.ResponseCodeType;

public class ServiceCertification {
    private long id;
    private long serviceId;

    private CertificationConditionType certificationType;
    private ResponseCodeType certificationConditionApplies;
    private CertificationConditionIndicatorType condition;
    private CertificationConditionIndicatorType additionalCondition1;
    private CertificationConditionIndicatorType additionalCondition2;
    private CertificationConditionIndicatorType additionalCondition3;

    private CertificationConditionIndicatorType additionalCondition4;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public CertificationConditionType getCertificationType() {
        return certificationType;
    }

    public void setCertificationType(CertificationConditionType certificationType) {
        this.certificationType = certificationType;
    }

    public ResponseCodeType getCertificationConditionApplies() {
        return certificationConditionApplies;
    }

    public void setCertificationConditionApplies(ResponseCodeType certificationConditionApplies) {
        this.certificationConditionApplies = certificationConditionApplies;
    }

    public CertificationConditionIndicatorType getCondition() {
        return condition;
    }

    public void setCondition(CertificationConditionIndicatorType condition) {
        this.condition = condition;
    }

    public CertificationConditionIndicatorType getAdditionalCondition1() {
        return additionalCondition1;
    }

    public void setAdditionalCondition1(CertificationConditionIndicatorType additionalCondition1) {
        this.additionalCondition1 = additionalCondition1;
    }

    public CertificationConditionIndicatorType getAdditionalCondition2() {
        return additionalCondition2;
    }

    public void setAdditionalCondition2(CertificationConditionIndicatorType additionalCondition2) {
        this.additionalCondition2 = additionalCondition2;
    }

    public CertificationConditionIndicatorType getAdditionalCondition3() {
        return additionalCondition3;
    }

    public void setAdditionalCondition3(CertificationConditionIndicatorType additionalCondition3) {
        this.additionalCondition3 = additionalCondition3;
    }

    public CertificationConditionIndicatorType getAdditionalCondition4() {
        return additionalCondition4;
    }

    public void setAdditionalCondition4(CertificationConditionIndicatorType additionalCondition4) {
        this.additionalCondition4 = additionalCondition4;
    }
}