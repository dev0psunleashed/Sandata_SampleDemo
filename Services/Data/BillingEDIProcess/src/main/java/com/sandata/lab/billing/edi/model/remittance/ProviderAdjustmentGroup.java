package com.sandata.lab.billing.edi.model.remittance;

import java.util.*;

public class ProviderAdjustmentGroup {
    private long id;
    private long transactionId;
    private String npi;
    private Date fiscalDate = new Date(0);

    private Collection<ProviderAdjustment> adjustments = new ArrayList<ProviderAdjustment>();

    public final Collection<ProviderAdjustment> getAdjustments() {
        return adjustments;
    }

    public final void setAdjustments(Collection<ProviderAdjustment> value) {
        adjustments = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getNpi() {
        return npi;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    public Date getFiscalDate() {
        return fiscalDate;
    }

    public void setFiscalDate(Date fiscalDate) {
        this.fiscalDate = fiscalDate;
    }
}