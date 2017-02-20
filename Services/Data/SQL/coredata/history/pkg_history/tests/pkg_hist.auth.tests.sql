SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_auth AUTH_T;

  l_random_num NUMBER;
  l_result NUMBER;

BEGIN

  l_random_num := Round(dbms_random.value(1,1000000),0);

  dbms_output.put_line('l_random_num: ' || l_random_num);

  -- Init Object
  l_auth := AUTH_T(
          4939,
          NULL,
          'fec8e473-9195-40d',
          TO_DATE('2016-08-16 12:15:55', 'YYYY-MM-DD HH24:MI:SS'),
          CURRENT_TIMESTAMP,
          TO_DATE('2016-08-16 12:15:55', 'YYYY-MM-DD HH24:MI:SS'),
          TO_DATE('9999-12-31 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
          'N/A',
          'PLSQL Test Script',
          'UPDATE: ' || l_random_num,
          1,
          79,
          '1',
          '20160816120621585',
          '6c674d30-289f-46b3-b',
          NULL,
          NULL,
          TO_DATE('2016-08-16 12:15:55', 'YYYY-MM-DD HH24:MI:SS'),
          TO_DATE('2016-08-16 13:15:55', 'YYYY-MM-DD HH24:MI:SS'),
          NULL,
          'NONE',
          'HOUR',
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

  l_result := PKG_HIST.updateAuth(l_auth);

  IF l_result = 1 THEN
    COMMIT;
    dbms_output.put_line ('PKG_HIST.updateAuth(l_auth): *** SUCCESS! ***: l_result = ' || l_result);
  ELSE
    ROLLBACK;
    dbms_output.put_line ('PKG_HIST.updateAuth(l_auth): *** FAILED! ***: l_result = ' || l_result);
  END IF;

EXCEPTION

  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  --WHEN OTHERS THEN
  --ROLLBACK;
  --dbms_output.put_line('ERROR: UNKNOWN!');

END;
