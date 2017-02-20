/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.data.model.wcf.lookup;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * Models the WCF StaffClass class.
 * <p/>
 *
 * @author David Rutgos
 */
public class WcfStaffClass extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffClassId")
    private int staffClassId;

    @SerializedName("Name")
    private String name;

    public int getStaffClassId() {
        return staffClassId;
    }

    public void setStaffClassId(int staffClassId) {
        this.staffClassId = staffClassId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
