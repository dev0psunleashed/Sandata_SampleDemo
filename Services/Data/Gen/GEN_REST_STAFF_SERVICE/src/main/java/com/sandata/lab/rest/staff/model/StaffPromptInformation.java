/**
 * 
 */
package com.sandata.lab.rest.staff.model;

import java.math.BigInteger;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.StateCode;

/**
 * @author huyvo
 *
 */
public class StaffPromptInformation {

    @SerializedName("StaffSK")
    protected BigInteger staffSK;
    
    @SerializedName("StaffID")
    protected String staffID;
    
    @SerializedName("StaffFirstName")
    protected String staffFirstName;
    
    @SerializedName("StaffLastName")
    protected String staffLastName;
    
    @SerializedName("Age")
    protected Integer age;
    
    @SerializedName("SSN")
    protected String ssn;
    
    @SerializedName("TIN")
    protected String tin;
    
    @SerializedName("StaffAddressLine1")
    protected String staffAddressLine1;
    
    @SerializedName("StaffAddressLine2")
    protected String staffAddressLine2;
    
    @SerializedName("StaffApartmentNumber")
    protected String staffApartmentNumber;
    
    @SerializedName("StaffCity")
    protected String staffCity;
    
    @SerializedName("StaffState")
    protected StateCode staffState;
    
    @SerializedName("StaffPostalCode")
    protected String staffPostalCode;
    
    @SerializedName("StaffPhone")
    protected String staffPhone;
    
    @SerializedName("StaffEmail")
    protected String staffEmail;

    public BigInteger getStaffSK() {
        return staffSK;
    }

    public void setStaffSK(BigInteger staffSK) {
        this.staffSK = staffSK;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getStaffAddressLine1() {
        return staffAddressLine1;
    }

    public void setStaffAddressLine1(String staffAddressLine1) {
        this.staffAddressLine1 = staffAddressLine1;
    }

    public String getStaffAddressLine2() {
        return staffAddressLine2;
    }

    public void setStaffAddressLine2(String staffAddressLine2) {
        this.staffAddressLine2 = staffAddressLine2;
    }

    public String getStaffApartmentNumber() {
        return staffApartmentNumber;
    }

    public void setStaffApartmentNumber(String staffApartmentNumber) {
        this.staffApartmentNumber = staffApartmentNumber;
    }

    public String getStaffCity() {
        return staffCity;
    }

    public void setStaffCity(String staffCity) {
        this.staffCity = staffCity;
    }

    public StateCode getStaffState() {
        return staffState;
    }

    public void setStaffState(StateCode staffState) {
        this.staffState = staffState;
    }

    public String getStaffPostalCode() {
        return staffPostalCode;
    }

    public void setStaffPostalCode(String staffPostalCode) {
        this.staffPostalCode = staffPostalCode;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }
}
