CREATE OR REPLACE PACKAGE PKG_AM_UTIL IS

  FUNCTION DELETE_PAYER_SVC_LST(v_be_id IN PAYER_SVC_LST.BE_ID%TYPE, v_payer_id IN PAYER_SVC_LST.PAYER_ID%TYPE) RETURN NUMBER;

  FUNCTION DELETE_PAYER_SVC(v_be_id IN PAYER_SVC_LST.BE_ID%TYPE, v_payer_id IN PAYER_SVC_LST.PAYER_ID%TYPE, v_svc_name IN PAYER_SVC_LST.SVC_NAME%TYPE) RETURN NUMBER;

  FUNCTION DELETE_PAYER_LOB_LST(v_be_id IN PAYER_LOB_LST.BE_ID%TYPE, v_payer_id IN PAYER_LOB_LST.PAYER_ID%TYPE) RETURN NUMBER;

  FUNCTION DELETE_PAYER_LOB_LST(v_be_id IN PAYER_LOB_LST.BE_ID%TYPE, v_payer_id IN PAYER_LOB_LST.PAYER_ID%TYPE, v_be_lob_list IN BE_LOB_LST) RETURN NUMBER;

  FUNCTION DELETE_BE_STAFF_TRNG_CTGY_LST(v_be_id IN BE_STAFF_TRNG_CTGY_LST.BE_ID%TYPE, v_staff_trng_code IN BE_STAFF_TRNG_CTGY_LST.STAFF_TRNG_CODE%TYPE) RETURN NUMBER;

  FUNCTION REMOVE_STAFF_TRNG_TO_CTGY_LNK(v_be_staff_trng_lkup_sk IN BE_STAFF_TRNG_LKUP.BE_STAFF_TRNG_LKUP_SK%TYPE) RETURN NUMBER;

  FUNCTION DELETE_PAYER_RATE_TYP_LST(v_be_id IN PAYER_RATE_TYP_LST.BE_ID%TYPE, v_payer_id IN PAYER_RATE_TYP_LST.PAYER_ID%TYPE) RETURN NUMBER;

  FUNCTION DELETE_PAYER_RATE_TYP_LST(v_be_id IN PAYER_RATE_TYP_LST.BE_ID%TYPE, v_payer_id IN PAYER_RATE_TYP_LST.PAYER_ID%TYPE, v_rate_typ_name_arr IN RATE_TYP_NAME_ARRAY) RETURN NUMBER;

  FUNCTION DELETE_PAYER_MDFR_LKUP(v_be_id IN PAYER_MDFR_LKUP.BE_ID%TYPE, v_payer_id IN PAYER_MDFR_LKUP.PAYER_ID%TYPE) RETURN NUMBER;

  FUNCTION DELETE_PAYER_MDFR_LKUP(v_be_id IN PAYER_MDFR_LKUP.BE_ID%TYPE, v_payer_id IN PAYER_MDFR_LKUP.PAYER_ID%TYPE, v_mdfr_code_list IN MDFR_CODE_LST_T) RETURN NUMBER;

  FUNCTION REMOVE_BE_COMP_LKUP_FULLFILL(v_be_comp_rel_sk_lst IN BE_COMP_REL_SK_LST_T) RETURN NUMBER;

  FUNCTION DELETE_STAFF_TRNG_CTGY_SVC_LST(v_staff_trng_ctgy_svc_sk_lst IN SK_ARRAY) RETURN NUMBER;

  FUNCTION REMOVE_CTGY_TO_COMP_LNK(v_be_comp_ctgy_lkup_sk IN BE_COMP_CTGY_LKUP.BE_COMP_CTGY_LKUP_SK%TYPE) RETURN NUMBER;
  
  FUNCTION DELETE_PAYER_BILLING_CODE_LKUP(v_be_id IN PAYER_BILLING_CODE_LKUP.BE_ID%TYPE, v_payer_id IN PAYER_BILLING_CODE_LKUP.PAYER_ID%TYPE) RETURN NUMBER;

  FUNCTION DEL_PAYER_BILL_CODE_LST_ID_LST(v_be_id IN PAYER_BILLING_CODE_LKUP.BE_ID%TYPE, v_payer_id IN PAYER_BILLING_CODE_LKUP.PAYER_ID%TYPE, v_billing_code_list IN BILLING_CODE_LST) RETURN NUMBER;

  FUNCTION DEL_PAYER_BILLING_CODE_CONTR(v_be_id IN CONTR_BILLING_CODE_LST.BE_ID%TYPE, v_payer_id IN CONTR_BILLING_CODE_LST.PAYER_ID%TYPE, v_billing_code_updated_list IN STRING_ARRAY) RETURN NUMBER;

  FUNCTION DEL_PAYER_RATE_TYP_CONTR(v_be_id IN CONTR_RATE_TYP_LST.BE_ID%TYPE, v_payer_id IN CONTR_RATE_TYP_LST.PAYER_ID%TYPE, v_rate_typ_updated_list IN STRING_ARRAY) RETURN NUMBER;
  
  FUNCTION DEL_PAYER_MDFR_CONTR(v_be_id IN CONTR_MDFR_LST.BE_ID%TYPE, v_payer_id IN CONTR_MDFR_LST.PAYER_ID%TYPE, v_mdfr_code_updated_list IN STRING_ARRAY) RETURN NUMBER;
  
  FUNCTION DEL_PAYER_SERVICE_CONTR(v_be_id IN CONTR_SVC_LST.BE_ID%TYPE, v_payer_id IN CONTR_SVC_LST.PAYER_ID%TYPE, v_svc_updated_list IN STRING_ARRAY) RETURN NUMBER;
  
  FUNCTION DEL_PAYER_LOB_CONTR(v_be_id IN CONTR.BE_ID%TYPE, v_payer_id IN CONTR.PAYER_ID%TYPE, v_lob_updated_list IN STRING_ARRAY) RETURN NUMBER;
  
END PKG_AM_UTIL;
/

CREATE OR REPLACE PACKAGE BODY PKG_AM_UTIL IS

  FUNCTION DELETE_PAYER_SVC_LST(v_be_id IN PAYER_SVC_LST.BE_ID%TYPE, v_payer_id IN PAYER_SVC_LST.PAYER_ID%TYPE) RETURN NUMBER
  AS
  BEGIN

    UPDATE PAYER_SVC_LST
    SET
      REC_TERM_TMSTP = TO_DATE(SYSDATE, 'YYYY-MM-DD'),
      CURR_REC_IND = 0
    WHERE
      BE_ID = v_be_id
      AND PAYER_ID = v_payer_id;

    RETURN SQL%ROWCOUNT;

  END DELETE_PAYER_SVC_LST;

  ------------------------------------------------------------------------
  FUNCTION DELETE_PAYER_SVC(v_be_id IN PAYER_SVC_LST.BE_ID%TYPE, v_payer_id IN PAYER_SVC_LST.PAYER_ID%TYPE, v_svc_name IN PAYER_SVC_LST.SVC_NAME%TYPE) RETURN NUMBER
  AS
  BEGIN

    UPDATE PAYER_SVC_LST
    SET
      REC_TERM_TMSTP = TO_DATE(SYSDATE, 'YYYY-MM-DD'),
      CURR_REC_IND = 0
    WHERE
      BE_ID = v_be_id
      AND PAYER_ID = v_payer_id
      AND SVC_NAME = v_svc_name;

    RETURN SQL%ROWCOUNT;

  END DELETE_PAYER_SVC;

  ------------------------------------------------------------------------
  FUNCTION DELETE_PAYER_LOB_LST(v_be_id IN PAYER_LOB_LST.BE_ID%TYPE, v_payer_id IN PAYER_LOB_LST.PAYER_ID%TYPE) RETURN NUMBER
  AS
  BEGIN

    UPDATE PAYER_LOB_LST
    SET
      REC_TERM_TMSTP = TO_DATE(SYSDATE, 'YYYY-MM-DD'),
      CURR_REC_IND = 0
    WHERE
      BE_ID = v_be_id
      AND PAYER_ID = v_payer_id;

    RETURN SQL%ROWCOUNT;

  END DELETE_PAYER_LOB_LST;

  ------------------------------------------------------------------------
  FUNCTION DELETE_PAYER_LOB_LST(v_be_id IN PAYER_LOB_LST.BE_ID%TYPE, v_payer_id IN PAYER_LOB_LST.PAYER_ID%TYPE, v_be_lob_list IN BE_LOB_LST) RETURN NUMBER
  AS
    TYPE payer_lob_lst_sks_t IS TABLE OF PAYER_LOB_LST.PAYER_LOB_LST_SK%TYPE INDEX BY PLS_INTEGER;
    l_payer_lob_lst_sks payer_lob_lst_sks_t;

  BEGIN

    SELECT PAYER_LOB_LST_SK BULK COLLECT INTO l_payer_lob_lst_sks
    FROM PAYER_LOB_LST
    WHERE
      BE_ID = v_be_id
      AND PAYER_ID = v_payer_id
      AND BE_LOB IN (SELECT * FROM TABLE(v_be_lob_list))
    FOR UPDATE NOWAIT;

    FORALL idx IN l_payer_lob_lst_sks.FIRST .. l_payer_lob_lst_sks.LAST
      UPDATE PAYER_LOB_LST
      SET
        REC_TERM_TMSTP = TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD'),
        CURR_REC_IND = 0
      WHERE PAYER_LOB_LST_SK = l_payer_lob_lst_sks(idx);

    RETURN SQL%ROWCOUNT;

  END DELETE_PAYER_LOB_LST;

  ------------------------------------------------------------------------
  FUNCTION DELETE_BE_STAFF_TRNG_CTGY_LST(v_be_id IN BE_STAFF_TRNG_CTGY_LST.BE_ID%TYPE, v_staff_trng_code IN BE_STAFF_TRNG_CTGY_LST.STAFF_TRNG_CODE%TYPE) RETURN NUMBER
  AS
  BEGIN

    UPDATE BE_STAFF_TRNG_CTGY_LST
    SET
      REC_TERM_TMSTP = TO_DATE(SYSDATE, 'YYYY-MM-DD'),
      CURR_REC_IND = 0
    WHERE
      BE_ID = v_be_id
      AND STAFF_TRNG_CODE = v_staff_trng_code;

    RETURN SQL%ROWCOUNT;

  END DELETE_BE_STAFF_TRNG_CTGY_LST;

  ------------------------------------------------------------------------
  FUNCTION REMOVE_STAFF_TRNG_TO_CTGY_LNK(v_be_staff_trng_lkup_sk IN BE_STAFF_TRNG_LKUP.BE_STAFF_TRNG_LKUP_SK%TYPE) RETURN NUMBER
  AS
  BEGIN

    UPDATE BE_STAFF_TRNG_CTGY_LST
    SET
      REC_TERM_TMSTP = CURRENT_TIMESTAMP,
      CURR_REC_IND = 0
    WHERE
      (BE_ID, STAFF_TRNG_CODE) = (SELECT BE_ID, STAFF_TRNG_CODE FROM BE_STAFF_TRNG_LKUP WHERE BE_STAFF_TRNG_LKUP_SK = v_be_staff_trng_lkup_sk)
      AND (TO_CHAR(BE_STAFF_TRNG_CTGY_LST.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND BE_STAFF_TRNG_CTGY_LST.CURR_REC_IND = '1');

    RETURN SQL%ROWCOUNT;

  END REMOVE_STAFF_TRNG_TO_CTGY_LNK;

  ------------------------------------------------------------------------
  FUNCTION DELETE_PAYER_RATE_TYP_LST(v_be_id IN PAYER_RATE_TYP_LST.BE_ID%TYPE, v_payer_id IN PAYER_RATE_TYP_LST.PAYER_ID%TYPE) RETURN NUMBER
  AS
  BEGIN

    UPDATE PAYER_RATE_TYP_LST
    SET
      REC_TERM_TMSTP = TO_DATE(SYSDATE, 'YYYY-MM-DD'),
      CURR_REC_IND = 0
    WHERE
      BE_ID = v_be_id
      AND PAYER_ID = v_payer_id;

    RETURN SQL%ROWCOUNT;

  END DELETE_PAYER_RATE_TYP_LST;

  ------------------------------------------------------------------------
  FUNCTION DELETE_PAYER_RATE_TYP_LST(v_be_id IN PAYER_RATE_TYP_LST.BE_ID%TYPE, v_payer_id IN PAYER_RATE_TYP_LST.PAYER_ID%TYPE, v_rate_typ_name_arr IN RATE_TYP_NAME_ARRAY) RETURN NUMBER
  AS
    TYPE payer_rate_typ_lst_sks_t IS TABLE OF PAYER_RATE_TYP_LST.PAYER_RATE_TYP_LST_SK%TYPE INDEX BY PLS_INTEGER;
    l_payer_rate_typ_lst_sks payer_rate_typ_lst_sks_t;

  BEGIN

    SELECT PAYER_RATE_TYP_LST_SK BULK COLLECT INTO l_payer_rate_typ_lst_sks
    FROM PAYER_RATE_TYP_LST
    WHERE
      BE_ID = v_be_id
      AND PAYER_ID = v_payer_id
      AND RATE_TYP_NAME IN (SELECT * FROM TABLE(v_rate_typ_name_arr))
    FOR UPDATE NOWAIT;

    FORALL idx IN l_payer_rate_typ_lst_sks.FIRST .. l_payer_rate_typ_lst_sks.LAST
      UPDATE PAYER_RATE_TYP_LST
      SET
        REC_TERM_TMSTP = CURRENT_DATE,
        CURR_REC_IND = 0
      WHERE PAYER_RATE_TYP_LST_SK = l_payer_rate_typ_lst_sks(idx);

    RETURN SQL%ROWCOUNT;

  END DELETE_PAYER_RATE_TYP_LST;

  ------------------------------------------------------------------------
  FUNCTION DELETE_PAYER_MDFR_LKUP(v_be_id IN PAYER_MDFR_LKUP.BE_ID%TYPE, v_payer_id IN PAYER_MDFR_LKUP.PAYER_ID%TYPE) RETURN NUMBER
  AS
  BEGIN

    UPDATE PAYER_MDFR_LKUP
    SET
      REC_TERM_TMSTP = TO_DATE(SYSDATE, 'YYYY-MM-DD'),
      CURR_REC_IND = 0
    WHERE
      BE_ID = v_be_id
      AND PAYER_ID = v_payer_id;

    RETURN SQL%ROWCOUNT;

  END DELETE_PAYER_MDFR_LKUP;

  ------------------------------------------------------------------------
  FUNCTION DELETE_PAYER_MDFR_LKUP(v_be_id IN PAYER_MDFR_LKUP.BE_ID%TYPE, v_payer_id IN PAYER_MDFR_LKUP.PAYER_ID%TYPE, v_mdfr_code_list IN MDFR_CODE_LST_T) RETURN NUMBER
  AS
    TYPE payer_mdfr_lkup_sks_t IS TABLE OF PAYER_MDFR_LKUP.PAYER_MDFR_LKUP_SK%TYPE INDEX BY PLS_INTEGER;
    l_payer_mdfr_lkup_sks payer_mdfr_lkup_sks_t;

  BEGIN

    SELECT PAYER_MDFR_LKUP_SK BULK COLLECT INTO l_payer_mdfr_lkup_sks
    FROM PAYER_MDFR_LKUP
    WHERE
      BE_ID = v_be_id
      AND PAYER_ID = v_payer_id
      AND MDFR_CODE IN (SELECT * FROM TABLE(v_mdfr_code_list))
    FOR UPDATE NOWAIT;

    FORALL idx IN l_payer_mdfr_lkup_sks.FIRST .. l_payer_mdfr_lkup_sks.LAST
      UPDATE PAYER_MDFR_LKUP
      SET
        REC_TERM_TMSTP = TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD'),
        CURR_REC_IND = 0
      WHERE PAYER_MDFR_LKUP_SK = l_payer_mdfr_LKUP_sks(idx);

    RETURN SQL%ROWCOUNT;

  END DELETE_PAYER_MDFR_LKUP;

  ------------------------------------------------------------------------
  FUNCTION REMOVE_BE_COMP_LKUP_FULLFILL(v_be_comp_rel_sk_lst IN BE_COMP_REL_SK_LST_T) RETURN NUMBER
  AS
    TYPE be_comp_rel_det_sks_t IS TABLE OF BE_COMP_REL_DET.BE_COMP_REL_DET_SK%TYPE INDEX BY PLS_INTEGER;
    l_be_comp_rel_det_sks be_comp_rel_det_sks_t;

  BEGIN

    SELECT BE_COMP_REL_DET_SK BULK COLLECT INTO l_be_comp_rel_det_sks
    FROM BE_COMP_REL_DET
    WHERE
      BE_COMP_REL_SK IN (SELECT * FROM TABLE(v_be_comp_rel_sk_lst))
    FOR UPDATE NOWAIT;

    FORALL idx IN l_be_comp_rel_det_sks.FIRST .. l_be_comp_rel_det_sks.LAST
      UPDATE BE_COMP_REL_DET
      SET
        COMP_MAND_COMPL_THRESHOLD = NULL
      WHERE BE_COMP_REL_DET_SK = l_be_comp_rel_det_sks(idx);

    RETURN SQL%ROWCOUNT;

  END REMOVE_BE_COMP_LKUP_FULLFILL;

  ------------------------------------------------------------------------
  FUNCTION DELETE_STAFF_TRNG_CTGY_SVC_LST(v_staff_trng_ctgy_svc_sk_lst IN SK_ARRAY) RETURN NUMBER
  AS
    TYPE staff_trng_ctgy_svc_sks_t IS TABLE OF STAFF_TRNG_CTGY_SVC.STAFF_TRNG_CTGY_SVC_SK%TYPE INDEX BY PLS_INTEGER;
    l_staff_trng_ctgy_svc_sks staff_trng_ctgy_svc_sks_t;

  BEGIN

    SELECT STAFF_TRNG_CTGY_SVC_SK BULK COLLECT INTO l_staff_trng_ctgy_svc_sks
    FROM STAFF_TRNG_CTGY_SVC
    WHERE
      STAFF_TRNG_CTGY_SVC_SK IN (SELECT * FROM TABLE(v_staff_trng_ctgy_svc_sk_lst))
    FOR UPDATE NOWAIT;

    FORALL idx IN l_staff_trng_ctgy_svc_sks.FIRST .. l_staff_trng_ctgy_svc_sks.LAST
      UPDATE STAFF_TRNG_CTGY_SVC
      SET
        REC_TERM_TMSTP = CURRENT_DATE,
        CURR_REC_IND = 0
      WHERE STAFF_TRNG_CTGY_SVC_SK = l_staff_trng_ctgy_svc_sks(idx);

    RETURN SQL%ROWCOUNT;

  END DELETE_STAFF_TRNG_CTGY_SVC_LST;

  ------------------------------------------------------------------------
  FUNCTION REMOVE_CTGY_TO_COMP_LNK(v_be_comp_ctgy_lkup_sk IN BE_COMP_CTGY_LKUP.BE_COMP_CTGY_LKUP_SK%TYPE) RETURN NUMBER
  AS
  BEGIN

    UPDATE BE_COMP_LKUP
    SET COMP_CTGY_CODE = NULL
    WHERE COMP_CTGY_CODE = (SELECT COMP_CTGY_CODE
                            FROM BE_COMP_CTGY_LKUP
                            WHERE BE_COMP_CTGY_LKUP_SK = v_be_comp_ctgy_lkup_sk);
  
    RETURN SQL%ROWCOUNT;

  END REMOVE_CTGY_TO_COMP_LNK;
  
  ------------------------------------------------------------------------
  FUNCTION DELETE_PAYER_BILLING_CODE_LKUP(v_be_id IN PAYER_BILLING_CODE_LKUP.BE_ID%TYPE, v_payer_id IN PAYER_BILLING_CODE_LKUP.PAYER_ID%TYPE) RETURN NUMBER
  AS
  BEGIN

    UPDATE PAYER_BILLING_CODE_LKUP
    SET
      REC_TERM_TMSTP = TO_DATE(SYSDATE, 'YYYY-MM-DD'),
      CURR_REC_IND = 0
    WHERE
      BE_ID = v_be_id
      AND PAYER_ID = v_payer_id;

    RETURN SQL%ROWCOUNT;

  END DELETE_PAYER_BILLING_CODE_LKUP;
  
  ------------------------------------------------------------------------
  FUNCTION DEL_PAYER_BILL_CODE_LST_ID_LST(v_be_id IN PAYER_BILLING_CODE_LKUP.BE_ID%TYPE, v_payer_id IN PAYER_BILLING_CODE_LKUP.PAYER_ID%TYPE, v_billing_code_list IN BILLING_CODE_LST) RETURN NUMBER
  AS
  BEGIN

    UPDATE PAYER_BILLING_CODE_LKUP
    SET
      REC_TERM_TMSTP = TO_DATE(SYSDATE, 'YYYY-MM-DD'),
      CURR_REC_IND = 0
    WHERE
      BE_ID = v_be_id
      AND PAYER_ID = v_payer_id
      AND BILLING_CODE IN (SELECT * FROM TABLE(v_billing_code_list));

    RETURN SQL%ROWCOUNT;

  END DEL_PAYER_BILL_CODE_LST_ID_LST;
  
  ------------------------------------------------------------------------
  FUNCTION DEL_PAYER_BILLING_CODE_CONTR(v_be_id IN CONTR_BILLING_CODE_LST.BE_ID%TYPE, v_payer_id IN CONTR_BILLING_CODE_LST.PAYER_ID%TYPE, v_billing_code_updated_list IN STRING_ARRAY) RETURN NUMBER
  AS
    TYPE payer_billing_code_contr_sks_t IS TABLE OF CONTR_BILLING_CODE_LST.CONTR_BILLING_CODE_LST_SK%TYPE INDEX BY PLS_INTEGER;
    l_payer_billing_code_contr_sks payer_billing_code_contr_sks_t;
	TYPE billing_rate_matrix_sks_t IS TABLE OF BILLING_RATE_MATRIX.BILLING_RATE_MATRIX_SK%TYPE INDEX BY PLS_INTEGER;
    l_billing_rate_matrix_sks billing_rate_matrix_sks_t;
  BEGIN

    SELECT T1.CONTR_BILLING_CODE_LST_SK BULK COLLECT INTO l_payer_billing_code_contr_sks
    FROM CONTR_BILLING_CODE_LST T1
        JOIN (SELECT * FROM CONTR WHERE CONTR_ACTIVE_IND = 0 AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 
            ON T1.BE_ID = T2.BE_ID AND T1.PAYER_ID = T2.PAYER_ID AND T1.CONTR_ID = T2.CONTR_ID
    WHERE
      T1.BE_ID = v_be_id
      AND T1.PAYER_ID = v_payer_id
      AND T1.BILLING_CODE NOT IN (SELECT * FROM TABLE(v_billing_code_updated_list))
    FOR UPDATE NOWAIT;
    
    FORALL idx IN l_payer_billing_code_contr_sks.FIRST .. l_payer_billing_code_contr_sks.LAST
      UPDATE CONTR_BILLING_CODE_LST
      SET
        REC_TERM_TMSTP = CURRENT_DATE,
        CURR_REC_IND = 0
    WHERE CONTR_BILLING_CODE_LST_SK = l_payer_billing_code_contr_sks(idx);

	
	SELECT T1.BILLING_RATE_MATRIX_SK BULK COLLECT INTO l_billing_rate_matrix_sks
    FROM BILLING_RATE_MATRIX T1
        JOIN (SELECT * FROM CONTR WHERE CONTR_ACTIVE_IND = 0 AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 
            ON T1.BE_ID = T2.BE_ID AND T1.PAYER_ID = T2.PAYER_ID AND T1.CONTR_ID = T2.CONTR_ID
    WHERE
      T1.BE_ID = v_be_id
      AND T1.PAYER_ID = v_payer_id
      AND T1.BILLING_CODE NOT IN (SELECT * FROM TABLE(v_billing_code_updated_list))
    FOR UPDATE NOWAIT;
    
    FORALL idx IN l_billing_rate_matrix_sks.FIRST .. l_billing_rate_matrix_sks.LAST
      UPDATE BILLING_RATE_MATRIX
      SET
        REC_TERM_TMSTP = CURRENT_DATE,
        CURR_REC_IND = 0
    WHERE BILLING_RATE_MATRIX_SK = l_billing_rate_matrix_sks(idx);
	
    RETURN SQL%ROWCOUNT;
    
  END DEL_PAYER_BILLING_CODE_CONTR;
  
  ------------------------------------------------------------------------
  FUNCTION DEL_PAYER_RATE_TYP_CONTR(v_be_id IN CONTR_RATE_TYP_LST.BE_ID%TYPE, v_payer_id IN CONTR_RATE_TYP_LST.PAYER_ID%TYPE, v_rate_typ_updated_list IN STRING_ARRAY) RETURN NUMBER
  AS
    TYPE payer_rate_typ_contr_sks_t IS TABLE OF CONTR_RATE_TYP_LST.CONTR_RATE_TYP_LST_SK%TYPE INDEX BY PLS_INTEGER;
    l_payer_rate_typ_contr_sks payer_rate_typ_contr_sks_t;
	TYPE billing_rate_matrix_sks_t IS TABLE OF BILLING_RATE_MATRIX.BILLING_RATE_MATRIX_SK%TYPE INDEX BY PLS_INTEGER;
    l_billing_rate_matrix_sks billing_rate_matrix_sks_t;
	TYPE pr_rate_matrix_sks_t IS TABLE OF PR_RATE_MATRIX.PR_RATE_MATRIX_SK%TYPE INDEX BY PLS_INTEGER;
    l_pr_rate_matrix_sks pr_rate_matrix_sks_t;
  BEGIN
    SELECT T1.CONTR_RATE_TYP_LST_SK BULK COLLECT INTO l_payer_rate_typ_contr_sks
    FROM CONTR_RATE_TYP_LST T1
        JOIN (SELECT * FROM CONTR WHERE CONTR_ACTIVE_IND = 0 AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 
            ON T1.BE_ID = T2.BE_ID AND T1.PAYER_ID = T2.PAYER_ID AND T1.CONTR_ID = T2.CONTR_ID
    WHERE
      T1.BE_ID = v_be_id
      AND T1.PAYER_ID = v_payer_id
      AND T1.RATE_TYP_NAME NOT IN (SELECT * FROM TABLE(v_rate_typ_updated_list))
    FOR UPDATE NOWAIT;
    
    FORALL idx IN l_payer_rate_typ_contr_sks.FIRST .. l_payer_rate_typ_contr_sks.LAST
      UPDATE CONTR_RATE_TYP_LST
      SET
        REC_TERM_TMSTP = CURRENT_DATE,
        CURR_REC_IND = 0
    WHERE CONTR_RATE_TYP_LST_SK = l_payer_rate_typ_contr_sks(idx);
	
	
	SELECT T1.BILLING_RATE_MATRIX_SK BULK COLLECT INTO l_billing_rate_matrix_sks
    FROM BILLING_RATE_MATRIX T1
        JOIN (SELECT * FROM CONTR WHERE CONTR_ACTIVE_IND = 0 AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 
            ON T1.BE_ID = T2.BE_ID AND T1.PAYER_ID = T2.PAYER_ID AND T1.CONTR_ID = T2.CONTR_ID
    WHERE
      T1.BE_ID = v_be_id
      AND T1.PAYER_ID = v_payer_id
      AND T1.RATE_TYP_NAME NOT IN (SELECT * FROM TABLE(v_rate_typ_updated_list))
    FOR UPDATE NOWAIT;
    
    FORALL idx IN l_billing_rate_matrix_sks.FIRST .. l_billing_rate_matrix_sks.LAST
      UPDATE BILLING_RATE_MATRIX
      SET
        REC_TERM_TMSTP = CURRENT_DATE,
        CURR_REC_IND = 0
    WHERE BILLING_RATE_MATRIX_SK = l_billing_rate_matrix_sks(idx);
	
		
	SELECT T1.PR_RATE_MATRIX_SK BULK COLLECT INTO l_pr_rate_matrix_sks
    FROM PR_RATE_MATRIX T1
        JOIN (SELECT * FROM CONTR WHERE CONTR_ACTIVE_IND = 0 AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 
            ON T1.BE_ID = T2.BE_ID AND T1.PAYER_ID = T2.PAYER_ID AND T1.CONTR_ID = T2.CONTR_ID
    WHERE
      T1.BE_ID = v_be_id
      AND T1.PAYER_ID = v_payer_id
      AND T1.RATE_TYP_NAME NOT IN (SELECT * FROM TABLE(v_rate_typ_updated_list))
    FOR UPDATE NOWAIT;
    
    FORALL idx IN l_pr_rate_matrix_sks.FIRST .. l_pr_rate_matrix_sks.LAST
      UPDATE PR_RATE_MATRIX
      SET
        REC_TERM_TMSTP = CURRENT_DATE,
        CURR_REC_IND = 0
    WHERE PR_RATE_MATRIX_SK = l_pr_rate_matrix_sks(idx);

    RETURN SQL%ROWCOUNT;
  END DEL_PAYER_RATE_TYP_CONTR;
  
  ------------------------------------------------------------------------
  FUNCTION DEL_PAYER_MDFR_CONTR(v_be_id IN CONTR_MDFR_LST.BE_ID%TYPE, v_payer_id IN CONTR_MDFR_LST.PAYER_ID%TYPE, v_mdfr_code_updated_list IN STRING_ARRAY) RETURN NUMBER
  AS
    TYPE payer_mdfr_contr_sks_t IS TABLE OF CONTR_MDFR_LST.CONTR_MDFR_LST_SK%TYPE INDEX BY PLS_INTEGER;
    l_payer_mdfr_contr_sks payer_mdfr_contr_sks_t;
	TYPE billing_rate_matrix_sks_t IS TABLE OF BILLING_RATE_MATRIX.BILLING_RATE_MATRIX_SK%TYPE INDEX BY PLS_INTEGER;
    l_billing_rate_matrix_sks billing_rate_matrix_sks_t;
  BEGIN
    SELECT T1.CONTR_MDFR_LST_SK BULK COLLECT INTO l_payer_mdfr_contr_sks
    FROM CONTR_MDFR_LST T1
        JOIN (SELECT * FROM CONTR WHERE CONTR_ACTIVE_IND = 0 AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 
            ON T1.BE_ID = T2.BE_ID AND T1.PAYER_ID = T2.PAYER_ID AND T1.CONTR_ID = T2.CONTR_ID
    WHERE
      T1.BE_ID = v_be_id
      AND T1.PAYER_ID = v_payer_id
      AND T1.MDFR_CODE NOT IN (SELECT * FROM TABLE(v_mdfr_code_updated_list))
    FOR UPDATE NOWAIT;
    
    FORALL idx IN l_payer_mdfr_contr_sks.FIRST .. l_payer_mdfr_contr_sks.LAST
      UPDATE CONTR_MDFR_LST
      SET
        REC_TERM_TMSTP = CURRENT_DATE,
        CURR_REC_IND = 0
    WHERE CONTR_MDFR_LST_SK = l_payer_mdfr_contr_sks(idx);
	
	
	SELECT T1.BILLING_RATE_MATRIX_SK BULK COLLECT INTO l_billing_rate_matrix_sks
    FROM BILLING_RATE_MATRIX T1
        JOIN (SELECT * FROM CONTR WHERE CONTR_ACTIVE_IND = 0 AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 
            ON T1.BE_ID = T2.BE_ID AND T1.PAYER_ID = T2.PAYER_ID AND T1.CONTR_ID = T2.CONTR_ID
    WHERE
      T1.BE_ID = v_be_id
      AND T1.PAYER_ID = v_payer_id
      AND ((T1.MDFR_1_CODE IS NOT NULL AND T1.MDFR_1_CODE NOT IN (SELECT * FROM TABLE(v_mdfr_code_updated_list)))
		  OR (T1.MDFR_2_CODE IS NOT NULL AND T1.MDFR_2_CODE NOT IN (SELECT * FROM TABLE(v_mdfr_code_updated_list)))
		  OR (T1.MDFR_3_CODE IS NOT NULL AND T1.MDFR_3_CODE NOT IN (SELECT * FROM TABLE(v_mdfr_code_updated_list)))
		  OR (T1.MDFR_4_CODE IS NOT NULL AND T1.MDFR_4_CODE NOT IN (SELECT * FROM TABLE(v_mdfr_code_updated_list))))
    FOR UPDATE NOWAIT;
    
    FORALL idx IN l_billing_rate_matrix_sks.FIRST .. l_billing_rate_matrix_sks.LAST
      UPDATE BILLING_RATE_MATRIX
      SET
        REC_TERM_TMSTP = CURRENT_DATE,
        CURR_REC_IND = 0
    WHERE BILLING_RATE_MATRIX_SK = l_billing_rate_matrix_sks(idx);

    RETURN SQL%ROWCOUNT;
  END DEL_PAYER_MDFR_CONTR;
  
  ------------------------------------------------------------------------
  FUNCTION DEL_PAYER_SERVICE_CONTR(v_be_id IN CONTR_SVC_LST.BE_ID%TYPE, v_payer_id IN CONTR_SVC_LST.PAYER_ID%TYPE, v_svc_updated_list IN STRING_ARRAY) RETURN NUMBER
  AS
    TYPE payer_svc_contr_sks_t IS TABLE OF CONTR_SVC_LST.CONTR_SVC_LST_SK%TYPE INDEX BY PLS_INTEGER;
    l_payer_svc_contr_sks payer_svc_contr_sks_t;
		TYPE billing_rate_matrix_sks_t IS TABLE OF BILLING_RATE_MATRIX.BILLING_RATE_MATRIX_SK%TYPE INDEX BY PLS_INTEGER;
    l_billing_rate_matrix_sks billing_rate_matrix_sks_t;
	TYPE pr_rate_matrix_sks_t IS TABLE OF PR_RATE_MATRIX.PR_RATE_MATRIX_SK%TYPE INDEX BY PLS_INTEGER;
    l_pr_rate_matrix_sks pr_rate_matrix_sks_t;
	TYPE contr_task_sks_t IS TABLE OF CONTR_TASK.CONTR_TASK_SK%TYPE INDEX BY PLS_INTEGER;
    l_contr_task_sks contr_task_sks_t;
  BEGIN
    SELECT T1.CONTR_SVC_LST_SK BULK COLLECT INTO l_payer_svc_contr_sks
    FROM CONTR_SVC_LST T1
        JOIN (SELECT * FROM CONTR WHERE CONTR_ACTIVE_IND = 0 AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 
            ON T1.BE_ID = T2.BE_ID AND T1.PAYER_ID = T2.PAYER_ID AND T1.CONTR_ID = T2.CONTR_ID
    WHERE
      T1.BE_ID = v_be_id
      AND T1.PAYER_ID = v_payer_id
      AND T1.SVC_NAME NOT IN (SELECT * FROM TABLE(v_svc_updated_list))
    FOR UPDATE NOWAIT;
    
    FORALL idx IN l_payer_svc_contr_sks.FIRST .. l_payer_svc_contr_sks.LAST
      UPDATE CONTR_SVC_LST
      SET
        REC_TERM_TMSTP = CURRENT_DATE,
        CURR_REC_IND = 0
    WHERE CONTR_SVC_LST_SK = l_payer_svc_contr_sks(idx);
	
	
	SELECT T1.BILLING_RATE_MATRIX_SK BULK COLLECT INTO l_billing_rate_matrix_sks
    FROM BILLING_RATE_MATRIX T1
        JOIN (SELECT * FROM CONTR WHERE CONTR_ACTIVE_IND = 0 AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 
            ON T1.BE_ID = T2.BE_ID AND T1.PAYER_ID = T2.PAYER_ID AND T1.CONTR_ID = T2.CONTR_ID
    WHERE
      T1.BE_ID = v_be_id
      AND T1.PAYER_ID = v_payer_id
      AND T1.SVC_NAME NOT IN (SELECT * FROM TABLE(v_svc_updated_list))
    FOR UPDATE NOWAIT;
    
    FORALL idx IN l_billing_rate_matrix_sks.FIRST .. l_billing_rate_matrix_sks.LAST
      UPDATE BILLING_RATE_MATRIX
      SET
        REC_TERM_TMSTP = CURRENT_DATE,
        CURR_REC_IND = 0
    WHERE BILLING_RATE_MATRIX_SK = l_billing_rate_matrix_sks(idx);
	
		
	SELECT T1.PR_RATE_MATRIX_SK BULK COLLECT INTO l_pr_rate_matrix_sks
    FROM PR_RATE_MATRIX T1
        JOIN (SELECT * FROM CONTR WHERE CONTR_ACTIVE_IND = 0 AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 
            ON T1.BE_ID = T2.BE_ID AND T1.PAYER_ID = T2.PAYER_ID AND T1.CONTR_ID = T2.CONTR_ID
    WHERE
      T1.BE_ID = v_be_id
      AND T1.PAYER_ID = v_payer_id
      AND T1.SVC_NAME NOT IN (SELECT * FROM TABLE(v_svc_updated_list))
    FOR UPDATE NOWAIT;
    
    FORALL idx IN l_pr_rate_matrix_sks.FIRST .. l_pr_rate_matrix_sks.LAST
      UPDATE PR_RATE_MATRIX
      SET
        REC_TERM_TMSTP = CURRENT_DATE,
        CURR_REC_IND = 0
    WHERE PR_RATE_MATRIX_SK = l_pr_rate_matrix_sks(idx);
	
	
	SELECT T1.CONTR_TASK_SK BULK COLLECT INTO l_contr_task_sks
    FROM CONTR_TASK T1
        JOIN (SELECT * FROM CONTR WHERE CONTR_ACTIVE_IND = 0 AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 
            ON T1.PAYER_ID = T2.PAYER_ID AND T1.CONTR_ID = T2.CONTR_ID
    WHERE
      T1.PAYER_ID = v_payer_id
      AND T1.SVC_NAME NOT IN (SELECT * FROM TABLE(v_svc_updated_list))
    FOR UPDATE NOWAIT;
    
    FORALL idx IN l_contr_task_sks.FIRST .. l_contr_task_sks.LAST
      UPDATE CONTR_TASK
      SET
        REC_TERM_TMSTP = CURRENT_DATE,
        CURR_REC_IND = 0
    WHERE CONTR_TASK_SK = l_contr_task_sks(idx);
	

    RETURN SQL%ROWCOUNT;
  END DEL_PAYER_SERVICE_CONTR;
  
  ------------------------------------------------------------------------
  FUNCTION DEL_PAYER_LOB_CONTR(v_be_id IN CONTR.BE_ID%TYPE, v_payer_id IN CONTR.PAYER_ID%TYPE, v_lob_updated_list IN STRING_ARRAY) RETURN NUMBER
  AS
    TYPE payer_lob_contr_sks_t IS TABLE OF CONTR.CONTR_SK%TYPE INDEX BY PLS_INTEGER;
    l_payer_lob_contr_sks payer_lob_contr_sks_t;
  BEGIN
    SELECT T1.CONTR_SK BULK COLLECT INTO l_payer_lob_contr_sks
    FROM CONTR T1
    WHERE
      T1.BE_ID = v_be_id
      AND T1.PAYER_ID = v_payer_id
      AND T1.BE_LOB_ID NOT IN (SELECT * FROM TABLE(v_lob_updated_list))
      AND T1.CONTR_ACTIVE_IND = 0
    FOR UPDATE NOWAIT;
    
    FORALL idx IN l_payer_lob_contr_sks.FIRST .. l_payer_lob_contr_sks.LAST
      UPDATE CONTR
      SET
        REC_TERM_TMSTP = CURRENT_DATE,
        CURR_REC_IND = 0
    WHERE CONTR_SK = l_payer_lob_contr_sks(idx);

    RETURN SQL%ROWCOUNT;
  END DEL_PAYER_LOB_CONTR;
  
END PKG_AM_UTIL;
/
