package com.sandata.lab.data.model.dl.model.audit;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;

import java.util.Date;

/**
 * Date: 8/18/16
 * Time: 5:20 PM
 */
@OracleMetadata(
        packageName = "PKG_AUDIT",
        insertMethod = "N/A",
        updateMethod = "N/A",
        deleteMethod = "N/A",
        getMethod = "N/A",
        findMethod = "N/A",
        jpubClass = "com.sandata.lab.data.model.dl.model.audit.AuditChangedT",
        primaryKeys = {})
public class AuditChanged extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("UserGuid")
    @Mapping(getter = "getUserGuid", setter = "setUserGuid", type = "String", index = 0)
    protected String userGuid;

    @SerializedName("UserName")
    @Mapping(getter = "getUserName", setter = "setUserName", type = "String", index = 1)
    protected String userName;

    @SerializedName("UserFirstName")
    @Mapping(getter = "getUserFirstName", setter = "setUserFirstName", type = "String", index = 2)
    protected String userFirstName;

    @SerializedName("UserLastName")
    @Mapping(getter = "getUserLastName", setter = "setUserLastName", type = "String", index = 3)
    protected String userLastName;

    @SerializedName("DataPoint")
    @Mapping(getter = "getDataPoint", setter = "setDataPoint", type = "String", index = 4)
    protected String dataPoint;

    @SerializedName("ChangedFrom")
    @Mapping(getter = "getChangedFrom", setter = "setChangedFrom", type = "String", index = 5)
    protected String changedFrom;

    @SerializedName("ChangedTo")
    @Mapping(getter = "getChangedTo", setter = "setChangedTo", type = "String", index = 6)
    protected String changedTo;

    @SerializedName("ChangedOn")
    @Mapping(getter = "getChangedOn", setter = "setChangedOn", type = "java.sql.Timestamp", index = 7)
    protected Date changedOn;

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getDataPoint() {
        return dataPoint;
    }

    public void setDataPoint(String dataPoint) {
        this.dataPoint = dataPoint;
    }

    public String getChangedFrom() {
        return changedFrom;
    }

    public void setChangedFrom(String changedFrom) {
        this.changedFrom = changedFrom;
    }

    public String getChangedTo() {
        return changedTo;
    }

    public void setChangedTo(String changedTo) {
        this.changedTo = changedTo;
    }

    public Date getChangedOn() {
        return changedOn;
    }

    public void setChangedOn(Date changedOn) {
        this.changedOn = changedOn;
    }
}
