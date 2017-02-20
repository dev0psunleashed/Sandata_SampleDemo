CREATE OR REPLACE
TYPE payroll_find_result_typ IS OBJECT (

  payroll_sk          NUMBER(38,0),
  staff_first_name    VARCHAR2(200 BYTE),
  staff_last_name     VARCHAR2(200 BYTE),
  staff_id            VARCHAR2(50 BYTE),
  service_name        VARCHAR2(50 BYTE),
  pay_hours           NUMBER(5, 2),
  check_number        VARCHAR2(50 BYTE),
  check_date          VARCHAR2(10 BYTE),
  CheckAmount         NUMBER(6, 2),
  pay_end_date        VARCHAR2(10 BYTE)
)
/

--CREATE OR REPLACE TYPE payroll_find_result_tab_typ AS TABLE OF payroll_find_result_typ;
--/
