CREATE OR REPLACE TYPE AUDIT_CHANGED_T AS OBJECT (

  USER_GUID VARCHAR2(128 BYTE),
  USER_NAME	VARCHAR2(100 BYTE),
  USER_FIRST_NAME VARCHAR2(100 BYTE),
  USER_LAST_NAME VARCHAR2(100 BYTE),
  DATA_POINT	VARCHAR2(50 BYTE),
  CHANGED_FROM	VARCHAR2(100 BYTE),
  CHANGED_TO	VARCHAR2(100 BYTE),
  CHANGED_ON DATE
);
/

CREATE OR REPLACE TYPE AUDIT_EMPL_STATUS_HIST_T AS OBJECT (

  AUDIT_HOST VARCHAR2(128 BYTE),
  USER_GUID VARCHAR2(128 BYTE),
  EFFECTIVE_DATE	DATE,
  STATUS	VARCHAR2(50 BYTE),
  REASON_CODE	VARCHAR2(100 BYTE),
  NOTES	VARCHAR2(255 BYTE),
  MODIFIED DATE
);
/

CREATE OR REPLACE TYPE AUDIT_VISIT_HIST_T AS OBJECT (

  AUDIT_HOST VARCHAR2(128 BYTE),
  USER_GUID VARCHAR2(128 BYTE),
  REASON_CODE VARCHAR2(200 BYTE),
  NOTES VARCHAR2(255 BYTE),  

  PT_ID_OLD VARCHAR2(100 BYTE),
  PT_ID_NEW VARCHAR2(100 BYTE),
  PT_NAME_OLD VARCHAR2(600 BYTE),
  PT_NAME_NEW VARCHAR2(600 BYTE),
  STAFF_ID_OLD VARCHAR2(100 BYTE),
  STAFF_ID_NEW VARCHAR2(100 BYTE),
  STAFF_NAME_OLD VARCHAR2(600 BYTE),
  STAFF_NAME_NEW VARCHAR2(600 BYTE),
  SERVICE_OLD VARCHAR2(100 BYTE),
  SERVICE_NEW VARCHAR2(100 BYTE),

  SCHD_IN_OLD VARCHAR2(100 BYTE),
  SCHD_IN_NEW VARCHAR2(100 BYTE),
  SCHD_OUT_OLD VARCHAR2(100 BYTE),
  SCHD_OUT_NEW VARCHAR2(100 BYTE),
  SCHD_HRS_OLD VARCHAR2(100 BYTE),
  SCHD_HRS_NEW VARCHAR2(100 BYTE),

  CALL_IN_OLD VARCHAR2(100 BYTE),
  CALL_IN_NEW VARCHAR2(100 BYTE),
  CALL_OUT_OLD VARCHAR2(100 BYTE),
  CALL_OUT_NEW VARCHAR2(100 BYTE),
  CALL_HRS_OLD VARCHAR2(100 BYTE),
  CALL_HRS_NEW VARCHAR2(100 BYTE),

  ADJ_IN_OLD VARCHAR2(100 BYTE),
  ADJ_IN_NEW VARCHAR2(100 BYTE),
  ADJ_OUT_OLD VARCHAR2(100 BYTE),
  ADJ_OUT_NEW VARCHAR2(100 BYTE),
  ADJ_HRS_OLD VARCHAR2(100 BYTE),
  ADJ_HRS_NEW VARCHAR2(100 BYTE),

  PAY_HRS_OLD VARCHAR2(100 BYTE),
  PAY_HRS_NEW VARCHAR2(100 BYTE),
  BILL_HRS_OLD VARCHAR2(100 BYTE),
  BILL_HRS_NEW VARCHAR2(100 BYTE),
  
  STATUS_OLD VARCHAR2(100 BYTE),
  STATUS_NEW VARCHAR2(100 BYTE),

  VERIFY_BY_SCHD_OLD VARCHAR2(100 BYTE),
  VERIFY_BY_SCHD_NEW VARCHAR2(100 BYTE),
  PAY_BY_SCHD_OLD VARCHAR2(100 BYTE),
  PAY_BY_SCHD_NEW VARCHAR2(100 BYTE),

  OT_ABS_HOURS_OLD VARCHAR2(100 BYTE),
  OT_ABS_HOURS_NEW VARCHAR2(100 BYTE),

  APPRV_OLD VARCHAR2(100 BYTE),
  APPRV_NEW VARCHAR2(100 BYTE),

  VISIT_EXCEPTIONS_OLD VARCHAR2(1000 BYTE),
  VISIT_EXCEPTIONS_NEW VARCHAR2(1000 BYTE),
  VISIT_NOTES_OLD VARCHAR2(1000 BYTE),
  VISIT_NOTES_NEW VARCHAR2(1000 BYTE),
  VISIT_TASKS_OLD VARCHAR2(1000 BYTE),
  VISIT_TASKS_NEW VARCHAR2(1000 BYTE)
);
/

CREATE OR REPLACE PACKAGE PKG_AUDIT IS

  FUNCTION GET_CHANGES_PAGING (v_host_array STRING_ARRAY, v_order_by_col VARCHAR2, v_order_by_dir VARCHAR2, v_page_from NUMBER, v_page_to NUMBER) RETURN SYS_REFCURSOR;
  FUNCTION GET_CHANGES (v_be_id VARCHAR2, v_sk NUMBER) RETURN SYS_REFCURSOR;
  FUNCTION INSERT_DATA_POINTS (v_data_point VARCHAR2, v_from_val VARCHAR2, v_to_val VARCHAR2) RETURN NUMBER;
  FUNCTION INSERT_CHANGES (v_be_id VARCHAR2, v_sk NUMBER, v_audit_changes AUDIT_CHANGED_T) RETURN NUMBER;
  FUNCTION INSERT_EMPL_STATUS_HIST (v_empl_status_hist AUDIT_EMPL_STATUS_HIST_T) RETURN NUMBER;
  FUNCTION INSERT_AUDIT_RECORD (v_audit_record AUDIT_EMPL_STATUS_HIST_T) RETURN NUMBER;
  FUNCTION INSERT_AUDIT_VISIT_HIST (v_audit_visit_hist AUDIT_VISIT_HIST_T) RETURN NUMBER;
  FUNCTION INSERT_AUDIT_ITEM (v_curr_tmstp DATE, v_user_guid VARCHAR2, v_audit_host VARCHAR2, v_item VARCHAR2, v_old_val VARCHAR2, v_new_val VARCHAR2) RETURN NUMBER;
  FUNCTION INSERT_STAFF_APP_AUDIT (v_app_audit_lst_t APP_AUDIT_LST_T) RETURN NUMBER;

END PKG_AUDIT;
/

CREATE OR REPLACE PACKAGE BODY PKG_AUDIT IS

  FUNCTION INSERT_AUDIT_RECORD (v_audit_record AUDIT_EMPL_STATUS_HIST_T) RETURN NUMBER
  AS

    l_sk NUMBER(38,0);

    BEGIN

      INSERT INTO APP_AUDIT (APP_AUDIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,USER_GUID,AUDIT_HOST,DATA_SEC_CLAS_ID,APP_DATA_STRUC_ELT_VAL,AUDIT_DATA_STRUC_WRITE_IND)
        VALUES (
          APP_AUDIT_SEQ.NEXTVAL,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP,
          v_audit_record.USER_GUID,
          NULL,
          'ForcedLogout',
          NULL,
          1
        );

      RETURN 1;

    END INSERT_AUDIT_RECORD;

  FUNCTION INSERT_EMPL_STATUS_HIST (v_empl_status_hist AUDIT_EMPL_STATUS_HIST_T) RETURN NUMBER
  AS

    l_sk NUMBER(38,0);

    BEGIN

      -- INSERT EFFECTIVE_DATE
      INSERT INTO APP_AUDIT (APP_AUDIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,USER_GUID,AUDIT_HOST,DATA_SEC_CLAS_ID,APP_DATA_STRUC_ELT_VAL,AUDIT_DATA_STRUC_WRITE_IND)
        VALUES (
          APP_AUDIT_SEQ.NEXTVAL,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP,
          v_empl_status_hist.USER_GUID,
          v_empl_status_hist.AUDIT_HOST,
          'EffectiveDate',
          TO_CHAR(v_empl_status_hist.EFFECTIVE_DATE, 'YYYY-MM-DD HH24:MI:SS'),
          1
        );

      -- INSERT STATUS
      INSERT INTO APP_AUDIT (APP_AUDIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,USER_GUID,AUDIT_HOST,DATA_SEC_CLAS_ID,APP_DATA_STRUC_ELT_VAL,AUDIT_DATA_STRUC_WRITE_IND)
        VALUES (
          APP_AUDIT_SEQ.NEXTVAL,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP,
          v_empl_status_hist.USER_GUID,
          v_empl_status_hist.AUDIT_HOST,
          'Status',
          v_empl_status_hist.STATUS,
          1
        );

      -- INSERT REASON_CODE
      INSERT INTO APP_AUDIT (APP_AUDIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,USER_GUID,AUDIT_HOST,DATA_SEC_CLAS_ID,APP_DATA_STRUC_ELT_VAL,AUDIT_DATA_STRUC_WRITE_IND)
        VALUES (
          APP_AUDIT_SEQ.NEXTVAL,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP,
          v_empl_status_hist.USER_GUID,
          v_empl_status_hist.AUDIT_HOST,
          'ReasonCode',
          v_empl_status_hist.REASON_CODE,
          1
        );

      -- INSERT NOTES
      INSERT INTO APP_AUDIT (APP_AUDIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,USER_GUID,AUDIT_HOST,DATA_SEC_CLAS_ID,APP_DATA_STRUC_ELT_VAL,AUDIT_DATA_STRUC_WRITE_IND)
        VALUES (
          APP_AUDIT_SEQ.NEXTVAL,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP,
          v_empl_status_hist.USER_GUID,
          v_empl_status_hist.AUDIT_HOST,
          'Notes',
          v_empl_status_hist.NOTES,
          1
        );

      -- INSERT MODIFIED
      INSERT INTO APP_AUDIT (APP_AUDIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,USER_GUID,AUDIT_HOST,DATA_SEC_CLAS_ID,APP_DATA_STRUC_ELT_VAL,AUDIT_DATA_STRUC_WRITE_IND)
        VALUES (
          APP_AUDIT_SEQ.NEXTVAL,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP,
          v_empl_status_hist.USER_GUID,
          v_empl_status_hist.AUDIT_HOST,
          'Modified',
          TO_CHAR(v_empl_status_hist.MODIFIED, 'YYYY-MM-DD HH24:MI:SS'),
          1
        );

      RETURN 1;

    END INSERT_EMPL_STATUS_HIST;

  FUNCTION INSERT_DATA_POINTS (v_data_point VARCHAR2, v_from_val VARCHAR2, v_to_val VARCHAR2) RETURN NUMBER
  AS

    l_sk NUMBER(38,0);

    BEGIN

      l_sk := APP_DATA_STRUC_SEQ.NEXTVAL;

      INSERT INTO APP_DATA_STRUC (APP_DATA_STRUC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,LGCL_ELT_TYP_NAME,LGCL_ELT_NAME,PHYS_ELT_NAME)
        VALUES (
          l_sk,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP,
          v_data_point,
          v_from_val,
          v_to_val
        );

      RETURN l_sk;

    END INSERT_DATA_POINTS;

  FUNCTION INSERT_CHANGES (v_be_id VARCHAR2, v_sk NUMBER, v_audit_changes AUDIT_CHANGED_T) RETURN NUMBER
  AS

    l_audit_host_key VARCHAR2(50 BYTE);

    l_ds_sk NUMBER(38,0);
    l_sk NUMBER(38,0);

    BEGIN

      l_ds_sk := PKG_AUDIT.INSERT_DATA_POINTS(v_audit_changes.DATA_POINT, v_audit_changes.CHANGED_FROM, v_audit_changes.CHANGED_TO);
      l_sk := APP_AUDIT_SEQ.NEXTVAL;

      l_audit_host_key := v_be_id || '|' || v_sk;

      INSERT INTO APP_AUDIT (APP_AUDIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,USER_GUID,AUDIT_HOST,APP_DATA_STRUC_SK,AUDIT_DATA_STRUC_WRITE_IND)
        VALUES (
          l_sk,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP,
          v_audit_changes.USER_GUID,
          l_audit_host_key,
          l_ds_sk,
          1);

      RETURN l_sk;

    END INSERT_CHANGES;

  FUNCTION GET_CHANGES (v_be_id VARCHAR2, v_sk NUMBER) RETURN SYS_REFCURSOR
  AS
    result_Cursor SYS_REFCURSOR;

    l_audit_host_key VARCHAR2(50 BYTE);

    BEGIN

      l_audit_host_key := v_be_id || '|' || v_sk;

      dbms_output.put_line('INFO: GET_CHANGES: l_audit_host_key: [' || l_audit_host_key || ']');

      OPEN result_Cursor FOR
      SELECT T2.USER_NAME,T2.USER_FIRST_NAME,T2.USER_LAST_NAME,
                T3.LGCL_ELT_TYP_NAME AS DATA_POINT,
                T3.LGCL_ELT_NAME AS CHANGED_FROM,
                T3.PHYS_ELT_NAME AS CHANGED_TO,
                T1.REC_CREATE_TMSTP AS CHANGED_ON
        FROM APP_AUDIT T1
        LEFT JOIN (SELECT USER_GUID,USER_NAME,USER_FIRST_NAME,USER_LAST_NAME
          FROM APP_USER) T2
        ON T1.USER_GUID = T2.USER_GUID
        LEFT JOIN (SELECT APP_DATA_STRUC_SK,LGCL_ELT_TYP_NAME,LGCL_ELT_NAME,PHYS_ELT_NAME
          FROM APP_DATA_STRUC) T3
        ON T1.APP_DATA_STRUC_SK = T3.APP_DATA_STRUC_SK
        WHERE AUDIT_HOST = l_audit_host_key
        ORDER BY CHANGED_ON DESC;

      RETURN result_Cursor;

      EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RETURN NULL;

      WHEN OTHERS THEN
        dbms_output.put_line('ERROR: GET_CHANGES: UNHANDLED EXCEPTION!');

    END GET_CHANGES;

  FUNCTION GET_CHANGES_PAGING (v_host_array STRING_ARRAY, v_order_by_col VARCHAR2, v_order_by_dir VARCHAR2, v_page_from NUMBER, v_page_to NUMBER) RETURN SYS_REFCURSOR
  AS
    result_Cursor SYS_REFCURSOR;

    l_sql CLOB;

    l_host_array_sel VARCHAR2(100 BYTE);

    l_cursor_id NUMBER;

    l_return NUMBER;

    BEGIN

      l_host_array_sel := '(SELECT column_value FROM TABLE(:b_host_array))';

      -- Dynamic SQL statement with placeholder:
      l_sql := 'SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (
                  SELECT * FROM (

                      (SELECT T2.USER_NAME,T2.USER_FIRST_NAME,T2.USER_LAST_NAME,
                                T3.LGCL_ELT_TYP_NAME AS DATA_POINT,
                                T3.LGCL_ELT_NAME AS CHANGED_FROM,
                                T3.PHYS_ELT_NAME AS CHANGED_TO,
                                T1.REC_CREATE_TMSTP AS CHANGED_ON
                        FROM APP_AUDIT T1
                        LEFT JOIN (SELECT USER_GUID,USER_NAME,USER_FIRST_NAME,USER_LAST_NAME
                          FROM APP_USER) T2
                        ON T1.USER_GUID = T2.USER_GUID
                        LEFT JOIN (SELECT APP_DATA_STRUC_SK,LGCL_ELT_TYP_NAME,LGCL_ELT_NAME,PHYS_ELT_NAME
                          FROM APP_DATA_STRUC) T3
                        ON T1.APP_DATA_STRUC_SK = T3.APP_DATA_STRUC_SK
                        WHERE AUDIT_HOST IN __AUDIT_HOST__
                        ORDER BY __ORD_BY_COL__ __DIRECTION__)
                  )

                ) R1)

                WHERE ROW_NUMBER BETWEEN :b_min_row AND :b_max_row';

        l_sql := REPLACE(l_sql, '__AUDIT_HOST__', l_host_array_sel);
        l_sql := REPLACE(l_sql, '__ORD_BY_COL__', v_order_by_col);
        l_sql := REPLACE(l_sql, '__DIRECTION__', v_order_by_dir);

      l_cursor_id := DBMS_SQL.OPEN_CURSOR;

      DBMS_SQL.PARSE(l_cursor_id, l_sql, DBMS_SQL.NATIVE);

      DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_host_array', v_host_array);
      DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_min_row', v_page_from);
      DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':b_max_row', v_page_to);

      l_return := DBMS_SQL.EXECUTE(l_cursor_id);

      dbms_output.put_line('INFO: GET_CHANGES_PAGING: l_return = [' || l_return || ']');

      result_Cursor := DBMS_SQL.TO_REFCURSOR(l_cursor_id);

      RETURN result_Cursor;

      EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RETURN NULL;

      WHEN OTHERS THEN
        dbms_output.put_line('ERROR: GET_CHANGES_PAGING: UNHANDLED EXCEPTION!');

    END GET_CHANGES_PAGING;

  -------------------------------------------------------------
  FUNCTION INSERT_AUDIT_VISIT_HIST (v_audit_visit_hist AUDIT_VISIT_HIST_T) RETURN NUMBER
  AS
    l_curr_tmstp DATE;
    l_ret NUMBER;

    BEGIN
      l_curr_tmstp := CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP) AS DATE);
      l_ret := 0;
      
      -- INSERT REASON_CODE
      INSERT INTO APP_AUDIT (APP_AUDIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,USER_GUID,AUDIT_HOST,DATA_SEC_CLAS_ID,APP_DATA_STRUC_ELT_VAL,AUDIT_DATA_STRUC_WRITE_IND)
        VALUES (
          APP_AUDIT_SEQ.NEXTVAL,
          l_curr_tmstp,
          l_curr_tmstp,
          v_audit_visit_hist.USER_GUID,
          v_audit_visit_hist.AUDIT_HOST,
          'ReasonCode',
          v_audit_visit_hist.REASON_CODE,
          1
        );
    
      -- INSERT NOTES
      INSERT INTO APP_AUDIT (APP_AUDIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,USER_GUID,AUDIT_HOST,DATA_SEC_CLAS_ID,APP_DATA_STRUC_ELT_VAL,AUDIT_DATA_STRUC_WRITE_IND)
        VALUES (
          APP_AUDIT_SEQ.NEXTVAL,
          l_curr_tmstp,
          l_curr_tmstp,
          v_audit_visit_hist.USER_GUID,
          v_audit_visit_hist.AUDIT_HOST,
          'Notes',
          v_audit_visit_hist.NOTES,
          1
        );
    
      -- PatientID
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'PatientID', v_audit_visit_hist.PT_ID_OLD, v_audit_visit_hist.PT_ID_NEW);
      
      -- PatientName
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'PatientName', v_audit_visit_hist.PT_NAME_OLD, v_audit_visit_hist.PT_NAME_NEW);
      
      -- StaffID
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'StaffID', v_audit_visit_hist.STAFF_ID_OLD, v_audit_visit_hist.STAFF_ID_NEW);
      
      -- StaffName
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'StaffName', v_audit_visit_hist.STAFF_NAME_OLD, v_audit_visit_hist.STAFF_NAME_NEW);
      
      -- Service
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Service', v_audit_visit_hist.SERVICE_OLD, v_audit_visit_hist.SERVICE_NEW);
      
      -- Schedule In
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Schedule In', v_audit_visit_hist.SCHD_IN_OLD, v_audit_visit_hist.SCHD_IN_NEW);
      
      -- Schedule Out
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Schedule Out', v_audit_visit_hist.SCHD_OUT_OLD, v_audit_visit_hist.SCHD_OUT_NEW);
      
      -- Schedule Hours
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Schedule Hours', v_audit_visit_hist.SCHD_HRS_OLD, v_audit_visit_hist.SCHD_HRS_NEW);
      
      -- Call In
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Call In', v_audit_visit_hist.CALL_IN_OLD, v_audit_visit_hist.CALL_IN_NEW);
      
      -- Call Out
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Call Out', v_audit_visit_hist.CALL_OUT_OLD, v_audit_visit_hist.CALL_OUT_NEW);
      
      -- Call Hours
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Call Hours', v_audit_visit_hist.CALL_HRS_OLD, v_audit_visit_hist.CALL_HRS_NEW);
      
      -- Adjusted In
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Adjusted In', v_audit_visit_hist.ADJ_IN_OLD, v_audit_visit_hist.ADJ_IN_NEW);
      
      -- Adjusted Out
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Adjusted Out', v_audit_visit_hist.ADJ_OUT_OLD, v_audit_visit_hist.ADJ_OUT_NEW);
      
      -- Adjusted Hours
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Adjusted Hours', v_audit_visit_hist.ADJ_HRS_OLD, v_audit_visit_hist.ADJ_HRS_NEW);
      
      -- Pay Hours
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Pay Hours', v_audit_visit_hist.PAY_HRS_OLD, v_audit_visit_hist.PAY_HRS_NEW);
      
      -- Bill Hours
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Bill Hours', v_audit_visit_hist.BILL_HRS_OLD, v_audit_visit_hist.BILL_HRS_NEW);
      
      -- Status
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Status', v_audit_visit_hist.STATUS_OLD, v_audit_visit_hist.STATUS_NEW);
      
      -- Verified By Schedule
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Verified By Schedule', v_audit_visit_hist.VERIFY_BY_SCHD_OLD, v_audit_visit_hist.VERIFY_BY_SCHD_NEW);
      
      -- Pay By Schedule
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Pay By Schedule', v_audit_visit_hist.PAY_BY_SCHD_OLD, v_audit_visit_hist.PAY_BY_SCHD_NEW);
      
      -- Overtime/Absence Hours
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Overtime/Absence Hours', v_audit_visit_hist.OT_ABS_HOURS_OLD, v_audit_visit_hist.OT_ABS_HOURS_NEW);
      
      -- Approved
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Approved', v_audit_visit_hist.APPRV_OLD, v_audit_visit_hist.APPRV_NEW);
      
      -- Visit Exceptions
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Visit Exceptions', v_audit_visit_hist.VISIT_EXCEPTIONS_OLD, v_audit_visit_hist.VISIT_EXCEPTIONS_NEW);
      
      -- Visit Notes
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Visit Notes', v_audit_visit_hist.VISIT_NOTES_OLD, v_audit_visit_hist.VISIT_NOTES_NEW);
      
      -- Visit Tasks
      l_ret := l_ret + PKG_AUDIT.INSERT_AUDIT_ITEM(l_curr_tmstp, v_audit_visit_hist.USER_GUID, v_audit_visit_hist.AUDIT_HOST, 'Visit Tasks', v_audit_visit_hist.VISIT_TASKS_OLD, v_audit_visit_hist.VISIT_TASKS_NEW);
      
      RETURN l_ret;
      
    END INSERT_AUDIT_VISIT_HIST;

  -------------------------------------------------------------
  FUNCTION INSERT_AUDIT_ITEM (v_curr_tmstp DATE, v_user_guid VARCHAR2, v_audit_host VARCHAR2, v_item VARCHAR2, v_old_val VARCHAR2, v_new_val VARCHAR2) RETURN NUMBER
  AS
    BEGIN
      
      IF ((v_old_val IS NULL OR v_old_val = '') AND (v_new_val IS NULL OR v_new_val = '')) OR (v_old_val IS NOT NULL AND v_new_val IS NOT NULL AND v_old_val = v_new_val) THEN
        -- no changes, don't insert into audit table
        RETURN 0;
      
      ELSE
        -- INSERT OLD value (ChangedFrom)
        INSERT INTO APP_AUDIT (APP_AUDIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,USER_GUID,AUDIT_HOST,DATA_SEC_CLAS_ID,APP_DATA_STRUC_ELT_VAL,AUDIT_DATA_STRUC_READ_IND)
          VALUES (
            APP_AUDIT_SEQ.NEXTVAL,
            v_curr_tmstp,
            v_curr_tmstp,
            v_user_guid,
            v_audit_host,
            v_item,
            v_old_val,
            1
          );
        
        -- INSERT NEW value (ChangedTo)
        INSERT INTO APP_AUDIT (APP_AUDIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,USER_GUID,AUDIT_HOST,DATA_SEC_CLAS_ID,APP_DATA_STRUC_ELT_VAL,AUDIT_DATA_STRUC_WRITE_IND)
          VALUES (
            APP_AUDIT_SEQ.NEXTVAL,
            v_curr_tmstp,
            v_curr_tmstp,
            v_user_guid,
            v_audit_host,
            v_item,
            v_new_val,
            1
          );
        
        RETURN 1;

      END IF;    
            
    END INSERT_AUDIT_ITEM;

-------------------------------------------------------------
FUNCTION INSERT_STAFF_APP_AUDIT (v_app_audit_lst_t APP_AUDIT_LST_T) RETURN NUMBER
AS
	BEGIN

	  FORALL idx IN v_app_audit_lst_t.FIRST .. v_app_audit_lst_t.LAST
		  INSERT INTO APP_AUDIT (APP_AUDIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,USER_GUID,AUDIT_HOST,DATA_SEC_CLAS_ID,APP_DATA_STRUC_ELT_VAL,AUDIT_DATA_STRUC_READ_IND,AUDIT_DATA_STRUC_WRITE_IND)
			VALUES (
			  APP_AUDIT_SEQ.NEXTVAL,
			  v_app_audit_lst_t(idx).REC_CREATE_TMSTP,
			  v_app_audit_lst_t(idx).REC_UPDATE_TMSTP,
			  v_app_audit_lst_t(idx).USER_GUID,
			  v_app_audit_lst_t(idx).AUDIT_HOST,
			  v_app_audit_lst_t(idx).DATA_SEC_CLAS_ID,
			  v_app_audit_lst_t(idx).APP_DATA_STRUC_ELT_VAL,
			  v_app_audit_lst_t(idx).AUDIT_DATA_STRUC_READ_IND,
			  v_app_audit_lst_t(idx).AUDIT_DATA_STRUC_WRITE_IND
			);

	  RETURN 1;

	END INSERT_STAFF_APP_AUDIT;
	
END PKG_AUDIT;
/

