SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_cursor SYS_REFCURSOR;

  l_params PR_GET_STAFF_RATE_PARAMS_T;
  l_pr_rate_matrix PR_RATE_MATRIX%ROWTYPE;

BEGIN

  dbms_output.put_line ('*** START: PKG_PR_UTIL.GET_PR_RATE_MATRIX ***');

  l_params := PR_GET_STAFF_RATE_PARAMS_T('1','1','1','20160822103402703', '20160822103404248', NULL, NULL, NULL, 77103, TO_DATE('2016-08-22 23:59:59', 'YYYY-MM-DD HH24:MI:SS'));

  l_cursor := COREDATA.PKG_PR_UTIL.GET_PR_RATE_MATRIX(l_params);

  IF l_cursor IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  LOOP
    FETCH l_cursor INTO l_pr_rate_matrix;

    EXIT WHEN l_cursor%NOTFOUND;

    dbms_output.put_line ('l_cursor: l_pr_rate_matrix.SVC_NAME = ' || l_pr_rate_matrix.SVC_NAME);
    dbms_output.put_line ('l_cursor: l_pr_rate_matrix.RATE_SUB_TYP_NAME = ' || l_pr_rate_matrix.RATE_SUB_TYP_NAME);
    dbms_output.put_line ('l_cursor: l_pr_rate_matrix.RATE_TYP_NAME = ' || l_pr_rate_matrix.RATE_TYP_NAME);
    dbms_output.put_line ('l_cursor: l_pr_rate_matrix.RATE_AMT = ' || l_pr_rate_matrix.RATE_AMT || CHR(13) || CHR(10));

  END LOOP;

  CLOSE l_cursor;

  dbms_output.put_line ('*** END: PKG_PR_UTIL.GET_PR_RATE_MATRIX ***' || CHR(13) || CHR(10));

  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  WHEN OTHERS THEN
  CLOSE l_cursor;
  dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;
