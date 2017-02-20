SELECT *
FROM
  (SELECT ROWNUM ROW_NUMBER,
    COUNT(*) OVER() TOTAL_ROWS,
    R1.*
  FROM
    (SELECT *
    FROM (
      (SELECT DISTINCT T1.PT_ID,
        T1.PT_SK,
        T1.BE_ID,
        T1.PT_FIRST_NAME,
        T1.PT_LAST_NAME,
        T1.PT_MIDDLE_NAME,
        T1.PT_DOB,
        T1.PT_STATUS_NAME,
        T1.PT_PRIO_LVL_CODE,
        T2.PT_ADDR1,
        T2.PT_ADDR2,
        T2.PT_APT_NUM,
        T2.PT_CITY,
        T2.PT_STATE,
        T2.PT_PSTL_CODE,
        T2.PT_ZIP4,
        T3.BE_NAME,
        T3.BE_TYP,
        T3.BE_PRMY_ADDR1,
        T3.BE_PRMY_ADDR2,
        T3.BE_PRMY_CITY,
        T3.BE_PRMY_STATE,
        T3.BE_PRMY_PSTL_CODE,
        T3.BE_PRMY_ZIP4,
        T3.BE_PRMY_PHONE_1,
        
        (
            SELECT PT_PHONE FROM (
                SELECT P1.PT_PHONE, P1.PT_CONT_PHONE_QLFR, NVL(P1.PT_PHONE_PRMY_IND, 0) AS PT_PHONE_PRMY_IND, P1.PT_ID, P1.BE_ID 
                FROM PT_CONT_PHONE P1 
                WHERE P1.PT_PHONE IS NOT NULL
                    AND (TO_CHAR(P1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND P1.CURR_REC_IND = 1)
                ORDER BY P1.PT_PHONE_PRMY_IND DESC, DECODE(UPPER(P1.PT_CONT_PHONE_QLFR), 'HOME', 1, 'MOBILE', 2, 'WORK', 3, 'FAX', 4, 5)
            
            ) P2 WHERE (P2.PT_ID = T1.PT_ID AND P2.BE_ID = T1.BE_ID) AND ROWNUM = 1
        
        ) AS PT_PHONE
        ,
        
        T6.PT_EMAIL
      FROM PT T1
      LEFT JOIN
        (SELECT PT_ID,
          BE_ID,
          ADDR_PRIO_NAME,
          PT_ADDR1,
          PT_ADDR2,
          PT_APT_NUM,
          PT_CITY,
          PT_STATE,
          PT_PSTL_CODE,
          PT_ZIP4,
          REC_TERM_TMSTP,
          CURR_REC_IND
        FROM PT_CONT_ADDR
        WHERE UPPER(ADDR_PRIO_NAME) = 'PRIMARY'
        ) T2
      ON T1.PT_ID                                   = T2.PT_ID
      AND T1.BE_ID                                  = T2.BE_ID
      AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'
      AND T2.CURR_REC_IND                           = 1)
      LEFT JOIN
        (SELECT BE_ID,
          BE_NAME,
          BE_TYP,
          BE_PRMY_ADDR1,
          BE_PRMY_ADDR2,
          BE_PRMY_CITY,
          BE_PRMY_STATE,
          BE_PRMY_PSTL_CODE,
          BE_PRMY_ZIP4,
          BE_PRMY_PHONE_1,
          REC_TERM_TMSTP,
          CURR_REC_IND
        FROM BE
        ) T3
      ON T1.BE_ID                                   = T3.BE_ID
      AND (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'
      AND T3.CURR_REC_IND                           = 1)
      
      LEFT OUTER JOIN
        (SELECT PT_ID,
          BE_ID,
          PT_EMAIL_PRMY_IND,
          PT_EMAIL,
          REC_TERM_TMSTP,
          CURR_REC_IND
        FROM PT_CONT_EMAIL
        WHERE (PT_EMAIL_PRMY_IND                 IS NOT NULL
        AND PT_EMAIL_PRMY_IND                     = 1
        AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'
        AND CURR_REC_IND                          = 1)
        AND PT_EMAIL                             IS NOT NULL
        ) T6
      ON T1.PT_ID  = T6.PT_ID
      AND T1.BE_ID = T6.BE_ID
      WHERE T1.PT_ID = (
          SELECT DISTINCT PT_ID FROM PT_CONT_PHONE 
          WHERE PT_PHONE LIKE '2342341112%' AND
          BE_ID = T1.BE_ID AND PT_ID = T1.PT_ID AND
          (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)
      )
      AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'
      AND T1.CURR_REC_IND                           = 1)
      AND T1.BE_ID                                  = 1
      ORDER BY T1.PT_LAST_NAME ASC
      ) )
    ) R1
  )
WHERE ROW_NUMBER BETWEEN 1 AND 10