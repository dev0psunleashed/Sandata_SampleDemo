package com.sandata.lab.dl.loc.services.google.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.util.List;

public class GeoCodeResult extends BaseObject {

    @SerializedName("geometry")
    private Geometry  geometry;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
