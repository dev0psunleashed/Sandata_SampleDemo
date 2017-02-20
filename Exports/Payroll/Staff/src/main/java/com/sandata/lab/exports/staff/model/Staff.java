package com.sandata.lab.exports.staff.model;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Staff {

    @XmlElement(name = "LastName")
    String lastName;

    @XmlElement(name = "FirstName")
    String firstName;

    @XmlElement(name = "MiddleName")
    String middleName;

    @XmlElement(name = "SSN")
    String ssn;

    @XmlElement(name = "Address")
    String address;

    @XmlElement(name = "Address2")
    String address2;

    @XmlElement(name = "City")
    String city;

    @XmlElement(name = "State")
    String state;

    @XmlElement(name = "ZipCode")
    String zipCode;

    @XmlElement(name = "Status")
    String status;

    @XmlElement(name = "HireDate")
    String hireDate;

    @XmlElement(name = "Gender")
    String gender;

    @XmlElement(name = "BirthDate")
    String birthDate;

    @XmlElement(name = "HomePhone")
    String homePhone;

    @XmlElement(name = "Ethnicity")
    String ethnicity;

    @XmlElement(name = "TermDate")
    String termDate;

    @XmlElement(name = "Position")
    String position;

    @XmlElement(name = "MaritalStatus")
    String maritalStatus;

    @XmlElement(name = "CC1")
    String cc1;

    @XmlElement(name = "CC2")
    String cc2;

    @XmlElement(name = "CC3")
    String cc3;

    @XmlElement(name = "CC4")
    String cc4;

    @XmlElement(name = "CC5")
    String cc5;

    @XmlElement(name = "User1")
    String user1;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getTermDate() {
        return termDate;
    }

    public void setTermDate(String termDate) {
        this.termDate = termDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getCc1() {
        return cc1;
    }

    public void setCc1(String cc1) {
        this.cc1 = cc1;
    }

    public String getCc2() {
        return cc2;
    }

    public void setCc2(String cc2) {
        this.cc2 = cc2;
    }

    public String getCc3() {
        return cc3;
    }

    public void setCc3(String cc3) {
        this.cc3 = cc3;
    }

    public String getCc4() {
        return cc4;
    }

    public void setCc4(String cc4) {
        this.cc4 = cc4;
    }

    public String getCc5() {
        return cc5;
    }

    public void setCc5(String cc5) {
        this.cc5 = cc5;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }
}
