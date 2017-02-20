CREATE OR REPLACE TYPE RACE_ETHNICITY_LKUP_T AS OBJECT (
RACE_ETHNICITY_LKUP_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_EFF_TMSTP DATE,
REC_TERM_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
CURR_REC_IND	NUMBER(1),
CHANGE_VERSION_ID	NUMBER(38),
RACE_ETHNICITY_CODE	VARCHAR2(10 BYTE),
RACE_ETHNICITY_PAR_CODE	VARCHAR2(10 BYTE),
RACE_ETHNICITY_NAME	VARCHAR2(200 BYTE),
RACE_ETHNICITY_HIERARCHY_CODE	VARCHAR2(50 BYTE),
RACE_ETHNICITY_DESC	VARCHAR2(250 BYTE)
);
/