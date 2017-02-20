SELECT DISTINCT T1.ADMIN_STAFF_SK,T1.ADMIN_STAFF_ID,T1.ADMIN_STAFF_FIRST_NAME,T1.ADMIN_STAFF_LAST_NAME,
          T2.ADMIN_STAFF_REL_SK,T2.ADMIN_STAFF_REL_TYP_NAME,T3.STAFF_ID,T3.ADMIN_STAFF_STAFF_XWALK_SK
  FROM ADMIN_STAFF T1
INNER JOIN (SELECT BE_ID,ADMIN_STAFF_ID,ADMIN_STAFF_REL_SK,ADMIN_STAFF_REL_TYP_NAME,REC_TERM_TMSTP,CURR_REC_IND,
              ADMIN_STAFF_REL_EFF_DATE,ADMIN_STAFF_REL_TERM_DATE
  FROM ADMIN_STAFF_REL
    WHERE TO_DATE('2016-06-29 23:59:59', 'YYYY-MM-DD HH24:MI:SS') -- Current Date/Time
      BETWEEN ADMIN_STAFF_REL_EFF_DATE AND ADMIN_STAFF_REL_TERM_DATE
        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2
ON T1.ADMIN_STAFF_ID = T2.ADMIN_STAFF_ID
INNER JOIN (SELECT ADMIN_STAFF_STAFF_XWALK_SK,BE_ID,ADMIN_STAFF_ID,STAFF_ID,REC_TERM_TMSTP,CURR_REC_IND,
              ADMIN_STAFF_STAFF_EFF_DATE,ADMIN_STAFF_STAFF_TERM_DATE
  FROM ADMIN_STAFF_STAFF_XWALK
    WHERE TO_DATE('2016-06-29 23:59:59', 'YYYY-MM-DD HH24:MI:SS') -- Current Date/Time
      BETWEEN ADMIN_STAFF_STAFF_EFF_DATE AND ADMIN_STAFF_STAFF_TERM_DATE
        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3
ON T2.ADMIN_STAFF_ID = T3.ADMIN_STAFF_ID AND T2.BE_ID = T3.BE_ID
WHERE T1.BE_ID = '1' AND T3.STAFF_ID = '1'
  AND UPPER(T2.ADMIN_STAFF_REL_TYP_NAME) = 'COORDINATOR'
  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1)
ORDER BY UPPER(T1.ADMIN_STAFF_LAST_NAME) ASC;
