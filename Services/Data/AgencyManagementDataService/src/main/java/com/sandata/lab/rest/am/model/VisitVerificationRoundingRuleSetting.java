package com.sandata.lab.rest.am.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.VisitVerificationRoundingRuleID;
import com.sandata.lab.data.model.dl.model.VisitVerificationRoundingRuleQualifier;

/**
 * Additional settings for VisitVerificationRoundingRuleLkup
 */
public class VisitVerificationRoundingRuleSetting {

    @SerializedName("VisitVerificationRoundingRuleID")
    private VisitVerificationRoundingRuleID visitVerificationRoundingRuleID;

    @SerializedName("VisitVerificationRoundingRuleQualifier")
    private VisitVerificationRoundingRuleQualifier visitVerificationRoundingRuleQualifier;

    @SerializedName("AdditionalSettings")
    private String additionalSettings;

    /**
     * @return the visitVerificationRoundingRuleID
     */
    public VisitVerificationRoundingRuleID getVisitVerificationRoundingRuleID() {
        return visitVerificationRoundingRuleID;
    }

    /**
     * @param visitVerificationRoundingRuleID the visitVerificationRoundingRuleID to set
     */
    public void setVisitVerificationRoundingRuleID(VisitVerificationRoundingRuleID visitVerificationRoundingRuleID) {
        this.visitVerificationRoundingRuleID = visitVerificationRoundingRuleID;
    }

    /**
     * @return the visitVerificationRoundingRuleQualifier
     */
    public VisitVerificationRoundingRuleQualifier getVisitVerificationRoundingRuleQualifier() {
        return visitVerificationRoundingRuleQualifier;
    }

    /**
     * @param visitVerificationRoundingRuleQualifier the visitVerificationRoundingRuleQualifier to set
     */
    public void setVisitVerificationRoundingRuleQualifier(
            VisitVerificationRoundingRuleQualifier visitVerificationRoundingRuleQualifier) {
        this.visitVerificationRoundingRuleQualifier = visitVerificationRoundingRuleQualifier;
    }

    /**
     * @return the additionalSettings
     */
    public String getAdditionalSettings() {
        return additionalSettings;
    }

    /**
     * @param additionalSettings the additionalSettings to set
     */
    public void setAdditionalSettings(String additionalSettings) {
        this.additionalSettings = additionalSettings;
    }
}
