package com.sandata.lab.data.model.dl.model.extended.compliance;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.BusinessEntityComplianceLookup;

/**
 * <p>BusinessEntityComplianceLookupExt</p>
 * <p>Date: 08/16/2016</p>
 * <p>Time: 2:29 PM</p>
 *
 * @author jasonscott
 */
public class BusinessEntityComplianceLookupExt extends BusinessEntityComplianceLookup {

    private static final long serialVersionUID = 1L;

    @SerializedName("ComplianceCategoryName")
    private String complianceCategoryName;

    public String getComplianceCategoryName() {
        return complianceCategoryName;
    }

    public void setComplianceCategoryName(String complianceCategoryName) {
        this.complianceCategoryName = complianceCategoryName;
    }
}
