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
 * Models the WCF Eligibility class.
 * <p/>
 *
 * @author David Rutgos
 */
public class WcfEligibility extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("EligibilityId")
    private int eligibilityId;

    @SerializedName("Name")
    private String name;

    public int getEligibilityId() {
        return eligibilityId;
    }

    public void setEligibilityId(int eligibilityId) {
        this.eligibilityId = eligibilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
