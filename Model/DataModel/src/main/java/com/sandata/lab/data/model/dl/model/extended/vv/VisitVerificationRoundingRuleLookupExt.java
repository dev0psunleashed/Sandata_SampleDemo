package com.sandata.lab.data.model.dl.model.extended.vv;

import org.springframework.beans.BeanUtils;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.VisitVerificationRoundingRuleLookup;

public class VisitVerificationRoundingRuleLookupExt extends VisitVerificationRoundingRuleLookup {

    private static final long serialVersionUID = 1784262627392282466L;

    @SerializedName("AdditionalSettings")
    protected String additionalSettings;

    /**
     * Default constructor
     */
    public VisitVerificationRoundingRuleLookupExt() {

    }

    /**
     * Constructor
     * @param visitVerificationRoundingRuleLookup
     */
    public VisitVerificationRoundingRuleLookupExt(VisitVerificationRoundingRuleLookup visitVerificationRoundingRuleLookup) {
        BeanUtils.copyProperties(visitVerificationRoundingRuleLookup, this);
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

    /**
     * @return VisitVerificationRoundingRuleLookup object
     */
    public VisitVerificationRoundingRuleLookup getVisitVerificationRoundingRuleLookup() {
        VisitVerificationRoundingRuleLookup visitVerificationRoundingRuleLookup = new VisitVerificationRoundingRuleLookup();
        BeanUtils.copyProperties(this, visitVerificationRoundingRuleLookup);

        return visitVerificationRoundingRuleLookup;
    }
}
