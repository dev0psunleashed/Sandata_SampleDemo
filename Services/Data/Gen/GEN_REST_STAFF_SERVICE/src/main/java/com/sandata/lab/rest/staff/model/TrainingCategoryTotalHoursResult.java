package com.sandata.lab.rest.staff.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigDecimal;

/**
 * Created by khangle on 10/07/2016.
 */
public class TrainingCategoryTotalHoursResult extends BaseObject {
    private static final long serialVersionUID = 1L;

    @SerializedName("StaffTrainingCategoryCode")
    private String staffTrainingCategoryCode;

    @SerializedName("StaffTrainingCategoryName")
    private String staffTrainingCategoryName;

    @SerializedName("CategoryTotalHours")
    private BigDecimal categoryTotalHours;

    @SerializedName("CategoryEnrolledTotalHours")
    private BigDecimal categoryEnrolledTotalHours;

    public String getStaffTrainingCategoryCode() {
        return staffTrainingCategoryCode;
    }

    public void setStaffTrainingCategoryCode(String staffTrainingCategoryCode) {
        this.staffTrainingCategoryCode = staffTrainingCategoryCode;
    }

    public String getStaffTrainingCategoryName() {
        return staffTrainingCategoryName;
    }

    public void setStaffTrainingCategoryName(String staffTrainingCategoryName) {
        this.staffTrainingCategoryName = staffTrainingCategoryName;
    }

    public BigDecimal getCategoryTotalHours() {
        return categoryTotalHours;
    }

    public void setCategoryTotalHours(BigDecimal categoryTotalHours) {
        this.categoryTotalHours = categoryTotalHours;
    }

    public BigDecimal getCategoryEnrolledTotalHours() {
        return categoryEnrolledTotalHours;
    }

    public void setCategoryEnrolledTotalHours(BigDecimal categoryEnrolledTotalHours) {
        this.categoryEnrolledTotalHours = categoryEnrolledTotalHours;
    }
}
