package com.sandata.lab.rest.visit.model;

import com.sandata.lab.data.model.base.BaseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Business entity that models some basic business entity attributes, like locations.
 * <p/>
 *
 * @author David Rutgos
 */
public class BusinessObject extends BaseObject {

    private static final long serialVersionUID = 1L;

    private List locations = new ArrayList<>();
    private String bsnEntId = null;
    private String company = null;

    public List getLocations() {
        return locations;
    }

    public void setLocations(List locations) {
        this.locations = locations;
    }

    public String getBsnEntId() {
        return bsnEntId;
    }

    public void setBsnEntId(String bsnEntId) {
        this.bsnEntId = bsnEntId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
