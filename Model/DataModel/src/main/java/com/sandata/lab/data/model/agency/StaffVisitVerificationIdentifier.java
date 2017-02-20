package com.sandata.lab.data.model.agency;

/**
 * <p>StaffVisitVerificationIdentifier.java</p>
 * <p>Visit verification settings call matching staff identifier.</p>
 * <p>Date: 10/19/16</p>
 * <p>Time: 12:32 PM</p>
 *
 * @author jasonscott
 */
public enum StaffVisitVerificationIdentifier {
    STAFF_ID("STAFF_ID"),
    STAFF_VV_ID("STAFF_VV_ID"),
    STAFF_TIN("STAFF_TIN");

    private final String staffVisitVerificationIdentifier;

    StaffVisitVerificationIdentifier(final String staffVisitVerificationIdentifier) {
        this.staffVisitVerificationIdentifier = staffVisitVerificationIdentifier;
    }

    @Override
    public String toString() {
        return this.staffVisitVerificationIdentifier;
    }
}
