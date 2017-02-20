SELECT DISTINCT T1.ADMIN_STAFF_SK,T1.ADMIN_STAFF_ID,T1.ADMIN_STAFF_FIRST_NAME,T1.ADMIN_STAFF_LAST_NAME,
          T2.ADMIN_STAFF_REL_SK,T2.ADMIN_STAFF_REL_TYP_NAME,
          T4.PT_ID,T4.PT_FIRST_NAME,T4.PT_LAST_NAME
  FROM ADMIN_STAFF T1

INNER JOIN (SELECT ADMIN_STAFF_ID,ADMIN_STAFF_REL_SK,ADMIN_STAFF_REL_TYP_NAME,REC_TERM_TMSTP,CURR_REC_IND,
              ADMIN_STAFF_REL_EFF_DATE,ADMIN_STAFF_REL_TERM_DATE
  FROM ADMIN_STAFF_REL
    WHERE TO_DATE('2016-07-14 10:44:49', 'YYYY-MM-DD HH24:MI:SS')
      BETWEEN ADMIN_STAFF_REL_EFF_DATE AND ADMIN_STAFF_REL_TERM_DATE
        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2
ON T1.ADMIN_STAFF_ID = T2.ADMIN_STAFF_ID

INNER JOIN (SELECT BE_ID,ADMIN_STAFF_ID,PT_ID,REC_TERM_TMSTP,CURR_REC_IND,
              ADMIN_STAFF_PT_EFF_DATE,ADMIN_STAFF_PT_TERM_DATE
  FROM ADMIN_STAFF_PT
    WHERE TO_DATE('2016-07-14 10:44:49', 'YYYY-MM-DD HH24:MI:SS')
      BETWEEN ADMIN_STAFF_PT_EFF_DATE AND ADMIN_STAFF_PT_TERM_DATE
        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3
ON T1.BE_ID = T3.BE_ID AND T1.ADMIN_STAFF_ID = T3.ADMIN_STAFF_ID

INNER JOIN (SELECT BE_ID,PT_ID,PT_FIRST_NAME,PT_LAST_NAME,REC_TERM_TMSTP,CURR_REC_IND
  FROM PT
    WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4
ON T3.BE_ID = T4.BE_ID AND T3.PT_ID = T4.PT_ID

WHERE T1.BE_ID = '1' AND T4.PT_ID = '1' AND UPPER(T2.ADMIN_STAFF_REL_TYP_NAME) = 'COORDINATOR'
  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1)
ORDER BY UPPER(T1.ADMIN_STAFF_LAST_NAME) ASC;