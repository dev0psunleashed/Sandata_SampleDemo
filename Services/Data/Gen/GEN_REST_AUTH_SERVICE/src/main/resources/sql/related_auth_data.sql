SELECT * FROM PAYER WHERE PAYER_ID = '1' AND BE_ID = '1' AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1);
SELECT * FROM PT WHERE PT_ID = '1' AND BE_ID = '1' AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1);
