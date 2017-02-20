package com.sandata.lab.logger.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.ApplicationLog;
import org.springframework.beans.BeanUtils;

public class ApplicationLogExt extends ApplicationLog {

    private static final long serialVersionUID = 1L;

    public ApplicationLogExt() {
    }

    public ApplicationLogExt(ApplicationLog applicationLog) {
        BeanUtils.copyProperties(applicationLog, this);
    }

    @SerializedName("UserGloballyUniqueIdentifier")
    private String userGloballyUniqueIdentifier;

    public String getUserGloballyUniqueIdentifier() {
        return userGloballyUniqueIdentifier;
    }

    public void setUserGloballyUniqueIdentifier(String userGloballyUniqueIdentifier) {
        this.userGloballyUniqueIdentifier = userGloballyUniqueIdentifier;
    }

    public ApplicationLog getApplicationLog() {
        ApplicationLog applicationLog = new ApplicationLog();
        BeanUtils.copyProperties(this, applicationLog);
        return applicationLog;
    }
}
