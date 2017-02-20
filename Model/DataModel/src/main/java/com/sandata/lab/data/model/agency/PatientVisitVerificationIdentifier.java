package com.sandata.lab.data.model.agency;

/**
 * <p>PatientVisitVerificationIdentifier.java</p>
 * <p>Visit verification settings call matching patient identifier.</p>
 * <p>Date: 10/19/16</p>
 * <p>Time: 12:32 PM</p>
 *
 * @author jasonscott
 */
public enum PatientVisitVerificationIdentifier {
    PATIENT_ID("PT_ID"),
    PATIENT_VV_ID("PT_VV_ID"),
    PATIENT_TIN("PT_TIN");

    private final String patientVisitVerificationIdentifier;

    PatientVisitVerificationIdentifier(final String patientVisitVerificationIdentifier) {
        this.patientVisitVerificationIdentifier = patientVisitVerificationIdentifier;
    }

    @Override
    public String toString() {
        return this.patientVisitVerificationIdentifier;
    }
}