package com.sandata.lab.rest.dashboard.model.enums;

public enum Columns {

    MV_SCHEDULE_SCHEDULE_DATE("SCHED_DATE"),
    MV_SCHEDULE_SCHEDULE_TOTAL_HOURS("SCHED_EVNT_TOTAL_HRS"),
    MV_VISIT_TOTAL_HOURS("VISIT_TOTAL_HRS"),
    MV_VISIT_END_DATE("VISIT_END_DATE"),
    AUTH_ID("AUTH_ID"),
    AUTH_SERVICE_END_DATE("AUTH_LMT_END_DATE"),
    MV_PR_PR_EXPORT_DATE("PR_EXPORT_DATE"),
    //	MV_PR_PR_EXPORT_DATE("WEEK_END_DATE"),
    MV_PR_PR_HRS("PR_HRS"),
    MV_PR_PR_AMT("PR_AMT"),
    PT_ID("PT_ID"),
    VISIT_SK("VISIT_SK"),
    VISIT_EXCEPTION_SK("VISIT_EXCP_SK"),
    VISIT_START_DATE("VISIT_START_DATE"),
    OPEN_ORD_COUNT("PT_ID"),
    OPEN_ORD_DATE("OPEN_ORD_DATE"),
    OUT_OF_COMPLIANCE_COUNT("COMP_IND"),
    OUT_OF_COMPLIANCE_DATE("STAFF_COMP_EXPR_DATE"),
    PT_NON_ELIGIB_COUNT("ELIG_SK"),
    PT_NON_ELIGIB_DATE("ELIG_TERM_DATE");

    private String value;

    public String getValue() {
        return value;
    }

    private Columns(String value) {
        this.value = value;
    }
}
