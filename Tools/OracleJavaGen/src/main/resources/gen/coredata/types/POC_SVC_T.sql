CREATE OR REPLACE TYPE POC_SVC_T AS OBJECT (
POC_SVC_SK	NUMBER(38),
POC_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
CHANGE_VERSION_ID	NUMBER(38),
BE_ID	VARCHAR2(50 BYTE),
SVC_NAME	VARCHAR2(20 BYTE)
);
/
