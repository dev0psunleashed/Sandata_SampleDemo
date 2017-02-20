SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_cursor SYS_REFCURSOR;

  l_inv_find_result INV_FIND_RESULT_T;

  l_inv_sk_array NUMBER_ARRAY;

  l_counter NUMBER(38,0);

BEGIN

  -- Init Object
  l_inv_find_result := INV_FIND_RESULT_T(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

  dbms_output.put_line ('*** START: PKG_BILLING_UTIL.GET_BRS_INV_DETAILS ***');

  l_inv_sk_array := NUMBER_ARRAY (5,1,2);

  l_cursor := COREDATA.PKG_BILLING_UTIL.GET_BRS_INV_DETAILS(l_inv_sk_array);

  IF l_cursor IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  LOOP
    FETCH l_cursor INTO
    l_inv_find_result.BE_LOC_ID,
    l_inv_find_result.INV_SK,
    l_inv_find_result.BE_LOB_ID,
    l_inv_find_result.PAYER_NAME,
    l_inv_find_result.CONTR_DESC,
    l_inv_find_result.INV_START_DATE,
    l_inv_find_result.INV_END_DATE,
    l_inv_find_result.INV_NUM,
    l_inv_find_result.INV_DATE,
    l_inv_find_result.PT_FIRST_NAME,
    l_inv_find_result.PT_LAST_NAME,
    l_inv_find_result.PT_INS_ID_NUM,
    l_inv_find_result.BE_NAME,
    l_inv_find_result.INV_TOTAL_AMT,
    l_inv_find_result.INV_SUBM_TYP_NAME;

    EXIT WHEN l_cursor%NOTFOUND;

    dbms_output.put_line ('l_cursor: BE_LOC_ID = ' || l_inv_find_result.BE_LOC_ID);
    dbms_output.put_line ('l_cursor: INV_SK = ' || l_inv_find_result.INV_SK);
    dbms_output.put_line ('l_cursor: BE_LOB_ID = ' || l_inv_find_result.BE_LOB_ID);
    dbms_output.put_line ('l_cursor: PAYER_NAME = ' || l_inv_find_result.PAYER_NAME);
    dbms_output.put_line ('l_cursor: CONTR_DESC = ' || l_inv_find_result.CONTR_DESC);
    dbms_output.put_line ('l_cursor: INV_START_DATE = ' || TO_CHAR(l_inv_find_result.INV_START_DATE, 'YYYY-MM-DD'));
    dbms_output.put_line ('l_cursor: INV_END_DATE = ' || TO_CHAR(l_inv_find_result.INV_END_DATE, 'YYYY-MM-DD'));
    dbms_output.put_line ('l_cursor: INV_NUM = ' || l_inv_find_result.INV_NUM);
    dbms_output.put_line ('l_cursor: INV_DATE = ' || TO_CHAR(l_inv_find_result.INV_DATE, 'YYYY-MM-DD'));
    dbms_output.put_line ('l_cursor: PT_FIRST_NAME = ' || l_inv_find_result.PT_FIRST_NAME);
    dbms_output.put_line ('l_cursor: PT_LAST_NAME = ' || l_inv_find_result.PT_LAST_NAME);
    dbms_output.put_line ('l_cursor: PT_INS_ID_NUM = ' || l_inv_find_result.PT_INS_ID_NUM);
    dbms_output.put_line ('l_cursor: BE_NAME = ' || l_inv_find_result.BE_NAME);
    dbms_output.put_line ('l_cursor: INV_TOTAL_AMT = ' || l_inv_find_result.INV_TOTAL_AMT);
    dbms_output.put_line ('l_cursor: INV_SUBM_TYP_NAME = ' || l_inv_find_result.INV_SUBM_TYP_NAME || CHR(13) || CHR(10));

  END LOOP;

  CLOSE l_cursor;

  dbms_output.put_line ('*** END: PKG_BILLING_UTIL.GET_BRS_INV_DETAILS ***' || CHR(13) || CHR(10));


  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  WHEN OTHERS THEN
  CLOSE l_cursor;
  dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;