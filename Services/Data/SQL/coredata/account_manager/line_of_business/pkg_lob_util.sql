CREATE OR REPLACE TYPE BE_LOB_ARRAY AS TABLE OF VARCHAR2(100);
/

CREATE OR REPLACE PACKAGE PKG_LOB_UTIL IS

  FUNCTION GET_BE_LOB_LKUP (l_be_id BE.BE_ID%TYPE, l_be_lob BE_LOB_LKUP.BE_LOB%TYPE) RETURN SYS_REFCURSOR;
  FUNCTION INSERT_BE_LOB (l_be_id BE.BE_ID%TYPE, l_be_lob_array BE_LOB_ARRAY) RETURN NUMBER;


END PKG_LOB_UTIL;
/

CREATE OR REPLACE PACKAGE BODY PKG_LOB_UTIL IS

  FUNCTION GET_BE_LOB_LKUP (l_be_id BE.BE_ID%TYPE, l_be_lob BE_LOB_LKUP.BE_LOB%TYPE) RETURN SYS_REFCURSOR
  AS
    result_Cursor SYS_REFCURSOR;

    BEGIN

      OPEN result_Cursor FOR
      SELECT DISTINCT T3.*
        FROM BE T1
      INNER JOIN (SELECT BE_ID,BE_PAR_ID,BE_REL_TYP,BE_REL_STATUS
        FROM BE_REL
          WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2
      ON T1.BE_ID = T2.BE_ID
      INNER JOIN (SELECT *
        FROM BE_LOB_LKUP
          WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3
      ON T2.BE_ID = T3.BE_ID
      WHERE (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1)
        AND T2.BE_REL_TYP = 'BE_LOB'
        AND T2.BE_PAR_ID = l_be_id
        AND UPPER(T3.BE_LOB) = l_be_lob
      START WITH T2.BE_PAR_ID IS NULL
      CONNECT BY NOCYCLE T2.BE_PAR_ID = PRIOR T1.BE_ID;

      RETURN result_Cursor;

      EXCEPTION
      WHEN NO_DATA_FOUND THEN
      RETURN NULL;

      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: GET_BE_LOB_LKUP: UNHANDLED EXCEPTION!');

    END GET_BE_LOB_LKUP;

  FUNCTION INSERT_BE_LOB (l_be_id BE.BE_ID%TYPE, l_be_lob_array BE_LOB_ARRAY) RETURN NUMBER
  AS
    v_be_lob_cursor SYS_REFCURSOR;

    l_be_lob_lkup BE_LOB_LKUP%ROWTYPE;

    v_result NUMBER;

    i NUMBER;

    l_exists NUMBER(1);

    BEGIN

      FOR i IN 1 .. l_be_lob_array.COUNT LOOP

        l_exists := 0;

        v_be_lob_cursor := PKG_LOB_UTIL.GET_BE_LOB_LKUP(l_be_id, l_be_lob_array(i));

        LOOP
        FETCH v_be_lob_cursor INTO l_be_lob_lkup;

        EXIT WHEN v_be_lob_cursor%NOTFOUND;

          l_exists := 1;

        END LOOP;

        CLOSE v_be_lob_cursor;

        IF l_exists = 1 THEN
          dbms_output.put_line('EXISTS!: [' || i || ']: ' || 'BE_LOB: ' || l_be_lob_array(i));
        ELSE
          dbms_output.put_line('DOES NOT EXIST!: [' || i || ']: ' || 'BE_LOB: ' || l_be_lob_array(i));
        END IF;

      END LOOP;

      RETURN 0;

      EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERROR: INSERT_BE_LOB: UNHANDLED EXCEPTION!');

    END INSERT_BE_LOB;

END PKG_LOB_UTIL;
/