package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.IndustryCodeType;

public class Form {
    private long id;
    private long serviceId;

    private IndustryCodeType codeType;
    private String code;

    private Collection<FormQuestionResponse> answers = new ArrayList<FormQuestionResponse>();

    public final Collection<FormQuestionResponse> getAnswers() {
        return answers;
    }

    public final void setAnswers(Collection<FormQuestionResponse> value) {
        answers = value;
    }

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

    public IndustryCodeType getCodeType() {
        return codeType;
    }

    public void setCodeType(IndustryCodeType codeType) {
        this.codeType = codeType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}