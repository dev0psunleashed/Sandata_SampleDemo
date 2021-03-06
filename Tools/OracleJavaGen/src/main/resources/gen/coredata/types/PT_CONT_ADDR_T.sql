CREATE OR REPLACE TYPE PT_CONT_ADDR_T AS OBJECT (
PT_CONT_ADDR_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_EFF_TMSTP DATE,
REC_TERM_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CURR_REC_IND	NUMBER(1),
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
CHANGE_VERSION_ID	NUMBER(38),
PT_ID	VARCHAR2(50 BYTE),
BE_ID	VARCHAR2(50 BYTE),
ADDR_PRIO_NAME	VARCHAR2(50 BYTE),
PT_ADDR_TYP_NAME	VARCHAR2(50 BYTE),
PT_ADDR_USE_FOR_MAIL_IND	NUMBER(1),
PT_ADDR1	VARCHAR2(50 BYTE),
PT_ADDR2	VARCHAR2(50 BYTE),
PT_APT_NUM	VARCHAR2(15 BYTE),
PT_CITY	VARCHAR2(50 BYTE),
PT_STATE	CHAR(2 BYTE),
PT_PSTL_CODE	VARCHAR2(5 BYTE),
PT_ZIP4	VARCHAR2(4 BYTE),
PT_COUNTY	VARCHAR2(50 BYTE),
PT_REGION	VARCHAR2(50 BYTE),
PT_BOROUGH	VARCHAR2(50 BYTE),
PT_COORD_LATITUDE	NUMBER(18, 15),
PT_COORD_LONGITUDE	NUMBER(18, 15),
PT_COORD_ALTITUDE	NUMBER(16),
PT_COORD_TMSTP DATE,
PT_ADDR_POSITION_TRK_IND	NUMBER(1)
);
/
