SELECT * FROM 
  PT_PAYER T1
  INNER JOIN PAYER T2 ON T1.PAYER_ID = T2.PAYER_ID AND T1.BE_ID = T2.BE_ID
  LEFT JOIN ELIG T3 ON T1.PT_ID = T3.PT_ID AND T1.PAYER_ID = T3.PAYER_ID AND T1.BE_ID = T2.BE_ID AND (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = '1')
  LEFT JOIN CONTR T4 ON T1.PAYER_ID = T4.PAYER_ID AND T1.CONTR_ID = T4.CONTR_ID AND T1.BE_ID = T4.BE_ID AND (TO_CHAR(T4.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T4.CURR_REC_IND = '1')
  LEFT JOIN PAYER_LOB_LST T5 ON T1.PAYER_ID = T5.PAYER_ID AND T1.BE_ID = T5.BE_ID AND (TO_CHAR(T5.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T5.CURR_REC_IND = '1')
  LEFT JOIN PT_PAYER_INS T6 ON T1.PT_ID = T6.PT_ID AND T1.PAYER_ID = T6.PAYER_ID AND T1.BE_ID = T6.BE_ID AND (TO_CHAR(T6.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T6.CURR_REC_IND = '1')
  LEFT JOIN PT_PAYER_LMT T7 ON T1.PT_ID = T7.PT_ID AND T1.PAYER_ID = T7.PAYER_ID AND T1.BE_ID = T7.BE_ID AND (TO_CHAR(T7.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T7.CURR_REC_IND = '1')
WHERE T1.BE_ID = '1' AND T1.PT_ID = '900000902'
  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1')
  AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = '1');