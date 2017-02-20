CREATE OR REPLACE PACKAGE PKG_HIST IS

  /** Auth **/
  FUNCTION updateAuth(AUTH_VAR AUTH_T) RETURN NUMBER;

  /** Order **/
  FUNCTION updateOrd(ORD_VAR ORD_T) RETURN NUMBER;

  /** Patient **/
  FUNCTION updatePatient(v_patient PT_T) RETURN NUMBER;

  /** Staff **/
  FUNCTION updateStaff(v_staff STAFF_T) RETURN NUMBER;

  -- For Order, v_auth_qlfr = 'ORDER'. For Authorizations, v_auth_qlfr = 'AUTHORIZATION'
  FUNCTION updateAuthSkForRelatedTables(v_old_auth_sk NUMBER, v_new_auth_sk NUMBER, v_auth_qlfr VARCHAR2) RETURN NUMBER;

  FUNCTION changeQlfrFromOrderToAuth(v_old_auth_sk NUMBER, v_new_auth_sk NUMBER) RETURN NUMBER;

END PKG_HIST;
/

CREATE OR REPLACE PACKAGE BODY PKG_HIST IS

  FUNCTION updateAuth(AUTH_VAR AUTH_T) RETURN NUMBER
  AS

    lt_auth_sk AUTH.AUTH_SK%TYPE;
    lt_curr_rec_ind AUTH.CURR_REC_IND%TYPE;
    lt_change_version_id AUTH.CHANGE_VERSION_ID%TYPE;
    lt_rec_create_tmstp AUTH.REC_CREATE_TMSTP%TYPE;
    lt_rec_term_tmstp AUTH.REC_TERM_TMSTP%TYPE;

    l_source_auth AUTH_T;
    l_result NUMBER;
    l_sk_val AUTH.AUTH_SK%TYPE;

    l_err_msg CLOB;

    v_current_date_utc DATE;

    BEGIN

      l_source_auth := AUTH_VAR;
      lt_auth_sk := l_source_auth.AUTH_SK;
      lt_curr_rec_ind := l_source_auth.CURR_REC_IND;

      v_current_date_utc := CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP) AS DATE);

      SELECT CHANGE_VERSION_ID,REC_CREATE_TMSTP,REC_TERM_TMSTP
      INTO lt_change_version_id,lt_rec_create_tmstp,lt_rec_term_tmstp
      FROM AUTH
      WHERE AUTH_SK = l_source_auth.AUTH_SK;

      IF TO_CHAR(lt_rec_term_tmstp, 'YYYY-MM-DD') <> '9999-12-31' THEN
        l_err_msg := 'Can not perform historical update for [AUTH_SK=' || l_source_auth.AUTH_SK || '] because it is already marked as logically deleted. [REC_TERM_TMSTP='
                         || lt_rec_term_tmstp || '].';
        PKG_EXCEPTIONS.REC_ALREADY_DEL_ERR(l_err_msg);
      ELSE
        IF lt_change_version_id <> l_source_auth.CHANGE_VERSION_ID THEN
          l_err_msg := 'TARGET.CHANGE_VERSION_ID = ' || lt_change_version_id || '; SOURCE.CHANGE_VERSION_ID = ' || l_source_auth.CHANGE_VERSION_ID;
          PKG_EXCEPTIONS.VERSION_MISMATCH_ERR(l_err_msg);
        ELSE
          -- Historical records are not deleted but only marked as deleted
          IF TO_CHAR(lt_rec_create_tmstp, 'YYYY-MM-DD') = TO_CHAR(v_current_date_utc, 'YYYY-MM-DD') THEN
            dbms_output.put_line ('Record is modified on the same day [' || lt_rec_create_tmstp || ']: CURR_REC_IND = 0');
            lt_curr_rec_ind := 0;
          END IF;

          l_source_auth.AUTH_SK := NULL;
          l_source_auth.REC_CREATE_TMSTP := v_current_date_utc;
          l_source_auth.REC_UPDATE_TMSTP := v_current_date_utc;

          l_sk_val := PKG_AUTH.insertAuth(l_source_auth);

          UPDATE AUTH SET REC_TERM_TMSTP = v_current_date_utc,
                          REC_UPDATE_TMSTP = v_current_date_utc,
                          CURR_REC_IND = lt_curr_rec_ind,
                          CHANGE_REASON_MEMO = 'PKG_HIST: updateAuth: New [AUTH_SK=' || l_sk_val || ']'
          WHERE AUTH_SK=lt_auth_sk;

          RETURN l_sk_val;
        END IF;
      END IF;

      RETURN -1;

    END updateAuth;

  -------------------------------------------------
  FUNCTION updateOrd(ORD_VAR ORD_T) RETURN NUMBER
  AS
 
    v_old_ord_sk ORD.ORD_SK%TYPE;
    v_curr_rec_ind ORD.CURR_REC_IND%TYPE;
    v_change_version_id ORD.CHANGE_VERSION_ID%TYPE;
    v_rec_create_tmstp ORD.REC_CREATE_TMSTP%TYPE;
    v_rec_term_tmstp ORD.REC_TERM_TMSTP%TYPE;

    v_source_ord ORD_T;
    v_result NUMBER;
    v_new_ord_sk ORD.ORD_SK%TYPE;

    v_err_msg CLOB;

    v_current_date_utc DATE;
    
    BEGIN

      v_source_ord := ORD_VAR;
      v_old_ord_sk := v_source_ord.ORD_SK;
      v_curr_rec_ind := v_source_ord.CURR_REC_IND;
      
      v_current_date_utc := CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP) AS DATE);

      SELECT CHANGE_VERSION_ID, REC_CREATE_TMSTP, REC_TERM_TMSTP
      INTO v_change_version_id, v_rec_create_tmstp, v_rec_term_tmstp
      FROM ORD
      WHERE ORD_SK = v_source_ord.ORD_SK;

      IF TO_CHAR(v_rec_term_tmstp, 'YYYY-MM-DD') <> '9999-12-31' THEN
        v_err_msg := 'Can not perform historical update for [ORD_SK=' || v_source_ord.ORD_SK || '] because it is already marked as logically deleted. [REC_TERM_TMSTP='
                         || v_rec_term_tmstp || '].';
        PKG_EXCEPTIONS.REC_ALREADY_DEL_ERR(v_err_msg);
      ELSE
        IF v_change_version_id <> v_source_ord.CHANGE_VERSION_ID THEN
          v_err_msg := 'TARGET.CHANGE_VERSION_ID = ' || v_change_version_id || '; SOURCE.CHANGE_VERSION_ID = ' || v_source_ord.CHANGE_VERSION_ID;
          PKG_EXCEPTIONS.VERSION_MISMATCH_ERR(v_err_msg);
        ELSE
          -- Historical records are not deleted but only marked as deleted
          IF TO_CHAR(v_rec_create_tmstp, 'YYYY-MM-DD') = TO_CHAR(v_current_date_utc, 'YYYY-MM-DD') THEN
            dbms_output.put_line ('Record is modified on the same day [' || v_rec_create_tmstp || ']: CURR_REC_IND = 0');
            v_curr_rec_ind := 0;
          END IF;

          v_source_ord.ORD_SK := NULL;
          v_source_ord.REC_CREATE_TMSTP := v_current_date_utc;
          v_source_ord.REC_UPDATE_TMSTP := v_current_date_utc;

          v_new_ord_sk := PKG_AUTH.insertOrd(v_source_ord);

          UPDATE ORD SET REC_TERM_TMSTP = v_current_date_utc,
                          REC_UPDATE_TMSTP = v_current_date_utc,
                          CURR_REC_IND = v_curr_rec_ind,
                          CHANGE_REASON_MEMO = 'PKG_HIST: updateOrd: New [ORD_SK=' || v_new_ord_sk || ']'
          WHERE ORD_SK = v_old_ord_sk;

          RETURN v_new_ord_sk;
        END IF;
      END IF;

      RETURN -1;

    END updateOrd;

  -------------------------------------------------
  -- For Order, v_auth_qlfr = 'ORDER'. For Authorizations, v_auth_qlfr = 'AUTHORIZATION'
  FUNCTION updateAuthSkForRelatedTables(v_old_auth_sk NUMBER, v_new_auth_sk NUMBER, v_auth_qlfr VARCHAR2) RETURN NUMBER
  AS

    v_result NUMBER;
    v_current_date_utc DATE;

    BEGIN
      
      v_result := 0;
      v_current_date_utc := CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP) AS DATE);
      
      -- update new SK for AUTH_SVC or ORD_SVC
      IF v_auth_qlfr = 'AUTHORIZATION' THEN
        
        UPDATE AUTH_SVC
        SET
          REC_UPDATE_TMSTP = v_current_date_utc,
          AUTH_SK = v_new_auth_sk,
          CHANGE_VERSION_ID = AUTH_SVC_VER.NEXTVAL
        WHERE
          AUTH_SK = v_old_auth_sk;

      ELSIF v_auth_qlfr = 'ORDER' THEN
        
        UPDATE ORD_SVC
        SET
          REC_UPDATE_TMSTP = v_current_date_utc,
          ORD_SK = v_new_auth_sk,
          CHANGE_VERSION_ID = ORD_SVC_VER.NEXTVAL
        WHERE
          ORD_SK = v_old_auth_sk;
      
      END IF;

      v_result := v_result + SQL%ROWCOUNT;

      -- update new SK for SCHED_AUTH
      UPDATE SCHED_AUTH
      SET
        REC_UPDATE_TMSTP = v_current_date_utc,
        AUTH_SK = v_new_auth_sk,
        CHANGE_VERSION_ID = SCHED_AUTH_VER.NEXTVAL
      WHERE
        AUTH_SK = v_old_auth_sk
        AND UPPER(AUTH_QLFR) = UPPER(v_auth_qlfr);

      v_result := v_result + SQL%ROWCOUNT;

      -- update new SK for SCHED_EVNT
      UPDATE SCHED_EVNT
      SET
        REC_UPDATE_TMSTP = v_current_date_utc,
        AUTH_SK = v_new_auth_sk,
        CHANGE_VERSION_ID = SCHED_EVNT_VER.NEXTVAL
      WHERE
        AUTH_SK = v_old_auth_sk
        AND UPPER(AUTH_QLFR) = UPPER(v_auth_qlfr)
        AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1;

      v_result := v_result + SQL%ROWCOUNT;

      -- update new SK for SCHED_EVNT_AUTH
      UPDATE SCHED_EVNT_AUTH
      SET
        REC_UPDATE_TMSTP = v_current_date_utc,
        AUTH_SK = v_new_auth_sk,
        CHANGE_VERSION_ID = SCHED_EVNT_AUTH_VER.NEXTVAL
      WHERE
        AUTH_SK = v_old_auth_sk
        AND UPPER(AUTH_QLFR) = UPPER(v_auth_qlfr);

      v_result := v_result + SQL%ROWCOUNT;
      
      -- update new SK for VISIT_AUTH
      UPDATE VISIT_AUTH
      SET
        REC_UPDATE_TMSTP = v_current_date_utc,
        AUTH_SK = v_new_auth_sk,
        CHANGE_VERSION_ID = VISIT_AUTH_VER.NEXTVAL
      WHERE
        AUTH_SK = v_old_auth_sk
        AND UPPER(AUTH_QLFR) = UPPER(v_auth_qlfr);

      v_result := v_result + SQL%ROWCOUNT;
      
      -- return number of rows updated
      RETURN v_result;
      
      EXCEPTION
        WHEN OTHERS THEN
          RETURN -1;
      
    END updateAuthSkForRelatedTables;

  -------------------------------------------------
  FUNCTION changeQlfrFromOrderToAuth(v_old_auth_sk NUMBER, v_new_auth_sk NUMBER) RETURN NUMBER
  AS

    v_result NUMBER;
    v_current_date_utc DATE;
    v_ord_qlfr VARCHAR2(30);
    v_auth_qlfr VARCHAR2(30);

    BEGIN

      v_result := 0;
      v_current_date_utc := CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP) AS DATE);
      v_ord_qlfr := 'ORDER';
      v_auth_qlfr := 'AUTHORIZATION';

      -- update new SK for SCHED_AUTH
      UPDATE SCHED_AUTH
      SET
        REC_UPDATE_TMSTP = v_current_date_utc,
        AUTH_SK = v_new_auth_sk,
        CHANGE_VERSION_ID = SCHED_AUTH_VER.NEXTVAL,
        AUTH_QLFR = v_auth_qlfr
      WHERE
        AUTH_SK = v_old_auth_sk
        AND UPPER(AUTH_QLFR) = v_ord_qlfr;

      v_result := v_result + SQL%ROWCOUNT;

      -- update new SK for SCHED_EVNT
      UPDATE SCHED_EVNT
      SET
        REC_UPDATE_TMSTP = v_current_date_utc,
        AUTH_SK = v_new_auth_sk,
        CHANGE_VERSION_ID = SCHED_EVNT_VER.NEXTVAL,
        AUTH_QLFR = v_auth_qlfr
      WHERE
        AUTH_SK = v_old_auth_sk
        AND UPPER(AUTH_QLFR) = v_ord_qlfr
        AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1;

      v_result := v_result + SQL%ROWCOUNT;

      -- update new SK for SCHED_EVNT_AUTH
      UPDATE SCHED_EVNT_AUTH
      SET
        REC_UPDATE_TMSTP = v_current_date_utc,
        AUTH_SK = v_new_auth_sk,
        CHANGE_VERSION_ID = SCHED_EVNT_AUTH_VER.NEXTVAL,
        AUTH_QLFR = v_auth_qlfr
      WHERE
        AUTH_SK = v_old_auth_sk
        AND UPPER(AUTH_QLFR) = v_ord_qlfr;

      v_result := v_result + SQL%ROWCOUNT;

      -- update new SK for VISIT_AUTH
      UPDATE VISIT_AUTH
      SET
        REC_UPDATE_TMSTP = v_current_date_utc,
        AUTH_SK = v_new_auth_sk,
        CHANGE_VERSION_ID = VISIT_AUTH_VER.NEXTVAL,
        AUTH_QLFR = v_auth_qlfr
      WHERE
        AUTH_SK = v_old_auth_sk
        AND UPPER(AUTH_QLFR) = v_ord_qlfr;

      v_result := v_result + SQL%ROWCOUNT;
      
      -- return number of rows updated
      RETURN v_result;
      
      EXCEPTION
        WHEN OTHERS THEN
          RETURN -1;

    END changeQlfrFromOrderToAuth;

  FUNCTION updatePatient(v_patient PT_T) RETURN NUMBER
  AS

    l_patient_sk PT.PT_SK%TYPE;
    l_curr_rec_ind PT.CURR_REC_IND%TYPE;
    l_change_version_id PT.CHANGE_VERSION_ID%TYPE;
    l_rec_create_tmstp PT.REC_CREATE_TMSTP%TYPE;
    l_rec_term_tmstp PT.REC_TERM_TMSTP%TYPE;
    l_rec_eff_tmstp PT.REC_EFF_TMSTP%TYPE;

    l_patient PT_T;
    l_result NUMBER;
    l_sk_val PT.PT_SK%TYPE;

    l_err_msg CLOB;

    l_current_date_utc DATE;

    BEGIN

      l_patient := v_patient;
      l_patient_sk := l_patient.PT_SK;
      l_curr_rec_ind := l_patient.CURR_REC_IND;

      l_current_date_utc := CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP) AS DATE);

      SELECT CHANGE_VERSION_ID,REC_CREATE_TMSTP,REC_TERM_TMSTP,REC_EFF_TMSTP
      INTO l_change_version_id,l_rec_create_tmstp,l_rec_term_tmstp,l_rec_eff_tmstp
      FROM PT
      WHERE PT_SK = l_patient.PT_SK;

      IF TO_CHAR(l_rec_term_tmstp, 'YYYY-MM-DD') <> '9999-12-31' THEN
        l_err_msg := 'Can not perform historical update for [PT_SK=' || l_patient.PT_SK || '] because it is already marked as logically deleted. [REC_TERM_TMSTP='
                         || l_rec_term_tmstp || '].';
        PKG_EXCEPTIONS.REC_ALREADY_DEL_ERR(l_err_msg);
      ELSE
        IF l_change_version_id <> l_patient.CHANGE_VERSION_ID THEN
          l_err_msg := 'TARGET.CHANGE_VERSION_ID = ' || l_change_version_id || '; SOURCE.CHANGE_VERSION_ID = ' || l_patient.CHANGE_VERSION_ID;
          PKG_EXCEPTIONS.VERSION_MISMATCH_ERR(l_err_msg);
        ELSE
          -- Historical records are not deleted but only marked as deleted
          IF TO_CHAR(l_rec_create_tmstp, 'YYYY-MM-DD') = TO_CHAR(l_current_date_utc, 'YYYY-MM-DD') THEN
            dbms_output.put_line ('Record is modified on the same day [' || l_rec_create_tmstp || ']: CURR_REC_IND = 0');
            l_curr_rec_ind := 0;
          END IF;

          l_patient.PT_SK := NULL;
          l_patient.REC_CREATE_TMSTP := l_current_date_utc;
          l_patient.REC_UPDATE_TMSTP := l_current_date_utc;
          l_patient.REC_EFF_TMSTP := l_rec_eff_tmstp; -- Do not allow user to change the effective date

          l_sk_val := PKG_PATIENT.insertPt(l_patient);

          UPDATE PT SET REC_TERM_TMSTP = l_current_date_utc,
                          REC_UPDATE_TMSTP = l_current_date_utc,
                          CURR_REC_IND = l_curr_rec_ind,
                          CHANGE_REASON_MEMO = 'PKG_HIST: insertPatient: New [PT_SK=' || l_sk_val || ']'
          WHERE PT_SK=l_patient_sk;

          RETURN l_sk_val;
        END IF;
      END IF;

      RETURN -1;

    END updatePatient;

  FUNCTION updateStaff(v_staff STAFF_T) RETURN NUMBER
  AS

    l_staff_sk STAFF.STAFF_SK%TYPE;
    l_curr_rec_ind STAFF.CURR_REC_IND%TYPE;
    l_change_version_id STAFF.CHANGE_VERSION_ID%TYPE;
    l_rec_create_tmstp STAFF.REC_CREATE_TMSTP%TYPE;
    l_rec_term_tmstp STAFF.REC_TERM_TMSTP%TYPE;
    l_rec_eff_tmstp STAFF.REC_EFF_TMSTP%TYPE;

    l_staff STAFF_T;
    l_result NUMBER;
    l_sk_val STAFF.STAFF_SK%TYPE;

    l_err_msg CLOB;

    l_current_date_utc DATE;

    BEGIN

      l_staff := v_staff;
      l_staff_sk := l_staff.STAFF_SK;
      l_curr_rec_ind := l_staff.CURR_REC_IND;

      l_current_date_utc := CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP) AS DATE);

      SELECT CHANGE_VERSION_ID,REC_CREATE_TMSTP,REC_TERM_TMSTP,REC_EFF_TMSTP
      INTO l_change_version_id,l_rec_create_tmstp,l_rec_term_tmstp,l_rec_eff_tmstp
      FROM STAFF
      WHERE STAFF_SK = l_staff.STAFF_SK;

      IF TO_CHAR(l_rec_term_tmstp, 'YYYY-MM-DD') <> '9999-12-31' THEN
        l_err_msg := 'Can not perform historical update for [STAFF_SK=' || l_staff.STAFF_SK || '] because it is already marked as logically deleted. [REC_TERM_TMSTP='
                         || l_rec_term_tmstp || '].';
        PKG_EXCEPTIONS.REC_ALREADY_DEL_ERR(l_err_msg);
      ELSE
        IF l_change_version_id <> l_staff.CHANGE_VERSION_ID THEN
          l_err_msg := 'TARGET.CHANGE_VERSION_ID = ' || l_change_version_id || '; SOURCE.CHANGE_VERSION_ID = ' || l_staff.CHANGE_VERSION_ID;
          PKG_EXCEPTIONS.VERSION_MISMATCH_ERR(l_err_msg);
        ELSE
          -- Historical records are not deleted but only marked as deleted
          IF TO_CHAR(l_rec_create_tmstp, 'YYYY-MM-DD') = TO_CHAR(l_current_date_utc, 'YYYY-MM-DD') THEN
            dbms_output.put_line ('Record is modified on the same day [' || l_rec_create_tmstp || ']: CURR_REC_IND = 0');
            l_curr_rec_ind := 0;
          END IF;

          l_staff.STAFF_SK := NULL;
          l_staff.REC_CREATE_TMSTP := l_current_date_utc;
          l_staff.REC_UPDATE_TMSTP := l_current_date_utc;
          l_staff.REC_EFF_TMSTP := l_rec_eff_tmstp; -- Do not allow user to change the effective date

          l_sk_val := PKG_STAFF.insertStaff(l_staff);

          UPDATE STAFF SET REC_TERM_TMSTP = l_current_date_utc,
                          REC_UPDATE_TMSTP = l_current_date_utc,
                          CURR_REC_IND = l_curr_rec_ind,
                          CHANGE_REASON_MEMO = 'PKG_HIST: insertStaff: New [STAFF_SK=' || l_sk_val || ']'
          WHERE STAFF_SK=l_staff_sk;

          RETURN l_sk_val;
        END IF;
      END IF;

      RETURN -1;

    END updateStaff;

END PKG_HIST;
/
