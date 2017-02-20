package com.sandata.lab.data.model.dl.model.extended.billing.preview;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.InvoiceDetail;
import com.sandata.lab.data.model.dl.model.find.InvoiceFindResult;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Models the invoice data that is select by the user to submit for payment.
 *
 * UI: https://projects.invisionapp.com/share/RK7ZVP4V9#/screens/175450631
 * <p/>
 *
 * @author David Rutgos
 */
public class BillingPreviewSummary extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("TransactionID")
    protected String transactionId;

    @SerializedName("SubmissionDate")
    protected Date submissionDate;

    @SerializedName("TotalInvoicesBilled")
    protected BigInteger totalInvoicesBilled;

    @SerializedName("TotalAmountBilled")
    protected BigDecimal totalAmountBilled;

    @SerializedName("BilledHours")
    protected BigInteger billedHours;

    @SerializedName("LineOfBusinessDetail")
    protected List<LineOfBusinessDetail> lineOfBusinessDetail;

    @SerializedName("PayerDetail")
    protected List<PayerDetail> payerDetail;

    @SerializedName("SubmissionTypeDetail")
    protected List<SubmissionTypeDetail> submissionTypeDetail;

    @SerializedName("Invoices")
    protected List<InvoiceFindResult> invoices;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
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

    public List<LineOfBusinessDetail> getLineOfBusinessDetail() {
        return lineOfBusinessDetail;
    }

    public void setLineOfBusinessDetail(List<LineOfBusinessDetail> lineOfBusinessDetail) {
        this.lineOfBusinessDetail = lineOfBusinessDetail;
    }

    public List<PayerDetail> getPayerDetail() {
        return payerDetail;
    }

    public void setPayerDetail(List<PayerDetail> payerDetail) {
        this.payerDetail = payerDetail;
    }

    public List<SubmissionTypeDetail> getSubmissionTypeDetail() {
        return submissionTypeDetail;
    }

    public void setSubmissionTypeDetail(List<SubmissionTypeDetail> submissionTypeDetail) {
        this.submissionTypeDetail = submissionTypeDetail;
    }

    public List<InvoiceFindResult> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<InvoiceFindResult> invoices) {
        this.invoices = invoices;
    }
}
