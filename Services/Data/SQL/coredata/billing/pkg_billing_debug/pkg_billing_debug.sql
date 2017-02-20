CREATE OR REPLACE PACKAGE PKG_BILLING_DEBUG IS

  FUNCTION GET_VISIT (v_visit_sk VISIT.VISIT_SK%TYPE) RETURN SYS_REFCURSOR;
  FUNCTION GET_AGENCY (v_be_id BE.BE_ID%TYPE) RETURN SYS_REFCURSOR;

END PKG_BILLING_DEBUG;
/

CREATE OR REPLACE PACKAGE BODY PKG_BILLING_DEBUG IS

  FUNCTION GET_VISIT (v_visit_sk VISIT.VISIT_SK%TYPE) RETURN SYS_REFCURSOR
  AS

    l_cursor SYS_REFCURSOR;

    BEGIN

      dbms_output.put_line (CHR(9) || '*** START: >>> GET_VISIT ***');

      OPEN l_cursor FOR
      SELECT *
      FROM VISIT
      WHERE VISIT_SK = v_visit_sk;

      dbms_output.put_line (CHR(9) || '*** END: <<< GET_VISIT ***' || CHR(13) || CHR(10));

      RETURN l_cursor;

      EXCEPTION

      WHEN OTHERS THEN
        dbms_output.put_line('ERROR: PKG_BILLING_DEBUG.GET_VISIT: UNHANDLED EXCEPTION!');

    END GET_VISIT;

  FUNCTION GET_AGENCY (v_be_id BE.BE_ID%TYPE) RETURN SYS_REFCURSOR
  AS

    l_cursor SYS_REFCURSOR;

    BEGIN

      dbms_output.put_line (CHR(9) || '*** START: >>> GET_AGENCY ***');

      OPEN l_cursor FOR
      SELECT *
      FROM BE
      WHERE BE_ID = v_be_id
        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1);

      dbms_output.put_line (CHR(9) || '*** END: <<< GET_AGENCY ***' || CHR(13) || CHR(10));

      RETURN l_cursor;

      EXCEPTION

      WHEN OTHERS THEN
        dbms_output.put_line('ERROR: PKG_BILLING_DEBUG.GET_AGENCY: UNHANDLED EXCEPTION!');

    END GET_AGENCY;

END PKG_BILLING_DEBUG;
/
