/**
 * 
 */
package com.sandata.lab.data.model.dl.model.extended.staff;


import org.springframework.beans.BeanUtils;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.StaffCompliance;

/**
 * @author huyvo
 *
 */
public class StaffComplianceExt extends StaffCompliance {
    private static final long serialVersionUID = 1L;
    
    @SerializedName("ComplianceCategoryName")
    private String complianceCategoryName;

    @SerializedName("ComplianceName")
    private String complianceName;



    @SerializedName("IsCompliant")
    private boolean isCompliant;
    
    public StaffComplianceExt(StaffCompliance staffCompliance) {
        BeanUtils.copyProperties(staffCompliance, this);
    }

    /**
     * @return the complianceCategoryName
     */
    public String getComplianceCategoryName() {
        return complianceCategoryName;
    }

    /**
     * @param complianceCategoryName the complianceCategoryName to set
     */
    public void setComplianceCategoryName(String complianceCategoryName) {
        this.complianceCategoryName = complianceCategoryName;
    }

    public String getComplianceName() {
        return complianceName;
    }

    public void setComplianceName(String complianceName) {
        this.complianceName = complianceName;
    }

    public boolean isCompliant() {
        return isCompliant;
    }

    public void setCompliant(boolean compliant) {
        isCompliant = compliant;
    }
}
