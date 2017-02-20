package com.sandata.lab.data.model.dl.model.extended.training;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.BusinessEntityStaffTrainingRelationship;
import org.springframework.beans.BeanUtils;

/**
 * <p>BusinessEntityStaffTrainingRelationshipExt</p>
 * <p>Date: 07/15/2016</p>
 * <p>Time: 2:29 PM</p>
 *
 * @author jasonscott
 */
public class BusinessEntityStaffTrainingRelationshipExt extends BusinessEntityStaffTrainingRelationship {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffTrainingName")
    protected String staffTrainingName;

    @SerializedName("ParentStaffTrainingName")
    protected String parentStaffTrainingName;

    public BusinessEntityStaffTrainingRelationshipExt() {
    }

    public BusinessEntityStaffTrainingRelationshipExt(BusinessEntityStaffTrainingRelationship businessEntityStaffTrainingRelationship) {
        BeanUtils.copyProperties(businessEntityStaffTrainingRelationship, this);
        this.getBusinessEntityStaffTrainingRelationshipDetail().addAll(businessEntityStaffTrainingRelationship.getBusinessEntityStaffTrainingRelationshipDetail());
    }

    public String getStaffTrainingName() {
        return staffTrainingName;
    }

    public void setStaffTrainingName(String staffTrainingName) {
        this.staffTrainingName = staffTrainingName;
    }

    public String getParentStaffTrainingName() {
        return parentStaffTrainingName;
    }

    public void setParentStaffTrainingName(String parentStaffTrainingName) {
        this.parentStaffTrainingName = parentStaffTrainingName;
    }
}
