package com.sandata.lab.dl.loc.services.google.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeoCodeResponse {

    @SerializedName("results")
    List<GeoCodeResult> geoCodeResultList;
}
