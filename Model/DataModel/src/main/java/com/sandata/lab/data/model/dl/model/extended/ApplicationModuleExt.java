package com.sandata.lab.data.model.dl.model.extended;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.ApplicationModule;

import java.util.List;

public class ApplicationModuleExt extends ApplicationModule{

    private static final long serialVersionUID = 1L;

    @SerializedName("ViewPermission")
    private boolean viewPermission;

    @SerializedName("EditPermission")
    private boolean editPermission;

    @SerializedName("ApplicationModules")
    List<ApplicationModule> applicationModules;

    public List<ApplicationModule> getApplicationModules() {
        return applicationModules;
    }

    public void setApplicationModules(List<ApplicationModule> applicationModules) {
        this.applicationModules = applicationModules;
    }

    public boolean isEditPermission() {
        return editPermission;
    }

    public void setEditPermission(boolean editPermission) {
        this.editPermission = editPermission;
    }

    public boolean isViewPermission() {
        return viewPermission;
    }

    public void setViewPermission(boolean viewPermission) {
        this.viewPermission = viewPermission;
    }
}
