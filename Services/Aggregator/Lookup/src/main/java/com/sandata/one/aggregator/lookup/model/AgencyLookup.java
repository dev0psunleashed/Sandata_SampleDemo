package com.sandata.one.aggregator.lookup.model;

import com.google.gson.annotations.SerializedName;

public class AgencyLookup {

    @SerializedName("BusinessEntityID")
    private String businessEntityID;

    @SerializedName("BusinessEntityName")
    private String businessEntityName;

    @SerializedName("BusinessEntityLegalName")
    protected String businessEntityLegalName;

    /**
     * @return the businessEntityID
     */
    public String getBusinessEntityID() {
        return businessEntityID;
    }

    /**
     * @param businessEntityID the businessEntityID to set
     */
    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }

    /**
     * @return the businessEntityName
     */
    public String getBusinessEntityName() {
        return businessEntityName;
    }

    /**
     * @param businessEntityName the businessEntityName to set
     */
    public void setBusinessEntityName(String businessEntityName) {
        this.businessEntityName = businessEntityName;
    }

    /**
     * @return the businessEntityLegalName
     */
    public String getBusinessEntityLegalName() {
        return businessEntityLegalName;
    }

    /**
     * @param businessEntityLegalName the businessEntityLegalName to set
     */
    public void setBusinessEntityLegalName(String businessEntityLegalName) {
        this.businessEntityLegalName = businessEntityLegalName;
    }
}
