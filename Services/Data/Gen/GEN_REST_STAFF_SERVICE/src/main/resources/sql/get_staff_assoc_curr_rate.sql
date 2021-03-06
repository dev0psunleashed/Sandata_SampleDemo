SELECT T4.BE_RATE_SK,T4.BE_RATE_AMT,T4.BE_RATE_EFF_DATE,T4.BE_RATE_TERM_DATE,
       T3.STAFF_RATE_SK,T3.STAFF_RATE_AMT,T3.STAFF_RATE_EFF_DATE,T3.STAFF_RATE_TERM_DATE,
       T2.PT_FIRST_NAME,T2.PT_LAST_NAME,T1.*
  FROM STAFF_ASSOC_RATE T1

-- Patient Name
INNER JOIN (SELECT BE_ID,PT_ID,PT_FIRST_NAME,PT_LAST_NAME,REC_TERM_TMSTP,CURR_REC_IND
  FROM PT WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2
ON T1.BE_ID = T2.BE_ID AND T1.PT_ID = T2.PT_ID

-- Staff Rate Overrides the Agency Rate
LEFT JOIN (SELECT STAFF_RATE_SK,BE_ID,STAFF_ID,SVC_NAME,RATE_SUB_TYP_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,
            STAFF_RATE_AMT,STAFF_RATE_EFF_DATE,STAFF_RATE_TERM_DATE
  FROM STAFF_RATE
    WHERE TO_DATE('2016-06-24 23:59:59', 'YYYY-MM-DD HH24:MI:SS') -- Current Date/Time
      BETWEEN STAFF_RATE_EFF_DATE AND STAFF_RATE_TERM_DATE) T3
ON T1.BE_ID = T3.BE_ID AND T1.STAFF_ID = T3.STAFF_ID
  AND T1.RATE_QLFR_CODE = T3.RATE_QLFR_CODE AND T1.SVC_NAME = T3.SVC_NAME
  AND T1.RATE_SUB_TYP_NAME = T3.RATE_SUB_TYP_NAME AND UPPER(T1.RATE_TYP_NAME) = UPPER(T3.RATE_TYP_NAME)

-- Agency Rate
LEFT JOIN (SELECT BE_RATE_SK,BE_ID,SVC_NAME,RATE_SUB_TYP_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,
            BE_RATE_AMT,BE_RATE_EFF_DATE,BE_RATE_TERM_DATE
  FROM BE_RATE
    WHERE TO_DATE('2016-06-24 23:59:59', 'YYYY-MM-DD HH24:MI:SS') -- Current Date/Time
      BETWEEN BE_RATE_EFF_DATE AND BE_RATE_TERM_DATE) T4
ON T1.BE_ID = T4.BE_ID
  AND T1.RATE_QLFR_CODE = T4.RATE_QLFR_CODE AND T1.SVC_NAME = T4.SVC_NAME
  AND T1.RATE_SUB_TYP_NAME = T4.RATE_SUB_TYP_NAME AND UPPER(T1.RATE_TYP_NAME) = UPPER(T4.RATE_TYP_NAME)

WHERE T1.STAFF_ID = '1' AND T1.BE_ID = '1' AND T1.RATE_QLFR_CODE = 'PAY'
  AND BE_RATE_SK IS NOT NULL -- There MUST be an Agency Rate, otherwise do not show the Assoc Rate!!
  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1);
