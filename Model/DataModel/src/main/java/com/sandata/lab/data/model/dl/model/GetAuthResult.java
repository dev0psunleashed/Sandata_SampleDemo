package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * Wrap the result objects of Authorization, Payer and Patient.
 *
 * @author David Rutgos
 */
public class GetAuthResult extends BaseObject {

    @SerializedName("Authorization")
    private Authorization authorization;

    @SerializedName("Payer")
    private Payer payer;

    @SerializedName("Patient")
    private Patient patient;

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
