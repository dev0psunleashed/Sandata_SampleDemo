SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_inv_sk_array NUMBER_ARRAY;

  result_code NUMBER(38,0);

BEGIN

  dbms_output.put_line ('*** START: PKG_BILLING_UTIL.DELETE_INV ***');

  l_inv_sk_array := NUMBER_ARRAY (1);

  result_code := COREDATA.D_PKG_BILLING_UTIL.DELETE_INV(l_inv_sk_array);

  dbms_output.put_line (CHR(9) || 'result_code = ' || result_code);

  dbms_output.put_line ('*** END: PKG_BILLING_UTIL.DELETE_INV ***' || CHR(13) || CHR(10));

  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  WHEN OTHERS THEN
  dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;
