SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (

  SELECT * FROM (

      (SELECT *
          FROM RACE_ETHNICITY_LKUP
          ORDER BY RACE_ETHNICITY_DESC ASC)
  )

) R1)

WHERE ROW_NUMBER BETWEEN 1 AND 10;
