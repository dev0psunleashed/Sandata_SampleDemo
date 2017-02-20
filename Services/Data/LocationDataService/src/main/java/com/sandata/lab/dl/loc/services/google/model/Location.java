package com.sandata.lab.dl.loc.services.google.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

public class Location extends BaseObject {

    @SerializedName("lat")
    private String Latitude;

    @SerializedName("lng")
    private String Longitude;
}
