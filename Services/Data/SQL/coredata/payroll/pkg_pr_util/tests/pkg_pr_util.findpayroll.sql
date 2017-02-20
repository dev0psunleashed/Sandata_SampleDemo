SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_cursor SYS_REFCURSOR;

  l_params PR_FIND_PARAMS_T;

  l_row_num NUMBER;
  l_total_rows NUMBER;

  l_ts_sk TIMESHEET_SUMMARY.TIMESHEET_SUMMARY_SK%TYPE;

  l_staff_id STAFF.STAFF_ID%TYPE;
  l_staff_fn STAFF.STAFF_FIRST_NAME%TYPE;
  l_staff_ln STAFF.STAFF_LAST_NAME%TYPE;

  l_ts_total_hrs TIMESHEET_SUMMARY.TIMESHEET_WEEK_TOTAL_HRS%TYPE;
  l_ts_week_end_date TIMESHEET_SUMMARY.TIMESHEET_WEEK_END_DATE%TYPE;

  l_eft_txn_id PR_INPUT.EFT_TXN_ID%TYPE;
  l_check_num PR_INPUT.CHECK_NUM%TYPE;
  l_check_date PR_INPUT.CHECK_DATE%TYPE;
  l_pay_amount PR_INPUT.GROSS_PAY_AMT%TYPE;

  l_ts_det_cursor SYS_REFCURSOR;

  l_visit_sk TIMESHEET_DET.VISIT_SK%TYPE;
  l_pt_id TIMESHEET_DET.PT_ID%TYPE;
  l_ts_date TIMESHEET_DET.TIMESHEET_DATE%TYPE;
  l_pr_rate TIMESHEET_DET.PR_RATE_AMT%TYPE;
  l_pr_hours TIMESHEET_DET.PR_HRS%TYPE;
  l_pt_fn PT.PT_FIRST_NAME%TYPE;
  l_pt_ln PT.PT_LAST_NAME%TYPE;
  l_service SVC.SVC_NAME%TYPE;

BEGIN

  dbms_output.put_line ('*** START: PKG_PR_UTIL.FIND_PAYROLL ***');

  l_params := PR_FIND_PARAMS_T(
              '1',         -- BE_ID
              NULL,        -- PT_ID
              NULL,        -- PT_FIRST_NAME
              NULL,        -- PT_LAST_NAME
              NULL,        -- STAFF_ID
              NULL,        -- STAFF_FIRST_NAME
              NULL,        -- STAFF_LAST_NAME
              TO_DATE('2016-07-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),        -- PR_FROM_WEEK_END_DATE
              TO_DATE('2017-08-31 23:59:59', 'YYYY-MM-DD HH24:MI:SS'),        -- PR_TO_WEEK_END_DATE
              NULL,        -- SVC_NAME
              NULL,        -- PAY_HRS
              NULL,        -- PAY_HRS_FILTER
              NULL,        -- RATE_AMT
              'PENDING',        -- STATUS
              NULL,        -- CHECK_NUM
              NULL,        -- CHECK_DATE
              NULL,        -- CHECK_AMOUNT
              1,        -- FROM_ROW
              100,        -- TO_ROW
              'STAFF_ID',        -- ORDER_BY_COL
              'DESC'         -- ORDER_BY_DIR
  );

  l_cursor := COREDATA.PKG_PR_UTIL.FIND_PAYROLL(l_params);

  IF l_cursor IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  LOOP
    FETCH l_cursor INTO
          l_row_num,
          l_total_rows,
          l_ts_sk,
          l_staff_id,
          l_staff_fn,
          l_staff_ln,
          l_ts_total_hrs,
          l_ts_week_end_date,
          l_eft_txn_id,
          l_check_num,
          l_check_date,
          l_pay_amount,
          l_ts_det_cursor;

    EXIT WHEN l_cursor%NOTFOUND;

    dbms_output.put_line ('l_cursor: l_row_num [' || l_row_num || ']: TOTAL: [' || l_total_rows || '] -->');
    dbms_output.put_line (CHR(9) || 'l_cursor: l_ts_sk = ' || l_ts_sk);
    dbms_output.put_line (CHR(9) || 'l_cursor: l_staff_id = ' || l_staff_id);
    dbms_output.put_line (CHR(9) || 'l_cursor: l_staff_fn = ' || l_staff_fn);
    dbms_output.put_line (CHR(9) || 'l_cursor: l_staff_ln = ' || l_staff_ln);
    dbms_output.put_line (CHR(9) || 'l_cursor: l_ts_total_hrs = ' || l_ts_total_hrs);
    dbms_output.put_line (CHR(9) || 'l_cursor: l_ts_week_end_date = ' || l_ts_week_end_date);
    dbms_output.put_line (CHR(9) || 'l_cursor: l_eft_txn_id = ' || l_eft_txn_id);
    dbms_output.put_line (CHR(9) || 'l_cursor: l_check_num = ' || l_check_num);
    dbms_output.put_line (CHR(9) || 'l_cursor: l_check_date = ' || l_check_date);
    dbms_output.put_line (CHR(9) || 'l_cursor: l_pay_amount = ' || l_pay_amount || CHR(13) || CHR(10));

    IF l_ts_det_cursor IS NULL THEN
      RAISE NULL_EXCEPTION;
    END IF;

    LOOP
      FETCH l_ts_det_cursor INTO
              l_visit_sk,
              l_pt_id,
              l_ts_date,
              l_pr_rate,
              l_pr_hours,
              l_pt_fn,
              l_pt_ln,
              l_service;

      EXIT WHEN l_ts_det_cursor%NOTFOUND;

      dbms_output.put_line (CHR(9) || CHR(9) || 'l_ts_det_cursor: l_visit_sk = ' || l_visit_sk);
      dbms_output.put_line (CHR(9) || CHR(9) || 'l_ts_det_cursor: l_pt_id = ' || l_pt_id);
      dbms_output.put_line (CHR(9) || CHR(9) || 'l_ts_det_cursor: l_ts_date = ' || l_ts_date);
      dbms_output.put_line (CHR(9) || CHR(9) || 'l_ts_det_cursor: l_pr_rate = ' || l_pr_rate);
      dbms_output.put_line (CHR(9) || CHR(9) || 'l_ts_det_cursor: l_pr_hours = ' || l_pr_hours);
      dbms_output.put_line (CHR(9) || CHR(9) || 'l_ts_det_cursor: l_pt_fn = ' || l_pt_fn);
      dbms_output.put_line (CHR(9) || CHR(9) || 'l_ts_det_cursor: l_pt_ln = ' || l_pt_ln);
      dbms_output.put_line (CHR(9) || CHR(9) || 'l_ts_det_cursor: l_service = ' || l_service || CHR(13) || CHR(10));

    END LOOP;

    CLOSE l_ts_det_cursor;

  END LOOP;

  CLOSE l_cursor;

  dbms_output.put_line ('*** END: PKG_PR_UTIL.FIND_PAYROLL ***' || CHR(13) || CHR(10));

  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  --WHEN OTHERS THEN
  --CLOSE l_cursor;
  --dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;
