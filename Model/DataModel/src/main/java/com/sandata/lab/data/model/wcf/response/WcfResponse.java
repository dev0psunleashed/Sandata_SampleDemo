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

package com.sandata.lab.data.model.wcf.response;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.wcf.error.WcfError;

import java.util.List;

/**
 * Models the response entity that is received from a WCF Import REST request.
 * <p/>
 *
 * @author David Rutgos
 */
public class WcfResponse extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("Errors")
    private List<WcfError> errors;

    @SerializedName("FailedCount")
    private int failedCount;

    @SerializedName("Status")
    private String status;

    @SerializedName("SucceededCount")
    private int succeededCount;

    public List<WcfError> getErrors() {
        return errors;
    }

    public void setErrors(List<WcfError> errors) {
        this.errors = errors;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSucceededCount() {
        return succeededCount;
    }

    public void setSucceededCount(int succeededCount) {
        this.succeededCount = succeededCount;
    }
}
