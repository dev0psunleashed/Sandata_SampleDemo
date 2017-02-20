package com.sandata.lab.billing.edi.model.remittance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.sandata.lab.billing.edi.model.enums.AccountNumberTypeCodeType;
import com.sandata.lab.billing.edi.model.enums.CreditDebitFlagCodeType;
import com.sandata.lab.billing.edi.model.enums.DFIIdTypeCodeType;
import com.sandata.lab.billing.edi.model.enums.PaymentFormatCodeType;
import com.sandata.lab.billing.edi.model.enums.PaymentMethodCodeType;
import com.sandata.lab.billing.edi.model.enums.RemittanceHandlingCodeType;

public class Transaction {
    private long id;
    private long responseMessageId;
    private RemittanceHandlingCodeType remittanceHandlingCode;
    private CreditDebitFlagCodeType creditOrDebit;
    private PaymentMethodCodeType paymentMethod;
    private PaymentFormatCodeType paymentFormat;
    private DFIIdTypeCodeType senderDFIType;
    private String senderDFI;
    private AccountNumberTypeCodeType senderAccountType;
    private String senderAccountNumber;
    private DFIIdTypeCodeType receiverDFIType;
    private String receiverDFI;
    private AccountNumberTypeCodeType receiverAccountType;
    private String receiverAccountNumber;
    private Date paymentEffectiveDate = new Date(0);
    private String checkOrEFTNumber;
    private String payerId;
    private String payerSupplementalId;
    private Date productionDate = new Date(0);
    private String currencyCode;
    private java.math.BigDecimal amount = new java.math.BigDecimal(0);

    private Payer payer;
    private Payee payee;

    private Collection<TransactionAdditionalId> additionalIds = new ArrayList<TransactionAdditionalId>();

    public final Collection<TransactionAdditionalId> getAdditionalIds() {
        return additionalIds;
    }

    public final void setAdditionalIds(Collection<TransactionAdditionalId> value) {
        additionalIds = value;
    }

    private Collection<ClaimPaymentGroup> claimPaymentGroups = new ArrayList<ClaimPaymentGroup>();

    public final Collection<ClaimPaymentGroup> getClaimPaymentGroups() {
        return claimPaymentGroups;
    }

    public final void setClaimPaymentGroups(Collection<ClaimPaymentGroup> value) {
        claimPaymentGroups = value;
    }

    private Collection<ProviderAdjustmentGroup> providerAdjustmentGroups = new ArrayList<ProviderAdjustmentGroup>();

    public final Collection<ProviderAdjustmentGroup> getProviderAdjustmentGroups() {
        return providerAdjustmentGroups;
    }

    public final void setProviderAdjustmentGroups(Collection<ProviderAdjustmentGroup> value) {
        providerAdjustmentGroups = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getResponseMessageId() {
        return responseMessageId;
    }

    public void setResponseMessageId(long responseMessageId) {
        this.responseMessageId = responseMessageId;
    }

    public RemittanceHandlingCodeType getRemittanceHandlingCode() {
        return remittanceHandlingCode;
    }

    public void setRemittanceHandlingCode(RemittanceHandlingCodeType remittanceHandlingCode) {
        this.remittanceHandlingCode = remittanceHandlingCode;
    }

    public CreditDebitFlagCodeType getCreditOrDebit() {
        return creditOrDebit;
    }

    public void setCreditOrDebit(CreditDebitFlagCodeType creditOrDebit) {
        this.creditOrDebit = creditOrDebit;
    }

    public PaymentMethodCodeType getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodCodeType paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentFormatCodeType getPaymentFormat() {
        return paymentFormat;
    }

    public void setPaymentFormat(PaymentFormatCodeType paymentFormat) {
        this.paymentFormat = paymentFormat;
    }

    public DFIIdTypeCodeType getSenderDFIType() {
        return senderDFIType;
    }

    public void setSenderDFIType(DFIIdTypeCodeType senderDFIType) {
        this.senderDFIType = senderDFIType;
    }

    public String getSenderDFI() {
        return senderDFI;
    }

    public void setSenderDFI(String senderDFI) {
        this.senderDFI = senderDFI;
    }

    public AccountNumberTypeCodeType getSenderAccountType() {
        return senderAccountType;
    }

    public void setSenderAccountType(AccountNumberTypeCodeType senderAccountType) {
        this.senderAccountType = senderAccountType;
    }

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public DFIIdTypeCodeType getReceiverDFIType() {
        return receiverDFIType;
    }

    public void setReceiverDFIType(DFIIdTypeCodeType receiverDFIType) {
        this.receiverDFIType = receiverDFIType;
    }

    public String getReceiverDFI() {
        return receiverDFI;
    }

    public void setReceiverDFI(String receiverDFI) {
        this.receiverDFI = receiverDFI;
    }

    public AccountNumberTypeCodeType getReceiverAccountType() {
        return receiverAccountType;
    }

    public void setReceiverAccountType(AccountNumberTypeCodeType receiverAccountType) {
        this.receiverAccountType = receiverAccountType;
    }

    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public Date getPaymentEffectiveDate() {
        return paymentEffectiveDate;
    }

    public void setPaymentEffectiveDate(Date paymentEffectiveDate) {
        this.paymentEffectiveDate = paymentEffectiveDate;
    }

    public String getCheckOrEFTNumber() {
        return checkOrEFTNumber;
    }

    public void setCheckOrEFTNumber(String checkOrEFTNumber) {
        this.checkOrEFTNumber = checkOrEFTNumber;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayerSupplementalId() {
        return payerSupplementalId;
    }

    public void setPayerSupplementalId(String payerSupplementalId) {
        this.payerSupplementalId = payerSupplementalId;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public java.math.BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Payee getPayee() {
        return payee;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }
}