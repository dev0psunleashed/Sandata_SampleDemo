package com.sandata.lab.rules.vv.model;

import java.io.Serializable;
import java.math.BigInteger;

public enum VisitState {

	//to mark VisitEventFact as being processed
	PROCESSING ,
	//Schedule states
	INVALID, LOADED, IN_PROGRESS, ENDED, UNPLANNED, NO_SCHEDULES, CREATE_UNPLANNED_VISIT, INSERTED,
	EXTRANEOUS,
	//Call and Schedule states
	NOT_MATCHED, MATCHED,
	//rules specific
    CALL_IN_INSERTED, CALL_OUT_INSERTED, BOTH_INSERTED,
	//aggregation
	AGG_WAITING_FOR_RESPONSE, AGG_INSERTED_FROM_RESPONSE,
	//staff specific states
	FAILED_9_OF_9_MATCH,
	STAFF_MATCHED, STAFF_MATCHED_7_OF_9, STAFF_MATCHED_6_OF_9, FAILED_7_OF_9_MATCH,
	FAILED_6_OF_9_MATCH, RETRACTED, POSSIBLE_MATCH, POSSIBLE_MATCH_FROM_RESPONSE, POSSIBLE_MATCH_WAITING_FOR_RESPONSE,
	CONTENDER, CONTENDER_FROM, INVALID_STAFF, TRY_FUZZY_MATCH_FOR_STAFF_ID, CONTENDER_TO,
	//Exception States
	SCHEDULED,
	VISIT_SCHEDULED_WITH_NO_CALLS,
	UNKNOWN_STAFF,
	UNKNOWN_PATIENT,
	MATCHED_PATIENT,
	UNSCHEDULED,
	NO_IN_CALLS,
	NO_OUT_CALLS,
	MISSING_TASKS,
	MISSING_CRITICAL_TASKS,
	MISSING_REASON_CODES,
	ACTUAL_HOURS_EXCEED_SCHEDULED_HOURS,
	UNMATCHED_PAYROLL_AND_SCHEDULED_HOURS,
	VISIT_LATE_IN_CALL,
	VISIT_EARLY_OUT_CALL,
	VISIT_SHORT,
	VISIT_NO_SHOW, GPS_EXCP_IN, GPS_EXCP_OUT, TESTING, CONTENDER_SCHEDULED, CONTENDER_UNSCHEDULED;

	public static VisitState getVisitExceptionFromExpectedSk(BigInteger sk) {
		switch(sk.intValue()) {
			case 0:
				return UNKNOWN_PATIENT;
			case 1:
				return UNKNOWN_STAFF;
			case 2:
				return VISIT_SCHEDULED_WITH_NO_CALLS;
			case 3:
				return NO_IN_CALLS;
			case 4:
				return NO_OUT_CALLS;
			case 5:
				return UNSCHEDULED;
			case 8:
				return ACTUAL_HOURS_EXCEED_SCHEDULED_HOURS;
			case 10:
				return MISSING_TASKS;
			case 11:
				return MISSING_CRITICAL_TASKS;
			case 18:
				return VISIT_LATE_IN_CALL;
			case 19:
				return VISIT_EARLY_OUT_CALL;
			case 20:
				return VISIT_SHORT;
			case 21:
				return VISIT_NO_SHOW;
			case 29:
				return MISSING_REASON_CODES;
			case 6:
				return UNMATCHED_PAYROLL_AND_SCHEDULED_HOURS;
			case 100:
				return GPS_EXCP_IN;
			case 101:
				return GPS_EXCP_OUT;
			default:
				return INVALID;
		}
	}
	public BigInteger getVisitException() {
		switch(this) {
			case UNKNOWN_PATIENT:
				return BigInteger.valueOf(0L);
			case UNKNOWN_STAFF:
				return BigInteger.valueOf(1L);
			case VISIT_SCHEDULED_WITH_NO_CALLS:
				return BigInteger.valueOf(2L);
			case NO_IN_CALLS:
				return BigInteger.valueOf(3L);
			case NO_OUT_CALLS:
				return BigInteger.valueOf(4L);
			case UNSCHEDULED:
				return BigInteger.valueOf(5L);
			case ACTUAL_HOURS_EXCEED_SCHEDULED_HOURS:
				return BigInteger.valueOf(8L);
			case MISSING_TASKS:
				return BigInteger.valueOf(10L);
			case MISSING_CRITICAL_TASKS:
				return BigInteger.valueOf(11L);
			case VISIT_LATE_IN_CALL:
				return BigInteger.valueOf(18L);
			case VISIT_EARLY_OUT_CALL:
				return BigInteger.valueOf(19L);
			case VISIT_SHORT:
				return BigInteger.valueOf(20L);
			case VISIT_NO_SHOW:
				return BigInteger.valueOf(21L);
			case MISSING_REASON_CODES:
				return BigInteger.valueOf(29L);
			case UNMATCHED_PAYROLL_AND_SCHEDULED_HOURS:
				return BigInteger.valueOf(6L);
			case GPS_EXCP_IN:
				return BigInteger.valueOf(100L);
			case GPS_EXCP_OUT:
				return BigInteger.valueOf(101L);
			default:
				return BigInteger.valueOf(-1L);

		}
	}
	public String getVisitExceptionName() {
		switch(this) {
			case UNKNOWN_PATIENT:
				return "Unknown Patient";
			case UNKNOWN_STAFF:
				return "Unknown Staff";
			case VISIT_SCHEDULED_WITH_NO_CALLS:
				return "Visits (Scheduled visit) without any calls";
			case NO_IN_CALLS:
				return "Visits without in-calls";
			case NO_OUT_CALLS:
				return "Visits without out-calls";
			case UNSCHEDULED:
				return "Unscheduled Visits";
			case ACTUAL_HOURS_EXCEED_SCHEDULED_HOURS:
				return "Actual Hours More Than Scheduled Hours";
			case MISSING_TASKS:
				return "Missing Tasks";
			case MISSING_CRITICAL_TASKS:
				return "Missing Critical Tasks";
			case VISIT_LATE_IN_CALL:
				return "Late InCall";
			case VISIT_EARLY_OUT_CALL:
				return "Early OutCall";
			case VISIT_SHORT:
				return "Short Visit";
			case VISIT_NO_SHOW:
				return "No Show Exception";
			case MISSING_REASON_CODES:
				return "Missing Reason Codes";
			case UNMATCHED_PAYROLL_AND_SCHEDULED_HOURS:
				return "Unmatched Payroll And Scheduled Hours";
			case GPS_EXCP_IN:
				return "GPS Exception on call in";
			case GPS_EXCP_OUT:
				return "GPS Exception on call out";
			default:
				return "Kabaam";

		}
	}
}

