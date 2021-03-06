SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (

  SELECT * FROM (

      (SELECT * FROM ADMIN_STAFF
          WHERE ADMIN_STAFF_FIRST_NAME IS NOT NULL
            AND ADMIN_STAFF_LAST_NAME IS NOT NULL
            AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)
            AND BE_ID = '1'
        ORDER BY ADMIN_STAFF_LAST_NAME ASC)
  )

) R1)

WHERE ROW_NUMBER BETWEEN 1 AND 10;
