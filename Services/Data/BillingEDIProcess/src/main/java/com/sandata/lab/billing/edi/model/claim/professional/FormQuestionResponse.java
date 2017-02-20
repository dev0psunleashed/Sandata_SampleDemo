package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.ResponseCodeType;

public class FormQuestionResponse {
    private long id;
    private long formId;

    private String questionNumber;
    private ResponseCodeType yesNoResponse;
    private String writtenResponse;
    private Date dateResponse;
    private java.math.BigDecimal percentResponse;

    public Date getDateResponse() {
        return dateResponse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFormId() {
        return formId;
    }

    public void setFormId(long formId) {
        this.formId = formId;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public ResponseCodeType getYesNoResponse() {
        return yesNoResponse;
    }

    public void setYesNoResponse(ResponseCodeType yesNoResponse) {
        this.yesNoResponse = yesNoResponse;
    }

    public String getWrittenResponse() {
        return writtenResponse;
    }

    public void setWrittenResponse(String writtenResponse) {
        this.writtenResponse = writtenResponse;
    }

    public java.math.BigDecimal getPercentResponse() {
        return percentResponse;
    }

    public void setPercentResponse(java.math.BigDecimal percentResponse) {
        this.percentResponse = percentResponse;
    }

    public void setDateResponse(Date dateResponse) {
        this.dateResponse = dateResponse;
    }
}