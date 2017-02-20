SET SERVEROUTPUT ON;

DECLARE

  NULL_RESULT EXCEPTION;

  l_last_date_worked DATE;

BEGIN

  l_last_date_worked := PKG_STAFF_UTIL.LAST_DATE_WORKED('225724', '10');

  IF l_last_date_worked IS NULL THEN
    RAISE NULL_RESULT;
  END IF;

  IF l_last_date_worked = TO_DATE('2016-06-21 14:00:00', 'YYYY-MM-DD HH24:MI:SS') THEN
    dbms_output.put_line('STAFF_SK: [225724]: SUCCESS: --> ' || l_last_date_worked);
  ELSE
    dbms_output.put_line('STAFF_SK: [225724]: FAILED: --> [EXPECTED: 2016-06-21 14:00:00] != ' || l_last_date_worked);
  END IF;

  EXCEPTION

    WHEN NULL_RESULT THEN
      dbms_output.put_line('WARNING: NO RESULT!');

    --WHEN OTHERS THEN
    --  dbms_output.put_line('ERROR: UNKNOWN!');

END;
