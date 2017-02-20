CREATE OR REPLACE PACKAGE PKG_PATIENT_UTIL IS
  FUNCTION CANCEL_SCHED_EVNTS_DISCHGED_PT(v_bsn_ent_id IN SCHED_EVNT.BE_ID%TYPE, v_pt_id IN SCHED_EVNT.PT_ID%TYPE, v_pt_discharged_date_str IN VARCHAR2) RETURN NUMBER;
  
  FUNCTION TERM_SCHED_EVNTS_DISCHGED_PT(v_bsn_ent_id IN SCHED_EVNT.BE_ID%TYPE, v_pt_id IN SCHED_EVNT.PT_ID%TYPE, v_pt_discharged_date_str IN VARCHAR2) RETURN NUMBER;
  
  FUNCTION DELETE_PT_ALLERGY_LST(v_bsn_ent_id IN PT_ALLERGY.BE_ID%TYPE, v_pt_id IN PT_ALLERGY.PT_ID%TYPE, v_allergy_name_lst IN ALLERGY_NAME_LIST) RETURN NUMBER;
  
  FUNCTION DELETE_PT_DME_SUPPLY_LST(v_bsn_ent_id IN PT_DME_AND_SUPPLY.BE_ID%TYPE, v_pt_id IN PT_DME_AND_SUPPLY.PT_ID%TYPE, v_dme_supply_id_lst IN DME_SUPPLY_ID_LIST) RETURN NUMBER;
  
  FUNCTION DELETE_PT_NUTR_RQMT_LST(v_bsn_ent_id IN PT_NUTR_RQMT.BE_ID%TYPE, v_pt_id IN PT_NUTR_RQMT.PT_ID%TYPE, v_nutr_rqmt_name_lst IN NUTR_RQMT_NAME_LIST) RETURN NUMBER;
  
  FUNCTION END_DATE_AUTH_FOR_DISCHGED_PT(v_bsn_ent_id IN AUTH.BE_ID%TYPE, v_pt_id IN AUTH.PT_ID%TYPE, v_pt_discharged_date_str IN VARCHAR2) RETURN NUMBER;
  
  FUNCTION END_DATE_ORDER_FOR_DISCHGED_PT(v_bsn_ent_id IN ORD.BE_ID%TYPE, v_pt_id IN ORD.PT_ID%TYPE, v_pt_discharged_date_str IN VARCHAR2) RETURN NUMBER;
  
  FUNCTION END_DATE_POC_FOR_DISCHGED_PT(v_bsn_ent_id IN POC.BE_ID%TYPE, v_pt_id IN POC.PT_ID%TYPE, v_pt_discharged_date_str IN VARCHAR2) RETURN NUMBER;
END PKG_PATIENT_UTIL;
/

CREATE OR REPLACE PACKAGE BODY PKG_PATIENT_UTIL IS
  FUNCTION CANCEL_SCHED_EVNTS_DISCHGED_PT(v_bsn_ent_id IN SCHED_EVNT.BE_ID%TYPE, v_pt_id IN SCHED_EVNT.PT_ID%TYPE, v_pt_discharged_date_str IN VARCHAR2) RETURN NUMBER
  AS
    TYPE SCHED_EVNT_SK_LIST_T IS TABLE OF SCHED_EVNT.SCHED_EVNT_SK%TYPE INDEX BY PLS_INTEGER;
    sched_evnt_sk_lst SCHED_EVNT_SK_LIST_T;
  BEGIN
    SELECT SCHED_EVNT_SK BULK COLLECT INTO sched_evnt_sk_lst
    FROM SCHED_EVNT
    WHERE 
      BE_ID = v_bsn_ent_id 
      AND PT_ID = v_pt_id
      AND SCHED_EVNT_START_DTIME >= TO_DATE(v_pt_discharged_date_str, 'MM/DD/YYYY HH24:MI:SS')
      AND SCHED_EVNT_STATUS='PENDING'
      AND (CURR_REC_IND=1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31')
      FOR UPDATE NOWAIT;
  
    FORALL idx IN sched_evnt_sk_lst.FIRST .. sched_evnt_sk_lst.LAST
    UPDATE SCHED_EVNT
    SET REC_UPDATE_TMSTP=CURRENT_TIMESTAMP, REC_EFF_TMSTP=TO_DATE(v_pt_discharged_date_str, 'MM/DD/YYYY HH24:MI:SS'), SCHED_EVNT_STATUS='CANCELLED'
    WHERE SCHED_EVNT_SK = sched_evnt_sk_lst(idx);
  
    RETURN SQL%ROWCOUNT;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
        
  END CANCEL_SCHED_EVNTS_DISCHGED_PT;
  
------------------------------------------------------------------------
FUNCTION TERM_SCHED_EVNTS_DISCHGED_PT(v_bsn_ent_id IN SCHED_EVNT.BE_ID%TYPE, v_pt_id IN SCHED_EVNT.PT_ID%TYPE, v_pt_discharged_date_str IN VARCHAR2) RETURN NUMBER
  AS
    TYPE SCHED_EVNT_SK_LIST_T IS TABLE OF SCHED_EVNT.SCHED_EVNT_SK%TYPE INDEX BY PLS_INTEGER;
    sched_evnt_sk_lst SCHED_EVNT_SK_LIST_T;
  BEGIN
    SELECT SCHED_EVNT_SK BULK COLLECT INTO sched_evnt_sk_lst
    FROM SCHED_EVNT
    WHERE 
      BE_ID = v_bsn_ent_id 
      AND PT_ID = v_pt_id
      AND SCHED_EVNT_START_DTIME >= TO_DATE(v_pt_discharged_date_str, 'MM/DD/YYYY HH24:MI:SS')
      AND UPPER(SCHED_EVNT_STATUS) = 'PENDING'
      AND (CURR_REC_IND=1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31')
      FOR UPDATE NOWAIT;
  
    FORALL idx IN sched_evnt_sk_lst.FIRST .. sched_evnt_sk_lst.LAST
    UPDATE SCHED_EVNT
    SET 
        REC_UPDATE_TMSTP=CURRENT_TIMESTAMP, 
        REC_EFF_TMSTP=TO_DATE(v_pt_discharged_date_str, 'MM/DD/YYYY HH24:MI:SS'), 
        REC_TERM_TMSTP=CURRENT_TIMESTAMP,
        CURR_REC_IND=0,
        SCHED_EVNT_STATUS='CANCELLED'
    WHERE SCHED_EVNT_SK = sched_evnt_sk_lst(idx);
  
    RETURN SQL%ROWCOUNT;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
        
  END TERM_SCHED_EVNTS_DISCHGED_PT;

------------------------------------------------------------------------
  FUNCTION DELETE_PT_ALLERGY_LST(v_bsn_ent_id IN PT_ALLERGY.BE_ID%TYPE, v_pt_id IN PT_ALLERGY.PT_ID%TYPE, v_allergy_name_lst IN ALLERGY_NAME_LIST) RETURN NUMBER
  AS
    TYPE PT_ALLERGY_SK_LIST_T IS TABLE OF PT_ALLERGY.PT_ALLERGY_SK%TYPE INDEX BY PLS_INTEGER;
    pt_allergy_sk_lst PT_ALLERGY_SK_LIST_T;
  BEGIN  
    IF (v_allergy_name_lst IS NOT NULL) THEN
      SELECT PT_ALLERGY_SK BULK COLLECT INTO pt_allergy_sk_lst
      FROM PT_ALLERGY
      WHERE 
        BE_ID = v_bsn_ent_id 
        AND PT_ID = v_pt_id 
        AND ALLERGY_NAME IN (SELECT * FROM TABLE(v_allergy_name_lst))
      FOR UPDATE NOWAIT;
      
    ELSE
      SELECT PT_ALLERGY_SK BULK COLLECT INTO pt_allergy_sk_lst
      FROM PT_ALLERGY
      WHERE 
        BE_ID = v_bsn_ent_id 
        AND PT_ID = v_pt_id 
      FOR UPDATE NOWAIT;
    END IF;

    FORALL idx1 IN pt_allergy_sk_lst.FIRST .. pt_allergy_sk_lst.LAST
    UPDATE PT_ALLERGY
    SET REC_TERM_TMSTP = CURRENT_DATE, CURR_REC_IND = 0
    WHERE PT_ALLERGY_SK = pt_allergy_sk_lst(idx1);
      
    RETURN SQL%ROWCOUNT;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
      
  END DELETE_PT_ALLERGY_LST;

------------------------------------------------------------------------
  FUNCTION DELETE_PT_DME_SUPPLY_LST(v_bsn_ent_id IN PT_DME_AND_SUPPLY.BE_ID%TYPE, v_pt_id IN PT_DME_AND_SUPPLY.PT_ID%TYPE, v_dme_supply_id_lst IN DME_SUPPLY_ID_LIST) RETURN NUMBER
  AS
    TYPE PT_DME_AND_SUPPLY_SK_LST IS TABLE OF PT_DME_AND_SUPPLY.PT_DME_AND_SUPPLY_SK%TYPE INDEX BY PLS_INTEGER;
    pt_dme_supply_sk_lst PT_DME_AND_SUPPLY_SK_LST;
  BEGIN
    IF (v_dme_supply_id_lst IS NOT NULL) THEN
      SELECT PT_DME_AND_SUPPLY_SK BULK COLLECT INTO pt_dme_supply_sk_lst
      FROM PT_DME_AND_SUPPLY
      WHERE 
        BE_ID = v_bsn_ent_id 
        AND PT_ID = v_pt_id 
        AND DME_SUPPLY_ID IN (SELECT * FROM TABLE(v_dme_supply_id_lst))
      FOR UPDATE NOWAIT;
      
    ELSE
      SELECT PT_DME_AND_SUPPLY_SK BULK COLLECT INTO pt_dme_supply_sk_lst
      FROM PT_DME_AND_SUPPLY
      WHERE 
        BE_ID = v_bsn_ent_id 
        AND PT_ID = v_pt_id
      FOR UPDATE NOWAIT;
    END IF;
    
    FORALL idx IN pt_dme_supply_sk_lst.FIRST .. pt_dme_supply_sk_lst.LAST
    UPDATE PT_DME_AND_SUPPLY
    SET REC_TERM_TMSTP = CURRENT_DATE, CURR_REC_IND = 0
    WHERE PT_DME_AND_SUPPLY_SK = pt_dme_supply_sk_lst(idx);
    
    RETURN SQL%ROWCOUNT;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
      
  END DELETE_PT_DME_SUPPLY_LST;

------------------------------------------------------------------------  
  FUNCTION DELETE_PT_NUTR_RQMT_LST(v_bsn_ent_id IN PT_NUTR_RQMT.BE_ID%TYPE, v_pt_id IN PT_NUTR_RQMT.PT_ID%TYPE, v_nutr_rqmt_name_lst IN NUTR_RQMT_NAME_LIST) RETURN NUMBER
  AS
    TYPE PT_NUTR_RQMT_SK_LST IS TABLE OF PT_NUTR_RQMT.PT_NUTR_RQMT_SK%TYPE INDEX BY PLS_INTEGER;
    pt_nutr_rqmt_sk_list PT_NUTR_RQMT_SK_LST;
  BEGIN
    IF (v_nutr_rqmt_name_lst IS NOT NULL) THEN
      SELECT PT_NUTR_RQMT_SK BULK COLLECT INTO pt_nutr_rqmt_sk_list
      FROM PT_NUTR_RQMT
      WHERE 
        BE_ID = v_bsn_ent_id 
        AND PT_ID = v_pt_id 
        AND NUTR_RQMT_NAME IN (SELECT * FROM TABLE(v_nutr_rqmt_name_lst))
      FOR UPDATE NOWAIT;
      
    ELSE
      SELECT PT_NUTR_RQMT_SK BULK COLLECT INTO pt_nutr_rqmt_sk_list
      FROM PT_NUTR_RQMT
      WHERE 
        BE_ID = v_bsn_ent_id 
        AND PT_ID = v_pt_id 
      FOR UPDATE NOWAIT;
    END IF;
    
    FORALL idx IN pt_nutr_rqmt_sk_list.FIRST .. pt_nutr_rqmt_sk_list.LAST
    UPDATE PT_NUTR_RQMT
    SET REC_TERM_TMSTP = CURRENT_DATE, CURR_REC_IND = 0
    WHERE PT_NUTR_RQMT_SK = pt_nutr_rqmt_sk_list(idx);
    
    RETURN SQL%ROWCOUNT;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
  
  END DELETE_PT_NUTR_RQMT_LST;
  
------------------------------------------------------------------------  
  FUNCTION END_DATE_AUTH_FOR_DISCHGED_PT(v_bsn_ent_id IN AUTH.BE_ID%TYPE, v_pt_id IN AUTH.PT_ID%TYPE, v_pt_discharged_date_str IN VARCHAR2) RETURN NUMBER
  AS
    auth_sk_list NUMBER_ARRAY;
    auth_svc_sk_list NUMBER_ARRAY;
    auth_select_row_count NUMBER;
    auth_svc_select_row_count NUMBER;
    updated_row_count NUMBER;
  BEGIN
    SELECT AUTH_SK BULK COLLECT INTO auth_sk_list
    FROM AUTH
    WHERE
      BE_ID = v_bsn_ent_id
      AND PT_ID = v_pt_id
      AND AUTH_END_TMSTP >= TO_DATE(v_pt_discharged_date_str, 'MM/DD/YYYY HH24:MI:SS')
      AND (CURR_REC_IND=1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31')
    FOR UPDATE NOWAIT;
    auth_select_row_count := SQL%ROWCOUNT;
  
    SELECT AUTH_SVC_SK BULK COLLECT INTO auth_svc_sk_list
    FROM AUTH_SVC
    WHERE BE_ID = v_bsn_ent_id AND AUTH_SK IN (SELECT * FROM TABLE(auth_sk_list))
    FOR UPDATE NOWAIT;
    auth_svc_select_row_count := SQL%ROWCOUNT;
  
    FORALL idx IN auth_svc_sk_list.FIRST .. auth_svc_sk_list.LAST
    UPDATE AUTH_SVC
    SET 
      REC_UPDATE_TMSTP = CURRENT_TIMESTAMP,
      AUTH_SVC_END_DATE = TO_DATE(v_pt_discharged_date_str, 'MM/DD/YYYY HH24:MI:SS')
    WHERE AUTH_SVC_SK = auth_svc_sk_list(idx);
    updated_row_count := SQL%ROWCOUNT;
    
    IF (auth_svc_select_row_count != updated_row_count) THEN
      RETURN -2;
    END IF;
    
    FORALL idx1 IN auth_sk_list.FIRST .. auth_sk_list.LAST
    UPDATE AUTH
    SET 
      REC_UPDATE_TMSTP = CURRENT_TIMESTAMP,
      REC_EFF_TMSTP = TO_DATE(v_pt_discharged_date_str, 'MM/DD/YYYY HH24:MI:SS'), 
      AUTH_END_TMSTP = TO_DATE(v_pt_discharged_date_str, 'MM/DD/YYYY HH24:MI:SS')
    WHERE AUTH_SK = auth_sk_list(idx1);
    updated_row_count := SQL%ROWCOUNT;
  
    IF (auth_select_row_count != updated_row_count) THEN
      RETURN -2;
    END IF;
  
    RETURN updated_row_count;
    
  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
  
  END END_DATE_AUTH_FOR_DISCHGED_PT;

------------------------------------------------------------------------  
  FUNCTION END_DATE_ORDER_FOR_DISCHGED_PT(v_bsn_ent_id IN ORD.BE_ID%TYPE, v_pt_id IN ORD.PT_ID%TYPE, v_pt_discharged_date_str IN VARCHAR2) RETURN NUMBER
  AS
    ord_sk_list NUMBER_ARRAY;
    ord_svc_sk_list NUMBER_ARRAY;
    ord_select_row_count NUMBER;
    ord_svc_select_row_count NUMBER;
    updated_row_count NUMBER;
  BEGIN
    SELECT ORD_SK BULK COLLECT INTO ord_sk_list
    FROM ORD
    WHERE
      BE_ID = v_bsn_ent_id
      AND PT_ID = v_pt_id
      AND ORD_END_TMSTP >= TO_DATE(v_pt_discharged_date_str, 'MM/DD/YYYY HH24:MI:SS')
      AND (CURR_REC_IND=1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31')
    FOR UPDATE NOWAIT;
    ord_select_row_count := SQL%ROWCOUNT;
  
    SELECT ORD_SVC_SK BULK COLLECT INTO ord_svc_sk_list
    FROM ORD_SVC
    WHERE BE_ID = v_bsn_ent_id AND ORD_SK IN (SELECT * FROM TABLE(ord_sk_list))
    FOR UPDATE NOWAIT;
    ord_svc_select_row_count := SQL%ROWCOUNT;
  
    FORALL idx IN ord_svc_sk_list.FIRST .. ord_svc_sk_list.LAST
    UPDATE ORD_SVC
    SET 
      REC_UPDATE_TMSTP = CURRENT_TIMESTAMP,
      ORD_SVC_END_DATE = TO_DATE(v_pt_discharged_date_str, 'MM/DD/YYYY HH24:MI:SS')
    WHERE ORD_SVC_SK = ord_svc_sk_list(idx);
    updated_row_count := SQL%ROWCOUNT;
    
    IF (ord_svc_select_row_count != updated_row_count) THEN
      RETURN -2;
    END IF;
    
    FORALL idx1 IN ord_sk_list.FIRST .. ord_sk_list.LAST
    UPDATE ORD
    SET 
      REC_UPDATE_TMSTP = CURRENT_TIMESTAMP,
      REC_EFF_TMSTP = TO_DATE(v_pt_discharged_date_str, 'MM/DD/YYYY HH24:MI:SS'), 
      ORD_END_TMSTP = TO_DATE(v_pt_discharged_date_str, 'MM/DD/YYYY HH24:MI:SS')
    WHERE ORD_SK = ord_sk_list(idx1);
    updated_row_count := SQL%ROWCOUNT;
  
    IF (ord_select_row_count != updated_row_count) THEN
      RETURN -2;
    END IF;
  
    RETURN updated_row_count;
    
  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
  
  END END_DATE_ORDER_FOR_DISCHGED_PT;

------------------------------------------------------------------------  
  FUNCTION END_DATE_POC_FOR_DISCHGED_PT(v_bsn_ent_id IN POC.BE_ID%TYPE, v_pt_id IN POC.PT_ID%TYPE, v_pt_discharged_date_str IN VARCHAR2) RETURN NUMBER
  AS
    poc_sk_list NUMBER_ARRAY;
    poc_select_row_count NUMBER;
    updated_row_count NUMBER;
  BEGIN
    SELECT POC_SK BULK COLLECT INTO poc_sk_list
    FROM POC
    WHERE
      BE_ID = v_bsn_ent_id
      AND PT_ID = v_pt_id
      AND POC_END_DATE >= TO_DATE(v_pt_discharged_date_str, 'MM/DD/YYYY HH24:MI:SS')
      AND (CURR_REC_IND=1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31')
    FOR UPDATE NOWAIT;
    poc_select_row_count := SQL%ROWCOUNT;
    
    FORALL idx IN poc_sk_list.FIRST .. poc_sk_list.LAST
    UPDATE POC
    SET 
      REC_UPDATE_TMSTP = CURRENT_TIMESTAMP,
      REC_EFF_TMSTP = TO_DATE(v_pt_discharged_date_str, 'MM/DD/YYYY HH24:MI:SS'), 
      POC_END_DATE = TO_DATE(v_pt_discharged_date_str, 'MM/DD/YYYY HH24:MI:SS')
    WHERE POC_SK = poc_sk_list(idx);
    updated_row_count := SQL%ROWCOUNT;
  
    IF (poc_select_row_count != updated_row_count) THEN
      RETURN -2;
    END IF;
  
    RETURN updated_row_count;
    
  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
  
  END END_DATE_POC_FOR_DISCHGED_PT;

END PKG_PATIENT_UTIL;
/
