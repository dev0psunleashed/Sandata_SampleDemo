package com.sandata.lab.rest.staff.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.ServiceUnitName;
import com.sandata.lab.data.model.dl.model.StaffAssociatedRate;
import org.springframework.beans.BeanUtils;

public class StaffAssocRateExt extends StaffAssociatedRate {

    private static final long serialVersionUID = 1L;

    @SerializedName("ServiceUnitName")
    private ServiceUnitName serviceUnitName;

    public StaffAssocRateExt() {
    }

    public StaffAssocRateExt(StaffAssociatedRate staffAssociatedRate) {
        BeanUtils.copyProperties(staffAssociatedRate, this);
    }

    public ServiceUnitName getServiceUnitName() {
        return serviceUnitName;
    }

    public void setServiceUnitName(ServiceUnitName serviceUnitName) {
        this.serviceUnitName = serviceUnitName;
    }
}
