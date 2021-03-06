CREATE OR REPLACE TYPE INV_T AS OBJECT (
INV_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_EFF_TMSTP DATE,
REC_TERM_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CHANGE_REASON_CODE	VARCHAR2(100 BYTE),
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
CURR_REC_IND	NUMBER(1),
CHANGE_VERSION_ID	NUMBER(38),
BE_ID	VARCHAR2(50 BYTE),
BE_LOC_ID	VARCHAR2(50 BYTE),
BE_LOB_ID	VARCHAR2(50 BYTE),
PAYER_ID	VARCHAR2(50 BYTE),
CONTR_ID	VARCHAR2(50 BYTE),
AUTH_ID	VARCHAR2(50 BYTE),
PT_ID	VARCHAR2(50 BYTE),
PT_INS_ID_NUM	VARCHAR2(50 BYTE),
INV_NUM	VARCHAR2(50 BYTE),
INV_STATUS_CODE	VARCHAR2(20 BYTE),
INV_SUBM_TYP_CODE	VARCHAR2(50 BYTE),
INV_START_DATE DATE,
INV_END_DATE DATE,
INV_DATE DATE,
INV_TYP_QLFR	VARCHAR2(50 BYTE),
INV_FMT_TYP_NAME	VARCHAR2(50 BYTE),
ICD_DX_CODE_REVISION_QLFR	VARCHAR2(10 BYTE),
ICD_DX_CODE_PRMY	VARCHAR2(20 BYTE),
ICD_DX_CODE_2	VARCHAR2(20 BYTE),
ICD_DX_CODE_3	VARCHAR2(20 BYTE),
ICD_DX_CODE_4	VARCHAR2(20 BYTE),
ICD_DX_CODE_5	VARCHAR2(20 BYTE),
ICD_DX_CODE_6	VARCHAR2(20 BYTE),
ICD_DX_CODE_7	VARCHAR2(20 BYTE),
ICD_DX_CODE_8	VARCHAR2(20 BYTE),
ICD_DX_CODE_9	VARCHAR2(20 BYTE),
ICD_DX_CODE_10	VARCHAR2(20 BYTE),
ICD_DX_CODE_11	VARCHAR2(20 BYTE),
ICD_DX_CODE_12	VARCHAR2(20 BYTE),
RENDER_HCP_NPI	VARCHAR2(10 BYTE),
REFING_PROFNL_NPI	VARCHAR2(10 BYTE),
INV_TOTAL_AMT	NUMBER(7, 2),
INV_TOTAL_UNIT	NUMBER(8),
INV_BILL_TYP_CODE	VARCHAR2(50 BYTE),
INV_MANUAL_OVERRIDE_IND	NUMBER(1),
USER_NAME	VARCHAR2(64 BYTE),
USER_GUID	VARCHAR2(128 BYTE),
MEMO	VARCHAR2(1000 BYTE),
CRN	VARCHAR2(50 BYTE),
CLAIM_FILING_IND_CODE	VARCHAR2(10 BYTE)
);
/
