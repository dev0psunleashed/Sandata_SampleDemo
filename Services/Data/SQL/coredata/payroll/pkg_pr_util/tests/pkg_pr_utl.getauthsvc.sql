SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  v_auth_svc_name AUTH_SVC.SVC_NAME%TYPE;

BEGIN

  v_auth_svc_name := COREDATA.PKG_PR_UTIL.GET_AUTH_SVC('1', 77103, TO_DATE('2016-08-22 23:59:59', 'YYYY-MM-DD HH24:MI:SS'));

  IF v_auth_svc_name IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  IF v_auth_svc_name = 'RN' THEN
    dbms_output.put_line('SUCCESS: Expected Result: v_auth_svc_name := *** ' || v_auth_svc_name || ' ***');
  ELSE
    dbms_output.put_line('FAILED: Expected Result: [RN]: v_auth_svc_name := *** ' || v_auth_svc_name || ' ***');
  END IF;

  EXCEPTION
    WHEN NULL_EXCEPTION THEN
      dbms_output.put_line('ERROR: RESULT == NULL');

    WHEN OTHERS THEN
      dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');
END;
