package com.sandata.lab.data.model.dl.model.custom;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigDecimal;
import java.util.Date;

public class AccountsReceivableAutoPostSummary extends BaseObject {

    private static final long serialVersionUID = 1L;


    @SerializedName("InvoicePaidPostAmount")
    BigDecimal invoicePaidPostAmount;

    @SerializedName("sandataInvoicesPosted")
    int sandataInvoicesPosted;

    @SerializedName("UnappliedBalancePostAmount")
    BigDecimal unappliedBalancePostAmount;

    @SerializedName("UnappliedBalance")
    BigDecimal unappliedBalance;

    @SerializedName("TotalBilled")
    BigDecimal totalBilled;

    @SerializedName("TotalPaid")
    BigDecimal totalPaid;

    @SerializedName("AdjustedAmount")
    BigDecimal adjustedAmount;

    @SerializedName("DeniedAmount")
    BigDecimal deniedAmount;

    @SerializedName("PLBAmount")
    BigDecimal plbAmount;

    @SerializedName("PLBCode")
    String plbCode;

    @SerializedName("PLBDescription")
    String plbDescription;

    public BigDecimal getInvoicePaidPostAmount() {
        return invoicePaidPostAmount;
    }

    public void setInvoicePaidPostAmount(BigDecimal invoicePaidPostAmount) {
        this.invoicePaidPostAmount = invoicePaidPostAmount;
    }

    public int getSandataInvoicesPosted() {
        return sandataInvoicesPosted;
    }

    public void setSandataInvoicesPosted(int sandataInvoicesPosted) {
        this.sandataInvoicesPosted = sandataInvoicesPosted;
    }

    public BigDecimal getUnappliedBalancePostAmount() {
        return unappliedBalancePostAmount;
    }

    public void setUnappliedBalancePostAmount(BigDecimal unappliedBalancePostAmount) {
        this.unappliedBalancePostAmount = unappliedBalancePostAmount;
    }

    public BigDecimal getUnappliedBalance() {
        return unappliedBalance;
    }

    public void setUnappliedBalance(BigDecimal unappliedBalance) {
        this.unappliedBalance = unappliedBalance;
    }

    public BigDecimal getTotalBilled() {
        return totalBilled;
    }

    public void setTotalBilled(BigDecimal totalBilled) {
        this.totalBilled = totalBilled;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public BigDecimal getAdjustedAmount() {
        return adjustedAmount;
    }

    public void setAdjustedAmount(BigDecimal adjustedAmount) {
        this.adjustedAmount = adjustedAmount;
    }

    public BigDecimal getDeniedAmount() {
        return deniedAmount;
    }

    public void setDeniedAmount(BigDecimal deniedAmount) {
        this.deniedAmount = deniedAmount;
    }

    public BigDecimal getPlbAmount() {
        return plbAmount;
    }

    public void setPlbAmount(BigDecimal plbAmount) {
        this.plbAmount = plbAmount;
    }

    public String getPlbCode() {
        return plbCode;
    }

    public void setPlbCode(String plbCode) {
        this.plbCode = plbCode;
    }

    public String getPlbDescription() {
        return plbDescription;
    }

    public void setPlbDescription(String plbDescription) {
        this.plbDescription = plbDescription;
    }


}
