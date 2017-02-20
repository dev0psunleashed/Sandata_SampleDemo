package com.sandata.lab.rest.payroll.model;

/**
 * Date: 8/18/16
 * Time: 5:30 PM
 */

public enum PayrollFindStatus {

    PENDING,
    PAID,
    ALL;

    private PayrollFindStatus() {
    }

    public String value() {
        return this.name();
    }

    public static PayrollFindStatus fromValue(String v) {
        return valueOf(v);
    }
}
