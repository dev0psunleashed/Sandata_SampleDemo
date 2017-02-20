SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_audit_changes AUDIT_CHANGED_T;

  l_result NUMBER;

BEGIN

  dbms_output.put_line ('*** START: PKG_AUDIT.INSERT_CHANGES ***');

  l_audit_changes := AUDIT_CHANGED_T(NULL,NULL,NULL,NULL,'patientFirstName','Davis', 'David',NULL);

  l_result := METADATA.PKG_AUDIT.INSERT_CHANGES('999', 559999955, l_audit_changes);

  dbms_output.put_line ('l_result = ' || l_result);

  dbms_output.put_line ('*** END: PKG_AUDIT.INSERT_CHANGES ***' || CHR(13) || CHR(10));

END;
