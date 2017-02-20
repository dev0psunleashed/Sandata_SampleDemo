SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_cursor SYS_REFCURSOR;
  l_inv_det_cursor SYS_REFCURSOR;

  l_inv_sk_array NUMBER_ARRAY;

  l_inv_record INV%ROWTYPE;

BEGIN

  dbms_output.put_line ('*** START: PKG_BILLING_UTIL.GET_INV_RECORDS ***');

  l_inv_sk_array := NUMBER_ARRAY (5,1,2);

  l_cursor := COREDATA.D_PKG_BILLING_UTIL.GET_INV_RECORDS(l_inv_sk_array);

  IF l_cursor IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  LOOP
    FETCH l_cursor INTO l_inv_record,l_inv_det_cursor;

    EXIT WHEN l_cursor%NOTFOUND;

    dbms_output.put_line ('l_cursor: l_inv_record.INV_SK = ' || l_inv_record.INV_SK);

    IF l_inv_det_cursor IS NOT NULL THEN
      CLOSE l_inv_det_cursor;
    END IF;

  END LOOP;

  CLOSE l_cursor;

  dbms_output.put_line ('*** END: PKG_BILLING_UTIL.GET_INV_RECORDS ***' || CHR(13) || CHR(10));

  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  WHEN OTHERS THEN
  CLOSE l_cursor;
  dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;
