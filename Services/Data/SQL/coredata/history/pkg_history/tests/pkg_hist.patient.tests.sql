SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_patient PT_T;

  l_random_num NUMBER;
  l_result NUMBER;

BEGIN

  l_random_num := Round(dbms_random.value(1,1000000),0);

  dbms_output.put_line('l_random_num: ' || l_random_num);

  -- Init Object
  l_patient := PT_T(
          1,
          '20160805012616941',
          TO_DATE('2016-08-05 13:26:16', 'YYYY-MM-DD HH24:MI:SS'),
          CURRENT_TIMESTAMP,
          TO_DATE('2016-08-05 13:26:16', 'YYYY-MM-DD HH24:MI:SS'),
          TO_DATE('9999-12-31 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
          'utest_george',
          'PLSQL Test Script',
          NULL,
          'UPDATE: ' || l_random_num,
          0,
          1,
          '1',
          'US/Eastern',
          'ACTIVE',
          NULL,
          NULL,
          '999999999',
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          'David',
          'Rutgos',
          NULL,
          NULL,
          TO_DATE('2001-08-05 13:26:16', 'YYYY-MM-DD HH24:MI:SS'),
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
          NULL,
          NULL,
          NULL,
          NULL);

  l_result := PKG_HIST.updatePatient(l_patient);

  IF l_result > 1 THEN
    COMMIT;
    dbms_output.put_line ('PKG_HIST.updatePatient(l_patient): *** SUCCESS! ***: l_result = ' || l_result);
  ELSE
    ROLLBACK;
    dbms_output.put_line ('PKG_HIST.updatePatient(l_patient): *** FAILED! ***: l_result = ' || l_result);
  END IF;

EXCEPTION

  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  --WHEN OTHERS THEN
  --ROLLBACK;
  --dbms_output.put_line('ERROR: UNKNOWN!');

END;
