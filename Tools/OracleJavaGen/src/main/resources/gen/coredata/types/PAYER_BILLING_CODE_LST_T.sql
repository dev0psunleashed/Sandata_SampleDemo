CREATE OR REPLACE TYPE PAYER_BILLING_CODE_LST_T AS OBJECT (
PAYER_BILLING_CODE_LST_SK	NUMBER(38),
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
PAYER_ID	VARCHAR2(50 BYTE),
BILLING_CODE	VARCHAR2(20 BYTE)
);
/
