SELECT * FROM
   (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (SELECT * FROM (
     SELECT T1.AR_SK,
            T1.INV_NUM,
            T2.PT_FIRST_NAME,
            T2.PT_LAST_NAME,
            T2.PT_MIDDLE_NAME,
            T2.PT_ID,
            T3.PT_INS_ID_NUM,
            T7.CRN,
            T1.INV_DET_DATE,
            T1.BILLING_CODE,
            T1.AR_REMIT_CODE,
            T5.AR_TXN_CODE,
            T6.AR_TXN_SIGN_QLFR,
            T1.MDFR_1_CODE,
            T1.MDFR_2_CODE,
            T1.MDFR_3_CODE,
            T1.MDFR_4_CODE,
            T6.AR_TXN_DESC,
            T1.INV_TOTAL_AMT AS PAYMENT,
            T4.INV_TOTAL_AMT AS BALANCE,
            T4.INV_STATUS_CODE,
            T1.AR_NOTE_TYP_CODE,
            T1.AR_NOTE,
            T1.PAYER_ID
       FROM AR T1

           INNER JOIN (SELECT BE_ID,PT_FIRST_NAME,PT_LAST_NAME,PT_MIDDLE_NAME,PT_ID
              FROM PT WHERE (CURR_REC_IND=1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31')) T2
           ON T2.BE_ID = T1.BE_ID AND T2.PT_ID = T1.PT_ID

           INNER JOIN (SELECT BE_ID,PT_ID,PAYER_ID,PT_INS_ID_NUM
              FROM PT_PAYER_INS WHERE (CURR_REC_IND=1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31')) T3
           ON T3.BE_ID = T2.BE_ID AND T3.PT_ID = T2.PT_ID AND T3.PAYER_ID = T1.PAYER_ID

           INNER JOIN (SELECT BE_ID,INV_NUM,INV_TOTAL_AMT,INV_STATUS_CODE
              FROM INV) T4
           ON T4.BE_ID = T1.BE_ID AND T4.INV_NUM = T1.INV_NUM

           INNER JOIN (SELECT BE_ID,AR_TXN_BATCH_ID,AR_TXN_CODE
              FROM AR_TXN_BATCH) T5
           ON T5.BE_ID = T1.BE_ID AND T5.AR_TXN_BATCH_ID = T1.AR_TXN_BATCH_ID

           INNER JOIN (SELECT AR_TXN_CODE,AR_TXN_SIGN_QLFR,AR_TXN_DESC
              FROM AR_TXN_LKUP) T6
           ON T6.AR_TXN_CODE = T5.AR_TXN_CODE

           INNER JOIN (SELECT BE_ID,CRN,INV_NUM
              FROM INV_DET) T7
           ON T7.BE_ID = T1.BE_ID AND T7.INV_NUM = T1.INV_NUM

         WHERE T1.BE_ID = '1' AND T1.AR_TXN_BATCH_ID = '9035768_DMR'

      ORDER BY INV_NUM ASC)) R1)

WHERE ROW_NUMBER BETWEEN 1 AND 100;
