CREATE OR REPLACE TYPE SCHED_EVNT_SVC_T AS OBJECT (
SCHED_EVNT_SVC_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_EFF_TMSTP DATE,
REC_TERM_TMSTP DATE,
REC_UPDATED_BY	VARCHAR2(50 BYTE),
REC_CREATED_BY	VARCHAR2(50 BYTE),
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
CURR_REC_IND	NUMBER(1),
CHANGE_VERSION_ID	NUMBER(38),
SCHED_EVNT_SK	NUMBER(38),
SVC_SK	NUMBER(38)
);
/
