package com.sandata.lab.security.auth.services.oracle.enums;

public enum Columns {

    META_TENANT_BE_XWALK_TENANT_SK("APP_TENANT_SK"),
    META_APP_USER_SK("APP_USER_SK"),
    META_TENANT_BE_XWALK_BE_ID("BE_ID"),
    META_USER_FIRST_NAME("USER_FIRST_NAME"),
    META_USER_LAST_NAME("USER_LAST_NAME"),
    META_USER_NAME("USER_NAME"),
    META_ROLE_NAME("ROLE_NAME"),
    META_USER_GUID("USER_GUID"),
    META_KEY("KEY"),
    META_KEY_NAME("KEY_NAME"),
    META_VALUE("VAL");

    private String value;
    
    public String getValue() {
        return value;
    }

    private Columns(String value){
        this.value = value;
    }
}
