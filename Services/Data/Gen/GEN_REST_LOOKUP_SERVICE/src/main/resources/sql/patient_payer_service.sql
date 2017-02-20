SELECT DISTINCT T1.*
    FROM SERVICE T1

    INNER JOIN (SELECT SERVICE_SK,BSN_ENT_ID,PATIENT_ID
      FROM PATIENT_PAYER) T2
    ON T1.BSN_ENT_ID = T2.BSN_ENT_ID AND T1.SERVICE_SK = T2.SERVICE_SK

    WHERE T2.PATIENT_ID = '20160211092632409'

    ORDER BY T1.SERVICE_NAME;