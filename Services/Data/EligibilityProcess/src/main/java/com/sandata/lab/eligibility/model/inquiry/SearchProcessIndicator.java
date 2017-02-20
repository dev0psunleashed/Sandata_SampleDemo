package com.sandata.lab.eligibility.model.inquiry;

public enum SearchProcessIndicator {
    Medicaid,
    SelfPay,
    Medicare,
    Schedule;

    public String value() {
        return name();
    }

    public static SearchProcessIndicator fromValue(String v) {
        return valueOf(v);
    }
}
