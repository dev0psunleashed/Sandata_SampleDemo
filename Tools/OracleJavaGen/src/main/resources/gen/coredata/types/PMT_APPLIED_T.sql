CREATE OR REPLACE TYPE PMT_APPLIED_T AS OBJECT (
PMT_APPLIED_SK	NUMBER(38),
PMT_APPLIED_ID	VARCHAR2(50 BYTE),
REC_CREATE_TMSTP DATE,
REC_EFF_TMSTP DATE,
REC_TERM_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
CHANGE_VERSION_ID	NUMBER(38),
CURR_REC_IND	NUMBER(1),
PMT_SK	NUMBER(38)
);
/