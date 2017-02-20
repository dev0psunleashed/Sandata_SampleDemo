package com.sandata.lab.rest.dashboard.model.enums;

public enum Tables {
    MV_SCHED("COREDATAMART.MV_SCHED"),
    MV_VISIT_VERIF("COREDATAMART.MV_VISIT_VERIF"),
    MV_AUTH_EXPIRE("COREDATAMART.MV_AUTH_EXPIRE"),
	MV_PR("COREDATAMART.MV_PR"),
    MV_PR_PENDING("COREDATAMART.MV_PR_PENDING"),
    MV_PR_OT("COREDATAMART.MV_PR_OT"),
    MV_OPEN_ORDER("COREDATAMART.MV_OPEN_ORD"),
    MV_VISIT_EXCP("COREDATAMART.MV_VISIT_EXCP"),
    MV_COMP("COREDATAMART.MV_COMP"),
    MV_NONELIGIB("COREDATAMART.MV_PT_NOT_ELIG");

    private String value;

    public String getValue(){
        return this.value;
    }
    private Tables(String value){
        this.value = value;
    }
}
