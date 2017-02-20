package com.sandata.lab.rest.staff.model;

import com.sandata.lab.data.model.base.BaseObject;

import java.util.List;

public class StaffLanguageItemRequest extends BaseObject {

    private static final long serialVersionUID = 1L;

    private List<String> languages;
    private String staffId;
    private String businessEntityId;

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }
}
