CREATE OR REPLACE PACKAGE PKG_PAYROLL_DATA IS

  --Define global variable
  TYPE payroll_detail_cursor IS REF CURSOR;

  FUNCTION FIND_PAYROLL_DETAIL (pr_filter_typ IN COREDATA.payroll_find_filter_typ) RETURN payroll_detail_cursor;

END PKG_PAYROLL_DATA;
/

CREATE OR REPLACE PACKAGE BODY PKG_PAYROLL_DATA IS

  FUNCTION FIND_PAYROLL_DETAIL (pr_filter_typ IN COREDATA.payroll_find_filter_typ) RETURN payroll_detail_cursor
  AS

    c_result    payroll_detail_cursor;

  BEGIN

    --dbms_output.put_line ('Staff is: ' || pr_filter_typ.STAFF_FIRST_NAME );

    OPEN c_result FOR
      SELECT * FROM
      (
        SELECT i.pr_input_sk, (select count(*) FROM staff) AS TEST_VAL
        FROM pr_input i
          LEFT OUTER JOIN pr_output o ON i.pr_input_id = o.pr_input_id

      ) WHERE TEST_VAL >= 9296;

    RETURN c_result;

  END FIND_PAYROLL_DETAIL;

END PKG_PAYROLL_DATA;
/
