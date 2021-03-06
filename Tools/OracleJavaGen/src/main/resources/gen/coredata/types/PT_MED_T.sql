CREATE OR REPLACE TYPE PT_MED_T AS OBJECT (
PT_MED_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_EFF_TMSTP DATE,
REC_TERM_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
CURR_REC_IND	NUMBER(1),
CHANGE_VERSION_ID	NUMBER(38),
PT_ID	VARCHAR2(50 BYTE),
BE_ID	VARCHAR2(50 BYTE),
MED_GENERIC_NAME	VARCHAR2(100 BYTE),
MED_DOSAGE_STRG_NAME	VARCHAR2(50 BYTE),
MED_DOSAGE_FORM_NAME	VARCHAR2(50 BYTE),
MED_DOSAGE_FREQ_NAME	VARCHAR2(50 BYTE),
FREQ_TYP_LKUP_SK	NUMBER(38),
MED_ROUTE_NAME	VARCHAR2(50 BYTE),
MED_CLAS_NAME	VARCHAR2(100 BYTE),
MED_START_DATE DATE,
MED_END_DATE DATE,
MED_RANK	VARCHAR2(50 BYTE),
AUTHREIMBURSE	VARCHAR2(50 BYTE),
MED_PRN_IND	NUMBER(1),
MED_PRN_REASON	VARCHAR2(250 BYTE),
MED_RXCUI	VARCHAR2(50 BYTE),
MED_CONTRAINDICATION	VARCHAR2(50 BYTE),
MED_COMMENT	VARCHAR2(1000 BYTE),
MED_INFO_SHEET BLOB
);
/
