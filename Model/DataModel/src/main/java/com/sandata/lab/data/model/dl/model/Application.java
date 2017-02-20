package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;

import java.math.BigInteger;
import java.util.Date;

@OracleMetadata(
        packageName = "PKG_APP",
        insertMethod = "insertApp",
        updateMethod = "updateApp",
        deleteMethod = "deleteApp",
        getMethod = "getApp",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.AppT",
        primaryKeys = {})
public class Application extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("ApplicationSK")
    @Mapping(getter = "getAppSk", setter = "setAppSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger applicationSK;

    @SerializedName("RecordCreateTimestamp")
    @Mapping(getter = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = 1)
    protected Date recordCreateTimestamp;

    @SerializedName("RecordUpdateTimestamp")
    @Mapping(getter = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = 2)
    protected Date recordUpdateTimestamp;

    @SerializedName("ApplicationName")
    @Mapping(getter = "getAppName", setter = "setAppName", type = "String", index = 3)
    protected String applicationName;

    @SerializedName("ApplicationDescription")
    @Mapping(getter = "getAppDesc", setter = "setAppDesc", type = "String", index = 4)
    protected String applicationDescription;

    public BigInteger getApplicationSK() {
        return applicationSK;
    }

    public void setApplicationSK(BigInteger applicationSK) {
        this.applicationSK = applicationSK;
    }

    public Date getRecordCreateTimestamp() {
        return recordCreateTimestamp;
    }

    public void setRecordCreateTimestamp(Date recordCreateTimestamp) {
        this.recordCreateTimestamp = recordCreateTimestamp;
    }

    public Date getRecordUpdateTimestamp() {
        return recordUpdateTimestamp;
    }

    public void setRecordUpdateTimestamp(Date recordUpdateTimestamp) {
        this.recordUpdateTimestamp = recordUpdateTimestamp;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }
}
