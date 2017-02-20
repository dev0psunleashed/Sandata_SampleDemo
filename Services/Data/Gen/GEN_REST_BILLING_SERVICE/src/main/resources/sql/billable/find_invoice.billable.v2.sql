SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (

  SELECT * FROM (

      (SELECT DISTINCT T1.BE_ID,T1.BE_LOC_ID,T1.BILLING_SK,T2.BE_LOB,T3.PAYER_NAME,T4.CONTR_DESC,T1.BILLING_START_DATE,T1.BILLING_END_DATE,
          BIX.INV_NUM,T1.BILLING_DATE,T5.PT_FIRST_NAME,T5.PT_LAST_NAME,T1.PT_INS_ID_NUM,T6.BE_NAME,
          T1.BILLING_TOTAL_AMT,T1.BILLING_SUBM_TYP_NAME,T1.BILLING_FMT_TYP_NAME,
          T3.PAYER_ID,T4.CONTR_ID,T5.PT_ID,T1.BILLING_STATUS_NAME

        FROM BILLING T1

          -- Invoice Number
          LEFT JOIN (SELECT BE_ID,INV_NUM,PAYER_ID,CONTR_ID,PT_ID
            FROM BILLING_INV_XWALK
              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) BIX
          ON T1.BE_ID = BIX.BE_ID AND T1.PAYER_ID = BIX.PAYER_ID AND T1.CONTR_ID = BIX.CONTR_ID
            AND T1.PT_ID = BIX.PT_ID

          -- Line Of Business
          LEFT JOIN (SELECT BE_ID,BE_LOB_ID,BE_LOB,BILLING_RATE_MATRIX_EFF_DATE,BILLING_RATE_MATRIX_TERM_DATE,
                        PAYER_ID,CONTR_ID
            FROM BILLING_RATE_MATRIX
              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2
            ON T1.BE_ID = T2.BE_ID AND T1.BE_LOB_ID = T2.BE_LOB_ID
              AND T1.BILLING_START_DATE BETWEEN T2.BILLING_RATE_MATRIX_EFF_DATE AND T2.BILLING_RATE_MATRIX_TERM_DATE
              AND T1.PAYER_ID = T2.PAYER_ID AND T1.CONTR_ID = T2.CONTR_ID

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

          WHERE BILLING_START_DATE BETWEEN TO_DATE('2016-08-01 00:00', 'YYYY-MM-DD HH24:MI')
                  AND TO_DATE('2016-09-10 23:59', 'YYYY-MM-DD HH24:MI')
              AND
                BILLING_END_DATE BETWEEN TO_DATE('2016-08-01 00:00', 'YYYY-MM-DD HH24:MI')
                  AND TO_DATE('2016-09-10 23:59', 'YYYY-MM-DD HH24:MI')

            -- Exclude 'Deleted' Records
            AND NOT EXISTS (SELECT 1 FROM BILLING_HIST WHERE BILLING_HIST.BILLING_SK = T1.BILLING_SK AND ACTION_CODE = 'D')

            -- Billable should be either overridden OR not have any exceptions
            AND (BILLING_MANUAL_OVERRIDE_IND = 1 OR
                    NOT EXISTS (
                      SELECT 1 FROM BILLING_DET_EXCP
                      INNER JOIN BILLING_DET
                      ON BILLING_DET_EXCP.BILLING_DET_SK = BILLING_DET.BILLING_DET_SK
                      WHERE BILLING_DET.BE_ID = T1.BE_ID AND BILLING_DET.PT_ID = T1.PT_ID
                          AND BILLING_DET.PAYER_ID = T1.PAYER_ID AND BILLING_DET.CONTR_ID = T1.CONTR_ID
                          AND (TO_CHAR(BILLING_DET_EXCP.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND BILLING_DET_EXCP.CURR_REC_IND = 1)
                      --TMP--BILLING_SK is missing form BILLING_DET -- WHERE BILLING_DET.BILLING_SK = T1.BILLING_SK
                    ))

            -- Billable means the amount being billed is greater than zero
            AND (BILLING_TOTAL_AMT IS NOT NULL AND BILLING_TOTAL_AMT > 0)

            AND T1.BE_ID = '1'

            AND UPPER(T1.BILLING_STATUS_NAME) = 'PENDING'

            --AND UPPER(BE_NAME) = 'AGENCY MASTERS OF AMERICA'

        ORDER BY PAYER_NAME DESC)
  )

) R1)

WHERE ROW_NUMBER BETWEEN 1 AND 10;
