CREATE OR REPLACE TYPE TIMESHEET_SUMMARY_T AS OBJECT (
TIMESHEET_SUMMARY_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
TIMESHEET_SUMMARY_ID	VARCHAR2(50 BYTE),
CHANGE_VERSION_ID	NUMBER(38),
BE_ID	VARCHAR2(50 BYTE),
BE_LOB_ID	VARCHAR2(50 BYTE),
STAFF_ID	VARCHAR2(50 BYTE),
BE_CALENDAR_LKUP_SK	NUMBER(38),
TIMESHEET_WEEK_START_DATE DATE,
TIMESHEET_WEEK_END_DATE DATE,
TIMESHEET_OT_HRS	NUMBER(8),
TIMESHEET_WEEK_TOTAL_HRS	NUMBER(8)
);
/
