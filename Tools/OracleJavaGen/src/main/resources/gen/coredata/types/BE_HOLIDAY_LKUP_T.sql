CREATE OR REPLACE TYPE BE_HOLIDAY_LKUP_T AS OBJECT (
BE_HOLIDAY_LKUP_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_EFF_TMSTP DATE,
REC_TERM_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CURR_REC_IND	NUMBER(1),
CHANGE_VERSION_ID	NUMBER(38),
BE_ID	VARCHAR2(50 BYTE),
HOLIDAY_NAME	VARCHAR2(100 BYTE),
HOLIDAY_START_DTIME DATE,
HOLIDAY_END_DTIME DATE,
HOLIDAY_FULL_DAY_IND	NUMBER(1),
HOLIDAY_BILL_IND	NUMBER(1),
HOLIDAY_PAY_IND	NUMBER(1)
);
/