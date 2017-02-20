package com.sandata.one.aggregator.lookup.model;

import com.google.gson.annotations.SerializedName;

public class PatientLookup {

    @SerializedName("PatientId")
    private String patientId;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("MiddleName")
    private String middleName;

    @SerializedName("MedicaidId")
    private String medicaidId;

    /**
     * @return the patientId
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the medicaidId
     */
    public String getMedicaidId() {
        return medicaidId;
    }

    /**
     * @param medicaidId the medicaidId to set
     */
    public void setMedicaidId(String medicaidId) {
        this.medicaidId = medicaidId;
    }
}
