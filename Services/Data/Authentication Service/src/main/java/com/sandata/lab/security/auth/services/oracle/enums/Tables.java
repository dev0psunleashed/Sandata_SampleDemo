package com.sandata.lab.security.auth.services.oracle.enums;

public enum Tables {
    META_TENANT_BE_XWALK("APP_TENANT_BE_XWALK"),
    META_APP_USER("APP_USER"),
    META_APP_USER_ROLE("APP_USER_ROLE"),
    META_APP_USER_SETTING("APP_USER_SETTING"),
    META_USER_KEY_CONF("APP_USER_KEY_CONF"),
    META_SYS_KEY_CONF("APP_SYS_KEY_CONF");

    private String value;

    public String getValue(){
        return this.value;
    }
    private Tables(String value){
        this.value = value;
    }
}
