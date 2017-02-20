CREATE OR REPLACE TYPE APP_USER_ROLE_T AS OBJECT (
APP_USER_ROLE_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
APP_TENANT_SK	NUMBER(38),
APP_USER_SK	NUMBER(38),
APP_ROLE_SK	NUMBER(38),
ACTIVE_ROLE_IND	NUMBER(1)
);
/