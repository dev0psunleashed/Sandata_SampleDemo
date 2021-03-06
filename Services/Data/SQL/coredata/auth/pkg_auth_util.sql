CREATE OR REPLACE PACKAGE PKG_AUTH_UTIL IS

  -- v_auth_end_time_str is a string in format of MM/DD/YYYY HH24:MI:SS
  FUNCTION CANCEL_SCHED_EVENTS_FOR_AUTH(v_be_id IN SCHED_EVNT.BE_ID%TYPE, v_patient_id IN SCHED_EVNT.PT_ID%TYPE, v_auth_end_time_str IN VARCHAR2) RETURN NUMBER;

END PKG_AUTH_UTIL;
/

CREATE OR REPLACE PACKAGE BODY PKG_AUTH_UTIL IS

  -- v_auth_end_time_str is a string in format of MM/DD/YYYY HH24:MI:SS
  FUNCTION CANCEL_SCHED_EVENTS_FOR_AUTH(v_be_id IN SCHED_EVNT.BE_ID%TYPE, v_patient_id IN SCHED_EVNT.PT_ID%TYPE, v_auth_end_time_str IN VARCHAR2) RETURN NUMBER
  AS
  BEGIN

    UPDATE SCHED_EVNT
    SET
      REC_UPDATE_TMSTP = CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP)AS DATE) ,
      REC_EFF_TMSTP = TO_DATE(v_auth_end_time_str, 'MM/DD/YYYY HH24:MI:SS'),
      SCHED_EVNT_STATUS = 'CANCELLED',
	  REC_TERM_TMSTP = CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP)AS DATE),
	  CURR_REC_IND = 0
    WHERE
      BE_ID = v_be_id
      AND PT_ID = v_patient_id
      AND TO_CHAR(SCHED_EVNT_START_DTIME, 'MM/DD/YYYY')  > v_auth_end_time_str
      AND SCHED_EVNT_STATUS = 'PENDING'
      AND (CURR_REC_IND = 1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31');

    RETURN SQL%ROWCOUNT;

  END CANCEL_SCHED_EVENTS_FOR_AUTH;

END PKG_AUTH_UTIL;
/
