CREATE OR REPLACE TYPE PR_INPUT_EARN_DET_T AS OBJECT (
PR_INPUT_EARN_DET_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
CHANGE_VERSION_ID	NUMBER(38),
PR_INPUT_SK	NUMBER(38),
BE_ID	VARCHAR2(50 BYTE),
EARN_CODE	VARCHAR2(20 BYTE),
EARN_AMT	NUMBER(7, 2),
DATE_OF_SVC DATE,
RATE_TYP_NAME	VARCHAR2(20 BYTE),
EARN_RATE_AMT	NUMBER(7, 2),
EARN_HRS	NUMBER(8)
);
/
