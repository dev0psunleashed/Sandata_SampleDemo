CREATE OR REPLACE PACKAGE PKG_BE_LOB IS

  FUNCTION BE_AGENCY_EXISTS (v_be_par_id BE.BE_ID%TYPE) RETURN NUMBER;

  FUNCTION BE_LOB_EXISTS (v_be_par_id BE.BE_ID%TYPE, v_be_name BE.BE_NAME%TYPE) RETURN NUMBER;

  FUNCTION getBeLobLkup(v_be_par_id BE.BE_ID%TYPE) RETURN SYS_REFCURSOR;

  FUNCTION deleteBeLobLkup(v_sk NUMBER) RETURN NUMBER;

  FUNCTION insertBeLobLkup(v_be_par_id BE.BE_ID%TYPE, v_be_lob_lkup BE_LOB_LKUP_T) RETURN NUMBER;

END PKG_BE_LOB;
/

CREATE OR REPLACE PACKAGE BODY PKG_BE_LOB IS

  FUNCTION BE_AGENCY_EXISTS (v_be_par_id BE.BE_ID%TYPE) RETURN NUMBER
  AS
    l_result NUMBER;

    BEGIN
      SELECT 1 INTO l_result FROM (
        SELECT T1.BE_ID,T1.BE_PAR_ID
        FROM BE_REL T1
        WHERE T1.BE_ID = v_be_par_id
          AND T1.BE_PAR_ID IS NULL);

      RETURN l_result;

      EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RETURN 0;

    END BE_AGENCY_EXISTS;

  FUNCTION BE_LOB_EXISTS (v_be_par_id BE.BE_ID%TYPE, v_be_name BE.BE_NAME%TYPE) RETURN NUMBER
  AS
    l_result NUMBER;

    BEGIN
      SELECT 1 INTO l_result FROM (
        SELECT DISTINCT T2.BE_PAR_ID,T1.BE_ID,T1.BE_NAME
          FROM BE T1
        INNER JOIN BE_REL T2
        ON T1.BE_ID = T2.BE_ID
          AND TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1
        WHERE T2.BE_PAR_ID = v_be_par_id
          AND T2.BE_REL_TYP = 'BE_LOB'
          AND T2.BE_REL_STATUS = 'ACTIVE'
          AND UPPER(TRIM(T1.BE_NAME)) = UPPER(TRIM(v_be_name))
        START WITH T2.BE_PAR_ID IS NULL
        CONNECT BY NOCYCLE T2.BE_PAR_ID = PRIOR T1.BE_ID);

        RETURN l_result;

      EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RETURN 0;

    END BE_LOB_EXISTS;

  FUNCTION getBeLobLkup(v_be_par_id BE.BE_ID%TYPE) RETURN SYS_REFCURSOR
  AS
    l_cursor SYS_REFCURSOR;

    BEGIN
      OPEN l_cursor FOR
      SELECT * FROM (
        SELECT DISTINCT
              T2.BE_PAR_ID,
              T1.BE_ID AS C_BE_ID,
              T1.BE_NAME AS C_BE_NAME
        FROM BE T1
        INNER JOIN BE_REL T2
        ON T1.BE_ID = T2.BE_ID
          AND TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1
        START WITH T2.BE_PAR_ID IS NULL
        CONNECT BY NOCYCLE T2.BE_PAR_ID = PRIOR T1.BE_ID)
      INNER JOIN BE_LOB_LKUP
      ON BE_LOB_LKUP.BE_ID = C_BE_ID AND BE_LOB_LKUP.BE_LOB = C_BE_NAME
      WHERE BE_PAR_ID = v_be_par_id
      ORDER BY C_BE_NAME ASC;

      RETURN l_cursor;

      EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERROR: getBeLobLkup: UNHANDLED EXCEPTION!');

    END getBeLobLkup;

  FUNCTION deleteBeLobLkup(v_sk NUMBER) RETURN NUMBER
  AS
    l_result NUMBER;

    l_be_id BE_LOB_LKUP.BE_ID%TYPE;
    l_be_sk BE.BE_SK%TYPE;
    l_be_rel_sk BE_REL.BE_REL_SK%TYPE;

    BEGIN

      l_result := 0;

      SELECT BE_ID INTO l_be_id FROM BE_LOB_LKUP WHERE BE_LOB_LKUP_SK = v_sk;
      SELECT BE_REL_SK INTO l_be_rel_sk FROM BE_REL WHERE BE_ID = l_be_id AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1);
      SELECT BE_SK INTO l_be_sk FROM BE WHERE BE_ID = l_be_id AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1);

      l_result := l_result + PKG_AM.deleteBeLobLkup(v_sk);
      l_result := l_result + PKG_BSN.deleteBeRel(l_be_rel_sk);
      l_result := l_result + PKG_BSN.deleteBe(l_be_sk);

      RETURN l_result;

      EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RETURN 0;

    END deleteBeLobLkup;

  FUNCTION insertBeLobLkup(v_be_par_id BE.BE_ID%TYPE, v_be_lob_lkup BE_LOB_LKUP_T) RETURN NUMBER
  AS

    l_result NUMBER;
    l_sk_val NUMBER;

    l_be_id BE.BE_ID%TYPE;

    l_err_msg CLOB;

    l_be BE_T;
    l_be_rel BE_REL_T;

    l_be_lob_lkup BE_LOB_LKUP_T;

    BEGIN

      l_result := PKG_BE_LOB.BE_AGENCY_EXISTS(v_be_par_id);
      IF l_result = 0 THEN
        l_err_msg := '[PARENT: BE_ID=' || v_be_par_id || ']: Agency does not have a ROOT record in the BE_REL table!';
        PKG_EXCEPTIONS.NO_AGENCY_ROOT_REC_ERR(l_err_msg);
      END IF;

      l_result := PKG_BE_LOB.BE_LOB_EXISTS(v_be_par_id, v_be_lob_lkup.BE_LOB);

      IF l_result = 1 THEN
        l_err_msg := '[PARENT: BE_ID=' || v_be_par_id || ']: [BE_LOB=' || v_be_lob_lkup.BE_LOB || ']: BE_LOB already exists for this agency!';
        PKG_EXCEPTIONS.BE_LOB_EXISTS_ERR(l_err_msg);
      END IF;

      l_be_lob_lkup := v_be_lob_lkup;

      l_be := BE_T(
        NULL, --BE_SK	NUMBER(38),
        v_be_lob_lkup.BE_ID, --BE_ID	VARCHAR2(50 BYTE),
        CURRENT_TIMESTAMP, --REC_CREATE_TMSTP DATE,
        CURRENT_TIMESTAMP, --REC_UPDATE_TMSTP DATE,
        CURRENT_TIMESTAMP, --REC_EFF_TMSTP DATE,
        TO_DATE('9999-12-31 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), --REC_TERM_TMSTP DATE,
        'MW: PKG_BE_LOB', --REC_CREATED_BY	VARCHAR2(50 BYTE),
        'N/A', --REC_UPDATED_BY	VARCHAR2(50 BYTE),
        'MW: PKG_BE_LOB: INSERT', --CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
        1, --CURR_REC_IND	NUMBER(1),
        0, --CHANGE_VERSION_ID	NUMBER(38),
        NULL, --TZ_NAME	VARCHAR2(64 BYTE),
        NULL, --BE_LOGO_POINTER	VARCHAR2(50 BYTE),
        v_be_lob_lkup.BE_LOB, --BE_NAME	VARCHAR2(100 BYTE),
        NULL, --BE_LEGAL_NAME	VARCHAR2(100 BYTE),
        NULL, --BE_FEDERALTAX_ID	VARCHAR2(50 BYTE),
        NULL, --BE_MEDICARE_ID	VARCHAR2(50 BYTE),
        NULL, --BE_MEDICAID_ID	VARCHAR2(50 BYTE),
        NULL, --OASIS_ID	VARCHAR2(50 BYTE),
        NULL, --BE_NPI	VARCHAR2(10 BYTE),
        NULL, --BE_API	VARCHAR2(50 BYTE),
        NULL, --BE_TAXONOMY_CODE	VARCHAR2(10 BYTE),
        'BE_LOB', --BE_TYP	VARCHAR2(50 BYTE),
        NULL, --BE_PRMY_CONT_NAME	VARCHAR2(200 BYTE),
        NULL, --BE_PRMY_CONT_TITLE	VARCHAR2(50 BYTE),
        NULL, --BE_PRMY_ADDR_USE_FOR_MAIL_IND	NUMBER(1),
        NULL, --BE_PRMY_ADDR1	VARCHAR2(50 BYTE),
        NULL, --BE_PRMY_ADDR2	VARCHAR2(50 BYTE),
        NULL, --BE_PRMY_CITY	VARCHAR2(50 BYTE),
        NULL, --BE_PRMY_STATE	CHAR(2 BYTE),
        NULL, --BE_PRMY_COUNTY	VARCHAR2(50 BYTE),
        NULL, --BE_PRMY_PSTL_CODE	VARCHAR2(5 BYTE),
        NULL, --BE_PRMY_ZIP4	VARCHAR2(10 BYTE),
        NULL, --BE_PRMY_PHONE	VARCHAR2(10 BYTE),
        NULL, --BE_PRMY_PHONE_EXT	VARCHAR2(8 BYTE),
        NULL, --BE_PRMY_PHONE_QLFR	VARCHAR2(30 BYTE),
        NULL, --BE_PRMY_PHONE_1	VARCHAR2(10 BYTE),
        NULL, --BE_PRMY_PHONE_1_EXT	VARCHAR2(8 BYTE),
        NULL, --BE_PRMY_PHONE_1_QLFR	VARCHAR2(30 BYTE),
        NULL, --BE_PRMY_PHONE_2	VARCHAR2(10 BYTE),
        NULL, --BE_PRMY_PHONE_2_EXT	VARCHAR2(8 BYTE),
        NULL, --BE_PRMY_PHONE_2_QLFR	VARCHAR2(30 BYTE),
        NULL, --BE_FAX	VARCHAR2(10 BYTE),
        NULL, --BE_FAX_QLFR	VARCHAR2(30 BYTE),
        NULL, --BE_FAX_1	VARCHAR2(10 BYTE),
        NULL, --BE_FAX_1_QLFR	VARCHAR2(30 BYTE),
        NULL, --BE_PRMY_EMAIL	VARCHAR2(100 BYTE),
        NULL, --BE_PR_WEEK_END_DAY	VARCHAR2(10 BYTE),
        NULL, --BE_HOLD_BILLING_IND	NUMBER(1),
        NULL, --BE_SPLIT_BILLING_ALWD_IND	NUMBER(1),
        NULL, --BE_INCL_REMIT_ADDR_IND	NUMBER(1),
        NULL, --BE_WEEKEND_START_DAY	VARCHAR2(10 BYTE),
        NULL, --BE_WEEKEND_START_TIME	VARCHAR2(50 BYTE),
        NULL, --BE_WEEKEND_END_DAY	VARCHAR2(10 BYTE),
        NULL, --BE_WEEKEND_END_TIME	VARCHAR2(50 BYTE),
        NULL, --BE_PT_ASMT_FREQ	VARCHAR2(50 BYTE),
        NULL, --BE_PT_SUPVY_VISIT_FREQ	VARCHAR2(50 BYTE),
        NULL, --BE_PR_FREQ_TYP_NAME	VARCHAR2(50 BYTE),
        NULL, --BE_BILLING_FREQ_TYP_NAME	VARCHAR2(50 BYTE),
        NULL, --BE_LI_EQUIV	NUMBER(38)
        NULL, --BE_FIRST_PR_DATE
        NULL, --BE_PR_START_DAY
        NULL --VV_RNDING_RULE_ID
      );

      l_result := PKG_BSN.insertBe(l_be);

      IF l_result > 0 THEN

        -- Get the BE_ID for the new created BE record
        SELECT BE_ID INTO l_be_id FROM BE WHERE BE_SK = l_result;

        -- Assign the BE_Id to the BE_LOB_LKUP record to be inserted
        l_be_lob_lkup.BE_ID := l_be_id;

        l_sk_val := PKG_AM.insertBeLobLkup(l_be_lob_lkup);

        IF l_sk_val > 0 THEN

          l_result := l_sk_val; -- Response is the SK of the BeLobLkup insert

          l_be_rel := BE_REL_T(
            NULL, --BE_REL_SK	NUMBER(38),
            NULL, --BE_REL_ID	VARCHAR2(50 BYTE),
            CURRENT_TIMESTAMP, --REC_CREATE_TMSTP DATE,
            CURRENT_TIMESTAMP, --REC_UPDATE_TMSTP DATE,
            CURRENT_TIMESTAMP, --REC_EFF_TMSTP DATE,
            TO_DATE('9999-12-31 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), --REC_TERM_TMSTP DATE,
            'MW: PKG_BE_LOB', --REC_CREATED_BY	VARCHAR2(50 BYTE),
            'N/A', --REC_UPDATED_BY	VARCHAR2(50 BYTE),
            'MW: PKG_BE_LOB: INSERT', --CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
            1, --CURR_REC_IND	NUMBER(1),
            0, --CHANGE_VERSION_ID	NUMBER(38),
            l_be_id, --BE_ID	VARCHAR2(50 BYTE),
            v_be_par_id, --BE_PAR_ID	VARCHAR2(50 BYTE),
            1, --BE_LVL_SK	NUMBER(38),
            'BE_LOB', --BE_REL_TYP	VARCHAR2(50 BYTE),
            'ACTIVE' --BE_REL_STATUS	VARCHAR2(50 BYTE)
          );

          l_sk_val := PKG_BSN.insertBeRel(l_be_rel);

        END IF;

      END IF;

      RETURN l_result;

    END insertBeLobLkup;

END PKG_BE_LOB;
/
