CREATE OR REPLACE PACKAGE PKG_STAFF_UTIL IS

  -- GEOR-6139: Staff verified start date
  FUNCTION VERIFIED_START_DATE (v_staff_id STAFF.STAFF_ID%TYPE) RETURN DATE;

  FUNCTION VERIFIED_VADJ_DATE (v_staff_id STAFF.STAFF_ID%TYPE) RETURN DATE;
  FUNCTION VERIFIED_VACT_DATE (v_staff_id STAFF.STAFF_ID%TYPE) RETURN DATE;
  FUNCTION VERIFIED_TRAINING_DATE (v_staff_id STAFF.STAFF_ID%TYPE) RETURN DATE;

  FUNCTION HIRE_DATE_BY_SK (l_staff_sk IN STAFF.STAFF_SK%TYPE) RETURN TIMESTAMP;

  FUNCTION HIRE_DATE (v_staff_id IN STAFF.STAFF_ID%TYPE, v_be_id IN STAFF.BE_ID%TYPE) RETURN TIMESTAMP;
  
  FUNCTION LAST_HIRE_DATE (v_staff_id IN STAFF.STAFF_ID%TYPE, v_be_id IN STAFF.BE_ID%TYPE) RETURN TIMESTAMP;

  FUNCTION REHIRE_DATE (v_staff_id IN STAFF.STAFF_ID%TYPE, v_be_id IN STAFF.BE_ID%TYPE) RETURN TIMESTAMP;

  FUNCTION LAST_DATE_WORKED_ADJ (v_staff_id STAFF.STAFF_ID%TYPE, v_be_id VISIT.BE_ID%TYPE) RETURN DATE;

  FUNCTION LAST_DATE_WORKED_ACT (v_staff_id STAFF.STAFF_ID%TYPE, v_be_id VISIT.BE_ID%TYPE) RETURN DATE;

  FUNCTION LAST_DATE_WORKED (v_staff_id STAFF.STAFF_ID%TYPE, v_be_id VISIT.BE_ID%TYPE) RETURN DATE;

  FUNCTION DELETE_STAFF_LANGUAGE_LIST(v_be_id IN STAFF.BE_ID%TYPE, v_staff_id IN STAFF_LANG.STAFF_ID%TYPE) RETURN NUMBER;

  FUNCTION DELETE_STAFF_TRNG_CLS_EVNT(v_staff_trng_loc_sk IN STAFF_TRNG_LOC.STAFF_TRNG_LOC_SK%TYPE) RETURN NUMBER;

  FUNCTION DEL_STAFF_TRNG_CLS_EVNT_ENROL(v_staff_trng_cls_evnt_enrol_sk IN STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_CLS_EVNT_ENROL_SK%TYPE) RETURN NUMBER;

  FUNCTION DEL_STAFF_TRNG_CLS_EVNT_ATTND(v_staff_trng_cls_evnt_enrol_sk STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_CLS_EVNT_ENROL_SK%TYPE) RETURN NUMBER;

  FUNCTION CANCEL_SCHED_EVNT_CLS_EVNT_ENR(v_be_id IN SCHED_EVNT.BE_ID%TYPE, v_staff_id IN SCHED_EVNT.STAFF_ID%TYPE, v_staff_trng_code IN STAFF_TRNG_CLS_EVNT.STAFF_TRNG_CODE%TYPE, v_staff_trng_loc_name IN STAFF_TRNG_CLS_EVNT.STAFF_TRNG_LOC_NAME%TYPE, v_staff_trng_start_datetime IN DATE) RETURN NUMBER;

END PKG_STAFF_UTIL;
/

CREATE OR REPLACE PACKAGE BODY PKG_STAFF_UTIL IS

  FUNCTION VERIFIED_VADJ_DATE (v_staff_id STAFF.STAFF_ID%TYPE) RETURN DATE
  AS

    l_visit_adj_date DATE;

    BEGIN

      -- Adjusted is taken into account first because that is the date manually set by the user
      SELECT VISIT_ADJ_START_TMSTP INTO l_visit_adj_date FROM
        (SELECT VISIT_ADJ_START_TMSTP
          FROM VISIT
          WHERE
            VISIT_APRVD_IND = 1
            AND STAFF_ID = v_staff_id
            AND VISIT_ADJ_START_TMSTP IS NOT NULL
          ORDER BY VISIT_ADJ_START_TMSTP ASC)
      WHERE ROWNUM <= 1; -- Only get the first result

    RETURN l_visit_adj_date;

    EXCEPTION
      WHEN NO_DATA_FOUND THEN
            RETURN NULL;

   END VERIFIED_VADJ_DATE;

  FUNCTION VERIFIED_VACT_DATE (v_staff_id STAFF.STAFF_ID%TYPE) RETURN DATE
  AS

    l_visit_act_date DATE;

    BEGIN

     -- If adjusted is NULL, then we check the actual start time which is set by call matching
      SELECT VISIT_ACT_START_TMSTP INTO l_visit_act_date FROM
        (SELECT VISIT_ACT_START_TMSTP
          FROM VISIT
          WHERE
            VISIT_APRVD_IND = 1
            AND STAFF_ID = v_staff_id
            AND VISIT_ACT_START_TMSTP IS NOT NULL
            AND VISIT_ADJ_START_TMSTP IS NULL
          ORDER BY VISIT_ACT_START_TMSTP ASC)
      WHERE ROWNUM <= 1; -- Only get the first result

    RETURN l_visit_act_date;

    EXCEPTION
      WHEN NO_DATA_FOUND THEN
            RETURN NULL;

   END VERIFIED_VACT_DATE;

  FUNCTION VERIFIED_TRAINING_DATE (v_staff_id STAFF.STAFF_ID%TYPE) RETURN DATE
  AS

    l_staff_training_date DATE;

    BEGIN

      SELECT STAFF_TRNG_START_DTIME INTO l_staff_training_date FROM
        (SELECT STAFF_TRNG_START_DTIME
          FROM STAFF_TRNG
          WHERE
            STAFF_ID = v_staff_id
            AND STAFF_TRNG_START_DTIME IS NOT NULL
            AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1
          ORDER BY STAFF_TRNG_START_DTIME ASC)
      WHERE ROWNUM <= 1;

    RETURN l_staff_training_date;

    EXCEPTION
      WHEN NO_DATA_FOUND THEN
            RETURN NULL;

   END VERIFIED_TRAINING_DATE;

  FUNCTION VERIFIED_START_DATE (v_staff_id STAFF.STAFF_ID%TYPE) RETURN DATE
  AS

    l_visit_adj_date DATE;
    l_visit_act_date DATE;
    l_staff_training_date DATE;

    l_result DATE;

    BEGIN

      l_result := NULL;

      l_staff_training_date := PKG_STAFF_UTIL.VERIFIED_TRAINING_DATE(v_staff_id);
      l_visit_adj_date := PKG_STAFF_UTIL.VERIFIED_VADJ_DATE(v_staff_id);
      l_visit_act_date := PKG_STAFF_UTIL.VERIFIED_VACT_DATE(v_staff_id);

      IF l_staff_training_date IS NOT NULL THEN
        l_result := l_staff_training_date;
      END IF;

      IF l_visit_adj_date IS NOT NULL THEN
        IF l_result IS NULL THEN
          l_result := l_visit_adj_date;
        ELSIF l_visit_adj_date < l_result THEN
          l_result := l_visit_adj_date;
        END IF;
      END IF;

      IF l_visit_act_date IS NOT NULL THEN
        IF l_result IS NULL THEN
          l_result := l_visit_act_date;
        ELSIF l_visit_act_date < l_result THEN
          l_result := l_visit_act_date;
        END IF;
      END IF;

      RETURN l_result;

      EXCEPTION
        WHEN NO_DATA_FOUND THEN
              RETURN NULL;

    END VERIFIED_START_DATE;


  FUNCTION HIRE_DATE_BY_SK (l_staff_sk IN STAFF.STAFF_SK%TYPE) RETURN TIMESTAMP
  AS

    l_hire_date STAFF.STAFF_HIRE_DATE%TYPE;
    l_staff_id STAFF.STAFF_ID%TYPE;
    l_staff_be_id STAFF.BE_ID%TYPE;

    BEGIN
      SELECT STAFF_ID,BE_ID
        INTO l_staff_id,l_staff_be_id
        FROM STAFF
      WHERE STAFF_SK = l_staff_sk;

      RETURN PKG_STAFF_UTIL.HIRE_DATE(l_staff_id, l_staff_be_id);

       EXCEPTION
          WHEN NO_DATA_FOUND THEN
            RETURN NULL;

    END HIRE_DATE_BY_SK;

  FUNCTION HIRE_DATE (v_staff_id IN STAFF.STAFF_ID%TYPE, v_be_id IN STAFF.BE_ID%TYPE) RETURN TIMESTAMP
  AS

    l_hire_date STAFF.STAFF_HIRE_DATE%TYPE;

    BEGIN
      SELECT T1.* INTO l_hire_date FROM (
          SELECT STAFF_HIRE_DATE
            FROM STAFF
          WHERE BE_ID = v_be_id
            AND CURR_REC_IND = 1
            AND STAFF_ID = v_staff_id
            ORDER BY REC_EFF_TMSTP DESC) T1
      WHERE ROWNUM <= 1;

      RETURN l_hire_date;

       EXCEPTION
          WHEN NO_DATA_FOUND THEN
            RETURN NULL;

    END HIRE_DATE;
	
	FUNCTION LAST_HIRE_DATE (v_staff_id IN STAFF.STAFF_ID%TYPE, v_be_id IN STAFF.BE_ID%TYPE) RETURN TIMESTAMP
    AS

      l_last_hire_date STAFF.STAFF_LAST_HIRE_DATE%TYPE;

      BEGIN
        SELECT T1.* INTO l_last_hire_date FROM (
            SELECT STAFF_LAST_HIRE_DATE
              FROM STAFF
            WHERE BE_ID = v_be_id
              AND CURR_REC_IND = 1
              AND STAFF_ID = v_staff_id
              ORDER BY REC_UPDATE_TMSTP DESC, REC_TERM_TMSTP DESC) T1
        WHERE ROWNUM <= 1;

        RETURN l_last_hire_date;

         EXCEPTION
            WHEN NO_DATA_FOUND THEN
              RETURN NULL;

    END LAST_HIRE_DATE;

    FUNCTION REHIRE_DATE (v_staff_id IN STAFF.STAFF_ID%TYPE, v_be_id IN STAFF.BE_ID%TYPE) RETURN TIMESTAMP
    AS

    l_hire_date STAFF.STAFF_LAST_HIRE_DATE%TYPE;

    BEGIN
      SELECT T1.* INTO l_hire_date FROM (
          SELECT STAFF_HIRE_DATE
            FROM STAFF
          WHERE BE_ID = v_be_id
            AND CURR_REC_IND = 1
            AND STAFF_ID = v_staff_id
            ORDER BY REC_EFF_TMSTP DESC) T1
      WHERE ROWNUM <= 1;

      RETURN l_hire_date;

       EXCEPTION
          WHEN NO_DATA_FOUND THEN
            RETURN NULL;

    END REHIRE_DATE;

  FUNCTION LAST_DATE_WORKED_ADJ (v_staff_id STAFF.STAFF_ID%TYPE, v_be_id VISIT.BE_ID%TYPE) RETURN DATE
  AS
    l_visit_adj_date DATE;

    BEGIN
      SELECT VISIT_ADJ_START_TMSTP INTO l_visit_adj_date FROM
        (SELECT VISIT_ADJ_START_TMSTP
          FROM VISIT
          WHERE
            STAFF_ID = v_staff_id
            AND BE_ID = v_be_id
            AND VISIT_ADJ_START_TMSTP IS NOT NULL
          ORDER BY VISIT_ADJ_START_TMSTP DESC)
      WHERE ROWNUM <= 1; -- Only get the first result

      RETURN l_visit_adj_date;

      EXCEPTION
          WHEN NO_DATA_FOUND THEN
            RETURN NULL;

    END LAST_DATE_WORKED_ADJ;


  FUNCTION LAST_DATE_WORKED_ACT (v_staff_id STAFF.STAFF_ID%TYPE, v_be_id VISIT.BE_ID%TYPE) RETURN DATE
  AS
    l_visit_act_date DATE;

    BEGIN
      SELECT VISIT_ACT_START_TMSTP INTO l_visit_act_date FROM
        (SELECT VISIT_ACT_START_TMSTP
          FROM VISIT
          WHERE
            STAFF_ID = v_staff_id
            AND BE_ID = v_be_id
            AND VISIT_ACT_START_TMSTP IS NOT NULL
            AND VISIT_ADJ_START_TMSTP IS NULL
          ORDER BY VISIT_ACT_START_TMSTP DESC)
      WHERE ROWNUM <= 1; -- Only get the first result

      RETURN l_visit_act_date;

      EXCEPTION
          WHEN NO_DATA_FOUND THEN
            RETURN NULL;

    END LAST_DATE_WORKED_ACT;

  FUNCTION LAST_DATE_WORKED (v_staff_id STAFF.STAFF_ID%TYPE, v_be_id VISIT.BE_ID%TYPE) RETURN DATE
  AS

    l_visit_adj_date DATE;
    l_visit_act_date DATE;

    BEGIN

      -- Adjusted is taken into account first because that is the date manually set by the user
      l_visit_adj_date := PKG_STAFF_UTIL.LAST_DATE_WORKED_ADJ(v_staff_id, v_be_id);

      -- If adjusted is NULL, then we check the actual start time which is set by call matching
      IF l_visit_adj_date IS NULL THEN
        l_visit_act_date := PKG_STAFF_UTIL.LAST_DATE_WORKED_ACT(v_staff_id, v_be_id);
        RETURN l_visit_act_date;
      END IF;

      RETURN l_visit_adj_date;

    END LAST_DATE_WORKED;

  FUNCTION DELETE_STAFF_LANGUAGE_LIST(v_be_id IN STAFF.BE_ID%TYPE, v_staff_id IN STAFF_LANG.STAFF_ID%TYPE) RETURN NUMBER
  AS
 
  BEGIN
    
    UPDATE STAFF_LANG
    SET
      REC_TERM_TMSTP = TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD'),
      CURR_REC_IND = 0
    WHERE BE_ID = v_be_id
      AND STAFF_ID = v_staff_id
      AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1);
    
    RETURN SQL%ROWCOUNT;

  END DELETE_STAFF_LANGUAGE_LIST;

  FUNCTION DELETE_STAFF_TRNG_CLS_EVNT(v_staff_trng_loc_sk IN STAFF_TRNG_LOC.STAFF_TRNG_LOC_SK%TYPE) RETURN NUMBER
  AS
  
  BEGIN
  
    UPDATE STAFF_TRNG_CLS_EVNT
    SET
      REC_TERM_TMSTP = CURRENT_TIMESTAMP,
      CURR_REC_IND = 0
    WHERE
      (BE_ID, STAFF_TRNG_LOC_NAME) = (SELECT BE_ID, STAFF_TRNG_LOC_NAME FROM STAFF_TRNG_LOC WHERE STAFF_TRNG_LOC_SK = v_staff_trng_loc_sk)
      AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1');
  
    RETURN SQL%ROWCOUNT;
  
  END DELETE_STAFF_TRNG_CLS_EVNT;

  FUNCTION DEL_STAFF_TRNG_CLS_EVNT_ENROL(v_staff_trng_cls_evnt_enrol_sk IN STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_CLS_EVNT_ENROL_SK%TYPE) RETURN NUMBER
  AS
  
  BEGIN
    
    UPDATE STAFF_TRNG
    SET
      REC_TERM_TMSTP = CURRENT_TIMESTAMP,
      CURR_REC_IND = 0
    WHERE
      (BE_ID, STAFF_ID, STAFF_TRNG_LOC_NAME, STAFF_TRNG_CODE, STAFF_TRNG_START_DTIME) = (SELECT BE_ID, STAFF_ID, STAFF_TRNG_LOC_NAME, STAFF_TRNG_CODE, STAFF_TRNG_START_DTIME FROM STAFF_TRNG_CLS_EVNT_ENROL WHERE STAFF_TRNG_CLS_EVNT_ENROL_SK = v_staff_trng_cls_evnt_enrol_sk)
      AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1');
    
    RETURN SQL%ROWCOUNT;

  END DEL_STAFF_TRNG_CLS_EVNT_ENROL;

  FUNCTION DEL_STAFF_TRNG_CLS_EVNT_ATTND(v_staff_trng_cls_evnt_enrol_sk STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_CLS_EVNT_ENROL_SK%TYPE) RETURN NUMBER
  AS
  
  BEGIN
  
    UPDATE STAFF_TRNG_CLS_EVNT_ATTND
    SET
      REC_TERM_TMSTP = CURRENT_TIMESTAMP,
      CURR_REC_IND = 0
    WHERE
      (BE_ID, STAFF_TRNG_LOC_NAME, STAFF_TRNG_CODE, STAFF_TRNG_START_DTIME, STAFF_ID) = (SELECT BE_ID, STAFF_TRNG_LOC_NAME, STAFF_TRNG_CODE, STAFF_TRNG_START_DTIME, STAFF_ID FROM STAFF_TRNG_CLS_EVNT_ENROL WHERE STAFF_TRNG_CLS_EVNT_ENROL_SK = v_staff_trng_cls_evnt_enrol_sk)
      AND (TO_CHAR(STAFF_TRNG_CLS_EVNT_ATTND.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND STAFF_TRNG_CLS_EVNT_ATTND.CURR_REC_IND = '1');
  
    RETURN SQL%ROWCOUNT;
  
  END DEL_STAFF_TRNG_CLS_EVNT_ATTND;

  FUNCTION CANCEL_SCHED_EVNT_CLS_EVNT_ENR(v_be_id IN SCHED_EVNT.BE_ID%TYPE, v_staff_id IN SCHED_EVNT.STAFF_ID%TYPE, v_staff_trng_code IN STAFF_TRNG_CLS_EVNT.STAFF_TRNG_CODE%TYPE, v_staff_trng_loc_name IN STAFF_TRNG_CLS_EVNT.STAFF_TRNG_LOC_NAME%TYPE, v_staff_trng_start_datetime IN DATE) RETURN NUMBER
  AS
    l_staff_trng_end_datetime DATE;
  BEGIN
    
    -- must use NVL in case there is no record found it will throws exception
    SELECT NVL(
        (SELECT STAFF_TRNG_CLS_EVNT.STAFF_TRNG_START_DTIME + BE_STAFF_TRNG_LKUP.STAFF_TRNG_TOTAL_HRS/24 
        FROM STAFF_TRNG_CLS_EVNT
        INNER JOIN BE_STAFF_TRNG_LKUP ON BE_STAFF_TRNG_LKUP.BE_ID = STAFF_TRNG_CLS_EVNT.BE_ID
          AND BE_STAFF_TRNG_LKUP.STAFF_TRNG_CODE = STAFF_TRNG_CLS_EVNT.STAFF_TRNG_CODE
        WHERE STAFF_TRNG_CLS_EVNT.BE_ID = v_be_id
          AND STAFF_TRNG_CLS_EVNT.STAFF_TRNG_CODE = v_staff_trng_code
          AND STAFF_TRNG_CLS_EVNT.STAFF_TRNG_LOC_NAME = v_staff_trng_loc_name
          AND STAFF_TRNG_CLS_EVNT.STAFF_TRNG_START_DTIME = v_staff_trng_start_datetime
          AND (TO_CHAR(STAFF_TRNG_CLS_EVNT.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND STAFF_TRNG_CLS_EVNT.CURR_REC_IND = 1)
          AND (TO_CHAR(BE_STAFF_TRNG_LKUP.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND BE_STAFF_TRNG_LKUP.CURR_REC_IND = 1)
          AND ROWNUM = 1)
      , TO_DATE('9999-12-31', 'YYYY-MM-DD'))
    INTO l_staff_trng_end_datetime FROM dual;
    
    -- leave l_staff_trng_end_datetime null if there is no value
    IF TO_CHAR(l_staff_trng_end_datetime, 'YYYY-MM-DD') = '9999-12-31' THEN
        l_staff_trng_end_datetime := NULL;
    END IF;
    
    -- cancelled events
    UPDATE SCHED_EVNT
    SET
      SCHED_EVNT.SCHED_EVNT_STATUS = 'CANCELLED',
      SCHED_EVNT.REC_UPDATE_TMSTP = CURRENT_TIMESTAMP,
      SCHED_EVNT.REC_EFF_TMSTP = CURRENT_TIMESTAMP
    WHERE
      SCHED_EVNT.BE_ID = v_be_id
      AND SCHED_EVNT.STAFF_ID = v_staff_id
      AND UPPER(SCHED_EVNT.SCHED_EVNT_STATUS) = 'PENDING'
      -- there should be a 30 min threshold between event and class time
      AND (SCHED_EVNT.SCHED_EVNT_START_DTIME < l_staff_trng_end_datetime + 1/(24*2) AND SCHED_EVNT.SCHED_EVNT_END_DTIME > v_staff_trng_start_datetime - 1/(24*2))
      AND (TO_CHAR(SCHED_EVNT.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND SCHED_EVNT.CURR_REC_IND = 1);

    RETURN SQL%ROWCOUNT;
  
  END CANCEL_SCHED_EVNT_CLS_EVNT_ENR;

END PKG_STAFF_UTIL;
/
