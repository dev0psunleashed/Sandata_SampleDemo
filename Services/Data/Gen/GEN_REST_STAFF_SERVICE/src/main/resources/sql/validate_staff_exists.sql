SELECT STAFF_ID,STAFF_SK,BSN_ENT_ID
  FROM STAFF T1
WHERE STAFF_ID = '20160329031736608' AND BSN_ENT_ID = '1' AND
       (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1');
