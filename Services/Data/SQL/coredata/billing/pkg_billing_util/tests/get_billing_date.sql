SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_visit_date DATE;

BEGIN

  dbms_output.put_line ('*** START: PKG_BILLING_UTIL.GET_BILLING_DATE ***');

  l_visit_date := COREDATA.PKG_BILLING_UTIL.GET_BILLING_DATE(77103);

  dbms_output.put_line (CHR(9) || 'l_visit_date = ' || l_visit_date);

  dbms_output.put_line ('*** END: PKG_BILLING_UTIL.GET_BILLING_DATE ***' || CHR(13) || CHR(10));

  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  WHEN OTHERS THEN
  dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;
