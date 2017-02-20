CREATE OR REPLACE TYPE BILLING_DET_T AS OBJECT (
BILLING_DET_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CHANGE_REASON_CODE	VARCHAR2(100 BYTE),
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
CURR_REC_IND	NUMBER(1),
CHANGE_VERSION_ID	NUMBER(38),
BE_ID	VARCHAR2(50 BYTE),
PT_ID	VARCHAR2(50 BYTE),
CONTR_ID	VARCHAR2(50 BYTE),
PAYER_ID	VARCHAR2(50 BYTE),
TIMESHEET_DET_SK	NUMBER(38),
TIMESHEET_SUMMARY_SK	NUMBER(38),
VENDOR_NAME	VARCHAR2(100 BYTE),
BILLING_DET_DATE DATE,
BILLING_DET_STATUS_CODE	VARCHAR2(50 BYTE),
BILLING_DET_SUBM_STATUS_NAME	VARCHAR2(50 BYTE),
SVC_NAME	VARCHAR2(20 BYTE),
BILLING_CODE	VARCHAR2(20 BYTE),
MDFR_1_CODE	VARCHAR2(20 BYTE),
MDFR_2_CODE	VARCHAR2(20 BYTE),
MDFR_3_CODE	VARCHAR2(20 BYTE),
MDFR_4_CODE	VARCHAR2(20 BYTE),
REV_CODE	VARCHAR2(20 BYTE),
RENDER_HCP_NPI	VARCHAR2(10 BYTE),
RATE_TYP_NAME	VARCHAR2(20 BYTE),
RATE_QLFR_CODE	VARCHAR2(4 BYTE),
SVC_UNIT_NAME	VARCHAR2(50 BYTE),
BILLING_DET_RATE_AMT	NUMBER(7, 2),
BILLING_DET_TOTAL_AMT	NUMBER(7, 2),
BILLING_DET_TOTAL_UNIT	NUMBER(8),
SPLIT_BILLING_ALWD_IND	NUMBER(1),
USER_NAME	VARCHAR2(64 BYTE),
USER_GUID	VARCHAR2(128 BYTE),
MEMO	VARCHAR2(1000 BYTE),
VISIT_SK	NUMBER(38)
);
/
