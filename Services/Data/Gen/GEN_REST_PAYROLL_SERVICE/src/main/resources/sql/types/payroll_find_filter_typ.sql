CREATE OR REPLACE
TYPE payroll_find_filter_typ AS OBJECT (

  BSN_ENT_ID          VARCHAR2(50 BYTE),
  STAFF_ID	          VARCHAR2(50 BYTE),
  STAFF_FIRST_NAME	  VARCHAR2(200 BYTE),
  STAFF_LAST_NAME	    VARCHAR2(200 BYTE),
  PAY_HOURS	          NUMBER(5, 2),       --PR_OUTPUT.PR_TIMESHEET_TOTAL_HRS
  SERVICE_NAME	      VARCHAR2(200 BYTE),
  FROM_DATE           VARCHAR2(10 BYTE),
  TO_DATE             VARCHAR2(10 BYTE),
  CHECK_NUMBER        VARCHAR2(50 BYTE),
  CHECK_DATE          VARCHAR2(10 BYTE),
  CHECK_AMOUNT	      NUMBER(6, 2),       --PR_INPUT.NET_PAY_AMT
  STATUS              VARCHAR2(7 BYTE),   --PENDING,PAID,ALL
  PAGE                NUMBER(38),
  PAGE_SIZE           NUMBER(38),
  SORT_ON             VARCHAR2(50 BYTE), --Table.Column Name
  DIRECTION           VARCHAR2(4 BYTE)  --ASC/DESC
)
/
