SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  v_rate_sub_typ BE_RATE.RATE_SUB_TYP_NAME%TYPE;

BEGIN

  v_rate_sub_typ := PKG_PR_UTIL.GET_RATE_SUB_TYP('1', TO_DATE('2016-08-22 23:59:59', 'YYYY-MM-DD HH24:MI:SS'));

  IF v_rate_sub_typ IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  IF v_rate_sub_typ = 'REGULAR' THEN
    dbms_output.put_line('SUCCESS: Expected Result: v_rate_sub_typ := *** ' || v_rate_sub_typ || ' ***');
  ELSE
    dbms_output.put_line('FAILED: Expected Result: [REGULAR]: v_rate_sub_typ := *** ' || v_rate_sub_typ || ' ***');
  END IF;

  EXCEPTION
    WHEN NULL_EXCEPTION THEN
      dbms_output.put_line('ERROR: RESULT == NULL');

    WHEN OTHERS THEN
      dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');
END;
