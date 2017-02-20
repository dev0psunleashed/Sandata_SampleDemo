package com.sandata.lab.data.model.dl.model.extended.compliance;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.BusinessEntityComplianceRelationship;
import org.springframework.beans.BeanUtils;

/**
 * <p>BusinessEntityComplianceRelationshipExt</p>
 * <p>Date: 07/15/2016</p>
 * <p>Time: 2:29 PM</p>
 *
 * @author jasonscott
 */
public class BusinessEntityComplianceRelationshipExt extends BusinessEntityComplianceRelationship {

    private static final long serialVersionUID = 1L;

    @SerializedName("ComplianceName")
    protected String complianceName;

    @SerializedName("ParentComplianceName")
    protected String parentComplianceName;

    public BusinessEntityComplianceRelationshipExt() {
    }

    public BusinessEntityComplianceRelationshipExt(BusinessEntityComplianceRelationship businessEntityComplianceRelationship) {
        BeanUtils.copyProperties(businessEntityComplianceRelationship, this);
        this.getBusinessEntityComplianceRelationshipDetail().addAll(businessEntityComplianceRelationship.getBusinessEntityComplianceRelationshipDetail());
    }

    public String getComplianceName() {
        return complianceName;
    }

    public void setComplianceName(String complianceName) {
        this.complianceName = complianceName;
    }

    public String getParentComplianceName() {
        return parentComplianceName;
    }

    public void setParentComplianceName(String parentComplianceName) {
        this.parentComplianceName = parentComplianceName;
    }
}
