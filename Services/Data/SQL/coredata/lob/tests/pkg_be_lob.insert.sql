SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_random_num NUMBER;

  l_result NUMBER;

  l_be_lob_lkup BE_LOB_LKUP_T;

BEGIN

  dbms_output.put_line ('*** START: PKG_BE_LOB.insertBeLobLkup ***');

    l_random_num := Round(dbms_random.value(1,1000000),0);

    l_be_lob_lkup := BE_LOB_LKUP_T(

      NULL, --BE_LOB_LKUP_SK	NUMBER(38),
      CURRENT_TIMESTAMP, --REC_CREATE_TMSTP DATE,
      CURRENT_TIMESTAMP, --REC_UPDATE_TMSTP DATE,
      CURRENT_TIMESTAMP, --REC_EFF_TMSTP DATE,
      TO_DATE('9999-12-31 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), --REC_TERM_TMSTP DATE,
      'PLSQL Test Script', --REC_CREATED_BY	VARCHAR2(50 BYTE),
      'N/A', --REC_UPDATED_BY	VARCHAR2(50 BYTE),
      'INSERT: ' || l_random_num, --CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
      1, --CURR_REC_IND	NUMBER(1),
      0, --CHANGE_VERSION_ID	NUMBER(38),
      NULL, --BE_ID	VARCHAR2(50 BYTE),
      'FIDA', --BE_LOB	VARCHAR2(100 BYTE),
      'FIDA Desc', --BE_LOB_DESC	VARCHAR2(250 BYTE),
      CURRENT_TIMESTAMP, --BE_LOB_EFF_DATE DATE,
      TO_DATE('9999-12-31 00:00:00', 'YYYY-MM-DD HH24:MI:SS') --BE_LOB_TERM_DATE DATE
    );

    l_result := COREDATA.PKG_BE_LOB.insertBeLobLkup('999', l_be_lob_lkup);

    IF l_result IS NULL THEN
      RAISE NULL_EXCEPTION;
    END IF;

    dbms_output.put_line ('l_result = ' || l_result);

    IF l_result > 0 THEN
      COMMIT;
      dbms_output.put_line ('*** SUCCESS! ***: l_result = ' || l_result);
    ELSE
      ROLLBACK;
      dbms_output.put_line ('*** FAILED! ***: l_result = ' || l_result);
    END IF;

  dbms_output.put_line ('*** END: PKG_BE_LOB.insertBeLobLkup ***' || CHR(13) || CHR(10));

  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  --WHEN OTHERS THEN
  --dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;
