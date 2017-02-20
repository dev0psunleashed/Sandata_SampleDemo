package com.sandata.lab.data.model.dl.model.extended;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.ApplicationUser;

import java.util.ArrayList;
import java.util.Date;

public class ApplicationUserExt extends ApplicationUser {

    private static final long serialVersionUID = 1L;

    @SerializedName("UserDateOfBirth")
    Date userDateOfBirth;

    @SerializedName("UserTelephoneNumber")
    String userPhoneNumber;

    @SerializedName("BusinessEntityID")
    String businessEntityID;

    @SerializedName("UserAddress")
    String userAddress;

    @SerializedName("UserApt")
    String userApt;

    @SerializedName("UserCity")
    String userCity;

    @SerializedName("UserState")
    String userState;

    @SerializedName("UserEmail")
    String userEmail;

    @SerializedName("UserMobilePhone")
    String userMobilePhone;

    @SerializedName("UserHomePhone")
    String userHomePhone;

    @SerializedName("UserDescription")
    String userDescription;

    @SerializedName("UserStatus")
    String userStatus;

    @SerializedName("UserDisplayName")
    String userDisplayName;

    @SerializedName("UserPersona")
    ArrayList<String> personas;

    @SerializedName("UserGroups")
    ArrayList<String> groups;

    @SerializedName("SandataReports")
    ArrayList<String> sanReports;

    @SerializedName("UserZipCode")
    String userZipCode;

    @SerializedName("SandataAccess")
    String isSandataAccess;

    @SerializedName("SandataAllowedSystems")
    ArrayList<String> sanAllowedSystems;

    @SerializedName("AgBusinessEntityID")
    String agBusinessEntityID;

    @SerializedName("AgReports")
    ArrayList<String> agReports;

    @SerializedName("AgPayer")
    String agPayer;

    @SerializedName("AgProvider")
    String agProvider;

    @SerializedName("AgSandataAccess")
    String isAgSandataAccess;


    public ArrayList<String> getSanAllowedSystems() {
        return sanAllowedSystems;
    }

    public void setSanAllowedSystems(ArrayList<String> sanAllowedSystems) {
        this.sanAllowedSystems = sanAllowedSystems;
    }

    public String getAgBusinessEntityID() {
        return agBusinessEntityID;
    }

    public void setAgBusinessEntityID(String agBusinessEntityID) {
        this.agBusinessEntityID = agBusinessEntityID;
    }

    public ArrayList getAgReports() {
        return agReports;
    }

    public void setAgReports(ArrayList agReports) {
        this.agReports = agReports;
    }

    public String getAgPayer() {
        return agPayer;
    }

    public void setAgPayer(String agPayer) {
        this.agPayer = agPayer;
    }

    public String getAgProvider() {
        return agProvider;
    }

    public void setAgProvider(String agProvider) {
        this.agProvider = agProvider;
    }

    public String getIsAgSandataAccess() {
        return isAgSandataAccess;
    }

    public void setIsAgSandataAccess(String isAgSandataAccess) {
        this.isAgSandataAccess = isAgSandataAccess;
    }


    public String getIsSandataAccess() {
        return isSandataAccess;
    }

    public void setIsSandataAccess(String isSandataAccess) {
        this.isSandataAccess = isSandataAccess;
    }

    public String getUserZipCode() {
        return userZipCode;
    }

    public void setUserZipCode(String userZipCode) {
        this.userZipCode = userZipCode;
    }

    public ArrayList<String> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<String> groups) {
        this.groups = groups;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(Date userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getBusinessEntityID() {
        return businessEntityID;
    }

    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMobilePhone() {
        return userMobilePhone;
    }

    public void setUserMobilePhone(String userMobilePhone) {
        this.userMobilePhone = userMobilePhone;
    }

    public String getUserHomePhone() {
        return userHomePhone;
    }

    public void setUserHomePhone(String userHomePhone) {
        this.userHomePhone = userHomePhone;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public ArrayList<String> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<String> personas) {
        this.personas = personas;
    }

    public ArrayList<String> getSanReports() {
        return sanReports;
    }

    public void setSanReports(ArrayList<String> sanReports) {
        this.sanReports = sanReports;
    }

    public String getUserApt() {
        return userApt;
    }

    public void setUserApt(String userApt) {
        this.userApt = userApt;
    }
}
