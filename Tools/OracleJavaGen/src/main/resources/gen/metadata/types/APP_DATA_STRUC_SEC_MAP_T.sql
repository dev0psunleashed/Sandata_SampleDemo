CREATE OR REPLACE TYPE APP_DATA_STRUC_SEC_MAP_T AS OBJECT (
APP_DATA_STRUC_SEC_MAP_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
DATA_SEC_COMP_TYP_NAME	VARCHAR2(50 BYTE),
DATA_SEC_CLAS_ID	VARCHAR2(50 BYTE),
APP_DATA_STRUC_SK	NUMBER(38),
DATA_STRUC_READ_IND	NUMBER(1),
DATA_STRUC_WRITE_IND	NUMBER(1)
);
/
