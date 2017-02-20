CREATE OR REPLACE PACKAGE PKG_APP_UTIL IS
  FUNCTION UPDATE_USR_SETTING_BY_GUID_KEY(v_value VARCHAR2, v_user_guid VARCHAR2, v_key VARCHAR2) RETURN NUMBER;
  
  FUNCTION DELETE_USR_SETTING_BY_GUID(v_user_guid VARCHAR2) RETURN NUMBER;
  
  FUNCTION UPDATE_CONF_LAST_EVV_EXPORT(v_last_evv_export_str IN APP_TENANT_KEY_CONF.TENANT_KEY_CONF_VAL%TYPE, v_be_id IN APP_TENANT_BE_XWALK.BE_ID%TYPE) RETURN NUMBER;
    
  FUNCTION UPDATE_BE_EXCP_LIST_SETTING(v_be_excp_id APP_TENANT_KEY_CONF.TENANT_KEY_CONF_VAL%TYPE, v_be_excp_setting APP_TENANT_KEY_CONF.TENANT_KEY_CONF_VAL%TYPE, v_be_id IN APP_TENANT_BE_XWALK.BE_ID%TYPE) RETURN NUMBER;
  
  FUNCTION GET_BE_EXCP_LIST_SETTING(v_be_excp_id APP_TENANT_KEY_CONF.TENANT_KEY_CONF_VAL%TYPE, v_be_id IN APP_TENANT_BE_XWALK.BE_ID%TYPE) RETURN VARCHAR2;
  
  FUNCTION DELETE_BE_EXCP_LIST_SETTING(v_be_excp_id APP_TENANT_KEY_CONF.TENANT_KEY_CONF_VAL%TYPE, v_be_id IN APP_TENANT_BE_XWALK.BE_ID%TYPE) RETURN NUMBER;
  
  FUNCTION GET_CONTR_EXCP_LST_SETTINGS(v_be_id VARCHAR2, v_payer_id VARCHAR2, v_contr_id VARCHAR2, v_excp_id VARCHAR2) RETURN VARCHAR2;
  
  FUNCTION UPDATE_CONTR_EXCP_LST_SETTINGS(v_be_id VARCHAR2, v_payer_id VARCHAR2, v_contr_id VARCHAR2, v_excp_id VARCHAR2, v_settings VARCHAR2) RETURN NUMBER;
  
  FUNCTION DELETE_CONTR_EXCP_LST_SETTINGS(v_be_id VARCHAR2, v_payer_id VARCHAR2, v_contr_id VARCHAR2, v_excp_id VARCHAR2) RETURN NUMBER;
  
  FUNCTION INS_UPD_BE_RNDING_RULE_SETTING(v_be_id VARCHAR2, v_rnding_rule_qlfr VARCHAR2, v_rnding_rule_id VARCHAR2, v_rnding_rule_setting VARCHAR2) RETURN NUMBER;
  
  FUNCTION GET_BE_RNDING_RULE_SETTING(v_be_id VARCHAR2, v_rnding_rule_qlfr VARCHAR2, v_rnding_rule_id VARCHAR2) RETURN VARCHAR2;
  
  FUNCTION DELETE_BE_RNDING_RULE_SETTING(v_be_id VARCHAR2, v_rnding_rule_qlfr VARCHAR2, v_rnding_rule_id VARCHAR2) RETURN NUMBER;
  
  FUNCTION INS_UPD_CONTR_RNDG_RULE_STNG(v_be_id VARCHAR2, v_payer_id VARCHAR2, v_contr_id VARCHAR2, v_rnding_rule_qlfr VARCHAR2, v_rnding_rule_id VARCHAR2, v_rnding_rule_setting VARCHAR2) RETURN NUMBER;
  
  FUNCTION GET_CONTR_RNDG_RULE_STNG(v_be_id VARCHAR2, v_payer_id VARCHAR2, v_contr_id VARCHAR2, v_rnding_rule_qlfr VARCHAR2, v_rnding_rule_id VARCHAR2) RETURN VARCHAR2;
  
  FUNCTION DELETE_CONTR_RNDG_RULE_STNG(v_be_id VARCHAR2, v_payer_id VARCHAR2, v_contr_id VARCHAR2, v_rnding_rule_qlfr VARCHAR2, v_rnding_rule_id VARCHAR2) RETURN NUMBER;
END PKG_APP_UTIL;
/

CREATE OR REPLACE PACKAGE BODY PKG_APP_UTIL IS
  FUNCTION DELETE_USR_SETTING_BY_GUID(v_user_guid VARCHAR2) RETURN NUMBER
  AS
  BEGIN
    DELETE FROM APP_USER_SETTING
    WHERE USER_GUID = v_user_guid;
    
    RETURN SQL%ROWCOUNT; 
    
  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
      
  END DELETE_USR_SETTING_BY_GUID;
  
------------------------------------------------------------------------
  FUNCTION UPDATE_USR_SETTING_BY_GUID_KEY(v_value VARCHAR2, v_user_guid VARCHAR2, v_key VARCHAR2) RETURN NUMBER
  AS
  BEGIN
    UPDATE APP_USER_SETTING
    SET VAL = v_value
    WHERE 
      USER_GUID = v_user_guid
      AND KEY = v_key;
  
    RETURN SQL%ROWCOUNT; 
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
      
  END UPDATE_USR_SETTING_BY_GUID_KEY;
  
  ------------------------------------------------------------------------
  FUNCTION UPDATE_CONF_LAST_EVV_EXPORT(v_last_evv_export_str IN APP_TENANT_KEY_CONF.TENANT_KEY_CONF_VAL%TYPE, v_be_id IN APP_TENANT_BE_XWALK.BE_ID%TYPE) RETURN NUMBER
  AS
  BEGIN

    UPDATE APP_TENANT_KEY_CONF
    SET TENANT_KEY_CONF_VAL = v_last_evv_export_str
    WHERE
      KEY_NAME = 'MDW_LAST_EVV_EXPORT'
      AND APP_TENANT_SK IN (SELECT APP_TENANT_SK FROM APP_TENANT_BE_XWALK WHERE BE_ID = v_be_id);

    RETURN SQL%ROWCOUNT; 

  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;

  END UPDATE_CONF_LAST_EVV_EXPORT;
 
  ------------------------------------------------------------------------
  FUNCTION UPDATE_BE_EXCP_LIST_SETTING(v_be_excp_id APP_TENANT_KEY_CONF.TENANT_KEY_CONF_VAL%TYPE, v_be_excp_setting APP_TENANT_KEY_CONF.TENANT_KEY_CONF_VAL%TYPE, v_be_id IN APP_TENANT_BE_XWALK.BE_ID%TYPE) RETURN NUMBER
  AS
    v_app_tenant_sk NUMBER;
    v_be_excp_conf_sk NUMBER;
    v_be_excp_conf_id_sk NUMBER;
    v_be_excp_setting_sk NUMBER;
    v_current_date DATE;
  
  BEGIN
  
    SELECT CAST(SYS_EXTRACT_UTC(SYSTIMESTAMP) AS DATE) INTO v_current_date FROM dual;
    
    SELECT APP_TENANT_SK INTO v_app_tenant_sk FROM APP_TENANT_BE_XWALK WHERE BE_ID = v_be_id;
    
    -- get SK for key name = MW_API_BE_EXCP_LIST
    SELECT NVL(
      (SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE KEY_NAME = 'MW_API_BE_EXCP_LIST' AND APP_TENANT_SK = v_app_tenant_sk AND APP_TENANT_KEY_CONF_PAR_SK IS NULL)
      , -1)
    INTO v_be_excp_conf_sk FROM dual;
    
    -- if not exist, insert key name = MW_API_BE_EXCP_LIST without value
    IF (v_be_excp_conf_sk = -1) THEN
      v_be_excp_conf_sk := PKG_APP.insertAppTenantKeyConf(APP_TENANT_KEY_CONF_T(0, v_current_date, v_current_date, null, v_app_tenant_sk, 'MW_API_BE_EXCP_LIST', null));
    END IF;
    
    -- get SK for key name = MW_API_BE_EXCP_LIST_EXCEPTION_ID
    SELECT NVL(
      (SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE 
        KEY_NAME = 'MW_API_BE_EXCP_LIST_EXCEPTION_ID'
        AND APP_TENANT_SK = v_app_tenant_sk
        AND APP_TENANT_KEY_CONF_PAR_SK = v_be_excp_conf_sk
        AND TENANT_KEY_CONF_VAL = v_be_excp_id)
      , -1)
    INTO v_be_excp_conf_id_sk FROM dual;

    -- if not exist, insert key name = MW_API_BE_EXCP_LIST_EXCEPTION_ID, value = v_be_excp_id
    IF (v_be_excp_conf_id_sk = -1) THEN
      v_be_excp_conf_id_sk := PKG_APP.insertAppTenantKeyConf(APP_TENANT_KEY_CONF_T(0, v_current_date, v_current_date, v_be_excp_conf_sk, v_app_tenant_sk, 'MW_API_BE_EXCP_LIST_EXCEPTION_ID', v_be_excp_id));
    END IF;
    
    -- get SK for key name = MW_API_BE_EXCP_LIST_EXCEPTION_SETTING
    SELECT NVL(
      (SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE KEY_NAME = 'MW_API_BE_EXCP_LIST_EXCEPTION_SETTING' AND APP_TENANT_SK = v_app_tenant_sk AND APP_TENANT_KEY_CONF_PAR_SK = v_be_excp_conf_id_sk)
      , -1)
    INTO v_be_excp_setting_sk FROM dual;
        
    -- if not exist, insert key name = MW_API_BE_EXCP_LIST_EXCEPTION_SETTING, value = v_be_excp_setting
    IF (v_be_excp_setting_sk = -1) THEN
      v_be_excp_setting_sk := PKG_APP.insertAppTenantKeyConf(APP_TENANT_KEY_CONF_T(0, v_current_date, v_current_date, v_be_excp_conf_id_sk, v_app_tenant_sk, 'MW_API_BE_EXCP_LIST_EXCEPTION_SETTING', v_be_excp_setting));
    ELSE
      UPDATE APP_TENANT_KEY_CONF
      SET
        TENANT_KEY_CONF_VAL = v_be_excp_setting,
        REC_UPDATE_TMSTP = v_current_date
      WHERE APP_TENANT_KEY_CONF_SK = v_be_excp_setting_sk;
    END IF;
      
    RETURN SQL%ROWCOUNT;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
  
  END UPDATE_BE_EXCP_LIST_SETTING;
  
  ------------------------------------------------------------------------
  FUNCTION GET_BE_EXCP_LIST_SETTING(v_be_excp_id APP_TENANT_KEY_CONF.TENANT_KEY_CONF_VAL%TYPE, v_be_id IN APP_TENANT_BE_XWALK.BE_ID%TYPE) RETURN VARCHAR2
  AS
    v_setting VARCHAR2(1000);

  BEGIN
  
    SELECT T3.TENANT_KEY_CONF_VAL INTO v_setting
    FROM
      APP_TENANT_BE_XWALK XWALK JOIN APP_TENANT_KEY_CONF T1 ON XWALK.APP_TENANT_SK = T1.APP_TENANT_SK AND T1.APP_TENANT_KEY_CONF_PAR_SK IS NULL
      JOIN APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_KEY_CONF_SK = T2.APP_TENANT_KEY_CONF_PAR_SK AND T1.APP_TENANT_SK = T2.APP_TENANT_SK
      JOIN APP_TENANT_KEY_CONF T3 ON T2.APP_TENANT_KEY_CONF_SK = T3.APP_TENANT_KEY_CONF_PAR_SK AND T2.APP_TENANT_SK = T3.APP_TENANT_SK
    WHERE
      XWALK.BE_ID = v_be_id
      AND T1.KEY_NAME = 'MW_API_BE_EXCP_LIST'
      AND T2.KEY_NAME = 'MW_API_BE_EXCP_LIST_EXCEPTION_ID' AND T2.TENANT_KEY_CONF_VAL = v_be_excp_id
      AND T3.KEY_NAME = 'MW_API_BE_EXCP_LIST_EXCEPTION_SETTING';
  
    RETURN v_setting;
    
    EXCEPTION
      WHEN OTHERS THEN
        RETURN NULL;

  END GET_BE_EXCP_LIST_SETTING;

  ------------------------------------------------------------------------
  FUNCTION DELETE_BE_EXCP_LIST_SETTING(v_be_excp_id APP_TENANT_KEY_CONF.TENANT_KEY_CONF_VAL%TYPE, v_be_id IN APP_TENANT_BE_XWALK.BE_ID%TYPE) RETURN NUMBER
  AS
    deleted_row_count NUMBER;
  BEGIN
  
    -- delete key name = MW_API_BE_EXCP_LIST_EXCEPTION_SETTING
    DELETE FROM APP_TENANT_KEY_CONF
    WHERE APP_TENANT_KEY_CONF_SK = 
      (SELECT T3.APP_TENANT_KEY_CONF_SK
      FROM
        APP_TENANT_BE_XWALK XWALK JOIN APP_TENANT_KEY_CONF T1 ON XWALK.APP_TENANT_SK = T1.APP_TENANT_SK AND T1.APP_TENANT_KEY_CONF_PAR_SK IS NULL
        JOIN APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_KEY_CONF_SK = T2.APP_TENANT_KEY_CONF_PAR_SK AND T1.APP_TENANT_SK = T2.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T3 ON T2.APP_TENANT_KEY_CONF_SK = T3.APP_TENANT_KEY_CONF_PAR_SK AND T2.APP_TENANT_SK = T3.APP_TENANT_SK
      WHERE
          XWALK.BE_ID = v_be_id
          AND T1.KEY_NAME = 'MW_API_BE_EXCP_LIST'
          AND T2.KEY_NAME = 'MW_API_BE_EXCP_LIST_EXCEPTION_ID' AND T2.TENANT_KEY_CONF_VAL = v_be_excp_id
          AND T3.KEY_NAME = 'MW_API_BE_EXCP_LIST_EXCEPTION_SETTING');
    
    deleted_row_count := SQL%ROWCOUNT;

    -- delete key name = MW_API_BE_EXCP_LIST_EXCEPTION_ID
    DELETE FROM APP_TENANT_KEY_CONF
    WHERE APP_TENANT_KEY_CONF_SK = 
      (SELECT T2.APP_TENANT_KEY_CONF_SK
      FROM
        APP_TENANT_BE_XWALK XWALK JOIN APP_TENANT_KEY_CONF T1 ON XWALK.APP_TENANT_SK = T1.APP_TENANT_SK AND T1.APP_TENANT_KEY_CONF_PAR_SK IS NULL
        JOIN APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_KEY_CONF_SK = T2.APP_TENANT_KEY_CONF_PAR_SK AND T1.APP_TENANT_SK = T2.APP_TENANT_SK
      WHERE
          XWALK.BE_ID = v_be_id
          AND T1.KEY_NAME = 'MW_API_BE_EXCP_LIST'
          AND T2.KEY_NAME = 'MW_API_BE_EXCP_LIST_EXCEPTION_ID' AND T2.TENANT_KEY_CONF_VAL = v_be_excp_id);
    
    deleted_row_count := deleted_row_count + SQL%ROWCOUNT;
  
    RETURN deleted_row_count;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
  
  END DELETE_BE_EXCP_LIST_SETTING;
------------------------------------------------------------------------
  FUNCTION GET_CONTR_EXCP_LST_SETTINGS(v_be_id VARCHAR2, v_payer_id VARCHAR2, v_contr_id VARCHAR2, v_excp_id VARCHAR2) RETURN VARCHAR2
  AS
    contr_excp_lst_settings VARCHAR2(1000);
  BEGIN
    SELECT TENANT_KEY_CONF_VAL INTO contr_excp_lst_settings
    FROM APP_TENANT_KEY_CONF
    WHERE 
        KEY_NAME = 'MW_API_CONTR_EXCP_LIST_CONTRACT_EXCEPTION_SETTING' 
        AND APP_TENANT_KEY_CONF_PAR_SK = (
            SELECT APP_TENANT_KEY_CONF_SK  -- EXCP_ID
            FROM APP_TENANT_KEY_CONF
            WHERE 
                KEY_NAME = 'MW_API_CONTR_EXCP_LIST_CONTRACT_EXCEPTION_ID' 
                AND TENANT_KEY_CONF_VAL = v_excp_id
                AND APP_TENANT_KEY_CONF_PAR_SK = (
                    SELECT APP_TENANT_KEY_CONF_SK  -- CONTRACT_ID
                    FROM APP_TENANT_KEY_CONF
                    WHERE 
                        KEY_NAME = 'MW_API_CONTR_EXCP_LIST_CONTRACT'
                        AND TENANT_KEY_CONF_VAL = v_contr_id
                        AND APP_TENANT_KEY_CONF_PAR_SK = (
                            SELECT APP_TENANT_KEY_CONF_SK -- PAYER_ID
                            FROM APP_TENANT_KEY_CONF
                            WHERE 
                                KEY_NAME = 'MW_API_CONTR_EXCP_LIST_PAYER'
                                AND TENANT_KEY_CONF_VAL = v_payer_id
                                AND APP_TENANT_KEY_CONF_PAR_SK = (
                                    SELECT APP_TENANT_KEY_CONF_SK -- ROOT OF CONTR_EXCP_LIST SETTING
                                    FROM APP_TENANT_KEY_CONF
                                    WHERE 
                                        KEY_NAME = 'MW_API_CONTR_EXCP_LIST'
                                        AND TENANT_KEY_CONF_VAL IS NULL
                                        AND APP_TENANT_SK = (
                                            SELECT APP_TENANT_SK  -- APP_TENANT_SK
                                            FROM APP_TENANT_BE_XWALK
                                            WHERE BE_ID = v_be_id
                                        )
                                )
                        )
                )
        );
    
    RETURN contr_excp_lst_settings;
    
    EXCEPTION
      WHEN OTHERS THEN
        RETURN NULL;
        
  END GET_CONTR_EXCP_LST_SETTINGS;

------------------------------------------------------------------------
  FUNCTION UPDATE_CONTR_EXCP_LST_SETTINGS(v_be_id VARCHAR2, v_payer_id VARCHAR2, v_contr_id VARCHAR2, v_excp_id VARCHAR2, v_settings VARCHAR2) RETURN NUMBER
  AS
    v_current_date DATE;
    v_app_tenant_sk NUMBER;
    v_cel_atkc_sk NUMBER;
    v_cel_payer_atkc_sk NUMBER;
    v_cel_contract_atkc_sk NUMBER;
    v_cel_excp_atkc_sk NUMBER;
    v_cel_excp_setting_atkc_sk NUMBER;
    v_Return NUMBER;
  BEGIN
    SELECT CAST(SYS_EXTRACT_UTC(SYSTIMESTAMP) AS DATE) INTO v_current_date FROM dual;
  
    SELECT APP_TENANT_SK INTO v_app_tenant_sk
    FROM APP_TENANT_BE_XWALK
    WHERE BE_ID = v_be_id;
    
    DBMS_OUTPUT.PUT_LINE('v_app_tenant_sk = ' || v_app_tenant_sk);
    
    -- INSERT IF NOT EXIST MW_API_CONTR_EXCP_LIST
    SELECT NVL((
      SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE 
        KEY_NAME = 'MW_API_CONTR_EXCP_LIST'
        AND APP_TENANT_KEY_CONF_PAR_SK IS NULL
        AND APP_TENANT_SK = v_app_tenant_sk), -1) INTO v_cel_atkc_sk
    FROM DUAL;
    DBMS_OUTPUT.PUT_LINE('v_cel_atkc_sk = ' || v_cel_atkc_sk);
    IF (v_cel_atkc_sk = -1) THEN
      v_cel_atkc_sk := PKG_APP.INSERTAPPTENANTKEYCONF(APP_TENANT_KEY_CONF_T(null, v_current_date, v_current_date, NULL, v_app_tenant_sk, 'MW_API_CONTR_EXCP_LIST', NULL));
    END IF;
    
    -- INSERT IF NOT EXIST MW_API_CONTR_EXCP_LIST_PAYER
    SELECT NVL((
      SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE 
        KEY_NAME = 'MW_API_CONTR_EXCP_LIST_PAYER'
        AND APP_TENANT_KEY_CONF_PAR_SK = v_cel_atkc_sk
        AND TENANT_KEY_CONF_VAL = v_payer_id
        AND APP_TENANT_SK = v_app_tenant_sk), -1) INTO v_cel_payer_atkc_sk
    FROM DUAL;
    DBMS_OUTPUT.PUT_LINE('v_cel_payer_atkc_sk = ' || v_cel_payer_atkc_sk);
    IF (v_cel_payer_atkc_sk = -1) THEN
      v_cel_payer_atkc_sk := PKG_APP.INSERTAPPTENANTKEYCONF(APP_TENANT_KEY_CONF_T(null, v_current_date, v_current_date, v_cel_atkc_sk, v_app_tenant_sk, 'MW_API_CONTR_EXCP_LIST_PAYER', v_payer_id));
    END IF;
    
    -- INSERT IF NOT EXIST MW_API_CONTR_EXCP_LIST_CONTRACT
    SELECT NVL((
      SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE 
        KEY_NAME = 'MW_API_CONTR_EXCP_LIST_CONTRACT'
        AND APP_TENANT_KEY_CONF_PAR_SK = v_cel_payer_atkc_sk
        AND TENANT_KEY_CONF_VAL = v_contr_id
        AND APP_TENANT_SK = v_app_tenant_sk), -1) INTO v_cel_contract_atkc_sk
    FROM DUAL;
    DBMS_OUTPUT.PUT_LINE('v_cel_contract_atkc_sk = ' || v_cel_contract_atkc_sk);
    IF (v_cel_contract_atkc_sk = -1) THEN
      v_cel_contract_atkc_sk := PKG_APP.INSERTAPPTENANTKEYCONF(APP_TENANT_KEY_CONF_T(null, v_current_date, v_current_date, v_cel_payer_atkc_sk, v_app_tenant_sk, 'MW_API_CONTR_EXCP_LIST_CONTRACT', v_contr_id));
    END IF;
    
    -- INSERT IF NOT EXIST MW_API_CONTR_EXCP_LIST_CONTRACT_EXCEPTION_ID
    SELECT NVL((
      SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE 
        KEY_NAME = 'MW_API_CONTR_EXCP_LIST_CONTRACT_EXCEPTION_ID'
        AND APP_TENANT_KEY_CONF_PAR_SK = v_cel_contract_atkc_sk
        AND TENANT_KEY_CONF_VAL = v_excp_id
        AND APP_TENANT_SK = v_app_tenant_sk), -1) INTO v_cel_excp_atkc_sk
    FROM DUAL;
    DBMS_OUTPUT.PUT_LINE('v_cel_excp_atkc_sk = ' || v_cel_excp_atkc_sk);
    IF (v_cel_excp_atkc_sk = -1) THEN
      v_cel_excp_atkc_sk := PKG_APP.INSERTAPPTENANTKEYCONF(APP_TENANT_KEY_CONF_T(null, v_current_date, v_current_date, v_cel_contract_atkc_sk, v_app_tenant_sk, 'MW_API_CONTR_EXCP_LIST_CONTRACT_EXCEPTION_ID', v_excp_id));
    END IF;
    
    -- INSERT IF NOT EXIST/UPDATE MW_API_CONTR_EXCP_LIST_CONTRACT_EXCEPTION_SETTING
    SELECT NVL((
      SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE 
        KEY_NAME = 'MW_API_CONTR_EXCP_LIST_CONTRACT_EXCEPTION_SETTING'
        AND APP_TENANT_KEY_CONF_PAR_SK = v_cel_excp_atkc_sk
        AND APP_TENANT_SK = v_app_tenant_sk), -1) INTO v_cel_excp_setting_atkc_sk
    FROM DUAL;
    DBMS_OUTPUT.PUT_LINE('v_cel_excp_setting_atkc_sk = ' || v_cel_excp_setting_atkc_sk);
    IF (v_cel_excp_setting_atkc_sk = -1) THEN
      v_cel_excp_setting_atkc_sk := PKG_APP.INSERTAPPTENANTKEYCONF(APP_TENANT_KEY_CONF_T(null, v_current_date, v_current_date, v_cel_excp_atkc_sk, v_app_tenant_sk, 'MW_API_CONTR_EXCP_LIST_CONTRACT_EXCEPTION_SETTING', v_settings));
    ELSE
      v_cel_excp_setting_atkc_sk := PKG_APP.UPDATEAPPTENANTKEYCONF(APP_TENANT_KEY_CONF_T(v_cel_excp_setting_atkc_sk, v_current_date, v_current_date, v_cel_excp_atkc_sk, v_app_tenant_sk, 'MW_API_CONTR_EXCP_LIST_CONTRACT_EXCEPTION_SETTING', v_settings));    
    END IF;
    
    
    RETURN v_cel_excp_setting_atkc_sk;

    EXCEPTION
      WHEN OTHERS THEN
        RETURN -1;
  
  END UPDATE_CONTR_EXCP_LST_SETTINGS;

------------------------------------------------------------------------
  FUNCTION DELETE_CONTR_EXCP_LST_SETTINGS(v_be_id VARCHAR2, v_payer_id VARCHAR2, v_contr_id VARCHAR2, v_excp_id VARCHAR2) RETURN NUMBER
  AS
    deleted_row_count NUMBER;
  BEGIN
    -- delete key name = MW_API_CONTR_EXCP_LIST_CONTRACT_EXCEPTION_SETTING
    DELETE FROM APP_TENANT_KEY_CONF
    WHERE APP_TENANT_KEY_CONF_SK = (
      SELECT T5.APP_TENANT_KEY_CONF_SK
      FROM
        APP_TENANT_BE_XWALK XWALK JOIN APP_TENANT_KEY_CONF T1 ON XWALK.APP_TENANT_SK = T1.APP_TENANT_SK AND T1.APP_TENANT_KEY_CONF_PAR_SK IS NULL
        JOIN APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_KEY_CONF_SK = T2.APP_TENANT_KEY_CONF_PAR_SK AND T1.APP_TENANT_SK = T2.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T3 ON T2.APP_TENANT_KEY_CONF_SK = T3.APP_TENANT_KEY_CONF_PAR_SK AND T2.APP_TENANT_SK = T3.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T4 ON T3.APP_TENANT_KEY_CONF_SK = T4.APP_TENANT_KEY_CONF_PAR_SK AND T3.APP_TENANT_SK = T4.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T5 ON T4.APP_TENANT_KEY_CONF_SK = T5.APP_TENANT_KEY_CONF_PAR_SK AND T4.APP_TENANT_SK = T5.APP_TENANT_SK
      WHERE
          XWALK.BE_ID = v_be_id
          AND T1.KEY_NAME = 'MW_API_CONTR_EXCP_LIST'
          AND T2.KEY_NAME = 'MW_API_CONTR_EXCP_LIST_PAYER' AND T2.TENANT_KEY_CONF_VAL = v_payer_id
          AND T3.KEY_NAME = 'MW_API_CONTR_EXCP_LIST_CONTRACT' AND T3.TENANT_KEY_CONF_VAL = v_contr_id
          AND T4.KEY_NAME = 'MW_API_CONTR_EXCP_LIST_CONTRACT_EXCEPTION_ID' AND T4.TENANT_KEY_CONF_VAL = v_excp_id
          AND T5.KEY_NAME = 'MW_API_CONTR_EXCP_LIST_CONTRACT_EXCEPTION_SETTING'
    );
    
    deleted_row_count := SQL%ROWCOUNT;
    
    -- delete key name = MW_API_CONTR_EXCP_LIST_CONTRACT_EXCEPTION_ID
    DELETE FROM APP_TENANT_KEY_CONF
    WHERE APP_TENANT_KEY_CONF_SK = (
      SELECT T4.APP_TENANT_KEY_CONF_SK
      FROM
        APP_TENANT_BE_XWALK XWALK JOIN APP_TENANT_KEY_CONF T1 ON XWALK.APP_TENANT_SK = T1.APP_TENANT_SK AND T1.APP_TENANT_KEY_CONF_PAR_SK IS NULL
        JOIN APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_KEY_CONF_SK = T2.APP_TENANT_KEY_CONF_PAR_SK AND T1.APP_TENANT_SK = T2.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T3 ON T2.APP_TENANT_KEY_CONF_SK = T3.APP_TENANT_KEY_CONF_PAR_SK AND T2.APP_TENANT_SK = T3.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T4 ON T3.APP_TENANT_KEY_CONF_SK = T4.APP_TENANT_KEY_CONF_PAR_SK AND T3.APP_TENANT_SK = T4.APP_TENANT_SK
      WHERE
          XWALK.BE_ID = v_be_id
          AND T1.KEY_NAME = 'MW_API_CONTR_EXCP_LIST'
          AND T2.KEY_NAME = 'MW_API_CONTR_EXCP_LIST_PAYER' AND T2.TENANT_KEY_CONF_VAL = v_payer_id
          AND T3.KEY_NAME = 'MW_API_CONTR_EXCP_LIST_CONTRACT' AND T3.TENANT_KEY_CONF_VAL = v_contr_id
          AND T4.KEY_NAME = 'MW_API_CONTR_EXCP_LIST_CONTRACT_EXCEPTION_ID' AND T4.TENANT_KEY_CONF_VAL = v_excp_id
    );
    
    deleted_row_count := deleted_row_count + SQL%ROWCOUNT;
  
    RETURN deleted_row_count;
    
    EXCEPTION
      WHEN OTHERS THEN
        RETURN -1;
  
  END DELETE_CONTR_EXCP_LST_SETTINGS;

  ------------------------------------------------------------------------
  FUNCTION INS_UPD_BE_RNDING_RULE_SETTING(v_be_id VARCHAR2, v_rnding_rule_qlfr VARCHAR2, v_rnding_rule_id VARCHAR2, v_rnding_rule_setting VARCHAR2) RETURN NUMBER
  AS
    v_app_tenant_sk NUMBER;
    v_rnding_rule_conf_sk NUMBER;
    v_rnding_rule_qlfr_conf_sk NUMBER;
    v_rnding_rule_id_conf_sk NUMBER;
    v_rnding_rule_setting_sk NUMBER;
    v_current_date DATE;
  
  BEGIN
    SELECT CAST(SYS_EXTRACT_UTC(SYSTIMESTAMP) AS DATE) INTO v_current_date FROM dual;
  
    SELECT APP_TENANT_SK INTO v_app_tenant_sk FROM APP_TENANT_BE_XWALK WHERE BE_ID = v_be_id;
  
    -- get SK for key name = MW_API_BE_VV_RNDING_RULE
    SELECT NVL(
      (SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE KEY_NAME = 'MW_API_BE_VV_RNDING_RULE' AND APP_TENANT_SK = v_app_tenant_sk AND APP_TENANT_KEY_CONF_PAR_SK IS NULL)
      , -1)
    INTO v_rnding_rule_conf_sk FROM dual;
    
    -- if not exist, insert key name = MW_API_BE_VV_RNDING_RULE without value
    IF (v_rnding_rule_conf_sk = -1) THEN
      v_rnding_rule_conf_sk := PKG_APP.insertAppTenantKeyConf(APP_TENANT_KEY_CONF_T(0, v_current_date, v_current_date, null, v_app_tenant_sk, 'MW_API_BE_VV_RNDING_RULE', null));
    END IF;

    -- get SK for key name = MW_API_BE_VV_RNDING_RULE_QLFR
    SELECT NVL(
      (SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE 
        KEY_NAME = 'MW_API_BE_VV_RNDING_RULE_QLFR'
        AND APP_TENANT_SK = v_app_tenant_sk
        AND APP_TENANT_KEY_CONF_PAR_SK = v_rnding_rule_conf_sk
        AND TENANT_KEY_CONF_VAL = v_rnding_rule_qlfr)
      , -1)
    INTO v_rnding_rule_qlfr_conf_sk FROM dual;

    -- if not exist, insert key name = MW_API_BE_VV_RNDING_RULE_QLFR, value = v_rnding_rule_qlfr
    IF (v_rnding_rule_qlfr_conf_sk = -1) THEN
      v_rnding_rule_qlfr_conf_sk := PKG_APP.insertAppTenantKeyConf(
        APP_TENANT_KEY_CONF_T(0, v_current_date, v_current_date, v_rnding_rule_conf_sk, v_app_tenant_sk, 'MW_API_BE_VV_RNDING_RULE_QLFR', v_rnding_rule_qlfr));
    END IF;
    
    -- get SK for key name = MW_API_BE_VV_RNDING_RULE_ID
    SELECT NVL(
      (SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE 
        KEY_NAME = 'MW_API_BE_VV_RNDING_RULE_ID'
        AND APP_TENANT_SK = v_app_tenant_sk
        AND APP_TENANT_KEY_CONF_PAR_SK = v_rnding_rule_qlfr_conf_sk
        AND TENANT_KEY_CONF_VAL = v_rnding_rule_id)
      , -1)
    INTO v_rnding_rule_id_conf_sk FROM dual;

    -- if not exist, insert key name = MW_API_BE_VV_RNDING_RULE_ID, value = v_rnding_rule_id
    IF (v_rnding_rule_id_conf_sk = -1) THEN
      v_rnding_rule_id_conf_sk := PKG_APP.insertAppTenantKeyConf(
        APP_TENANT_KEY_CONF_T(0, v_current_date, v_current_date, v_rnding_rule_qlfr_conf_sk, v_app_tenant_sk, 'MW_API_BE_VV_RNDING_RULE_ID', v_rnding_rule_id));
    END IF;
    
    -- get SK for key name = MW_API_BE_VV_RNDING_RULE_SETTINGS
    SELECT NVL(
      (SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE
        KEY_NAME = 'MW_API_BE_VV_RNDING_RULE_SETTINGS'
        AND APP_TENANT_SK = v_app_tenant_sk
        AND APP_TENANT_KEY_CONF_PAR_SK = v_rnding_rule_id_conf_sk)
      , -1)
    INTO v_rnding_rule_setting_sk FROM dual;
        
    -- if not exist, insert key name = MW_API_BE_VV_RNDING_RULE_SETTINGS, value = v_rnding_rule_setting
    IF (v_rnding_rule_setting_sk = -1) THEN
      v_rnding_rule_setting_sk := PKG_APP.insertAppTenantKeyConf(
        APP_TENANT_KEY_CONF_T(0, v_current_date, v_current_date, v_rnding_rule_id_conf_sk, v_app_tenant_sk, 'MW_API_BE_VV_RNDING_RULE_SETTINGS', v_rnding_rule_setting));
    ELSE
      UPDATE APP_TENANT_KEY_CONF
      SET
        TENANT_KEY_CONF_VAL = v_rnding_rule_setting,
        REC_UPDATE_TMSTP = v_current_date
      WHERE APP_TENANT_KEY_CONF_SK = v_rnding_rule_setting_sk;
    END IF;
    
    RETURN v_rnding_rule_setting_sk;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
      
  END INS_UPD_BE_RNDING_RULE_SETTING;

  ------------------------------------------------------------------------
  FUNCTION GET_BE_RNDING_RULE_SETTING(v_be_id VARCHAR2, v_rnding_rule_qlfr VARCHAR2, v_rnding_rule_id VARCHAR2) RETURN VARCHAR2
  AS
    v_setting APP_TENANT_KEY_CONF.TENANT_KEY_CONF_VAL%TYPE;
  
  BEGIN
    
    SELECT T4.TENANT_KEY_CONF_VAL INTO v_setting
    FROM
      APP_TENANT_BE_XWALK XWALK JOIN APP_TENANT_KEY_CONF T1 ON XWALK.APP_TENANT_SK = T1.APP_TENANT_SK AND T1.APP_TENANT_KEY_CONF_PAR_SK IS NULL
      JOIN APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_KEY_CONF_SK = T2.APP_TENANT_KEY_CONF_PAR_SK AND T1.APP_TENANT_SK = T2.APP_TENANT_SK
      JOIN APP_TENANT_KEY_CONF T3 ON T2.APP_TENANT_KEY_CONF_SK = T3.APP_TENANT_KEY_CONF_PAR_SK AND T2.APP_TENANT_SK = T3.APP_TENANT_SK
      JOIN APP_TENANT_KEY_CONF T4 ON T3.APP_TENANT_KEY_CONF_SK = T4.APP_TENANT_KEY_CONF_PAR_SK AND T3.APP_TENANT_SK = T4.APP_TENANT_SK
    WHERE
      XWALK.BE_ID = v_be_id
      AND T1.KEY_NAME = 'MW_API_BE_VV_RNDING_RULE'
      AND T2.KEY_NAME = 'MW_API_BE_VV_RNDING_RULE_QLFR' AND T2.TENANT_KEY_CONF_VAL = v_rnding_rule_qlfr
      AND T3.KEY_NAME = 'MW_API_BE_VV_RNDING_RULE_ID' AND T3.TENANT_KEY_CONF_VAL = v_rnding_rule_id
      AND T4.KEY_NAME = 'MW_API_BE_VV_RNDING_RULE_SETTINGS';
  
    RETURN v_setting;
    
    EXCEPTION
      WHEN OTHERS THEN
        RETURN NULL;
      
  END GET_BE_RNDING_RULE_SETTING;
  
  ------------------------------------------------------------------------
  FUNCTION DELETE_BE_RNDING_RULE_SETTING(v_be_id VARCHAR2, v_rnding_rule_qlfr VARCHAR2, v_rnding_rule_id VARCHAR2) RETURN NUMBER
  AS
    deleted_row_count NUMBER;
  
  BEGIN
    
    -- delete key name = MW_API_BE_VV_RNDING_RULE_SETTINGS
    DELETE FROM APP_TENANT_KEY_CONF
    WHERE APP_TENANT_KEY_CONF_SK = 
      (SELECT T4.APP_TENANT_KEY_CONF_SK
      FROM
        APP_TENANT_BE_XWALK XWALK JOIN APP_TENANT_KEY_CONF T1 ON XWALK.APP_TENANT_SK = T1.APP_TENANT_SK AND T1.APP_TENANT_KEY_CONF_PAR_SK IS NULL
        JOIN APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_KEY_CONF_SK = T2.APP_TENANT_KEY_CONF_PAR_SK AND T1.APP_TENANT_SK = T2.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T3 ON T2.APP_TENANT_KEY_CONF_SK = T3.APP_TENANT_KEY_CONF_PAR_SK AND T2.APP_TENANT_SK = T3.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T4 ON T3.APP_TENANT_KEY_CONF_SK = T4.APP_TENANT_KEY_CONF_PAR_SK AND T3.APP_TENANT_SK = T4.APP_TENANT_SK
      WHERE
        XWALK.BE_ID = v_be_id
        AND T1.KEY_NAME = 'MW_API_BE_VV_RNDING_RULE'
        AND T2.KEY_NAME = 'MW_API_BE_VV_RNDING_RULE_QLFR' AND T2.TENANT_KEY_CONF_VAL = v_rnding_rule_qlfr
        AND T3.KEY_NAME = 'MW_API_BE_VV_RNDING_RULE_ID' AND T3.TENANT_KEY_CONF_VAL = v_rnding_rule_id
        AND T4.KEY_NAME = 'MW_API_BE_VV_RNDING_RULE_SETTINGS');
    
    deleted_row_count := SQL%ROWCOUNT;

    -- delete key name = MW_API_BE_VV_RNDING_RULE_ID
    DELETE FROM APP_TENANT_KEY_CONF
    WHERE APP_TENANT_KEY_CONF_SK =
      (SELECT T3.APP_TENANT_KEY_CONF_SK
      FROM
        APP_TENANT_BE_XWALK XWALK JOIN APP_TENANT_KEY_CONF T1 ON XWALK.APP_TENANT_SK = T1.APP_TENANT_SK AND T1.APP_TENANT_KEY_CONF_PAR_SK IS NULL
        JOIN APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_KEY_CONF_SK = T2.APP_TENANT_KEY_CONF_PAR_SK AND T1.APP_TENANT_SK = T2.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T3 ON T2.APP_TENANT_KEY_CONF_SK = T3.APP_TENANT_KEY_CONF_PAR_SK AND T2.APP_TENANT_SK = T3.APP_TENANT_SK
      WHERE
        XWALK.BE_ID = v_be_id
        AND T1.KEY_NAME = 'MW_API_BE_VV_RNDING_RULE'
        AND T2.KEY_NAME = 'MW_API_BE_VV_RNDING_RULE_QLFR' AND T2.TENANT_KEY_CONF_VAL = v_rnding_rule_qlfr
        AND T3.KEY_NAME = 'MW_API_BE_VV_RNDING_RULE_ID' AND T3.TENANT_KEY_CONF_VAL = v_rnding_rule_id);
    
    deleted_row_count := deleted_row_count + SQL%ROWCOUNT;
  
    RETURN deleted_row_count;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
  
  END DELETE_BE_RNDING_RULE_SETTING;

  ------------------------------------------------------------------------
  FUNCTION INS_UPD_CONTR_RNDG_RULE_STNG(v_be_id VARCHAR2, v_payer_id VARCHAR2, v_contr_id VARCHAR2, v_rnding_rule_qlfr VARCHAR2, v_rnding_rule_id VARCHAR2, v_rnding_rule_setting VARCHAR2) RETURN NUMBER
  AS
    v_app_tenant_sk NUMBER;
    v_contr_rndg_rule_conf_sk NUMBER;
    v_contr_rndg_rule_payer_sk NUMBER;
    v_contr_rndg_rule_contract_sk NUMBER;
    v_contr_rndg_rule_qlfr_sk NUMBER;
    v_contr_rndg_rule_id_conf_sk NUMBER;
    v_contr_rndg_rule_setting_sk NUMBER;
    v_current_date DATE;
  
  BEGIN
    SELECT CAST(SYS_EXTRACT_UTC(SYSTIMESTAMP) AS DATE) INTO v_current_date FROM dual;
  
    SELECT APP_TENANT_SK INTO v_app_tenant_sk FROM APP_TENANT_BE_XWALK WHERE BE_ID = v_be_id;
    
    -- get SK for key name = MW_API_CONTR_VV_RNDING_RULE
    SELECT NVL(
      (SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE
        KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE'
        AND APP_TENANT_SK = v_app_tenant_sk
        AND APP_TENANT_KEY_CONF_PAR_SK IS NULL)
      , -1)
    INTO v_contr_rndg_rule_conf_sk FROM dual;
    
    -- if not exist, insert key name = MW_API_CONTR_VV_RNDING_RULE without value
    IF (v_contr_rndg_rule_conf_sk = -1) THEN
      v_contr_rndg_rule_conf_sk := PKG_APP.insertAppTenantKeyConf(
          APP_TENANT_KEY_CONF_T(0, v_current_date, v_current_date, null, v_app_tenant_sk, 'MW_API_CONTR_VV_RNDING_RULE', null));
    END IF;

    -- get SK for key name = MW_API_CONTR_VV_RNDING_RULE_PAYER
    SELECT NVL(
      (SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE
        KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_PAYER'
        AND APP_TENANT_SK = v_app_tenant_sk
        AND APP_TENANT_KEY_CONF_PAR_SK = v_contr_rndg_rule_conf_sk
        AND TENANT_KEY_CONF_VAL = v_payer_id)
      , -1)
    INTO v_contr_rndg_rule_payer_sk FROM dual;
    
    -- if not exist, insert key name = MW_API_CONTR_VV_RNDING_RULE_PAYER, value = v_payer_id
    IF (v_contr_rndg_rule_payer_sk = -1) THEN
      v_contr_rndg_rule_payer_sk := PKG_APP.insertAppTenantKeyConf(
          APP_TENANT_KEY_CONF_T(0, v_current_date, v_current_date, v_contr_rndg_rule_conf_sk, v_app_tenant_sk, 'MW_API_CONTR_VV_RNDING_RULE_PAYER', v_payer_id));
    END IF;

    -- get SK for key name = MW_API_CONTR_VV_RNDING_RULE_CONTRACT
    SELECT NVL(
      (SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE
        KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_CONTRACT'
        AND APP_TENANT_SK = v_app_tenant_sk
        AND APP_TENANT_KEY_CONF_PAR_SK = v_contr_rndg_rule_payer_sk
        AND TENANT_KEY_CONF_VAL = v_contr_id)
      , -1)
    INTO v_contr_rndg_rule_contract_sk FROM dual;
    
    -- if not exist, insert key name = MW_API_CONTR_VV_RNDING_RULE_CONTRACT, value = v_contr_id
    IF (v_contr_rndg_rule_contract_sk = -1) THEN
      v_contr_rndg_rule_contract_sk := PKG_APP.insertAppTenantKeyConf(
          APP_TENANT_KEY_CONF_T(0, v_current_date, v_current_date, v_contr_rndg_rule_payer_sk, v_app_tenant_sk, 'MW_API_CONTR_VV_RNDING_RULE_CONTRACT', v_contr_id));
    END IF;

    -- get SK for key name = MW_API_CONTR_VV_RNDING_RULE_QLFR
    SELECT NVL(
      (SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE 
        KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_QLFR'
        AND APP_TENANT_SK = v_app_tenant_sk
        AND APP_TENANT_KEY_CONF_PAR_SK = v_contr_rndg_rule_contract_sk
        AND TENANT_KEY_CONF_VAL = v_rnding_rule_qlfr)
      , -1)
    INTO v_contr_rndg_rule_qlfr_sk FROM dual;

    -- if not exist, insert key name = MW_API_CONTR_VV_RNDING_RULE_QLFR, value = v_rnding_rule_qlfr
    IF (v_contr_rndg_rule_qlfr_sk = -1) THEN
      v_contr_rndg_rule_qlfr_sk := PKG_APP.insertAppTenantKeyConf(
        APP_TENANT_KEY_CONF_T(0, v_current_date, v_current_date, v_contr_rndg_rule_contract_sk, v_app_tenant_sk, 'MW_API_CONTR_VV_RNDING_RULE_QLFR', v_rnding_rule_qlfr));
    END IF;

    -- get SK for key name = MW_API_CONTR_VV_RNDING_RULE_ID
    SELECT NVL(
      (SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE 
        KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_ID'
        AND APP_TENANT_SK = v_app_tenant_sk
        AND APP_TENANT_KEY_CONF_PAR_SK = v_contr_rndg_rule_qlfr_sk
        AND TENANT_KEY_CONF_VAL = v_rnding_rule_id)
      , -1)
    INTO v_contr_rndg_rule_id_conf_sk FROM dual;

    -- if not exist, insert key name = MW_API_CONTR_VV_RNDING_RULE_ID, value = v_rnding_rule_id
    IF (v_contr_rndg_rule_id_conf_sk = -1) THEN
      v_contr_rndg_rule_id_conf_sk := PKG_APP.insertAppTenantKeyConf(
        APP_TENANT_KEY_CONF_T(0, v_current_date, v_current_date, v_contr_rndg_rule_qlfr_sk, v_app_tenant_sk, 'MW_API_CONTR_VV_RNDING_RULE_ID', v_rnding_rule_id));
    END IF;
 
    -- get SK for key name = MW_API_CONTR_VV_RNDING_RULE_SETTINGS
    SELECT NVL(
      (SELECT APP_TENANT_KEY_CONF_SK
      FROM APP_TENANT_KEY_CONF
      WHERE KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_SETTINGS' AND APP_TENANT_SK = v_app_tenant_sk AND APP_TENANT_KEY_CONF_PAR_SK = v_contr_rndg_rule_id_conf_sk)
      , -1)
    INTO v_contr_rndg_rule_setting_sk FROM dual;
        
    -- if not exist, insert key name = MW_API_CONTR_VV_RNDING_RULE_SETTINGS, value = v_rnding_rule_setting
    IF (v_contr_rndg_rule_setting_sk = -1) THEN
      v_contr_rndg_rule_setting_sk := PKG_APP.insertAppTenantKeyConf(
          APP_TENANT_KEY_CONF_T(0, v_current_date, v_current_date, v_contr_rndg_rule_id_conf_sk, v_app_tenant_sk, 'MW_API_CONTR_VV_RNDING_RULE_SETTINGS', v_rnding_rule_setting));
    ELSE
      UPDATE APP_TENANT_KEY_CONF
      SET
        TENANT_KEY_CONF_VAL = v_rnding_rule_setting,
        REC_UPDATE_TMSTP = v_current_date
      WHERE APP_TENANT_KEY_CONF_SK = v_contr_rndg_rule_setting_sk;
    END IF;
    
    RETURN v_contr_rndg_rule_setting_sk;

  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
      
  END INS_UPD_CONTR_RNDG_RULE_STNG;
  
  ------------------------------------------------------------------------
  FUNCTION GET_CONTR_RNDG_RULE_STNG(v_be_id VARCHAR2, v_payer_id VARCHAR2, v_contr_id VARCHAR2, v_rnding_rule_qlfr VARCHAR2, v_rnding_rule_id VARCHAR2) RETURN VARCHAR2
  AS
    v_setting APP_TENANT_KEY_CONF.TENANT_KEY_CONF_VAL%TYPE;
  
  BEGIN
    
    SELECT T6.TENANT_KEY_CONF_VAL INTO v_setting
    FROM
      APP_TENANT_BE_XWALK XWALK JOIN APP_TENANT_KEY_CONF T1 ON XWALK.APP_TENANT_SK = T1.APP_TENANT_SK AND T1.APP_TENANT_KEY_CONF_PAR_SK IS NULL
      JOIN APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_KEY_CONF_SK = T2.APP_TENANT_KEY_CONF_PAR_SK AND T1.APP_TENANT_SK = T2.APP_TENANT_SK
      JOIN APP_TENANT_KEY_CONF T3 ON T2.APP_TENANT_KEY_CONF_SK = T3.APP_TENANT_KEY_CONF_PAR_SK AND T2.APP_TENANT_SK = T3.APP_TENANT_SK
      JOIN APP_TENANT_KEY_CONF T4 ON T3.APP_TENANT_KEY_CONF_SK = T4.APP_TENANT_KEY_CONF_PAR_SK AND T3.APP_TENANT_SK = T4.APP_TENANT_SK
      JOIN APP_TENANT_KEY_CONF T5 ON T4.APP_TENANT_KEY_CONF_SK = T5.APP_TENANT_KEY_CONF_PAR_SK AND T4.APP_TENANT_SK = T5.APP_TENANT_SK
      JOIN APP_TENANT_KEY_CONF T6 ON T5.APP_TENANT_KEY_CONF_SK = T6.APP_TENANT_KEY_CONF_PAR_SK AND T5.APP_TENANT_SK = T6.APP_TENANT_SK
    WHERE
      XWALK.BE_ID = v_be_id
      AND T1.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE'
      AND T2.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_PAYER' AND T2.TENANT_KEY_CONF_VAL = v_payer_id
      AND T3.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_CONTRACT' AND T3.TENANT_KEY_CONF_VAL = v_contr_id
      AND T4.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_QLFR' AND T4.TENANT_KEY_CONF_VAL = v_rnding_rule_qlfr
      AND T5.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_ID' AND T5.TENANT_KEY_CONF_VAL = v_rnding_rule_id
      AND T6.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_SETTINGS';
  
    RETURN v_setting;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  
  END GET_CONTR_RNDG_RULE_STNG;
  
  ------------------------------------------------------------------------
  FUNCTION DELETE_CONTR_RNDG_RULE_STNG(v_be_id VARCHAR2, v_payer_id VARCHAR2, v_contr_id VARCHAR2, v_rnding_rule_qlfr VARCHAR2, v_rnding_rule_id VARCHAR2) RETURN NUMBER
  AS
    deleted_row_count NUMBER;
  BEGIN
    -- delete key name = MW_API_CONTR_VV_RNDING_RULE_SETTINGS
    DELETE FROM APP_TENANT_KEY_CONF
    WHERE APP_TENANT_KEY_CONF_SK = (
      SELECT T6.APP_TENANT_KEY_CONF_SK
      FROM
        APP_TENANT_BE_XWALK XWALK JOIN APP_TENANT_KEY_CONF T1 ON XWALK.APP_TENANT_SK = T1.APP_TENANT_SK AND T1.APP_TENANT_KEY_CONF_PAR_SK IS NULL
        JOIN APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_KEY_CONF_SK = T2.APP_TENANT_KEY_CONF_PAR_SK AND T1.APP_TENANT_SK = T2.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T3 ON T2.APP_TENANT_KEY_CONF_SK = T3.APP_TENANT_KEY_CONF_PAR_SK AND T2.APP_TENANT_SK = T3.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T4 ON T3.APP_TENANT_KEY_CONF_SK = T4.APP_TENANT_KEY_CONF_PAR_SK AND T3.APP_TENANT_SK = T4.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T5 ON T4.APP_TENANT_KEY_CONF_SK = T5.APP_TENANT_KEY_CONF_PAR_SK AND T4.APP_TENANT_SK = T5.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T6 ON T5.APP_TENANT_KEY_CONF_SK = T6.APP_TENANT_KEY_CONF_PAR_SK AND T5.APP_TENANT_SK = T6.APP_TENANT_SK
      WHERE
        XWALK.BE_ID = v_be_id
        AND T1.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE'
        AND T2.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_PAYER' AND T2.TENANT_KEY_CONF_VAL = v_payer_id
        AND T3.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_CONTRACT' AND T3.TENANT_KEY_CONF_VAL = v_contr_id
        AND T4.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_QLFR' AND T4.TENANT_KEY_CONF_VAL = v_rnding_rule_qlfr
        AND T5.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_ID' AND T5.TENANT_KEY_CONF_VAL = v_rnding_rule_id
        AND T6.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_SETTINGS'
    );
    
    deleted_row_count := SQL%ROWCOUNT;
    
    -- delete key name = MW_API_CONTR_EXCP_LIST_CONTRACT_EXCEPTION_ID
    DELETE FROM APP_TENANT_KEY_CONF
    WHERE APP_TENANT_KEY_CONF_SK = (
      SELECT T5.APP_TENANT_KEY_CONF_SK
      FROM
        APP_TENANT_BE_XWALK XWALK JOIN APP_TENANT_KEY_CONF T1 ON XWALK.APP_TENANT_SK = T1.APP_TENANT_SK AND T1.APP_TENANT_KEY_CONF_PAR_SK IS NULL
        JOIN APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_KEY_CONF_SK = T2.APP_TENANT_KEY_CONF_PAR_SK AND T1.APP_TENANT_SK = T2.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T3 ON T2.APP_TENANT_KEY_CONF_SK = T3.APP_TENANT_KEY_CONF_PAR_SK AND T2.APP_TENANT_SK = T3.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T4 ON T3.APP_TENANT_KEY_CONF_SK = T4.APP_TENANT_KEY_CONF_PAR_SK AND T3.APP_TENANT_SK = T4.APP_TENANT_SK
        JOIN APP_TENANT_KEY_CONF T5 ON T4.APP_TENANT_KEY_CONF_SK = T5.APP_TENANT_KEY_CONF_PAR_SK AND T4.APP_TENANT_SK = T5.APP_TENANT_SK
      WHERE
      XWALK.BE_ID = v_be_id
      AND T1.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE'
      AND T2.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_PAYER' AND T2.TENANT_KEY_CONF_VAL = v_payer_id
      AND T3.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_CONTRACT' AND T3.TENANT_KEY_CONF_VAL = v_contr_id
      AND T4.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_QLFR' AND T4.TENANT_KEY_CONF_VAL = v_rnding_rule_qlfr
      AND T5.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_ID' AND T5.TENANT_KEY_CONF_VAL = v_rnding_rule_id
    );
    
    deleted_row_count := deleted_row_count + SQL%ROWCOUNT;
  
    RETURN deleted_row_count;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN -1;
      
  END DELETE_CONTR_RNDG_RULE_STNG;
END PKG_APP_UTIL;
/
