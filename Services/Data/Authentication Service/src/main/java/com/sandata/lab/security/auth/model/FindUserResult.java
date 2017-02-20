package com.sandata.lab.security.auth.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;
import java.util.Date;

public class FindUserResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("RoleName")
    private String roleName;

    @SerializedName("ApplicationRoleSk")
    private BigInteger applicationRoleSk;

    @SerializedName("ApplicationUserRoleSK")
    private BigInteger applicationUserRoleSk;

    @SerializedName("ApplicationTenantSk")
    private BigInteger applictionTenantSk;

    @SerializedName("ApplicationUserSK")
    private BigInteger applicationUserSK;

    @SerializedName("RecordCreateTimestamp")
    private Date recordCreateTimestamp;

    @SerializedName("RecordUpdateTimestamp")
    private Date recordUpdateTimestamp;

    @SerializedName("UserName")
    private String userName;
 
    @SerializedName("UserFirstName")
    private String userFirstName;
   
    @SerializedName("UserMiddleName")
    private String userMiddleName;
 
    @SerializedName("UserLastName")
    private String userLastName;

    @SerializedName("UserGloballyUniqueIdentifier")
    private String userGloballyUniqueIdentifier;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public BigInteger getApplicationUserRoleSk() {
        return applicationUserRoleSk;
    }

    public void setApplicationUserRoleSk(BigInteger applicationUserRoleSk) {
        this.applicationUserRoleSk = applicationUserRoleSk;
    }

    public BigInteger getApplictionTenantSk() {
        return applictionTenantSk;
    }

    public void setApplictionTenantSk(BigInteger applictionTenantSk) {
        this.applictionTenantSk = applictionTenantSk;
    }

    public BigInteger getApplicationUserSK() {
        return applicationUserSK;
    }

    public void setApplicationUserSK(BigInteger applicationUserSK) {
        this.applicationUserSK = applicationUserSK;
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

    public String getUserMiddleName() {
        return userMiddleName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserGloballyUniqueIdentifier() {
        return userGloballyUniqueIdentifier;
    }

    public void setUserGloballyUniqueIdentifier(String userGloballyUniqueIdentifier) {
        this.userGloballyUniqueIdentifier = userGloballyUniqueIdentifier;
    }

    public BigInteger getApplicationRoleSk() {
        return applicationRoleSk;
    }

    public void setApplicationRoleSk(BigInteger applicationRoleSk) {
        this.applicationRoleSk = applicationRoleSk;
    }
}
