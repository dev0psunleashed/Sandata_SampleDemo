CREATE OR REPLACE TYPE AR_HIST_T AS OBJECT (
AR_HIST_SK	NUMBER(38),
REC_CREATE_TMSTP_HIST DATE,
ACTION_CODE	VARCHAR2(8 BYTE),
AR_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CHANGE_REASON_CODE	VARCHAR2(100 BYTE),
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
CHANGE_VERSION_ID	NUMBER(38),
BE_LOB_ID	VARCHAR2(50 BYTE),
BE_LOC_ID	VARCHAR2(50 BYTE),
AR_TXN_BATCH_DET_SK	NUMBER(38),
AR_TXN_CODE	VARCHAR2(20 BYTE),
AR_REMIT_CODE	VARCHAR2(20 BYTE),
AR_TXN_AMT	NUMBER(7, 2),
AR_TXN_TOTAL_UNIT	NUMBER(8),
AR_TXN_UNIT_RATE	NUMBER(7, 2),
BE_ID	VARCHAR2(50 BYTE),
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
REFING_HCP_NPI	VARCHAR2(10 BYTE),
RENDER_PROFNL_NPI	VARCHAR2(10 BYTE),
INV_TOTAL_AMT	NUMBER(7, 2),
INV_TOTAL_UNIT	NUMBER(8),
INV_BILL_TYP_CODE	VARCHAR2(50 BYTE),
INV_MANUAL_OVERRIDE_IND	NUMBER(1),
USER_NAME	VARCHAR2(64 BYTE),
USER_GUID	VARCHAR2(128 BYTE),
MEMO	VARCHAR2(1000 BYTE),
SVC_UNIT_NAME	VARCHAR2(50 BYTE),
RATE_AMT	NUMBER(7, 2),
INV_DET_ID	VARCHAR2(50 BYTE),
INV_DET_TOTAL_UNIT	NUMBER(8),
INV_DET_TOTAL_AMT	NUMBER(7, 2),
INV_DET_SPLIT_BILLING_ALWD_IND	NUMBER(1),
VENDOR_NAME	VARCHAR2(100 BYTE),
SVC_NAME	VARCHAR2(20 BYTE),
BILLING_CODE	VARCHAR2(20 BYTE),
MDFR_1_CODE	VARCHAR2(20 BYTE),
MDFR_2_CODE	VARCHAR2(20 BYTE),
MDFR_3_CODE	VARCHAR2(20 BYTE),
MDFR_4_CODE	VARCHAR2(20 BYTE),
REV_CODE	VARCHAR2(20 BYTE),
INV_DET_DATE DATE,
INV_SUBM_STATUS	VARCHAR2(50 BYTE),
INV_DET_STATUS_CODE	VARCHAR2(20 BYTE),
RATE_TYP_NAME	VARCHAR2(20 BYTE),
RATE_QLFR_CODE	VARCHAR2(4 BYTE),
AR_NOTE_TYP_CODE	VARCHAR2(100 BYTE),
AR_NOTE	VARCHAR2(1000 BYTE),
CRN	VARCHAR2(50 BYTE),
AR_UNAPPLIED_BALANCE_IND	NUMBER(1)
);
/
