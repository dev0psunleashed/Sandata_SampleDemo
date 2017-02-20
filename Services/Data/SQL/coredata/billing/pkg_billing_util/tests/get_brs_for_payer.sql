SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_cursor SYS_REFCURSOR;

  l_inv_sk_array NUMBER_ARRAY;

  l_payer_name PAYER.PAYER_NAME%TYPE;
  l_total_inv_billed NUMBER(38,0);
  l_total_amt_billed NUMBER(38,2);
  l_billed_hours NUMBER(38,0);

  l_counter NUMBER(38,0);

BEGIN

  dbms_output.put_line ('*** START: PKG_BILLING_UTIL.GET_BRS_FOR_PAYER ***');

  l_inv_sk_array := NUMBER_ARRAY (5,1,2);

  l_cursor := COREDATA.PKG_BILLING_UTIL.GET_BRS_FOR_PAYER(l_inv_sk_array);

  IF l_cursor IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  LOOP
    FETCH l_cursor INTO l_payer_name,l_total_inv_billed,l_total_amt_billed,l_billed_hours;

    EXIT WHEN l_cursor%NOTFOUND;

    dbms_output.put_line ('l_cursor: l_payer_name = ' || l_payer_name);
    dbms_output.put_line ('l_cursor: l_total_inv_billed = ' || l_total_inv_billed);
    dbms_output.put_line ('l_cursor: l_total_amt_billed = ' || l_total_amt_billed);
    dbms_output.put_line ('l_cursor: l_billed_hours = ' || l_billed_hours || CHR(13) || CHR(10));

  END LOOP;

  CLOSE l_cursor;

  dbms_output.put_line ('*** END: PKG_BILLING_UTIL.GET_BRS_FOR_PAYER ***' || CHR(13) || CHR(10));


  EXCEPTION
    WHEN NULL_EXCEPTION THEN
      dbms_output.put_line('ERROR: RESULT == NULL');

  WHEN OTHERS THEN
    CLOSE l_cursor;
      dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;