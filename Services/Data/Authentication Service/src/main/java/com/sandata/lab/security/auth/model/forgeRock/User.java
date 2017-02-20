package com.sandata.lab.security.auth.model.forgeRock;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class User {

    @SerializedName("familyName")
    String lastName;

    @SerializedName("sn")
    String sn;

    @SerializedName("givenName")
    String firstName;

    @SerializedName("MI")
    String middleInitial;

    @SerializedName("DOB")
    Date dateOfBirth;

    @SerializedName("telephoneNumber")
    String phoneNumber;

    @SerializedName("password")
    String password;

    @SerializedName("ou")
    String organizationalUnit;

    @SerializedName("o")
    ArrayList<String> organization;

    @SerializedName("address1")
    String address1;

    @SerializedName("address2")
    String address2;

    @SerializedName("city")
    String city;

    @SerializedName("stateProvince")
    String state;

    @SerializedName("userType")
    String userType;

    @SerializedName("mail")
    String email;

    @SerializedName("mobile")
    String mobilePhone;

    @SerializedName("homePhone")
    String homePhone;

    @SerializedName("postalCode")
    String zipCode;

    @SerializedName("SandataAccess")
    String isSandataAccess;

    @SerializedName("description")
    String description;

    @SerializedName("inetUserStatus")
    String userStatus;

    @SerializedName("userName")
    String userName;

    @SerializedName("displayName")
    String displayName;

    @SerializedName("SandataGUID")
    String sandataGUID;

    @SerializedName("Persona")
    ArrayList<String> personas;

    @SerializedName("sanreports")
    ArrayList<String> sanReports;

    @SerializedName("code")
    String code;

    @SerializedName("StaffManager")
    String staffManager;

    @SerializedName("PatientManager")
    String patientManager;

    @SerializedName("PatientHIPAA")
    String patientHIPAA;

    @SerializedName("StaffHIPAA")
    String staffHIPAA;

    @SerializedName("BEID")
    String businessEntityID;

    @SerializedName("pwdAccountLockedTime")
    String passwordAccountLockedTime;

    @SerializedName("pwdChangedTime")
    String passwordChangedTime;

    @SerializedName("SanAllowedSystems")
    ArrayList<String> sanAllowedSystems;

    @SerializedName("AgBusinessEntityID")
    String agBusinessEntityID;

    @SerializedName("AgReports")
    ArrayList<String> agReports;

    @SerializedName("AgPayer")
    String agPayer;

    @SerializedName("AgProvider")
    String agProvider;

    public ArrayList<String> getSanAllowedSystems() {
        return sanAllowedSystems;
    }

    public void setSanAllowedSystems(ArrayList<String> sanAllowedSystems) {
        this.sanAllowedSystems = sanAllowedSystems;
    }

    public ArrayList getAgReports() {
        return agReports;
    }

    public void setAgReports(ArrayList agReports) {
        this.agReports = agReports;
    }

    public String getIsAgSandataAccess() {
        return isAgSandataAccess;
    }

    public void setIsAgSandataAccess(String isAgSandataAccess) {
        this.isAgSandataAccess = isAgSandataAccess;
    }

    @SerializedName("AgSandataAccess")
    String isAgSandataAccess;

    public String getPasswordAccountLockedTime() {
        return passwordAccountLockedTime;
    }

    public void setPasswordAccountLockedTime(String passwordAccountLockedTime) {
        this.passwordAccountLockedTime = passwordAccountLockedTime;
    }

    public String getPasswordChangedTime() {
        return passwordChangedTime;
    }

    public void setPasswordChangedTime(String passwordChangedTime) {
        this.passwordChangedTime = passwordChangedTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSandataGUID() {
        return sandataGUID;
    }

    public void setSandataGUID(String sandataGUID) {
        this.sandataGUID = sandataGUID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrganizationalUnit() {
        return organizationalUnit;
    }

    public void setOrganizationalUnit(String organizationalUnit) {
        this.organizationalUnit = organizationalUnit;
    }

    public ArrayList<String> getOrganization() {
        return organization;
    }

    public void setOrganization(ArrayList<String> organization) {
        this.organization = organization;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String isSandataAccess() {
        return isSandataAccess;
    }

    public void setSandataAccess(String sandataAccess) {
        isSandataAccess = sandataAccess;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getIsSandataAccess() {
        return isSandataAccess;
    }

    public void setIsSandataAccess(String isSandataAccess) {
        this.isSandataAccess = isSandataAccess;
    }

    public ArrayList<String> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<String> personas) {
        this.personas = personas;
    }

    public String getStaffManager() {
        return staffManager;
    }

    public void setStaffManager(String staffManager) {
        this.staffManager = staffManager;
    }

    public String getPatientManager() {
        return patientManager;
    }

    public void setPatientManager(String patientManager) {
        this.patientManager = patientManager;
    }

    public String getPatientHIPAA() {
        return patientHIPAA;
    }

    public void setPatientHIPAA(String patientHIPAA) {
        this.patientHIPAA = patientHIPAA;
    }

    public String getStaffHIPAA() {
        return staffHIPAA;
    }

    public void setStaffHIPAA(String staffHIPAA) {
        this.staffHIPAA = staffHIPAA;
    }

    public String getBusinessEntityID() {
        return businessEntityID;
    }

    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }

    public ArrayList<String> getSanReports() {
        return sanReports;
    }

    public void setSanReports(ArrayList<String> sanReports) {
        this.sanReports = sanReports;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
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

    public String getAgBusinessEntityID() {
        return agBusinessEntityID;
    }

    public void setAgBusinessEntityID(String agBusinessEntityID) {
        this.agBusinessEntityID = agBusinessEntityID;
    }
}
