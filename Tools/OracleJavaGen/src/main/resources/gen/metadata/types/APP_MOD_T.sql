CREATE OR REPLACE TYPE APP_MOD_T AS OBJECT (
APP_MOD_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
APP_MOD_PAR_SK	NUMBER(38),
MOD_NAME	VARCHAR2(100 BYTE),
MOD_TYP_NAME	VARCHAR2(50 BYTE),
APP_NAME	VARCHAR2(100 BYTE)
);
/