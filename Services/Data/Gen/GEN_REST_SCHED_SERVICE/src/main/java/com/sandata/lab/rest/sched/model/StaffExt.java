package com.sandata.lab.rest.sched.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.Staff;
import com.sandata.lab.data.model.dl.model.StaffContactPhone;

/**
 * Extends the Staff entity to provide the UI with some more properties.
 * 
 * @author khangle
 */
public class StaffExt extends Staff {

    private static final long serialVersionUID = 1L;
    
    @SerializedName("StaffContactPhone")
    private List<StaffContactPhone> staffContactPhone;
    
    /**
     * Default constructor
     */
    public StaffExt() {
        
    }

    /**
     * Constructor to copy properties from Staff
     * 
     * @param staff
     */
    public StaffExt(Staff staff) {
        BeanUtils.copyProperties(staff, this);
    }
    
    public List<StaffContactPhone> getStaffContactPhone() {
        if (staffContactPhone == null) {
            staffContactPhone = new ArrayList<StaffContactPhone>();
        }
        return this.staffContactPhone;
    }
}
