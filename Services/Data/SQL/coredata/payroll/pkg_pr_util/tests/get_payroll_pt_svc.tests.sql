SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_cursor SYS_REFCURSOR;

  l_visit_sk VISIT.VISIT_SK%TYPE;
  l_pt_id PT.PT_ID%TYPE;
  l_pt_fn PT.PT_FIRST_NAME%TYPE;
  l_pt_ln PT.PT_LAST_NAME%TYPE;
  l_svc SVC.SVC_NAME%TYPE;

BEGIN

  dbms_output.put_line ('*** START: PKG_PR_UTIL.GET_PAYROLL_PT_SVC ***');

  l_cursor := COREDATA.PKG_PR_UTIL.GET_PAYROLL_PT_SVC(4236);

  IF l_cursor IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  LOOP
    FETCH l_cursor INTO l_visit_sk,l_pt_id,l_pt_fn,l_pt_ln,l_svc;

    EXIT WHEN l_cursor%NOTFOUND;

    dbms_output.put_line ('l_cursor: l_visit_sk = ' || l_visit_sk);
    dbms_output.put_line ('l_cursor: l_pt_id = ' || l_pt_id);
    dbms_output.put_line ('l_cursor: l_pt_fn = ' || l_pt_fn);
    dbms_output.put_line ('l_cursor: l_pt_ln = ' || l_pt_ln);
    dbms_output.put_line ('l_cursor: l_svc = ' || l_svc || CHR(13) || CHR(10));

  END LOOP;

  CLOSE l_cursor;

  dbms_output.put_line ('*** END: PKG_PR_UTIL.GET_PAYROLL_PT_SVC ***' || CHR(13) || CHR(10));

  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  WHEN OTHERS THEN
  CLOSE l_cursor;
  dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;