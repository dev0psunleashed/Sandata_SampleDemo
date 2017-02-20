CREATE OR REPLACE TYPE APP_LOG_T AS OBJECT (
APP_LOG_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
APP_SESS_SK	NUMBER(38),
LOG_HOST	VARCHAR2(128 BYTE),
LOG_PROCESS	VARCHAR2(128 BYTE),
LOG_PID	NUMBER(38),
LOG_THREAD	NUMBER(38),
LOG_LVL	VARCHAR2(50 BYTE),
LOG_MSG	VARCHAR2(4000 BYTE)
);
/
