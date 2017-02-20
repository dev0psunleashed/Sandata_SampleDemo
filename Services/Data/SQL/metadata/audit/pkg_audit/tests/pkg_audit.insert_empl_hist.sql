SET SERVEROUTPUT ON;

DECLARE

  NULL_EXCEPTION EXCEPTION;

  l_empl_status_hist AUDIT_EMPL_STATUS_HIST_T;

  l_result NUMBER;

BEGIN

  dbms_output.put_line ('*** START: PKG_AUDIT.INSERT_EMPL_STATUS_HIST ***');

  l_empl_status_hist := AUDIT_EMPL_STATUS_HIST_T(
              '999|123456',
              'c59993c0-15c1-4c45-a2d8-6a3e7767d42a',
              TO_DATE('2017-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
              'TEST',
              'This is a reason code!',
              'This is a Note!',
              TO_DATE('2017-01-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS')
  );

  l_result := METADATA.PKG_AUDIT.INSERT_EMPL_STATUS_HIST(l_empl_status_hist);

  dbms_output.put_line ('l_result = ' || l_result);

  dbms_output.put_line ('*** END: PKG_AUDIT.INSERT_EMPL_STATUS_HIST ***' || CHR(13) || CHR(10));

END;
