SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (

  SELECT * FROM (

      (SELECT * FROM ADMIN_STAFF_PT
          WHERE TO_DATE('2016-06-29 16:11:54', 'YYYY-MM-DD HH24:MI:SS') -- Current Date
              BETWEEN ADMIN_STAFF_PT_EFF_DATE AND ADssssssMIN_STAFF_PT_TERM_DATE
            AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)
            AND BE_ID = '1' AND PT_ID = '1'
        ORDER BY ADMIN_STAFF_ID ASC)
  )

) R1)

WHERE ROW_NUMBER BETWEEN 1 AND 10;
