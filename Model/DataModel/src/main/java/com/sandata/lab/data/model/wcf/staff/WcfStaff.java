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

package com.sandata.lab.data.model.wcf.staff;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;

/**
 * Models the WCF Staff class.
 * <p/>
 *
 * @author David Rutgos
 */
public class WcfStaff extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffId")
    private int staffId;

    @SerializedName("AgencyId")
    private Integer agencyId;

    @SerializedName("TypeId")
    private Integer typeId;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("MiddleInitial")
    private String middleInitial;

    @SerializedName("SocialSecurity")
    private String ssn;

    @SerializedName("TaxId_EIN")
    private String taxIdEin;

    @SerializedName("SantraxID")
    private String santraxId;

    @SerializedName("HomePhone")
    private String homePhone;

    @SerializedName("CellPhone")
    private String cellPhone;

    @SerializedName("Email")
    private String email;

    @SerializedName("PreferredContactId")
    private Integer preferredContactId;

    @SerializedName("NPINumber")
    private String npiNumber;

    @SerializedName("APINumber")
    private String apiNumber;

    @SerializedName("AgencyLocation")
    private String agencyLocation;

    @SerializedName("AdmissionTypeId")
    private Integer admissionTypeId;

    @SerializedName("PositionId")
    private Integer positionId;

    @SerializedName("DateOfBirth")
    private Date dateOfBirth;

    @SerializedName("MaritalStatusId")
    private Integer maritalStatusId;

    @SerializedName("GenderId")
    private Integer genderId;

    @SerializedName("LanguageId")
    private Integer languageId;

    @SerializedName("EthnicityId")
    private Integer ethnicityId;

    @SerializedName("ReligionId")
    private Integer religionId;

    @SerializedName("Address1")
    private String address1;

    @SerializedName("Address2")
    private String address2;

    @SerializedName("Apt")
    private String apt;

    @SerializedName("City")
    private String city;

    @SerializedName("StateId")
    private Integer stateId;

    @SerializedName("Zip")
    private String zip;

    @SerializedName("County")
    private String county;

    @SerializedName("Region")
    private String region;

    @SerializedName("HomeLocation")
    private String homeLocation;

    @SerializedName("HireDate")
    private Date hireDate;

    @SerializedName("FirstDayWorked")
    private Date firstDayWorked;

    @SerializedName("ReleasedDate")
    private Date releasedDate;

    @SerializedName("RehireDate")
    private Date rehireDate;

    @SerializedName("StaffManager")
    private String staffManager;

    @SerializedName("Coordinator")
    private String coordinator;

    @SerializedName("Team")
    private String team;

    @SerializedName("StaffClassId")
    private Integer staffClassId;

    @SerializedName("MilitaryStatusId")
    private Integer militaryStatusId;

    @SerializedName("TransportationTypeId")
    private Integer transportationTypeId;

    @SerializedName("TravelRadius")
    private String travelRadius;

    @SerializedName("Referral")
    private String referral;

    @SerializedName("StaffStatusId")
    private Integer staffStatusId;

    @SerializedName("FederalExemptions")
    private String federalExemptions;

    @SerializedName("FederalMaritalStatusId")
    private Integer federalMaritalStatusId;

    @SerializedName("StateExemptions")
    private String stateExemptions;

    @SerializedName("StateMaritalStatusId")
    private Integer stateMaritalStatusId;

    @SerializedName("StateTaxId")
    private Integer stateTaxId;

    @SerializedName("LiveInStateId")
    private Integer liveInStateId;

    @SerializedName("CityTax1Id")
    private Integer cityTax1Id;

    @SerializedName("CityTax2Id")
    private Integer cityTax2Id;

    @SerializedName("PayTypeId")
    private Integer payTypeId;

    @SerializedName("LastRaiseDate")
    private Date lastRaiseDate;

    @SerializedName("PayFrequencyId")
    private Integer payFrequencyId;

    @SerializedName("IsAlwaysPayRegularHourlyRate")
    private Boolean isAlwaysPayRegularHourlyRate;

    @SerializedName("IsAlwaysPayRegularDailyRate")
    private Boolean isAlwaysPayRegularDailyRate;

    @SerializedName("IsBypassHolidayPayRate")
    private Boolean isBypassHolidayPayRate;

    @SerializedName("IsOvernightOK")
    private Boolean isOvernightOk;

    /* TODO --
    @SerializedName("AdmissionType")
    private AdmissionType admissionType;

    @SerializedName("Agency")
    private Agency agency;

    @SerializedName("CityTax")
    private CityTax cityTax;

    @SerializedName("CityTax1")
    private CityTax cityTax1;

    @SerializedName("StaffClass")
    private StaffClass staffClass;

    @SerializedName("StaffStatus")
    private StaffStatus staffStatus;

    @SerializedName("StaffType")
    private StaffType staffType;

    @SerializedName("Ethnicity")
    private Ethnicity ethnicity;

    @SerializedName("Gender")
    private Gender gender;

    @SerializedName("Language")
    private Language language;

    @SerializedName("MaritalStatus")
    private MaritalStatus maritalStatus;

    @SerializedName("MaritalStatus1")
    private MaritalStatus maritalStatus1;

    @SerializedName("MaritalStatus2")
    private MaritalStatus maritalStatus2;

    @SerializedName("MilitaryStatus")
    private MilitaryStatus militaryStatus;

    @SerializedName("PayFrequency")
    private PayFrequency payFrequency;

    @SerializedName("PayType")
    private PayType payType;

    @SerializedName("Position")
    private Position position;

    @SerializedName("PreferredContact")
    private PreferredContact preferredContact;

    @SerializedName("Religion")
    private Religion religion;

    @SerializedName("State")
    private State state;

    @SerializedName("State1")
    private State state1;

    @SerializedName("StateTax")
    private StateTax stateTax;

    @SerializedName("TransportationType")
    private TransportationType transportationType;
    */

    public Boolean isOvernightOk() {
        return isOvernightOk;
    }

    public void setOvernightOk(Boolean isOvernightOk) {
        this.isOvernightOk = isOvernightOk;
    }

    public Boolean isBypassHolidayPayRate() {
        return isBypassHolidayPayRate;
    }

    public void setBypassHolidayPayRate(Boolean isBypassHolidayPayRate) {
        this.isBypassHolidayPayRate = isBypassHolidayPayRate;
    }

    public Boolean isAlwaysPayRegularDailyRate() {
        return isAlwaysPayRegularDailyRate;
    }

    public void setAlwaysPayRegularDailyRate(Boolean isAlwaysPayRegularDailyRate) {
        this.isAlwaysPayRegularDailyRate = isAlwaysPayRegularDailyRate;
    }

    public Boolean isAlwaysPayRegularHourlyRate() {
        return isAlwaysPayRegularHourlyRate;
    }

    public void setAlwaysPayRegularHourlyRate(Boolean isAlwaysPayRegularHourlyRate) {
        this.isAlwaysPayRegularHourlyRate = isAlwaysPayRegularHourlyRate;
    }

    public Integer getPayFrequencyId() {
        return payFrequencyId;
    }

    public void setPayFrequencyId(Integer payFrequencyId) {
        this.payFrequencyId = payFrequencyId;
    }

    public Date getLastRaiseDate() {
        return lastRaiseDate;
    }

    public void setLastRaiseDate(Date lastRaiseDate) {
        this.lastRaiseDate = lastRaiseDate;
    }

    public Integer getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(Integer payTypeId) {
        this.payTypeId = payTypeId;
    }

    public Integer getCityTax2Id() {
        return cityTax2Id;
    }

    public void setCityTax2Id(Integer cityTax2Id) {
        this.cityTax2Id = cityTax2Id;
    }

    public Integer getCityTax1Id() {
        return cityTax1Id;
    }

    public void setCityTax1Id(Integer cityTax1Id) {
        this.cityTax1Id = cityTax1Id;
    }

    public Integer getLiveInStateId() {
        return liveInStateId;
    }

    public void setLiveInStateId(Integer liveInStateId) {
        this.liveInStateId = liveInStateId;
    }

    public Integer getStateTaxId() {
        return stateTaxId;
    }

    public void setStateTaxId(Integer stateTaxId) {
        this.stateTaxId = stateTaxId;
    }

    public Integer getStateMaritalStatusId() {
        return stateMaritalStatusId;
    }

    public void setStateMaritalStatusId(Integer stateMaritalStatusId) {
        this.stateMaritalStatusId = stateMaritalStatusId;
    }

    public String getStateExemptions() {
        return stateExemptions;
    }

    public void setStateExemptions(String stateExemptions) {
        this.stateExemptions = stateExemptions;
    }

    public Integer getFederalMaritalStatusId() {
        return federalMaritalStatusId;
    }

    public void setFederalMaritalStatusId(Integer federalMaritalStatusId) {
        this.federalMaritalStatusId = federalMaritalStatusId;
    }

    public String getFederalExemptions() {
        return federalExemptions;
    }

    public void setFederalExemptions(String federalExemptions) {
        this.federalExemptions = federalExemptions;
    }

    public Integer getStaffStatusId() {
        return staffStatusId;
    }

    public void setStaffStatusId(Integer staffStatusId) {
        this.staffStatusId = staffStatusId;
    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public String getTravelRadius() {
        return travelRadius;
    }

    public void setTravelRadius(String travelRadius) {
        this.travelRadius = travelRadius;
    }

    public Integer getTransportationTypeId() {
        return transportationTypeId;
    }

    public void setTransportationTypeId(Integer transportationTypeId) {
        this.transportationTypeId = transportationTypeId;
    }

    public Integer getMilitaryStatusId() {
        return militaryStatusId;
    }

    public void setMilitaryStatusId(Integer militaryStatusId) {
        this.militaryStatusId = militaryStatusId;
    }

    public Integer getStaffClassId() {
        return staffClassId;
    }

    public void setStaffClassId(Integer staffClassId) {
        this.staffClassId = staffClassId;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public String getStaffManager() {
        return staffManager;
    }

    public void setStaffManager(String staffManager) {
        this.staffManager = staffManager;
    }

    public Date getRehireDate() {
        return rehireDate;
    }

    public void setRehireDate(Date rehireDate) {
        this.rehireDate = rehireDate;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Date getFirstDayWorked() {
        return firstDayWorked;
    }

    public void setFirstDayWorked(Date firstDayWorked) {
        this.firstDayWorked = firstDayWorked;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(String homeLocation) {
        this.homeLocation = homeLocation;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public Integer getReligionId() {
        return religionId;
    }

    public void setReligionId(Integer religionId) {
        this.religionId = religionId;
    }

    public Integer getEthnicityId() {
        return ethnicityId;
    }

    public void setEthnicityId(Integer ethnicityId) {
        this.ethnicityId = ethnicityId;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public Integer getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Integer maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getAdmissionTypeId() {
        return admissionTypeId;
    }

    public void setAdmissionTypeId(Integer admissionTypeId) {
        this.admissionTypeId = admissionTypeId;
    }

    public String getAgencyLocation() {
        return agencyLocation;
    }

    public void setAgencyLocation(String agencyLocation) {
        this.agencyLocation = agencyLocation;
    }

    public String getApiNumber() {
        return apiNumber;
    }

    public void setApiNumber(String apiNumber) {
        this.apiNumber = apiNumber;
    }

    public String getNpiNumber() {
        return npiNumber;
    }

    public void setNpiNumber(String npiNumber) {
        this.npiNumber = npiNumber;
    }

    public Integer getPreferredContactId() {
        return preferredContactId;
    }

    public void setPreferredContactId(Integer preferredContactId) {
        this.preferredContactId = preferredContactId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getSantraxId() {
        return santraxId;
    }

    public void setSantraxId(String santraxId) {
        this.santraxId = santraxId;
    }

    public String getTaxIdEin() {
        return taxIdEin;
    }

    public void setTaxIdEin(String taxIdEin) {
        this.taxIdEin = taxIdEin;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
}
