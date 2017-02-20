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

package com.sandata.lab.data.model.patient;

import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;

/**
 * Models the Patient class.
 * <p/>
 *
 * @author David Rutgos
 */
public class Patient extends BaseObject {

    private static final long serialVersionUID = 1L;

    private int patientId;
    private int agencyId;
    private String memberNumber;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private Date dateOfBirth;
    private int age;
    private String maritalStatus;
    private String gender;
    private String ssn;
    private String language;
    private String ethnicity;
    private String religion;
    private String address1;
    private String address2;
    private String apt;
    private String city;
    private String state;
    private String zip;
    private String homePhone;
    private String cellPhone;
    private String email;
    private String insurance;
    private String insuranceId;
    private String medicaidId;
    private String medicareId;
    private String medicalRecordNumber;
    private Date insuranceStartDate;
    private Date insuranceEndDate;
    private String referralNumber;
    private Date referralStartDate;
    private Date referralEndDate;
    private String referralSource;
    private String admissionId;
    private Double HourlyRate;
    private Double billRate;
    private String agencyLocation;
    private String clinicalManager;
    private String coordinator;
    private String marketer;
    private Integer disasterLevelId;
    private Integer dnrId;
    private Date dnrDate;
    private String allergies;
    private String dmeAndSupplies;
    private String nutritionalRequirements;
    private String safetyMeasures;
    private String medication;
    private String medicationStrength;
    private String medicationDosage;
    private String medicationRoute;
    private String medicationFrequency;
    private String medicationComments;
    private Integer medicationClassification;
    private Date medicationBeginDate;
    private Date medicationEndDate;
    private String referenceNumber;
    private String referenceFormat;
    private String referenceMaximum;
    private String referenceModifier1;
    private String referenceModifier2;
    private String referenceModifier3;
    private String referenceModifier4;
    private Date authorizationBeginDate;
    private Date authorizationEndDate;
    private String authorizationComments;
    private String limitBy;
    private String total;
    private String task;
    private String schedule;
    private String eligibility;
    private String nameOfThePayerCovering;
    private String copay;
    private Date lastEligibilityCheckDate;
    private int[] selectedServices;
    private int[] selectedTasks;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
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

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getMedicaidId() {
        return medicaidId;
    }

    public void setMedicaidId(String medicaidId) {
        this.medicaidId = medicaidId;
    }

    public String getMedicareId() {
        return medicareId;
    }

    public void setMedicareId(String medicareId) {
        this.medicareId = medicareId;
    }

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public Date getInsuranceStartDate() {
        return insuranceStartDate;
    }

    public void setInsuranceStartDate(Date insuranceStartDate) {
        this.insuranceStartDate = insuranceStartDate;
    }

    public Date getInsuranceEndDate() {
        return insuranceEndDate;
    }

    public void setInsuranceEndDate(Date insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }

    public String getReferralNumber() {
        return referralNumber;
    }

    public void setReferralNumber(String referralNumber) {
        this.referralNumber = referralNumber;
    }

    public Date getReferralStartDate() {
        return referralStartDate;
    }

    public void setReferralStartDate(Date referralStartDate) {
        this.referralStartDate = referralStartDate;
    }

    public Date getReferralEndDate() {
        return referralEndDate;
    }

    public void setReferralEndDate(Date referralEndDate) {
        this.referralEndDate = referralEndDate;
    }

    public String getReferralSource() {
        return referralSource;
    }

    public void setReferralSource(String referralSource) {
        this.referralSource = referralSource;
    }

    public String getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(String admissionId) {
        this.admissionId = admissionId;
    }

    public Double getHourlyRate() {
        return HourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        HourlyRate = hourlyRate;
    }

    public Double getBillRate() {
        return billRate;
    }

    public void setBillRate(Double billRate) {
        this.billRate = billRate;
    }

    public String getAgencyLocation() {
        return agencyLocation;
    }

    public void setAgencyLocation(String agencyLocation) {
        this.agencyLocation = agencyLocation;
    }

    public String getClinicalManager() {
        return clinicalManager;
    }

    public void setClinicalManager(String clinicalManager) {
        this.clinicalManager = clinicalManager;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public String getMarketer() {
        return marketer;
    }

    public void setMarketer(String marketer) {
        this.marketer = marketer;
    }

    public Integer getDisasterLevelId() {
        return disasterLevelId;
    }

    public void setDisasterLevelId(Integer disasterLevelId) {
        this.disasterLevelId = disasterLevelId;
    }

    public Integer getDnrId() {
        return dnrId;
    }

    public void setDnrId(Integer dnrId) {
        this.dnrId = dnrId;
    }

    public Date getDnrDate() {
        return dnrDate;
    }

    public void setDnrDate(Date dnrDate) {
        this.dnrDate = dnrDate;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getDmeAndSupplies() {
        return dmeAndSupplies;
    }

    public void setDmeAndSupplies(String dmeAndSupplies) {
        this.dmeAndSupplies = dmeAndSupplies;
    }

    public String getNutritionalRequirements() {
        return nutritionalRequirements;
    }

    public void setNutritionalRequirements(String nutritionalRequirements) {
        this.nutritionalRequirements = nutritionalRequirements;
    }

    public String getSafetyMeasures() {
        return safetyMeasures;
    }

    public void setSafetyMeasures(String safetyMeasures) {
        this.safetyMeasures = safetyMeasures;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getMedicationStrength() {
        return medicationStrength;
    }

    public void setMedicationStrength(String medicationStrength) {
        this.medicationStrength = medicationStrength;
    }

    public String getMedicationDosage() {
        return medicationDosage;
    }

    public void setMedicationDosage(String medicationDosage) {
        this.medicationDosage = medicationDosage;
    }

    public String getMedicationRoute() {
        return medicationRoute;
    }

    public void setMedicationRoute(String medicationRoute) {
        this.medicationRoute = medicationRoute;
    }

    public String getMedicationFrequency() {
        return medicationFrequency;
    }

    public void setMedicationFrequency(String medicationFrequency) {
        this.medicationFrequency = medicationFrequency;
    }

    public String getMedicationComments() {
        return medicationComments;
    }

    public void setMedicationComments(String medicationComments) {
        this.medicationComments = medicationComments;
    }

    public Integer getMedicationClassification() {
        return medicationClassification;
    }

    public void setMedicationClassification(Integer medicationClassification) {
        this.medicationClassification = medicationClassification;
    }

    public Date getMedicationBeginDate() {
        return medicationBeginDate;
    }

    public void setMedicationBeginDate(Date medicationBeginDate) {
        this.medicationBeginDate = medicationBeginDate;
    }

    public Date getMedicationEndDate() {
        return medicationEndDate;
    }

    public void setMedicationEndDate(Date medicationEndDate) {
        this.medicationEndDate = medicationEndDate;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getReferenceFormat() {
        return referenceFormat;
    }

    public void setReferenceFormat(String referenceFormat) {
        this.referenceFormat = referenceFormat;
    }

    public String getReferenceMaximum() {
        return referenceMaximum;
    }

    public void setReferenceMaximum(String referenceMaximum) {
        this.referenceMaximum = referenceMaximum;
    }

    public String getReferenceModifier1() {
        return referenceModifier1;
    }

    public void setReferenceModifier1(String referenceModifier1) {
        this.referenceModifier1 = referenceModifier1;
    }

    public String getReferenceModifier2() {
        return referenceModifier2;
    }

    public void setReferenceModifier2(String referenceModifier2) {
        this.referenceModifier2 = referenceModifier2;
    }

    public String getReferenceModifier3() {
        return referenceModifier3;
    }

    public void setReferenceModifier3(String referenceModifier3) {
        this.referenceModifier3 = referenceModifier3;
    }

    public String getReferenceModifier4() {
        return referenceModifier4;
    }

    public void setReferenceModifier4(String referenceModifier4) {
        this.referenceModifier4 = referenceModifier4;
    }

    public Date getAuthorizationBeginDate() {
        return authorizationBeginDate;
    }

    public void setAuthorizationBeginDate(Date authorizationBeginDate) {
        this.authorizationBeginDate = authorizationBeginDate;
    }

    public Date getAuthorizationEndDate() {
        return authorizationEndDate;
    }

    public void setAuthorizationEndDate(Date authorizationEndDate) {
        this.authorizationEndDate = authorizationEndDate;
    }

    public String getAuthorizationComments() {
        return authorizationComments;
    }

    public void setAuthorizationComments(String authorizationComments) {
        this.authorizationComments = authorizationComments;
    }

    public String getLimitBy() {
        return limitBy;
    }

    public void setLimitBy(String limitBy) {
        this.limitBy = limitBy;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getNameOfThePayerCovering() {
        return nameOfThePayerCovering;
    }

    public void setNameOfThePayerCovering(String nameOfThePayerCovering) {
        this.nameOfThePayerCovering = nameOfThePayerCovering;
    }

    public String getCopay() {
        return copay;
    }

    public void setCopay(String copay) {
        this.copay = copay;
    }

    public Date getLastEligibilityCheckDate() {
        return lastEligibilityCheckDate;
    }

    public void setLastEligibilityCheckDate(Date lastEligibilityCheckDate) {
        this.lastEligibilityCheckDate = lastEligibilityCheckDate;
    }

    public int[] getSelectedServices() {
        return selectedServices;
    }

    public void setSelectedServices(int[] selectedServices) {
        this.selectedServices = selectedServices;
    }

    public int[] getSelectedTasks() {
        return selectedTasks;
    }

    public void setSelectedTasks(int[] selectedTasks) {
        this.selectedTasks = selectedTasks;
    }
}
