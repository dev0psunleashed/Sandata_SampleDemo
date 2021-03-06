CREATE OR REPLACE TYPE BILLING_RATE_T AS OBJECT (
BILLING_RATE_SK	NUMBER(38),
BILLING_RATE_ID	VARCHAR2(50 BYTE),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_EFF_TMSTP DATE,
REC_TERM_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
CURR_REC_IND	NUMBER(1),
CHANGE_VERSION_ID	NUMBER(38),
BE_ID	VARCHAR2(50 BYTE),
PT_ID	VARCHAR2(50 BYTE),
PT_BILLING_RATE_UNIT	VARCHAR2(50 BYTE),
PT_BE_HOURLY_RATE	NUMBER(7, 2),
PT_PAYER_BILL_RATE	NUMBER(7, 2)
);
/
