CREATE OR REPLACE TYPE PT_CONT_PHONE_T AS OBJECT (
PT_CONT_PHONE_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_EFF_TMSTP DATE,
REC_TERM_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CURR_REC_IND	NUMBER(1),
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
CHANGE_VERSION_ID	NUMBER(38),
BE_ID	VARCHAR2(50 BYTE),
PT_ID	VARCHAR2(50 BYTE),
ADDR_PRIO_NAME	VARCHAR2(50 BYTE),
PT_CONT_PHONE_QLFR	VARCHAR2(100 BYTE),
PT_PHONE	VARCHAR2(10 BYTE),
PT_PHONE_EXT	VARCHAR2(8 BYTE),
PT_PHONE_ANI_ENABLED_IND	NUMBER(1),
PT_PHONE_PRMY_IND	NUMBER(1),
PT_PHONETEXT_MSG_IND	NUMBER(1)
);
/
