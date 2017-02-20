CREATE OR REPLACE TYPE SCHED_PTC_EXCL_T AS OBJECT (
SCHED_PTC_EXCL_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
REC_EFF_TMSTP DATE,
REC_TERM_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CURR_REC_IND	NUMBER(1),
CHANGE_VERSION_ID	NUMBER(38),
BE_ID	VARCHAR2(50 BYTE),
PT_ID	VARCHAR2(50 BYTE),
STAFF_ID	VARCHAR2(50 BYTE),
SCHED_PTC_EXCL_QLFR	VARCHAR2(50 BYTE),
SCHED_PERM_QLFR	VARCHAR2(50 BYTE),
SCHED_PTC_EXCL_NOTE	VARCHAR2(1000 BYTE)
);
/
