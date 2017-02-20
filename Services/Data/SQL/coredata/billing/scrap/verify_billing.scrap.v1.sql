SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_visit VISIT%ROWTYPE;

  l_agency BE%ROWTYPE;

  l_visit_sk VISIT.VISIT_SK%TYPE;

  l_cursor SYS_REFCURSOR;

BEGIN

  dbms_output.put_line ('*** START: PKG_BILLING_DEBUG ***' || CHR(13) || CHR(10));

  l_visit_sk := 744444;
  --l_visit_sk := &VisitSK;

  l_cursor := PKG_BILLING_DEBUG.GET_VISIT(l_visit_sk);

  IF l_cursor IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  FETCH l_cursor INTO l_visit;

  dbms_output.put_line ('l_visit.VISIT_APRVD_IND = [' || l_visit.VISIT_APRVD_IND || ']');
  dbms_output.put_line ('l_visit.VISIT_DO_NOT_BILL_IND = [' || l_visit.VISIT_DO_NOT_BILL_IND || ']');
  dbms_output.put_line ('l_visit.VISIT_CANCELLED_IND = [' || l_visit.VISIT_CANCELLED_IND || ']');
  dbms_output.put_line (CHR(13));
  dbms_output.put_line ('l_visit.VISIT_ACT_START_TMSTP = [' || l_visit.VISIT_ACT_START_TMSTP || ']');
  dbms_output.put_line ('l_visit.VISIT_ACT_END_TMSTP = [' || l_visit.VISIT_ACT_END_TMSTP || ']');
  dbms_output.put_line ('l_visit.VISIT_ADJ_START_TMSTP = [' || l_visit.VISIT_ADJ_START_TMSTP || ']');
  dbms_output.put_line ('l_visit.VISIT_ADJ_END_TMSTP = [' || l_visit.VISIT_ADJ_END_TMSTP || ']');
  dbms_output.put_line (CHR(13));
  dbms_output.put_line ('l_visit.BE_ID = [' || l_visit.BE_ID || ']');
  dbms_output.put_line ('l_visit.PT_ID = [' || l_visit.PT_ID || ']');
  dbms_output.put_line ('l_visit.STAFF_ID = [' || l_visit.STAFF_ID || ']');
  dbms_output.put_line ('l_visit.SCHED_EVNT_SK = [' || l_visit.SCHED_EVNT_SK || ']');
  dbms_output.put_line (CHR(13));

  CLOSE l_cursor;

  l_cursor := PKG_BILLING_DEBUG.GET_AGENCY(l_visit.BE_ID);

  IF l_cursor IS NULL THEN
    RAISE NULL_EXCEPTION;
  END IF;

  FETCH l_cursor INTO l_agency;
  CLOSE l_cursor;

  dbms_output.put_line ('*** END: PKG_BILLING_DEBUG ***' || CHR(13) || CHR(10));

  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      dbms_output.put_line ('*** ERROR ***: l_visit_sk=[' || l_visit_sk || ']: DOES NOT EXIST!');

      WHEN OTHERS THEN
        dbms_output.put_line('ERROR: UNHANDLED EXCEPTION!');
END;
