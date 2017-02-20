SET SERVEROUTPUT ON;

DECLARE

  NULL_RESULT EXCEPTION;

  l_hire_date STAFF.STAFF_HIRE_DATE%TYPE;

BEGIN

  l_hire_date := PKG_STAFF_UTIL.REHIRE_DATE('20160708113523717', '1');

  IF l_hire_date IS NULL THEN
    RAISE NULL_RESULT;
  END IF;

  IF l_hire_date = TO_DATE('2016-06-01 11:35:25', 'YYYY-MM-DD HH24:MI:SS') THEN
    dbms_output.put_line('STAFF_ID: [20160708113523717]: SUCCESS: --> ' || l_hire_date);
  ELSE
    dbms_output.put_line('STAFF_ID: [20160708113523717]: FAILED: --> [EXPECTED: 2016-06-01 11:35:25] != ' || l_hire_date);
  END IF;

  EXCEPTION

    WHEN NULL_RESULT THEN
      dbms_output.put_line('WARNING: NO RESULT!');

    WHEN OTHERS THEN
      dbms_output.put_line('ERROR: UNKNOWN!');

END;
