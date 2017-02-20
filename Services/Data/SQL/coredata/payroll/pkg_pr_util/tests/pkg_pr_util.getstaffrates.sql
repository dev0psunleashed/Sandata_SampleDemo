SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_cursor SYS_REFCURSOR;

  c_be_rate SYS_REFCURSOR;
  c_staff_sup_rate SYS_REFCURSOR;
  c_staff_ass_rate SYS_REFCURSOR;
  c_pr_matrix SYS_REFCURSOR;

  l_params PR_GET_STAFF_RATE_PARAMS_T;

  l_staff_rate STAFF_RATE_T;

BEGIN

  dbms_output.put_line ('*** START: PKG_PR_UTIL.GET_STAFF_RATES ***');

  l_params := PR_GET_STAFF_RATE_PARAMS_T('1','1','1','998990228', '78786788', NULL, NULL, NULL, 766120, TO_DATE('2016-10-18 15:00:00', 'YYYY-MM-DD HH24:MI:SS'));

  l_staff_rate := STAFF_RATE_T(
    NULL, --STAFF_RATE_SK	NUMBER(38),
    NULL, --REC_CREATE_TMSTP DATE,
    NULL, --REC_UPDATE_TMSTP DATE,
    NULL, --REC_EFF_TMSTP DATE,
    NULL, --REC_TERM_TMSTP DATE,
    NULL, --REC_CREATED_BY	VARCHAR2(50 BYTE),
    NULL, --REC_UPDATED_BY	VARCHAR2(50 BYTE),
    NULL, --CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
    NULL, --CURR_REC_IND	NUMBER(1),
    NULL, --CHANGE_VERSION_ID	NUMBER(38),
    NULL, --BE_ID	VARCHAR2(50 BYTE),
    NULL, --STAFF_ID	VARCHAR2(50 BYTE),
    NULL, --STAFF_RATE_EFF_DATE DATE,
    NULL, --STAFF_RATE_TERM_DATE DATE,
    NULL, --SVC_NAME	VARCHAR2(20 BYTE),
    NULL, --RATE_SUB_TYP_NAME	VARCHAR2(50 BYTE),
    NULL, --RATE_TYP_NAME	VARCHAR2(20 BYTE),
    NULL, --RATE_QLFR_CODE	VARCHAR2(4 BYTE),
    NULL  --STAFF_RATE_AMT	NUMBER(7, 2)
  );

  l_cursor := COREDATA.PKG_PR_UTIL.GET_STAFF_RATES(l_params);

  IF l_cursor IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  LOOP
    FETCH l_cursor INTO
      c_be_rate,
      c_staff_sup_rate,
      c_staff_ass_rate,
      c_pr_matrix,
      l_staff_rate.STAFF_RATE_SK,
      l_staff_rate.REC_CREATE_TMSTP,
      l_staff_rate.REC_UPDATE_TMSTP,
      l_staff_rate.REC_EFF_TMSTP,
      l_staff_rate.REC_TERM_TMSTP,
      l_staff_rate.REC_CREATED_BY,
      l_staff_rate.REC_UPDATED_BY,
      l_staff_rate.CHANGE_REASON_MEMO,
      l_staff_rate.CURR_REC_IND,
      l_staff_rate.CHANGE_VERSION_ID,
      l_staff_rate.BE_ID,
      l_staff_rate.STAFF_ID,
      l_staff_rate.STAFF_RATE_EFF_DATE,
      l_staff_rate.STAFF_RATE_TERM_DATE,
      l_staff_rate.SVC_NAME,
      l_staff_rate.RATE_SUB_TYP_NAME,
      l_staff_rate.RATE_TYP_NAME,
      l_staff_rate.RATE_QLFR_CODE,
      l_staff_rate.STAFF_RATE_AMT;

    EXIT WHEN l_cursor%NOTFOUND;

    CLOSE c_be_rate;
    CLOSE c_staff_sup_rate;
    CLOSE c_staff_ass_rate;
    CLOSE c_pr_matrix;

    dbms_output.put_line ('l_cursor: l_staff_rate.SVC_NAME = ' || l_staff_rate.SVC_NAME);
    dbms_output.put_line ('l_cursor: l_staff_rate.RATE_SUB_TYP_NAME = ' || l_staff_rate.RATE_SUB_TYP_NAME);
    dbms_output.put_line ('l_cursor: l_staff_rate.RATE_TYP_NAME = ' || l_staff_rate.RATE_TYP_NAME);
    dbms_output.put_line ('l_cursor: l_staff_rate.STAFF_RATE_AMT = ' || l_staff_rate.STAFF_RATE_AMT || CHR(13) || CHR(10));

  END LOOP;

  CLOSE l_cursor;

  dbms_output.put_line ('*** END: PKG_PR_UTIL.GET_STAFF_RATES ***' || CHR(13) || CHR(10));

  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  --WHEN OTHERS THEN
  --CLOSE l_cursor;
  --dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;
