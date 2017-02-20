package com.sandata.lab.billing.edi.model.remittance;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.ProcedureServiceCodeType;

public class ServicePayment {
    private long id;
    private long claimPaymentId;

    private ProcedureServiceCodeType serviceCodeType;
    private String serviceCode;
    private String serviceCodeModifier1;
    private String serviceCodeModifier2;
    private String serviceCodeModifier3;
    private String serviceCodeModifier4;
    private java.math.BigDecimal lineItemChargeAmount = new java.math.BigDecimal(0);
    private java.math.BigDecimal lineItemProviderPaymentAmount = new java.math.BigDecimal(0);
    private String revenueCode;
    private java.math.BigDecimal paidUnitsOfService;
    private ProcedureServiceCodeType submittedServiceCodeType;
    private String submittedServiceCode;
    private String submittedServiceCodeModifier1;
    private String submittedServiceCodeModifier2;
    private String submittedServiceCodeModifier3;
    private String submittedServiceCodeModifier4;
    private String submittedServiceCodeDescription;
    private java.math.BigDecimal originalUnitsOfService;

    private Collection<ServicePaymentDate> dates = new ArrayList<ServicePaymentDate>();

    public final Collection<ServicePaymentDate> getDates() {
        return dates;
    }

    public final void setDates(Collection<ServicePaymentDate> value) {
        dates = value;
    }

    private Collection<ServicePaymentAdjustment> adjustments = new ArrayList<ServicePaymentAdjustment>();

    public final Collection<ServicePaymentAdjustment> getAdjustments() {
        return adjustments;
    }

    public final void setAdjustments(Collection<ServicePaymentAdjustment> value) {
        adjustments = value;
    }

    private Collection<ServicePaymentAdditionalId> additionalIds = new ArrayList<ServicePaymentAdditionalId>();

    public final Collection<ServicePaymentAdditionalId> getAdditionalIds() {
        return additionalIds;
    }

    public final void setAdditionalIds(Collection<ServicePaymentAdditionalId> value) {
        additionalIds = value;
    }

    private Collection<ServicePaymentAmount> amounts = new ArrayList<ServicePaymentAmount>();

    public final Collection<ServicePaymentAmount> getAmounts() {
        return amounts;
    }

    public final void setAmounts(Collection<ServicePaymentAmount> value) {
        amounts = value;
    }

    private Collection<ServicePaymentQuantity> quantities = new ArrayList<ServicePaymentQuantity>();

    public final Collection<ServicePaymentQuantity> getQuantities() {
        return quantities;
    }

    public final void setQuantities(Collection<ServicePaymentQuantity> value) {
        quantities = value;
    }

    private Collection<ServicePaymentRemark> remarks = new ArrayList<ServicePaymentRemark>();

    public final Collection<ServicePaymentRemark> getRemarks() {
        return remarks;
    }

    public final void setRemarks(Collection<ServicePaymentRemark> value) {
        remarks = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClaimPaymentId() {
        return claimPaymentId;
    }

    public void setClaimPaymentId(long claimPaymentId) {
        this.claimPaymentId = claimPaymentId;
    }

    public ProcedureServiceCodeType getServiceCodeType() {
        return serviceCodeType;
    }

    public void setServiceCodeType(ProcedureServiceCodeType serviceCodeType) {
        this.serviceCodeType = serviceCodeType;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceCodeModifier1() {
        return serviceCodeModifier1;
    }

    public void setServiceCodeModifier1(String serviceCodeModifier1) {
        this.serviceCodeModifier1 = serviceCodeModifier1;
    }

    public String getServiceCodeModifier2() {
        return serviceCodeModifier2;
    }

    public void setServiceCodeModifier2(String serviceCodeModifier2) {
        this.serviceCodeModifier2 = serviceCodeModifier2;
    }

    public String getServiceCodeModifier3() {
        return serviceCodeModifier3;
    }

    public void setServiceCodeModifier3(String serviceCodeModifier3) {
        this.serviceCodeModifier3 = serviceCodeModifier3;
    }

    public String getServiceCodeModifier4() {
        return serviceCodeModifier4;
    }

    public void setServiceCodeModifier4(String serviceCodeModifier4) {
        this.serviceCodeModifier4 = serviceCodeModifier4;
    }

    public java.math.BigDecimal getLineItemChargeAmount() {
        return lineItemChargeAmount;
    }

    public void setLineItemChargeAmount(java.math.BigDecimal lineItemChargeAmount) {
        this.lineItemChargeAmount = lineItemChargeAmount;
    }

    public java.math.BigDecimal getLineItemProviderPaymentAmount() {
        return lineItemProviderPaymentAmount;
    }

    public void setLineItemProviderPaymentAmount(java.math.BigDecimal lineItemProviderPaymentAmount) {
        this.lineItemProviderPaymentAmount = lineItemProviderPaymentAmount;
    }

    public String getRevenueCode() {
        return revenueCode;
    }

    public void setRevenueCode(String revenueCode) {
        this.revenueCode = revenueCode;
    }

    public java.math.BigDecimal getPaidUnitsOfService() {
        return paidUnitsOfService;
    }

    public void setPaidUnitsOfService(java.math.BigDecimal paidUnitsOfService) {
        this.paidUnitsOfService = paidUnitsOfService;
    }

    public ProcedureServiceCodeType getSubmittedServiceCodeType() {
        return submittedServiceCodeType;
    }

    public void setSubmittedServiceCodeType(ProcedureServiceCodeType submittedServiceCodeType) {
        this.submittedServiceCodeType = submittedServiceCodeType;
    }

    public String getSubmittedServiceCode() {
        return submittedServiceCode;
    }

    public void setSubmittedServiceCode(String submittedServiceCode) {
        this.submittedServiceCode = submittedServiceCode;
    }

    public String getSubmittedServiceCodeModifier1() {
        return submittedServiceCodeModifier1;
    }

    public void setSubmittedServiceCodeModifier1(String submittedServiceCodeModifier1) {
        this.submittedServiceCodeModifier1 = submittedServiceCodeModifier1;
    }

    public String getSubmittedServiceCodeModifier2() {
        return submittedServiceCodeModifier2;
    }

    public void setSubmittedServiceCodeModifier2(String submittedServiceCodeModifier2) {
        this.submittedServiceCodeModifier2 = submittedServiceCodeModifier2;
    }

    public String getSubmittedServiceCodeModifier3() {
        return submittedServiceCodeModifier3;
    }

    public void setSubmittedServiceCodeModifier3(String submittedServiceCodeModifier3) {
        this.submittedServiceCodeModifier3 = submittedServiceCodeModifier3;
    }

    public String getSubmittedServiceCodeModifier4() {
        return submittedServiceCodeModifier4;
    }

    public void setSubmittedServiceCodeModifier4(String submittedServiceCodeModifier4) {
        this.submittedServiceCodeModifier4 = submittedServiceCodeModifier4;
    }

    public String getSubmittedServiceCodeDescription() {
        return submittedServiceCodeDescription;
    }

    public void setSubmittedServiceCodeDescription(String submittedServiceCodeDescription) {
        this.submittedServiceCodeDescription = submittedServiceCodeDescription;
    }

    public java.math.BigDecimal getOriginalUnitsOfService() {
        return originalUnitsOfService;
    }

    public void setOriginalUnitsOfService(java.math.BigDecimal originalUnitsOfService) {
        this.originalUnitsOfService = originalUnitsOfService;
    }
}