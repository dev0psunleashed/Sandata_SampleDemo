package com.sandata.lab.data.model.dl.model.extended.billing.preview;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Models the invoice data that is select by the user to submit for payment by Payer.
 *
 * UI: https://projects.invisionapp.com/share/RK7ZVP4V9#/screens/175450631
 * <p/>
 *
 * @author David Rutgos
 */
public class PayerDetail extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("PayerID")
    private String payerId;

    @SerializedName("PayerName")
    private String payerName;

    @SerializedName("TotalInvoicesBilled")
    protected BigInteger totalInvoicesBilled;

    @SerializedName("TotalAmountBilled")
    protected BigDecimal totalAmountBilled;

    @SerializedName("BilledHours")
    protected BigInteger billedHours;

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public BigInteger getTotalInvoicesBilled() {
        return totalInvoicesBilled;
    }

    public void setTotalInvoicesBilled(BigInteger totalInvoicesBilled) {
        this.totalInvoicesBilled = totalInvoicesBilled;
    }

    public BigDecimal getTotalAmountBilled() {
        return totalAmountBilled;
    }

    public void setTotalAmountBilled(BigDecimal totalAmountBilled) {
        this.totalAmountBilled = totalAmountBilled;
    }

    public BigInteger getBilledHours() {
        return billedHours;
    }

    public void setBilledHours(BigInteger billedHours) {
        this.billedHours = billedHours;
    }
}
