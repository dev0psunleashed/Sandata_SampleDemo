package com.sandata.lab.data.model.dl.model.extended.ar;

import org.springframework.beans.BeanUtils;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.Invoice;

/**
 * Extends the Invoice entity to provide the UI with some more properties.
 */
public class InvoiceExt extends Invoice {
    private static final long serialVersionUID = 2699957347215755321L;

    public InvoiceExt(Invoice invoice) {
        BeanUtils.copyProperties(invoice, this);
    }

    @SerializedName("PatientLastName")
    private String patientLastName;

    @SerializedName("PatientFirstName")
    private String patientFirstName;

    /**
     * @return the patientLastName
     */
    public String getPatientLastName() {
        return patientLastName;
    }

    /**
     * @param patientLastName the patientLastName to set
     */
    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    /**
     * @return the patientFirstName
     */
    public String getPatientFirstName() {
        return patientFirstName;
    }

    /**
     * @param patientFirstName the patientFirstName to set
     */
    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }
}
