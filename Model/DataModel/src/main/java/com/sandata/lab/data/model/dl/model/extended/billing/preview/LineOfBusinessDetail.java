package com.sandata.lab.data.model.dl.model.extended.billing.preview;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Models the invoice data that is select by the user to submit for payment by Line Of Business.
 *
 * UI: https://projects.invisionapp.com/share/RK7ZVP4V9#/screens/175450631
 * <p/>
 *
 * @author David Rutgos
 */
public class LineOfBusinessDetail extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("LineOfBusiness")
    protected String lineOfBusiness;

    @SerializedName("TotalInvoicesBilled")
    protected BigInteger totalInvoicesBilled;

    @SerializedName("TotalAmountBilled")
    protected BigDecimal totalAmountBilled;

    @SerializedName("BilledHours")
    protected BigInteger billedHours;

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
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
