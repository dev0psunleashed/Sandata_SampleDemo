package com.sandata.lab.data.model.payer;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * <p>PayerAdditionalSettings.java</p>
 * <p>Additional settings for a payer.</p>
 * <p>Date: 6/9/2016</p>
 * <p>Time: 3:46 PM</p>
 *
 * @author jasonscott
 */
public class PayerAdditionalSettings extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("IncludeAgencyRemitAddress")
    protected boolean includeAgencyRemitAddress;

    @SerializedName("UseScheduledTimesForBillingAndPayrollTimes")
    protected boolean useScheduledTimesForBillingAndPayrollTimes;

    @SerializedName("RequiresAuthorizationId")
    protected boolean requiresAuthorizationId;

    @SerializedName("RequiresPhysicianNpiOnInvoice")
    protected boolean requiresPhysicianNpiOnInvoice;

    @SerializedName("SplitInvoicesByServiceOnPaperClaims")
    protected boolean splitInvoicesByServiceOnPaperClaims;

    @SerializedName("AssignClaimReferenceNumOnInvoice")
    protected boolean assignClaimReferenceNumOnInvoice;

    @SerializedName("IcdCodeRequiredForBilling")
    protected boolean icdCodeRequiredForBilling;

    public boolean isIncludeAgencyRemitAddress() {
        return includeAgencyRemitAddress;
    }

    public void setIncludeAgencyRemitAddress(boolean includeAgencyRemitAddress) {
        this.includeAgencyRemitAddress = includeAgencyRemitAddress;
    }

    public boolean isUseScheduledTimesForBillingAndPayrollTimes() {
        return useScheduledTimesForBillingAndPayrollTimes;
    }

    public void setUseScheduledTimesForBillingAndPayrollTimes(boolean useScheduledTimesForBillingAndPayrollTimes) {
        this.useScheduledTimesForBillingAndPayrollTimes = useScheduledTimesForBillingAndPayrollTimes;
    }

    public boolean isRequiresAuthorizationId() {
        return requiresAuthorizationId;
    }

    public void setRequiresAuthorizationId(boolean requiresAuthorizationId) {
        this.requiresAuthorizationId = requiresAuthorizationId;
    }

    public boolean isRequiresPhysicianNpiOnInvoice() {
        return requiresPhysicianNpiOnInvoice;
    }

    public void setRequiresPhysicianNpiOnInvoice(boolean requiresPhysicianNpiOnInvoice) {
        this.requiresPhysicianNpiOnInvoice = requiresPhysicianNpiOnInvoice;
    }

    public boolean isSplitInvoicesByServiceOnPaperClaims() {
        return splitInvoicesByServiceOnPaperClaims;
    }

    public void setSplitInvoicesByServiceOnPaperClaims(boolean splitInvoicesByServiceOnPaperClaims) {
        this.splitInvoicesByServiceOnPaperClaims = splitInvoicesByServiceOnPaperClaims;
    }

    public boolean isAssignClaimReferenceNumOnInvoice() {
        return assignClaimReferenceNumOnInvoice;
    }

    public void setAssignClaimReferenceNumOnInvoice(boolean assignClaimReferenceNumOnInvoice) {
        this.assignClaimReferenceNumOnInvoice = assignClaimReferenceNumOnInvoice;
    }

    public boolean isIcdCodeRequiredForBilling() {
        return icdCodeRequiredForBilling;
    }

    public void setIcdCodeRequiredForBilling(boolean icdCodeRequiredForBilling) {
        this.icdCodeRequiredForBilling = icdCodeRequiredForBilling;
    }
}
