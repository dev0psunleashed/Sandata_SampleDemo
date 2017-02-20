package com.sandata.lab.rules.visit.exception.service.utils.constants;

public class App {

    public enum VISIT_EXCEPTION {

        UNKNOWN_PATIENT(0,"Unknown Patient"),
        UNKNOWN_STAFF(1,"Unknown Staff"),
        VISITS_WITHOUT_CALLS(2,"Visits without any calls"),
        VISITS_WITHOUT_IN_CALL(3,"Visits with no in-calls"),
        VISITS_WITHOUT_OUT_CALL(4,"Visits with no out-calls"),
        UNSCHEDULED_VISITS(5,"Unscheduled visits"),
        UNMATCHED_PAYROLL_SCHED_HOURS(6,"Unmatched payroll and scheduled hours"),
        ACTUAL_HOURS_GREATER_THAN_SCHED(8,"Actual hours more than scheduled hours"),
        MISSING_TASKS(10,"Missing tasks"),
        MISSING_CRITICAL_TASKS(11,"Missing critical tasks"),
        LATE_IN_CALL(18,"Late in-call"),
        EARLY_OUT_CALL(19,"Early out-call"),
        SHORT_VISIT(20,"Short visit"),
        NO_SHOW_EXCEPTION(21,"No show exception"),
        MISSING_REASON_CODES(29,"Missing reason codes"),
        GPS_DISTANCE_EXCEPTION(26,"GPS Distance Exception"),
        EXTRANOUS_CALLS(36,"Extraneous calls");

        public final String name;
        public final int excpId;

        VISIT_EXCEPTION(final int excpId, final String name) {
            this.excpId = excpId;
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

}
