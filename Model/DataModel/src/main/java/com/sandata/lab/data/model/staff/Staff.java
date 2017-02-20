/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.data.model.staff;

import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;

/**
 * Models the Staff class.
 * <p/>
 *
 * @author David Rutgos
 */
public class Staff extends BaseObject {

    private static final long serialVersionUID = 1L;

    private int staffId;
    private int agencyId;
    private String type;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String ssn;
    private String taxIdEin;
    private String santraxId;
    private String homePhone;
    private String cellPhone;
    private String email;
    private String preferredContact;
    private String npiNumber;
    private String apiNumber;
    private String agencyLocation;
    private String admissionType;
    private String position;
    private Date dateOfBirth;
    private String maritalStatus;
    private String gender;
    private String language;
    private String ethnicity;
    private String religion;
    private String address1;
    private String address2;
    private String apt;
    private String city;
    private String state;
    private String zip;
    private String county;
    private String region;
    private String homeLocation;
    private Date hireDate;
    private Date firstDayWorked;
    private Date releasedDate;
    private Date rehireDate;
    private String staffManager;
    private String coordinator;
    private String team;
    private String staffClass;
    private String militaryStatus;
    private String transportationType;
    private String travelRadius;
    private String referral;
    private String staffStatus;
    private String federalExemptions;
    private String federalMaritalStatus;
    private String stateExemptions;
    private String stateMaritalStatus;
    private String stateTax;
    private String liveInState;
    private String cityTax1;
    private String cityTax2;
    private String payType;
    private Date lastRaiseDate;
    private String payFrequency;
    private Boolean isAlwaysPayRegularHourlyRate;
    private Boolean isAlwaysPayRegularDailyRate;
    private Boolean isBypassHolidayPayRate;
    private Boolean isOvernightOk;

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getTaxIdEin() {
        return taxIdEin;
    }

    public void setTaxIdEin(String taxIdEin) {
        this.taxIdEin = taxIdEin;
    }

    public String getSantraxId() {
        return santraxId;
    }

    public void setSantraxId(String santraxId) {
        this.santraxId = santraxId;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreferredContact() {
        return preferredContact;
    }

    public void setPreferredContact(String preferredContact) {
        this.preferredContact = preferredContact;
    }

    public String getNpiNumber() {
        return npiNumber;
    }

    public void setNpiNumber(String npiNumber) {
        this.npiNumber = npiNumber;
    }

    public String getApiNumber() {
        return apiNumber;
    }

    public void setApiNumber(String apiNumber) {
        this.apiNumber = apiNumber;
    }

    public String getAgencyLocation() {
        return agencyLocation;
    }

    public void setAgencyLocation(String agencyLocation) {
        this.agencyLocation = agencyLocation;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
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

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(String homeLocation) {
        this.homeLocation = homeLocation;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getFirstDayWorked() {
        return firstDayWorked;
    }

    public void setFirstDayWorked(Date firstDayWorked) {
        this.firstDayWorked = firstDayWorked;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Date getRehireDate() {
        return rehireDate;
    }

    public void setRehireDate(Date rehireDate) {
        this.rehireDate = rehireDate;
    }

    public String getStaffManager() {
        return staffManager;
    }

    public void setStaffManager(String staffManager) {
        this.staffManager = staffManager;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getStaffClass() {
        return staffClass;
    }

    public void setStaffClass(String staffClass) {
        this.staffClass = staffClass;
    }

    public String getMilitaryStatus() {
        return militaryStatus;
    }

    public void setMilitaryStatus(String militaryStatus) {
        this.militaryStatus = militaryStatus;
    }

    public String getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(String transportationType) {
        this.transportationType = transportationType;
    }

    public String getTravelRadius() {
        return travelRadius;
    }

    public void setTravelRadius(String travelRadius) {
        this.travelRadius = travelRadius;
    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public String getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus;
    }

    public String getFederalExemptions() {
        return federalExemptions;
    }

    public void setFederalExemptions(String federalExemptions) {
        this.federalExemptions = federalExemptions;
    }

    public String getFederalMaritalStatus() {
        return federalMaritalStatus;
    }

    public void setFederalMaritalStatus(String federalMaritalStatus) {
        this.federalMaritalStatus = federalMaritalStatus;
    }

    public String getStateExemptions() {
        return stateExemptions;
    }

    public void setStateExemptions(String stateExemptions) {
        this.stateExemptions = stateExemptions;
    }

    public String getStateMaritalStatus() {
        return stateMaritalStatus;
    }

    public void setStateMaritalStatus(String stateMaritalStatus) {
        this.stateMaritalStatus = stateMaritalStatus;
    }

    public String getStateTax() {
        return stateTax;
    }

    public void setStateTax(String stateTax) {
        this.stateTax = stateTax;
    }

    public String getLiveInState() {
        return liveInState;
    }

    public void setLiveInState(String liveInState) {
        this.liveInState = liveInState;
    }

    public String getCityTax1() {
        return cityTax1;
    }

    public void setCityTax1(String cityTax1) {
        this.cityTax1 = cityTax1;
    }

    public String getCityTax2() {
        return cityTax2;
    }

    public void setCityTax2(String cityTax2) {
        this.cityTax2 = cityTax2;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Date getLastRaiseDate() {
        return lastRaiseDate;
    }

    public void setLastRaiseDate(Date lastRaiseDate) {
        this.lastRaiseDate = lastRaiseDate;
    }

    public String getPayFrequency() {
        return payFrequency;
    }

    public void setPayFrequency(String payFrequency) {
        this.payFrequency = payFrequency;
    }

    public Boolean getIsAlwaysPayRegularHourlyRate() {
        return isAlwaysPayRegularHourlyRate;
    }

    public void setIsAlwaysPayRegularHourlyRate(Boolean isAlwaysPayRegularHourlyRate) {
        this.isAlwaysPayRegularHourlyRate = isAlwaysPayRegularHourlyRate;
    }

    public Boolean getIsAlwaysPayRegularDailyRate() {
        return isAlwaysPayRegularDailyRate;
    }

    public void setIsAlwaysPayRegularDailyRate(Boolean isAlwaysPayRegularDailyRate) {
        this.isAlwaysPayRegularDailyRate = isAlwaysPayRegularDailyRate;
    }

    public Boolean getIsBypassHolidayPayRate() {
        return isBypassHolidayPayRate;
    }

    public void setIsBypassHolidayPayRate(Boolean isBypassHolidayPayRate) {
        this.isBypassHolidayPayRate = isBypassHolidayPayRate;
    }

    public Boolean getIsOvernightOk() {
        return isOvernightOk;
    }

    public void setIsOvernightOk(Boolean isOvernightOk) {
        this.isOvernightOk = isOvernightOk;
    }
}
