CREATE OR REPLACE TYPE POC_ACTIVITY_T AS OBJECT (
POC_ACTIVITY_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
CHANGE_VERSION_ID	NUMBER(38),
ACTIVITY_SK	NUMBER(38),
POC_SK	NUMBER(38)
);
/