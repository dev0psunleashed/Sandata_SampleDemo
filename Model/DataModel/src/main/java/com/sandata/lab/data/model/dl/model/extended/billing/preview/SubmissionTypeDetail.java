package com.sandata.lab.data.model.dl.model.extended.billing.preview;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.InvoiceSubmissionTypeName;

import java.util.List;

/**
 * Models the invoice data that is select by the user to submit for payment by Submission Type.
 *
 * UI: https://projects.invisionapp.com/share/RK7ZVP4V9#/screens/175450631
 * <p/>
 *
 * @author David Rutgos
 */
public class SubmissionTypeDetail extends BaseObject {

    private static final long serialVersionUID = 1L;

    // https://mnt-ers-ts01.sandata.com/object/view.spg?key=154792
    @SerializedName("InvoiceSubmissionTypeName")
    protected InvoiceSubmissionTypeName invoiceSubmissionTypeName;

    @SerializedName("PayerDetail")
    protected List<PayerDetail> payerDetail;

    public InvoiceSubmissionTypeName getInvoiceSubmissionTypeName() {
        return invoiceSubmissionTypeName;
    }

    public void setInvoiceSubmissionTypeName(InvoiceSubmissionTypeName invoiceSubmissionTypeName) {
        this.invoiceSubmissionTypeName = invoiceSubmissionTypeName;
    }

    public List<PayerDetail> getPayerDetail() {
        return payerDetail;
    }

    public void setPayerDetail(List<PayerDetail> payerDetail) {
        this.payerDetail = payerDetail;
    }
}
