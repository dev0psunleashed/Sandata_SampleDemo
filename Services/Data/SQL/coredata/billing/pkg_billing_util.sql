CREATE OR REPLACE TYPE INV_FIND_RESULT_T AS OBJECT (

  BE_LOC_ID	VARCHAR2(50 BYTE),
  INV_SK	NUMBER(38),
  BE_LOB_ID	VARCHAR2(50 BYTE),
  PAYER_NAME	VARCHAR2(100 BYTE),
  CONTR_DESC	VARCHAR2(250 BYTE),
  INV_START_DATE DATE,
  INV_END_DATE DATE,
  INV_NUM	VARCHAR2(50 BYTE),
  INV_DATE DATE,
  PT_FIRST_NAME	VARCHAR2(200 BYTE),
  PT_LAST_NAME	VARCHAR2(200 BYTE),
  PT_INS_ID_NUM	VARCHAR2(50 BYTE),
  BE_NAME	VARCHAR2(100 BYTE),
  INV_TOTAL_AMT	NUMBER(6, 2),
  INV_SUBM_TYP_NAME	VARCHAR2(50 BYTE)

);
/

CREATE OR REPLACE TYPE BILLING_FIND_RESULT_T AS OBJECT (

  BE_LOC_ID	VARCHAR2(50 BYTE),
  BILLING_SK	NUMBER(38),
  BE_LOB_ID	VARCHAR2(50 BYTE),
  PAYER_NAME	VARCHAR2(100 BYTE),
  CONTR_DESC	VARCHAR2(250 BYTE),
  BILLING_START_DATE DATE,
  BILLING_END_DATE DATE,
  BILLING_DATE DATE,
  PT_FIRST_NAME	VARCHAR2(200 BYTE),
  PT_LAST_NAME	VARCHAR2(200 BYTE),
  PT_INS_ID_NUM	VARCHAR2(50 BYTE),
  BE_NAME	VARCHAR2(100 BYTE),
  BILLING_TOTAL_AMT	NUMBER(6, 2),
  BILLING_SUBM_TYP_NAME	VARCHAR2(50 BYTE)

);
/

CREATE OR REPLACE PACKAGE PKG_BILLING_UTIL IS

  -- Returns the Visit Date (Adjusted if exists / otherwise Actual)
  FUNCTION GET_BILLING_DATE (l_visit_sk VISIT.VISIT_SK%TYPE) RETURN DATE;

  -- Returns the Billable Review Summary Grouped By Line Of Business
  FUNCTION GET_BRS_FOR_LOB (sk_array NUMBER_ARRAY) RETURN SYS_REFCURSOR;

  -- Returns the Billable Review Summary Grouped By Payer
  FUNCTION GET_BRS_FOR_PAYER (sk_array NUMBER_ARRAY) RETURN SYS_REFCURSOR;

  -- Returns the Billable Review Summary Grouped By Payer for given Submission Type
  FUNCTION GET_BRS_PAYER_FOR_SUBM_TYP (sk_array NUMBER_ARRAY, SUBM_TYP BILLING.BILLING_SUBM_TYP_NAME%TYPE) RETURN SYS_REFCURSOR;

  -- Returns the Billable Review Summary Grouped By Submission Type
  FUNCTION GET_BRS_FOR_SUBM_TYP (sk_array NUMBER_ARRAY) RETURN SYS_REFCURSOR;

  -- Returns the Billable Review Summary Billing Details
  FUNCTION GET_BRS_BILLING_DETAILS (sk_array NUMBER_ARRAY) RETURN SYS_REFCURSOR;

  -- Returns the Complete Billable Review Summary
  FUNCTION BILLABLE_REVIEW_SUMMARY (sk_array NUMBER_ARRAY) RETURN SYS_REFCURSOR;

END PKG_BILLING_UTIL;
/

CREATE OR REPLACE PACKAGE BODY PKG_BILLING_UTIL IS

  FUNCTION GET_BILLING_DATE (l_visit_sk VISIT.VISIT_SK%TYPE) RETURN DATE
  AS

    l_visit_act_date VISIT.VISIT_ACT_START_TMSTP%TYPE;
    l_visit_adj_date VISIT.VISIT_ADJ_START_TMSTP%TYPE;

    BEGIN

      SELECT VISIT_ACT_START_TMSTP,VISIT_ADJ_START_TMSTP
      INTO l_visit_act_date,l_visit_adj_date
      FROM VISIT
      WHERE VISIT_SK = l_visit_sk;

      IF l_visit_adj_date IS NOT NULL THEN
        RETURN l_visit_adj_date;
      END IF;

      RETURN l_visit_act_date;

      EXCEPTION
      WHEN NO_DATA_FOUND THEN
      RETURN NULL;

      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: GET_BILLING_DATE: UNHANDLED EXCEPTION!');

    END GET_BILLING_DATE;

  FUNCTION GET_BRS_FOR_LOB (sk_array NUMBER_ARRAY) RETURN SYS_REFCURSOR
  AS

    result_Cursor SYS_REFCURSOR;

    BEGIN

      OPEN result_Cursor FOR
      SELECT BE_LOB_ID AS LINE_OF_BUSINESS,
             Count(*) AS TOTAL_INVOICES_BILLED,
             ROUND(SUM(BILLING_TOTAL_AMT),2) AS TOTAL_AMOUNT_BILLED,
             ROUND(SUM(BILLING_TOTAL_UNIT/3600),0) AS BILLED_HOURS
      FROM BILLING
      WHERE BILLING_SK IN (SELECT column_value FROM TABLE(sk_array))
            AND BILLING_TOTAL_AMT IS NOT NULL
            AND BILLING_TOTAL_UNIT IS NOT NULL
      GROUP BY BE_LOB_ID;

      RETURN result_Cursor;

      EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: GET_BRS_FOR_LOB: UNHANDLED EXCEPTION!');

    END GET_BRS_FOR_LOB;

  FUNCTION GET_BRS_FOR_PAYER (sk_array NUMBER_ARRAY) RETURN SYS_REFCURSOR
  AS

    result_Cursor SYS_REFCURSOR;

    BEGIN

      OPEN result_Cursor FOR
      SELECT PAYER_NAME AS PAYER,
             Count(*) AS TOTAL_INVOICES_BILLED,
             ROUND(SUM(BILLING_TOTAL_AMT),2) AS TOTAL_AMOUNT_BILLED,
             ROUND(SUM(BILLING_TOTAL_UNIT/3600),0) AS BILLED_HOURS
      FROM BILLING T1
        INNER JOIN (SELECT BE_ID,PAYER_ID,PAYER_NAME
                    FROM PAYER) T2
          ON T1.BE_ID = T2.BE_ID AND T1.PAYER_ID = T2.PAYER_ID
      WHERE T1.BILLING_SK IN (SELECT column_value FROM TABLE(sk_array))
            AND BILLING_TOTAL_AMT IS NOT NULL
            AND BILLING_TOTAL_UNIT IS NOT NULL
      GROUP BY PAYER_NAME;

      RETURN result_Cursor;

      EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: GET_BRS_FOR_PAYER: UNHANDLED EXCEPTION!');

    END GET_BRS_FOR_PAYER;

  FUNCTION GET_BRS_PAYER_FOR_SUBM_TYP (sk_array NUMBER_ARRAY, SUBM_TYP BILLING.BILLING_SUBM_TYP_NAME%TYPE) RETURN SYS_REFCURSOR
  AS

    result_Cursor SYS_REFCURSOR;

    BEGIN

      OPEN result_Cursor FOR
      SELECT PAYER_NAME,T1.PAYER_ID,
        Count(*) AS TOTAL_INVOICES_BILLED,
        ROUND(SUM(BILLING_TOTAL_AMT),2) AS TOTAL_AMOUNT_BILLED,
        ROUND(SUM(BILLING_TOTAL_UNIT/3600),0) AS BILLED_HOURS
      FROM BILLING T1
        INNER JOIN (SELECT BE_ID,PAYER_ID,PAYER_NAME
                    FROM PAYER) T2
          ON T1.BE_ID = T2.BE_ID AND T1.PAYER_ID = T2.PAYER_ID
      WHERE T1.BILLING_SK IN (SELECT column_value FROM TABLE(sk_array))
            AND BILLING_TOTAL_AMT IS NOT NULL
            AND BILLING_TOTAL_UNIT IS NOT NULL
            AND UPPER(BILLING_SUBM_TYP_NAME) = SUBM_TYP
      GROUP BY PAYER_NAME,T1.PAYER_ID;

      RETURN result_Cursor;

      EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: GET_BRS_PAYER_FOR_SUBM_TYP: UNHANDLED EXCEPTION!');

    END GET_BRS_PAYER_FOR_SUBM_TYP;

  FUNCTION GET_BRS_FOR_SUBM_TYP (sk_array NUMBER_ARRAY) RETURN SYS_REFCURSOR
  AS

    result_Cursor SYS_REFCURSOR;

    BEGIN

      OPEN result_Cursor FOR
      SELECT BILLING_SUBM_TYP_NAME AS SUBMISSION_TYPE,
             Count(*) AS TOTAL_INVOICES_BILLED,
             ROUND(SUM(BILLING_TOTAL_AMT),2) AS TOTAL_AMOUNT_BILLED,
             ROUND(SUM(BILLING_TOTAL_UNIT/3600),0) AS BILLED_HOURS
      FROM BILLING
      WHERE BILLING_SK IN (SELECT column_value FROM TABLE(sk_array))
            AND BILLING_TOTAL_AMT IS NOT NULL
            AND BILLING_TOTAL_UNIT IS NOT NULL
      GROUP BY BILLING_SUBM_TYP_NAME;

      RETURN result_Cursor;

      EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: GET_BRS_FOR_SUBM_TYP: UNHANDLED EXCEPTION!');

    END GET_BRS_FOR_SUBM_TYP;

  FUNCTION GET_BRS_BILLING_DETAILS (sk_array NUMBER_ARRAY) RETURN SYS_REFCURSOR
  AS

    result_Cursor SYS_REFCURSOR;

    BEGIN

      OPEN result_Cursor FOR
      SELECT T1.BE_LOC_ID,T1.BILLING_SK,T1.BE_LOB_ID,T3.PAYER_NAME,T4.CONTR_DESC,T1.BILLING_START_DATE,T1.BILLING_END_DATE,
        T1.BILLING_DATE,T5.PT_FIRST_NAME,T5.PT_LAST_NAME,T1.PT_INS_ID_NUM,T6.BE_NAME,
        T1.BILLING_TOTAL_AMT,T1.BILLING_SUBM_TYP_NAME,BIX.INV_NUM
      FROM BILLING T1

        -- Invoice Number
        LEFT JOIN (SELECT BE_ID,INV_NUM,PAYER_ID,CONTR_ID,PT_ID
                   FROM BILLING_INV_XWALK
                   WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) BIX
          ON T1.BE_ID = BIX.BE_ID AND T1.PAYER_ID = BIX.PAYER_ID AND T1.CONTR_ID = BIX.CONTR_ID
             AND T1.PT_ID = BIX.PT_ID

        -- Payer Name
        LEFT JOIN (SELECT BE_ID,PAYER_ID,PAYER_NAME
                   FROM PAYER
                   WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3
          ON T1.BE_ID = T3.BE_ID AND T1.PAYER_ID = T3.PAYER_ID

        -- Contract Description
        LEFT JOIN (SELECT BE_ID,PAYER_ID,CONTR_ID,CONTR_DESC
                   FROM CONTR
                   WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4
          ON T1.BE_ID = T4.BE_ID AND T1.PAYER_ID = T4.PAYER_ID AND T1.CONTR_ID = T4.CONTR_ID

        -- Patient First/Last Name
        LEFT JOIN (SELECT BE_ID,PT_ID,PT_FIRST_NAME,PT_LAST_NAME
                   FROM PT
                   WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T5
          ON T1.BE_ID = T5.BE_ID AND T1.PT_ID = T5.PT_ID

        -- Location
        LEFT JOIN (SELECT BE_ID,BE_NAME
                   FROM BE
                   WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T6
          ON T1.BE_LOC_ID = T6.BE_ID

      WHERE T1.BILLING_SK IN (SELECT column_value FROM TABLE(sk_array))

      ORDER BY PAYER_NAME;

      RETURN result_Cursor;

      EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: GET_BRS_BILLING_DETAILS: UNHANDLED EXCEPTION!');

    END GET_BRS_BILLING_DETAILS;

  FUNCTION BILLABLE_REVIEW_SUMMARY (sk_array NUMBER_ARRAY) RETURN SYS_REFCURSOR
  AS

    result_Cursor SYS_REFCURSOR;

    BEGIN

      OPEN result_Cursor FOR
      SELECT TO_DATE(TO_CHAR(CURRENT_TIMESTAMP, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS') AS SUBMISSION_DATE,
             Count(*) AS TOTAL_INVOICES_BILLED,
             ROUND(SUM(BILLING_TOTAL_AMT),2) AS TOTAL_AMOUNT_BILLED,
             ROUND(SUM(BILLING_TOTAL_UNIT/3600),0) AS BILLED_HOURS,
             PKG_BILLING_UTIL.GET_BRS_FOR_LOB(sk_array) AS LOB_CURSOR_RESULT,
             PKG_BILLING_UTIL.GET_BRS_FOR_PAYER(sk_array) AS PAYER_CURSOR_RESULT,
             PKG_BILLING_UTIL.GET_BRS_FOR_SUBM_TYP(sk_array) AS SUBM_TYP_CURSOR_RESULT,
             PKG_BILLING_UTIL.GET_BRS_BILLING_DETAILS(sk_array) AS INV_DET_CURSOR_RESULT
      FROM BILLING
      WHERE BILLING_SK IN (SELECT column_value FROM TABLE(sk_array))
            AND BILLING_TOTAL_AMT IS NOT NULL
            AND BILLING_TOTAL_UNIT IS NOT NULL;

      RETURN result_Cursor;

      EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: BILLABLE_REVIEW_SUMMARY: UNHANDLED EXCEPTION!');

    END BILLABLE_REVIEW_SUMMARY;

END PKG_BILLING_UTIL;
/
