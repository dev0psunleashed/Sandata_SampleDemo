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
 * Models the WCF Agency class.
 * <p/>
 *
 * @author David Rutgos
 */
public class WcfAgency extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("AgencyId")
    private int agencyId;

    @SerializedName("Name")
    private String name;

    @SerializedName("Logo")
    private String logo;

    @SerializedName("Number")
    private String number;

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
