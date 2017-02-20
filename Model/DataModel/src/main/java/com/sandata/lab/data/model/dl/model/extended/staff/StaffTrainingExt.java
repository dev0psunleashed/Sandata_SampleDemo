package com.sandata.lab.data.model.dl.model.extended.staff;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.BusinessEntityStaffTrainingCategoryLookup;
import com.sandata.lab.data.model.dl.model.StaffTraining;

/**
 * Extends the Staff Training entity to provide the UI with some more properties.
 */
public class StaffTrainingExt extends StaffTraining {
    private static final long serialVersionUID = 7545833277685723307L;

    public StaffTrainingExt(StaffTraining staffTraining) {
        BeanUtils.copyProperties(staffTraining, this);
    }

    @SerializedName("StaffTrainingCodeDescription")
    private String staffTrainingCodeDescription;

    @SerializedName("StaffTrainingName")
    private String staffTrainingName;
    
    @SerializedName("StaffTrainingCategory")
    private List<BusinessEntityStaffTrainingCategoryLookup> businessEntityStaffTrainingCategoryLookup;

    /**
     * @return the staffTrainingCodeDescription
     */
    public String getStaffTrainingCodeDescription() {
        return staffTrainingCodeDescription;
    }

    /**
     * @param staffTrainingCodeDescription the staffTrainingCodeDescription to set
     */
    public void setStaffTrainingCodeDescription(String staffTrainingCodeDescription) {
        this.staffTrainingCodeDescription = staffTrainingCodeDescription;
    }

    /**
     * @return the staffTrainingName
     */
    public String getStaffTrainingName() {
        return staffTrainingName;
    }

    /**
     * @param staffTrainingName the staffTrainingName to set
     */
    public void setStaffTrainingName(String staffTrainingName) {
        this.staffTrainingName = staffTrainingName;
    }
    
    public List<BusinessEntityStaffTrainingCategoryLookup> getBusinessEntityStaffTrainingCategoryLookup() {
        if (businessEntityStaffTrainingCategoryLookup == null) {
            businessEntityStaffTrainingCategoryLookup = new ArrayList<BusinessEntityStaffTrainingCategoryLookup>();
        }
        return businessEntityStaffTrainingCategoryLookup;
    }
}
