SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (

  SELECT * FROM (

      (SELECT ExcpLkup.EXCP_NAME, ExcpLst.*
        FROM BE_EXCP_LST ExcpLst

        INNER JOIN (SELECT EXCP_ID,EXCP_NAME
          FROM EXCP_LKUP
            WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) ExcpLkup
        ON ExcpLst.EXCP_ID = ExcpLkup.EXCP_ID

        WHERE (TO_CHAR(ExcpLst.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND ExcpLst.CURR_REC_IND = 1)
        ORDER BY BE_ID ASC
    )
  )

) R1)

WHERE ROW_NUMBER BETWEEN 1 AND 10;