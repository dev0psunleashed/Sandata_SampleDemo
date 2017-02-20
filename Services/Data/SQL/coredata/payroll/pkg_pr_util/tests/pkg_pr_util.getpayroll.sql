SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_cursor SYS_REFCURSOR;

  l_staff_id STAFF.STAFF_ID%TYPE;
  l_staff_fn STAFF.STAFF_FIRST_NAME%TYPE;
  l_staff_ln STAFF.STAFF_LAST_NAME%TYPE;

  l_eft_txn_id PR_INPUT.EFT_TXN_ID%TYPE;
  l_check_num PR_INPUT.CHECK_NUM%TYPE;
  l_check_date PR_INPUT.CHECK_DATE%TYPE;
  l_net_pay PR_INPUT.NET_PAY_AMT%TYPE;
  l_gross_pay PR_INPUT.GROSS_PAY_AMT%TYPE;

  l_ts_det_cursor SYS_REFCURSOR;

  l_visit_sk TIMESHEET_DET.VISIT_SK%TYPE;
  l_pt_id TIMESHEET_DET.PT_ID%TYPE;
  l_ts_date TIMESHEET_DET.TIMESHEET_DATE%TYPE;
  l_pr_rate TIMESHEET_DET.PR_RATE_AMT%TYPE;
  l_pr_hours TIMESHEET_DET.PR_HRS%TYPE;
  l_pt_fn PT.PT_FIRST_NAME%TYPE;
  l_pt_ln PT.PT_LAST_NAME%TYPE;
  l_service SVC.SVC_NAME%TYPE;

  l_tss TIMESHEET_SUMMARY%ROWTYPE;

BEGIN

  dbms_output.put_line ('*** START: PKG_PR_UTIL.GET_PAYROLL ***');

  l_cursor := COREDATA.PKG_PR_UTIL.GET_PAYROLL(5384);

  IF l_cursor IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  LOOP
    FETCH l_cursor INTO
          l_tss,
          l_staff_fn,
          l_staff_ln,
          l_staff_id,
          l_eft_txn_id,
          l_check_num,
          l_check_date,
          l_net_pay,
          l_gross_pay,
          l_ts_det_cursor;

    EXIT WHEN l_cursor%NOTFOUND;

    dbms_output.put_line (CHR(9) || 'l_cursor: l_staff_fn = ' || l_staff_fn);
    dbms_output.put_line (CHR(9) || 'l_cursor: l_staff_ln = ' || l_staff_ln);
    dbms_output.put_line (CHR(9) || 'l_cursor: l_staff_id = ' || l_staff_id);
    dbms_output.put_line (CHR(9) || 'l_cursor: l_eft_txn_id = ' || l_eft_txn_id);
    dbms_output.put_line (CHR(9) || 'l_cursor: l_check_num = ' || l_check_num);
    dbms_output.put_line (CHR(9) || 'l_cursor: l_check_date = ' || l_check_date);
    dbms_output.put_line (CHR(9) || 'l_cursor: l_gross_pay = ' || l_gross_pay || CHR(13) || CHR(10));

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

  dbms_output.put_line ('*** END: PKG_PR_UTIL.GET_PAYROLL ***' || CHR(13) || CHR(10));

  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  --WHEN OTHERS THEN
  --CLOSE l_cursor;
  --dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;
