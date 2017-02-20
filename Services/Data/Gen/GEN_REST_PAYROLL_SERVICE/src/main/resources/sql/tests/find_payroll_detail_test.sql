SET SERVEROUTPUT ON

DECLARE

  c_result        PKG_PAYROLL_DATA.payroll_detail_cursor;
  pr_row          COREDATA.payroll_find_result_typ;
  pr_filter_typ   COREDATA.payroll_find_filter_typ;

  test_val        NUMBER;

BEGIN

  pr_row := COREDATA.payroll_find_result_typ(null,null,null,null,null,null,null,null,null,null);
  pr_filter_typ := COREDATA.payroll_find_filter_typ(
                        '1',null,'Jim',null,null,null,'2015-11-01','2015-12-31',null,null,null,'ALL',1,10,'sfn','DESC');
  c_result := PKG_PAYROLL_DATA.FIND_PAYROLL_DETAIL(pr_filter_typ);

  LOOP

    FETCH c_result INTO pr_row.payroll_sk,test_val;
    --Set condition for the exit
    EXIT WHEN c_result%NOTFOUND;

    dbms_output.put_line (pr_row.payroll_sk || ':-->' || test_val);

  END LOOP;

  CLOSE C_RESULT;

END;
