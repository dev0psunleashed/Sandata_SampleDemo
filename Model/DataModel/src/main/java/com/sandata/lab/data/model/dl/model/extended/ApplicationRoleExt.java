package com.sandata.lab.data.model.dl.model.extended;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.ApplicationModule;
import com.sandata.lab.data.model.dl.model.ApplicationRole;
import com.sandata.lab.data.model.dl.model.ApplicationSecureGroup;

import java.util.List;

public class ApplicationRoleExt extends ApplicationRole {

    private static final long serialVersionUID = 1L;

    @SerializedName("BusinessEntityID")
    private String businessEntityID;

    @SerializedName("ApplicationModule")
    private List<ApplicationModuleExt> applicationModules;

    @SerializedName("ApplicationSecureGroup")
    private List<ApplicationSecureGroup> applicationSecureGroups;

    public List<ApplicationSecureGroup> getApplicationSecureGroups() {
        return applicationSecureGroups;
    }

    public void setApplicationSecureGroups(List<ApplicationSecureGroup> applicationSecureGroups) {
        this.applicationSecureGroups = applicationSecureGroups;
    }

    public List<ApplicationModuleExt> getApplicationModules() {
        return applicationModules;
    }

    public void setApplicationModules(List<ApplicationModuleExt> applicationModules) {
        this.applicationModules = applicationModules;
    }

    public String getBusinessEntityID() {
        return businessEntityID;
    }

    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }
}
