SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;
  RATE_SUB_TYP BE_RATE.RATE_SUB_TYP_NAME%TYPE;

  V_AUTH_SVC_NAME AUTH_SVC.SVC_NAME%TYPE;
  V_RATE_TYP_NAME PR_RATE_MATRIX.RATE_TYP_NAME%TYPE;

BEGIN

  -- Test to make sure we get the service for the given visit
  V_RATE_TYP_NAME := COREDATA.PKG_PR_UTIL.GET_RATE_TYP_NAME('1', 'HOLIDAY', '20160721041745216', 68332, TO_DATE('2016-07-04 23:59:59', 'YYYY-MM-DD HH24:MI:SS'));

  IF V_RATE_TYP_NAME IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  IF V_RATE_TYP_NAME = 'HOURLY' THEN
    dbms_output.put_line('SUCCESS: Expected Result: V_RATE_TYP_NAME := *** ' || V_RATE_TYP_NAME || ' ***');
  ELSE
    dbms_output.put_line('FAILED: Expected Result: [HOURLY]: V_RATE_TYP_NAME := *** ' || V_RATE_TYP_NAME || ' ***');
  END IF;

  -- Test to make sure we get the service for the given visit
  V_AUTH_SVC_NAME := COREDATA.PKG_PR_UTIL.GET_AUTH_SVC('1', 68332, TO_DATE('2016-07-04 23:59:59', 'YYYY-MM-DD HH24:MI:SS'));

  IF V_AUTH_SVC_NAME IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  IF V_AUTH_SVC_NAME = 'RN' THEN
    dbms_output.put_line('SUCCESS: Expected Result: V_AUTH_SVC_NAME := *** ' || V_AUTH_SVC_NAME || ' ***');
  ELSE
    dbms_output.put_line('FAILED: Expected Result: [RN]: V_AUTH_SVC_NAME := *** ' || V_AUTH_SVC_NAME || ' ***');
  END IF;

  -- Test to make sure we get back RATE_SUB_TYP = REGULAR
  RATE_SUB_TYP := COREDATA.PKG_PR_UTIL.GET_RATE_SUB_TYP('1', TO_DATE('2016-07-25 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

  IF RATE_SUB_TYP IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  IF RATE_SUB_TYP = 'REGULAR' THEN
    dbms_output.put_line('SUCCESS: Expected Result: RATE_SUB_TYP := *** ' || RATE_SUB_TYP || ' ***');
  ELSE
    dbms_output.put_line('FAILED: Expected Result: [REGULAR]: RATE_SUB_TYP := *** ' || RATE_SUB_TYP || ' ***');
  END IF;

  -- Test to make sure we get back RATE_SUB_TYP = REGULAR
  RATE_SUB_TYP := COREDATA.PKG_PR_UTIL.GET_RATE_SUB_TYP('1', TO_DATE('2016-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

  IF RATE_SUB_TYP IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  IF RATE_SUB_TYP = 'REGULAR' THEN
    dbms_output.put_line('SUCCESS: Expected Result: RATE_SUB_TYP := *** ' || RATE_SUB_TYP || ' ***');
  ELSE
    dbms_output.put_line('FAILED: Expected Result: [REGULAR]: RATE_SUB_TYP := *** ' || RATE_SUB_TYP || ' ***');
  END IF;

  -- Test to make sure we get back RATE_SUB_TYP = WEEKEND
  RATE_SUB_TYP := COREDATA.PKG_PR_UTIL.GET_RATE_SUB_TYP('1', TO_DATE('2016-07-30 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

  IF RATE_SUB_TYP IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  IF RATE_SUB_TYP = 'WEEKEND' THEN
    dbms_output.put_line('SUCCESS: Expected Result: RATE_SUB_TYP := *** ' || RATE_SUB_TYP || ' ***');
  ELSE
    dbms_output.put_line('FAILED: Expected Result: [WEEKEND]: RATE_SUB_TYP := *** ' || RATE_SUB_TYP || ' ***');
  END IF;

  -- Test to make sure we get back RATE_SUB_TYP = WEEKEND
  RATE_SUB_TYP := COREDATA.PKG_PR_UTIL.GET_RATE_SUB_TYP('1', TO_DATE('2016-07-31 23:59:59', 'YYYY-MM-DD HH24:MI:SS'));

  IF RATE_SUB_TYP IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  IF RATE_SUB_TYP = 'WEEKEND' THEN
    dbms_output.put_line('SUCCESS: Expected Result: RATE_SUB_TYP := *** ' || RATE_SUB_TYP || ' ***');
  ELSE
    dbms_output.put_line('FAILED: Expected Result: [WEEKEND]: RATE_SUB_TYP := *** ' || RATE_SUB_TYP || ' ***');
  END IF;

  -- Test to make sure we get back RATE_SUB_TYP = HOLIDAY
  RATE_SUB_TYP := COREDATA.PKG_PR_UTIL.GET_RATE_SUB_TYP('1', TO_DATE('2016-07-04 23:59:59', 'YYYY-MM-DD HH24:MI:SS'));

  IF RATE_SUB_TYP IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  IF RATE_SUB_TYP = 'HOLIDAY' THEN
    dbms_output.put_line('SUCCESS: Expected Result: RATE_SUB_TYP := *** ' || RATE_SUB_TYP || ' ***');
  ELSE
    dbms_output.put_line('FAILED: Expected Result: [HOLIDAY]: RATE_SUB_TYP := *** ' || RATE_SUB_TYP || ' ***');
  END IF;

  EXCEPTION
    WHEN NULL_EXCEPTION THEN
      dbms_output.put_line('ERROR: RESULT == NULL');

    WHEN OTHERS THEN
      dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');
END;
