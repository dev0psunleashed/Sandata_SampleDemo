SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (

  SELECT * FROM (
  
      (SELECT T1.PR_INPUT_SK AS PAYROLL_SK,T3.STAFF_FIRST_NAME,T3.STAFF_LAST_NAME,T3.STAFF_ID,
          T1.CHECK_NUM,T1.NET_PAY_AMT AS CHECK_AMOUNT,T2.PR_TIMESHEET_TOTAL_HRS AS PAY_HOURS,
          T4.STAFF_RATE AS PAY_RATE,T1.WEEK_END_DATE AS PAY_END_DATE,T1.CHECK_DATE,
          
          -- GET THE SERVICE NAME
          (SELECT T1.SERVICE_NAME FROM (SELECT SERVICE_ACTIVITY_BILL_CODE_SK,SERVICE_SK FROM SERVICE_ACTIVITY_BILL_CODE) SERVICE_TABLE
            INNER JOIN (SELECT SERVICE_SK,SERVICE_NAME FROM SERVICE) T1 ON SERVICE_TABLE.SERVICE_SK = T1.SERVICE_SK
            WHERE SERVICE_TABLE.SERVICE_ACTIVITY_BILL_CODE_SK = (SELECT DISTINCT T1.SERVICE_ACTIVITY_BILL_CODE_SK FROM (SELECT DISTINCT TIMESHEET_SUMMARY_SK FROM PR_OUTPUT) PAYROLL_OUT
            INNER JOIN (SELECT TIMESHEET_SUMMARY_SK,SERVICE_ACTIVITY_BILL_CODE_SK FROM TIMESHEET_DETL) T1 ON PAYROLL_OUT.TIMESHEET_SUMMARY_SK = T1.TIMESHEET_SUMMARY_SK
            WHERE PAYROLL_OUT.TIMESHEET_SUMMARY_SK = T2.TIMESHEET_SUMMARY_SK AND T1.SERVICE_ACTIVITY_BILL_CODE_SK IS NOT NULL)) AS SERVICE_NAME
  
          FROM PR_INPUT T1
          
          INNER JOIN (SELECT BSN_ENT_ID,STAFF_ID,WEEK_END_DATE,TIMESHEET_SUMMARY_SK,PR_TIMESHEET_TOTAL_HRS,REC_TERM_TMSTP,CURR_REC_IND FROM PR_OUTPUT) T2
          ON T1.BSN_ENT_ID = T2.BSN_ENT_ID AND T1.STAFF_ID = T2.STAFF_ID AND T1.WEEK_END_DATE = T2.WEEK_END_DATE
  
          INNER JOIN (SELECT BSN_ENT_ID,STAFF_SK,STAFF_ID,STAFF_FIRST_NAME,STAFF_LAST_NAME,REC_TERM_TMSTP,CURR_REC_IND FROM STAFF) T3
          ON T1.BSN_ENT_ID = T2.BSN_ENT_ID AND T1.STAFF_ID = T3.STAFF_ID
  
          -- STAFF_RATE RECORD ** MUST ** EXIST
          INNER JOIN (SELECT STAFF_SK,STAFF_RATE FROM STAFF_RATE) T4
          ON T3.STAFF_SK = T4.STAFF_SK
         
         WHERE
         
              T1.WEEK_END_DATE BETWEEN TO_DATE('2015-11-24', 'YYYY-MM-DD') AND
              TO_DATE('2015-12-31', 'YYYY-MM-DD') AND
  
              (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) AND
              (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = 1) AND
              (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = 1) AND
  
              T1.BSN_ENT_ID = '1')
  )

  WHERE (CHECK_NUM IS NULL OR CHECK_NUM IS NOT NULL) AND SERVICE_NAME = 'HHA'
            
  
  ORDER BY UPPER(STAFF_LAST_NAME) ASC,UPPER(STAFF_FIRST_NAME) ASC
  
) R1)

WHERE ROW_NUMBER BETWEEN 1 AND 100;