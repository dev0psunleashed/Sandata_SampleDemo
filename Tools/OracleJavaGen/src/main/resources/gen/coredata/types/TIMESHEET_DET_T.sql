CREATE OR REPLACE TYPE TIMESHEET_DET_T AS OBJECT (
TIMESHEET_DET_SK	NUMBER(38),
TIMESHEET_SUMMARY_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CHANGE_REASON_CODE	VARCHAR2(100 BYTE),
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
CHANGE_VERSION_ID	NUMBER(38),
BE_ID	VARCHAR2(50 BYTE),
PT_ID	VARCHAR2(50 BYTE),
VISIT_SK	NUMBER(38),
BE_CALENDAR_LKUP_SK	NUMBER(38),
SVC_ACTIVITY_BILLING_CODE_SK	NUMBER(38),
PR_CODE	VARCHAR2(20 BYTE),
TIMESHEET_DATE DATE,
PR_RATE_AMT	NUMBER(7, 2),
PR_HRS	NUMBER(8),
PR_AMT	NUMBER(7, 2),
RELEASE_TO_BILL_IND	NUMBER(1)
);
/
