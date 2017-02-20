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

package com.sandata.lab.data.model.wcf.patient;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;

/**
 * Models the WCF Patient class.
 * <p/>
 *
 * @author David Rutgos
 */
public class WcfPatient extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("PatientId")
    private int patientId;

    @SerializedName("AgencyId")
    private int agencyId;

    @SerializedName("MemberNumber")
    private String memberNumber;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("MiddleInitial")
    private String middleInitial;

    @SerializedName("DateofBirth")
    private String dateOfBirth;

    @SerializedName("Age")
    private int age;

    @SerializedName("MaritalStatusId")
    private Integer maritalStatusId;

    @SerializedName("GenderId")
    private Integer genderId;

    @SerializedName("SSN")
    private String ssn;

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

    @SerializedName("HomePhone")
    private String homePhone;

    @SerializedName("CellPhone")
    private String cellPhone;

    @SerializedName("Email")
    private String email;

    @SerializedName("Insurance")
    private String insurance;

    @SerializedName("InsuranceID")
    private String insuranceId;

    @SerializedName("MedicaidID")
    private String medicaidId;

    @SerializedName("MedicareID")
    private String medicareId;

    @SerializedName("MedicalRecordNumber")
    private String medicalRecordNumber;

    @SerializedName("InsuranceStartDate")
    private Date insuranceStartDate;

    @SerializedName("InsuranceEndDate")
    private Date insuranceEndDate;

    @SerializedName("ReferralNumber")
    private String referralNumber;

    @SerializedName("ReferralStartDate")
    private Date referralStartDate;

    @SerializedName("ReferralEndDate")
    private Date referralEndDate;

    @SerializedName("ReferralSource")
    private String referralSource;

    @SerializedName("AdmissionID")
    private String admissionId;

    @SerializedName("HourlyRate")
    private Double HourlyRate;

    @SerializedName("BillRate")
    private Double billRate;

    @SerializedName("AgencyLocation")
    private String agencyLocation;

    @SerializedName("ClinicalManager")
    private String clinicalManager;

    @SerializedName("Coordinator")
    private String coordinator;

    @SerializedName("Marketer")
    private String marketer;

    @SerializedName("DisasterLevelId")
    private Integer disasterLevelId;

    @SerializedName("DNRId")
    private Integer dnrId;

    @SerializedName("DNRDate")
    private Date dnrDate;

    @SerializedName("Allergies")
    private String allergies;

    @SerializedName("DMEandSupplies")
    private String dmeAndSupplies;

    @SerializedName("NutritionalRequirements")
    private String nutritionalRequirements;

    @SerializedName("SafetyMeasures")
    private String safetyMeasures;

    @SerializedName("Medication")
    private String medication;

    @SerializedName("MedicationStrengthId")
    private Integer medicationStrengthId;

    @SerializedName("MedicationDosage")
    private String medicationDosage;

    @SerializedName("MedicationRouteId")
    private Integer medicationRouteId;

    @SerializedName("MedicationFrequency")
    private String medicationFrequency;

    @SerializedName("MedicationComments")
    private String medicationComments;

    @SerializedName("MedicationClassificationId")
    private Integer medicationClassificationId;

    @SerializedName("MedicationBeginDate")
    private Date medicationBeginDate;

    @SerializedName("MedicationEndDate")
    private Date medicationEndDate;

    @SerializedName("ReferenceNumber")
    private String referenceNumber;

    @SerializedName("ReferenceFormatId")
    private Integer referenceFormatId;

    @SerializedName("ReferenceMaximum")
    private String referenceMaximum;

    @SerializedName("ReferenceModifier1")
    private String referenceModifier1;

    @SerializedName("ReferenceModifier2")
    private String referenceModifier2;

    @SerializedName("ReferenceModifier3")
    private String referenceModifier3;

    @SerializedName("ReferenceModifier4")
    private String referenceModifier4;

    @SerializedName("AuthorizationBeginDate")
    private String authorizationBeginDate;

    @SerializedName("AuthorizationEndDate")
    private String authorizationEndDate;

    @SerializedName("AuthorizationComments")
    private String authorizationComments;

    @SerializedName("LimitById")
    private Integer limitById;

    @SerializedName("Total")
    private String total;

    @SerializedName("Task")
    private String task;

    @SerializedName("Schedule")
    private String schedule;

    @SerializedName("EligibilityId")
    private Integer eligibilityId;

    @SerializedName("NameOfThePayerCovering")
    private String nameOfThePayerCovering;

    @SerializedName("Copay")
    private String copay;

    @SerializedName("LastEligibilitycheckDate")
    private String lastEligibilityCheckDate;

    @SerializedName("SelectedServices")
    private int[] selectedServices;

    @SerializedName("SelectedTasks")
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Integer maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getEthnicityId() {
        return ethnicityId;
    }

    public void setEthnicityId(Integer ethnicityId) {
        this.ethnicityId = ethnicityId;
    }

    public Integer getReligionId() {
        return religionId;
    }

    public void setReligionId(Integer religionId) {
        this.religionId = religionId;
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

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
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

    public Integer getMedicationStrengthId() {
        return medicationStrengthId;
    }

    public void setMedicationStrengthId(Integer medicationStrengthId) {
        this.medicationStrengthId = medicationStrengthId;
    }

    public String getMedicationDosage() {
        return medicationDosage;
    }

    public void setMedicationDosage(String medicationDosage) {
        this.medicationDosage = medicationDosage;
    }

    public Integer getMedicationRouteId() {
        return medicationRouteId;
    }

    public void setMedicationRouteId(Integer medicationRouteId) {
        this.medicationRouteId = medicationRouteId;
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

    public Integer getMedicationClassificationId() {
        return medicationClassificationId;
    }

    public void setMedicationClassificationId(Integer medicationClassificationId) {
        this.medicationClassificationId = medicationClassificationId;
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

    public Integer getReferenceFormatId() {
        return referenceFormatId;
    }

    public void setReferenceFormatId(Integer referenceFormatId) {
        this.referenceFormatId = referenceFormatId;
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

    public String getAuthorizationBeginDate() {
        return authorizationBeginDate;
    }

    public void setAuthorizationBeginDate(String authorizationBeginDate) {
        this.authorizationBeginDate = authorizationBeginDate;
    }

    public String getAuthorizationEndDate() {
        return authorizationEndDate;
    }

    public void setAuthorizationEndDate(String authorizationEndDate) {
        this.authorizationEndDate = authorizationEndDate;
    }

    public String getAuthorizationComments() {
        return authorizationComments;
    }

    public void setAuthorizationComments(String authorizationComments) {
        this.authorizationComments = authorizationComments;
    }

    public Integer getLimitById() {
        return limitById;
    }

    public void setLimitById(Integer limitById) {
        this.limitById = limitById;
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

    public Integer getEligibilityId() {
        return eligibilityId;
    }

    public void setEligibilityId(Integer eligibilityId) {
        this.eligibilityId = eligibilityId;
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

    public String getLastEligibilityCheckDate() {
        return lastEligibilityCheckDate;
    }

    public void setLastEligibilityCheckDate(String lastEligibilityCheckDate) {
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
