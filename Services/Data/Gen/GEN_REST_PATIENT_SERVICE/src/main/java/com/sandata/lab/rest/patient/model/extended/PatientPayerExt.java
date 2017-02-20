package com.sandata.lab.rest.patient.model.extended;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.Contract;
import com.sandata.lab.data.model.dl.model.Eligibility;
import com.sandata.lab.data.model.dl.model.PatientPayer;
import com.sandata.lab.data.model.dl.model.PayerLineOfBusinessList;

/**
 * Extends the Patient Payer entity to add Eligibility
 */
public class PatientPayerExt extends PatientPayer {
    private static final long serialVersionUID = 1L;

    public PatientPayerExt(PatientPayer patientPayer) {
        BeanUtils.copyProperties(patientPayer, this);

        // manually copy Boolean properties as BeanUtils.copyproperties does not work for Boolean
        // see https://stackoverflow.com/questions/11332820/beanutils-setproperty-not-working-for-boolean-values
        setPatientSelfPayIndicator(patientPayer.isPatientSelfPayIndicator());
        setPatientGaurantorIndicator(patientPayer.isPatientGaurantorIndicator());
    }

    @SerializedName("Eligibility")
    private Eligibility eligibility;

    @SerializedName("Contract")
    private List<Contract> contract;

    @SerializedName("PayerLineOfBusinessList")
    private List<PayerLineOfBusinessList> payerLineOfBusinessList;

    public Eligibility getEligibility() {
        return eligibility;
    }

    public void setEligibility(Eligibility eligibility) {
        this.eligibility = eligibility;
    }

    public List<Contract> getContract() {
        if (this.contract == null) {
            this.contract = new ArrayList<Contract>();
        }
        return this.contract;
    }

    public List<PayerLineOfBusinessList> getPayerLineOfBusinessList() {
        if (this.payerLineOfBusinessList == null) {
            this.payerLineOfBusinessList = new ArrayList<PayerLineOfBusinessList>();
        }
        return this.payerLineOfBusinessList;
    }

    public PatientPayer getPatientPayer() {
        PatientPayer patientPayer = new PatientPayer();
        BeanUtils.copyProperties(this, patientPayer);

        // manually copy Boolean properties as BeanUtils.copyproperties does not work for Boolean
        // see https://stackoverflow.com/questions/11332820/beanutils-setproperty-not-working-for-boolean-values
        patientPayer.setPatientSelfPayIndicator(this.isPatientSelfPayIndicator());
        patientPayer.setPatientGaurantorIndicator(this.isPatientGaurantorIndicator());

        return patientPayer;
    }
}
