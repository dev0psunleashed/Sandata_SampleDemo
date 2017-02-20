-- Rate Type for given PatientID for given Visit Date
SELECT T1.RATE_TYP_NAME
  FROM PR_RATE_MATRIX T1
INNER JOIN (SELECT BE_ID,PT_ID,PAYER_ID,CONTR_ID,PAYER_RANK_NAME,
    SVC_NAME,PT_PAYER_EFF_DATE,PT_PAYER_TERM_DATE,REC_TERM_TMSTP,CURR_REC_IND
  FROM PT_PAYER
    WHERE TO_DATE('2016-07-04 23:59:59', 'YYYY-MM-DD HH24:MI:SS') BETWEEN PT_PAYER_EFF_DATE AND PT_PAYER_TERM_DATE
        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2
ON T1.BE_ID = T2.BE_ID
  AND T1.PAYER_ID = T2.PAYER_ID
  AND T1.CONTR_ID = T2.CONTR_ID
  AND T1.SVC_NAME = T2.SVC_NAME
  AND TO_DATE('2016-07-04 23:59:59', 'YYYY-MM-DD HH24:MI:SS') BETWEEN PR_RATE_MATRIX_EFF_DATE AND PR_RATE_MATRIX_TERM_DATE
WHERE T1.BE_ID = '1'
  AND T1.RATE_SUB_TYP_NAME = 'HOLIDAY'
  AND T2.PT_ID = '20160721041745216';