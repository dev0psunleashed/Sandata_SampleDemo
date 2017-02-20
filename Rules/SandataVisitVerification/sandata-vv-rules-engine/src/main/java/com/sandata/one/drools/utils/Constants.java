package com.sandata.one.drools.utils;

import java.math.BigInteger;

public class Constants {
	
    public static final int FALSE = 0;
    public static final int TRUE = 1;
    public static final int UNKNOWN = 2;
    public static final String ALL_TIME_RULE_NAME="Run all the time to control and send to FUSE once.";
    
    public static final String COMMA = ",";
    
    public static enum EXCEPTION_TYPE {

    	UNKNOWN_PATIENT                       (                            "UNKNOWN_PATIENT", BigInteger.valueOf(0)  ),
    	UNKNOWN_STAFF                         (                              "UNKNOWN_STAFF", BigInteger.valueOf(1)  ),
    	VISITS_WITHOUT_ANY_CALLS              (                   "VISITS_WITHOUT_ANY_CALLS", BigInteger.valueOf(2)  ),
    	MISSING_IN_CALL                       (                            "MISSING_IN_CALL", BigInteger.valueOf(3)  ),
    	MISSING_OUT_CALL                      (                           "MISSING_OUT_CALL", BigInteger.valueOf(4)  ),
    	UNSCHEDULED_VISITS                    (                         "UNSCHEDULED_VISITS", BigInteger.valueOf(5)  ),
    	UNMATCHED_PAYROLL_AND_SCHEDULED_HOURS (      "UNMATCHED_PAYROLL_AND_SCHEDULED_HOURS", BigInteger.valueOf(6)  ),
    	ACTUAL_HOURS_MORE_THAN_SCHEDULED_HOURS(     "ACTUAL_HOURS_MORE_THAN_SCHEDULED_HOURS", BigInteger.valueOf(8)  ),
    	MISSING_TASKS                         (                              "MISSING_TASKS", BigInteger.valueOf(10)  ),
    	MISSING_CRITICAL_TASKS                (                     "MISSING_CRITICAL_TASKS", BigInteger.valueOf(11)  ),
    	UNMATCHED_PATIENT_ID_PHONE            (                 "UNMATCHED_PATIENT_ID_PHONE", BigInteger.valueOf(15)  ),
    	LATE_IN_CALL                          (                               "LATE_IN_CALL", BigInteger.valueOf(18)  ),
    	EARLY_OUT_CALL                        (                             "EARLY_OUT_CALL", BigInteger.valueOf(19)  ),
    	SHORT_VISIT                           (                                "SHORT_VISIT", BigInteger.valueOf(20)  ),
    	NO_SHOW_EXCEPTION                     (                          "NO_SHOW_EXCEPTION", BigInteger.valueOf(21)  ),
    	MISSING_SERVICE                       (                            "MISSING_SERVICE", BigInteger.valueOf(23)  ),
    	GPS_DISTANCE_EXCEPTION                (                     "GPS_DISTANCE_EXCEPTION", BigInteger.valueOf(25)  ),
    	PATIENT_VALIDATION_EXCEPTION          (               "PATIENT_VALIDATION_EXCEPTION", BigInteger.valueOf(28)  ),
    	MISSING_REASON_CODES                  (                       "MISSING_REASON_CODES", BigInteger.valueOf(29)  ),
    	PATIENT_SIGNATURE_EXCEPTION		      (		           "PATIENT_SIGNATURE_EXCEPTION", BigInteger.valueOf(39)  );
    	
        private final String name;
        private final BigInteger exceptionId;

        private EXCEPTION_TYPE(final String name, final BigInteger exceptionId) {
            this.name = name;
            this.exceptionId = exceptionId;
        }


		public String getName() {
			return name;
		}


		public BigInteger getExceptionId() {
			return exceptionId;
		}


		@Override
        public String toString() {
            return name + "-" + exceptionId;
        }
    }    
}
