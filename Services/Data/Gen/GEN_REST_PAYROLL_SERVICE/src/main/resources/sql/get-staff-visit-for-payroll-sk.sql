SELECT * FROM (SELECT r1.*
   FROM
     (SELECT T1.PR_INPUT_SK AS PAYROLL_SK,T3.VISIT_SK,T1.GROSS_PAY_AMT AS TOTAL_PAY,
              T5.PT_FIRST_NAME,T5.PT_LAST_NAME,T5.PT_ID,T2.PR_RATE AS RATE,T4.VISIT_PAY_HRS AS HOURS,

              -- TMP: WAITING ON BA AS PER DEV MEETING
              T4.VISIT_OT_ABS_HRS AS TOTAL_ADDITIONAL_PAY,

              ROUND((T4.VISIT_BILL_HRS*T2.PR_RATE),2) AS TOTAL_VISIT_PAY FROM PR_INPUT T1

       LEFT JOIN (SELECT BE_ID,STAFF_ID,WEEK_END_DATE,TIMESHEET_SUMMARY_SK,PR_RATE,
                    REC_TERM_TMSTP,CURR_REC_IND FROM PR_OUTPUT) T2
         ON T1.BE_ID = T2.BE_ID AND T1.STAFF_ID = T2.STAFF_ID AND T1.WEEK_END_DATE = T2.WEEK_END_DATE
       LEFT JOIN (SELECT TIMESHEET_SUMMARY_SK,VISIT_SK FROM TIMESHEET_DET) T3
         ON T2.TIMESHEET_SUMMARY_SK = T3.TIMESHEET_SUMMARY_SK
       LEFT JOIN (SELECT VISIT_SK,PT_ID,VISIT_PAY_HRS,VISIT_OT_ABS_HRS,VISIT_BILL_HRS FROM VISIT) T4
         ON T3.VISIT_SK = T4.VISIT_SK
       LEFT JOIN (SELECT PT_ID,PT_FIRST_NAME,PT_LAST_NAME FROM PT) T5
         ON T4.PT_ID = T5.PT_ID

      WHERE T1.PR_INPUT_SK = 1
) r1);
