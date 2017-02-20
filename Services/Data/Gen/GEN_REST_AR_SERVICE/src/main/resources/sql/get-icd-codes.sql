SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (

  SELECT * FROM (
  
      (SELECT T1.*
          
          FROM ICD_DIAGNOSIS_CODE T1
          
          WHERE T1.ICD_DIAGNOSIS_CODE_SK IS NOT NULL AND UPPER(T1.ICD_DIAGNOSIS_CODE) LIKE 'AA%'
         
      )
  )         
  
  ORDER BY UPPER(ICD_DIAGNOSIS_CODE) ASC
  
) R1)

WHERE ROW_NUMBER BETWEEN 1 AND 10;