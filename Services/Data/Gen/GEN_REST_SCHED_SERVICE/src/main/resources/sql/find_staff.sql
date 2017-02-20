SELECT * FROM (
  SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, r1.* FROM
  (SELECT DISTINCT T1.STAFF_SK,T1.STAFF_ID,T1.STAFF_FIRST_NAME,T1.STAFF_LAST_NAME,T6.LANG_NAME AS STAFF_PRMY_LANG,T1.STAFF_GENDER_TYP_NAME,
      T3.STAFF_ADDR1,T3.STAFF_ADDR2,T3.STAFF_CITY,T3.STAFF_STATE,T3.STAFF_ZIP4,T1.STAFF_POSITION_NAME
  FROM STAFF T1

  LEFT JOIN (SELECT STAFF_ID,STAFF_ADDR1,STAFF_ADDR2,STAFF_CITY,STAFF_STATE,STAFF_ZIP4,ADDR_PRIO_NAME,REC_TERM_TMSTP,CURR_REC_IND
    FROM STAFF_CONT_ADDR WHERE UPPER(ADDR_PRIO_NAME) = 'PRIMARY' AND
      (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3
  ON T1.STAFF_ID = T3.STAFF_ID

  LEFT JOIN (SELECT BE_ID,STAFF_ID,LANG_CODE,STAFF_LANG_PRMY_IND
    FROM STAFF_LANG WHERE STAFF_LANG_PRMY_IND = 1 AND
      (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T5
  ON T1.STAFF_ID = T5.STAFF_ID AND T1.BE_ID = T5.BE_ID

  LEFT JOIN (SELECT LANG_CODE,LANG_NAME
    FROM LANG_LKUP
      WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T6
  ON T5.LANG_CODE = T6.LANG_CODE

  WHERE T1.BE_ID = '1' AND
    (UPPER(T1.STAFF_POSITION_NAME) = 'RN')
      AND (UPPER(T1.STAFF_FIRST_NAME) LIKE 'RUTGOS%' OR UPPER(T1.STAFF_LAST_NAME) LIKE 'RUTGOS%')
      AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1)

  ORDER BY DECODE(STAFF_PRMY_LANG,'English',1) ASC
) r1)

WHERE ROW_NUMBER BETWEEN 1 AND 10;