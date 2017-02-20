package com.sandata.one.aggregator.visit.review.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;

public class ReviewVisitStaffResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffID")
    protected String staffID;

    @SerializedName("StaffFirstName")
    protected String staffFirstName;

    @SerializedName("StaffLastName")
    protected String staffLastName;

    @SerializedName("StaffMiddleName")
    protected String staffMiddleName;

    @SerializedName("TelephonyID")
    private String telephonyID;

    @SerializedName("PreferredPhone")
    private String preferredPhone;

    @SerializedName("StaffPosition")
    private String staffPosition;

    @SerializedName("AddressName")
    private String addressName;

    @SerializedName("Address1")
    private String address1;

    @SerializedName("Address2")
    private String address2;

    @SerializedName("City")
    private String city;

    @SerializedName("State")
    private String state;

    @SerializedName("PostalCode")
    private String postalCode;

    @SerializedName("Zip4")
    private String zip4;

    @SerializedName("County")
    private String county;

    @SerializedName("HireDate")
    private Date hireDate;

    @SerializedName("TermDate")
    private Date termDate;

    @SerializedName("SchedEvntDate")
    private Date schedEvntDate;

    @SerializedName("ThirdpartyID")
    private String thirdpartyID;

    @SerializedName("ThirdPartyStaffIDIndicator")
    private boolean thirdPartyStaffIDIndicator;

    @SerializedName("SchedEvntSk")
    private Long schedEvntSk;

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffLastName() {
        return staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public String getStaffMiddleName() {
        return staffMiddleName;
    }

    public void setStaffMiddleName(String staffMiddleName) {
        this.staffMiddleName = staffMiddleName;
    }

    public String getTelephonyID() {
        return telephonyID;
    }

    public void setTelephonyID(String telephonyID) {
        this.telephonyID = telephonyID;
    }

    public String getPreferredPhone() {
        return preferredPhone;
    }

    public void setPreferredPhone(String preferredPhone) {
        this.preferredPhone = preferredPhone;
    }

    public String getStaffPosition() {
        return staffPosition;
    }

    public void setStaffPosition(String staffPosition) {
        this.staffPosition = staffPosition;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
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

    public String getZip4() {
        return zip4;
    }

    public void setZip4(String zip4) {
        this.zip4 = zip4;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getTermDate() {
        return termDate;
    }

    public void setTermDate(Date termDate) {
        this.termDate = termDate;
    }

    public String getThirdpartyID() {
        return thirdpartyID;
    }

    public void setThirdpartyID(String thirdpartyID) {
        this.thirdpartyID = thirdpartyID;
    }

    public boolean isThirdPartyStaffIDIndicator() {
        return thirdPartyStaffIDIndicator;
    }

    public void setThirdPartyStaffIDIndicator(boolean thirdPartyStaffIDIndicator) {
        this.thirdPartyStaffIDIndicator = thirdPartyStaffIDIndicator;
    }

    public Date getSchedEvntDate() {
        return schedEvntDate;
    }

    public void setSchedEvntDate(Date schedEvntDate) {
        this.schedEvntDate = schedEvntDate;
    }

    public Long getSchedEvntSk() {
        return schedEvntSk;
    }

    public void setSchedEvntSk(Long schedEvntSk) {
        this.schedEvntSk = schedEvntSk;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
