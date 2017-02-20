/**
 * 
 */
package com.sandata.lab.rest.dashboard.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;


/**
 * 
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
public class Location extends BaseObject  {

    private static final long serialVersionUID = 1L;
    
    @SerializedName("BusinessEntityName")
    private String businessEntityName;
    
    @SerializedName("BusinessEnityID")
    private String businessEntityId;

    @SerializedName("BusinessEntityLevel")
    private int businessEntityLevel;
    
    @SerializedName("Value")
    private int value;

    /*
    @SerializedName("HasChildren")
    private boolean hasChildren;
*/

    /**
     * Getters and setters
     */
    public String getBusinessEntityName() {
        return businessEntityName;
    }

    public void setBusinessEntityName(String businessEntityName) {
        this.businessEntityName = businessEntityName;
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityID(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
/*
    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
*/
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
