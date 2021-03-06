CREATE OR REPLACE TYPE AR_TXN_BATCH_DET_T AS OBJECT (
AR_TXN_BATCH_DET_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CHANGE_REASON_CODE	VARCHAR2(100 BYTE),
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
CHANGE_VERSION_ID	NUMBER(38),
BE_ID	VARCHAR2(50 BYTE),
AR_TXN_BATCH_ID	VARCHAR2(50 BYTE),
PAYER_ID	VARCHAR2(50 BYTE),
INV_NUM	VARCHAR2(50 BYTE),
CHECK_DATE DATE,
CHECK_DEPOSIT_DATE DATE,
CHECK_RCVD_DATE DATE,
PMT_TYP_QLFR	VARCHAR2(50 BYTE),
PMT_TYP_NUM	VARCHAR2(50 BYTE),
PMT_AMT	NUMBER(7, 2),
AR_NOTE_TYP_CODE	VARCHAR2(100 BYTE),
AR_TXN_NOTE	VARCHAR2(1000 BYTE),
AR_TXN_BATCH_POST_IND	NUMBER(1)
);
/
