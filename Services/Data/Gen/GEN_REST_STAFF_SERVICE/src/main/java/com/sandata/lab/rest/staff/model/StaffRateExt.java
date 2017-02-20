package com.sandata.lab.rest.staff.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.ServiceUnitName;
import com.sandata.lab.data.model.dl.model.StaffRate;
import org.springframework.beans.BeanUtils;

public class StaffRateExt extends StaffRate {

    private static final long serialVersionUID = 1L;

    public StaffRateExt(StaffRate staffRate) {
        BeanUtils.copyProperties(staffRate, this);
    }

    @SerializedName("ServiceUnitName")
    private ServiceUnitName serviceUnitName;

    public ServiceUnitName getServiceUnitName() {
        return serviceUnitName;
    }

    public void setServiceUnitName(ServiceUnitName serviceUnitName) {
        this.serviceUnitName = serviceUnitName;
    }
}
