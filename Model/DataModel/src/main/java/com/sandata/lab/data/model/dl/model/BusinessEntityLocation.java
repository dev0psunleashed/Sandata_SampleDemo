package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * Date: 12/14/15
 * Time: 10:32 PM
 */

public class BusinessEntityLocation extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("Location")
    private String location;

    @SerializedName("BusinessEntityID")
    private String businessEntityID;

    @SerializedName("BusinessEntityParentID")
    private String businessEntityParentID;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBusinessEntityID() {
        return businessEntityID;
    }

    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }

    public String getBusinessEntityParentID() {
        return businessEntityParentID;
    }

    public void setBusinessEntityParentID(String businessEntityParentID) {
        this.businessEntityParentID = businessEntityParentID;
    }
}
