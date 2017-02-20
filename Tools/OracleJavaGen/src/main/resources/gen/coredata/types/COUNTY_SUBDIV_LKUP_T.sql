CREATE OR REPLACE TYPE COUNTY_SUBDIV_LKUP_T AS OBJECT (
COUNTY_SUBDIV_LKUP_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_EFF_TMSTP DATE,
REC_TERM_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
CURR_REC_IND	NUMBER(1),
CHANGE_VERSION_ID	NUMBER(38),
COUNTY_SUBDIV_FIPS_CODE	VARCHAR2(10 BYTE),
COUNTY_SUBDIV_QLFR	VARCHAR2(50 BYTE),
COUNTY_SUBDIV_NAME	VARCHAR2(50 BYTE),
COUNTY_FIPS_CODE	VARCHAR2(10 BYTE),
STATE_FIPS_CODE	VARCHAR2(10 BYTE),
STATE_CODE	CHAR(5 BYTE),
COUNTY_SUBDIV_FUNC_STATUS_CODE	VARCHAR2(50 BYTE)
);
/