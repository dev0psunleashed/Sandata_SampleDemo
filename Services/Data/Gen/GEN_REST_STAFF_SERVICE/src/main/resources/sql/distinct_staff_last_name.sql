SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (

  SELECT * FROM (

      (SELECT DISTINCT STAFF_LAST_NAME FROM STAFF
          WHERE STAFF_LAST_NAME IS NOT NULL AND
          BSN_ENT_ID = '1' ORDER BY STAFF_LAST_NAME ASC)
  )

) R1)

WHERE ROW_NUMBER BETWEEN 1 AND 100;
