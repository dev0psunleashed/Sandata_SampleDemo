SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_cursor SYS_REFCURSOR;

  l_be_lob_lkup BE_LOB_LKUP%ROWTYPE;

BEGIN

  dbms_output.put_line ('*** START: PKG_LOB_UTIL.GET_BRS_FOR_LOB ***');

  l_cursor := COREDATA.PKG_LOB_UTIL.GET_BE_LOB_LKUP('1', 'MCO');

  IF l_cursor IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  LOOP
    FETCH l_cursor INTO l_be_lob_lkup;

    EXIT WHEN l_cursor%NOTFOUND;

    dbms_output.put_line ('l_cursor: l_be_lob_lkup.BE_ID = ' || l_be_lob_lkup.BE_ID);
    dbms_output.put_line ('l_cursor: l_be_lob_lkup.BE_LOB = ' || l_be_lob_lkup.BE_LOB || CHR(13) || CHR(10));

  END LOOP;

  CLOSE l_cursor;

  dbms_output.put_line ('*** END: PKG_LOB_UTIL.GET_BRS_FOR_LOB ***' || CHR(13) || CHR(10));

  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  WHEN OTHERS THEN
  CLOSE l_cursor;
  dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;