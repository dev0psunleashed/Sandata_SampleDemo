SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, r1.*
               FROM
                 (SELECT T1.* FROM STAFF T1
                   LEFT JOIN (SELECT SCHED_EVNT_SK,STAFF_ID,REC_TERM_TMSTP,CURR_REC_IND FROM SCHED_EVNT) T2
                     ON T1.STAFF_ID = T2.STAFF_ID
                 WHERE ((UPPER(T1.STAFF_FIRST_NAME) LIKE '7F94684C%') OR
                        (UPPER(T1.STAFF_LAST_NAME) LIKE '7F94684C%')) AND
                       (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) AND
                       (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = 1)
                  ORDER BY T1.STAFF_LAST_NAME ASC
                 ) r1)
WHERE ROW_NUMBER BETWEEN 1 AND 10;