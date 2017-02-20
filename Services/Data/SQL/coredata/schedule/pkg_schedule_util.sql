CREATE OR REPLACE PACKAGE PKG_SCHEDULE_UTIL IS
  FUNCTION DELETE_SCHEDULES(v_sched_sk IN SCHED.SCHED_SK%TYPE, v_week_day_list IN WEEK_DAY_LIST) RETURN NUMBER;

  FUNCTION UPDATE_STAFF_ID_FOR_SCHED_EVNT(v_staff_id IN SCHED_EVNT.STAFF_ID%TYPE, v_sched_evnt_sk IN SCHED_EVNT.SCHED_EVNT_SK%TYPE, v_be_in IN SCHED_EVNT.BE_ID%TYPE) RETURN NUMBER;

  FUNCTION UPDATE_STAFF_ID_FOR_SCHED_SK(v_staff_id IN SCHED_EVNT.STAFF_ID%TYPE, v_sched_sk IN SCHED_EVNT.SCHED_SK%TYPE, v_be_in IN SCHED_EVNT.BE_ID%TYPE) RETURN NUMBER;

  FUNCTION DELETE_ALL_VISITS_FOR_SCHED(v_shed_sk IN NUMBER, v_day_of_wk_lst IN NUMBER_ARRAY) RETURN NUMBER;

  FUNCTION UPDATE_VISIT_BY_SCHED_SK(v_visit IN VISIT_T, v_sched_sk IN SCHED_EVNT.SCHED_SK%TYPE) RETURN NUMBER;

  FUNCTION UPDATE_VISIT_BY_SCHED_EVNT_SK(v_visit IN VISIT_T, v_sched_evnt_sk IN VISIT.SCHED_EVNT_SK%TYPE) RETURN NUMBER;
END PKG_SCHEDULE_UTIL;
/

CREATE OR REPLACE PACKAGE BODY PKG_SCHEDULE_UTIL IS

  FUNCTION DELETE_SCHEDULES(v_sched_sk IN SCHED.SCHED_SK%TYPE, v_week_day_list IN WEEK_DAY_LIST) RETURN NUMBER
  AS
  BEGIN
    DECLARE rows_updated NUMBER;
    BEGIN
      FORALL idx IN v_week_day_list.FIRST .. v_week_day_list.LAST
      UPDATE SCHED_EVNT 
      SET REC_TERM_TMSTP = CURRENT_DATE, CURR_REC_IND = 0
      WHERE SCHED_SK = v_sched_sk AND TO_CHAR(SCHED_EVNT_START_DTIME, 'D') = v_week_day_list(idx);
      
      rows_updated := SQL%ROWCOUNT;
      IF (rows_updated > 0) THEN
        FORALL idx IN v_week_day_list.FIRST .. v_week_day_list.LAST
        DELETE FROM SCHED_RPT_DAY_OF_WEEK
        WHERE SCHED_SK = v_sched_sk AND DAY_OF_WEEK_NUM = v_week_day_list(idx);
        rows_updated := SQL%ROWCOUNT;
      END IF;
      
      RETURN rows_updated;

    EXCEPTION
      WHEN OTHERS THEN
        RETURN -1;
    END;
  
  END DELETE_SCHEDULES;

  ----------------------------------------------------------------------------------
  FUNCTION UPDATE_STAFF_ID_FOR_SCHED_EVNT(v_staff_id IN SCHED_EVNT.STAFF_ID%TYPE, v_sched_evnt_sk IN SCHED_EVNT.SCHED_EVNT_SK%TYPE, v_be_in IN SCHED_EVNT.BE_ID%TYPE) RETURN NUMBER
  AS
  BEGIN

    UPDATE SCHED_EVNT
    SET STAFF_ID = v_staff_id
    WHERE
      SCHED_EVNT_SK = v_sched_evnt_sk
      AND BE_ID = v_be_in
      AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1);

    RETURN SQL%ROWCOUNT;

  END UPDATE_STAFF_ID_FOR_SCHED_EVNT;

  ----------------------------------------------------------------------------------
  FUNCTION UPDATE_STAFF_ID_FOR_SCHED_SK(v_staff_id IN SCHED_EVNT.STAFF_ID%TYPE, v_sched_sk IN SCHED_EVNT.SCHED_SK%TYPE, v_be_in IN SCHED_EVNT.BE_ID%TYPE) RETURN NUMBER
  AS
  BEGIN

    UPDATE SCHED_EVNT
    SET STAFF_ID = v_staff_id
    WHERE
      SCHED_SK = v_sched_sk
      AND BE_ID = v_be_in
      AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1);

    RETURN SQL%ROWCOUNT;

  END UPDATE_STAFF_ID_FOR_SCHED_SK;

  ----------------------------------------------------------------------------------
  FUNCTION DELETE_ALL_VISITS_FOR_SCHED(v_shed_sk IN NUMBER, v_day_of_wk_lst IN NUMBER_ARRAY) RETURN NUMBER
  AS
    v_row_deleted NUMBER;
  BEGIN

    DELETE (SELECT * FROM VISIT JOIN SCHED_EVNT ON VISIT.SCHED_EVNT_SK = SCHED_EVNT.SCHED_EVNT_SK
                    WHERE SCHED_EVNT.SCHED_SK = v_shed_sk
                    AND TO_NUMBER(TO_CHAR(SCHED_EVNT_START_DTIME, 'D')) IN (SELECT * FROM TABLE(v_day_of_wk_lst)));

    v_row_deleted := SQL%ROWCOUNT;

    IF (v_row_deleted >= 0) THEN
      DELETE FROM SCHED_RPT_DAY_OF_WEEK
      WHERE
        DAY_OF_WEEK_NUM IN (SELECT * FROM TABLE(v_day_of_wk_lst))
        AND SCHED_SK = v_shed_sk;

      v_row_deleted := SQL%ROWCOUNT;
    END IF;

    RETURN v_row_deleted;

    EXCEPTION
      WHEN OTHERS THEN
        RETURN -1;

  END DELETE_ALL_VISITS_FOR_SCHED;

  ----------------------------------------------------------------------------------
  FUNCTION UPDATE_VISIT_BY_SCHED_SK(v_visit IN VISIT_T, v_sched_sk IN SCHED_EVNT.SCHED_SK%TYPE) RETURN NUMBER
  AS
    v_sql VARCHAR2(1024);
  BEGIN

    v_sql := 'UPDATE VISIT SET REC_UPDATE_TMSTP = :rec_update_tmstp';
    v_sql := v_sql || ', REC_UPDATED_BY = :rec_updated_by, CHANGE_REASON_MEMO = :change_reason_memo';

    --find out what change
    --this was an empty visit if its changing form schedule there are only certain things that can change.
    --right now we can only change staffId
    IF (v_visit.STAFF_ID IS NOT NULL) THEN
      v_sql := v_sql || ', STAFF_ID = :staff_id';
    END IF;

    v_sql := v_sql || ' WHERE VISIT.SCHED_EVNT_SK IN (SELECT SCHED_EVNT_SK FROM SCHED_EVNT WHERE SCHED_EVNT.SCHED_SK = :sched_sk)';

    IF (v_visit.STAFF_ID IS NOT NULL) THEN
      EXECUTE IMMEDIATE v_sql USING v_visit.REC_UPDATE_TMSTP, v_visit.REC_UPDATED_BY, v_visit.CHANGE_REASON_MEMO, v_visit.STAFF_ID, v_sched_sk;
    ELSE
      EXECUTE IMMEDIATE v_sql USING v_visit.REC_UPDATE_TMSTP, v_visit.REC_UPDATED_BY, v_visit.CHANGE_REASON_MEMO, v_sched_sk;
    END IF;

    RETURN SQL%ROWCOUNT;

  END UPDATE_VISIT_BY_SCHED_SK;

  ----------------------------------------------------------------------------------
  FUNCTION UPDATE_VISIT_BY_SCHED_EVNT_SK(v_visit IN VISIT_T, v_sched_evnt_sk IN VISIT.SCHED_EVNT_SK%TYPE) RETURN NUMBER
  AS
    v_sql VARCHAR2(1024);
  BEGIN

    v_sql := 'UPDATE VISIT SET REC_UPDATE_TMSTP = :rec_update_tmstp';
    v_sql := v_sql || ', REC_UPDATED_BY = :rec_updated_by, CHANGE_REASON_MEMO = :change_reason_memo';

    --find out what change
    --this was an empty visit if its changing form schedule there are only certain things that can change.
    --right now we can only change staffId
    IF (v_visit.STAFF_ID IS NOT NULL) THEN
      v_sql := v_sql || ', STAFF_ID = :staff_id';
    END IF;

    v_sql := v_sql || ' WHERE VISIT.SCHED_EVNT_SK = :sched_evnt_sk';

    IF (v_visit.STAFF_ID IS NOT NULL) THEN
      EXECUTE IMMEDIATE v_sql USING v_visit.REC_UPDATE_TMSTP, v_visit.REC_UPDATED_BY, v_visit.CHANGE_REASON_MEMO, v_visit.STAFF_ID, v_sched_evnt_sk;
    ELSE
      EXECUTE IMMEDIATE v_sql USING v_visit.REC_UPDATE_TMSTP, v_visit.REC_UPDATED_BY, v_visit.CHANGE_REASON_MEMO, v_sched_evnt_sk;
    END IF;

    RETURN SQL%ROWCOUNT;

  END UPDATE_VISIT_BY_SCHED_EVNT_SK;

END PKG_SCHEDULE_UTIL;
/
