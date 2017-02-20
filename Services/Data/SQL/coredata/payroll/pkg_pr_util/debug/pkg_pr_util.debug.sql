CREATE OR REPLACE TYPE PR_FIND_PARAMS_T AS OBJECT (

  BE_ID	VARCHAR2(50 BYTE),
  PT_ID	VARCHAR2(50 BYTE),
  PT_FIRST_NAME	VARCHAR2(200 BYTE),
  PT_LAST_NAME	VARCHAR2(200 BYTE),
  STAFF_ID	VARCHAR2(50 BYTE),
  STAFF_FIRST_NAME	VARCHAR2(200 BYTE),
  STAFF_LAST_NAME	VARCHAR2(200 BYTE),
  PR_FROM_WEEK_END_DATE DATE,
  PR_TO_WEEK_END_DATE DATE,
  SVC_NAME	VARCHAR2(500 BYTE),
  PAY_HRS NUMBER(8,0),
  PAY_HRS_FILTER VARCHAR2(100 BYTE),
  RATE_AMT	NUMBER(7,2),
  STATUS	VARCHAR2(10 BYTE),
  CHECK_NUM	VARCHAR2(50 BYTE),
  CHECK_DATE DATE,
  CHECK_AMOUNT NUMBER(7, 2),
  FROM_ROW NUMBER(38,0),
  TO_ROW NUMBER (38,0),
  ORDER_BY_COL VARCHAR2(30 BYTE),
  ORDER_BY_DIR VARCHAR2(4 BYTE),
  RATE_TYP_NAME VARCHAR2(20 BYTE)
);
/

CREATE OR REPLACE TYPE PR_GET_STAFF_RATE_PARAMS_T AS OBJECT (

  BE_ID	VARCHAR2(50 BYTE),
  BE_LOC_ID VARCHAR2(50 BYTE),
  BE_LOB_ID VARCHAR2(50 BYTE),
  PT_ID	VARCHAR2(50 BYTE),
  STAFF_ID	VARCHAR2(50 BYTE),
  SVC_NAME	VARCHAR2(20 BYTE),
  RATE_TYP_NAME	VARCHAR2(20 BYTE),
  RATE_SUB_TYP_NAME	VARCHAR2(50 BYTE),
  VISIT_SK NUMBER(38,0),
  VISIT_DATE DATE
);
/

CREATE OR REPLACE PACKAGE PKG_PR_UTIL_DEBUG IS

  -- Get Payroll for SK
  FUNCTION GET_PAYROLL (l_tss_sk TIMESHEET_SUMMARY.TIMESHEET_SUMMARY_SK%TYPE) RETURN SYS_REFCURSOR;

  -- Find Payroll
  FUNCTION FIND_PAYROLL (l_params PR_FIND_PARAMS_T, v_svc_array STRING_ARRAY) RETURN SYS_REFCURSOR;

  -- Do Services Exist for Given Timesheet Summary
  FUNCTION SERVICES_EXISTS (v_tss_sk TIMESHEET_SUMMARY.TIMESHEET_SUMMARY_SK%TYPE, v_svc_array STRING_ARRAY) RETURN NUMBER;

  -- Does the Rate Type Exist for Given Timesheet Summary
  FUNCTION RATE_TYPE_EXISTS (v_tss_sk TIMESHEET_SUMMARY.TIMESHEET_SUMMARY_SK%TYPE, v_rate_typ AUTH_SVC.RATE_TYP_NAME%TYPE) RETURN NUMBER;

  -- Get the Patient and Services for a given Timesheet Summary SK
  FUNCTION GET_PAYROLL_PT_SVC (l_tss_sk TIMESHEET_SUMMARY.TIMESHEET_SUMMARY_SK%TYPE) RETURN SYS_REFCURSOR;

  /** Staff Rate **/
  FUNCTION GET_BE_RATE (l_params PR_GET_STAFF_RATE_PARAMS_T) RETURN SYS_REFCURSOR;
  FUNCTION GET_STAFF_SUPPL_RATE (l_params PR_GET_STAFF_RATE_PARAMS_T) RETURN SYS_REFCURSOR;
  FUNCTION GET_STAFF_ASSOC_RATE (l_params PR_GET_STAFF_RATE_PARAMS_T) RETURN SYS_REFCURSOR;
  FUNCTION GET_PR_RATE_MATRIX (l_params PR_GET_STAFF_RATE_PARAMS_T) RETURN SYS_REFCURSOR;
  FUNCTION GET_STAFF_RATE (v_params PR_GET_STAFF_RATE_PARAMS_T) RETURN SYS_REFCURSOR;
  FUNCTION GET_STAFF_RATES (v_params PR_GET_STAFF_RATE_PARAMS_T) RETURN SYS_REFCURSOR;

  /** Rate Types **/
  FUNCTION GET_AUTH_RATE_TYP (v_be_id IN BE.BE_ID%TYPE, v_visit_sk IN VISIT.VISIT_SK%TYPE, visit_start_date IN DATE) RETURN BE_RATE.RATE_TYP_NAME%TYPE;

  -- Get the payroll rate type for the auth/payer/contract/agency
  FUNCTION GET_RATE_TYP_NAME (v_be_id IN BE.BE_ID%TYPE, v_rate_sub_typ_name IN BE_RATE.RATE_SUB_TYP_NAME%TYPE, v_pt_id IN PT.PT_ID%TYPE, v_visit_sk IN VISIT.VISIT_SK%TYPE, visit_start_date IN DATE) RETURN BE_RATE.RATE_TYP_NAME%TYPE;

  /** Service **/
  FUNCTION GET_AUTH_SVC (v_be_id IN BE.BE_ID%TYPE, v_visit_sk IN VISIT.VISIT_SK%TYPE, visit_start_date IN DATE) RETURN AUTH_SVC.SVC_NAME%TYPE;

  /** Rate Sub Types **/
  FUNCTION HOLIDAY_RATE_SUB_TYP (v_be_id IN BE.BE_ID%TYPE, visit_start_date IN DATE) RETURN BE_RATE.RATE_SUB_TYP_NAME%TYPE;

  FUNCTION WEEKEND_RATE_SUB_TYP (v_be_id IN BE.BE_ID%TYPE, visit_start_date IN DATE) RETURN BE_RATE.RATE_SUB_TYP_NAME%TYPE;

  -- Return Regular/Weekend/Holiday (Rate Sub Type Name) https://mnt-ers-ts01.sandata.com/object/view.spg?key=325851
  FUNCTION GET_RATE_SUB_TYP (v_be_id IN BE.BE_ID%TYPE, visit_start_date IN DATE) RETURN BE_RATE.RATE_SUB_TYP_NAME%TYPE;

END PKG_PR_UTIL_DEBUG;
/

CREATE OR REPLACE PACKAGE BODY PKG_PR_UTIL_DEBUG IS

  FUNCTION RATE_TYPE_EXISTS (v_tss_sk TIMESHEET_SUMMARY.TIMESHEET_SUMMARY_SK%TYPE, v_rate_typ AUTH_SVC.RATE_TYP_NAME%TYPE) RETURN NUMBER
  AS
    l_result NUMBER;

    BEGIN
      SELECT 1 INTO l_result
      FROM TIMESHEET_DET TD_T1

      INNER JOIN (SELECT VISIT_SK,PT_ID FROM VISIT) TD_T2
      ON TD_T1.VISIT_SK = TD_T2.VISIT_SK

      INNER JOIN (SELECT PT_PAYER_SK,BE_ID,PT_ID,PAYER_ID,CONTR_ID,PT_PAYER_EFF_DATE,PT_PAYER_TERM_DATE
        FROM PT_PAYER WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) TD_T3
      ON TD_T1.BE_ID = TD_T3.BE_ID AND TD_T2.PT_ID = TD_T3.PT_ID
        AND  TD_T1.TIMESHEET_DATE BETWEEN TD_T3.PT_PAYER_EFF_DATE AND TD_T3.PT_PAYER_TERM_DATE
        AND TD_T3.CONTR_ID IS NOT NULL

      INNER JOIN (SELECT BE_ID,AUTH_SK,AUTH_ID,PT_ID,PAYER_ID,AUTH_START_TMSTP,AUTH_END_TMSTP
        FROM AUTH WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) TD_T4
      ON TD_T3.BE_ID = TD_T4.BE_ID AND TD_T3.PT_ID = TD_T4.PT_ID AND TD_T3.PAYER_ID = TD_T4.PAYER_ID
        AND  TD_T1.TIMESHEET_DATE BETWEEN TD_T4.AUTH_START_TMSTP AND TD_T4.AUTH_END_TMSTP

      INNER JOIN (SELECT AUTH_SK,SVC_NAME,RATE_TYP_NAME FROM AUTH_SVC) TD_T5
      ON TD_T4.AUTH_SK = TD_T5.AUTH_SK

      WHERE TD_T1.TIMESHEET_SUMMARY_SK = v_tss_sk AND UPPER(RATE_TYP_NAME) = v_rate_typ;

      RETURN l_result;

    END RATE_TYPE_EXISTS;

  FUNCTION SERVICES_EXISTS (v_tss_sk TIMESHEET_SUMMARY.TIMESHEET_SUMMARY_SK%TYPE, v_svc_array STRING_ARRAY) RETURN NUMBER
  AS
    l_cursor SYS_REFCURSOR;
    l_cursor_id NUMBER;

    l_sql CLOB;

    l_result NUMBER;

    BEGIN

      --dbms_output.put_line('DEBUG: SERVICES_EXISTS: v_services = ' || v_services);

      l_sql := 'SELECT 1 FROM TIMESHEET_DET TD_T1

                  INNER JOIN (SELECT VISIT_SK,PT_ID FROM VISIT) TD_T2
                  ON TD_T1.VISIT_SK = TD_T2.VISIT_SK

                  INNER JOIN (SELECT PT_PAYER_SK,BE_ID,PT_ID,PAYER_ID,CONTR_ID,PT_PAYER_EFF_DATE,PT_PAYER_TERM_DATE
                    FROM PT_PAYER WHERE (TO_CHAR(REC_TERM_TMSTP, ''YYYY-MM-DD'') = ''9999-12-31'' AND CURR_REC_IND = 1)) TD_T3
                  ON TD_T1.BE_ID = TD_T3.BE_ID AND TD_T2.PT_ID = TD_T3.PT_ID
                    AND  TD_T1.TIMESHEET_DATE BETWEEN TD_T3.PT_PAYER_EFF_DATE AND TD_T3.PT_PAYER_TERM_DATE
                    AND TD_T3.CONTR_ID IS NOT NULL

                  INNER JOIN (SELECT BE_ID,AUTH_SK,AUTH_ID,PT_ID,PAYER_ID,AUTH_START_TMSTP,AUTH_END_TMSTP
                    FROM AUTH WHERE (TO_CHAR(REC_TERM_TMSTP, ''YYYY-MM-DD'') = ''9999-12-31'' AND CURR_REC_IND = 1)) TD_T4
                  ON TD_T3.BE_ID = TD_T4.BE_ID AND TD_T3.PT_ID = TD_T4.PT_ID AND TD_T3.PAYER_ID = TD_T4.PAYER_ID
                    AND  TD_T1.TIMESHEET_DATE BETWEEN TD_T4.AUTH_START_TMSTP AND TD_T4.AUTH_END_TMSTP

                  INNER JOIN (SELECT AUTH_SK,SVC_NAME FROM AUTH_SVC) TD_T5
                  ON TD_T4.AUTH_SK = TD_T5.AUTH_SK

                  WHERE TD_T1.TIMESHEET_SUMMARY_SK = :b_tss_sk AND TD_T5.SVC_NAME IN (SELECT column_value FROM TABLE(:b_svc_array))';

      l_cursor_id := DBMS_SQL.OPEN_CURSOR;

      DBMS_SQL.PARSE(l_cursor_id, l_sql, DBMS_SQL.NATIVE);
      DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_tss_sk', v_tss_sk);
      DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_svc_array', v_svc_array);

      l_result := DBMS_SQL.EXECUTE(l_cursor_id);

      dbms_output.put_line('INFO: SERVICES_EXISTS: l_result = [' || l_result || ']');

      l_cursor := DBMS_SQL.TO_REFCURSOR(l_cursor_id);

      IF l_cursor IS NULL THEN
        dbms_output.put_line('WARN: SERVICES_EXISTS: l_cursor = NULL');
        RETURN NULL;
      END IF;

      FETCH l_cursor INTO l_result;
      CLOSE l_cursor;

      RETURN l_result;

    --EXCEPTION
    --  WHEN OTHERS THEN
    --  dbms_output.put_line('ERROR: SERVICES_EXISTS: UNHANDLED EXCEPTION!');

    END SERVICES_EXISTS;

  FUNCTION GET_PAYROLL (l_tss_sk TIMESHEET_SUMMARY.TIMESHEET_SUMMARY_SK%TYPE) RETURN SYS_REFCURSOR
  AS
    result_Cursor SYS_REFCURSOR;

    BEGIN
      OPEN result_Cursor FOR
      SELECT
            T1.*,
            T2.STAFF_FIRST_NAME,
            T2.STAFF_LAST_NAME,
            T2.STAFF_ID,
            T4.EFT_TXN_ID,
            T4.CHECK_NUM,
            T4.CHECK_DATE,
            T4.NET_PAY_AMT,
            T4.GROSS_PAY_AMT,
            PKG_PR_UTIL_DEBUG.GET_PAYROLL_PT_SVC(l_tss_sk) AS TIMESHEET_DET_CURSOR

      FROM TIMESHEET_SUMMARY T1

      INNER JOIN (SELECT BE_ID,STAFF_ID,STAFF_FIRST_NAME,STAFF_LAST_NAME
        FROM STAFF
          WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2
      ON T1.BE_ID = T2.BE_ID AND T1.STAFF_ID = T2.STAFF_ID

      LEFT JOIN (SELECT TIMESHEET_SUMMARY_SK,INTF_ID,STAFF_ID,BE_LOB_ID,BE_ID
        FROM PR_OUTPUT
          WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3
      ON T1.TIMESHEET_SUMMARY_SK = T3.TIMESHEET_SUMMARY_SK

      LEFT JOIN (SELECT INTF_ID,STAFF_ID,BE_ID,WEEK_END_DATE,CHECK_NUM,CHECK_DATE,GROSS_PAY_AMT,EFT_TXN_ID,
                  PR_INPUT_SK,PR_INPUT_ID,NET_PAY_AMT
        FROM PR_INPUT
          WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4
      ON T3.BE_ID = T4.BE_ID AND T3.INTF_ID = T4.INTF_ID AND T3.STAFF_ID = T4.STAFF_ID
        AND T4.WEEK_END_DATE = T1.TIMESHEET_WEEK_END_DATE

      WHERE T1.TIMESHEET_SUMMARY_SK = l_tss_sk;

      RETURN result_Cursor;

      EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: GET_PAYROLL: UNHANDLED EXCEPTION!');
    END GET_PAYROLL;

  FUNCTION FIND_PAYROLL (l_params PR_FIND_PARAMS_T, v_svc_array STRING_ARRAY) RETURN SYS_REFCURSOR
  AS

    result_Cursor SYS_REFCURSOR;

    l_sql CLOB;

    l_staff_id VARCHAR2(50 BYTE);
    l_staff_first_name VARCHAR2(100 BYTE);
    l_staff_last_name VARCHAR2(100 BYTE);

    l_pay_hours VARCHAR2(100 BYTE);
    l_rate_amount VARCHAR2(200 BYTE);

    l_pt_id VARCHAR2(200 BYTE);
    l_pt_first_name VARCHAR2(500 BYTE);
    l_pt_last_name VARCHAR2(500 BYTE);

    l_services CLOB;

    l_status_filter VARCHAR2(100 BYTE);

    l_pay_hours_fiter VARCHAR2(100 BYTE);

    l_check_amount VARCHAR2(100 BYTE);
    l_check_number VARCHAR2(100 BYTE);

    l_rate_typ_filter VARCHAR2(100 BYTE);

    l_order_by_col VARCHAR2(30 BYTE);
    l_order_by_dir VARCHAR2(4 BYTE);

    l_cursor_id NUMBER;

    l_return NUMBER;

    BEGIN

      -- Dynamic SQL statement with placeholder:
      l_sql := 'SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (
          SELECT * FROM (

              (SELECT
                      T1.TIMESHEET_SUMMARY_SK,
                      T1.STAFF_ID,
                      T2.STAFF_FIRST_NAME,
                      T2.STAFF_LAST_NAME,
                      T1.TIMESHEET_WEEK_TOTAL_HRS,
                      T1.TIMESHEET_WEEK_END_DATE,
                      T4.EFT_TXN_ID,
                      T4.CHECK_NUM,
                      T4.CHECK_DATE,
                      T4.GROSS_PAY_AMT,
                      PKG_PR_UTIL_DEBUG.GET_PAYROLL_PT_SVC(T1.TIMESHEET_SUMMARY_SK) AS TIMESHEET_DET_CURSOR

                FROM TIMESHEET_SUMMARY T1

                INNER JOIN (SELECT BE_ID,STAFF_ID,STAFF_FIRST_NAME,STAFF_LAST_NAME
                  FROM STAFF
                    WHERE (TO_CHAR(REC_TERM_TMSTP, ''YYYY-MM-DD'') = ''9999-12-31'' AND CURR_REC_IND = 1)) T2
                ON T1.BE_ID = T2.BE_ID AND T1.STAFF_ID = T2.STAFF_ID

                LEFT JOIN (SELECT TIMESHEET_SUMMARY_SK,INTF_ID,STAFF_ID,BE_LOB_ID,BE_ID
                  FROM PR_OUTPUT
                    WHERE (TO_CHAR(REC_TERM_TMSTP, ''YYYY-MM-DD'') = ''9999-12-31'' AND CURR_REC_IND = 1)) T3
                ON T1.TIMESHEET_SUMMARY_SK = T3.TIMESHEET_SUMMARY_SK

                LEFT JOIN (SELECT INTF_ID,STAFF_ID,BE_ID,WEEK_END_DATE,CHECK_NUM,CHECK_DATE,GROSS_PAY_AMT,EFT_TXN_ID
                  FROM PR_INPUT
                    WHERE (TO_CHAR(REC_TERM_TMSTP, ''YYYY-MM-DD'') = ''9999-12-31'' AND CURR_REC_IND = 1)) T4
                ON T3.BE_ID = T4.BE_ID AND T3.INTF_ID = T4.INTF_ID AND T3.STAFF_ID = T4.STAFF_ID
                  AND T4.WEEK_END_DATE = T1.TIMESHEET_WEEK_END_DATE

                WHERE (T1.TIMESHEET_WEEK_END_DATE IS NOT NULL
                        -- ONLY RETURN RECORDS THAT HAVE TIMESHEET_DET ENTRIES (EXCLUDE BAD DATA)
                        AND EXISTS (SELECT 1 FROM TIMESHEET_DET WHERE TIMESHEET_DET.TIMESHEET_SUMMARY_SK = T1.TIMESHEET_SUMMARY_SK))
                        AND TIMESHEET_WEEK_END_DATE
                          BETWEEN :b_from_date AND :b_to_date
                        AND T1.BE_ID = :b_be_id

                        -- Apply User Filters
                        __STAFF_ID__
                        __STAFF_FIRST_NAME__
                        __STAFF_LAST_NAME__
                        __PAY_HRS__
                        __RATE_AMT__
                        __PT_ID__
                        __PT_FIRST_NAME__
                        __PT_LAST_NAME__
                        __SVC_NAME_ARRAY__
                        __PAY_HOURS_FILTER__
                        __STATUS_FILTER__
                        __CHECK_AMOUNT__
                        __CHECK_NUM__
                        __RATE_TYPE__

                ORDER BY __ORD_BY_COL__ __DIRECTION__)
          )

        ) R1)

        WHERE ROW_NUMBER BETWEEN :b_min_row AND :b_max_row';

      l_staff_id := '';

      IF l_params.STAFF_ID IS NOT NULL THEN

        l_staff_id := 'AND T1.STAFF_ID = :b_staff_id';
      END IF;

      l_sql := REPLACE(l_sql, '__STAFF_ID__', l_staff_id);

      l_staff_first_name := '';

      IF l_params.STAFF_FIRST_NAME IS NOT NULL THEN

        l_staff_first_name := 'AND UPPER(T2.STAFF_FIRST_NAME) LIKE :b_staff_first_name';
      END IF;

      l_sql := REPLACE(l_sql, '__STAFF_FIRST_NAME__', l_staff_first_name);

      l_staff_last_name := '';

      IF l_params.STAFF_LAST_NAME IS NOT NULL THEN

        l_staff_last_name := 'AND UPPER(T2.STAFF_LAST_NAME) LIKE :b_staff_last_name';
      END IF;

      l_sql := REPLACE(l_sql, '__STAFF_LAST_NAME__', l_staff_last_name);

      l_pay_hours := '';

      IF l_params.PAY_HRS IS NOT NULL THEN

        l_pay_hours := 'AND T1.TIMESHEET_WEEK_TOTAL_HRS = :b_pay_hrs';
      END IF;

      l_sql := REPLACE(l_sql, '__PAY_HRS__', l_pay_hours);

      l_rate_amount := '';

      IF l_params.RATE_AMT IS NOT NULL THEN

        l_rate_amount := 'AND EXISTS(SELECT 1 FROM TIMESHEET_DET WHERE TIMESHEET_DET.TIMESHEET_SUMMARY_SK = T1.TIMESHEET_SUMMARY_SK AND TIMESHEET_DET.PR_RATE_AMT = :b_rate_amount)';
      END IF;

      l_sql := REPLACE(l_sql, '__RATE_AMT__', l_rate_amount);

      l_pt_id := '';

      IF l_params.PT_ID IS NOT NULL THEN

        l_pt_id := 'AND EXISTS(SELECT 1 FROM TIMESHEET_DET WHERE TIMESHEET_DET.TIMESHEET_SUMMARY_SK = T1.TIMESHEET_SUMMARY_SK AND TIMESHEET_DET.PT_ID = :b_pt_id)';
      END IF;

      l_sql := REPLACE(l_sql, '__PT_ID__', l_pt_id);

      l_pt_first_name := '';

      IF l_params.PT_FIRST_NAME IS NOT NULL THEN

        l_pt_first_name := 'AND EXISTS(SELECT 1 FROM TIMESHEET_DET TD_T1 INNER JOIN (SELECT BE_ID,PT_ID,PT_FIRST_NAME FROM PT '
                              || 'WHERE TO_CHAR(REC_TERM_TMSTP, ''YYYY-MM-DD'') = ''9999-12-31'' AND CURR_REC_IND = 1) TD_T2 ON TD_T1.BE_ID = TD_T2.BE_ID AND TD_T1.PT_ID = TD_T2.PT_ID '
                              || 'WHERE UPPER(TD_T2.PT_FIRST_NAME) LIKE :b_pt_first_name AND TD_T1.TIMESHEET_SUMMARY_SK = T1.TIMESHEET_SUMMARY_SK)';
      END IF;

      l_sql := REPLACE(l_sql, '__PT_FIRST_NAME__', l_pt_first_name);

      l_pt_last_name := '';

      IF l_params.PT_LAST_NAME IS NOT NULL THEN

        l_pt_last_name := 'AND EXISTS(SELECT 1 FROM TIMESHEET_DET TD_T1 INNER JOIN (SELECT BE_ID,PT_ID,PT_LAST_NAME FROM PT '
                              || 'WHERE TO_CHAR(REC_TERM_TMSTP, ''YYYY-MM-DD'') = ''9999-12-31'' AND CURR_REC_IND = 1) TD_T2 ON TD_T1.BE_ID = TD_T2.BE_ID AND TD_T1.PT_ID = TD_T2.PT_ID '
                              || 'WHERE UPPER(TD_T2.PT_LAST_NAME) LIKE :b_pt_last_name AND TD_T1.TIMESHEET_SUMMARY_SK = T1.TIMESHEET_SUMMARY_SK)';
      END IF;

      l_sql := REPLACE(l_sql, '__PT_LAST_NAME__', l_pt_last_name);

      l_services := '';

      IF v_svc_array IS NOT NULL AND v_svc_array.COUNT > 0 THEN

        l_services := 'AND PKG_PR_UTIL_DEBUG.SERVICES_EXISTS(T1.TIMESHEET_SUMMARY_SK, :b_svc_array) = 1';

      END IF;

      l_sql := REPLACE(l_sql, '__SVC_NAME_ARRAY__', l_services);

      l_pay_hours_fiter := '';

      IF l_params.PAY_HRS_FILTER IS NOT NULL THEN
        l_pay_hours_fiter := l_params.PAY_HRS_FILTER;
      END IF;

      l_sql := REPLACE(l_sql, '__PAY_HOURS_FILTER__', l_pay_hours_fiter);

      l_status_filter := ''; -- DEFAULT ALL

      IF l_params.STATUS = 'PENDING' THEN
        l_status_filter := 'AND (T4.CHECK_NUM IS NULL AND T4.EFT_TXN_ID IS NULL)';
      ELSIF l_params.STATUS = 'PAID' THEN
        l_status_filter := 'AND (T4.CHECK_NUM IS NOT NULL OR T4.EFT_TXN_ID IS NOT NULL)';
      END IF;

      l_sql := REPLACE(l_sql, '__STATUS_FILTER__', l_status_filter);

      l_check_amount := '';

      IF l_params.CHECK_AMOUNT IS NOT NULL THEN
        l_check_amount := 'AND GROSS_PAY_AMT = :b_check_amount';
      END IF;

      l_sql := REPLACE(l_sql, '__CHECK_AMOUNT__', l_check_amount);

      l_check_number := '';

      IF l_params.CHECK_NUM IS NOT NULL THEN
        l_check_number := 'AND CHECK_NUM = :b_check_number';
      END IF;

      l_sql := REPLACE(l_sql, '__CHECK_NUM__', l_check_number);

      l_rate_typ_filter := '';

      IF l_params.RATE_TYP_NAME IS NOT NULL THEN
        l_rate_typ_filter := 'AND PKG_PR_UTIL_DEBUG.RATE_TYPE_EXISTS(T1.TIMESHEET_SUMMARY_SK, :b_rate_typ) = 1';
      END IF;

      l_sql := REPLACE(l_sql, '__RATE_TYPE__', l_rate_typ_filter);

      l_order_by_col := l_params.ORDER_BY_COL;
      l_order_by_dir := l_params.ORDER_BY_DIR;

      l_sql := REPLACE(l_sql, '__ORD_BY_COL__', l_order_by_col);
      l_sql := REPLACE(l_sql, '__DIRECTION__', l_order_by_dir);

      l_cursor_id := DBMS_SQL.OPEN_CURSOR;

      DBMS_SQL.PARSE(l_cursor_id, l_sql, DBMS_SQL.NATIVE);
      DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_from_date', l_params.PR_FROM_WEEK_END_DATE);
      DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_to_date', l_params.PR_TO_WEEK_END_DATE);
      DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_be_id', l_params.BE_ID);

      IF LENGTH(l_staff_id) > 0 THEN
        DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_staff_id', l_params.STAFF_ID);
      END IF;

      IF LENGTH(l_staff_first_name) > 0 THEN
        DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_staff_first_name', l_params.STAFF_FIRST_NAME);
      END IF;

      IF LENGTH(l_staff_last_name) > 0 THEN
        DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_staff_last_name', l_params.STAFF_LAST_NAME);
      END IF;

      IF LENGTH(l_pay_hours) > 0 THEN
        DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_pay_hrs', l_params.PAY_HRS);
      END IF;

      IF LENGTH(l_rate_amount) > 0 THEN
        DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_rate_amount', l_params.RATE_AMT);
      END IF;

      IF LENGTH(l_pt_id) > 0 THEN
        DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_pt_id', l_params.PT_ID);
      END IF;

      IF LENGTH(l_pt_first_name) > 0 THEN
        DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_pt_first_name', l_params.PT_FIRST_NAME);
      END IF;

      IF LENGTH(l_pt_last_name) > 0 THEN
        DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_pt_last_name', l_params.PT_LAST_NAME);
      END IF;

      IF LENGTH(l_services) > 0 THEN
        DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_svc_array', v_svc_array);
      END IF;

      IF LENGTH(l_check_amount) > 0 THEN
        DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_check_amount', l_params.CHECK_AMOUNT);
      END IF;

      IF LENGTH(l_check_number) > 0 THEN
        DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_check_number', l_params.CHECK_NUM);
      END IF;

      IF LENGTH(l_rate_typ_filter) > 0 THEN
        DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_rate_typ', l_params.RATE_TYP_NAME);
      END IF;

      DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_min_row', l_params.FROM_ROW);
      DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_max_row', l_params.TO_ROW);

      l_return := DBMS_SQL.EXECUTE(l_cursor_id);

      dbms_output.put_line('INFO: FIND_PAYROLL: l_return = [' || l_return || ']');

      result_Cursor := DBMS_SQL.TO_REFCURSOR(l_cursor_id);

      RETURN result_Cursor;

      --EXCEPTION
      --WHEN OTHERS THEN
      --dbms_output.put_line('ERROR: FIND_PAYROLL: UNHANDLED EXCEPTION!');

    END FIND_PAYROLL;

  FUNCTION GET_PAYROLL_PT_SVC (l_tss_sk TIMESHEET_SUMMARY.TIMESHEET_SUMMARY_SK%TYPE) RETURN SYS_REFCURSOR
  AS

    result_Cursor SYS_REFCURSOR;

    BEGIN

      OPEN result_Cursor FOR
      SELECT  T1.*,
        T2.PT_FIRST_NAME,
        T2.PT_LAST_NAME,
        T5.SVC_NAME,
        T5.RATE_TYP_NAME
      FROM TIMESHEET_DET T1

        LEFT JOIN (SELECT BE_ID,PT_ID,PT_FIRST_NAME,PT_LAST_NAME
                   FROM PT
                   WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2
          ON T1.BE_ID = T2.BE_ID AND T1.PT_ID = T2.PT_ID

        LEFT JOIN (SELECT PT_PAYER_SK,BE_ID,PT_ID,PAYER_ID,CONTR_ID,PT_PAYER_EFF_DATE,PT_PAYER_TERM_DATE
          FROM PT_PAYER WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3
        ON T2.BE_ID = T3.BE_ID AND T2.PT_ID = T3.PT_ID
          AND  T1.TIMESHEET_DATE BETWEEN T3.PT_PAYER_EFF_DATE AND T3.PT_PAYER_TERM_DATE
          AND T3.CONTR_ID IS NOT NULL

        LEFT JOIN (SELECT BE_ID,AUTH_SK,AUTH_ID,PT_ID,PAYER_ID,AUTH_START_TMSTP,AUTH_END_TMSTP
          FROM AUTH WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4
        ON T3.BE_ID = T4.BE_ID AND T3.PT_ID = T4.PT_ID AND T3.PAYER_ID = T4.PAYER_ID
          AND  T1.TIMESHEET_DATE BETWEEN T4.AUTH_START_TMSTP AND T4.AUTH_END_TMSTP

        LEFT JOIN (SELECT AUTH_SK,SVC_NAME,RATE_TYP_NAME
          FROM AUTH_SVC) T5
        ON T4.AUTH_SK = T5.AUTH_SK

      WHERE TIMESHEET_SUMMARY_SK = l_tss_sk;

      RETURN result_Cursor;

      EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: GET_PAYROLL_PT_SVC: UNHANDLED EXCEPTION!');

    END GET_PAYROLL_PT_SVC;

  FUNCTION GET_BE_RATE (l_params PR_GET_STAFF_RATE_PARAMS_T) RETURN SYS_REFCURSOR
  AS
    result_Cursor SYS_REFCURSOR;

    l_auth_svc AUTH_SVC.SVC_NAME%TYPE;
    l_sub_rate_typ BE_RATE.RATE_SUB_TYP_NAME%TYPE;
    l_rate_typ BE_RATE.RATE_TYP_NAME%TYPE;

    BEGIN

      IF l_params.SVC_NAME IS NULL THEN
        l_auth_svc := PKG_PR_UTIL_DEBUG.GET_AUTH_SVC(l_params.BE_ID, l_params.VISIT_SK, l_params.VISIT_DATE);
      ELSE
        l_auth_svc := l_params.SVC_NAME;
      END IF;

      IF l_params.RATE_SUB_TYP_NAME IS NULL THEN
        l_sub_rate_typ := PKG_PR_UTIL_DEBUG.GET_RATE_SUB_TYP(l_params.BE_ID, l_params.VISIT_DATE);
      ELSE
        l_sub_rate_typ := l_params.RATE_SUB_TYP_NAME;
      END IF;

      IF l_params.RATE_TYP_NAME IS NULL THEN
        l_rate_typ := PKG_PR_UTIL_DEBUG.GET_RATE_TYP_NAME(l_params.BE_ID, l_sub_rate_typ, l_params.PT_ID, l_params.VISIT_SK, l_params.VISIT_DATE);
      ELSE
        l_rate_typ := l_params.RATE_TYP_NAME;
      END IF;

      OPEN result_Cursor FOR
      SELECT T1.*
      FROM BE_RATE T1
      WHERE BE_ID = l_params.BE_ID
        AND BE_LOC_ID = l_params.BE_LOC_ID
        AND BE_LOB_ID = l_params.BE_LOB_ID
        AND SVC_NAME = l_auth_svc
        AND UPPER(RATE_TYP_NAME) = l_rate_typ
        AND RATE_QLFR_CODE = 'PAY'
        AND l_params.VISIT_DATE BETWEEN BE_RATE_EFF_DATE AND BE_RATE_TERM_DATE
        AND RATE_SUB_TYP_NAME = l_sub_rate_typ;

      RETURN result_Cursor;

      EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: GET_BE_RATE: UNHANDLED EXCEPTION!');

    END GET_BE_RATE;

  FUNCTION GET_STAFF_SUPPL_RATE (l_params PR_GET_STAFF_RATE_PARAMS_T) RETURN SYS_REFCURSOR
  AS
    result_Cursor SYS_REFCURSOR;

    l_auth_svc AUTH_SVC.SVC_NAME%TYPE;
    l_sub_rate_typ BE_RATE.RATE_SUB_TYP_NAME%TYPE;
    l_rate_typ BE_RATE.RATE_TYP_NAME%TYPE;

    BEGIN

      IF l_params.SVC_NAME IS NULL THEN
        l_auth_svc := PKG_PR_UTIL_DEBUG.GET_AUTH_SVC(l_params.BE_ID, l_params.VISIT_SK, l_params.VISIT_DATE);
      ELSE
        l_auth_svc := l_params.SVC_NAME;
      END IF;

      IF l_params.RATE_SUB_TYP_NAME IS NULL THEN
        l_sub_rate_typ := PKG_PR_UTIL_DEBUG.GET_RATE_SUB_TYP(l_params.BE_ID, l_params.VISIT_DATE);
      ELSE
        l_sub_rate_typ := l_params.RATE_SUB_TYP_NAME;
      END IF;

      IF l_params.RATE_TYP_NAME IS NULL THEN
        l_rate_typ := PKG_PR_UTIL_DEBUG.GET_RATE_TYP_NAME(l_params.BE_ID, l_sub_rate_typ, l_params.PT_ID, l_params.VISIT_SK, l_params.VISIT_DATE);
      ELSE
        l_rate_typ := l_params.RATE_TYP_NAME;
      END IF;

      OPEN result_Cursor FOR
      SELECT T1.*
      FROM STAFF_SUPPL_RATE T1
      WHERE BE_ID = l_params.BE_ID
        AND SVC_NAME = l_auth_svc
        AND UPPER(RATE_TYP_NAME) = l_rate_typ
        AND RATE_QLFR_CODE = 'PAY'
        AND l_params.VISIT_DATE BETWEEN STAFF_SUPPL_RATE_EFF_DATE AND STAFF_SUPPL_RATE_TERM_DATE
        AND RATE_SUB_TYP_NAME = l_sub_rate_typ
        AND STAFF_ID = l_params.STAFF_ID;

      RETURN result_Cursor;

      EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: GET_STAFF_SUPPL_RATE: UNHANDLED EXCEPTION!');

    END GET_STAFF_SUPPL_RATE;

  FUNCTION GET_STAFF_ASSOC_RATE (l_params PR_GET_STAFF_RATE_PARAMS_T) RETURN SYS_REFCURSOR
  AS
    result_Cursor SYS_REFCURSOR;

    l_auth_svc AUTH_SVC.SVC_NAME%TYPE;
    l_sub_rate_typ BE_RATE.RATE_SUB_TYP_NAME%TYPE;
    l_rate_typ BE_RATE.RATE_TYP_NAME%TYPE;

    BEGIN

      IF l_params.SVC_NAME IS NULL THEN
        l_auth_svc := PKG_PR_UTIL_DEBUG.GET_AUTH_SVC(l_params.BE_ID, l_params.VISIT_SK, l_params.VISIT_DATE);
      ELSE
        l_auth_svc := l_params.SVC_NAME;
      END IF;

      IF l_params.RATE_SUB_TYP_NAME IS NULL THEN
        l_sub_rate_typ := PKG_PR_UTIL_DEBUG.GET_RATE_SUB_TYP(l_params.BE_ID, l_params.VISIT_DATE);
      ELSE
        l_sub_rate_typ := l_params.RATE_SUB_TYP_NAME;
      END IF;

      IF l_params.RATE_TYP_NAME IS NULL THEN
        l_rate_typ := PKG_PR_UTIL_DEBUG.GET_RATE_TYP_NAME(l_params.BE_ID, l_sub_rate_typ, l_params.PT_ID, l_params.VISIT_SK, l_params.VISIT_DATE);
      ELSE
        l_rate_typ := l_params.RATE_TYP_NAME;
      END IF;

      OPEN result_Cursor FOR
      SELECT T1.*
      FROM STAFF_ASSOC_RATE T1
      WHERE BE_ID = l_params.BE_ID
        AND SVC_NAME = l_auth_svc
        AND UPPER(RATE_TYP_NAME) = l_rate_typ
        AND RATE_QLFR_CODE = 'PAY'
        AND l_params.VISIT_DATE BETWEEN STAFF_ASSOC_RATE_EFF_DATE AND STAFF_ASSOC_RATE_TERM_DATE
        AND RATE_SUB_TYP_NAME = l_sub_rate_typ
        AND STAFF_ID = l_params.STAFF_ID
        AND PT_ID = l_params.PT_ID;

      RETURN result_Cursor;

      EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: GET_STAFF_ASSOC_RATE: UNHANDLED EXCEPTION!');

    END GET_STAFF_ASSOC_RATE;

  FUNCTION GET_PR_RATE_MATRIX (l_params PR_GET_STAFF_RATE_PARAMS_T) RETURN SYS_REFCURSOR
  AS
    result_Cursor SYS_REFCURSOR;

    l_auth_svc AUTH_SVC.SVC_NAME%TYPE;
    l_sub_rate_typ BE_RATE.RATE_SUB_TYP_NAME%TYPE;
    l_rate_typ BE_RATE.RATE_TYP_NAME%TYPE;

    BEGIN

      IF l_params.SVC_NAME IS NULL THEN
        l_auth_svc := PKG_PR_UTIL_DEBUG.GET_AUTH_SVC(l_params.BE_ID, l_params.VISIT_SK, l_params.VISIT_DATE);
      ELSE
        l_auth_svc := l_params.SVC_NAME;
      END IF;

      IF l_params.RATE_SUB_TYP_NAME IS NULL THEN
        l_sub_rate_typ := PKG_PR_UTIL_DEBUG.GET_RATE_SUB_TYP(l_params.BE_ID, l_params.VISIT_DATE);
      ELSE
        l_sub_rate_typ := l_params.RATE_SUB_TYP_NAME;
      END IF;

      IF l_params.RATE_TYP_NAME IS NULL THEN
        l_rate_typ := PKG_PR_UTIL_DEBUG.GET_RATE_TYP_NAME(l_params.BE_ID, l_sub_rate_typ, l_params.PT_ID, l_params.VISIT_SK, l_params.VISIT_DATE);
      ELSE
        l_rate_typ := l_params.RATE_TYP_NAME;
      END IF;

      OPEN result_Cursor FOR
      SELECT T1.*
      FROM PR_RATE_MATRIX T1
        INNER JOIN (SELECT BE_ID,PT_ID,PAYER_ID,CONTR_ID,PAYER_RANK_NAME,
                      SVC_NAME,PT_PAYER_EFF_DATE,PT_PAYER_TERM_DATE,REC_TERM_TMSTP,CURR_REC_IND
            FROM PT_PAYER
            WHERE l_params.VISIT_DATE BETWEEN PT_PAYER_EFF_DATE AND PT_PAYER_TERM_DATE
                  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2
        ON T1.BE_ID = T2.BE_ID
             AND T1.PAYER_ID = T2.PAYER_ID
             AND T1.CONTR_ID = T2.CONTR_ID
             AND T1.SVC_NAME = T2.SVC_NAME
             AND l_params.VISIT_DATE BETWEEN PR_RATE_MATRIX_EFF_DATE AND PR_RATE_MATRIX_TERM_DATE
        WHERE T1.BE_ID = l_params.BE_ID
            AND T1.BE_LOC_ID = l_params.BE_LOC_ID
            AND T1.BE_LOB_ID = l_params.BE_LOB_ID
            AND RATE_QLFR_CODE = 'PAY'
            AND T1.SVC_NAME = l_auth_svc
            AND UPPER(T1.RATE_TYP_NAME) = l_rate_typ
            AND T1.RATE_SUB_TYP_NAME = l_sub_rate_typ
            AND T2.PT_ID = l_params.PT_ID;

      RETURN result_Cursor;

      EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: GET_PR_RATE_MATRIX: UNHANDLED EXCEPTION!');

    END GET_PR_RATE_MATRIX;

  FUNCTION GET_STAFF_RATE (v_params PR_GET_STAFF_RATE_PARAMS_T) RETURN SYS_REFCURSOR
  AS
    result_Cursor SYS_REFCURSOR;

    l_params PR_GET_STAFF_RATE_PARAMS_T;

    BEGIN

      l_params := v_params;

      IF l_params.SVC_NAME IS NULL THEN
        l_params.SVC_NAME := PKG_PR_UTIL_DEBUG.GET_AUTH_SVC(l_params.BE_ID, l_params.VISIT_SK, l_params.VISIT_DATE);
      END IF;

      IF l_params.RATE_SUB_TYP_NAME IS NULL THEN
        l_params.RATE_SUB_TYP_NAME := PKG_PR_UTIL_DEBUG.GET_RATE_SUB_TYP(l_params.BE_ID, l_params.VISIT_DATE);
      END IF;

      IF l_params.RATE_TYP_NAME IS NULL THEN
        l_params.RATE_TYP_NAME := PKG_PR_UTIL_DEBUG.GET_RATE_TYP_NAME(l_params.BE_ID, l_params.RATE_SUB_TYP_NAME, l_params.PT_ID, l_params.VISIT_SK, l_params.VISIT_DATE);
      END IF;

      OPEN result_Cursor FOR
      SELECT T1.*
      FROM STAFF_RATE T1
      WHERE BE_ID = l_params.BE_ID
        AND SVC_NAME = l_params.SVC_NAME
        AND UPPER(RATE_TYP_NAME) = l_params.RATE_TYP_NAME
        AND RATE_QLFR_CODE = 'PAY'
        AND l_params.VISIT_DATE BETWEEN STAFF_RATE_EFF_DATE AND STAFF_RATE_TERM_DATE
        AND RATE_SUB_TYP_NAME = l_params.RATE_SUB_TYP_NAME
        AND STAFF_ID = l_params.STAFF_ID;

      RETURN result_Cursor;

      EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: GET_STAFF_RATE: UNHANDLED EXCEPTION!');

    END GET_STAFF_RATE;

  FUNCTION GET_STAFF_RATES (v_params PR_GET_STAFF_RATE_PARAMS_T) RETURN SYS_REFCURSOR
  AS
    result_Cursor SYS_REFCURSOR;

    l_params PR_GET_STAFF_RATE_PARAMS_T;

    BEGIN

      l_params := v_params;

      IF l_params.SVC_NAME IS NULL THEN
        l_params.SVC_NAME := PKG_PR_UTIL_DEBUG.GET_AUTH_SVC(l_params.BE_ID, l_params.VISIT_SK, l_params.VISIT_DATE);
      END IF;

      IF l_params.RATE_SUB_TYP_NAME IS NULL THEN
        l_params.RATE_SUB_TYP_NAME := PKG_PR_UTIL_DEBUG.GET_RATE_SUB_TYP(l_params.BE_ID, l_params.VISIT_DATE);
      END IF;

      IF l_params.RATE_TYP_NAME IS NULL THEN
        l_params.RATE_TYP_NAME := PKG_PR_UTIL_DEBUG.GET_RATE_TYP_NAME(l_params.BE_ID, l_params.RATE_SUB_TYP_NAME, l_params.PT_ID, l_params.VISIT_SK, l_params.VISIT_DATE);
      END IF;

      OPEN result_Cursor FOR
      SELECT PKG_PR_UTIL_DEBUG.GET_BE_RATE(l_params) AS BE_RATE_CURSOR,
              PKG_PR_UTIL_DEBUG.GET_STAFF_SUPPL_RATE(l_params) AS STAFF_SUPPL_RATE_CURSOR,
              PKG_PR_UTIL_DEBUG.GET_STAFF_ASSOC_RATE(l_params) AS STAFF_ASSOC_RATE_CURSOR,
              PKG_PR_UTIL_DEBUG.GET_PR_RATE_MATRIX(l_params) AS PR_RATE_MATRIX_CURSOR,
              T1.*
      FROM STAFF_RATE T1
      WHERE BE_ID = l_params.BE_ID
        AND SVC_NAME = l_params.SVC_NAME
        AND UPPER(RATE_TYP_NAME) = l_params.RATE_TYP_NAME
        AND RATE_QLFR_CODE = 'PAY'
        AND l_params.VISIT_DATE BETWEEN STAFF_RATE_EFF_DATE AND STAFF_RATE_TERM_DATE
        AND RATE_SUB_TYP_NAME = l_params.RATE_SUB_TYP_NAME
        AND STAFF_ID = l_params.STAFF_ID;

      RETURN result_Cursor;

      EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: GET_STAFF_RATES: UNHANDLED EXCEPTION!');

    END GET_STAFF_RATES;

  FUNCTION GET_AUTH_RATE_TYP (v_be_id IN BE.BE_ID%TYPE, v_visit_sk IN VISIT.VISIT_SK%TYPE, visit_start_date IN DATE) RETURN BE_RATE.RATE_TYP_NAME%TYPE
  AS

    v_rate_typ BE_RATE.RATE_TYP_NAME%TYPE;

    BEGIN

      SELECT T4.RATE_TYP_NAME
      INTO v_rate_typ
      FROM VISIT T1

      INNER JOIN (SELECT PT_PAYER_SK,BE_ID,PT_ID,PAYER_ID,CONTR_ID,PT_PAYER_EFF_DATE,PT_PAYER_TERM_DATE
        FROM PT_PAYER WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2
      ON T1.BE_ID = T2.BE_ID AND T1.PT_ID = T2.PT_ID
        AND visit_start_date BETWEEN T2.PT_PAYER_EFF_DATE AND T2.PT_PAYER_TERM_DATE
        AND T2.CONTR_ID IS NOT NULL

      INNER JOIN (SELECT BE_ID,AUTH_SK,AUTH_ID,PT_ID,PAYER_ID,AUTH_START_TMSTP,AUTH_END_TMSTP
        FROM AUTH WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3
      ON T1.BE_ID = T3.BE_ID AND T1.PT_ID = T3.PT_ID AND T2.PAYER_ID = T3.PAYER_ID
        AND visit_start_date BETWEEN AUTH_START_TMSTP AND AUTH_END_TMSTP

      INNER JOIN (SELECT AUTH_SVC_SK,AUTH_SK,RATE_TYP_NAME
        FROM AUTH_SVC) T4
      ON T3.AUTH_SK = T4.AUTH_SK

      WHERE VISIT_SK = v_visit_sk AND T1.BE_ID = v_be_id;

      RETURN v_rate_typ;

      EXCEPTION
      WHEN NO_DATA_FOUND THEN
      RETURN NULL;

    END GET_AUTH_RATE_TYP;

  FUNCTION GET_RATE_TYP_NAME (v_be_id IN BE.BE_ID%TYPE, v_rate_sub_typ_name IN BE_RATE.RATE_SUB_TYP_NAME%TYPE, v_pt_id IN PT.PT_ID%TYPE, v_visit_sk IN VISIT.VISIT_SK%TYPE, visit_start_date IN DATE) RETURN BE_RATE.RATE_TYP_NAME%TYPE
  AS
    v_rate_typ BE_RATE.RATE_TYP_NAME%TYPE;

    BEGIN

      v_rate_typ := COREDATA.PKG_PR_UTIL_DEBUG.GET_AUTH_RATE_TYP (v_be_id, v_visit_sk, visit_start_date);

      IF v_rate_typ IS NOT NULL THEN

        dbms_output.put_line('GET_RATE_TYP_NAME: Using [RATE_TYP_NAME=' || v_rate_typ || ']: Source: AUTH_SVC');
        RETURN v_rate_typ;
      END IF;

      SELECT T1.RATE_TYP_NAME
      INTO v_rate_typ
      FROM PR_RATE_MATRIX T1
        INNER JOIN (SELECT BE_ID,PT_ID,PAYER_ID,CONTR_ID,PAYER_RANK_NAME,
                      SVC_NAME,PT_PAYER_EFF_DATE,PT_PAYER_TERM_DATE,REC_TERM_TMSTP,CURR_REC_IND
            FROM PT_PAYER
            WHERE visit_start_date BETWEEN PT_PAYER_EFF_DATE AND PT_PAYER_TERM_DATE
                  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2
        ON T1.BE_ID = T2.BE_ID
             AND T1.PAYER_ID = T2.PAYER_ID
             AND T1.CONTR_ID = T2.CONTR_ID
             AND T1.SVC_NAME = T2.SVC_NAME
             AND visit_start_date BETWEEN PR_RATE_MATRIX_EFF_DATE AND PR_RATE_MATRIX_TERM_DATE
        WHERE T1.BE_ID = v_be_id
            AND T1.RATE_SUB_TYP_NAME = v_rate_sub_typ_name
            AND T2.PT_ID = v_pt_id;

      dbms_output.put_line('GET_RATE_TYP_NAME: Using [RATE_TYP_NAME=' || v_rate_typ || ']: Source: PR_RATE_MATRIX');
      RETURN v_rate_typ;

      EXCEPTION
      WHEN NO_DATA_FOUND THEN
      RETURN 'HOURLY';  -- Default

    END GET_RATE_TYP_NAME;

  FUNCTION GET_AUTH_SVC (v_be_id IN BE.BE_ID%TYPE, v_visit_sk IN VISIT.VISIT_SK%TYPE, visit_start_date IN DATE) RETURN AUTH_SVC.SVC_NAME%TYPE
  AS
    v_auth_svc_name AUTH_SVC.SVC_NAME%TYPE;

    BEGIN

      SELECT T4.SVC_NAME
      INTO v_auth_svc_name
      FROM VISIT T1

      INNER JOIN (SELECT PT_PAYER_SK,BE_ID,PT_ID,PAYER_ID,CONTR_ID,PT_PAYER_EFF_DATE,PT_PAYER_TERM_DATE
        FROM PT_PAYER WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2
      ON T1.BE_ID = T2.BE_ID AND T1.PT_ID = T2.PT_ID
        AND visit_start_date BETWEEN T2.PT_PAYER_EFF_DATE AND T2.PT_PAYER_TERM_DATE
        AND T2.CONTR_ID IS NOT NULL

      INNER JOIN (SELECT BE_ID,AUTH_SK,AUTH_ID,PT_ID,PAYER_ID,AUTH_START_TMSTP,AUTH_END_TMSTP
        FROM AUTH WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3
      ON T1.BE_ID = T3.BE_ID AND T1.PT_ID = T3.PT_ID AND T2.PAYER_ID = T3.PAYER_ID
        AND visit_start_date BETWEEN AUTH_START_TMSTP AND AUTH_END_TMSTP

      INNER JOIN (SELECT AUTH_SVC_SK,AUTH_SK,SVC_NAME
        FROM AUTH_SVC) T4
      ON T3.AUTH_SK = T4.AUTH_SK

      WHERE VISIT_SK = v_visit_sk;

      RETURN v_auth_svc_name;

      EXCEPTION
      WHEN NO_DATA_FOUND THEN
      RETURN NULL;

    END GET_AUTH_SVC;

  FUNCTION HOLIDAY_RATE_SUB_TYP (v_be_id IN BE.BE_ID%TYPE, visit_start_date IN DATE) RETURN BE_RATE.RATE_SUB_TYP_NAME%TYPE
  AS
    V_BE_HOLIDAY_SK BE_CALENDAR_LKUP.BE_CALENDAR_LKUP_SK%TYPE;

    BEGIN

      -- Check the Visit Start Date against the BE_CALENDAR_LKUP
      SELECT BE_CALENDAR_LKUP_SK
      INTO V_BE_HOLIDAY_SK
      FROM BE_CALENDAR_LKUP
      -- TRUNC removes the time from the DATE
      WHERE TRUNC(visit_start_date) = TRUNC(CALENDAR_DATE)
            AND BE_ID = v_be_id
            AND (NATIONAL_HOLIDAY_IND = 1
                 OR STATE_HOLIDAY_IND = 1
                 OR LOC_HOLIDAY_IND = 1)
            AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)
            AND ROWNUM <= 1;

      IF V_BE_HOLIDAY_SK IS NOT NULL THEN
        RETURN 'HOLIDAY';
      END IF;

      RETURN NULL;

      EXCEPTION
      WHEN NO_DATA_FOUND THEN
      RETURN NULL;

    END HOLIDAY_RATE_SUB_TYP;

  FUNCTION WEEKEND_RATE_SUB_TYP (v_be_id IN BE.BE_ID%TYPE, visit_start_date IN DATE) RETURN BE_RATE.RATE_SUB_TYP_NAME%TYPE
  AS
    V_VISIT_DAY_OF_WEEK NUMBER(1);

    V_BE_WEEKEND_START_DAY NUMBER(1);
    V_BE_WEEKEND_START_TIME BE.BE_WEEKEND_START_TIME%TYPE;
    V_BE_WEEKEND_END_DAY NUMBER(1);
    V_BE_WEEKEND_END_TIME BE.BE_WEEKEND_END_TIME%TYPE;

    BEGIN

      SELECT TO_NUMBER(TO_CHAR(visit_start_date, 'D'),'9')
      INTO V_VISIT_DAY_OF_WEEK
      FROM DUAL;

      SELECT TO_NUMBER(TO_CHAR(NEXT_DAY(sysdate, BE_WEEKEND_START_DAY), 'D'),'9'),
        BE_WEEKEND_START_TIME,
        TO_NUMBER(TO_CHAR(NEXT_DAY(sysdate, BE_WEEKEND_END_DAY), 'D'),'9'),
        BE_WEEKEND_END_TIME
      INTO V_BE_WEEKEND_START_DAY,V_BE_WEEKEND_START_TIME,V_BE_WEEKEND_END_DAY,V_BE_WEEKEND_END_TIME
      FROM BE WHERE BE_ID = v_be_id
                    AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)
                    AND ROWNUM <= 1;

      IF V_VISIT_DAY_OF_WEEK > V_BE_WEEKEND_START_DAY
         -- V_BE_WEEKEND_END_DAY will be either Sunday (1) or Monday (2) so we add 7 so the compare will work
         AND V_VISIT_DAY_OF_WEEK < (V_BE_WEEKEND_END_DAY + 7) THEN
        RETURN 'WEEKEND';
      END IF;

      IF V_VISIT_DAY_OF_WEEK = V_BE_WEEKEND_START_DAY
         AND visit_start_date >= TO_DATE(TO_CHAR(visit_start_date,'YYYY-MM-DD') || ' ' || V_BE_WEEKEND_START_TIME,'YYYY-MM-DD HH24:MI') THEN
        RETURN 'WEEKEND';
      END IF;

      -- If the current day is Sunday or Monday we add 7 so the compare will work
      IF (V_VISIT_DAY_OF_WEEK + 7) < (V_BE_WEEKEND_END_DAY + 7) THEN
        RETURN 'WEEKEND';
      END IF;

      -- If the current day is Sunday or Monday we add 7 so the compare will work
      IF (V_VISIT_DAY_OF_WEEK + 7) = (V_BE_WEEKEND_END_DAY + 7)
         AND visit_start_date < TO_DATE(TO_CHAR(visit_start_date,'YYYY-MM-DD') || ' ' || V_BE_WEEKEND_END_TIME,'YYYY-MM-DD HH24:MI') THEN
        RETURN 'WEEKEND';
      END IF;

      RETURN NULL;

      EXCEPTION
      WHEN NO_DATA_FOUND THEN
      RETURN NULL;

    END WEEKEND_RATE_SUB_TYP;

  FUNCTION GET_RATE_SUB_TYP (v_be_id IN BE.BE_ID%TYPE, visit_start_date IN DATE) RETURN BE_RATE.RATE_SUB_TYP_NAME%TYPE
  AS

    V_RATE_SUB_TYP_NAME BE_RATE.RATE_SUB_TYP_NAME%TYPE;

    BEGIN

      V_RATE_SUB_TYP_NAME := PKG_PR_UTIL_DEBUG.HOLIDAY_RATE_SUB_TYP(v_be_id, visit_start_date);

      IF V_RATE_SUB_TYP_NAME IS NOT NULL THEN
         dbms_output.put_line ('visit_start_date [' || visit_start_date || ']: HOLIDAY!');
        RETURN V_RATE_SUB_TYP_NAME; -- HOLIDAY
      END IF;

      V_RATE_SUB_TYP_NAME := PKG_PR_UTIL_DEBUG.WEEKEND_RATE_SUB_TYP(v_be_id, visit_start_date);

      IF V_RATE_SUB_TYP_NAME IS NOT NULL THEN
        dbms_output.put_line ('visit_start_date [' || visit_start_date || ']: WEEKEND!');
        RETURN V_RATE_SUB_TYP_NAME; -- WEEKEND
      END IF;

      dbms_output.put_line ('visit_start_date [' || visit_start_date || ']: REGULAR!');
      RETURN 'REGULAR';

    END GET_RATE_SUB_TYP;

END PKG_PR_UTIL_DEBUG;
/
