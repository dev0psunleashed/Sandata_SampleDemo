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
 * Models the WCF MedicationStrength class.
 * <p/>
 *
 * @author David Rutgos
 */
public class WcfMedicationStrength extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("MedicationStrengthId")
    private int medicationStrengthId;

    @SerializedName("Name")
    private String name;

    public int getMedicationStrengthId() {
        return medicationStrengthId;
    }

    public void setMedicationStrengthId(int medicationStrengthId) {
        this.medicationStrengthId = medicationStrengthId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
