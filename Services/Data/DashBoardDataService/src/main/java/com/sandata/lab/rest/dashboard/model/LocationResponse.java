/**
 * 
 */
package com.sandata.lab.rest.dashboard.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;


/**
 * Create a Locations response object that will be set on the "data" property of the "Commons" Response object"
 * 
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 7, 2016
 * 
 * 
 */
public class LocationResponse extends BaseObject  {

   
    private static final long serialVersionUID = 1L;
    
    @SerializedName("BusinessEntityID")
    private String businessEntityId;

    @SerializedName("BusinessEntityLevel")
    private int businessEntityLevel;
    
    @SerializedName("Value")
    private int value;
    
    @SerializedName("Locations")
    private List<Location> locations;

    /**
     * Getters and setters
     */
    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getBusinessEntityLevel() {
        return businessEntityLevel;
    }

    public void setBusinessEntityLevel(int businessEntityLevel) {
        this.businessEntityLevel = businessEntityLevel;
    }
}
