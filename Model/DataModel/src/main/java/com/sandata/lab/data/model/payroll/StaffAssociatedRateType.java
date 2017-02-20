package com.sandata.lab.data.model.payroll;

public enum StaffAssociatedRateType {

    Patient,
    Contract,
    Weekend,
    Holiday;

    public String value() {
        return name();
    }

    public static StaffAssociatedRateType fromValue(String v) {
        return valueOf(v);
    }
}
