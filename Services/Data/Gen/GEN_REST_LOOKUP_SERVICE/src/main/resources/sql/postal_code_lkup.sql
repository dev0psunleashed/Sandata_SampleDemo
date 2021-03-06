SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (

  SELECT DISTINCT * FROM (

      (SELECT *
          FROM PSTL_CODE_LKUP
          WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)
            AND PSTL_CODE LIKE '%112%'
          ORDER BY PSTL_CODE ASC
    )
  )

) R1)

WHERE ROW_NUMBER BETWEEN 1 AND 100;
