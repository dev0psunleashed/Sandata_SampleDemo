SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_staff STAFF_T;

  l_random_num NUMBER;
  l_result NUMBER;

BEGIN

  l_random_num := Round(dbms_random.value(1,1000000),0);

  dbms_output.put_line('l_random_num: ' || l_random_num);

  -- Init Object
  l_staff := STAFF_T(
          1,
          '1',
          TO_DATE('2016-03-10 04:55:17', 'YYYY-MM-DD HH24:MI:SS'),
          CURRENT_TIMESTAMP,
          TO_DATE('2016-03-10 04:55:17', 'YYYY-MM-DD HH24:MI:SS'),
          TO_DATE('9999-12-31 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
          'N/A',
          'PLSQL Test Script',
          'UPDATE: ' || l_random_num,
          1,
          12835,
          '1',
          'US/Eastern',
          'ACTIVE',
          'SSN',
          '100226577',
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          'David',
          NULL,
          NULL,
          'Rutgos',
          NULL,
          NULL,
          NULL,
          '1',
          NULL,
          NULL,
          NULL,
          NULL,
          TO_DATE('0001-01-01 12:00:00', 'YYYY-MM-DD HH24:MI:SS'),
          NULL,
          TO_DATE('2016-03-28 12:00:00', 'YYYY-MM-DD HH24:MI:SS'),
          'MALE',
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          'RN',
          NULL);

  l_result := PKG_HIST.updateStaff(l_staff);

  IF l_result > 1 THEN
    COMMIT;
    dbms_output.put_line ('PKG_HIST.updateStaff(l_staff): *** SUCCESS! ***: l_result = ' || l_result);
  ELSE
    ROLLBACK;
    dbms_output.put_line ('PKG_HIST.updateStaff(l_staff): *** FAILED! ***: l_result = ' || l_result);
  END IF;

EXCEPTION

  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  --WHEN OTHERS THEN
  --ROLLBACK;
  --dbms_output.put_line('ERROR: UNKNOWN!');

END;
