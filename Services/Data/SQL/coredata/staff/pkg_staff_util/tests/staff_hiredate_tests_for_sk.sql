SET SERVEROUTPUT ON;

DECLARE

  NULL_RESULT EXCEPTION;

  l_hire_date_for_sk STAFF.STAFF_HIRE_DATE%TYPE;

BEGIN

  l_hire_date_for_sk := PKG_STAFF_UTIL.HIRE_DATE_BY_SK(21601);

  IF l_hire_date_for_sk IS NULL THEN
    RAISE NULL_RESULT;
  END IF;

  IF l_hire_date_for_sk = TO_DATE('2014-08-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS') THEN
    dbms_output.put_line('STAFF_SK: [1]: SUCCESS: --> ' || l_hire_date_for_sk);
  ELSE
    dbms_output.put_line('STAFF_SK: [1]: FAILED: --> [EXPECTED: 2014-08-12 00:00:00] != ' || l_hire_date_for_sk);
  END IF;

  EXCEPTION

    WHEN NULL_RESULT THEN
      dbms_output.put_line('WARNING: NO RESULT!');

    WHEN OTHERS THEN
      dbms_output.put_line('ERROR: UNKNOWN!');

END;
