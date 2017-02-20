SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_result NUMBER;

BEGIN

  dbms_output.put_line ('*** START: PKG_BE_LOB.BE_LOB_EXISTS ***');

    l_result := COREDATA.PKG_BE_LOB.BE_LOB_EXISTS('1', 'FIDA');

    IF l_result IS NULL THEN
      RAISE NULL_EXCEPTION;
    END IF;

    dbms_output.put_line ('l_result = ' || l_result);

  dbms_output.put_line ('*** END: PKG_BE_LOB.BE_LOB_EXISTS ***' || CHR(13) || CHR(10));

  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  --WHEN OTHERS THEN
  --dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;
