SELECT PATIENT_ID,PATIENT_SK,BSN_ENT_ID
  FROM PATIENT T1
WHERE PATIENT_ID = 'ff925201-8d30-46aa-af4b-9e0db0fe521e' AND BSN_ENT_ID = '1' AND
       (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1');