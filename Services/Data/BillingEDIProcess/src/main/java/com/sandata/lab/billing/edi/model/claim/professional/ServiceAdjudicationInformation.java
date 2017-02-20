package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.ProcedureServiceCodeType;

public class ServiceAdjudicationInformation {
    private long id;
    private long serviceId;

    private String payerId;
    private java.math.BigDecimal paidAmount = new java.math.BigDecimal(0);
    private ProcedureServiceCodeType procedureCodeType;
    private String procedureCode;
    private String procedureCodeModifier1;
    private String procedureCodeModifier2;
    private String procedureCodeModifier3;
    private String procedureCodeModifier4;
    private String procedureCodeDescription;
    private java.math.BigDecimal quantity = new java.math.BigDecimal(0);
    private Long bundledLineNumber;

    private Date paymentDate = new Date(0);
    private java.math.BigDecimal amountOwed;

    private Collection<ServiceAdjustment> adjustments;

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

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public java.math.BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(java.math.BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public ProcedureServiceCodeType getProcedureCodeType() {
        return procedureCodeType;
    }

    public void setProcedureCodeType(ProcedureServiceCodeType procedureCodeType) {
        this.procedureCodeType = procedureCodeType;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getProcedureCodeModifier1() {
        return procedureCodeModifier1;
    }

    public void setProcedureCodeModifier1(String procedureCodeModifier1) {
        this.procedureCodeModifier1 = procedureCodeModifier1;
    }

    public String getProcedureCodeModifier2() {
        return procedureCodeModifier2;
    }

    public void setProcedureCodeModifier2(String procedureCodeModifier2) {
        this.procedureCodeModifier2 = procedureCodeModifier2;
    }

    public String getProcedureCodeModifier3() {
        return procedureCodeModifier3;
    }

    public void setProcedureCodeModifier3(String procedureCodeModifier3) {
        this.procedureCodeModifier3 = procedureCodeModifier3;
    }

    public String getProcedureCodeModifier4() {
        return procedureCodeModifier4;
    }

    public void setProcedureCodeModifier4(String procedureCodeModifier4) {
        this.procedureCodeModifier4 = procedureCodeModifier4;
    }

    public String getProcedureCodeDescription() {
        return procedureCodeDescription;
    }

    public void setProcedureCodeDescription(String procedureCodeDescription) {
        this.procedureCodeDescription = procedureCodeDescription;
    }

    public java.math.BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Long getBundledLineNumber() {
        return bundledLineNumber;
    }

    public void setBundledLineNumber(Long bundledLineNumber) {
        this.bundledLineNumber = bundledLineNumber;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public java.math.BigDecimal getAmountOwed() {
        return amountOwed;
    }

    public void setAmountOwed(java.math.BigDecimal amountOwed) {
        this.amountOwed = amountOwed;
    }

    public Collection<ServiceAdjustment> getAdjustments() {
        return adjustments;
    }

    public void setAdjustments(Collection<ServiceAdjustment> adjustments) {
        this.adjustments = adjustments;
    }
}