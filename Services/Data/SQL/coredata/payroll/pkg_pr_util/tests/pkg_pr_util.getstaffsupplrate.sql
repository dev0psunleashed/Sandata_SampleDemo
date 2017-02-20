SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_cursor SYS_REFCURSOR;

  l_params PR_GET_STAFF_RATE_PARAMS_T;
  l_staff_suppl_rate STAFF_SUPPL_RATE%ROWTYPE;

BEGIN

  dbms_output.put_line ('*** START: PKG_PR_UTIL.GET_STAFF_SUPPL_RATE ***');

  l_params := PR_GET_STAFF_RATE_PARAMS_T('1','1','1','20160822103402703', '20160822103404248', NULL, NULL, NULL, 77103, TO_DATE('2016-08-22 23:59:59', 'YYYY-MM-DD HH24:MI:SS'));

  l_cursor := COREDATA.PKG_PR_UTIL.GET_STAFF_SUPPL_RATE(l_params);

  IF l_cursor IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  LOOP
    FETCH l_cursor INTO l_staff_suppl_rate;

    EXIT WHEN l_cursor%NOTFOUND;

    dbms_output.put_line ('l_cursor: l_staff_suppl_rate.SVC_NAME = ' || l_staff_suppl_rate.SVC_NAME);
    dbms_output.put_line ('l_cursor: l_staff_suppl_rate.RATE_SUB_TYP_NAME = ' || l_staff_suppl_rate.RATE_SUB_TYP_NAME);
    dbms_output.put_line ('l_cursor: l_staff_suppl_rate.RATE_TYP_NAME = ' || l_staff_suppl_rate.RATE_TYP_NAME);
    dbms_output.put_line ('l_cursor: l_staff_suppl_rate.STAFF_SUPPL_RATE = ' || l_staff_suppl_rate.STAFF_SUPPL_RATE || CHR(13) || CHR(10));

  END LOOP;

  CLOSE l_cursor;

  dbms_output.put_line ('*** END: PKG_PR_UTIL.GET_STAFF_SUPPL_RATE ***' || CHR(13) || CHR(10));

  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  WHEN OTHERS THEN
  CLOSE l_cursor;
  dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;
