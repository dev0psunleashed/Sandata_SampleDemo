SELECT B1.BSN_ENT_SK AS COMPANY_BSN_ENT_SK, B1.BSN_ENT_NAME AS COMPANY,
        B2.BSN_ENT_PARENT_ID, B2.BSN_ENT_ID AS LOCATION_BSN_ENT_ID,
        B3.BSN_ENT_NAME AS LOCATION FROM BSN_ENT B1
JOIN (SELECT BSN_ENT_ID,BSN_ENT_PARENT_ID
  FROM BSN_ENT_REL) B2
ON B1.BSN_ENT_ID = B2.BSN_ENT_PARENT_ID
JOIN (SELECT BSN_ENT_ID,BSN_ENT_NAME
  FROM BSN_ENT) B3
ON B2.BSN_ENT_ID = B3.BSN_ENT_ID
WHERE B1.BSN_ENT_ID = '1';
