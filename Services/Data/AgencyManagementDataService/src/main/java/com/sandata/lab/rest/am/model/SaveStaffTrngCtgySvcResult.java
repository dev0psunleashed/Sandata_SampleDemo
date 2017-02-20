package com.sandata.lab.rest.am.model;

import com.google.gson.annotations.SerializedName;

public class SaveStaffTrngCtgySvcResult {

	private static final long serialVersionUID = 1L;

    @SerializedName("StaffTrainingCategoryServiceSK")
    private long staffTrainingCategoryServiceSK;

    @SerializedName("Index")
    private int index;

    @SerializedName("ErrorMessage")
    private String errorMessage;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

	public long getStaffTrainingCategoryServiceSK() {
		return staffTrainingCategoryServiceSK;
	}

	public void setStaffTrainingCategoryServiceSK(long staffTrainingCategoryServiceSK) {
		this.staffTrainingCategoryServiceSK = staffTrainingCategoryServiceSK;
	}
    
}
