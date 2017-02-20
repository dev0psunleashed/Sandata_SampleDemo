SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_result NUMBER;

BEGIN

  dbms_output.put_line ('*** START: PKG_AUDIT.INSERT_DATA_POINTS ***');

  l_result := METADATA.PKG_AUDIT.INSERT_DATA_POINTS('patientLastName', 'rutgos', 'Rutgos');

  dbms_output.put_line ('l_result = ' || l_result);

  dbms_output.put_line ('*** END: PKG_AUDIT.INSERT_DATA_POINTS ***' || CHR(13) || CHR(10));

END;
