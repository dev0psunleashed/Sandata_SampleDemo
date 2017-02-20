SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_result NUMBER;

BEGIN

  l_result := PKG_PR_UTIL_DEBUG.SERVICES_EXIST(5384, '''RN'',''HHA''');
  --l_result := PKG_PR_UTIL_DEBUG.SERVICES_EXIST(5384, '''RN''');
  --l_result := PKG_PR_UTIL_DEBUG.SERVICES_EXIST(5384, '''LPN''');

  IF l_result IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  IF l_result = 1 THEN
    dbms_output.put_line('SUCCESS: Expected Result: l_result := *** [' || l_result || ']: EXISTS! ***');
  ELSE
    dbms_output.put_line('FAILED: Expected Result: [1]: l_result := *** [' || l_result || '] DOES NOT EXIST! ***');
  END IF;

  EXCEPTION
    WHEN NULL_EXCEPTION THEN
      dbms_output.put_line('ERROR: RESULT == NULL');

    --WHEN OTHERS THEN
    --  dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');
END;
