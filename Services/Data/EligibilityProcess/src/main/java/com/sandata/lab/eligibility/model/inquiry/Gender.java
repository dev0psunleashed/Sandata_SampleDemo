package com.sandata.lab.eligibility.model.inquiry;

public enum Gender {
    Male,
    Female,
    Unknown;

    public String value() {
        return name();
    }

    public static Gender fromValue(String v) {
        return valueOf(v);
    }
}