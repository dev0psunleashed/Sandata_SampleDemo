CREATE OR REPLACE TYPE APP_SECR_GRP_T AS OBJECT (
APP_SECR_GRP_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
SECR_GRP_NAME	VARCHAR2(100 BYTE),
APP_NAME	VARCHAR2(100 BYTE)
);
/
