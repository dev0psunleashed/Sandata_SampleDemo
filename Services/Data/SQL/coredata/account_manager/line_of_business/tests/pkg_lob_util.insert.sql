SET SERVEROUTPUT ON;

DECLARE

  l_cursor SYS_REFCURSOR;

  l_be_lob_array BE_LOB_ARRAY;

  l_result NUMBER;

BEGIN

  dbms_output.put_line ('*** START: PKG_LOB_UTIL.INSERT_BE_LOB ***');

  l_be_lob_array := BE_LOB_ARRAY('MCO','ABC','FIDA');

  l_result := COREDATA.PKG_LOB_UTIL.INSERT_BE_LOB('1', l_be_lob_array);

  IF l_result = 0 THEN
    dbms_output.put_line ('l_result = 0 ');
  END IF;

  dbms_output.put_line ('*** END: PKG_LOB_UTIL.INSERT_BE_LOB ***' || CHR(13) || CHR(10));

END;