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

  l_pt_fn PT.PT_FIRST_NAME%TYPE;
  l_pt_ln PT.PT_LAST_NAME%TYPE;
  l_service SVC.SVC_NAME%TYPE;

  l_ts_detl TIMESHEET_DET%ROWTYPE;


  l_svc_array STRING_ARRAY;

BEGIN

  dbms_output.put_line ('*** START: PKG_PR_UTIL.FIND_PAYROLL ***');

  l_svc_array := STRING_ARRAY('RN','LPN');
  --l_svc_array := STRING_ARRAY('HHA');
  --l_svc_array := STRING_ARRAY();

  l_params := PR_FIND_PARAMS_T(
              '1',         -- BE_ID
              NULL,        -- PT_ID
              NULL,        -- PT_FIRST_NAME
              NULL,        -- PT_LAST_NAME
              '20160906110100512',        -- STAFF_ID
              NULL,        -- STAFF_FIRST_NAME
              NULL,        -- STAFF_LAST_NAME
              TO_DATE('2016-07-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),        -- PR_FROM_WEEK_END_DATE
              TO_DATE('2016-09-28', 'YYYY-MM-DD HH24:MI:SS'),        -- PR_TO_WEEK_END_DATE
              NULL,        -- SVC_NAME
              NULL,        -- PAY_HRS
              'AND TIMESHEET_WEEK_TOTAL_HRS >= 144000',        -- PAY_HRS_FILTER
              --'AND TIMESHEET_WEEK_TOTAL_HRS BETWEEN 0 AND 18000',        -- PAY_HRS_FILTER
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

  l_cursor := COREDATA.PKG_PR_UTIL_DEBUG.FIND_PAYROLL(l_params, l_svc_array);
  --l_cursor := COREDATA.PKG_PR_UTIL_DEBUG.FIND_PAYROLL(l_params, NULL);

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
              l_ts_detl.TIMESHEET_DET_SK,
              l_ts_detl.TIMESHEET_SUMMARY_SK,
              l_ts_detl.REC_CREATE_TMSTP,
              l_ts_detl.REC_UPDATE_TMSTP,
              l_ts_detl.REC_CREATED_BY,
              l_ts_detl.REC_UPDATED_BY,
              l_ts_detl.CHANGE_REASON_CODE,
              l_ts_detl.CHANGE_REASON_MEMO,
              l_ts_detl.CHANGE_VERSION_ID,
              l_ts_detl.BE_ID,
              l_ts_detl.PT_ID,
              l_ts_detl.VISIT_SK,
              l_ts_detl.BE_CALENDAR_LKUP_SK,
              l_ts_detl.SVC_ACTIVITY_BILLING_CODE_SK,
              l_ts_detl.PR_CODE,
              l_ts_detl.TIMESHEET_DATE,
              l_ts_detl.PR_RATE_AMT,
              l_ts_detl.PR_HRS,
              l_ts_detl.PR_AMT,
              l_ts_detl.RELEASE_TO_BILL_IND,
              l_pt_fn,
              l_pt_ln,
              l_service;

      EXIT WHEN l_ts_det_cursor%NOTFOUND;

      dbms_output.put_line (CHR(9) || CHR(9) || 'l_ts_det_cursor: l_visit_sk = ' || l_ts_detl.VISIT_SK);
      dbms_output.put_line (CHR(9) || CHR(9) || 'l_ts_det_cursor: l_pt_id = ' || l_ts_detl.PT_ID);
      dbms_output.put_line (CHR(9) || CHR(9) || 'l_ts_det_cursor: l_ts_date = ' || l_ts_detl.TIMESHEET_DATE);
      dbms_output.put_line (CHR(9) || CHR(9) || 'l_ts_det_cursor: l_pr_rate = ' || l_ts_detl.PR_RATE_AMT);
      dbms_output.put_line (CHR(9) || CHR(9) || 'l_ts_det_cursor: l_pr_hours = ' || l_ts_detl.PR_HRS);
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
