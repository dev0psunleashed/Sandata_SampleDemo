package com.sandata.lab.data.model.data;

import com.sandata.lab.data.model.base.BaseObject;

/**
 * Date: 9/5/16
 * Time: 8:20 PM
 */

public class CompareResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String dataPoint;
    private String originalValue;
    private String updatedValue;

    public String getDataPoint() {
        return dataPoint;
    }

    public void setDataPoint(String dataPoint) {
        this.dataPoint = dataPoint;
    }

    public String getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(String originalValue) {
        this.originalValue = originalValue;
    }

    public String getUpdatedValue() {
        return updatedValue;
    }

    public void setUpdatedValue(String updatedValue) {
        this.updatedValue = updatedValue;
    }
}
