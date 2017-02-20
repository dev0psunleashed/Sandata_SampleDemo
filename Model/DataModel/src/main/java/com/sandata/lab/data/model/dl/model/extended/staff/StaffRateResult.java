package com.sandata.lab.data.model.dl.model.extended.staff;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.StaffRate;

import java.util.List;

/**
 * Entity that combines the StaffRate and StaffAssociatedRate.
 * <p/>
 *
 * @author David Rutgos
 */
public class StaffRateResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffAssociatedRates")
    protected List<StaffAssociatedRateExt> staffAssociatedRateExtList;

    @SerializedName("StaffRates")
    protected List<StaffRate> staffRateList;

    public List<StaffAssociatedRateExt> getStaffAssociatedRateExtList() {
        return staffAssociatedRateExtList;
    }

    public void setStaffAssociatedRateExtList(List<StaffAssociatedRateExt> staffAssociatedRateExtList) {
        this.staffAssociatedRateExtList = staffAssociatedRateExtList;
    }

    public List<StaffRate> getStaffRateList() {
        return staffRateList;
    }

    public void setStaffRateList(List<StaffRate> staffRateList) {
        this.staffRateList = staffRateList;
    }
}
