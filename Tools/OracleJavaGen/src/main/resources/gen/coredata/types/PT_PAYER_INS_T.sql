CREATE OR REPLACE TYPE PT_PAYER_INS_T AS OBJECT (
PT_PAYER_INS_SK	NUMBER(38),
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
PAYER_ID	VARCHAR2(50 BYTE),
PT_INS_GRP_NUM	VARCHAR2(50 BYTE),
PT_INS_GRP_NAME	VARCHAR2(50 BYTE),
PAYER_PLAN_NAME	VARCHAR2(100 BYTE),
PT_PAYER_PLAN_TYP	VARCHAR2(50 BYTE),
PT_PAYER_CVG_TYP	VARCHAR2(50 BYTE),
PT_INS_PROVIDER	VARCHAR2(100 BYTE),
PT_INS_ID_NUM	VARCHAR2(50 BYTE),
PT_INS_START_DATE DATE,
PT_INS_END_DATE DATE
);
/
