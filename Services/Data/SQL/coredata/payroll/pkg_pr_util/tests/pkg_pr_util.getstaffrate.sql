SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_cursor SYS_REFCURSOR;

  l_params PR_GET_STAFF_RATE_PARAMS_T;

  l_staff_rate STAFF_RATE%ROWTYPE;

BEGIN

  dbms_output.put_line ('*** START: PKG_PR_UTIL.GET_STAFF_RATE ***');

  --l_params := PR_GET_STAFF_RATE_PARAMS_T('2','2','2','900002014', '900225033', 'PCA', NULL, NULL, 2742309, TO_DATE('2016-09-11 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

  l_params := PR_GET_STAFF_RATE_PARAMS_T('1','1','1','20160822103402703', '20160822103404248', NULL, NULL, NULL, 77103, TO_DATE('2016-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

  l_cursor := COREDATA.PKG_PR_UTIL.GET_STAFF_RATE(l_params);

  IF l_cursor IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  LOOP
    FETCH l_cursor INTO l_staff_rate;

    EXIT WHEN l_cursor%NOTFOUND;

    dbms_output.put_line ('l_cursor: l_staff_rate.SVC_NAME = ' || l_staff_rate.SVC_NAME);
    dbms_output.put_line ('l_cursor: l_staff_rate.RATE_SUB_TYP_NAME = ' || l_staff_rate.RATE_SUB_TYP_NAME);
    dbms_output.put_line ('l_cursor: l_staff_rate.RATE_TYP_NAME = ' || l_staff_rate.RATE_TYP_NAME);
    dbms_output.put_line ('l_cursor: l_staff_rate.STAFF_RATE_AMT = ' || l_staff_rate.STAFF_RATE_AMT || CHR(13) || CHR(10));

  END LOOP;

  CLOSE l_cursor;

  dbms_output.put_line ('*** END: PKG_PR_UTIL.GET_STAFF_RATE ***' || CHR(13) || CHR(10));

  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  --WHEN OTHERS THEN
  --CLOSE l_cursor;
  --dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;
