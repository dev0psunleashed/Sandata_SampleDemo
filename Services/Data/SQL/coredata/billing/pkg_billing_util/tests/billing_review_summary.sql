SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_inv_find_result INV_FIND_RESULT_T;

  l_cursor SYS_REFCURSOR;
  l_lob_cursor SYS_REFCURSOR;
  l_payer_cursor SYS_REFCURSOR;
  l_subm_typ_payer_cursor SYS_REFCURSOR;
  l_subm_typ_cursor SYS_REFCURSOR;
  l_inv_det_typ_cursor SYS_REFCURSOR;

  l_inv_sk_array NUMBER_ARRAY;

  l_lob_name BE_LOB_LKUP.BE_LOB%TYPE;
  l_payer_name PAYER.PAYER_NAME%TYPE;
  l_subm_typ INV.INV_SUBM_TYP_NAME%TYPE;

  l_subm_date DATE;
  l_total_inv_billed NUMBER(38,0);
  l_total_amt_billed NUMBER(38,2);
  l_billed_hours NUMBER(38,0);

  l_counter NUMBER(38,0);

BEGIN

  dbms_output.put_line ('*** START: PKG_BILLING_UTIL.BILLABLE_REVIEW_SUMMARY ***');

  l_inv_sk_array := NUMBER_ARRAY (5,1,2);

  l_cursor := COREDATA.PKG_BILLING_UTIL.BILLABLE_REVIEW_SUMMARY(l_inv_sk_array);

  IF l_cursor IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  LOOP
    FETCH l_cursor INTO l_subm_date,l_total_inv_billed,l_total_amt_billed,l_billed_hours,
    l_lob_cursor,l_payer_cursor,l_subm_typ_cursor,l_inv_det_typ_cursor;

    EXIT WHEN l_cursor%NOTFOUND;

    dbms_output.put_line ('l_cursor: l_subm_date = ' || TO_CHAR(l_subm_date, 'YYYY-MM-DD'));
    dbms_output.put_line ('l_cursor: l_total_inv_billed = ' || l_total_inv_billed);
    dbms_output.put_line ('l_cursor: l_total_amt_billed = ' || l_total_amt_billed);
    dbms_output.put_line ('l_cursor: l_billed_hours = ' || l_billed_hours || CHR(13) || CHR(10));

    IF l_lob_cursor IS NOT NULL THEN

      l_counter := 1;

      LOOP
        FETCH l_lob_cursor INTO l_lob_name,l_total_inv_billed,l_total_amt_billed,l_billed_hours;

        EXIT WHEN l_lob_cursor%NOTFOUND;

        dbms_output.put_line ('*** l_lob_cursor [' || l_counter || '] ***');

        dbms_output.put_line ('l_lob_cursor: l_lob_name = ' || l_lob_name);
        dbms_output.put_line (CHR(9) || 'l_lob_cursor: l_total_inv_billed = ' || l_total_inv_billed);
        dbms_output.put_line (CHR(9) || 'l_lob_cursor: l_total_amt_billed = ' || l_total_amt_billed);
        dbms_output.put_line (CHR(9) || 'l_lob_cursor: l_billed_hours = ' || l_billed_hours || CHR(13) || CHR(10));

        l_counter := l_counter + 1;

      END LOOP;

      CLOSE l_lob_cursor;
    END IF;

    IF l_payer_cursor IS NOT NULL THEN

      l_counter := 1;

      LOOP
        FETCH l_payer_cursor INTO l_payer_name,l_total_inv_billed,l_total_amt_billed,l_billed_hours;

        EXIT WHEN l_payer_cursor%NOTFOUND;

        dbms_output.put_line ('*** l_payer_cursor [' || l_counter || '] ***');

        dbms_output.put_line ('l_payer_cursor: l_payer_name = ' || l_payer_name);
        dbms_output.put_line (CHR(9) || 'l_payer_cursor: l_total_inv_billed = ' || l_total_inv_billed);
        dbms_output.put_line (CHR(9) || 'l_payer_cursor: l_total_amt_billed = ' || l_total_amt_billed);
        dbms_output.put_line (CHR(9) || 'l_payer_cursor: l_billed_hours = ' || l_billed_hours || CHR(13) || CHR(10));

        l_counter := l_counter + 1;

      END LOOP;

      CLOSE l_payer_cursor;
    END IF;

    IF l_subm_typ_cursor IS NOT NULL THEN

      l_counter := 1;

      LOOP
        FETCH l_subm_typ_cursor INTO l_subm_typ,l_subm_typ_payer_cursor;

        EXIT WHEN l_subm_typ_cursor%NOTFOUND;

        dbms_output.put_line ('*** l_subm_typ_cursor [' || l_counter || '] ***');

        dbms_output.put_line ('l_subm_typ_cursor: l_subm_typ = ' || l_subm_typ);

        IF l_subm_typ_payer_cursor IS NOT NULL THEN

          CLOSE l_subm_typ_payer_cursor;

        END IF;

        l_counter := l_counter + 1;

      END LOOP;

      CLOSE l_subm_typ_cursor;
    END IF;

    IF l_inv_det_typ_cursor IS NOT NULL THEN

      l_counter := 1;

      LOOP
        FETCH l_inv_det_typ_cursor INTO
        l_inv_find_result.BE_LOC_ID,
        l_inv_find_result.INV_SK,
        l_inv_find_result.BE_LOB_ID,
        l_inv_find_result.PAYER_NAME,
        l_inv_find_result.CONTR_DESC,
        l_inv_find_result.INV_START_DATE,
        l_inv_find_result.INV_END_DATE,
        l_inv_find_result.INV_NUM,
        l_inv_find_result.INV_DATE,
        l_inv_find_result.PT_FIRST_NAME,
        l_inv_find_result.PT_LAST_NAME,
        l_inv_find_result.PT_INS_ID_NUM,
        l_inv_find_result.BE_NAME,
        l_inv_find_result.INV_TOTAL_AMT,
        l_inv_find_result.INV_SUBM_TYP_NAME;

        EXIT WHEN l_inv_det_typ_cursor%NOTFOUND;

        dbms_output.put_line ('*** l_inv_det_typ_cursor [' || l_counter || '] ***');
        dbms_output.put_line ('l_inv_det_typ_cursor: BE_LOC_ID = ' || l_inv_find_result.BE_LOC_ID);
        dbms_output.put_line ('l_inv_det_typ_cursor: INV_SK = ' || l_inv_find_result.INV_SK);
        dbms_output.put_line ('l_inv_det_typ_cursor: BE_LOB_ID = ' || l_inv_find_result.BE_LOB_ID);
        dbms_output.put_line ('l_inv_det_typ_cursor: PAYER_NAME = ' || l_inv_find_result.PAYER_NAME);
        dbms_output.put_line ('l_inv_det_typ_cursor: CONTR_DESC = ' || l_inv_find_result.CONTR_DESC);
        dbms_output.put_line ('l_inv_det_typ_cursor: INV_START_DATE = ' || TO_CHAR(l_inv_find_result.INV_START_DATE, 'YYYY-MM-DD'));
        dbms_output.put_line ('l_inv_det_typ_cursor: INV_END_DATE = ' || TO_CHAR(l_inv_find_result.INV_END_DATE, 'YYYY-MM-DD'));
        dbms_output.put_line ('l_inv_det_typ_cursor: INV_NUM = ' || l_inv_find_result.INV_NUM);
        dbms_output.put_line ('l_inv_det_typ_cursor: INV_DATE = ' || TO_CHAR(l_inv_find_result.INV_DATE, 'YYYY-MM-DD'));
        dbms_output.put_line ('l_inv_det_typ_cursor: PT_FIRST_NAME = ' || l_inv_find_result.PT_FIRST_NAME);
        dbms_output.put_line ('l_inv_det_typ_cursor: PT_LAST_NAME = ' || l_inv_find_result.PT_LAST_NAME);
        dbms_output.put_line ('l_inv_det_typ_cursor: PT_INS_ID_NUM = ' || l_inv_find_result.PT_INS_ID_NUM);
        dbms_output.put_line ('l_inv_det_typ_cursor: BE_NAME = ' || l_inv_find_result.BE_NAME);
        dbms_output.put_line ('l_inv_det_typ_cursor: INV_TOTAL_AMT = ' || l_inv_find_result.INV_TOTAL_AMT);
        dbms_output.put_line ('l_inv_det_typ_cursor: INV_SUBM_TYP_NAME = ' || l_inv_find_result.INV_SUBM_TYP_NAME || CHR(13) || CHR(10));

        l_counter := l_counter + 1;

      END LOOP;

      CLOSE l_inv_det_typ_cursor;
    END IF;

  END LOOP;

  CLOSE l_cursor;

  dbms_output.put_line ('*** END: PKG_BILLING_UTIL.BILLABLE_REVIEW_SUMMARY ***' || CHR(13) || CHR(10));

  EXCEPTION
  WHEN NULL_EXCEPTION THEN
  dbms_output.put_line('ERROR: RESULT == NULL');

  WHEN OTHERS THEN
  CLOSE l_cursor;
  dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');

END;