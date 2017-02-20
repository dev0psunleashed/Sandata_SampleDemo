package com.sandata.lab.rest.lookup.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * Models the response for getting all available Federal Tax ID's for a given business entity and its children.
 * <p/>
 *
 * @author David Rutgos
 */
public class BusinessEntityFedTaxLookup extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("BusinessEntityID")
    protected String businessEntityID;

    @SerializedName("BusinessEntityParentID")
    protected String businessEntityParentID;

    @SerializedName("BusinessEntityName")
    protected String businessEntityName;

    @SerializedName("BusinessEntityFederalTaxIdentifier")
    protected String businessEntityFederalTaxIdentifier;

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

    public String getBusinessEntityName() {
        return businessEntityName;
    }

    public void setBusinessEntityName(String businessEntityName) {
        this.businessEntityName = businessEntityName;
    }

    public String getBusinessEntityFederalTaxIdentifier() {
        return businessEntityFederalTaxIdentifier;
    }

    public void setBusinessEntityFederalTaxIdentifier(String businessEntityFederalTaxIdentifier) {
        this.businessEntityFederalTaxIdentifier = businessEntityFederalTaxIdentifier;
    }
}
