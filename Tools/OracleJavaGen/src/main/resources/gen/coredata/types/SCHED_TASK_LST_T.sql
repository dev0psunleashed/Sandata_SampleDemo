CREATE OR REPLACE TYPE SCHED_TASK_LST_T AS OBJECT (
SCHED_TASK_LST_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
CHANGE_VERSION_ID	NUMBER(38),
SCHED_SK	NUMBER(38),
BE_ID	VARCHAR2(50 BYTE),
BE_TASK_ID	VARCHAR2(64 BYTE)
);
/
