package com.sandata.services.mobilehealth.data.models;

public enum StatusCode {

    STATUS_SUCCESS("COMPLETED"),
    STATUS_ERROR("ERROR");
    
    private String value;

    private StatusCode(String value) {
        this.value = value;
    }
    public final String getValue(){
        return value;
    }

}



