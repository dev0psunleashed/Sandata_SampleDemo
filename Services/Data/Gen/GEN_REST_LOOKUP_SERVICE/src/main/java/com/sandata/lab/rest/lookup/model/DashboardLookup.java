package com.sandata.lab.rest.lookup.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;

public class DashboardLookup extends EnumLookup {

    public DashboardLookup(int id, String type, int min, int max, boolean isDefault, String description) {
        this.id = id;
        this.type = type;
        this.min = min;
        this.max = max;
        this.isDefault = isDefault;
        this.desc = description;

        recordCreated = new Date();
        recordUpdated = new Date();
    }

    @SerializedName("Min")
    private int min;

    @SerializedName("Max")
    private int max;

    @SerializedName("IsDefault")
    private boolean isDefault;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

}
