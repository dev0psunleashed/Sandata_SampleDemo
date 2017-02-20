package com.sandata.lab.rest.staff.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.util.List;

/**
 * Entity that combines the StaffRate and StaffAssociatedRate.
 * <p/>
 *
 * @author David Rutgos
 */
public class StaffRateResultExt extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffAssociatedRates")
    protected List<StaffAssociatedRateExt> staffAssociatedRateExtList;

    @SerializedName("StaffRates")
    protected List<StaffRateExt> staffRateList;

    public List<StaffAssociatedRateExt> getStaffAssociatedRateExtList() {
        return staffAssociatedRateExtList;
    }

    public void setStaffAssociatedRateExtList(List<StaffAssociatedRateExt> staffAssociatedRateExtList) {
        this.staffAssociatedRateExtList = staffAssociatedRateExtList;
    }

    public List<StaffRateExt> getStaffRateList() {
        return staffRateList;
    }

    public void setStaffRateList(List<StaffRateExt> staffRateList) {
        this.staffRateList = staffRateList;
    }
}
