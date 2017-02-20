CREATE OR REPLACE TYPE APP_SECR_ELT_HIST_T AS OBJECT (
APP_SECR_ELT_HIST_SK	NUMBER(38),
REC_CHANGE_TMSTP DATE,
REC_CHANGE_CODE	VARCHAR2(8 BYTE),
APP_SECR_ELT_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
APP_TENANT_SK	NUMBER(38),
APP_FUNC_ELT_SK	NUMBER(38),
APP_FUN_SK	NUMBER(38),
APP_MOD_SK	NUMBER(38),
SECR_ELT_NAME	VARCHAR2(100 BYTE),
INCL_INHC_IND	NUMBER(1)
);
/