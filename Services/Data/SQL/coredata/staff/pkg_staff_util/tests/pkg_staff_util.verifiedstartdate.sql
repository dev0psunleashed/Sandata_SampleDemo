SET SERVEROUTPUT ON;

DECLARE

  NULL_RESULT EXCEPTION;

  l_date DATE;

BEGIN

  l_date := PKG_STAFF_UTIL.VERIFIED_START_DATE('1');

  IF l_date IS NULL THEN
    RAISE NULL_RESULT;
  END IF;

  IF l_date = TO_DATE('2015-11-11 12:00:00', 'YYYY-MM-DD HH24:MI:SS') THEN
    dbms_output.put_line('STAFF_SK: [1]: SUCCESS: --> ' || l_date);
  ELSE
    dbms_output.put_line('STAFF_SK: [1]: FAILED: --> [EXPECTED: 2015-11-11 12:00:00] != ' || l_date);
  END IF;

  EXCEPTION

    WHEN NULL_RESULT THEN
      dbms_output.put_line('WARNING: NO RESULT!');

    --WHEN OTHERS THEN
    --  dbms_output.put_line('ERROR: UNKNOWN!');

END;
