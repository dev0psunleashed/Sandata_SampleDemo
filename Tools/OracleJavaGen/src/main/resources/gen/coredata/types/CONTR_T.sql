CREATE OR REPLACE TYPE CONTR_T AS OBJECT (
CONTR_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_EFF_TMSTP DATE,
REC_TERM_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
CURR_REC_IND	NUMBER(1),
CHANGE_VERSION_ID	NUMBER(38),
BE_ID	VARCHAR2(50 BYTE),
TZ_NAME	VARCHAR2(64 BYTE),
PAYER_ID	VARCHAR2(50 BYTE),
CONTR_ID	VARCHAR2(50 BYTE),
CONTR_EFF_DATE DATE,
CONTR_TERM_DATE DATE,
REV_CODE	VARCHAR2(20 BYTE),
CONTR_DESC	VARCHAR2(250 BYTE),
CONTR_CONT_NAME	VARCHAR2(200 BYTE),
CONTR_CONT_TITLE	VARCHAR2(50 BYTE),
CONTR_CONT_PHONE	VARCHAR2(10 BYTE),
CONTR_CONT_EMAIL	VARCHAR2(100 BYTE),
CONTR_CONT_EMAIL_QLFR	VARCHAR2(30 BYTE),
CONTR_ADDR1	VARCHAR2(50 BYTE),
CONTR_ADDR2	VARCHAR2(50 BYTE),
CONTR_CITY	VARCHAR2(50 BYTE),
CONTR_COUNTY	VARCHAR2(50 BYTE),
CONTR_STATE	CHAR(2 BYTE),
CONTR_PSTL_CODE	VARCHAR2(5 BYTE),
CONTR_ZIP4	VARCHAR2(4 BYTE),
CONTR_PHONE	VARCHAR2(10 BYTE),
CONTR_PHONE_EXT	VARCHAR2(8 BYTE),
CONTR_PHONE_QLFR	VARCHAR2(30 BYTE),
CONTR_PHONE_1	VARCHAR2(10 BYTE),
CONTR_PHONE_1_EXT	VARCHAR2(8 BYTE),
CONTR_PHONE_1_QLFR	VARCHAR2(30 BYTE),
CONTR_FAX	VARCHAR2(10 BYTE),
CONTR_FAX_QLFR	VARCHAR2(30 BYTE),
CONTR_FAX_1	VARCHAR2(10 BYTE),
CONTR_FAX_1_QLFR	VARCHAR2(30 BYTE),
CONTR_EMAIL	VARCHAR2(100 BYTE),
CONTR_EMAIL_QLFR	VARCHAR2(30 BYTE),
CONTR_BILL_TO_CONT_NAME	VARCHAR2(200 BYTE),
CONTR_BILL_TO_CONT_TITLE	VARCHAR2(50 BYTE),
CONTR_BILL_TO_ADDR1	VARCHAR2(50 BYTE),
CONTR_BILL_TO_ADDR2	VARCHAR2(50 BYTE),
CONTR_BILL_TO_CITY	VARCHAR2(50 BYTE),
CONTR_BILL_TO_STATE	CHAR(2 BYTE),
CONTR_BILL_TO_PSTL_CODE	VARCHAR2(5 BYTE),
CONTR_BILL_TO_ZIP4	VARCHAR2(4 BYTE),
CONTR_BILL_TO_PHONE	VARCHAR2(10 BYTE),
CONTR_BILL_TO_PHONE_EXT	VARCHAR2(8 BYTE),
CONTR_REMIT_TO_CONT_NAME	VARCHAR2(200 BYTE),
CONTR_REMIT_TO_CONT_TITLE	VARCHAR2(50 BYTE),
CONTR_REMIT_TO_ADDR1	VARCHAR2(50 BYTE),
CONTR_REMIT_TO_ADDR2	VARCHAR2(50 BYTE),
CONTR_REMIT_TO_CITY	VARCHAR2(50 BYTE),
CONTR_REMIT_TO_STATE	CHAR(2 BYTE),
CONTR_REMIT_TO_PSTL_CODE	VARCHAR2(5 BYTE),
CONTR_REMIT_TO_ZIP4	VARCHAR2(4 BYTE),
CONTR_REMIT_TO_PHONE	VARCHAR2(10 BYTE),
CONTR_REMIT_TO_PHONE_EXT	VARCHAR2(8 BYTE),
CONTR_WEEK_END_DAY	VARCHAR2(10 BYTE),
CONTR_ACTIVE_IND	NUMBER(1),
CONTR_BILL_CODE	VARCHAR2(20 BYTE),
CONTR_INV_FMT_TYP_NAME	VARCHAR2(50 BYTE),
CONTR_RSBMT_INV_FMT_TYP_NAME	VARCHAR2(50 BYTE),
CONTR_CLAIM_SUBM_FREQ_TYP_NAME	VARCHAR2(50 BYTE),
CONTR_WEEKEND_START_DAY	VARCHAR2(10 BYTE),
CONTR_WEEKEND_START_TIME	VARCHAR2(50 BYTE),
CONTR_WEEKEND_END_DAY	VARCHAR2(10 BYTE),
CONTR_WEEKEND_END_TIME	VARCHAR2(50 BYTE),
CONTR_SVC_UNIT_NAME	VARCHAR2(50 BYTE),
CONTR_SVC_UNIT_EQUIV	NUMBER(38),
CONTR_LI_EQUIV	NUMBER(38),
CONTR_EDI_ROUTING_ID	VARCHAR2(50 BYTE),
CONTR_EDI_SUBMITTABLE_IND	NUMBER(1),
CONTR_HOLD_BILLING_IND	NUMBER(1),
CONTR_SPLIT_BILLING_ALWD_IND	NUMBER(1),
CONTR_NO_HOLIDAY_PAY_IND	NUMBER(1),
CONTR_ELIG_CHECK_RQD_IND	NUMBER(1),
CONTR_CDS_IND	NUMBER(1),
CONTR_BILLING_UNIT_ROUND_IND	NUMBER(1),
CONTR_SIG_RQD_IND	NUMBER(1),
CONTR_PT_INS_ID_RQD_IND	NUMBER(1),
CONTR_APPROVAL_TYP	VARCHAR2(50 BYTE),
CONTR_COVERTING_COPAY_AMT	NUMBER(7, 2),
CONTR_COPAY_AMT	NUMBER(7, 2),
CONTR_PT_ASMT_FREQ	VARCHAR2(50 BYTE),
CONTR_PT_SUPVY_VISIT_FREQ	VARCHAR2(50 BYTE),
CONTR_EDI_PROVIDER_LOC_CODE	VARCHAR2(20 BYTE),
CONTR_EDI_FMT_QLFR	VARCHAR2(50 BYTE),
CONTR_BILL_TYP_CODE	VARCHAR2(50 BYTE),
PCP_CODE	VARCHAR2(20 BYTE),
BE_LOB_ID	VARCHAR2(50 BYTE),
CONTR_NURSE_NOTE_RQD_IND	NUMBER(1),
CONTR_SVCING_LOC_QLFR	VARCHAR2(50 BYTE),
VV_RNDING_RULE_ID	VARCHAR2(50 BYTE),
CONTR_NOTE	VARCHAR2(1000 BYTE)
);
/