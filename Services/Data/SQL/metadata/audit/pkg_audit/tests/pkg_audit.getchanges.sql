SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_cursor SYS_REFCURSOR;

  l_result AUDIT_CHANGED_T;

BEGIN

  dbms_output.put_line ('*** START: PKG_AUDIT.GET_CHANGES ***');

  l_result := AUDIT_CHANGED_T(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

  l_cursor := METADATA.PKG_AUDIT.GET_CHANGES('999', 559999955);

  IF l_cursor IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  LOOP
    FETCH l_cursor INTO
        l_result.USER_NAME,
        l_result.USER_FIRST_NAME,
        l_result.USER_LAST_NAME,
        l_result.DATA_POINT,
        l_result.CHANGED_FROM,
        l_result.CHANGED_TO,
        l_result.CHANGED_ON;

    EXIT WHEN l_cursor%NOTFOUND;

    dbms_output.put_line ('l_cursor: l_result.USER_NAME = ' || l_result.USER_NAME);
    dbms_output.put_line ('l_cursor: l_result.USER_FIRST_NAME = ' || l_result.USER_FIRST_NAME);
    dbms_output.put_line ('l_cursor: l_result.USER_LAST_NAME = ' || l_result.USER_LAST_NAME);
    dbms_output.put_line ('l_cursor: l_result.DATA_POINT = ' || l_result.DATA_POINT);
    dbms_output.put_line ('l_cursor: l_result.CHANGED_FROM = ' || l_result.CHANGED_FROM);
    dbms_output.put_line ('l_cursor: l_result.CHANGED_TO = ' || l_result.CHANGED_TO);
    dbms_output.put_line ('l_cursor: l_result.CHANGED_ON = ' || l_result.CHANGED_ON || CHR(13) || CHR(10));

  END LOOP;

  CLOSE l_cursor;

  dbms_output.put_line ('*** END: PKG_AUDIT.GET_CHANGES ***' || CHR(13) || CHR(10));

  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  --WHEN OTHERS THEN
  --CLOSE l_cursor;
  --dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;
